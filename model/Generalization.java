package model;

public class Generalization extends Heritage {
	public Generalization(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.GENERALIZATION;
		this.frenchName = "generalisation";
	}
	
	public String getUml() {
		return "generalization";
	}
}