package cartes;

public class Parade extends Bataille {

	@Override
	public String toString() {
		return getType().getMsgParade();
	}
	
	public Parade(Type type) {
		super(type);
	}
}
