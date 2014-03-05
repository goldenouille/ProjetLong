package model;

public class Aggregation extends BinaryAssociation {
	
	public Aggregation(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name) {
		super(trg, trgMult, src, srcMult, id, name);
		this.nature = UMLNature.AGGREGATION;
		this.frenchName = "aggregation";
	}
}