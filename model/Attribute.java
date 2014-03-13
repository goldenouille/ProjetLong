package model;

public class Attribute implements GraphItem{

	private Type type;	/* the type of the attribut, it is designed to be either a TypeBase or a Vertex
						but in this current verstion of L.U.N.E, it can only but a TypeBase. If it has to
						be something which is not a basic java type, it takes the value TypeBase.ANY */

	private String name;	// name of the attribute
	private Visibility visibility;	// visibility of the attribute
	private String frenchName;	// is used overrite the fonction 'toString' to print the UML type in french
	private int id;		// id of the attribute
	private Vertex motherClass;		// vertex to which it is linked
	private boolean isDeletable;	// says if the attribute can be deleted. If it is validated, it can not deleted.
	private int score;

	/**
	 * Create a basic attribut
	 * 
	 */
	public Attribute () {
		this.frenchName = "attribut";
		this.isDeletable = true;
	}

	/**
	 * Create a complet attribut
	 * 
	 * @param name
	 *            	name of the attribute
	 * @param type
	 * 			  	type of the attribut
	 * @param visibility
	 *				visibility of the attribut
	 * @param mother
	 *				vertex to which the attribut is linked
	 * @param id
	 *				id of the attribut			
	 * @param score 
	 */
	public Attribute (String name, Type type, Visibility visibility, Vertex mother, int id, int score) {
		this.name = name;
		this.type = type;
		this.visibility = visibility; 
		this.frenchName = "attribut";
		this.motherClass = mother;
		this.id = id;
		this.isDeletable = true;
		this.score = score;
	}
	
	/**
	 * Create an attribut with its name, its type and its visibility
	 * 
	 * @param name
	 *				name of the attribut
	 * @param type
	 *         		type of the attribut
	 * @param visibility
	 *				visibility of the attribut
	 */
	public Attribute (String name, Type type, Visibility visibility) {
		this.name = name;
		this.type = type;
		this.visibility = visibility;
		this.frenchName = "attribut";
		this.id = -1;
		this.isDeletable = true;
	}

	public boolean isDeletable() {
		return this.isDeletable;
	}

	public void validate() {
		this.isDeletable = false;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type newType) {
		this.type = newType;
	}

	public Visibility getVisibility() {
		return this.visibility;
	}

	public void setVisibility(Visibility newVisibility) {
		this.visibility = newVisibility;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int n) {
		this.id = n;
	}

	/*
	Set a motherClass to the attribut.
	Only classes and abstract classes can have attributes, so vertex can not represent an interface
	This is checked when the fonction is called in Exercice.java to link an attribute and a class
	*/
	public void setMotherClass(Vertex vertex) {
		if (vertex instanceof VertexClass) {
			this.motherClass = vertex;
		}
	}

	public Vertex getMotherClass() {
		return this.motherClass;
	}

	@Override
	public UMLNature getUmlNature() {
		return UMLNature.ATTRIBUTE;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return this.name;
	}
}
