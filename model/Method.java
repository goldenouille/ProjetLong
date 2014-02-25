package Model;

import java.util.ArrayList;

public class Method implements GraphItem {

	private String name;
	private Visibility visibility;
	private Type returnType;
	private ArrayList<Type> paramTypes;
	private String frenchName;

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
}