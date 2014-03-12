package model;

import java.util.ArrayList;

// this class represents interfaces
public class Vertex implements Type, GraphItem {

	protected String name;
	protected int id;

	protected ArrayList<Method> methods;	// list of the methods of the interface
	protected ArrayList<Edge> edges;	// list of relations this interface has with others elements
	protected String frenchName;
	protected boolean isDeletable;	// if it can be deleted - if the item has been validated, it can not deleted
	

	/**
	 * Create a basic interface
	 * 
	 */
	public Vertex() {
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
		this.frenchName = "interface";
		this.id = -1;
		this.isDeletable = true;
	}

	/**
	 * Create an interface with its name and its id
	 * 
	 * @param name
	 *            	name of the interface
	 * @param id
	 *				id of the interface
	 */
	public Vertex(String name, int id) {
		this.name = name;
		this.id = id;
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
		this.frenchName = "interface";
		this.isDeletable = true;
	}

	public void validate() {
		this.isDeletable = false;
	}

	public boolean isDeletable() {
		return this.isDeletable;
	}
	
	public String getUml() {
		return "interface";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<Method> getMethods() {
		return this.methods;
	}

	/**
	 * Add a method to the methods list of the interface
	 * 
	 * @param method
	 *            	the method to add
	 */
	public void addMethod(Method method) {
		this.methods.add(method);
	}

	/**
	 * Remove a method from the methods list of the interface
	 * 
	 * @param method
	 *            	method to remove
	 */
	public void removeMethod(Method met) {
		this.methods.remove(met);
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}


	/**
	 * Add an Edge to the edges list of the interface
	 * 
	 * @param edge
	 *            	edge to add
	 */
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	public UMLNature getUmlNature() {
		return UMLNature.INTERFACE;
	}
}
