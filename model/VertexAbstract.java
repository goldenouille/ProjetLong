package Model;

import java.util.ArrayList;

public class VertexAbstract extends Vertex {

	private ArrayList<Attribute> attributes;
	private boolean isAbstract;

	
	public VertexClass() {
		super();
		this.attributes = new ArrayList<Attribute>();
		this.isAbstract = true;
		this.frenchName = "classe abstraite";
	}
}
