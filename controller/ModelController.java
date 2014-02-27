package controller;

import parser.Parser;

public class ModelController {
	private ClassicGuiController cgc;
	private Parser p;
	private StepFactory sf;

	public void setCGC(ClassicGuiController cgc) {
		this.cgc = cgc;
		this.sf = new StepFactory();
	}

	public void setParser(Parser p) {
		this.p = p;
	}

	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doValidateText(firstWord,lastWord,userText);
	}

	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doInvalidateText(firstWord, lastWord, userText);
	}
}