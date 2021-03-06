package model;

public class Composition extends BinaryAssociation {

	/**
	 * Create a Composition edge
	 * 
	 * @param trg
	 *            	'target' Vertex of the Composition edge 
	 * @param trgMult
	 *				multiplicity of the target vertex
	 * @param src
	 *				'source' Vertex of the Composition edge
	 * @param srcMult
	 *				multiplicity of the source vertex
	 * @param id
	 *				id of the Composition edge
	 * @param name
	 *				name of the Composition edge
	 * @param score
	 *				score attributed to the edge
	 */

	public Composition(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name, int score) {
		super(trg, trgMult, src, srcMult, id, name, score);
		this.nature = UMLNature.COMPOSITION;
		this.frenchName = "composition";
	}
	
	/**
	 * Get the name of the UML Nature of the edge
	 * 
	 * @param the UML Nature's name of the edge
	 */	
	public String getUml() {
		return "composition";
	}
	
	public UMLNature getUmlNature() {
		return UMLNature.COMPOSITION;
	}
}