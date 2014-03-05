package model;

public class BinaryAssociation extends Association {
	protected Vertex target;
	protected Vertex source;
	protected String targetMult;
	protected String sourceMult;
	
	public BinaryAssociation(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name) {
		this.target = trg;
		this.targetMult = trgMult;
		this.source = src;
		this.sourceMult = srcMult;
		this.id = id;
		this.name = name;
		this.nature = UMLNature.ASSOCIATION;
		this.frenchName = "Association";
	}
}