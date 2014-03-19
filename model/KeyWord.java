package model;

public class KeyWord extends Word {
	
	private int score;	// score associated to the keyWord

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/*
	 * Create a basic keyWord
	 */
	public KeyWord() {
		super();
		this.isKeyWord = true;
		this.score = 0;
	}
}