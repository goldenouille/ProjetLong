package model;

import java.util.ArrayList;

import controller.ModelController;

public class BuildGraphUMLStep extends Step {

	public BuildGraphUMLStep() {
		this.name = "BuildGraphUMLStep";
		this.frenchName = "Construction du graphe UML.";
	}
	
	public static void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		Score score = exo.getScore();
		ModelController mc = exo.getModelController();
		
		for (int i = 0; i< text.size() ; i++) {
			Word word=text.get(i);
			if (word.isKeyWord()) {
				boolean error=false;
				switch (word.getUmlNature()) {
				case ATTRIBUTE :
					Attribute attribute = ((Attribute)word.getGraphItem());
					Attribute userattribute = ((Attribute)word.getUserGraphItem());
					error=(attribute.getType()==userattribute.getType());
					error=error||(attribute.getMotherClass().getId()==userattribute.getMotherClass().getId());
					if (error) {
						mc.doShowUMLDrawingInErrorColor(userattribute,UMLNature.ATTRIBUTE);
					}
					break;
				case METHOD :
					Method method = ((Method)word.getGraphItem());
					Method usermethod = ((Method)word.getUserGraphItem());
					error=error||(method.getMotherClass().getId()==usermethod.getMotherClass().getId());
					if (error) {
						mc.doShowUMLDrawingInErrorColor(usermethod,UMLNature.METHOD);
					}
					break;
				default :
					break;
				}
			}
		}
		
	}

}
