package testsFonctionnels;

import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Sabot;

public class TestSabot {

	public static void main(String[] args) {
		Carte[] cartes = new JeuDeCartes().donnerCartes();
		Sabot sabot = new Sabot(cartes);
		
		int i = 1;
		while (!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println(i + ". Je pioche " + carte);
			i++;
		}

	}

}
