package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import controller.ModelController;

import model.UMLNature;

public class ClassicGuiController implements GuiController {

	// link to core
	private ModelController core;

	// Gui elements
	private MainFrame mainFrame;
	private TextSectionPanel textSectionPanel;
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

	// test
	// private Iterator itP;
	// private Iterator itS1;
	// private Iterator itS2;
	// test

	// *** General methods ***//

	/**
	 * Main constructor, create and show the gui.
	 * 
	 * @param core
	 */
	public ClassicGuiController(ModelController core) {

		// test
		// ArrayList<String> parts = new ArrayList<String>();
		// parts.add("Partie 1");parts.add("Partie 2");
		// ArrayList<String> steps1 = new ArrayList<String>();
		// steps1.add("1.1");steps1.add("1.2");steps1.add("1.3");
		// ArrayList<String> steps2 = new ArrayList<String>();
		// steps2.add("2.1");steps2.add("2.2");steps2.add("2.3");
		// itP = parts.iterator();
		// itS1 = steps1.iterator();
		// itS2 = steps2.iterator();
		// test

		this.core = core;
		this.userTextFocus = false;

		this.textPanel = new TextPanel(this, false);
		this.userTextPanel = new TextPanel(this, true);
		this.textSectionPanel = new TextSectionPanel(this, textPanel, userTextPanel);
		this.scorePanel = new ScorePanel(this);
		this.timerPanel = new TimerPanel(this);
		this.umlPanel = new UmlPanel(this);
		this.navigationPanel = new NavigationPanel(this);

		this.mainFrame = new MainFrame(this, navigationPanel, scorePanel, timerPanel, textSectionPanel, umlPanel);
		this.mainFrame.setVisible(true);

		// test
		doAddText(
				false,
				"Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro.Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. Un compte simple est caracterise par un solde exprime en euro. "
						.split(" "));
		doAddText(true, "Un texte quelconque rajoute par l'utilisateur.".split(" "));
		// test

	}

