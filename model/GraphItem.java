package model;

public interface GraphItem {

	public String getName();
	
	public UMLNature getUmlNature();

	public boolean isDeletable();
	public void validate();
	public int getScore();
	public String toString();
	public int getId();
}