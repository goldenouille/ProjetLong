package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import parser.Parser;
import controller.ModelController;
import model.UMLNature;
import model.Step;
import model.StepFactory;
import model.Exercise;

import gui.ClassicGuiController;

public class ModelController {
	private ClassicGuiController cgc;
	private Parser p;
	private StepFactory sf;
	private Exercise exo;

	public void setCGC(ClassicGuiController cgc) {
		this.cgc = cgc;
		this.sf = new StepFactory();
	}

	public void setParser(Parser p) {
		this.p = p;
	}

	public void askSelectText(int[] pourcentages) {
		exo.selectText(pourcentages);
	}

	public void askUnSelectText(int[] pourcentages) {
		exo.unselectText(pourcentages);
	}

	public Object[] askUmlInstancesNatures() {
		Object[] natures = { UMLNature.CLASS, UMLNature.ABSTRACT_CLASS, UMLNature.INTERFACE, UMLNature.ATTRIBUTE, UMLNature.METHOD };
		return natures;
	}

	public void askValidateStep(Object s) {
		System.out.println("Correction de " + ((Step) s).getFrenchName());
		((Step) s).getCorrection(exo);
	}

	public void askAddText(String text, String comment) {
		System.out.println("askAddText " + text + "\n" + comment);
		exo.addText(text);
	}

	public String askUmlInstanceName(int firstWord, int lastWord, boolean userText, Object nature) {
		// TODO Auto-generated method stub
		return null;
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateClass(int firstWord, int lastWord, boolean userText, String name) {
		exo.addClass(firstWord,lastWord,userText,name);
		System.out.println("askCreateClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateAbstractClass(int firstWord, int lastWord, boolean userText, String name) {
		exo.addAbstractClass(firstWord, lastWord, userText, name);
		System.out.println("askCreateAbstractClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateInterface(int firstWord, int lastWord, boolean userText, String name) {
		exo.addInterface(firstWord, lastWord, userText, name);
		System.out.println("askCreateInterface " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateAttribute(int firstWord, int lastWord, boolean userText, String name, String type, String visibility) {
		exo.addAttribute(firstWord, lastWord, userText, name, type, visibility);
		System.out.println("askCreateAttribute " + name + " " + type + " " + visibility);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	// pour l'instant ne peut prendre que des types de base comme type de retour et comme parametres
	public void askCreateMethod(int firstWord, int lastWord, boolean userText, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		exo.addMethod(firstWord, lastWord, userText, name,  paramTypes, returnType, visibility);
		System.out.println("askCreateMethod " + name + " " + paramTypes.toString() + " " + returnType + " " + visibility);
	}

	public void doPrintMessage(String title, String message) {
		cgc.doPrintMessage(title, message);
	}

	public void doSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doSelectText(firstWord, lastWord, userText);
	}

	public void doUnSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnSelectText(firstWord, lastWord, userText);
	}

	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doValidateText(firstWord,lastWord,userText);
	}

	public void doUnValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnValidateText(firstWord, lastWord, userText);
	}

	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doInvalidateText(firstWord, lastWord, userText);
	}

	public void doUnInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnInvalidateText(firstWord, lastWord, userText);
	}

	public void doShowUmlInstanceCreationPopup(int firstWord, int lastWord, Object nature) 
																			throws BadLocationException {
		cgc.doShowUmlInstanceCreationPopup(firstWord, lastWord, nature);
	}

	public void doShowUmlInstanceEditionPopup(int firstWord, int lastWord, boolean userText, Object nature) 
																				throws BadLocationException {
		// TODO Auto-generated method stub
	}

	public void doShowTextAdditionPopup() {
		cgc.doShowTextAdditionPopup();
	}

	public void doAddText(boolean userText, String[] text) {
		cgc.doAddText(userText, text);
	}

	public void doSetTextFont(Font font) {
		cgc.doSetTextFont(font);
	}

	public void doShowMissingKeywordNumber(int nb) {
		cgc.doShowMissingKeywordNumber(nb);
	}


	// ******************************************************************************************************//
	// *****************************************Navigation_methods*******************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// fonction appelee pas encore implementee
	public void askSelectPart(Object part) {
		exo.selectPart(part);
		System.out.println("askSelectPart " + part.toString());
	}

	// fonction appelee pas encore implementee
	public void askCorrectStep(Object step) {
		exo.selectStep(step);
		System.out.println("askCorrectStep " + step.toString());
	}

	// fonction appele pas encore implementee
	public Object askNextPart() {
		return exo.nextPart();
	}

	public Object askNextStep() {
		return exo.nextStep();
	}


	// ****************************************************************************************************//
	// *********************************************Timer_methods******************************************//
	// ****************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// Timer pas encore gere, a implementer dans ce fichier
	public String askTimer() {
		// TODO Auto-generated method stub
		return "0";
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Font askTimerFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Color askTimerBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	public void doSetTimer(String timer) {
		cgc.doSetTimer(timer);
	}

	public void doSetTimerFont(Font font) {
		cgc.doSetTimerFont(font);
	}

	public void doSetTimerBgColor(Color color) {
		cgc.doSetTimerBgColor(color);
	}


	// ******************************************************************************************************//
	// *******************************************Score_methods**********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// fonction avec valeur par defaut pour l'instant
	public String askScore() {
		return exo.askScore();
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Font askScoreFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	// par l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Color askScoreBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	public void doSetScore(String score) {
		cgc.doSetScore(score);
	}

	public void doSetScoreFont(Font font) {
		cgc.doSetScoreFont(font);
	}

	public void doSetScoreBgColor(Color color) {
		cgc.doSetScoreBgColor(color);
	}

}


