package cartes;

public class Attaque extends Bataille {

	@Override
	public String toString() {
		return getType().getMsgAttaque();
	}
	
	public Attaque(Type type) {
		super(type);
	}	
}
