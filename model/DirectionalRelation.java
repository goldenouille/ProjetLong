package model;

public class DirectionalRelation extends Edge {
	
	protected Vertex target;
	protected Vertex source;
	
	public DirectionalRelation(Vertex trg, Vertex src, int id, String name) {
		super();
		this.target = trg;
		this.source = src;
		this.id = id;
		this.name = name;
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
}