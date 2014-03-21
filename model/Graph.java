package model;

import java.util.HashMap;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Vertex> vertex;	// list of all the vertex of the graph -interface, class, abstract class
	private ArrayList<Edge> edges;		// list of all the edges of the graph -> relations
	private ArrayList<Attribute> attributes;	// list of all the attributes
	private ArrayList<Method> methods;			// list of all the methods
	private HashMap<Integer,GraphItem> idTable;	// hashmap matching an id to the associated graphItem

	/**
	 * Create an empty graph
	 */
	public Graph() {
		this.vertex = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		this.attributes = new ArrayList<Attribute>();
		this.methods = new ArrayList<Method>();
		this.idTable = new HashMap<Integer,GraphItem>();
	}
	
	/**
	 * Add a vertex to the graph's list of the vertex
	 * 
	 * @param v
	 *           vertex to add
	 */
	public void addVertex(Vertex v) {
		this.vertex.add(v);
	}
	
	/**
	 * Add an edge to the graph's list of the edges
	 * 
	 * @param e
	 *           edge to add
	 */
	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	/**
	 * Add an attribute to the graph's list of the attributes
	 * 
	 * @param a
	 *           attributes to add
	 */
	public void addAttribute(Attribute a) {
		this.attributes.add(a);
	}
	
	/**
	 * Add a method to the graph's list of the methods
	 * 
	 * @param m
	 *           method to add
	 */
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

	/**
	 * Remove a given graphItem from the graph
	 * 
	 * @param gi
	 *           the graphItem to remove
	 */
	public void remove(GraphItem gi) {
		if (gi instanceof Vertex) {
			removeInterface((Vertex) gi);
		}
		if (gi instanceof VertexClass) {
			removeClass((VertexClass) gi);
		}
		if (gi instanceof VertexAbstract) {
			removeAbstractClass((VertexAbstract) gi);
		}
		if (gi instanceof Attribute) {
			removeAttribute((Attribute) gi);
		}
		if (gi instanceof Method) {
			removeMethod((Method) gi);
		}
	}

	/**
	 * Remove a given class from the graph
	 * 
	 * @param vertexClass
	 *           the class to remove
	 */
	public void removeClass(VertexClass vertexClass) {
		// remove vertexClass form the Vertex List
		this.vertex.remove(vertexClass);
		// unlink all the elements linked to the class to delete
		removeEdgeFrom(vertexClass);
		removeAttributeFrom(vertexClass);
		removeMethodFrom(vertexClass);
		// when it is take into account, we will have to delete all the function using the deleted class
		// but in this version of LUNE, methods can only use basic java types
	}

	/**
	 * Remove a given abstract class from the graph
	 * 
	 * @param vertexAbstract
	 *           the abstract class to remove
	 */
	public void removeAbstractClass(VertexAbstract vertexAbstract) {
		// remove vertexAbstract form the Vertex List
		this.vertex.remove(vertexAbstract);
		// unlink all the elements linked to the abstract class to delete
		removeEdgeFrom(vertexAbstract);
		removeAttributeFrom(vertexAbstract);
		removeMethodFrom(vertexAbstract);
		// when it is taken into account, we will have to delete all the function using the deleted abstract class
		// but in this version of LUNE, methods can only use basic java types
	}

	/**
	 * Remove a given interface from the graph
	 * 
	 * @param v
	 *           the interface to remove
	 */	
	public void removeInterface(Vertex v) {
		// remove the vertex form the Vertex List
		this.vertex.remove(v);
		// unlink all the elements linked to the interface to delete
		removeEdgeFrom(v);
		removeMethodFrom(v);
		// when it is taken into account, we will have to delete all the function using the deleted abstract class
		// but in this version of LUNE, methods can only use basic java types
	}

	/**
	 * Remove a given attribute from the graph
	 * 
	 * @param att
	 *           the attribute to remove
	 */	
	public void removeAttribute(Attribute att) {
		// remove the attribute from the Attribute list
		this.attributes.remove(att);
		// unlink the motherclass if the attribute has one
		VertexClass v = ((VertexClass) att.getMotherClass());
		if (v != null) {
			v.getAttributes().remove(att);
		}
	}

	/**
	 * Remove a given method from the graph
	 * 
	 * @param met
	 *           the method to remove
	 */
	public void removeMethod(Method met) {
		// remove the method from the Methodes list
		this.methods.remove(met);
		// unlink the mother class if the method has one
		Vertex v = met.getMotherClass();
		if (v != null) {
			v.getMethods().remove(met);
		}
	}
	
	/**
	 * Remove a given relation from the graph
	 * 
	 * @param id
	 *           the edges to remove
	 */
	public void removeEdge(Edge id) {
		// remove the edge from the Edges list
		this.edges.remove(id);
		// unlink the vertex linked by the relation
		ArrayList<Vertex> vl = id.getVertex();
		for (Vertex v : vl) {
			v.getEdges().remove(id);
		}
	}	

	/**
	 * Delete all the relations associated to the input vertex
	 * 
	 * @param v
	 *         the aimed vertex  
	 */
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

	/**
	 * Unlink all the attributes associated to the input class
	 * 
	 * @param vc
	 *           the aimed class
	 */
	private void removeAttributeFrom(VertexClass vc) {
		// unlink all the attributes from vc
		ArrayList<Attribute> att = vc.getAttributes();
		for (Attribute a : att) {
			a.setMotherClass(null);
		}
	}

	/**
	 * Unlink all the methods associated to the input vertex
	 * 
	 * @param v
	 *           the aimed vertex
	 */
	private void removeMethodFrom(Vertex v) {
		// unlink all the methods from v
		ArrayList<Method> mets = v.getMethods();
		for (Method m : mets) {
			m.setMotherClass(null);
		}
	}
}
