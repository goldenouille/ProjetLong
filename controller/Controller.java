package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import parser.Parser;
import gui.*;

public class Controller {

	private ModelController modelController;
	private ClassicGuiController guiController;
	private Parser parser;

	public Controller() {
		this.modelController = new ModelController();
		this.guiController = new ClassicGuiController(this.modelController);
		this.modelController.setCGC(this.guiController);
		this.parser = new Parser();
		this.modelController.setParser(this.parser);
	}
}