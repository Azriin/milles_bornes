package cartes;

public class FinLimite extends Limite {

	public FinLimite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Fin Limite";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FinLimite) {
			return true;
		}
		return false;
	}
}
