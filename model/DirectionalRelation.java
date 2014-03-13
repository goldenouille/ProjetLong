package model;

import java.util.ArrayList;

public class DirectionalRelation extends Edge {

	protected Vertex target;	// target vertex
	protected Vertex source;	// source target

	/**
	 * Create a DirectionalRelation
	 * 
	 * @param trg
	 *            	'target' Vertex of the directionalRelation edge 
	 * @param src
	 *				'source' Vertex of the directionalRelation edge
	 * @param id
	 *				id of the directionalRelation edge
	 * @param name
	 *				name of the directionalRelation edge
	 * @param score
	 *				score attributed of the edge
	 */

	public DirectionalRelation(Vertex trg, Vertex src, int id, String name, int score) {
		super();
		this.target = trg;
		this.source = src;
		this.id = id;
		this.name = name;
		this.score = score;
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
	public ArrayList<Vertex> getVertex() {
		ArrayList<Vertex> vl = new ArrayList<Vertex>();
		vl.add(this.target);
		vl.add(this.source);
		return vl;
	}

	/**
	 * Reverse the BinaryAssociation Edge
	 * 
	 */
	public void reverseRelation() {
		Vertex vAux = this.target;
		this.target = this.source;
		this.source = vAux;
	}

}