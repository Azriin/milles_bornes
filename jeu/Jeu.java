package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	Sabot sabot;
		
	public Jeu() {
		Carte[] jeuDeCarte = new JeuDeCartes().donnerCartes();
		List<Carte> lstCarte = new ArrayList<>();
		Collections.addAll(lstCarte, jeuDeCarte);
		lstCarte = GestionCartes.melanger(lstCarte);
		jeuDeCarte = (Carte[]) lstCarte.toArray();
		this.sabot = new Sabot(jeuDeCarte);
	}
}
