package model;

public class KeyWord extends Word {
	
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public KeyWord() {
		super();
		this.isKeyWord = true;
	}
}