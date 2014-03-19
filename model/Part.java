package model;

import parser.ParserException;
import parser.Text;
import parser.PseudoGraph;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

import java.util.ArrayList;

public class Part {

	private String name;	// name of the part
	private ArrayList<Word> text;	// initial list of the words of the part
	private Graph graph;		// expected graph
	private ArrayList<Step> steps;	// list of the steps composing the part
	private Iterator<Step> stepsIterator;	// step iterator
	private HashMap<Integer, GraphItem> idTable;	// hashMap matching an id with the associated item

	private Score score;
	
	private int auxNbStep;	// auxiliary field used in computing
	private int nbStep;	// number of steps

	private int auxNbWord; // auxiliary field used in computing

	/*
	 * Create a basic part
	 */
	public Part() {
		this.text = new ArrayList<Word>();
		this.steps = new ArrayList<Step>();
		this.idTable = new HashMap<Integer, GraphItem>();
		this.nbStep = 0;
		this.auxNbStep = -1;
		this.auxNbWord = 0;
		this.graph = new Graph();
		this.score = new Score();
	}

	public int getNbStep() {
		return this.nbStep;
	}

	/**
	 * Is used to give the next step to the Classic GUI controller
	 *
	 * @return the next step object
	 */
	public Object nextStep() {
		if (stepsIterator == null)
			stepsIterator = steps.iterator();
		if (stepsIterator.hasNext()) {
			return stepsIterator.next();
		} else {
			return null;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Add words (not keyWord) of a string to the initial text of the part
	 *
	 * @param t
	 *			string which is splitted according to spaces added as non-keyword words
	 */
	public void addText(String t) {
		String[] words = t.split(" ");
		int l = words.length;
		for (int i = 0; i < l; i++) {
			text.add(new Word(words[i]));
		}
	}

	/**
	 * Add words (not keyWord) of the input text to the initial text of the part
	 *
	 * @param tt
	 *			Text to add as non-keyword words to the initial text of the part
	 */
	public void addText(Text tt) {
		String t = tt.getBody();
		String[] words = t.split(" ");
		int l = words.length;
		for (int i = 0; i < l; i++) {
			Word w = new Word(words[i]);
			w.setLength(1);
			w.setFirstWord(this.auxNbWord);
			this.auxNbWord++;
			w.setLastWord(this.auxNbWord - 1);
			text.add(w);
		}
	}

	/**
	 * Add a keyWord to the initial text of the part
	 *
	 * @param keyWord
	 *			keyWord to add to the initial text of the part
	 */
	public void addKeyWord(KeyWord keyWord) {
		String w = keyWord.getWord();
		this.score.setScoreText(this.score.getScoreText() + keyWord.getScore());
		int l = (w.split(" ")).length;
		keyWord.setLength(l);
		keyWord.setFirstWord(this.auxNbWord);
		this.auxNbWord = this.auxNbWord + l;
		keyWord.setLastWord(this.auxNbWord - 1);
		text.add(keyWord);
		idTable.put(keyWord.getId(),new Vertex());
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

	/**
	 * Add a step to the step list of the part
	 *
	 * @param s
	 *			step to add
	 */
	public void addStep(Step s) {
		this.steps.add(s);
		this.nbStep = nbStep + 1;
	}

	/**
	 * Create a step from its name and add it to the step list of the part
	 *
	 * @param stepName
	 *			name of the step to create and add
	 */
	public void addStep(String stepName) {
		this.steps.add(StepFactory.createStep(stepName));
		this.nbStep = nbStep+1;
	}

	public ArrayList<Step> getSteps() {
		return this.steps;
	}

	public Step getAStep(int i) {
		return this.steps.get(i);
	}

	public void setIdTable(HashMap<Integer, GraphItem> it) {
		this.idTable = it;
	}

	public HashMap<Integer, GraphItem> getIdTable() {
		return this.idTable;
	}

	/**
	 * Create the expected graph from the pseudoGraph build by the parser
	 *
	 * @param PG
	 *			pseudoGraph created by the parser
	 */
	public void initGraph(PseudoGraph PG) throws ParserException {
		this.graph = PG.buildGraph(this.idTable);
	}

	public String toString() {
		return name;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score= score;
	}
	
	public void setScoreNature(int s) {
		this.score.setScoreNature(s);
	}
	
	public void setScoreGraph(int s) {
		this.score.setScoreGraph(s);
	}

}
