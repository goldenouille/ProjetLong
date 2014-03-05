package model;

public class DirectionalRelation extends Edge {
	
	protected Vertex target;
	protected Vertex source;
	
	public DirectionalRelation(Vertex trg, Vertex src, int id, String name) {
		this.target = trg;
		this.source = src;
		this.id = id;
		this.name = name;
	}
}