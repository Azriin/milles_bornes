package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
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
	
	public int donnerLimitationVitesse() {
		if (pileLimite.size() == 0 || pileLimite.getLast().equals(new FinLimite())) {
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
		}
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && pileBataille.getLast().equals(new Parade(Type.FEU));
	}
	
	private boolean estDepotFeuVertAutorise() {
		// l'inverse de peutAvancer ?
		return pileBataille.isEmpty() 
			|| pileBataille.getLast().equals(new Attaque(Type.FEU))
			|| !pileBataille.getLast().equals(new Parade(Type.FEU));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() 
			&& borne.getKm() <= donnerLimitationVitesse() 
			&& (donnerKmParcourus()+borne.getKm() <= 1000);
	}
	
	private boolean estDepiotLimiteAutorise(Limite limite) {
		if (limite.equals(new DebutLimite())) {
			return pileLimite.isEmpty() || pileLimite.getLast().equals(new FinLimite());
		} else {
			return pileLimite.getLast().equals(new DebutLimite());
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
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
		} return false;
	}
}
