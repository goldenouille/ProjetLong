package model;

import java.util.ArrayList;

public class Word {


	protected String word;		// string remembered by the word
	protected boolean isKeyWord;	// if the word is a keyWord or not
	protected boolean isSelected;	// if the word is currently selected
	protected int id;	// the word's id, which will be the same as the one of the associated GraphItem
	
	protected UMLNature umlNature;	// expected UMLNature
	protected UMLNature userUmlNature;	// UMLNature given by the user

	protected GraphItem graphItem;	// expected GraphItem
	protected GraphItem userGraphItem;	// GraphItem given by the user


	protected int length;	// number of words included in the remembered string
	protected int firstWord;	// index of the beginning of the Word in the printed text in the GUI
	protected int lastWord;		// index of the end of the Word in the printed text in the GUI

	/**
	 * Basic constructor
	 */
	public Word() {
		this.isSelected = false;
	}


	/**
	 * Create a word with a string and its id
	 * 
	 * @param word
	 *            	the string to be remembered by the word
	 * @param isKeyWord
	 *				define if the word is a keyWord or not
	 * @param id
	 *				the word's id
	 */
	public Word (String word, boolean isKeyWord, int id) {
		this.word = word;
		this.isKeyWord = isKeyWord;
		this.isSelected = false;
		if (this.isKeyWord) {
			this.id = id;
		}
		this.userUmlNature = null;
	}

	/**
	 * Create a basic word wich is not a keyword and has no id
	 * 
	 * @param word
	 *            	the string to be remembered by the word
	 */	

	public Word (String word) {
		this.word = word;
		this.isKeyWord = false;
		this.isSelected = false;
		this.userUmlNature = null;
	}

	/**
	 * Create a word with its id but which is not a keyword
	 * 
	 * @param word
	 *            	the string to be remembered by the word
	 * @param id
	 *				the word's id
	 */
	public Word (String word, int id) {
		this.word = word;
		this.isKeyWord = false;
		this.isSelected = false;
		this.id = id;
		this.userUmlNature = null;
	}

	/**
	 * Ask to validate the userGraphItem
	 * 
	 */
	public void validate() {
		this.userGraphItem.validate();
	}

	public void setFirstWord(int i) {
		this.firstWord = i;
	}

	public int getFirstWord() {
		return this.firstWord;
	}

	public void setLastWord(int j) {
		this.lastWord = j;
	}

	public int getLastWord() {
		return this.lastWord;
	}

	public void setLength(int l) {
		this.length = l;
	}

	public int getLength() {
		return this.length;
	}


	public void setWord (String word) {
		this.word = word;
	}

	public String getWord() {
		return this.word;
	}

	public void setIsKeyWord(boolean iKW) {
		this.isKeyWord = iKW;
	}

	public boolean isKeyWord() {
		return this.isKeyWord;
	}

	public void setIsSelected(boolean iS) {
		this.isSelected = iS;
	}

	public boolean isSelected() {
		return this.isSelected;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setUmlNature(UMLNature umlNature) {
		this.umlNature = umlNature;
	}

	public UMLNature getUmlNature() {
		return this.umlNature;
	}
	public void setUserUmlNature(UMLNature userUmlNature) {
		this.userUmlNature = userUmlNature;
	}

	public UMLNature getUserUmlNature() {
		return this.userUmlNature;
	}

	public void setGraphItem(GraphItem gi) {
		this.graphItem = gi;
	}

	public GraphItem getGraphItem() {
		return this.graphItem;
	}

	public void setUserGraphItem(GraphItem gi) {
		this.userGraphItem = gi;
	}

	public GraphItem getUserGraphItem() {
		return this.userGraphItem;
	}	

	public void select() {
		this.isSelected = true;		
	}

	public void unselect() {
		this.isSelected = false;
	}
	
	public String toString() {
		return this.word;
	}

}