package model;

import java.util.ArrayList;

public class Edge implements GraphItem{


	protected String name;	// name of the edge
	protected int id;		// id of the edge
	protected String frenchName;	// french name used in the toString
	protected UMLNature nature;		// UMLNature of the edge
	protected boolean isDeletable;	// if the edge can be deleted. if it has been deleted, it can not be deleted
	protected boolean isCorrected;	// if the edge has been corrected during the last correction
	protected int score;		// score attributed to the edge


	
	/**
	 * Create an edge
	 * 
	 */
	public Edge() {
		this.id = -1;
		this.isDeletable = true;
	}

	public UMLNature getNature() {
		return nature;
	}

	public boolean isDeletable() {
		return this.isDeletable;
	}

	public void validate() {
		this.isDeletable = false;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String n) {
		this.name = n;
	}
	
	public boolean getIsCorrected() {
		return this.isCorrected;
	}
	
	public void setIsCorrected(boolean corrected) {
		this.isCorrected = corrected;
	}
	
	public String getUml() {
		return null;
	}

	public int getId() {
		return id;
	}

	// it ensures that this function can be called by any sub-class of edge
	// it returns an empty arrayList
	public ArrayList<Vertex> getVertex() {
		return new ArrayList<Vertex>();
	}

	// it ensures that this function can be called by any sub-class of edge
	// it returns an empty arrayList
	public ArrayList<String> getMultiplicity() {
		return new ArrayList<String>();
	}


	/**
	 * Create statically a specific edge from its name, its UMLNatute, the list of its vertex and its multiplicities
	 * 
	 * @param nature
	 *            	UMLNature of the wanted edge 
	 * @param v
	 *				vertex list of the edge
	 * @param multiplicity
	 *				multiplicity list of the edge
	 * @param name
	 *				name of edge
	 * @param id
	 *				id of the edge
	 */
	public static Edge createEdge(UMLNature nature,ArrayList<Vertex> v,ArrayList<String> multiplicity,
																							String name, int id) {
		// By convertion, when an ArrayList is used for a binary edge, the first item refers to the target 
		// and the second refers to the source	

		if (nature.equals(UMLNature.AGGREGATION)) {
			return (new Aggregation(v.get(0), multiplicity.get(0), v.get(1), multiplicity.get(1), id, name,0));
		}
		if (nature.equals(UMLNature.ASSOCIATION)) {
			return (new BinaryAssociation(v.get(0), multiplicity.get(0), v.get(1), multiplicity.get(1), id, name,0));
		}
		if (nature.equals(UMLNature.COMPOSITION)) {
			return (new Composition(v.get(0), multiplicity.get(0), v.get(1), multiplicity.get(1), id, name,0));
		}
		if (nature.equals(UMLNature.DEPENDANCY)) {
			return (new Dependancy(v.get(0), v.get(1), id, name,0));
		}
		if (nature.equals(UMLNature.GENERALIZATION)) {
			return (new Generalization(v.get(0), v.get(1), id, name,0));
		}
		if (nature.equals(UMLNature.N_ASSOCIATION)) {
			System.out.println("La creation des association n-aire n'est pas gere pour l'instant");
			return null;
		}
		if (nature.equals(UMLNature.REALIZATION)) {
			return (new Realization(v.get(0), v.get(1), id, name,0));
		}
		return null;
	}

	@Override
	public UMLNature getUmlNature() {
		return this.nature;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

}
