package Model;

import java.util.ArrayList;

public class Word {

	private String word;
	private boolean isKeyWord;
	private boolean isSelected;
	private int id;
	private UMLNature umlNature;
	private UMLNature userUmlNature;

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


	public void select() {
		this.isSelected = true;
	}

	public void unselect() {
		this.isSelected = false;
	}

}