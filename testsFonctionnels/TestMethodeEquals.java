package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {

	public static void main(String[] args) {
		Borne borne25A = new Borne(25);
		Borne borne25B = new Borne(25);
		System.out.println("Deux borne 25KM sont identiques ? " + borne25A.equals(borne25B));

		Attaque feuA = new Attaque(Type.FEU);
		Attaque feuB = new Attaque(Type.FEU);
		System.out.println("Deux feux rouges sont identiques ? " + feuA.equals(feuB));
		
		Parade feuC = new Parade(Type.FEU);
		System.out.println("feu rouge et feu vert sont identiques ? " + feuA.equals(feuC));
		
	}

}
