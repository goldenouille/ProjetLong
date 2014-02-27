package model;

import java.util.ArrayList;

import controller.ModelController;

public class LinkKeyWordToUMLStep extends Step {

	public LinkKeyWordToUMLStep() {
		this.name = "LinkKeyWordToUMLStep";
		this.frenchName = "Association des mots-clef avec un type UML";
	}
	
	public void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Word> userText = exo.getUserText();
		ModelController mc = exo.getModelController();

		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isSelected()) {
				mc.UMLtypeCorrect(i,i,i.umlNature==i.userUmlNature);
			}
		}
	}		
}
