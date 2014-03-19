package controller;

import model.Exercise;
import gui.ClassicGuiController;


public class Controller {
		// below the two controllers and which deal with the input exercise
	private ModelController modelController;
	private ClassicGuiController guiController;

	/**
	 * Create the controller and create its fields
	 *
	 * @param ex
	 *			the exercise which is about to be played
	 */
	public Controller(Exercise ex) {
		this.modelController = new ModelController();	// create a new modelController
		this.modelController.setExercise(ex);			// set the exercise in the modelController
		ex.setModelController(this.modelController);	// set the modelController in the exercise
		
		this.guiController = new ClassicGuiController(this.modelController);	// create a ClassicGUIController
																// with the associated modelController
		this.modelController.setCGC(this.guiController);	// set the ClassicGUIController in the modelController
		ex.init();	// initiate the exercise
	}
}