package model;

import java.util.ArrayList;

public class Word {

	protected String word;
	protected boolean isKeyWord;
	protected boolean isSelected;
	protected int id;
	protected UMLNature umlNature;
	protected UMLNature userUmlNature;

	public Word() {
		this.isSelected = "false";
	}

	public Word (String word, boolean isKeyWord, int id) {
		this.word = word;
		this.isKeyWord = isKeyWord;
		this.isSelected = false;
		if (this.isKeyWord) {
			this.id = id;
		}
	}

	public Word (String word) {
		this.word = word;
		this.isKeyWord = false;
		this.isSelected = false;
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

	public void setUmlNature(String umlNature) {
		//TODO TODO TODO TODO TODO -> aller implémenter une fonction dans UMLNature pour récupérer une constante 
		// a part d'une string
	}

	public UMLNature getUmlNature() {
		return this.umlNature;
	}
	public void setUserUmlNature(UMLNature userUmlNature) {
		this.userUmlNature = userUmlNature;
	}

	public void setUserUmlNature(String userUmlNature) {
		//TODO TODO TODO TODO TODO -> aller implémenter une fonction dans UMLNature pour récupérer une constante 
		// a part d'une string
	}

	public UMLNature getUserUmlNature() {
		return this.userUmlNature;
	}

	public void select() {
		this.isSelected = true;
	}

	public void unselect() {
		this.isSelected = false;
	}

}