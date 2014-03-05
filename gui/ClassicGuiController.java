package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import uml.UMLDrawingPanel;
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
	private UMLDrawingPanel umlDrawingPanel;
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
		// parts.add("Partie 1");
		// parts.add("Partie 2");
		// ArrayList<String> steps1 = new ArrayList<String>();
		// steps1.add("1.1");
		// steps1.add("1.2");
		// steps1.add("1.3");
		// ArrayList<String> steps2 = new ArrayList<String>();
		// steps2.add("2.1");
		// steps2.add("2.2");
		// steps2.add("2.3");
		// itP = parts.iterator();
		// itS1 = steps1.iterator();
		// itS2 = steps2.iterator();
		// test

		this.core = core;
		this.userTextFocus = false;

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (Exception e) {
		}
		// com.jgoodies.looks.windows.WindowsLookAndFeel
		// com.jgoodies.looks.plastic.PlasticLookAndFeel
		// com.jgoodies.looks.plastic.Plastic3DLookAndFeel
		// com.jgoodies.looks.plastic.PlasticXPLookAndFeel

		this.textPanel = new TextPanel(this, false);
		this.userTextPanel = new TextPanel(this, true);
		this.textSectionPanel = new TextSectionPanel(this, textPanel, userTextPanel);
		this.scorePanel = new ScorePanel(this);
		this.timerPanel = new TimerPanel(this);
		this.umlDrawingPanel = new UMLDrawingPanel(this);
		this.navigationPanel = new NavigationPanel(this);

		this.mainFrame = new MainFrame(this, navigationPanel, scorePanel, timerPanel, textSectionPanel, umlDrawingPanel);
		this.mainFrame.setVisible(true);
	}

	/**
	 * Print a message
	 * 
	 * @param title
	 *            message pop-up title
	 * @param message
	 *            message to print
	 */
	public void doPrintMessage(String title, String message) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Sends to the core the user's request to validate his keyword selection.
	 */
	public void askValidateKeywords() {
		// TODO Auto-generated method stub

		// System.out.println("askValidateKeywords");
		//
		// int[] tab = getActiveTextPanel().getSelection();
		// int start = -1, end = -1;
		// for (int i = 0; i < tab.length - 1; i++) {
		// if (start == -1 && tab[i] != 0)
		// start = i;
		// if (end == -1 && start != -1 && tab[i + 1] == 0)
		// end = i;
		// }
		// try {
		// doValidateText(start, end, userTextFocus);
		// } catch (BadLocationException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Sends to the core the user's request to validate his keyword Uml
	 * association.
	 */
	public void askValidateAssociation() {
		// TODO Auto-generated method stub
		// System.out.println("askValidateAssociation");
	}

	/**
	 * Sends to the core the user's request to validate his Uml diagram
	 */
	public void askValidateDiagram() {
		// TODO Auto-generated method stub
		// System.out.println("askValidateAssociation");
	}

	// ******************************************************************************************************//
	// **********************************************Text_methods********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Sends to the core the text the user wants to select as an int table where
	 * tab[i] = selection %age of the word number i and a boolean true if user
	 * selects from his own text
	 */
	public void askSelectText() {
		int[] tab = getActiveTextPanel().getSelection();
		core.askSelectText(tab, userTextFocus);

		// System.out.println("askSelectText : " + Arrays.toString(tab));
		//
		// int start = -1, end = -1;
		// for (int i = 0; i < tab.length - 1; i++) {
		// if (start == -1 && tab[i] != 0)
		// start = i;
		// if (end == -1 && start != -1 && tab[i + 1] == 0)
		// end = i;
		// }
		// try {
		// doSelectText(start, end, userTextFocus);
		// } catch (BadLocationException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Sends to the core the text the user wants to unselect as an int table
	 * where tab[i] = selection %age of the word number i and a boolean true if
	 * user selects from his own text
	 */
	public void askUnSelectText() {
		int[] tab = getActiveTextPanel().getSelection();
		core.askUnSelectText(tab, userTextFocus);

		// System.out.println("askUnSelectText : " + Arrays.toString(tab));
		//
		// int start = -1, end = -1;
		// for (int i = 0; i < tab.length - 1; i++) {
		// if (start == -1 && tab[i] != 0)
		// start = i;
		// if (end == -1 && start != -1 && tab[i + 1] == 0)
		// end = i;
		// }
		// try {
		// doUnSelectText(start, end, userTextFocus);
		// } catch (BadLocationException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Ask for a table of the instanciable UMLnatures. Warning : if one of thos
	 * elements is not recognized by "doShowUmlInstanceCreationPopup" exceptions
	 * may occur
	 * 
	 * @return UMLnatures table
	 */
	public Object[] askUmlInstancesNatures() {
		return core.askUmlInstancesNatures();
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
		core.askAddText(text, comment);

		// System.out.println("askAddText " + text + "\n" + comment);
		//
		// doAddText(true, text.split(" "));
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
		core.askCreateClass(firstWord, lastWord, userText, name);

		// System.out.println("askCreateClass " + name);
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
		core.askCreateAbstractClass(firstWord, lastWord, userText, name);

		// System.out.println("askCreateAbstractClass " + name);
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
		core.askCreateInterface(firstWord, lastWord, userText, name);

		// System.out.println("askCreateInterface " + name);
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
		core.askCreateAttribute(firstWord, lastWord, userText, name, type, visibility);

		// System.out.println("askCreateAttribute " + name + " " + type + " " +
		// visibility);
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
		core.askCreateMethod(firstWord, lastWord, userText, name, paramTypes, returnType, visibility);

		// System.out.println("askCreateMethod " + name + " " +
		// paramTypes.toString() + " " + returnType + " " + visibility);
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
		String keyword = getText(firstWord, lastWord);
		if (nature.equals(UMLNature.CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle classe abstraite", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAbstractClass(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.INTERFACE)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvelle interface", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateInterface(firstWord, lastWord, userTextFocus, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			AttributeEditionPanel panel = new AttributeEditionPanel(this, keyword, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, panel, "Nouvel attribut", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateAttribute(firstWord, lastWord, userTextFocus, panel.getAttributeName(), panel.getAttributeType(), panel.getAttributeVisibility());
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			MethodEditionPanel panel = new MethodEditionPanel(this, keyword, null, null, null, null);
			int result = JOptionPane.showConfirmDialog(null, new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), "Nouvelle methode", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askCreateMethod(firstWord, lastWord, userTextFocus, panel.getMethodName(), panel.getMethodParams(), panel.getMethodReturnType(), panel
						.getMethodVisibility());
			}
		}
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
	 * Prints the missing keywords number. if nb is less than 1, hide the
	 * missing keywords label
	 * 
	 * @param nb
	 *            number to show
	 */
	public void doShowMissingKeywordNumber(int nb) {
		textSectionPanel.setMissingKeywords(nb);
	}

	// *********//
	// Internal //
	// *********//

	/**
	 * Internal method. Returns the last text panel to have recorded user
	 * activity.
	 * 
	 * @return the last text panel to have recorded user activity
	 */
	private TextPanel getActiveTextPanel() {
		if (userTextFocus)
			return userTextPanel;
		else
			return textPanel;
	}

	/**
	 * Internal method. Records the last text panel to have recorded user
	 * activity.
	 * 
	 * @param userTextFocus
	 *            true the last text panel to have recorded user activity is the
	 *            user's text panel
	 */
	public void setUserTextFocus(boolean userTextFocus) {
		this.userTextFocus = userTextFocus;
	}

	/**
	 * Internal method. Search for a validated expression at the given
	 * coordinate of the active text panel
	 * 
	 * @param point
	 *            coordinates to check
	 * @return indexs of first and last words of the expression if found, else
	 *         null
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
	 * Internal method. Returns the specified expression.
	 * 
	 * @param firstWord
	 *            index of expression first word
	 * @param lastWord
	 *            index of expression last word
	 * @return the specified expression
	 * @throws BadLocationException
	 *             if expression does not exist
	 */
	public String getText(int firstWord, int lastWord) throws BadLocationException {
		return getActiveTextPanel().getText(firstWord, lastWord);
	}

	/**
	 * Internal method. Gets the currently used text font.
	 * 
	 * @return the texts font
	 */
	public Font getTextFont() {
		return textPanel.getFont();
	}

	// ******************************************************************************************************//
	// ******************************************Uml_section_methods*****************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Sends to the core the user's request to edit the given class instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askEditClass(Object id, String name) {
		core.askEditClass(id, name);

		// System.out.println("askEditClass " + name);
	}

	/**
	 * Sends to the core the user's request to edit the given abstract class
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askEditAbstractClass(Object id, String name) {
		core.askEditAbstractClass(id, name);

		// System.out.println("askEditAbstractClass " + name);
	}

	/**
	 * Sends to the core the user's request to edit the given interface instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param name
	 *            name wanted for the new instance
	 */
	private void askEditInterface(Object id, String name) {
		core.askEditInterface(id, name);

		// System.out.println("askEditInterface " + name);
	}

	/**
	 * Sends to the core the user's request to edit the given attribute instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param name
	 *            name wanted for the new instance
	 * @param type
	 *            type wanted for the new instance
	 * @param visibility
	 *            visibility wanted for the new instance
	 */
	private void askEditAttribute(Object id, String name, String type, String visibility) {
		core.askEditAttribute(id, name, type, visibility);

		// System.out.println("askEditAttribute " + name + " " + type + " " +
		// visibility);
	}

	/**
	 * Sends to the core the user's request to edit the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param name
	 *            name wanted for the new instance
	 * @param paramTypes
	 *            list of the parameters types wanted for the new instance
	 * @param returnType
	 *            returnType wanted for the new instance
	 * @param visibility
	 *            visibility wanted for the new instance
	 */
	private void askEditMethod(Object id, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		core.askEditMethod(id, name, paramTypes, returnType, visibility);

		// System.out.println("askEditMethod " + name + " " +
		// paramTypes.toString() + " " + returnType + " " + visibility);
	}
	
	/**
	 * Sends to the core the user's request to delete the given class instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteClass(Object id) {
		core.askDeleteClass(id);

		// System.out.println("askDeleteClass");
	}

	/**
	 * Sends to the core the user's request to delete the given abstract class
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	private void askDeleteAbstractClass(Object id) {
		core.askDeleteAbstractClass(id);

		// System.out.println("askDeleteAbstractClass");
	}

	/**
	 * Sends to the core the user's request to delete the given interface instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	private void askDeleteInterface(Object id, String name) {
		core.askDeleteInterface(id);

		// System.out.println("askDeleteInterface");
	}

	/**
	 * Sends to the core the user's request to delete the given attribute instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	private void askDeleteAttribute(Object id) {
		core.askDeleteAttribute(id);

		// System.out.println("askDeleteAttribute");
	}

	/**
	 * Sends to the core the user's request to delete the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	private void askDeleteMethod(Object id) {
		core.askDeleteMethod(id);

		// System.out.println("askDeleteMethod");
	}
	
	public void askLinkAttributeToClass(Object attributeID, Object classID) {
		core.askLinkAttributeToClass(attributeID, classID);
	}
	
	public void askLinkMethodToClass(Object methodID, Object classID) {
		core.askLinkMethodToClass(methodID, classID);
	}

	/**
	 * Ask the core about the name of the Uml instance corresponding to the
	 * defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return name of the instance
	 */
	public String askUmlInstanceName(Object id) {
		return core.askUmlInstanceName(id);
	}

	/**
	 * Ask the core about the type or return type of the Uml instance
	 * corresponding to the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return type or return type of the instance
	 */
	public String askUmlInstanceType(Object id) {
		return core.askUmlInstanceType(id);
	}

	/**
	 * Ask the core about the visibility of the Uml instance corresponding to
	 * the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return visibility of the instance
	 */
	public String askUmlInstanceVisibility(Object id) {
		return core.askUmlInstanceVisibility(id);
	}

	/**
	 * Ask the core about the param types of the Uml instance corresponding to
	 * the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return param types of the instance
	 */
	public ArrayList<String> askUmlInstanceParamTypes(Object id) {
		return core.askUmlInstanceParamTypes(id);
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Add an UML instance to element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doAddElementToPool(Object id, Object nature) {
		umlDrawingPanel.doAddElementToPool(id, nature);
	}

	/**
	 * Change color of UML instance to red in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doShowUMLInstanceInRed(Object id, Object nature) {
		umlDrawingPanel.doShowUMLInstanceInRed(id, nature);
	}

	/**
	 * Reset color of UML instances to black in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doResetUMLInstanceColor(Object id, Object nature) {
		umlDrawingPanel.doResetUMLInstanceColor(id, nature);
	}

	/**
	 * Change color of UML drawing to red in drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doShowUMLDrawingInRed(Object id, Object nature) {
		umlDrawingPanel.doShowUMLDrawingInRed(id, nature);
	}

	/**
	 * Reset color of UML drawings to black in drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doResetUMLDrawingColor(Object id, Object nature) {
		umlDrawingPanel.doResetUMLDrawingColor(id, nature);
	}
	
	/**
	 * Set number of missing UML instance to display
	 * If 0, it is not display
	 * 
	 * @param number
	 *            number of missing instance to display
	 */
	public void doSetMissingUMLInstanceMissingNumber(int number) {
		umlDrawingPanel.doSetMissingUMLInstanceMissingNumber(number);
	}
	
	/**
	 * Set number of missing UML drawing to display
	 * If 0, it is not display
	 * 
	 * @param number
	 *            number of missing drawing to display
	 */
	public void doSetMissingUMLDrawingNumber(int number) {
		umlDrawingPanel.doSetMissingUMLDrawingNumber(number);
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
	public void doShowUmlInstanceEditionPopup(Object id, Object nature) throws BadLocationException {
		String keyword = id.toString();
		if (nature.equals(UMLNature.CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, askUmlInstanceName(id));
			int result = JOptionPane.showConfirmDialog(null, panel, "Edition de classe", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askEditClass(id, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, askUmlInstanceName(id));
			int result = JOptionPane.showConfirmDialog(null, panel, "Edition de classe abstraite", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askEditAbstractClass(id, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.INTERFACE)) {
			VertexEditionPanel panel = new VertexEditionPanel(this, keyword, askUmlInstanceName(id));
			int result = JOptionPane.showConfirmDialog(null, panel, "Edition d'interface", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askEditInterface(id, panel.getVertexName());
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			AttributeEditionPanel panel = new AttributeEditionPanel(this, keyword, askUmlInstanceName(id), askUmlInstanceType(id), askUmlInstanceVisibility(id));
			int result = JOptionPane.showConfirmDialog(null, panel, "Edition d'attribut", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askEditAttribute(id, panel.getAttributeName(), panel.getAttributeType(), panel.getAttributeVisibility());
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			MethodEditionPanel panel = new MethodEditionPanel(this, keyword, askUmlInstanceName(id), askUmlInstanceParamTypes(id), askUmlInstanceType(id),
					askUmlInstanceVisibility(id));
			int result = JOptionPane.showConfirmDialog(null, new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), "Edition de methode", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				askEditMethod(id, panel.getMethodName(), panel.getMethodParams(), panel.getMethodReturnType(), panel.getMethodVisibility());
			}
		}
	}

	// ******************************************************************************************************//
	// *****************************************Navigation_methods*******************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	/**
	 * Sends to the core the user's request to go to the part identified by the
	 * given object
	 * 
	 * @param part
	 *            object that the core can use to identify a part
	 */
	public void askSelectPart(Object part) {
		core.askSelectPart(part);

		// System.out.println("askSelectPart " + part.toString());
	}

	/**
	 * Sends to the core the user's request to get the correction of the step
	 * identified by the given object
	 * 
	 * @param step
	 *            object that the core can use to identify a step
	 */
	public void askCorrectStep(Object step) {
		core.askCorrectStep(step);

		// System.out.println("askCorrectStep " + step.toString());
	}

	/**
	 * Asks the core for the next part of the exercise. Consecutive calls shall
	 * then return all parts in the right order.
	 * 
	 * @return an object that the core can use to identify a part, null if no
	 *         more parts
	 */
	public Object askNextPart() {
		return core.askNextPart();

		// try {
		// return itP.next();
		// } catch (Exception e) {
		// return null;
		// }
	}

	/**
	 * Asks the core for the next step of the current part. Consecutive calls
	 * shall then return all steps in the right order.
	 * 
	 * @return an object that the core can use to identify a step, null if no
	 *         more steps in current part
	 */
	Object askNextStep() {
		return core.askNextStep();

		// try {
		// if (itP.hasNext())
		// return itS1.next();
		// else
		// return itS2.next();
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
	 * Asks the core for the timer to show
	 * 
	 * @return the timer to show
	 */
	public String askTimer() {
		return core.askTimer();
	}

	/**
	 * Asks the core for the timer font to use
	 * 
	 * @return the timer font to use
	 */
	public Font askTimerFont() {
		return core.askTimerFont();
	}

	/**
	 * Asks the core for the timer background color to use
	 * 
	 * @return the timer background color to use
	 */
	public Color askTimerBgColor() {
		return core.askTimerBgColor();
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Sets the timer to the given value
	 * 
	 * @param timer
	 *            the timer to show
	 */
	public void doSetTimer(String timer) {
		timerPanel.setTimer(timer);
	}

	/**
	 * Sets the timer font
	 * 
	 * @param font
	 *            the timer font to use
	 */
	public void doSetTimerFont(Font font) {
		timerPanel.setTimerFont(font);
	}

	/**
	 * Sets the timer background color
	 * 
	 * @param color
	 *            the timer background color to use
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
	 * Asks the core for the score to show
	 * 
	 * @return the score to show
	 */
	public String askScore() {
		return core.askScore();
	}

	/**
	 * Asks the core for the score font to use
	 * 
	 * @return the score font to use
	 */
	public Font askScoreFont() {
		return core.askScoreFont();
	}

	/**
	 * Asks the core for the score background color to use
	 * 
	 * @return the score background color to use
	 */
	public Color askScoreBgColor() {
		return core.askScoreBgColor();
	}

	// ***//
	// Do //
	// ***//

	/**
	 * Sets the score to the given value
	 * 
	 * @param score
	 *            the score to show
	 */
	public void doSetScore(String score) {
		scorePanel.setScore(score);
	}

	/**
	 * Sets the score font
	 * 
	 * @param font
	 *            the score font to use
	 */
	public void doSetScoreFont(Font font) {
		scorePanel.setScoreFont(font);
	}

	/**
	 * Sets the score background color
	 * 
	 * @param color
	 *            the score background color to use
	 */
	public void doSetScoreBgColor(Color color) {
		scorePanel.setScoreBgColor(color);
	}

}
