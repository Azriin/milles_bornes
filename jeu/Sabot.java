package jeu;

import java.util.ConcurrentModificationException;

import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLDocument.Iterator;

import cartes.Carte;

public class Sabot {
	private Carte[] cartes;
	private int nbCartes = 110;
	
	private Iterateur<Carte> ite = new Iterateur<>();
	private int nbOperationReference = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes < cartes.length) {
			cartes[nbCartes ++] = carte;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	
	private class Iterateur implements Iterator<E> {
		private int pointeur = 0;
		private boolean canRemove = false;
		private int nbOperation = nbOperationReference;
		
		private void verifierConcurence() {
			if (nbOperation != nbOperationReference) {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public E next() {
			verifierConcurence();
			if (hasNext()) {
				canRemove = true;
				return cartes[pointeur++];
			} else {
				throw new IllegalStateException();
			}
		}

		public boolean hasNext() {
			return nbCartes > pointeur;
		}
		
		public void remove() {
			verifierConcurence();
			canRemove = false;
		}
		
	}
}
