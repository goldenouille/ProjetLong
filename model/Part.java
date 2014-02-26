package model;

import java.util.ArrayList;
import parser.Text;

public class Part {

	private String name; 
	private ArrayList<Word> text;
	private Graph graph;
	private ArrayList<Step> steps;

	public Part() {
		this.text = new ArrayList<Word>();
		this.steps = new ArrayList<Step>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addText(Text tt) {
		String t = tt.getBody();
		String[] words = t.split(" ");
		int l = words.length;
		for (int i = 0; i<l; i++) {
			text.add(new Word(words[i]));
		}
	}

	public void addKeyWord(KeyWord keyWord) {
		text.add(keyWord);
	}

	public ArrayList<Word> getText() {
		return this.text;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Graph getGraph() {
		return this.graph;
	}

	public void addStep(Step s) {
		this.steps.add(s);
	}

	public void addStep(String stepName) {
		// avec stepFactory?
	}

	public ArrayList<Step> getSteps() {
		return this.steps;
	}

	public Step getAStep(int i) {
		return this.steps.get(i);
	}

}