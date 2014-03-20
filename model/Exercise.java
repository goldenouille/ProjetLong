package model;

import java.util.ArrayList;

import controller.ModelController;

public class Exercise {

	private ModelController modelController;	// the modelController helps to interact with the GUI

	private String name;		// name of the exercise
	private String preview;		// preview of the exercise, 
								// is used when the exercise is about to be open by the launcher

	private ArrayList<Word> text;	// list of words of the initial text of the current part
	private ArrayList<Word> userText;	// text added by the user

	private Graph graph;	// expected graph for the current part
	private Graph userGraph;	// graph build by the user

	private ArrayList<Part> parts;	// list of the different part
	private Part currentPart;	// the part the user is doing

	private Score score;	// used to compute the score

	// are used during the initialisation to give the parts to the GUI to be displayed
	private int auxNextPart;	
	private int nbParts;

	private int idUserItem;	// id of the last item the user added. this id is always negative
	private int nbUserWords;	// nb of words the user has added in the userText, it is used to know
								// the index of the word when displayed by the GUI

	/**
	 * Create a basic exercise
	 * 
	 */
	public Exercise() {

		this.text = new ArrayList<Word>();
		this.userText = new ArrayList<Word>();

		this.graph = new Graph();
		this.userGraph = new Graph();

		this.parts = new ArrayList<Part>();

		this.score = new Score();

		this.auxNextPart = -1;	// is always incremented before used

		this.nbParts = 0;

		this.idUserItem = 0;
		this.nbUserWords = -1;	// is always incremented before used
	}

