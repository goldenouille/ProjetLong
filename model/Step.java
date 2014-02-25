package model;

public abstract class Step {

	protected String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	abstract void getCorrection(Exercise exo);


}