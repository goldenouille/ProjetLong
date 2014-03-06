package controller;

import model.Exercise;
import gui.ClassicGuiController;


public class Controller {

	private ModelController modelController;
	private ClassicGuiController guiController;

	public Controller(Exercise ex) {
		this.modelController = new ModelController();
		this.modelController.setExercise(ex);
		ex.setModelController(this.modelController);
		
		this.guiController = new ClassicGuiController(this.modelController);
		this.modelController.setCGC(this.guiController);
		ex.init();
	}
}