package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import parser.Parser;
import gui.*;
//test
import model.*;

public class Controller {

	private ModelController modelController;
	private ClassicGuiController guiController;
	private Parser parser;

	public Controller() {
/// instanciation pour test
		Exercise exo= new Exercise();
		Step s = new SelectKeyWordStep();
		Part p = new Part();
		p.addText("ceci est un test");
		p.addStep(s);
		exo.init();


// fin des instanciation pour test

		this.modelController = new ModelController();
		this.guiController = new ClassicGuiController(this.modelController);
		this.modelController.setCGC(this.guiController);
		this.parser = new Parser();
		this.modelController.setParser(this.parser);
	}

	public static void main(String[] args) {
		Controller cont = new Controller();
	}
}