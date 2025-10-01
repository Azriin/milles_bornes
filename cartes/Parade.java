package cartes;

public class Parade extends Bataille {

	@Override
	public String toString() {
		return getType().toString();
	}
	
	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
