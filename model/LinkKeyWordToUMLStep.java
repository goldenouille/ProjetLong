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
		
		boolean juste = true;
		String msg = "";

		mc.doResetUMLInstanceColor();

		System.out.println("on commence la correction de l'association des mots-cle");
		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isKeyWord()) {
				Word word = text.get(i);
				GraphItem gi = exo.getCurrentPart().getIdTable().get(text.get(i).getId());
				if (word.getUserUmlNature() == null) {
					missingAssociation ++;
					juste = false;
				}
				
				else if (gi.getUmlNature().equals(word.getUserUmlNature())) {
					System.out.println("Le type de " + word.getWord() + " est correct !");
					mc.doShowUMLInstanceInValidateColor(word.getUserGraphItem());
					
				}
				
				else {
					mc.doShowUMLInstanceInErrorColor(word.getUserGraphItem());
    				score.removeScoreNature(score.getScoreNature()/5);
    				System.out.println("Le type de " + word.getWord() + " est incorrect !");
    				juste = false;
				}
			}
		}
	if (juste) {
		mc.doPrintMessage("Succes", "Association juste");
		mc.doSetValidateAssociationButtonEnabled(false);
		mc.doSetValidateDiagramButtonEnabled(true);
	}
	else {
		mc.doPrintMessage("Echec", "Il manque " + missingAssociation + " mots-cle.");
	}
	mc.doShowMissingAssociationNumber(missingAssociation);
	mc.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
	}
}
