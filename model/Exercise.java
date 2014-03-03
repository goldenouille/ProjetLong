package model;

import java.util.ArrayList;

import controller.ModelController;

public class Exercise {

	private ModelController modelController;
	
	private String name;

	private ArrayList<Word> text;
	private ArrayList<Word> userText;

	private Graph graph;
	private Graph userGraph;

	private ArrayList<Part> parts;
	private int currentPart;

	public ArrayList<Word> getText() {
			return this.text;
	}

	public void setName(String s) {
		this.name = s;
	}
	public String getName() {
		return this.name;
	}

	public ArrayList<Word> getUserText() {
		return this.userText;
	}

	public ArrayList<Part> getParts() {
		return this.parts;
	}

	/*
	cette fonction sélectionne tous les mots qui sont sélectionnés à plus de 90 %
	TODO: implémenter une vérification pour éviter qu'on puisse tricher en sélectionnant l'intégralité du texte
	TODO: créer une excetion si la sélection n'est pas de la bonne dimension
	TODO: gérer le userText
	*/
	public void selectText(int[] selection) {
		if (selection.length == text.size()) {
			for (int i=0; i<selection.length; i++) {
    			if (selection[i]>90) {
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
		if (selection.length == text.size()) {
			for (int i=0; i<selection.length; i++) {
    			if (selection[i]>90) {
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
	public void addClass(int first, int last, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);
			
		for (int i= first; i<= last; i++) {
			userText.get(i).setUserUmlNature(UMLNature.CLASS);
			userText.get(i).setUserGraphItem(vertexClass);
		}	
		this.userGraph.addVertex(vertexClass);
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public void addAbstractClass(int first, int last, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);
			
		for (int i= first; i<= last; i++) {
			userText.get(i).setUserUmlNature(UMLNature.ABSTRACT_CLASS);
			userText.get(i).setUserGraphItem(vertexAbstract);
		}	
		this.userGraph.addVertex(vertexAbstract);
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public void addInterface(int firstWord, int lastWord, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		Vertex vertex = new Vertex();
		vertex.setName(name);
			
		for (int i= firstWord; i<= lastWord; i++) {
			t.get(i).setUserUmlNature(UMLNature.INTERFACE);
			t.get(i).setUserGraphItem(vertex);
		}	
		this.userGraph.addVertex(vertex);
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	// ne fonctionne qu'avec des types de bases
	public void addAttribute(int firstWord, int lastWord, boolean userT, String name, 
														String type, String visibility) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		Attribute att = new Attribute(name,TypeBase.getByName(type),Visibility.getByName(visibility));

		for (int i= firstWord; i<= lastWord; i++) {
			t.get(i).setUserUmlNature(UMLNature.ATTRIBUTE);
			t.get(i).setUserGraphItem(att);
		}	
		this.userGraph.addAttribute(att);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	// pour l'instant ne peut prendre que des types de base comme type de retour et comme parametres
	 
	public void addMethod(int firstWord, int lastWord, boolean userT, String name, 
											ArrayList<String> paramTypes, String returnType, String visibility) {
		ArrayList<Word> t;
		ArrayList<Type> params = new ArrayList<Type>();
		if (userT) {
			t = userText;
		} else {
			t = text;
		}

		for (int i= 0; i<paramTypes.size(); i++) {
			params.add(TypeBase.getByName(paramTypes.get(i)));
		}

		Method met = new Method(name, TypeBase.getByName(returnType), Visibility.getByName(visibility), params);
		for (int i= firstWord; i<= lastWord; i++) {
			t.get(i).setUserUmlNature(UMLNature.METHOD);
			t.get(i).setUserGraphItem(met);
		}	
		this.userGraph.addMethod(met);
	}

	// pas encore implementee
	public void selectPart(Object p) {
		System.out.println("askSelectPart from Exercise " + p.toString());
	}

	// pas encore implementee
	public void selectStep(Object s) {
		System.out.println("askSelectStep from Exercise " + s.toString());
	}

	// pas encore implementee
	public Object nextPart() {
		return null;
	}

	// pas encore implementee
	public Object nextStep() {
		return null;
	}

	// pas encore geree
	public String askScore() {
		return "0 / 100";
	}

}
