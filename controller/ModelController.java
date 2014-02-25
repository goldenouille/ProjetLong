package Controller;

import Parser.Parser;

public class ModelController {
	private ClassicGuiController cgc;
	private Parser p;

	public void setCGC(ClassicGuiController cgc) {
		this.cgc = cgc;
	}

	public void setParser(Parser p) {
		this.p = p;
	}
}