package model;

import java.util.ArrayList;

public class SelectKeyWordStep extends Step {

	public SelectKeyWordStep() {
		this.name = "selectKeyWordStep";
		this.frenchName = "Selection des mots-cle dans l'enonce";
	}

	public void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Boolean> correction = new ArrayList<Boolean>();

		for (Word word : text) {
    		//TODO TODO TODO
		}

	}
}