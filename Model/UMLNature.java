public enum UMLNature {
	ABSTRACT_CLASS("classe abstraite"),
	AGGREGATION("aggrégation"),
	ASSOCIATION("assocition"),
	ATTRIBUTE("attribut"),
	CLASS("classe"),
	COMPOSITION("composition"),
	DEPENDANCY("dépendance"),
	GENERALIZATION("généralisation"),
	INTERFACE("interface"), 
	METHOD("méthode"),
	N_ASSOCIATION("assocition n-aire"),
	REALIZATION("rélisation");

	private String name;

	UMLNature(String name) {
		this.name = name;
	}
}	