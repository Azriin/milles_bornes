package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class GestionCartes {
	public static <E> E extraire1(List<E> l) {
		int idCarte = (int)(Math.random()*l.size());
		return l.remove(idCarte);
	}
	
	public static <E> E extraire2(List<E> l) {
		int idElt = (int)(Math.random()*l.size());
		E elt;
		for (ListIterator<E> iterator = l.listIterator(); iterator.hasNext();) {
			elt = (E) iterator.next();
			if (idElt == 0) {
				iterator.remove();
				return elt;
			}
			idElt --;
		}
		throw new ArrayIndexOutOfBoundsException();
	}
	
	public static <E> List<E> melanger(List<E> l){
		List<E> newL = new ArrayList<>();
		while (!l.isEmpty()) {
			newL.add(extraire1(l));
		}
		return newL;
	}
	
	public static <E> boolean verifierMelange(List<E> l1, List<E> l2) {
		for (ListIterator<E> iterator = l1.listIterator(); iterator.hasNext();) {
			E elt = (E) iterator.next();
			if (Collections.frequency(l1, elt) != Collections.frequency(l2, elt)) {
				return false;
			}
		}
		return true;
	}
	
	public static <E> List<E> rassembler(List<E> l) {
		
//		Carte carteRef = l.get(0);
//		for (ListIterator<Carte> iterator = l.listIterator(); iterator.hasNext();) {
//			Carte carte = (Carte) iterator.next();
//			if (carteRef.equals(carte)) {
//				iterator.remove();
//				iterator.add(0, carte);
//			}
//		}
//		
//		return l;
		return null;
	}

	private static <E> boolean existeResteListe(List<E> l, int indice) {
		E eCherche = l.get(indice);
		int indiceIte = indice+1;
		if (indiceIte == l.size()) {
			return false;
		}
		for (ListIterator<E> iterator = l.listIterator(indiceIte); iterator.hasNext();) {
			E e = (E) iterator.next();
			if (e == eCherche) {
				return true;
			}
		}
		return false;
	}
	
	public static <E> boolean verifierRassemblement(List<E> l) {
		E eRef = l.getFirst();
		int indice = 0;
		for (ListIterator<E> iterator = l.listIterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			if (eRef != e && existeResteListe(l, indice)) {
				return false;
			}
			eRef = e;
			indice ++;
		}
		return true;
	}
}