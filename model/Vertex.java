package model;

import java.util.ArrayList;

public class Vertex implements Type, GraphItem {

	protected String name;
	protected int id;
	protected ArrayList<Method> methods;
	protected ArrayList<Edge> edges;
	protected String frenchName;
	protected boolean isDeletable;
	
	public Vertex() {
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
		this.frenchName = "interface";
		this.id = -1;
		this.isDeletable = true;
	}

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

	public void addMethod(Method method) {
		this.methods.add(method);
	}

	public void removeMethod(Method met) {
		this.methods.remove(met);
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	public UMLNature getUmlNature() {
		return UMLNature.INTERFACE;
	}
}
