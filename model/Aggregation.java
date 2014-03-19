package model;

public class Aggregation extends BinaryAssociation {


	/**
	 * Create a Aggregation
	 * 
	 * @param trg
	 *            	'target' Vertex of the aggregation edge 
	 * @param trgMult
	 *				multiplicity of the target vertex
	 * @param src
	 *				'source' Vertex of the aggregation edge
	 * @param srcMult
	 *				multiplicity of the source vertex
	 * @param id
	 *				id of the aggregation edge
	 * @param name
	 *				name of the aggregation edge
	 * @param score 
	 */
	public Aggregation(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name, int score) {
		super(trg, trgMult, src, srcMult, id, name,score);
		this.nature = UMLNature.AGGREGATION;
		this.frenchName = "aggregation";
	}


	/**
	 * Get the name of the UML nature of the element
	 *
	 * @return the name of the UML nature of the element
	 */
	public String getUml() {
		return "aggregation";
	}
}