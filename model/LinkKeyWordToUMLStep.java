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
		Score score = exo.getScore();
		ModelController mc = exo.getModelController();
		int missingAssociation = 0;

		mc.doResetUMLInstanceColor();

		System.out.println("on commence la correction de l'association des mots-cle");
		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isKeyWord()) {
				Word word = text.get(i);
<<<<<<< HEAD
				if (word.getUmlNature()==word.getUserUmlNature()) {
					System.out.println("Le type de " + word.getWord() + " est correct !");
				} else if (word.getUserUmlNature() != null) {
					mc.doShowUMLInstanceInRed(word.getUserGraphItem());
					System.out.println("Le type de " + word.getWord() + " est incorrect !");
				} else {
=======
				GraphItem gi = exo.getCurrentPart().getIdTable().get(text.get(i).getId());

				if (word.getUserUmlNature() == null) {
>>>>>>> 5aa0984113d1ecda4493ef8df142e3d1c4feef74
					missingAssociation ++;
				}
				
				else if (gi.getUmlNature().equals(word.getUserUmlNature())) {
					System.out.println("Le type de " + word.getWord() + " est correct !");
					mc.doShowUMLInstanceInGreen(word.getUserGraphItem());
				}
				
				else {
					mc.doShowUMLInstanceInErrorColor(word.getUserGraphItem());
    				score.removeScoreNature(score.getScoreNature()/5);
    				System.out.println("Le type de " + word.getWord() + " est incorrect !");
				}
			}
		}
<<<<<<< HEAD
		mc.doShowMissingAssociationNumber(missingAssociation);
=======
	mc.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
>>>>>>> 5aa0984113d1ecda4493ef8df142e3d1c4feef74
	}
}
