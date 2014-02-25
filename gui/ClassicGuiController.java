package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

public class ClassicGuiController implements GuiController {

	// link to core
	private Object core;

	// Gui elements
	private MainFrame mainFrame;
	private TextPanel textPanel;
	private TextPanel userTextPanel;
	private ScorePanel scorePanel;
	private TimerPanel timerPanel;
	private UmlPanel umlPanel;
	private NavigationPanel navigationPanel;

	// Gui Variables
	private boolean userTextFocus;

	// Gui Constants
	public static final Color SELECTION_COLOR = new Color(212, 212, 255);
	public static final Color VALIDATION_COLOR = new Color(212, 255, 212);
	public static final Color INVALIDATION_COLOR = new Color(255, 212, 212);

	//test
	private Iterator itP;
	private Iterator itS1;
	private Iterator itS2;
	//test
	
	
	// *** General methods ***//

	/**
	 * Constructeur primaire : crée et affiche les elements du gui.
	 */
	public ClassicGuiController(Object core) {
		
		//test
		ArrayList<String> parts = new ArrayList<String>();
		parts.add("Partie 1");parts.add("Partie 2");
		ArrayList<String> steps1 = new ArrayList<String>();
		steps1.add("1.1");steps1.add("1.2");steps1.add("1.3");
		ArrayList<String> steps2 = new ArrayList<String>();
		steps2.add("2.1");steps2.add("2.2");steps2.add("2.3");
		itP = parts.iterator();
		itS1 = steps1.iterator();
		itS2 = steps2.iterator();
		//test
		
		this.core = core;
		this.userTextFocus = false;

		this.textPanel = new TextPanel(this, false);
		this.userTextPanel = new TextPanel(this, true);
		TextSectionPanel textSectionPanel = new TextSectionPanel(this, textPanel, userTextPanel);
		this.scorePanel = new ScorePanel(this);
		this.timerPanel = new TimerPanel(this);
		this.umlPanel = new UmlPanel(this);
		this.navigationPanel = new NavigationPanel(this);

		this.mainFrame = new MainFrame(this, navigationPanel, scorePanel, timerPanel, textSectionPanel, umlPanel);
		this.mainFrame.setVisible(true);
		
		//test
		doAddText(false, "Un compte simple est caracterise par un solde exprime en euro.".split(" "));
		//doAddText(true, "Un texte quelconque rajoute par l'utilisateur.".split(" "));
		//test
		
	}