	/**
	 * Print a message
	 * 
	 * @param message
	 *            message to print
	 */
	public void doPrintMessage(String title, String message) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.WARNING_MESSAGE);
	}

	// ******************************************************************************************************//
	// **********************************************Text_methods********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Sends to the core the text the user wants to select as an int table where
	 * tab[i] = selection %age of the word number i
	 */
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
			e.printStackTrace();
		}
	}

	/**
	 * Sends to the core the text the user wants to unselect as an int table
	 * where tab[i] = selection %age of the word number i
	 */
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
			e.printStackTrace();
		}
	}

	/**
	 * Ask for a table of the instanciable UMLnatures. Warning : if one of thos
	 * elements is not recognized by "doShowUmlInstanceCreationPopup" exceptions
	 * may occur
	 * 
	 * @return UMLnatures table
	 */
	public Object[] askUmlInstancesNatures() {
		// TODO Auto-generated method stub
		Object[] natures = { UMLNature.CLASS, UMLNature.ABSTRACT_CLASS, UMLNature.INTERFACE, UMLNature.ATTRIBUTE, UMLNature.METHOD };
		return natures;
	}

	/**
	 * Sends to the core the user's request to validate his keyword selection.
	 */
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
			e.printStackTrace();
		}
	}

	/**
	 * Sends to the core the user's request to validate his keyword Uml
	 * association.
	 */
	public void askValidateAssociation() {
		// TODO Auto-generated method stub
		System.out.println("askValidateAssociation");
	}

	/**
	 * Ask the core about the name of the Uml instance corresponding to the
	 * defined keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param nature
	 *            Uml Nature of the instance
	 * @return name of the instance
	 */
	private String askUmlInstanceName(int firstWord, int lastWord, boolean userText, Object nature) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sends to the core the user's request to add text
	 * 
	 * @param text
	 *            the text to add
	 * @param comment
	 *            user's comment
	 */
	public void askAddText(String text, String comment) {
		// TODO Auto-generated method stub
		System.out.println("askAddText " + text + "\n" + comment);

		doAddText(true, text.split(" "));
	}

	/**
	 * Sends to the core the user's request to create a new class instance for
	 * the specified keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askCreateClass(int firstWord, int lastWord, boolean userText, String name) {
		// TODO Auto-generated method stub
		System.out.println("askCreateClass " + name);
	}

	/**
	 * Sends to the core the user's request to create a new abstract class
	 * instance for the specified keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askCreateAbstractClass(int firstWord, int lastWord, boolean userText, String name) {
		// TODO Auto-generated method stub
		System.out.println("askCreateAbstractClass " + name);
	}

	/**
	 * Sends to the core the user's request to create a new interface instance
	 * for the specified keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askCreateInterface(int firstWord, int lastWord, boolean userText, String name) {
		// TODO Auto-generated method stub
		System.out.println("askCreateInterface " + name);
	}

	/**
	 * Sends to the core the user's request to create a new attribute instance
	 * for the specified keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param name
	 *            name wanted for the new instance
	 * @param type
	 *            type wanted for the new instance
	 * @param visibility
	 *            visibility wanted for the new instance
	 */
	private void askCreateAttribute(int firstWord, int lastWord, boolean userText, String name, String type, String visibility) {
		// TODO Auto-generated method stub
		System.out.println("askCreateAttribute " + name + " " + type + " " + visibility);
	}

	/**
	 * Sends to the core the user's request to create a new method instance for
	 * the specified keyword
	 * 
	 * @param firstWord
	 *            index of the expression's first word
	 * @param lastWord
	 *            index of the expression's last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param name
	 *            name wanted for the new instance
	 * @param paramTypes
	 *            list of the parameters types wanted for the new instance
	 * @param returnType
	 *            returnType wanted for the new instance
	 * @param visibility
	 *            visibility wanted for the new instance
	 */
	private void askCreateMethod(int firstWord, int lastWord, boolean userText, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		// TODO Auto-generated method stub
		System.out.println("askCreateMethod " + name + " " + paramTypes.toString() + " " + returnType + " " + visibility);
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Highlight the given expression with "SELECTION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(SELECTION_COLOR, firstWord, lastWord);
	}

	/**
	 * Unhighlight the given expression of "SELECTION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doUnSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(SELECTION_COLOR, firstWord, lastWord);
	}

	/**
	 * Highlight the given expression with "VALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Unhighlight the given expression of "VALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doUnValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(VALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Highlight the given expression with "INVALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).highlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Unhighlight the given expression of "INVALIDATION_COLOR"
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doUnInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Efface tout surlignage de la portion de texte definie.
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doResetTextHighlight(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		(userText ? userTextPanel : textPanel).unHighlight(SELECTION_COLOR, firstWord, lastWord);
		(userText ? userTextPanel : textPanel).unHighlight(VALIDATION_COLOR, firstWord, lastWord);
		(userText ? userTextPanel : textPanel).unHighlight(INVALIDATION_COLOR, firstWord, lastWord);
	}

	/**
	 * Shows a pop-up allowing to specify properties of a new uml instance of
	 * the given nature, bound to the specified expression. Calls the associated
	 * instance creation method if user validates.
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param nature
	 *            UMLNature of the new instance
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doShowUmlInstanceCreationPopup(int firstWord, int lastWord, Object nature) throws BadLocationException {
		if (nature.equals(UMLNature.CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe abstraite", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAbstractClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.INTERFACE)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, firstWord, lastWord, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle interface", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateInterface(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			AttributeEditionPanel panel = new AttributeEditionPanel(this, firstWord, lastWord, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvel attribut", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAttribute(firstWord, lastWord, userTextFocus, panel.getAttributeName(), panel.getAttributeType(), panel.getAttributeVisibility());
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			MethodEditionPanel panel = new MethodEditionPanel(this, firstWord, lastWord, null, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
					"Nouvelle methode", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateMethod(firstWord, lastWord, userTextFocus, panel.getMethodName(), panel.getMethodParams(), panel.getMethodReturnType(),
						panel.getMethodVisibility());
			}
		}
	}

	/**
	 * Shows a pop-up allowing to specify properties of an existing uml instance
	 * of the given nature, bound to the specified expression. Calls the
	 * associated instance edition method if user validates.
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @param userText
	 *            true if expression belongs to the user's text
	 * @param nature
	 *            UMLNature of the instance
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public void doShowUmlInstanceEditionPopup(int firstWord, int lastWord, boolean userText, Object nature) throws BadLocationException {
		// TODO Auto-generated method stub
	}

	/**
	 * Shows a pop-up allowing to enter some new text and a comment. Calls
	 * askAddText method if user validates.
	 */
	public void doShowTextAdditionPopup() {
		TextAdditionPanel textPanel = new TextAdditionPanel(this);
		int result = JOptionPane.showConfirmDialog(null, textPanel, "Ajout de texte", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			askAddText(textPanel.getNewText(), textPanel.getComment());
		}
	}

	/**
	 * Adds text to the specified panel
	 * 
	 * @param userText
	 *            true to add to user's text panel
	 * @param text
	 *            the text to add
	 */
	public void doAddText(boolean userText, String[] text) {
		(userText ? userTextPanel : textPanel).apendText(text);
	}

	/**
	 * Sets the texts font
	 * 
	 * @param font
	 *            the new font
	 */
	public void doSetTextFont(Font font) {
		textPanel.setTextFont(font);
		userTextPanel.setTextFont(font);
	}

	/**
	 * Prints the missing keywords number. if nb<1, hide the missing keywords
	 * label
	 * 
	 * @param nb
	 *            number to show
	 */
	private void doShowMissingKeywordNumber(int nb) {
		textSectionPanel.setMissingKeywords(nb);
	}

	// *********//
	// Internal //
	// *********//

	/**
	 * Methode interne. Renvoie le dernier panel de texte ayant recu une
	 * activité utilisateur.
	 * 
	 * @return dernier panel de texte ayant recu une activité utilisateur
	 */
	private TextPanel getActiveTextPanel() {
		if (userTextFocus)
			return userTextPanel;
		else
			return textPanel;
	}

	/**
	 * Methode interne. Permet de definir le dernier panel de texte ayant recu
	 * une activité utilisateur.
	 * 
	 * @param userTextFocus
	 *            vrai si le dernier panel de texte ayant recu une activité
	 *            utilisateur est celui du texte de l'utilisateur
	 */
	public void setUserTextFocus(boolean userTextFocus) {
		this.userTextFocus = userTextFocus;
	}

	/**
	 * Methode interne. Permet de rechercher si une expression validée occupe
	 * les coordonnees donnees dans le panel de texte actif.
	 * 
	 * @param point
	 *            coordonnees ou chercher une expression validée
	 * @return indices des premiers et derniers mots de l'expression trouvée,
	 *         null sinon
	 */
	public int[] getKeyword(Point point) {
		try {
			return getActiveTextPanel().getKeywordPosition(point);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Methode interne. Recherche l'expression delimitee dans le panel de texte
	 * actif
	 * 
	 * @param firstWord
	 *            index of expression first word a trouver
	 * @param lastWord
	 *            index of expression last word a trouver
	 * @return l'expression delimitee
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public String getText(int firstWord, int lastWord) throws BadLocationException {
		return getActiveTextPanel().getText(firstWord, lastWord);
	}

	/**
	 * Trouve la police d'affichage des panels de texte
	 * 
	 * @return la police utilisee
	 */
	public Font getTextFont() {
		return textPanel.getFont();
	}

	// ******************************************************************************************************//
	// *****************************************Navigation_methods*******************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

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
		return null;
		// TODO Auto-generated method stub

		// try {
		// return itP.next();
		// } catch (Exception e) {
		// return null;
		// }
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
		return null;
		// TODO Auto-generated method stub
		// try {
		// if(itP.hasNext())
		// return itS1.next();
		// else return itS2.next();
		// } catch (Exception e) {
		// return null;
		// }
	}

	// ****************************************************************************************************//
	// *********************************************Timer_methods******************************************//
	// ****************************************************************************************************//

	// ****//
	// Ask //
	// ****//

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

	// ***//
	// Do //
	// ***//

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

	// ******************************************************************************************************//
	// *******************************************Score_methods**********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

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

	// ***//
	// Do //
	// ***//

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
