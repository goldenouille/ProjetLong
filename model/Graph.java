package model;

import java.util.AbstractMap;
import java.util.HashMap;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Vertex> vertex;
	private ArrayList<Edge> edges;
	private ArrayList<Attribute> attributes;
	private ArrayList<Method> methods;
	private HashMap<Integer,GraphItem> idTable;

	public Graph() {
		this.vertex = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		this.attributes = new ArrayList<Attribute>();
		this.methods = new ArrayList<Method>();
		this.idTable = new HashMap<Integer,GraphItem>();
	}
	
	public void addVertex(Vertex v) {
		this.vertex.add(v);
	}
	
	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	public void addAttribute(Attribute a) {
		this.attributes.add(a);
	}
	
	public void addMethod(Method m) {
		this.methods.add(m);
	}

	public void setIdTable(HashMap<Integer, GraphItem> iT) {
		this.idTable = iT;
	}
}
