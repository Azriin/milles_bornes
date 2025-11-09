package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs = new HashSet<>();
	private final int NBCARTES = 6;
	
	public Jeu() {
		Carte[] jeuDeCarte = new JeuDeCartes().donnerCartes();
		List<Carte> lstCarte = new ArrayList<>();
		Collections.addAll(lstCarte, jeuDeCarte);
		lstCarte = GestionCartes.melanger(lstCarte);
		jeuDeCarte = lstCarte.toArray(new Carte[0]);
		this.sabot = new Sabot(jeuDeCarte);
	}
	
	public void inscrire(Joueur...joueurs) {
		for (Joueur joueur : joueurs) {
			this.joueurs.add(joueur);
		}
	}
	
	public void distribuerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Iterator<Joueur> iterator = joueurs.iterator(); iterator.hasNext();) {
				Joueur joueur = (Joueur) iterator.next();
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
		Coup coup = joueur.choisirCoup(joueurs);
		joueur.retirerDeLaMain(coup.getCarte());
		if (coup.getCible() == null) {
			sabot.ajouterCarte(coup.getCarte());
		} else {
			coup.getCible().donner(coup.getCarte());
		}
		return coup.toString();
	}
	
}
