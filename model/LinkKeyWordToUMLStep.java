package model;

import java.util.ArrayList;

import controller.ModelController;

public class LinkKeyWordToUMLStep extends Step {

	public LinkKeyWordToUMLStep() {
		this.name = "LinkKeyWordToUMLStep";
		this.frenchName = "Association des mots-clef avec un type UML";
	}
	
	public static void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Word> userText = exo.getUserText();
		ModelController mc = exo.getModelController();
		int missingAssociation = 0;

		mc.doResetUMLInstanceColor();

		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isKeyWord()) {
				Word word = text.get(i);
				if (word.getUmlNature()==word.getUserUmlNature()) {
					System.out.println("Le type de " + word.getWord() + " est correct !");
				} else if (word.getUserUmlNature() != null) {
						mc.doShowUMLInstanceInRed(word.getUserGraphItem);
						System.out.println("Le type de " + word.getWord() + " est incorrect !");
					} else {
						missingAssociation ++;
					}
				}
			}
		}
	}		
}


	public void doShowUMLInstanceInRed(Object id) {
		umlDrawingPanel.doShowUMLInstanceInRed(id);
	}
	
	/**
	 * Reset color of UML instance to default in element pool
	 * 
	 * @param id
	 *            identifier of the instance to reset
	 */
	public void doResetUMLInstanceColor(Object id) {
		umlDrawingPanel.doResetUMLInstanceColor(id);
	}