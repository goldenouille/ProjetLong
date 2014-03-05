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
	
	public String getUml() {
		return "association";
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public String getTargetMult() {
		return targetMult;
	}

	public void setTargetMult(String targetMult) {
		this.targetMult = targetMult;
	}

	public String getSourceMult() {
		return sourceMult;
	}

	public void setSourceMult(String sourceMult) {
		this.sourceMult = sourceMult;
	}
}