package cartes;

public class Attaque extends Bataille {

	@Override
	public String toString() {
		return getType().getMsgAttaque();
	}
	
	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Attaque attaque) {
			return super.equals(attaque);
		}
		return false;
	}
	
}
