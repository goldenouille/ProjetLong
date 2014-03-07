package model;

import java.util.ArrayList;

public class Edge implements GraphItem{

	protected String name;
	protected int id;
	protected String frenchName;
	protected UMLNature nature;
	protected String label;
	
	public Edge() {
		this.id = -1;
	}

	public UMLNature getNature() {
		return nature;
	}

	public String getName() {
		return this.name;
	}

	public String getUml() {
		return null;
	}

	public int getId() {
		return id;
	}
	
	public ArrayList<Vertex> getVertex() {
		return new ArrayList<Vertex>();
	}

	public ArrayList<String> getMultiplicity() {
		return new ArrayList<String>();
	}

	@Override
	public UMLNature getUmlNature() {
		return this.nature;
	}

}