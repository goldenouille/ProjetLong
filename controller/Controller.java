package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import parser.Parser;

public class Controller implements ActionListener {

	private ModelControler modelControler;
	private ClassicGuiController guiControler;
	private Parser parser;

	public Controler() {
		this.modelControler = new ModelControler();
		this.guiControler = new ClassicGuiController(this.modelControler);
		this.modelControler.setCGC(this.guiControler);
		this.parser = new Parser();
		this.modelControler.setParser(this.parser);
	}
}