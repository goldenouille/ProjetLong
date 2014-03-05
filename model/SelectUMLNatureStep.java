package model;

import java.util.ArrayList;

import controller.ModelController;

public class SelectUMLNatureStep extends Step {

	public SelectUMLNatureStep() {
		this.name = "SelectUMLNatureStep";
		this.frenchName = "Choix de la nature des mots-cle";
	}
	
	public void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ArrayList<Word> userText = exo.getUserText();
		ModelController mc = exo.getModelController();
		
		
		try {
			for (int i = 0; i < text.size(); i++) {
				if (text.get(i).isKeyWord()) {
					if (text.get(i).getUmlNature() == text.get(i).getUserUmlNature()) {
						//Afficher ce mot-cle est du bon type
					}
					else {
						//Afficher ce mot-cle n'est pas du bon type
					}
				}
			}
			for (int i = 0; i< userText.size() ; i++) {
				if (userText.get(i).isKeyWord()) {
					//Afficher ce mot-cle est du bon type
				}
			}
				//Afficher les mots-cle du mauvais type
			} catch (Exception e) {
				System.out.println("erreur dans la correction de SelectUMLNatureStep");
			}

	}

}
