package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

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
		int hashCible = 0;
		if (cible != null) {
			hashCible += cible.hashCode();
		}
		return 7*(courant.hashCode()+hashCible+carte.hashCode());
	}
	
	@Override
	public String toString() {
		if (cible == null) {
			return "defausse la carte " + carte;
		}
		return "depose la carte " + carte + " dans la zone de jeu de " + cible;
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
		if (cible == null) {
			return true;
		}
		if (!cible.equals(courant)) {
			return (carte instanceof Attaque || carte instanceof DebutLimite) && cible.estDepotAutorise(carte);
		}
		return !(carte instanceof Attaque || carte instanceof DebutLimite) && cible.estDepotAutorise(carte);
	}
	
}