package model;

public class SelectKeyWordStep {

	public SelectKeyWordStep() {
		this.name = "selectKeyWordStep";
		this.frenchName = "Selection des mots-cle dans l'enonce";
	}

	public void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<boolean> correction = new ArrayList<boolean>();

		for (Word word : text) {
    		//TODO TODO TODO
		}

	}
}