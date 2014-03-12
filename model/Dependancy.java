package model;

public class Dependancy extends DirectionalRelation {
	public Dependancy(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.DEPENDANCY;
		this.frenchName = "dependance";
	}
	
	public String getUml() {
		return "dependancy";
	}
}