	/**
	 * Affiche un message passe en parametre
	 * 
	 * @param message
	 *            le message a afficher
	 */
	public void doPrintMessage(String title, String message) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.WARNING_MESSAGE);
	}

	// *** Text methods ***//
	// Ask //
	public void askSelectText() {
		// TODO Auto-generated method stub
		int[] tab = getActiveTextPanel().getSelection();
		System.out.println("askSelectText : " + Arrays.toString(tab));

		int start = -1, end = -1;
		for (int i = 0; i < tab.length - 1; i++) {
			if (start == -1 && tab[i] != 0)
				start = i;
			if (end == -1 && start != -1 && tab[i + 1] == 0)
				end = i;
		}
		try {
			doSelectText(start, end, userTextFocus);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void askUnSelectText() {
		// TODO Auto-generated method stub
		int[] tab = getActiveTextPanel().getSelection();
		System.out.println("askUnSelectText : " + Arrays.toString(tab));

		int start = -1, end = -1;
		for (int i = 0; i < tab.length - 1; i++) {
			if (start == -1 && tab[i] != 0)
				start = i;
			if (end == -1 && start != -1 && tab[i + 1] == 0)
				end = i;
		}
		try {
			doUnSelectText(start, end, userTextFocus);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object[] askUmlInstancesNatures() {
		// TODO Auto-generated method stub
		Object[] natures = {UMLNature.CLASS,UMLNature.ABSTRACT_CLASS,UMLNature.INTERFACE,UMLNature.ATTRIBUTE};
		return natures;
	}

	public void askValidateKeywords() {
		// TODO Auto-generated method stub
		System.out.println("askValidateKeywords");
		
		int[] tab = getActiveTextPanel().getSelection();
		int start = -1, end = -1;
		for (int i = 0; i < tab.length - 1; i++) {
			if (start == -1 && tab[i] != 0)
				start = i;
			if (end == -1 && start != -1 && tab[i + 1] == 0)
				end = i;
		}
		try {
			doValidateText(start, end, userTextFocus);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void askValidateAssociation() {
		// TODO Auto-generated method stub
		System.out.println("askValidateAssociation");
	}

	private String askUmlInstanceName(int firstWord, int lastWord, boolean userText, Object nature) {
		// TODO Auto-generated method stub
		return null;
	}

	public void askAddText(String text, String comment) {
		// TODO Auto-generated method stub
		System.out.println("askAddText " + text + "\n"+ comment);
		
		doAddText(true, text.split(" "));
	}

	// Do //
	public void doSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(SELECTION_COLOR, firstWord, lastWord);
	}

	public void doUnSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(SELECTION_COLOR, firstWord, lastWord);
	}

	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	public void doUnValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	public void doUnInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	public void doShowUmlInstanceCreationPopup(int firstWord, int lastWord, Object nature)
			throws BadLocationException {
		NaturePopup popup;
		if (nature.equals(UMLNature.CLASS)) {
			popup = new ClassPopup((userTextFocus ? userTextPanel : textPanel).getTextPane(), this, firstWord, lastWord,
					null);
		}

		// TODO Auto-generated method stub
	}
	
	public void doShowUmlInstanceEditionPopup(int firstWord, int lastWord, boolean userText, Object nature)
			throws BadLocationException {
		NaturePopup popup;
		if (nature.equals(UMLNature.CLASS)) {
			String name = this.askUmlInstanceName(firstWord, lastWord, userText, nature);
			popup = new ClassPopup((userText ? userTextPanel : textPanel).getTextPane(), this, firstWord, lastWord,
					name);
		}

		// TODO Auto-generated method stub
	}
	
	public void doShowTextAdditionPopup() {
		// TODO Auto-generated method stub
		TextAdditionPopup popup = new TextAdditionPopup(this);
	}
	
	public void doAddText(boolean userText, String[] text) {
		(userText ? userTextPanel : textPanel).apendText(text);
	}

	// Internal //

	private TextPanel getActiveTextPanel() {
		if (userTextFocus)
			return userTextPanel;
		else
			return textPanel;
	}

	public void setUserTextFocus(boolean userTextFocus) {
		this.userTextFocus = userTextFocus;
	}

	public int[] getKeyword(Point point) {
		try {
			return getActiveTextPanel().getKeywordPosition(point);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getText(int firstWord, int lastWord) throws BadLocationException {
		return getActiveTextPanel().getText(firstWord, lastWord);
	}

	// *** Navigation methods ***//
	// Ask //

	/**
	 * Transmet au code metier un objet identifiant la partie que l'utilisateur
	 * a selectionne
	 * 
	 * @param part
	 *            objet permettant au code metier d'identifier la partie a
	 *            laquelle il correspond
	 */
	public void askSelectPart(Object part) {
		// TODO Auto-generated method stub
		System.out.println("askSelectPart " + part.toString());
	}

	/**
	 * Transmet au code metier un objet identifiant l'etape dont l'utilisateur a
	 * demande la correction
	 * 
	 * @param step
	 *            objet permettant au code metier d'identifier l'etape a
	 *            laquelle il correspond
	 */
	public void askCorrectStep(Object step) {
		// TODO Auto-generated method stub
		System.out.println("askCorrectStep " + step.toString());
	}

	

	
	/**
	 * Demande au code metier un objet identifiant la partie suivante de
	 * l'exercice. Le premier appel doit donc renvoyer la premiere partie et
	 * ainsi de suite.
	 * 
	 * @return Un objet permettant au code metier d'identifier la partie a
	 *         laquelle il correspond
	 */
	public Object askNextPart() {
		// TODO Auto-generated method stub
	
		try {
		 	return itP.next();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Demande au code metier un objet identifiant l'etape suivante de la partie
	 * courante de l'exercice. Le premier appel doit donc renvoyer la premiere
	 * etape et ainsi de suite.
	 * 
	 * @return Un objet permettant au code metier d'identifier l'etape a
	 *         laquelle il correspond
	 */
	public Object askNextStep() {
		// TODO Auto-generated method stub
		try {
			if(itP.hasNext())
		 	return itS1.next();
			else return itS2.next();
		} catch (Exception e) {
			return null;
		}
	}

	// *** Timer methods ***//
	// Ask //

	/**
	 * Demande au code metier le chronometre a afficher
	 * 
	 * @return le score souhaite
	 */
	public String askTimer() {
		// TODO Auto-generated method stub
		return "0";
	}

	/**
	 * Demande au code metier la police a utiliser pour afficher le chronometre
	 * 
	 * @return la police souhaitee
	 */
	public Font askTimerFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	/**
	 * Demande au code metier la couleur a utiliser pour l'arriere plan du
	 * chronometre
	 * 
	 * @return la couleur souhaitee
	 */
	public Color askTimerBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// Do //

	/**
	 * Change le chronometre affiche
	 * 
	 * @param timer
	 *            le nouveau chronometre a afficher
	 */
	public void doSetTimer(String timer) {
		timerPanel.setTimer(timer);
	}

	/**
	 * Change la police du chronometre
	 * 
	 * @param font
	 *            la nouvelle police souhaitee
	 */
	public void doSetTimerFont(Font font) {
		timerPanel.setTimerFont(font);
	}

	/**
	 * Change la couleur d'arriere plan du chronometre
	 * 
	 * @param color
	 *            la nouvelle couleur souhaitee
	 */
	public void doSetTimerBgColor(Color color) {
		timerPanel.setTimerBgColor(color);
	}

	// *** Score methods ***//
	// Ask //

	/**
	 * Demande au code metier le score a afficher
	 * 
	 * @return le score souhaite
	 */
	public String askScore() {
		// TODO Auto-generated method stub
		return "0 / 100";
	}

	/**
	 * Demande au code metier la police a utiliser pour afficher le score
	 * 
	 * @return la police souhaitee
	 */
	public Font askScoreFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	/**
	 * Demande au code metier la couleur a utiliser pour l'arriere plan du score
	 * 
	 * @return la couleur souhaitee
	 */
	public Color askScoreBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// Do //

	/**
	 * Change le score affiche
	 * 
	 * @param score
	 *            le nouveau score a afficher
	 */
	public void doSetScore(String score) {
		scorePanel.setScore(score);
	}

	/**
	 * Change la police du score
	 * 
	 * @param font
	 *            la nouvelle police souhaitee
	 */
	public void doSetScoreFont(Font font) {
		scorePanel.setScoreFont(font);
	}

	/**
	 * Change la couleur d'arriere plan du score
	 * 
	 * @param color
	 *            la nouvelle couleur souhaitee
	 */
	public void doSetScoreBgColor(Color color) {
		scorePanel.setScoreBgColor(color);
	}



}
