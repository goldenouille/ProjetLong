package model;

import java.util.ArrayList;

import controller.ModelController;

public class Exercise {

	private ModelController modelController;

	private String name;
	private String preview;

	private ArrayList<Word> text;
	private ArrayList<Word> userText;

	private Graph graph;
	private Graph userGraph;

	private ArrayList<Part> parts;
	private Part currentPart;

	private Score score;

	private int auxNextPart;
	private int nbParts;

	private int idUserItem;
	private int nbUserWords;

	public Exercise() {

		this.text = new ArrayList<Word>();
		this.userText = new ArrayList<Word>();

		this.graph = new Graph();
		this.userGraph = new Graph();

		this.parts = new ArrayList<Part>();

		this.score = new Score();

		this.auxNextPart = -1;

		this.nbParts = 0;

		this.idUserItem = 0;
		this.nbUserWords = -1;
	}

	public Exercise(ModelController modelController) {
		this.modelController = modelController;

		this.text = new ArrayList<Word>();
		this.userText = new ArrayList<Word>();

		this.graph = new Graph();
		this.userGraph = new Graph();

		this.parts = new ArrayList<Part>();

		this.auxNextPart = -1;

		this.nbParts = 0;
	}

	public Word getByPosition(int firstWord, int lastWord, ArrayList<Word> t) {
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getFirstWord() <= firstWord && lastWord <= t.get(i).getLastWord() ) {
				return t.get(i);
			}
		}
		return null;
	}

	public Word getById(int id, ArrayList<Word> t) {
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getId() == id) {
				return t.get(i);
			}
		}
		return null;
	}

	public ArrayList<Word> getText() {
		return this.text;
	}

	public Part getCurrentPart() {
		return this.currentPart;
	}

	public void setName(String s) {
		this.name = s;
	}

	public String getName() {
		return this.name;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public Graph getUserGraph() {
		return this.userGraph;
	}
	
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public ArrayList<Word> getUserText() {
		return this.userText;
	}

	public ArrayList<Part> getParts() {
		return this.parts;
	}

	/*
	 * cette fonction sélectionne tous les mots qui sont sélectionnés à plus
	 * de 90 % TODO: implémenter une vérification pour éviter qu'on puisse
	 * tricher en sélectionnant l'intégralité du texte TODO: créer une
	 * excetion si la sélection n'est pas de la bonne dimension TODO: gérer le
	 * userText
	 */
	public void selectText(int[] selection, boolean userT) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}


		//if (selection.length == t.size()) {
			for (int i=0; i<selection.length; i++) {
    			if (selection[i]>=80) {
    				Word w = getByPosition(i,i,t);
    				
    				w.select();
    				
    				try {
    				this.modelController.doSelectText(w.getFirstWord(),w.getLastWord(), userT );
    				} catch (Exception e) {
    					e.printStackTrace();
    				}	
       			}

			}
		//} // else {LEVER EXCEPTION !! !! !!}
	}

	/*
	 * cette fonction déselectionne tous les mots sélectionnés à plus de 90
	 * % pas besoin de vérification supplémentaire sur cette fonction TODO:
	 * créer une exception si la sélection n'est pas de la bonne dimension
	 * TODO: gérer le userText
	 */
	public void unselectText(int[] selection, boolean userT) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}


		//if (selection.length == t.size()) {
			for (int i=0; i<selection.length; i++) {
    			if (selection[i]>80) {
    				Word w = getByPosition(i,i,t);
    				w.unselect();
    				try {
    				this.modelController.doResetTextHighlight(w.getFirstWord(),w.getLastWord(), userT );
    				this.modelController.doUnSelectText(w.getFirstWord(),w.getLastWord(), userT );
    				} catch (Exception e) {
    					e.printStackTrace();
    				}	
    			}
			}
		//} // else {LEVER EXCEPTION !! !! !!}
	}

	/*
	 * addText permet d'ajouter le texte de l'étudiant, pour cela on découpe
	 * son texte en mot et utilisant un split sur les espaces, cela permettra à
	 * l'étudiant de sélectionner un mot clé précis dans son texte
	 */
	public String[] addText(String string) {
		String text[] = string.split(" ");
		int l = text.length;
		for (int i = 0; i < l; i++) {
			this.idUserItem = this.idUserItem - 1;
			this.nbUserWords --;
			Word w = new Word(text[i], this.idUserItem);
			w.setFirstWord(this.nbUserWords);
			w.setLastWord(this.nbUserWords);
			this.userText.add(w);
		}
		return text;
	}

	public void init() {
		this.text = this.parts.get(0).getText();
		this.currentPart = this.parts.get(0);
		this.graph = this.parts.get(0).getGraph();

		ArrayList<String> list = new ArrayList<String>();
		String[] str;
		for (int i = 0; i < text.size(); i++) {
			str = text.get(i).getWord().split(" ");
			for (int j = 0; j < str.length; j++) {
				list.add(str[j]);
			}
		}
		String[] tab = new String[list.size()];
		list.toArray(tab);

		this.score = this.parts.get(0).getScore();

		this.modelController.doReplaceText(false, tab);
		this.modelController.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
		this.modelController.doSetValidateKeywordsButtonEnabled(true);
		this.modelController.doSetValidateAssociationButtonEnabled(false);
		this.modelController.doSetValidateDiagramButtonEnabled(false);

	}

	public void addPart(Part p) {
		p.addStep("selectKeyWord");
		p.addStep("LinkKeyWordToUML");
		// p.addStep("BuildUMLGraph");
		this.parts.add(p);
		this.nbParts = this.nbParts + 1;
	}

	public ModelController getModelController() {
		return this.modelController;
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public Vertex addClass(int first, int last, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);

		Word w = getByPosition(first, last, t);
		// TODO = verifier que la fin du mot correspond

		vertexClass.setId(w.getId());
		w.setUserUmlNature(UMLNature.CLASS);
		w.setUserGraphItem(vertexClass);

		this.userGraph.addVertex(vertexClass);
		return vertexClass;
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public Vertex addAbstractClass(int first, int last, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);

		Word w = getByPosition(first, last, t);
		// TODO = verifier que la fin du mot correspond

		vertexAbstract.setId(w.getId());
		w.setUserUmlNature(UMLNature.ABSTRACT_CLASS);
		w.setUserGraphItem(vertexAbstract);

		this.userGraph.addVertex(vertexAbstract);
		return vertexAbstract;
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	public Vertex addInterface(int first, int last, boolean userT, String name) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		Vertex vertex = new Vertex();
		vertex.setName(name);

		Word w = getByPosition(first, last, t);
		// TODO = verifier que la fin du mot correspond

		vertex.setId(w.getId());
		w.setUserUmlNature(UMLNature.INTERFACE);
		w.setUserGraphItem(vertex);

		this.userGraph.addVertex(vertex);
		return vertex;
	}

	// BEAUCOUP DE VERIFICATION A IMPLEMENTER
	// ne fonctionne qu'avec des types de bases
	public Attribute addAttribute(int first, int last, boolean userT, String name, String type, String visibility) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
		Attribute att = new Attribute(name, TypeBase.getByName(type), Visibility.getByName(visibility));

		Word w = getByPosition(first, last, t);
		// TODO = verifier que la fin du mot correspond

		att.setId(w.getId());
		w.setUserUmlNature(UMLNature.ATTRIBUTE);
		w.setUserGraphItem(att);

		this.userGraph.addAttribute(att);
		return att;
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	// pour l'instant ne peut prendre que des types de base comme type de retour
	// et comme parametres

	public Method addMethod(int first, int last, boolean userT, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		ArrayList<Word> t;
		ArrayList<Type> params = new ArrayList<Type>();
		if (userT) {
			t = userText;
		} else {
			t = text;
		}

		for (int i = 0; i < paramTypes.size(); i++) {
			params.add(TypeBase.getByName(paramTypes.get(i)));
		}

		Method met = new Method(name, TypeBase.getByName(returnType), Visibility.getByName(visibility), params);

		Word w = getByPosition(first, last, t);
		// TODO = verifier que la fin du mot correspond

		met.setId(w.getId());
		w.setUserUmlNature(UMLNature.METHOD);
		w.setUserGraphItem(met);

		this.userGraph.addMethod(met);
		return met;
	}

	public void selectPart(Object p) {
		Part part = (Part) p;
		this.text = part.getText();
		this.currentPart = part;
		this.graph = part.getGraph();
		
		ArrayList<String> list = new ArrayList<String>();
		String[] str;
		for (int i = 0; i < text.size(); i++) {
			str = text.get(i).getWord().split(" ");
			for (int j = 0; j < str.length; j++) {
				list.add(str[j]);
			}
		}
		String[] tab = new String[list.size()];
		list.toArray(tab);
		
		this.score = part.getScore();
		this.modelController.doReplaceText(false, tab);
		this.modelController.doReplaceText(true, (new String[] {}));
		this.modelController.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
		this.modelController.doSetValidateKeywordsButtonEnabled(true);
		this.modelController.doSetValidateAssociationButtonEnabled(false);
		this.modelController.doSetValidateDiagramButtonEnabled(false);
		System.out.println("askSelectPart from Exercise " + p.toString());
	}

	// pas encore implementee
	public void selectStep(Object s) {
		System.out.println("askSelectStep from Exercise " + s.toString());
	}

	// pas encore implementee
	public Object nextPart() {
		if (auxNextPart + 1 < this.parts.size()) {
			this.auxNextPart = this.auxNextPart + 1;
			return this.parts.get(this.auxNextPart);
		} else {
			return null;
		}
	}

	// pas encore implementee
	public Object nextStep() {
		return this.parts.get(auxNextPart).nextStep();
	}

	// pas encore geree
	public String askScore() {
		return "0 / 100";
	}

	public void removeUMLNatureAndGraphItemFromKeyWord(int id) {
		if (id < 0) {
			Word w = getById(id, this.userText);
			if (w != null) {
				w.setUserGraphItem(null);
				w.setUserUmlNature(null);
			}
		} else {
			Word w = getById(id, this.text);
			w.setUserGraphItem(null);
			w.setUserUmlNature(null);
		}
	}

	public void askDeleteClass(VertexClass vertexClass) {
		if (vertexClass.isDeletable()) {
			int id = vertexClass.getId();
			this.userGraph.removeClass(vertexClass);
			removeUMLNatureAndGraphItemFromKeyWord(id);
			this.modelController.doRemoveElementFromPool(vertexClass,UMLNature.CLASS);
		} else {
			this.modelController.doPrintMessage("Classe non supprimable",
					"La classe que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Sends to the core the user's request to delete the given abstract class
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteAbstractClass(VertexAbstract vertexAbstract) {
		if (vertexAbstract.isDeletable()) {
			int id = vertexAbstract.getId();
			this.userGraph.removeAbstractClass(vertexAbstract);
			removeUMLNatureAndGraphItemFromKeyWord(id);
			this.modelController.doRemoveElementFromPool(vertexAbstract,UMLNature.ABSTRACT_CLASS);
		} else {
			this.modelController.doPrintMessage("Classe abstraite non supprimable",
					"La que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Sends to the core the user's request to delete the given interface
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteInterface(Vertex vertex) {
		if (vertex.isDeletable()) {
			int id = vertex.getId();
			this.userGraph.removeInterface(vertex);
			removeUMLNatureAndGraphItemFromKeyWord(id);
			this.modelController.doRemoveElementFromPool(vertex, UMLNature.INTERFACE);
		} else {
			this.modelController.doPrintMessage("Interface non supprimable",
					"L'interface que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Sends to the core the user's request to delete the given attribute
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteAttribute(Attribute att) {
		if (att.isDeletable()) {
			int id = att.getId();
			this.userGraph.removeAttribute(att);
			removeUMLNatureAndGraphItemFromKeyWord(id);
			this.modelController.doRemoveElementFromPool(att,UMLNature.ATTRIBUTE);
		} else {
			this.modelController.doPrintMessage("Attribute non supprimable",
					"L'attribute que vous cherchez a supprimer a ete valide et donc ne peut etre supprime.");
		}
	}

	/**
	 * Sends to the core the user's request to delete the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteMethod(Method met) {
		if (met.isDeletable()) {
			int id = met.getId();
			this.userGraph.removeMethod(met);
			removeUMLNatureAndGraphItemFromKeyWord(id);
			this.modelController.doRemoveElementFromPool(met,UMLNature.METHOD);
		} else {
			this.modelController.doPrintMessage("Method non supprimable",
					"La methode que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	public void askLinkAttributeToClass(Attribute att, VertexClass c) {
		c.addAttribute(att);
		att.setMotherClass(c);
	}

	public void askLinkMethodToClass(Method met, Vertex c) {
		c.addMethod(met);
		met.setMotherClass(c);
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	public void askCreateRelation(UMLNature nature, ArrayList<Vertex> v, ArrayList<String> multiplicity, String label) {
		this.idUserItem--;
		Edge newE = Edge.createEdge(nature, v, multiplicity, label, idUserItem);
	}

	public void askDeleteRelation(Edge id) {
		if (id.isDeletable()) {
			userGraph.removeEdge(id);
			// allow to check the relation can be deleted or not
			this.modelController.doDeleteRelation(id);
		} else {
			this.modelController.doPrintMessage("Relation non supprimable",
					"La relation que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	public ArrayList<Vertex> askUMLRelationClasses(Edge id) {
		return id.getVertex();
	}

	public ArrayList<String> askUMLRelationMultiplicity(Edge id) {
		return id.getMultiplicity();
	}

	public void editVertex(Vertex id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.CLASS);

	}

	public void editAttribut(Attribute id, String name, TypeBase type, Visibility visibility) {
		id.setName(name);
		id.setType(type);
		id.setVisibility(visibility);
	}

	public void editMethod(Method id, String name, ArrayList<Type> lp, Type returnType, Visibility visibility) {
		id.setName(name);
		id.setParamType(lp);
		id.setReturnType(returnType);
		id.setVisibility(visibility);
	}

	public void askEditRelation(Edge id, ArrayList<String> multiplicity, String name) {
		id.setName(name);
		if (id instanceof Association) {
			((Association) id).setMultiplicities(multiplicity);
		}
		// allow to check what can be modified or not
		this.modelController.doEditRelation(id); 
	}

	public String askUMLRelationText(Edge id) {
		return id.getName();
	}

	public void askReverseRelation(Object id) {
		if (id instanceof DirectionalRelation) {
			((DirectionalRelation) id).reverseRelation();
		} else if (id instanceof BinaryAssociation) {
			((BinaryAssociation) id).reverseRelation();
		}
		// allow to check what can be reversed or not
		this.modelController.doReverseRelation(id);
	}

	public Vertex askCreateClassInPanel(String name) {
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);
		this.idUserItem--;
		vertexClass.setId(this.idUserItem);
		this.userGraph.addVertex(vertexClass);
		return vertexClass;
	}

	public Vertex askCreateAbstractClassInPanel(String name) {
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);
		this.idUserItem--;
		vertexAbstract.setId(this.idUserItem);
		this.userGraph.addVertex(vertexAbstract);
		return vertexAbstract;
	}

	public Vertex askCreateInterfaceInPanel(String name) {
		Vertex v = new Vertex();
		v.setName(name);
		this.idUserItem--;
		v.setId(this.idUserItem);
		this.userGraph.addVertex(v);
		return v;
	}

	public Attribute askCreateAttributeInPanel(String name, String type, String visibility) {
		Attribute att = new Attribute(name, TypeBase.getByName(type), Visibility.getByName(visibility));
		this.idUserItem--;
		att.setId(this.idUserItem);
		this.userGraph.addAttribute(att);
		return att;
	}

	public Method askCreateMethodInPanel(String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		ArrayList<Type> params = new ArrayList<Type>();
		for (int i = 0; i < paramTypes.size(); i++) {
			params.add(TypeBase.getByName(paramTypes.get(i)));
		}

		Method met = new Method(name, TypeBase.getByName(returnType), Visibility.getByName(visibility), params);
		this.idUserItem--;
		met.setId(this.idUserItem);
		this.userGraph.addMethod(met);
		return met;
	}

}