	/**
	 * Get a word in a text using the index used when displayed by the Gui
	 * 
	 * @param firstWord
	 *				index of the beginning of the wanted expression in the displayed text
	 * @param lastWord
	 *				index of the end of the wanted expression in the displayed text
	 * @param t
	 *				text where the expression has to be looked for, can be either this.text or this.userText as used
	 *				by the fonctions of this version of L.U.N.E.
	 * @return the word corresponding to the index or null, if nothing matches
	 */
	public Word getByPosition(int firstWord, int lastWord, ArrayList<Word> t) {
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getFirstWord() <= firstWord && lastWord <= t.get(i).getLastWord() ) {
				return t.get(i);
			}
		}
		return null;
	}


	/**
	 * Get a word using its id
	 * 
	 * @param id
	 *				id of the wanted word
	 * @return the word corresponding to the id or null if it doesn't exist
	 */

	public Word getById(int id) {
		if (id < 0) {
			return getById(id, this.userText);
		} else {
			return getById(id, this.text);
		}
	}

	/**
	 * Get a word using its id in a specific text
	 * 
	 * @param id
	 *				id of the wanted word
	 * @param t
	 *				text where the word has to be looked for
	 * @return the word corresponding to the id or null if it doesn't exist
	 */
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


	// here a word is selected is its pourcentage is higher than 80%
	// if a part of a keyword get selected, all of it will be so

	// in this version of LUNE, if the length of the selection matches with the number of words
	// is not verified, but as it's for internal used, there is not problem
	/**
	 * Select the words in a specific text according by a given selection
	 * 
	 * @param selection
	 *				pourcentage of selection of the words in according with the index in the printed text
	 * @param userT
	 *				is true if the function has to use the userText, is false if it is the text
	 */
	public void selectText(int[] selection, boolean userT) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
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
	}

	// here a word is deselected is its pourcentage is higher than 80%
	// if a part of a keyword get deselected, all of it will be so

	// in this version of LUNE, if the length of the selection matches with the number of words
	// is not verified, but as it's for internal used, there is not problem
	/**
	 * Deselect the words in a specific text according by a given selection
	 * 
	 * @param selection
	 *				pourcentage of selection of the words in according with the index in the printed text
	 * @param userT
	 *				is true if the function has to use the userText, is false if it is the text
	 */
	public void unselectText(int[] selection, boolean userT) {
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
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
	}


	/**
	 * Get a text, split it into words according to spaces and add to the userText
	 * 
	 * @param string
	 *				text to split and add to the userText
	 * @return text splitted according to spaces
	 */
	public String[] addText(String string) {
		String text[] = string.split(" ");
		int l = text.length;
		for (int i = 0; i < l; i++) {
			this.idUserItem = this.idUserItem - 1;
			this.nbUserWords ++;
			Word w = new Word(text[i], this.idUserItem);
			w.setFirstWord(this.nbUserWords);
			w.setLastWord(this.nbUserWords);
			this.userText.add(w);
		}
		return text;
	}

	/**
	 * Initialize the parameters of the exercise before the user stats it
	 */
	public void init() {
			// load the current part and text
		this.text = this.parts.get(0).getText();
		this.currentPart = this.parts.get(0);
		this.graph = this.parts.get(0).getGraph();

		// create an array containing all the words of the initial text in order to give to the GUI
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

		// initializing the GUI set display
		this.modelController.doReplaceText(false, tab);
		this.modelController.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
		this.modelController.doSetValidateKeywordsButtonEnabled(true);
		this.modelController.doSetValidateAssociationButtonEnabled(false);
		this.modelController.doSetValidateDiagramButtonEnabled(false);

	}

	/**
	 * Add a part to the exercise
	 * 
	 * @param p
	 *				the part to add
	 */
	public void addPart(Part p) {
		// in this version of LUNE the composition of the part is static, but it was meant to be dynamic
		// and given by the XML file, and each step build by the stepFactory from its name
		p.addStep("selectKeyWord");
		p.addStep("LinkKeyWordToUML");
		p.addStep("BuildGraphUML");
		this.parts.add(p);
		this.nbParts = this.nbParts + 1;
	}

	public ModelController getModelController() {
		return this.modelController;
	}

	/**
	 * Add a class associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				text to split and add to the userText
	 * @param last
	 *				text to split and add to the userText
	 * @param userT
	 *				text to split and add to the userText
	 * @param name
	 *				text to split and add to the userText
	 * @return text splitted according to spaces
	 */
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
		removeUMLNatureAndGraphItemFromKeyWord(w);
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
		removeUMLNatureAndGraphItemFromKeyWord(w);
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
		removeUMLNatureAndGraphItemFromKeyWord(w);
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
		removeUMLNatureAndGraphItemFromKeyWord(w);
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
		removeUMLNatureAndGraphItemFromKeyWord(w);
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

	public void removeUMLNatureAndGraphItemFromKeyWord(Word w) {
			GraphItem gi = w.getUserGraphItem();
			if (gi != null) {
				this.modelController.doRemoveElementFromPool(w.getUserGraphItem(), w.getUserUmlNature());
			}
			w.setUserGraphItem(null);
			w.setUserUmlNature(null);
	}

	public void removeUMLNatureAndGraphItemFromKeyWord(int id) {
		if (id < 0) {
			Word w = getById(id, this.userText);
			if (w != null) {
				GraphItem gi = w.getUserGraphItem();
				w.setUserGraphItem(null);
				w.setUserUmlNature(null);
			}
		} else {
			Word w = getById(id, this.text);
			GraphItem gi = w.getUserGraphItem();
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
		// allow to check if it can be linked
		this.modelController.doLinkAttributeToClass(att, c);
	}

	public void askUnLinkAttributeToClass(Attribute att, VertexClass c) {
		c.removeAttribute(att);
		att.setMotherClass(null);
		// allow to check if it can be unlinked
		this.modelController.doUnLinkAttributeToClass(att,c);
	}

	public void askLinkMethodToClass(Method met, Vertex c) {
		c.addMethod(met);
		met.setMotherClass(c);
		// allow to check if it can be linked
		this.modelController.doLinkMethodToClass(met, c);
	}

	public void askUnLinkMethodToClass(Method met, Vertex c) {
		c.removeMethod(met);
		met.setMotherClass(null);
		// allow to check if it can be unlinked
		this.modelController.doUnLinkMethodToClass(met,c);
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	public void askCreateRelation(UMLNature nature, ArrayList<Vertex> v, ArrayList<String> multiplicity, String label) {
		this.idUserItem--;
		Edge newE = Edge.createEdge(nature, v, multiplicity, label, idUserItem);
		this.modelController.doAddRelationToDrawingArea(newE, nature); 
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
		this.modelController.doEditElementFromPool(id, UMLNature.INTERFACE);
	}

	public void editVertexAbstract(VertexAbstract id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.ABSTRACT_CLASS);

	}

	public void editVertexClass(VertexClass id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.CLASS);
	}



	public void editAttribut(Attribute id, String name, TypeBase type, Visibility visibility) {
		id.setName(name);
		id.setType(type);
		id.setVisibility(visibility);
		this.modelController.doEditElementFromPool(id, UMLNature.ATTRIBUTE);

	}

	public void editMethod(Method id, String name, ArrayList<Type> lp, Type returnType, Visibility visibility) {
		id.setName(name);
		id.setParamType(lp);
		id.setReturnType(returnType);
		id.setVisibility(visibility);
		this.modelController.doEditElementFromPool(id, UMLNature.METHOD);

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
