package model;

import parser.ParserException;
import parser.Text;
import parser.PseudoGraph;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

import java.util.ArrayList;

public class Part {

	private String name;
	private ArrayList<Word> text;
	private Graph graph;
	private ArrayList<Step> steps;
	private Iterator<Step> stepsIterator;
	private HashMap<Integer, GraphItem> idTable;

	private int scoreText;
	private int scoreNature;
	private int scoreGraphe;
	
	private int auxNbStep;
	private int nbStep;

	private int auxNbWord;

	public Part() {
		this.text = new ArrayList<Word>();
		this.steps = new ArrayList<Step>();
		this.idTable = new HashMap<Integer, GraphItem>();
		this.nbStep = 0;
		this.auxNbStep = -1;
		this.auxNbWord = 0;
		this.graph = new Graph();
		this.scoreText = 0;
		this.scoreNature = 0;
		this.scoreGraphe = 0;
	}

	public int getNbStep() {
		return this.nbStep;
	}

	public Object nextStep() {
		//voila un iterateur
		if (stepsIterator == null)
			stepsIterator = steps.iterator();
		if (stepsIterator.hasNext()) {
			return stepsIterator.next();
		} else {
			return null;
		}
		// if (auxNbStep +1 < nbStep) {
		// auxNbStep = auxNbStep + 1;
		// return this.steps.get(auxNbStep);
		// } else {
		// return null;
		// }
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// pour test
	public void addText(String t) {
		String[] words = t.split(" ");
		int l = words.length;
		for (int i = 0; i < l; i++) {
			text.add(new Word(words[i]));
		}
	}

	public void addText(Text tt) {
		String t = tt.getBody();
		String[] words = t.split(" ");
		int l = words.length;
		for (int i = 0; i < l; i++) {
			Word w = new Word(words[i]);
			w.setLength(1);
			w.setFirstWord(this.auxNbWord);
			this.auxNbWord++;
			//System.out.println("on ajoute 1 au nombre de mots parce qu'on est en train de rajouter du texte");
			w.setLastWord(this.auxNbWord - 1);
			text.add(w);
		}
	}

	public void addKeyWord(KeyWord keyWord) {
		String w = keyWord.getWord();
		this.scoreText += keyWord.getScore();
		int l = (w.split(" ")).length;
		keyWord.setLength(l);
		keyWord.setFirstWord(this.auxNbWord);
		this.auxNbWord = this.auxNbWord + l;
		//System.out.println("on ajoute " + l +" au nombre de mots parce qu'on ajoute un mot-clef");
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

	public void addStep(Step s) {
		this.steps.add(s);
		this.nbStep = nbStep + 1;
	}

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

	// Pour creer un graphe complet à partir d'un Pseudo graph contenant les infos brutes de l'xml
	public void initGraph(PseudoGraph PG) throws ParserException {
		this.graph = PG.buildGraph(this.idTable);
	}

	public String toString() {
		return name;
	}

	public int getScoreText() {
		return scoreText;
	}

	public void setScoreText(int scoreText) {
		this.scoreText = scoreText;
	}

	public int getScoreNature() {
		return scoreNature;
	}

	public void setScoreNature(int scoreNature) {
		this.scoreNature = scoreNature;
	}

	public int getScoreGraphe() {
		return scoreGraphe;
	}

	public void setScoreGraphe(int scoreGraphe) {
		this.scoreGraphe = scoreGraphe;
	}
	
	public int getScoreMax() {
		return this.getScoreNature() + this.getScoreGraphe() + this.getScoreText();
	}

}
