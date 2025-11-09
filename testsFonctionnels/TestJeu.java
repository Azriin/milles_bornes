package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;
import jeu.ZoneDeJeu;

public class TestJeu {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur joueur1 = new Joueur("Jack", new ZoneDeJeu());
		Joueur joueur2 = new Joueur("Bill", new ZoneDeJeu());
		Joueur joueur3 = new Joueur("Luffy", new ZoneDeJeu());
		jeu.inscrire(joueur1, joueur2, joueur3);
		jeu.distribuerCartes();
		jeu.jouerTour(joueur1);
		jeu.jouerTour(joueur2);
		jeu.jouerTour(joueur3);
		
	}
}
