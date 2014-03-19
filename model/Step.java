package model;

public abstract class Step {

	protected String name;		// name of the step, used to know which step the stepFactory has to created
	protected String frenchName;	// french description of the step

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getFrenchName() {
		return this.frenchName;
	}

	/**
	 * the step correct the exercise and interacts with the GUI to show to the user what is correct and what is not
	 *
	 * @param exo
	 *			exercise that has to be corrected according to the step
	 */
	public static void getCorrection(Exercise exo) {
	}

}