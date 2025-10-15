package jeu;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private int nbCartes = 106;
	
	private Iterator<Carte> ite = iterator();
	private int nbOperationReference = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		nbCartes = cartes.length;
		
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes < cartes.length) {
			cartes[nbCartes ++] = carte;
			nbOperationReference ++;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	public Carte piocher() {		
		Carte carte = null;
		if (ite.hasNext()) {
			carte = ite.next();
			ite.remove();
		}
		return carte;
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int pointeur = 0;
		private boolean canRemove = false;
		private int nbOperation = nbOperationReference;
		
		private void verifierConcurence() {
			if (nbOperation != nbOperationReference) {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public Carte next() {
			verifierConcurence();
			if (hasNext()) {
				canRemove = true;
				nbOperation ++;
				nbOperationReference ++;
				return cartes[pointeur++];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public boolean hasNext() {
			return pointeur < nbCartes;
		}
		
		@Override
		public void remove() {
			verifierConcurence();
			if (!canRemove) {
				throw new IllegalStateException();
			}
			for (int i = pointeur-1; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			
			canRemove = false;
			nbOperationReference ++;
			nbOperation ++;
			nbCartes --;
			pointeur --;
		}
		
	}

}
