package model;

public class Score {

	private int scoreText;
	private int scoreNature;
	private int scoreGraphe;
	private int currScoreText;
	private int currScoreNature;
	private int currScoreGraphe;
	
	public Score() {
		
	}
	
	public int getScoreText() {
		return scoreText;
	}
	public void setScoreText(int scoreText) {
		this.scoreText = scoreText;
		this.currScoreText = scoreText;
	}
	public int getScoreNature() {
		return scoreNature;
	}
	public void setScoreNature(int scoreNature) {
		this.scoreNature = scoreNature;
		this.currScoreNature = scoreNature;
	}
	public int getScoreGraphe() {
		return scoreGraphe;
	}
	public void setScoreGraphe(int scoreGraphe) {
		this.scoreGraphe = scoreGraphe;
		this.currScoreGraphe = scoreGraphe;
	}
	public int getCurrScoreText() {
		return currScoreText;
	}
	public void setCurrScoreText(int currScoreText) {
		this.currScoreText = (currScoreText>0?currScoreText:0);
	}
	public int getCurrScoreNature() {
		return currScoreNature;
	}
	public void setCurrScoreNature(int currScoreNature) {
		this.currScoreNature = (currScoreNature>0?currScoreNature:0);
	}
	public int getCurrScoreGraphe() {
		return currScoreGraphe;
	}
	public void setCurrScoreGraphe(int currScoreGraphe) {
		this.currScoreGraphe = (currScoreGraphe>0?currScoreGraphe:0);
	}
	
	public int getScoreMax() {
		return this.scoreGraphe + this.scoreNature + this.scoreText;
	}
	
	public int getCurrScore() {
		return this.currScoreGraphe + this.currScoreNature + this.currScoreText;
	}
	
	public void removeScoreText(int n) {
		this.setCurrScoreText(this.getCurrScoreText() -n );
	}
}
