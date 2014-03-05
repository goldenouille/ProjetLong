package model;

public class Dependancy extends DirectionalRelation {
	public Dependancy(Vertex trg, Vertex src, int id, String name) {
		super(trg,src,id,name);
		this.nature = UMLNature.DEPENDANCY;
		this.frenchName = "dependance";
	}
}