package model;

public class Realization extends Heritage {

	/**
	 * Create a Realization
	 * 
	 * @param trg
	 *            	'target' Vertex of the realization edge 
	 * @param src
	 *				'source' Vertex of the realization edge
	 * @param id
	 *				id of the aggregation edge
	 * @param name
	 *				name of the aggregation edge
	 * @param score	
	 *				score used during the correction
	 */
	public Realization(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.REALIZATION;
		this.frenchName = "realisation";
	}

	/**
	 * Get the name of the UML nature of the edge
	 * 
	 * @return the name of the UML nature of the item
	 */	
	public String getUml() {
		return "realization";
	}
}