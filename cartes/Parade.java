package cartes;

public class Parade extends Bataille {

	@Override
	public String toString() {
		return getType().getMsgParade();
	}
	
	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parade parade) {
			return super.equals(parade);
		}
		return false;
	}
	
}
