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
	 */
	public Realization(Vertex trg, Vertex src, int id, String name) {
		super(trg,src,id,name);
		this.nature = UMLNature.REALIZATION;
		this.frenchName = "realisation";
	}
	
	public String getUml() {
		return "realization";
	}
}