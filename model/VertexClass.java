package model;

import java.util.ArrayList;

public class VertexClass extends Vertex {

	protected ArrayList<Attribute> attributes;

	public VertexClass(String name, int id) {
		super(name, id);
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe";
	}

	public VertexClass() {
		super();
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe";
	}
	
	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	public void addAttributes(Attribute attribute) {
		this.attributes.add(attribute);
	}

}
