package model;

import java.util.ArrayList;

public class Method implements GraphItem {

	private String name;	// name of the method
	private Visibility visibility;	// visibility of the method
	private Type returnType;	// return type 
	private ArrayList<Type> paramTypes;	// list of param types
	private int id;		// id of the method, if if links to a keyWord, they both have the same id, 
						// otherwise it has a unique negative id
	private String frenchName;	// french name of the nature of the item
	private Vertex motherClass;		// a method is linked to only one motherClas
	private boolean isDeletable;	// if it can be deleted, 
									// if the method has been validated, it can not be deleted
	private int score;	// score associated to the method


	/**
	 * Create a basic method
	 */
	public Method() {
		this.frenchName = "methode";
		this.isDeletable = true;
	}

	/** 
	 * Create a Method with all its parameters
	 *
	 * @param name
	 *				name of the method
	 * @param type
	 *				return type of the method
	 * @param visibility
	 *				visibility of the method
	 * @param mother
	 *				mother class of the method
	 * @param id
	 *				id of the method
	 * @param paramTypes
	 *				list of the parameters' type
	 * @param score
	 *				score associated to the method
	 */
	public Method (String name, Type type, Visibility visibility, Vertex mother, int id, ArrayList<Type> paramTypes, int score) {
		this.name = name;
		this.returnType = type;
		this.visibility = visibility;
		this.paramTypes = paramTypes;
		this.frenchName = "methode";
		this.motherClass = mother;
		this.id = id;
		this.isDeletable = true;
		this.score = score;
	}
	
	/** 
	 * Create a Method with its name, its return type, its visibility and the list of the types of its parameters
	 *
	 * @param name
	 *				name of the method
	 * @param type
	 *				return type of the method
	 * @param visibility
	 *				visibility of the method
	 * @param paramTypes
	 *				list of the parameters' type
	 */
	public Method (String name, Type type, Visibility visibility, ArrayList<Type> paramTypes) {
		this.name = name;
		this.returnType = type;
		this.visibility = visibility;
		this.paramTypes = paramTypes;
		this.frenchName = "methode";
		this.id = -1;
		this.isDeletable = true;
	}

	public boolean isDeletable() {
		return this.isDeletable;
	}

	public void validate() {
		this.isDeletable = false;
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

	/**
	 * Add a type to the list of the types of the method's parameters
	 *
	 * @param type
	 *			type to add to the types list
	 */
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

	public UMLNature getUmlNature() {
		return UMLNature.METHOD;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return this.name;
	}
}