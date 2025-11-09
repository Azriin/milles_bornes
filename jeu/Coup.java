package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur courant;
	private Joueur cible;
	private Carte carte;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup coup) {
			return  (carte.equals(coup.getCarte()))
					&& (courant.equals(coup.getCourant()))
					&& ((cible == null && coup.getCible() == null) 
					|| (cible != null && cible.equals(coup.getCourant())));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 7*(courant.hashCode()+cible.hashCode()+carte.hashCode());
	}
	
	@Override
	public String toString() {
		if (cible == null) {
			return "defausse la carte" + carte;
		}
		return "depose la coarte " + carte + " dans la zone de jeu de " + cible;
	}
	
	public Carte getCarte() {
		return carte;
	}
	
	public Joueur getCible() {
		return cible;
	}
	
	public Joueur getCourant() {
		return courant;
	}
	
	public Coup(Joueur courant, Joueur cible, Carte carte) {
		this.courant = courant;
		this.cible = cible;
		this.carte = carte;
	}

	public boolean estValide() {
		return ((carte instanceof Attaque || carte instanceof Limite) && !cible.equals(courant)
			|| (cible.equals(courant)));
	}
	
}