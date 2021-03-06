package model;

import java.util.ArrayList;

// this class represents an abstract class
public class VertexAbstract extends VertexClass {
	
	/**
	 * Creat an abstract class with its name and its id
	 * 
	 * @param name
	 *            	name to give to the abstract class
	 * @param id
	 *				id to give to the abstract class
	 * @param score 
	 */
	public VertexAbstract(String name, int id, int score) {
		super(name, id,score);
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe abstraite";
	}

	public VertexAbstract() {
		super();
		this.frenchName = "classe abstraite";
	}

	/**
	 * Get the name of the UML nature of the element
	 *
	 * @return the name of the UML nature of the element
	 */
	public String getUml() {
		return "abstract";
	}
	
	public UMLNature getUmlNature() {
		return UMLNature.ABSTRACT_CLASS;
	}
}
