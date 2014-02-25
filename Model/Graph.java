package Model;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Vertex> vertex;
	private ArrayList<Edge> edges;
	private ArrayList<Attribute> attributes;
	private ArrayList<Method> methods;

	public Graph() {
		this.vertex = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		this.attributes = new ArrayList<Attribute>();
		this.methods = new ArrayList<Method>();
	}
}