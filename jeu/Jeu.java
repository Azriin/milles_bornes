package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private final int NBCARTES = 6;
	private Iterator<Joueur> iteJoueur;
	
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
		StringBuilder sb = new StringBuilder();
		Carte carte = sabot.piocher();
		sb.append("Le joueur " + joueur + " a pioche " + carte + "\n");
		joueur.donner(carte);
		Coup coup = joueur.choisirCoup(joueurs);
//		if (coup == null) {
//			return "coup null";
//		}
		joueur.retirerDeLaMain(coup.getCarte());
		sb.append("il a dans sa main : " + joueur.getMainJoueur() + "\n");
		if (coup.getCible() == null) {
			sabot.ajouterCarte(coup.getCarte());
		} else {
			coup.getCible().deposer(coup.getCarte());
		}
		sb.append(joueur + " " + coup + "\n");
		return sb.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iteJoueur.hasNext()) {
			iteJoueur = joueurs.iterator();
		}
		return iteJoueur.next();
	}
	
	public String lancer() {
		StringBuilder sb = new StringBuilder();
		iteJoueur = joueurs.iterator();
		Joueur joueurCourant;
		do {
			joueurCourant = donnerJoueurSuivant();
			sb.append(jouerTour(joueurCourant));
		} while (joueurCourant.donnerKmParcourus() < 1000);
		Joueur premier = joueurCourant;
		
		sb.append("\nClassement: \n");
		do {
			sb.append(joueurCourant + " " + joueurCourant.donnerKmParcourus() + "km\n");
			joueurCourant = donnerJoueurSuivant();
		} while (joueurCourant != premier);
		return sb.toString();
	}
}
