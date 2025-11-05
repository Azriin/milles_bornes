package cartes;

public enum Type {
	FEU("Feu rouge", "Feu vert", "Vehicule prioritaire"), 
	ESSENCE("Panne d'essence", "Essence", "Citerne d'essence"), 
	CREVAISON("Crevaison", "Roue de secours", "Increvable"), 
	ACCIDENT("Accident", "Reparations", "As du volant");
	
	private String msgAttaque;
	private String msgParade;
	private String msgBotte;
	
	Type(String attaque, String parade, String botte){
		this.msgAttaque = attaque;
		this.msgParade = parade;
		this.msgBotte = botte;
	}

	public String getMsgAttaque() {
		return msgAttaque;
	}

	public String getMsgParade() {
		return msgParade;
	}

	public String getMsgBotte() {
		return msgBotte;
	}
	
	
	
}
