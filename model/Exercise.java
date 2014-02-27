package model;

import java.util.ArrayList;

import controller.ModelController;

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


	public ArrayList<Word> getUserText() {
		return this.userText;
	}

	/*
	cette fonction sélectionne tous les mots qui sont sélectionnés à plus de 90 %
	TODO: implémenter une vérification pour éviter qu'on puisse tricher en sélectionnant l'intégralité du texte
	TODO: créer une excetion si la sélection n'est pas de la bonne dimension
	TODO: gérer le userText
	*/
	public void selectText(int[] selection) {
		if (selection.length = text.size()) {
			for (int i=0; i<selection.size(); i++) {
    			if (selection.[i]>90) {
    				text.get(i).select();
    			}
			}
		} // else {LEVER EXCEPTION !! !! !!}
	}

	/*
	cette fonction déselectionne tous les mots sélectionnés à plus de 90 %
	pas besoin de vérification supplémentaire sur cette fonction
	TODO: créer une exception si la sélection n'est pas de la bonne dimension
	TODO: gérer le userText
	*/
	public void unselectText(int[] selection) {
		if (selection.length != text.size()) {
			for (int i=0; i<selection.size(); i++) {
    			if (selection.get(i)>90) {
    				text.get(i).unselect();
    			}
			}
		} // else {LEVER EXCEPTION !! !! !!}
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
	
	public void addPart(Part p) {
		this.parts.add(p);
	}

	public ModelController getModelController() {
		return this.modelController;
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public void addClass(int fisrt, int last, boolean userText, String name) {
		ArrayList<Word> t;
		if (userText) {
			t = exo.getUserText();
		} else {
			t = exo.getText();
		}
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);
			
		for (int i= first; i<= last; i++) {
			userText.get(i).setUserUmlNature(UMLNature.CLASS);
			userText.get(i).setUserGraphItem(vertexClass);
		}	
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public void addAbstractClass(int fisrt, int last, boolean userText, String name) {
		ArrayList<Word> t;
		if (userText) {
			t = exo.getUserText();
		} else {
			t = exo.getText();
		}
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);
			
		for (int i= first; i<= last; i++) {
			userText.get(i).setUserUmlNature(UMLNature.ABSTRACT_CLASS);
			userText.get(i).setUserGraphItem(vertexAbstract);
		}	
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	addInterface(firstWord, lastWord, userText, name) {
		ArrayList<Word> t;
		if (userText) {
			t = exo.getUserText();
		} else {
			t = exo.getText();
		}
		Vertex vertex = new Vertex();
		vertex.setName(name);
			
		for (int i= first; i<= last; i++) {
			userText.get(i).setUserUmlNature(UMLNature.INTERFACE);
			userText.get(i).setUserGraphItem(vertex);
		}	
	}


}
