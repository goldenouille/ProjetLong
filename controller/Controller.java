package controller;

import parser.Parser;
import gui.*;
//test
import model.*;

public class Controller {

	private ModelController modelController;
	private ClassicGuiController guiController;
	private Parser parser;

	public Controller() {


		

// fin des instanciation pour test

		this.modelController = new ModelController();
		
		/// instanciation pour test
				Exercise exo= new Exercise(modelController);
				Step s = new SelectKeyWordStep();
				Part p = new Part();
				p.addText("ceci est un test");
				p.addStep(s);
				exo.addPart(p);
				
		
		
		this.modelController.setExercise(exo);
		
		this.guiController = new ClassicGuiController(this.modelController);
		this.modelController.setCGC(this.guiController);
		this.parser = new Parser();
		this.modelController.setParser(this.parser);
		
		exo.init();
	}

	public static void main(String[] args) {
		Controller cont = new Controller();
	}
}