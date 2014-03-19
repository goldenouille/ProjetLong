package model;

// this interface is gathering all the items of a graph (attributes, classes, methods...)
public interface GraphItem {

	public String getName();
	
	public UMLNature getUmlNature();

	public boolean isDeletable();
	public void validate();
	public int getScore();
	public String toString();
	public int getId();
}