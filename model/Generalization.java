package model;

public class Generalization extends Heritage {
<<<<<<< HEAD

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
	
	public String getUml() {
		return "generalization";
	}
}