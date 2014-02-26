package model;

public abstract class Step {

	protected String name;
	protected String frenchName;

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFrenchName() {
		return this.frenchName;
	}

	abstract void getCorrection(Exercise exo);


}