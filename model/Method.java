package model;

import java.util.ArrayList;

public class Method implements GraphItem {

	private String name;
	private Visibility visibility;
	private Type returnType;
	private ArrayList<Type> paramTypes;
	private int id;
	private String frenchName;
	private Vertex motherClass;

	public Method() {
		this.frenchName = "methode";
	}

	public Method (String name, Type type, Visibility visibility, ArrayList<Type> paramTypes) {
		this.name = name;
		this.returnType = type;
		this.visibility = visibility;
		this.paramTypes = paramTypes;
		this.frenchName = "methode";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Visibility getVisibility() {
		return this.visibility;
	}

	public void setVisibility(Visibility newVisibility) {
		this.visibility = newVisibility;
	}

	public Type getReturnType() {
		return this.returnType;
	}

	public void setReturnType(Type newType) {
		this.returnType = newType;
	}

	public ArrayList<Type> getParamType() {
		return this.paramTypes;
	}

	public void setParamType(ArrayList<Type> newTypes) {
		this.paramTypes = newTypes;
	}

	public void addParamType(Type type) {
		this.paramTypes.add(type);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getFrenchName() {
		return this.frenchName;
	}

	public void setMotherClass(Vertex vertex) {
		this.motherClass = vertex;
	}

	public Vertex getMotherClass() {
		return this.motherClass;
	}
}