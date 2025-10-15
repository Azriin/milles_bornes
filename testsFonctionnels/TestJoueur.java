package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.Joueur;
import jeu.ZoneDeJeu;

public class TestJoueur {
	public static void main(String[] args) {
		Joueur joueur = new Joueur("Moi", new ZoneDeJeu());
		System.out.println("Deposer carte 25 km");
		joueur.deposer(new Borne(25));
		System.out.println("Deposer carte 50 km");
		joueur.deposer(new Borne(50));
		System.out.println("Deposer carte 75 km");
		joueur.deposer(new Borne(75));
		System.out.println("Total des bornes : " + joueur.donnerKmParcourus());
		System.out.println("Limite : " + joueur.donnerLimitationVitesse());
		joueur.deposer(new DebutLimite());
		System.out.println("Limite : " + joueur.donnerLimitationVitesse());
		joueur.deposer(new FinLimite());
		System.out.println("Limite : " + joueur.donnerLimitationVitesse());
	}
}
