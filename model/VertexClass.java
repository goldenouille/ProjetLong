package model;

import java.util.ArrayList;

// this class represents a class
public class VertexClass extends Vertex {

	protected ArrayList<Attribute> attributes;  	// list of the attributes


	/**
	 * Create a vertexClass with its name and its id
	 * 
	 * @param name
	 *            	name of the class
	 * @param id
	 *				id of the class
	 */
	public VertexClass(String name, int id) {
		super(name, id);
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe";
	}

	public VertexClass() {
		super();
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe";
	}
	
	/**
	 * Get all the attributes
	 * 
	 * @return the list of the attributes			
	 */
	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	/**
	 * Add an attribute to the class's attributes
	 * 
	 * @param attribute
	 *            	the attribute to add
	 */
	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}

	/**
	 * Remove an attribute from the class's attributes list
	 * 
	 * @param attribute
	 *            	the attribute to remove
	 */
	public void removeAttribute(Attribute att) {
		this.attributes.remove(att);
	}
	
	public String getUml() {
		return "class";
	}

	public UMLNature getUmlNature() {
		return UMLNature.CLASS;
	}
}
