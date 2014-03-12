package model;

public class Score {

	private int scoreText;
	private int scoreNature;
	private int scoreGraph;
	private int currScoreText;
	private int currScoreNature;
	private int currScoreGraph;
	
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
	public int getScoreGraph() {
		return scoreGraph;
	}
	public void setScoreGraph(int scoreGraph) {
		this.scoreGraph = scoreGraph;
		this.currScoreGraph = scoreGraph;
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
	public int getCurrScoreGraph() {
		return currScoreGraph;
	}
	public void setCurrScoreGraph(int currScoreGraph) {
		this.currScoreGraph = (currScoreGraph>0?currScoreGraph:0);
	}
	
	public int getScoreMax() {
		return this.scoreGraph + this.scoreNature + this.scoreText;
	}
	
	public int getCurrScore() {
		return this.currScoreGraph + this.currScoreNature + this.currScoreText;
	}
	
	public void removeScoreText(int n) {
		this.setCurrScoreText(this.getCurrScoreText() -n );
		System.out.println("- " + n + " points.");
	}
	
	public void removeScoreNature(int n) {
		this.setCurrScoreNature(this.getCurrScoreNature() -n );
	}
	
	public void removeScoreGraph(int n) {
		System.out.println("- " + n + " points.");
		this.setCurrScoreGraph(this.getCurrScoreGraph() -n );
	}
}
