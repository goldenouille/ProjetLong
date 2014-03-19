package model;

// UMLNautre is a class reprensenting the UML natures
public enum UMLNature {
					// list of the constances with their name in frech
	ABSTRACT_CLASS("classe abstraite"),
	AGGREGATION("aggregation"),
	ASSOCIATION("assocition"),
	ATTRIBUTE("attribut"),
	CLASS("classe"),
	COMPOSITION("composition"),
	DEPENDANCY("dependance"),
	GENERALIZATION("generalisation"),
	INTERFACE("interface"), 
	METHOD("methode"),
	N_ASSOCIATION("assocition n-aire"),
	REALIZATION("realisation");

	private String name; // french name of the UMLNature item

	/**
	 * Create a UMLNature from a name
	 *
	 * @param name
	 *			name which has to be set in the new UMLNature item
	 */
	public UMLNature(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
	
	/**
	 * Compare the item with another UMLNature instance
	 *
	 * @param uml
	 *			UMLNature item to which it has to been compared to
	 * @return if the two are equals 
	 */
	public boolean equals(UMLNature uml) {
		return uml.name().equalsIgnoreCase(this.name());
	}
}	