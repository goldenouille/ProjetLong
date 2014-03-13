package model;

public class Dependancy extends DirectionalRelation {


	/**
	 * Create a BinaryAssociation
	 * 
	 * @param trg
	 *            	'target' Vertex of the binaryAssociation edge 
	 * @param src
	 *				'source' Vertex of the binaryAssociation edge
	 * @param id
	 *				id of the binaryAssociation edge
	 * @param name
	 *				name of the binaryAssociation edge
	 * @param score
	 *				score attributed to the edge
	 */

	public Dependancy(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.DEPENDANCY;
		this.frenchName = "dependance";
	}
	
	public String getUml() {
		return "dependancy";
	}
}