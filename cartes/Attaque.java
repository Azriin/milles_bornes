package cartes;

public class Attaque extends Bataille {

	@Override
	public String toString() {
		return getType().toString();
	}
	
	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
