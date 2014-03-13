package model;

public class Dependancy extends DirectionalRelation {
<<<<<<< HEAD

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
	 */
	public Dependancy(Vertex trg, Vertex src, int id, String name) {
		super(trg,src,id,name);
=======
	public Dependancy(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
>>>>>>> d59b5c0300145921ab41e6ee4151f3ee40dfdba2
		this.nature = UMLNature.DEPENDANCY;
		this.frenchName = "dependance";
	}
	
	public String getUml() {
		return "dependancy";
	}
}