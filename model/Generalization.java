package model;

public class Generalization extends Heritage {


	/**
	 * Create a Generalization
	 * 
	 * @param trg
	 *            	'target' Vertex of the generalization edge 
	 * @param trgMult
	 *				multiplicity of the target vertex
	 * @param src
	 *				'source' Vertex of the generalization edge
	 * @param srcMult
	 *				multiplicity of the generalization, vertex
	 * @param id
	 *				id of the generalization edge
	 * @param name
	 *				name of the generalization edge
	 * @param scord
	 *				score attributed to the edge
	 */

	public Generalization(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.GENERALIZATION;
		this.frenchName = "generalisation";
	}


	/**
	 * Get the name of the UML nature of the edge
	 * 
	 * @return the UML nature's name of the edge 
	*/
	public String getUml() {
		return "generalization";
	}
}