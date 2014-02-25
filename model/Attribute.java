package Model;

public class Attribute implements GraphItem{

	private Type type;
	private String name;
	private Visibility visibility;
	private String frenchName;

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
}