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
	
	public void addVertex(Vertex v) {
		this.vertex.add(v);
	}
	
	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	public void addAttribute(Attribute a) {
		this.attributes.edd(a);
	}
	
	public void addMethod(Method m) {
		this.methods.add(m);
	}
}
