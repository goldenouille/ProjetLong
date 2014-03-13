package model;

public class Composition extends BinaryAssociation {
<<<<<<< HEAD

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
	 */
	public Composition(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name) {
		super(trg, trgMult, src, srcMult, id, name);
=======
	public Composition(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name, int score) {
		super(trg, trgMult, src, srcMult, id, name, score);
>>>>>>> d59b5c0300145921ab41e6ee4151f3ee40dfdba2
		this.nature = UMLNature.COMPOSITION;
		this.frenchName = "composition";
	}
	
	public String getUml() {
		return "composition";
	}
	
	public UMLNature getUmlNature() {
		return UMLNature.COMPOSITION;
	}
}