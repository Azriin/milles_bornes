package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> collectionBorne = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	public int donnerLimitationVitesse() {
		if (pileLimite.size() == 0 || pileLimite.getLast().equals(new FinLimite()) || estPrioritaire()) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int somme = 0;
		for (ListIterator<Borne> iterator = collectionBorne.listIterator(); iterator.hasNext();) {
			Borne borne = (Borne) iterator.next();
			somme += borne.getKm();
			
		}
		return somme;
	}
	
	public void deposer (Carte c) {
		if (c instanceof Borne borne) {
			collectionBorne.addLast(borne);
		} else if (c instanceof Limite limite) {
			pileLimite.addLast(limite);
		} else if (c instanceof Bataille bataille) {
			pileBataille.addLast(bataille);
		}  else if (c instanceof Botte botte) {
			bottes.add(botte);
		}
	}
	
	public boolean peutAvancer() {
//		System.out.println("prio ? " + estPrioritaire() + " | empty ? " + pileBataille.isEmpty());
		
		if (!pileBataille.isEmpty()) {
			if (pileBataille.getLast().equals(Cartes.FEU_VERT)) {
				return true;
			} if (estPrioritaire()) {
				Bataille sommet = pileBataille.getLast();
//				System.out.println("sommet : " + sommet + " | botte ? " + bottes.contains(new Botte(sommet.getType())));
				return (sommet.getClass().equals(new Parade(null).getClass()))
					|| (sommet.equals(Cartes.FEU_ROUGE))
					|| (sommet.getClass().equals(new Attaque(null).getClass()) && bottes.contains(new Botte(sommet.getType())));
			}
		} 
		return estPrioritaire();
	}
	
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {return false;}
		if (pileBataille.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBataille.getLast();
		return sommet.equals(new Attaque(Type.FEU))
			|| !sommet.equals(new Parade(Type.FEU))
			|| (sommet.getClass().equals(new Attaque(null).getClass()) && bottes.contains(new Botte(sommet.getType()))) ;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() 
			&& borne.getKm() <= donnerLimitationVitesse() 
			&& (donnerKmParcourus()+borne.getKm() <= 1000);
	}
	
	private boolean estDepiotLimiteAutorise(Limite limite) {
		if (estPrioritaire()) {
			return false;
		}
		if (limite.equals(new DebutLimite())) {
			return pileLimite.isEmpty() || pileLimite.getLast().equals(new FinLimite());
		} 
		return pileLimite.getLast().equals(new DebutLimite());
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bottes.contains(new Botte(bataille.getType()))) {
			return false;
		}
		
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} if (bataille.equals(new Parade(Type.FEU))) {
			return estDepotFeuVertAutorise();
		}
		return !pileBataille.isEmpty() && pileBataille.getLast().getType().equals(bataille.getType());
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else if (carte instanceof Limite limite) {
			return estDepiotLimiteAutorise(limite);
		} else if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Botte) {
			return true;
		} return false;
	}
	
	private boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
}
