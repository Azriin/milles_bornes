package cartes;

public class Botte extends Probleme {

	@Override
	public String toString() {
		return getType().getMsgBotte();
	}
	
	public Botte(Type type) {
		super(type);
	}

}
