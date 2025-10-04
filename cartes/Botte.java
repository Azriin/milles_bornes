package cartes;

public class Botte extends Probleme {

	@Override
	public String toString() {
		return getType().getMsgBotte();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte botte) {
			return super.equals(botte);
		}
		return false;
	}
	
	
	public Botte(Type type) {
		super(type);
	}

}
