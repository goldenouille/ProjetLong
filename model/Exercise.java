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

	// in this version of LUNE, there is a lack of verification, 
	//especially concerning the index and the word selected
	/**
	 * Add a class associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				index of the beginning of the printed word
	 * @param last
	 *				index of the end of the printed word
	 * @param userT
	 *				true if the word has to looked for in the userText, false if it's in the text
	 * @param name
	 *				name of the abstract class to create
	 * @return the created class
	 */
	public Vertex addClass(int first, int last, boolean userT, String name) {
			// find which text the function has to deal with
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
			// create a class and set its name
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);

			/* find the word associated to the index
			   and remove from it its previous UserUMLNature and UserGaphItem
			   and if necessary the UserGraphItem from the graph and the graphic pool of element
			*/
		Word w = getByPosition(first, last, t);
		removeUMLNatureAndGraphItemFromKeyWord(w);
			// associate to the class the id of the associated word
		vertexClass.setId(w.getId());
			// refill the userUMLNature and userGaphItem fields with the new values
		w.setUserUmlNature(UMLNature.CLASS);
		w.setUserGraphItem(vertexClass);
			// add the new class to the current graph
		this.userGraph.addVertex(vertexClass);
		return vertexClass;
	}

	// in this version of LUNE, there is a lack of verification, 
	//especially concerning the index and the word selected
	/**
	 * Add an abstract class associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				index of the beginning of the printed word
	 * @param last
	 *				index of the end of the printed word
	 * @param userT
	 *				true if the word has to looked for in the userText, false if it's in the text
	 * @param name
	 *				name of the abstract class to create
	 * @return the created abstract class
	 */
	public Vertex addAbstractClass(int first, int last, boolean userT, String name) {
			// find which text the function has to deal with
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
			// create an abstract class and set its name
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);
			/* find the word associated to the index
			   and remove from it its previous UserUMLNature and UserGaphItem
			   and if necessary the UserGraphItem from the graph and the graphic pool of element
			*/
		Word w = getByPosition(first, last, t);
		removeUMLNatureAndGraphItemFromKeyWord(w);
			// associate to the abstract class the id of the associated word
		vertexAbstract.setId(w.getId());
			// refill the userUMLNature and userGaphItem fields with the new values
		w.setUserUmlNature(UMLNature.ABSTRACT_CLASS);
		w.setUserGraphItem(vertexAbstract);
			// add the new abstract class to the current graph
		this.userGraph.addVertex(vertexAbstract);
		return vertexAbstract;
	}

	// in this version of LUNE, there is a lack of verification, 
	//especially concerning the index and the word selected
	/**
	 * Add an interface associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				index of the beginning of the printed word
	 * @param last
	 *				index of the end of the printed word
	 * @param userT
	 *				true if the word has to looked for in the userText, false if it's in the text
	 * @param name
	 *				name of the interface to create
	 * @return the created interface
	 */
	public Vertex addInterface(int first, int last, boolean userT, String name) {
			// find which text the function has to deal with
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
			// create an interface and set its name
		Vertex vertex = new Vertex();
		vertex.setName(name);
			/* find the word associated to the index
			   and remove from it its previous UserUMLNature and UserGaphItem
			   and if necessary the UserGraphItem from the graph and the graphic pool of element
			*/
		Word w = getByPosition(first, last, t);
		removeUMLNatureAndGraphItemFromKeyWord(w);
			// associate to the interface the id of the associated word
		vertex.setId(w.getId());
			// refill the userUMLNature and userGaphItem fields with the new values
		w.setUserUmlNature(UMLNature.INTERFACE);
		w.setUserGraphItem(vertex);
			// add the new interface to the current graph
		this.userGraph.addVertex(vertex);
		return vertex;
	}

	/* in this version of LUNE, there is a lack of verification, especially concerning the index 
	   and the word selected
	   for now attributes can only have basic java type (corresponding to the TypeBase enum)
	*/
	/**
	 * Add an attribut associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				index of the beginning of the printed word
	 * @param last
	 *				index of the end of the printed word
	 * @param userT
	 *				true if the word has to looked for in the userText, false if it's in the text
	 * @param name
	 *				name of the attribute to create
	 * @return the created attribute
	 */
	public Attribute addAttribute(int first, int last, boolean userT, String name, String type, String visibility) {
			// find which text the function has to deal with
		ArrayList<Word> t;
		if (userT) {
			t = userText;
		} else {
			t = text;
		}
			// create an attribute with all its parameters
		Attribute att = new Attribute(name, TypeBase.getByName(type), Visibility.getByName(visibility));
			/* find the word associated to the index
			   and remove from it its previous UserUMLNature and UserGaphItem
			   and if necessary the UserGraphItem from the graph and the graphic pool of element
			*/
		Word w = getByPosition(first, last, t);
		removeUMLNatureAndGraphItemFromKeyWord(w);
			// associate to the attribute the id of the associated word
		att.setId(w.getId());
			// refill the userUMLNature and userGaphItem fields with the new values
		w.setUserUmlNature(UMLNature.ATTRIBUTE);
		w.setUserGraphItem(att);
			// add the new attribute to the current graph
		this.userGraph.addAttribute(att);
		return att;
	}

	/* in this version of LUNE, there is a lack of verification, especially concerning the index 
	   and the word selected
	   for now methods can only use basic java types (corresponding to the TypeBase enum)
	*/
	/**
	 * Add a method associated to a word characterized by its index in the text given by userT
	 * 
	 * @param first
	 *				index of the beginning of the printed word
	 * @param last
	 *				index of the end of the printed word
	 * @param userT
	 *				true if the word has to looked for in the userText, false if it's in the text
	 * @param name
	 *				name of the method to create
	 * @return the created method
	 */
	public Method addMethod(int first, int last, boolean userT, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
			// find which text the function has to deal with
		ArrayList<Word> t;
		ArrayList<Type> params = new ArrayList<Type>();
		if (userT) {
			t = userText;
		} else {
			t = text;
		}	
			// find all the types corresponding to the string list of the parameters
		for (int i = 0; i < paramTypes.size(); i++) {
			params.add(TypeBase.getByName(paramTypes.get(i)));
		}
			// create a class and set its name
		Method met = new Method(name, TypeBase.getByName(returnType), Visibility.getByName(visibility), params);
			/* find the word associated to the index
			   and remove from it its previous UserUMLNature and UserGaphItem
			   and if necessary the UserGraphItem from the graph and the graphic pool of element
			*/
		Word w = getByPosition(first, last, t);
		removeUMLNatureAndGraphItemFromKeyWord(w);
			// associate to the method the id of the associated word
		met.setId(w.getId());
			// refill the userUMLNature and userGaphItem fields with the new values
		w.setUserUmlNature(UMLNature.METHOD);
		w.setUserGraphItem(met);
			// add the new method to the current graph
		this.userGraph.addMethod(met);
		return met;
	}

	/**
	 * Select a part of the exercise in according with the input object
	 * 
	 * @param p
	 *				object allowing to find the corresponding part in the exercise
	 */
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

	// not implemented in this version of LUNE
	public void selectStep(Object s) {
		System.out.println("askSelectStep from Exercise " + s.toString());
	}

	/**
	 * Function used during the initialization of the gui, give successively all the parts of the exercise
	 * 
	 * @return the next part
	 */
	public Object nextPart() {
		if (auxNextPart + 1 < this.parts.size()) {
			this.auxNextPart = this.auxNextPart + 1;
			return this.parts.get(this.auxNextPart);
		} else {
			return null;
		}
	}

	/**
	 * Function used during the initialization of the gui, give successively all the steps of the current part
	 * 
	 * @return the next step of the current
	 */
	public Object nextStep() {
		return this.parts.get(auxNextPart).nextStep();
	}

	/**
	 * Remove the userUMLNature and the userGraphItem from a word and if necessary, the userGraphitem 
	 *	from the graph and the graphic pool of elements
	 * 
	 * @param w
	 *			the word that needs to be cleaned
	 */
	public void removeUMLNatureAndGraphItemFromKeyWord(Word w) {
			GraphItem gi = w.getUserGraphItem();
			if (gi != null) {
				this.modelController.doRemoveElementFromPool(w.getUserGraphItem(), w.getUserUmlNature());
				this.userGraph.remove(gi);
			}
			w.setUserGraphItem(null);
			w.setUserUmlNature(null);
	}

	/**
	 * Remove the userUMLNature and the userGraphItem from a word if it exists necessary and the input userGraphitem 
	 *	from the graph and the graphic pool of elements
	 * 
	 * @param gi
	 *			the graphItem that needs to be cleaned
	 */
	public void removeGraphItemFromGraphAndKeyWord(GraphItem gi) {
		Word w = getById(gi.getId());
		if (w!=null) {
			w.setUserGraphItem(null);
			w.setUserUmlNature(null);
		} 
		this.modelController.doRemoveElementFromPool(w.getUserGraphItem(), w.getUserUmlNature());
		this.userGraph.remove(gi);
	}

	/**
	 * Delete the given class
	 * 
	 * @param vertexClass
	 *			the class to delete
	 */
	public void askDeleteClass(VertexClass vertexClass) {
		if (vertexClass.isDeletable()) {
			removeGraphItemFromGraphAndKeyWord(vertexClass);
			this.modelController.doRemoveElementFromPool(vertexClass,UMLNature.CLASS);
		} else {
			this.modelController.doPrintMessage("Classe non supprimable",
					"La classe que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Delete the given abstract class
	 * 
	 * @param vertexAbstract
	 *            the abstract class to delete
	 */
	public void askDeleteAbstractClass(VertexAbstract vertexAbstract) {
		if (vertexAbstract.isDeletable()) {
			removeGraphItemFromGraphAndKeyWord(vertexAbstract);
			this.modelController.doRemoveElementFromPool(vertexAbstract,UMLNature.ABSTRACT_CLASS);
		} else {
			this.modelController.doPrintMessage("Classe abstraite non supprimable",
					"La classe abstraite que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Delete the given interface
	 * 
	 * @param vertex
	 *            interface to delete
	 */
	public void askDeleteInterface(Vertex vertex) {
		if (vertex.isDeletable()) {
			removeGraphItemFromGraphAndKeyWord(vertex);
			this.modelController.doRemoveElementFromPool(vertex, UMLNature.INTERFACE);
		} else {
			this.modelController.doPrintMessage("Interface non supprimable",
					"L'interface que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Delete the given attribute
	 * 
	 * @param att
	 *            attribute to delete
	 */
	public void askDeleteAttribute(Attribute att) {
		if (att.isDeletable()) {
			removeGraphItemFromGraphAndKeyWord(att);
			this.modelController.doRemoveElementFromPool(att,UMLNature.ATTRIBUTE);
		} else {
			this.modelController.doPrintMessage("Attribute non supprimable",
					"L'attribute que vous cherchez a supprimer a ete valide et donc ne peut etre supprime.");
		}
	}

	/**
	 * Delete the given method instance
	 * 
	 * @param met
	 *            identifier of the instance to delete
	 */
	public void askDeleteMethod(Method met) {
		if (met.isDeletable()) {
			removeGraphItemFromGraphAndKeyWord(met);
			this.modelController.doRemoveElementFromPool(met,UMLNature.METHOD);
		} else {
			this.modelController.doPrintMessage("Method non supprimable",
					"La methode que vous cherchez a supprimer a ete validee et donc ne peut etre supprimee.");
		}
	}

	/**
	 * Link the given attribute to the given class or abstract class
	 * 
	 * @param att
	 *            attribute to link
	 * @param c
	 *			 chosen motherClass
	 */
	public void askLinkAttributeToClass(Attribute att, VertexClass c) {
		c.addAttribute(att);
		att.setMotherClass(c);
		// allow to check if it can be linked
		this.modelController.doLinkAttributeToClass(att, c);
	}

	/**
	 * Unlink the given attribute from the given class or abstract class
	 * 
	 * @param att
	 *            attribute to unlink
	 * @param c
	 *			 ex-motherClass
	 */
	public void askUnLinkAttributeToClass(Attribute att, VertexClass c) {
		c.removeAttribute(att);
		att.setMotherClass(null);
		// allow to check if it can be unlinked
		this.modelController.doUnLinkAttributeToClass(att,c);
	}

	/**
	 * Link the given method to the given class, abstract class or interface
	 * 
	 * @param met
	 *            method to link
	 * @param c
	 *			 chosen motherClass
	 */
	public void askLinkMethodToClass(Method met, Vertex c) {
		c.addMethod(met);
		met.setMotherClass(c);
		// allow to check if it can be linked
		this.modelController.doLinkMethodToClass(met, c);
	}

	/**
	 * Unlink the given method to the given class, abstract class or interface
	 * 
	 * @param met
	 *            method to unlink
	 * @param c
	 *			 ex-motherClass
	 */
	public void askUnLinkMethodToClass(Method met, Vertex c) {
		c.removeMethod(met);
		met.setMotherClass(null);
		// allow to check if it can be unlinked
		this.modelController.doUnLinkMethodToClass(met,c);
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	/**
	 * Create a relation with all its parameters
	 * 
	 * @param nature
	 *           UML nature of the wanted relation
	 * @param v
	 *			 list of the vertex linked by the relation
	 * @param multiplicity
	 *			 list of the multiplicities associated to each vertex, the order in this list is the same as in the previous one
	 * @param label
	 *			string to print near the relation when drawn
	 */
	public void askCreateRelation(UMLNature nature, ArrayList<Vertex> v, ArrayList<String> multiplicity, String label) {
		this.idUserItem--;
		Edge newE = Edge.createEdge(nature, v, multiplicity, label, idUserItem);
		this.modelController.doAddRelationToDrawingArea(newE, nature); 
	}

	/**
	 * Delete the given relation
	 * 
	 * @param id
	 *            the edge to delete
	 * @param c
	 *			 chosen motherClass
	 */
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

	/**
	 * Get the list of the vertex which are linked by the edge
	 * 
	 * @param id
	 *            the aimed edge
	 * @return the list of the vertex of the input edge
	 */
	public ArrayList<Vertex> askUMLRelationClasses(Edge id) {
		return id.getVertex();
	}

	/**
	 * Get the list of the multiplicities of each vertex linked to the edge
	 * 
	 * @param id
	 *            the aimed edge
	 * @param the list of the multiplicities
	 */
	public ArrayList<String> askUMLRelationMultiplicity(Edge id) {
		return id.getMultiplicity();
	}
	/**
	 * Edit the name of an interface
	 * 
	 * @param id
	 *           the interface to edit
	 * @param name
	 *			 the new name of the interface
	 */
	public void editVertex(Vertex id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.INTERFACE);
	}
	/**
	 * Edit the name of an abstract class
	 * 
	 * @param id
	 *           the abstract class to edit
	 * @param name
	 *			 the new name of the abstract class
	 */
	public void editVertexAbstract(VertexAbstract id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.ABSTRACT_CLASS);

	}
	/**
	 * Edit the name of a class
	 * 
	 * @param id
	 *           the class to edit
	 * @param name
	 *			 the new name of the class
	 */
	public void editVertexClass(VertexClass id, String name) {
		id.setName(name);
		this.modelController.doEditElementFromPool(id, UMLNature.CLASS);
	}


	/**
	 * Edit the name, the type and the visibility of an attribute
	 * 
	 * @param id
	 *           the attribute to edit
	 * @param name
	 *			 the new name of the attribute
	 * @param type
	 *			 the new type of the attribute, can only be a basic java type (TypeBase)
	 * @param visibility
	 *			 the new visibility of the attribute
	 */
	public void editAttribut(Attribute id, String name, TypeBase type, Visibility visibility) {
		id.setName(name);
		id.setType(type);
		id.setVisibility(visibility);
		this.modelController.doEditElementFromPool(id, UMLNature.ATTRIBUTE);

	}

	/**
	 * Edit the name, the parameters' types, the return type and the visibility of a method
	 * 
	 * @param id
	 *           the method to edit
	 * @param name
	 *			 the new name of the method
	 * @param lp
	 *			 the new list of the parameters' types, can only be basic jaba types (TypeBase)
	 * @param returnType
	 *			 the new return type of the method, can only be a basic java type (TypeBase)
	 * @param visibility
	 *			 the new visibility of the method
	 */
	public void editMethod(Method id, String name, ArrayList<Type> lp, Type returnType, Visibility visibility) {
		id.setName(name);
		id.setParamType(lp);
		id.setReturnType(returnType);
		id.setVisibility(visibility);
		this.modelController.doEditElementFromPool(id, UMLNature.METHOD);

	}

	/**
	 * Edit the multiplicities and the name of a relation
	 * 
	 * @param id
	 *           the relation to edit
	 * @param multiplicity
	 *			 the new multiplicity list of the relation
	 * @param name
	 *			 the new name of the relation
	 */
	public void askEditRelation(Edge id, ArrayList<String> multiplicity, String name) {
		id.setName(name);
		if (id instanceof Association) {
			((Association) id).setMultiplicities(multiplicity);
		}
		// allow to check what can be modified or not
		this.modelController.doEditRelation(id); 
	}

	/**
	 * Get the text to print near the relation when drawn
	 * 
	 * @param id
	 *           the wanted relation
	 * @param the label to print 
	 */
	public String askUMLRelationText(Edge id) {
		return id.getName();
	}

	/**
	 * Reverse a binary relation
	 * 
	 * @param id
	 *           the edge to reverse
	 */
	public void askReverseRelation(Edge id) {
		if (id instanceof DirectionalRelation) {
			((DirectionalRelation) id).reverseRelation();
		} else if (id instanceof BinaryAssociation) {
			((BinaryAssociation) id).reverseRelation();
		}
		// allow to check what can be reversed or not
		this.modelController.doReverseRelation(id);
	}

	/**
	 * Create a class in the graphic pool of elements, not associated to a word
	 * 
	 * @param name
	 *           name of the class to create
	 * @return the created class
	 */
	public Vertex askCreateClassInPanel(String name) {
		VertexClass vertexClass = new VertexClass();
		vertexClass.setName(name);
		this.idUserItem--;
		vertexClass.setId(this.idUserItem);
		this.userGraph.addVertex(vertexClass);
		return vertexClass;
	}

	/**
	 * Create an abstract class in the graphic pool of elements, not associated to a word
	 * 
	 * @param name
	 *           name of the abstract class to create
	 * @return the created abstract class
	 */
	public Vertex askCreateAbstractClassInPanel(String name) {
		VertexAbstract vertexAbstract = new VertexAbstract();
		vertexAbstract.setName(name);
		this.idUserItem--;
		vertexAbstract.setId(this.idUserItem);
		this.userGraph.addVertex(vertexAbstract);
		return vertexAbstract;
	}

	/**
	 * Create an interface in the graphic pool of elements, not associated to a word
	 * 
	 * @param name
	 *           name of the interface to create
	 * @return the created interface
	 */
	public Vertex askCreateInterfaceInPanel(String name) {
		Vertex v = new Vertex();
		v.setName(name);
		this.idUserItem--;
		v.setId(this.idUserItem);
		this.userGraph.addVertex(v);
		return v;
	}

	/**
	 * Create an attribute in the graphic pool of elements, not associated to a word
	 * 
	 * @param name
	 *           name of the attribute to create
	 * @param type
	 *			 type of the attribute to create, can only be a basic java type (TypeBase)
	 * @param visibility
	 *			 visibility of the attribute to create
	 * @return the created attribute
	 */
	public Attribute askCreateAttributeInPanel(String name, String type, String visibility) {
		Attribute att = new Attribute(name, TypeBase.getByName(type), Visibility.getByName(visibility));
		this.idUserItem--;
		att.setId(this.idUserItem);
		this.userGraph.addAttribute(att);
		return att;
	}

	/**
	 * Create a method in the graphic pool of elements, not associated to a word
	 * 
	 * @param name
	 *           name of the method to create
	 * @param paramTypes
	 *			 list of the parameters' types of the method to create
	 * @param returnType
	 *			 return type of the method to create, can only be a basic java type (TypeBase)
	 * @param visibility
	 *			 visibility of the method to create
	 * @return the created method
	 */
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