package model;

public class Attribute implements GraphItem{

	private Type type;
	private String name;
	private Visibility visibility;
	private String frenchName;
	private int id;
	private Vertex motherClass;

	public Attribute () {
		this.frenchName = "attribut";
	}

	public Attribute (String name, Type type, Visibility visibility, Vertex mother, int id) {
		this.name = name;
		this.type = type;
		this.visibility = visibility; 
		this.frenchName = "attribut";
		this.motherClass = mother;
		this.id = id;
	}
	
	public Attribute (String name, Type type, Visibility visibility) {
		this.name = name;
		this.type = type;
		this.visibility = visibility; 
		this.frenchName = "attribut";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type newType) {
		this.type = newType;
	}

	public Visibility getVisibility() {
		return this.visibility;
	}

	public void setVisibility(Visibility newVisibility) {
		this.visibility = newVisibility;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int n) {
		this.id = n;
	}

	/*
	TODO TODO TODO il faut rajouter le throw d'une exception si c'est pas une classe TODO TODO TODO
	*/
	public void setMotherClass(Vertex vertex) {
		if (vertex instanceof VertexClass) {
			this.motherClass = vertex;
		}
	}

	public Vertex getMotherClass() {
		return this.motherClass;
	}
}
