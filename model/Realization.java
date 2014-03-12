package model;

public class Realization extends Heritage {
	public Realization(Vertex trg, Vertex src, int id, String name, int score) {
		super(trg,src,id,name,score);
		this.nature = UMLNature.REALIZATION;
		this.frenchName = "realisation";
	}
	
	public String getUml() {
		return "realization";
	}
}