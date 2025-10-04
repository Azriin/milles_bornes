package cartes;

public class DebutLimite extends Limite {

	public DebutLimite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Limite 50";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DebutLimite) {
			return true;
		}
		return false;
	}
}
