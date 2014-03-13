package model;

import java.util.ArrayList;

public class BinaryAssociation extends Association {
	protected Vertex target;	// target Vertex 
	protected Vertex source;	// source Vertex
	protected String targetMult;	// target multiplicity
	protected String sourceMult;	// source mutliplicity
	
<<<<<<< HEAD

	/**
	 * Create a BinaryAssociation
	 * 
	 * @param trg
	 *            	'target' Vertex of the binaryAssociation edge 
	 * @param trgMult
	 *				multiplicity of the target vertex
	 * @param src
	 *				'source' Vertex of the binaryAssociation edge
	 * @param srcMult
	 *				multiplicity of the source vertex
	 * @param id
	 *				id of the binaryAssociation edge
	 * @param name
	 *				name of the binaryAssociation edge
	 */
	public BinaryAssociation(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name) {
=======
	public BinaryAssociation(Vertex trg, String trgMult, Vertex src, String srcMult, int id, String name, int score) {
>>>>>>> d59b5c0300145921ab41e6ee4151f3ee40dfdba2
		this.target = trg;
		this.targetMult = trgMult;
		this.source = src;
		this.sourceMult = srcMult;
		this.id = id;
		this.name = name;
		this.nature = UMLNature.ASSOCIATION;
		this.frenchName = "Association";
		this.score = score;
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

	// By convertion, when an ArrayList is used, the first item refers to the target 
	// and the second refers to the source
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

	// By convertion, when an ArrayList is used, the first item refers to the target 
	// and the second refers to the source	
	public ArrayList<Vertex> getVertex() {
		ArrayList<Vertex> vl = new ArrayList<Vertex>();
		vl.add(this.target);
		vl.add(this.source);
		return vl;
	}

	// By convertion, when an ArrayList is used, the first item refers to the target 
	// and the second refers to the source
	public ArrayList<String> getMultiplicity() {
		ArrayList<String> vm = new ArrayList<String>();
		vm.add(this.targetMult);
		vm.add(this.sourceMult);
		return vm;
	}

	/**
	 * Reverse the BinaryAssociation Edge
	 * 
	 */
	public void reverseRelation() {
		Vertex vAux = this.target;
		this.target = this.source;
		this.source = vAux;

		String stringAux = this.targetMult;
		this.targetMult = this.sourceMult;
		this.sourceMult = stringAux;
	}
}