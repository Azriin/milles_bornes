package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	private List<Carte> main = new ArrayList<>();
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		return main.toString();
	}
	
	public List<Carte> getMain() {
		return main;
	}
}
