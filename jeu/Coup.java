package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur courant;
	private Joueur cible;
	private Carte carte;
	
	public Carte getCarte() {
		return carte;
	}
	
	public Joueur getCible() {
		return cible;
	}
	
	public Joueur getCourant() {
		return courant;
	}

	public void setCourant(Joueur courant) {
		this.courant = courant;
	}

	public void setCible(Joueur cible) {
		this.cible = cible;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	public boolean estValide() {
		return ((carte instanceof Attaque || carte instanceof Limite) && !cible.equals(courant)
			|| (cible.equals(courant)));
	}
	
}