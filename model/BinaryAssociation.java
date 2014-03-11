package model;

import java.util.ArrayList;

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

	public void setMultiplicities(ArrayList<String> m) {
		this.targetMult = m.get(0);
		this.sourceMult = m.get(1);
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
	
	public ArrayList<Vertex> getVertex() {
		ArrayList<Vertex> vl = new ArrayList<Vertex>();
		vl.add(this.source);
		vl.add(this.target);
		return vl;
	}

	public ArrayList<String> getMultiplicity() {
		ArrayList<String> vm = new ArrayList<String>();
		vm.add(this.sourceMult);
		wm.add(this.targetMult);
		return vm;
	}

	public void reverseRelation() {
		Vertex vAux = this.target;
		this.target = this.source;
		this.source = vAux;

		String stringAux = this.targetMult;
		this.targetMult = this.sourceMult;
		this.sourceMult = stringAux;
	}
}