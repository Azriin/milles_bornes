package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur = new MainJoueur();
	
	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}
	
	@Override
	public int hashCode() {
		return 31*nom.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return nom != null && nom.equals(joueur.getNom());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		Carte carte = sabot.piocher();
		if (carte != null) {
			donner(carte);
		}
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer (Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	private Set<Coup> coupsContre(Joueur joueur) {
		Set<Coup> setCoup= new HashSet<>();
		for (ListIterator<Carte> iteCarte = this.mainJoueur.getMain().listIterator(); iteCarte.hasNext();) {
			Carte carte = (Carte) iteCarte.next();
			Coup coup = new Coup(this, joueur, carte);
			if (coup.estValide()) {
				setCoup.add(coup);
			}
		}
		return setCoup;
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> setPossible = new HashSet<>();
		for (Iterator<Joueur> iteJoueur = participants.iterator(); iteJoueur.hasNext();) {
			Joueur joueur = (Joueur) iteJoueur.next();
			setPossible.addAll(coupsContre(joueur));
		}
		return setPossible;
	}
	
	public Set<Coup> coupDefausse() {
		Set<Coup> setDefausse = new HashSet<>();
		setDefausse.addAll(coupsContre(null));
		return setDefausse;
	}
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.jouer(carte);
	}
	
	private Coup coupAleatoire(Set<Coup> setCoup) {
		int indice = (int) (Math.random()*setCoup.size());
		Coup[] lstCoup = setCoup.toArray(new Coup[0]);
		return lstCoup[indice];
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Coup coup = null;
		Set<Coup> coupParticipants = coupsPossibles(participants);
		Set<Coup> coupDefausse = coupDefausse();
		if (!coupParticipants.isEmpty()) {
			coup = coupAleatoire(coupParticipants);
		} else if (!coupDefausse.isEmpty()) {
			coup = coupAleatoire(coupDefausse);
		}
		return coup;
	}
	
	public String afficherEtatJoueur() {
		StringBuilder sb = new StringBuilder();
		sb.append(zoneDeJeu.getBottes());
		sb.append(zoneDeJeu.donnerLimitationVitesse() == 50);
		sb.append(zoneDeJeu.getSommetPileBataille());
		
		sb.append(mainJoueur.getMain());
		
		return sb.toString();
	}
}
