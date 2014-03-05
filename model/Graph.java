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

	public ArrayList<Vertex> getVertex() {
		return vertex;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public HashMap<Integer, GraphItem> getIdTable() {
		return idTable;
	}

	private void removeEdgeFrom(Vertex v) {
		// kill all the edges linked to v
		ArrayList<Edge> ed = v.getEdges();
		this.edges.remove(ed);
		for (Edge e: ed) {
			ArrayList<Vertex> vs = e.getVertex();
			for (int i = 0; i< vs.size(); i++) {
				vs.get(i).getEdges().remove(e);
			}
		}
	}

	private void removeAttributeFrom(VertexClass vc) {
		// unlink all the attributes from vc
		ArrayList<Attribute> att = vertexClass.getAttributes();
		for (Attribute a : att) {
			a.setMotherClass(null);
		}
	}

	private void removeMethodFrom(Vertex v) {
		// unlink all the methods from v
		ArrayList<Attribute> att = vertexClass.getAttributes();
		for (Attribute a : att) {
			a.setMotherClass(null);
		}
	}

	public void removeClass(VertexClass vertexClass) {
		// remove vertexClass form the Vertex List
		this.vertex.remove(vertexClass);
		removeEdgeFrom(vertexClass);
		removeAttributeFrom(vertexClass);
		removeMethodFrom(vertexClass);
		// TODO retirer les methodes qui ont comme type la classe retiree
		// pour l'instant non gere.


	}


	public void removeAbstractClass(VertexAbstract vertexAbstract) {
		// remove vertexAbstract form the Vertex List
		this.vertex.remove(vertexAbstract);
		removeEdgeFrom(vertexAbstract);
		removeAttributeFrom(vertexAbstract);
		removeMethodFrom(vertexAbstract);
		// TODO retirer les methodes qui ont comme type la classe retiree
		// pour l'instant non gere.
	}

	/**
	 * Sends to the core the user's request to delete the given interface instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void removeInterface(Vertex v) {
		// remove vertex form the Vertex List
		this.vertex.remove(v);
		removeEdgeFrom(v);
		removeMethodFrom(v);
		// TODO retirer les methodes qui ont comme type la classe retiree
		// pour l'instant non gere.
	}

	/**
	 * Sends to the core the user's request to delete the given attribute instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void removeAttribute(Attribute att) {
		this.attributes.remove(att);
		((VertexClass) att.getMotherClass()).getAttributes().remove(att);
	}

	/**
	 * Sends to the core the user's request to delete the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void removeMethod(Method met) {
		this.methods.remove(met);
		met.getMotherClass().getMethods().remove(met);
	}
	
}
