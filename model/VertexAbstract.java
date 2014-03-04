package model;

import java.util.ArrayList;

public class VertexAbstract extends VertexClass {
	
	public VertexAbstract(String name, int id) {
		super(name, id);
		this.attributes = new ArrayList<Attribute>();
		this.frenchName = "classe abstraite";
	}

	public VertexAbstract() {
		super();
		this.frenchName = "classe abstraite";
	}
	
	public String getUml() {
		return "abstract";
	}
}
