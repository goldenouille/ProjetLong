package model;

public class Composition extends BinaryAssociation {
	public Composition(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name) {
		super(trg, trgMult, src, srcMult, id, name);
		this.nature = UMLNature.COMPOSITION;
		this.frenchName = "composition";
	}
	
	public String getUml() {
		return "composition";
	}
}