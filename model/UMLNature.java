package model;

public enum UMLNature {
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
	REALIZATION("relisation");

	private String name;

	UMLNature(String name) {
		this.name = name;
	}
}	