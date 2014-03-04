package controller;

import model.Exercise;
import gui.ClassicGuiController;


public class Controller {

	private ModelController modelController;
	private ClassicGuiController guiController;

	public Controller(Exercise ex) {
		this.modelController = new ModelController();
		this.modelController.setExercise(ex);
		
		this.guiController = new ClassicGuiController(this.modelController);
		this.modelController.setCGC(this.guiController);
	}
}