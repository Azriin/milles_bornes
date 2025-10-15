package utils;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private static <E> List<E> ajoutConsecutif(List<E> l, E elt){
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).equals(elt)) {
				l.add(i, elt);
				return l;
			}
		}
		l.add(elt);
		return l;
	}
	
	public static <E> List<E> rassembler(List<E> l) {
		List<E> listRassembler = new ArrayList<>();
		for (ListIterator<E> iterator = l.listIterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			listRassembler = ajoutConsecutif(listRassembler, e);
		}
		return listRassembler;
	}

	private static <E> boolean existeResteListe(List<E> l, int indice) {
		E eCherche = l.get(indice-1);
		int indiceIte = indice;
		for (ListIterator<E> iterator = l.listIterator(indiceIte); iterator.hasNext();) {
			E e = (E) iterator.next();
			if (e.equals(eCherche)) {
				return true;
			}
		}
		return false;
	}
	
	public static <E> boolean verifierRassemblement(List<E> l) {
		
		E eRef = l.get(0);
		int indice = 0;
		for (ListIterator<E> iterator = l.listIterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			if (!(eRef.equals(e)) && existeResteListe(l, indice)) {
				System.out.println("indice = " + indice);
				return false;
			}
			eRef = e;
			indice ++;
		}
		return true;
	}
}