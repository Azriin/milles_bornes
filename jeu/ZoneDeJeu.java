package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

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
			collectionBorne.add(borne);
		} else if (c instanceof Limite limite) {
			pileLimite.addLast(limite);
		} else if (c instanceof Bataille bataille) {
			pileBataille.addLast(bataille);
		}
	}
}
