package model;

import java.util.ArrayList;

public class Edge implements GraphItem{

	protected String name;
	protected int id;
	protected String frenchName;
	protected UMLNature nature;

	public UMLNature getNature() {
		return nature;
	}

	public String getName() {
		return this.name;
	}

}