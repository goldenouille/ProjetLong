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
		ModelController mc = exo.getModelController();
		int missingKW = 0;

		try {
		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isSelected()) {
				if (text.get(i).isKeyWord()) {
    					mc.doValidateText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//Selected KW = ok
    				} else {
    					mc.doInvalidateText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//selected not KW = false
    				}	
    			} else {
    				if (text.get(i).isKeyWord()) {
    					missingKW++; // KW not selected = missing
    				}
    			}
		}
		for (int i = 0; i< userText.size() ; i++) {
			if (userText.get(i).isSelected()) {
				mc.doValidateText(userText.get(i).getFirstWord(), userText.get(i).getLastWord(),true); //KW in user text are considered correct for now.
			}
		}
		mc.doShowMissingKeywordNumber(missingKW);
		} catch (Exception e) {
			System.out.println("erreur dans la correction de SelectKeyWordStep");
		}
	}
	
	public String toString() {
		return this.frenchName;
	}
}
