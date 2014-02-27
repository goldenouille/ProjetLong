package model;

import java.util.ArrayList;

import controller.ModelController;

public class SelectKeyWordStep extends Step {

	public SelectKeyWordStep() {
		this.name = "selectKeyWordStep";
		this.frenchName = "Selection des mots-cle dans l'enonce";
	}

	public void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Word> userText = exo.getUserText();
		ArrayList<Boolean> correction = new ArrayList<Boolean>();
		ModelController mc = exo.getModelController();

		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isSelected()) {
				if (text.get(i).isKeyWord()) {
    				mc.doValidateText(i, i, false);
    			} else {
    				mc.doInvalidateText(i, i, false);
    			}	
    		}
		}
		for (int i = 0; i< userText.size() ; i++) {
			if (userText.get(i).isSelected()) {
				mc.doValidateText(i,i,true);
			}
		}
	}
}