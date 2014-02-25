package model;

import java.util.ArrayList;

public class Exercise {

	private ModelController modelController;

	private ArrayList<Word> text;
	private ArrayList<Word> userText;

	private Graph graph;
	private Graph userGraph;

	private ArrayList<Part> parts;
	private int currentPart;

	public ArrayList<Word> getText() {
			return this.text;
	}


	/*
	cette fonction sélectionne tous les mots qui sont sélectionnés à plus de 90 %
	TODO: implémenter une vérification pour éviter qu'on puisse tricher en sélectionnant l'intégralité du texte
	TODO: créer une excetion si la sélection n'est pas de la bonne dimension
	TODO: gérer le userText
	*/
	public boolean selectText(ArrayList<Integer> selection) {
		if (selection.size() != text.size()) {
			return false;
		}
		for (int i=0; i<selection.size(); i++) {
    		if (selection.get(i)>90) {
    			text.get(i).select();
    		}
		}
		return true;
	}

	/*
	cette fonction déselectionne tous les mots sélectionnés à plus de 90 %
	pas besoin de vérification supplémentaire sur cette fonction
	TODO: créer une exception si la sélection n'est pas de la bonne dimension
	TODO: gérer le userText
	*/
	public boolean unselectText(ArrayList<Integer> selection) {
		if (selection.size() != text.size()) {
			return false;
		}
		for (int i=0; i<selection.size(); i++) {
    		if (selection.get(i)>90) {
    			text.get(i).unselect();
    		}
		}
		return true;
	}

	/*
	addText permet d'ajouter le texte de l'étudiant, pour cela on découpe son texte en mot et utilisant 
	un split sur les espaces, cela permettra à l'étudiant de sélectionner un mot clé précis dans son texte
	*/
	public void addText(String string) {
		String text[] = string.split(" ");
		int l = text.length;
		for (int i = 0; i<l; i++) {
			userText.add(new Word(text[i]));
		}
	}

	public void init() {
		
	}

}