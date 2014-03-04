package model;

import java.util.ArrayList;

public class Vertex implements Type, GraphItem {

	protected String name;
	protected int id;
	protected ArrayList<Method> methods;
	protected ArrayList<Edge> edges;
	protected String frenchName;
	
	public Vertex() {
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
		this.frenchName = "interface";
	}

	public Vertex(String name, int id) {
		this.name = name;
		this.id = id;
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
		this.frenchName = "interface";
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

/*	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}
*/	

	public ArrayList<Method> getMethods() {
		return this.methods;
	}

	public void addMethode(Method method) {
		this.methods.add(method);
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}


}
