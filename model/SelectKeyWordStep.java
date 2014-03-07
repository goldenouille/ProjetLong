package model;

import java.util.ArrayList;

import controller.ModelController;

public class SelectKeyWordStep extends Step {

	public SelectKeyWordStep() {
		this.name = "selectKeyWordStep";
		this.frenchName = "Selection des mots-cle dans l'enonce";
	}

	public static void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Word> userText = exo.getUserText();
		ModelController mc = exo.getModelController();
		int missingKW = 0;

		try {
		//System.out.println("on commence la correction de la selection des mots-cle");
		for (int i = 0; i < text.size(); i++) {
			//System.out.println(" -> on check le mot " + i + " : " + text.get(i).getWord());
			if (text.get(i).isSelected()) {
				//System.out.println("--> le mot est selectionne");
				if (text.get(i).isKeyWord()) {
					//System.out.println("---> et est un mot-cle");
    				mc.doUnSelectText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//Selected KW = ok
    				mc.doValidateText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//Selected KW = ok
    			} else {
    				//System.out.println("---> mais n'est pas un mot-cle");
    				mc.doUnSelectText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//selected not KW = false
    				mc.doInvalidateText(text.get(i).getFirstWord(), text.get(i).getLastWord(), false);//selected not KW = false
    				
    			}	
    		} else {
    			//System.out.println("--> le mot n'est pas selectionne");
    			if (text.get(i).isKeyWord()) {
    				//System.out.println("---> mais c'est un mot-cle");
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
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return this.frenchName;
	}
}
