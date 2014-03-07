package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

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

	// *** General methods ***//

	/**
	 * Main constructor, create and show the gui.
	 * 
	 * @param core
	 */
	public ClassicGuiController(ModelController core) {

		this.core = core;
		this.userTextFocus = false;

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
		core.askValidateKeywords();
	}
	
	/** Enable or disable the keywords validation button
	 * @param enabled propertie of the button to set
	 */
	public void doSetValidateKeywordsButtonEnabled(boolean enabled) {
		textSectionPanel.setValidateKeywordsButtonEnabled(enabled);
	}

	/**
	 * Sends to the core the user's request to validate his keyword Uml
	 * association.
	 */
	public void askValidateAssociation() {
		core.askValidateAssociation();
	}
	
	/** Enable or disable the association validation button
	 * @param enabled propertie of the button to set
	 */
	public void doSetValidateAssociationButtonEnabled(boolean enabled) {
		textSectionPanel.setValidateAssociationButtonEnabled(enabled);
	}

	/**
	 * Sends to the core the user's request to validate his Uml diagram
	 */
	public void askValidateDiagram() {
		core.askValidateDiagram();
	}
	
	/** Enable or disable the diagram validation button
	 * @param enabled propertie of the button to set
	 */
	public void doSetValidateDiagramButtonEnabled(boolean enabled) {
		umlDrawingPanel.setValidateDiagramButtonEnabled(enabled);
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
		//TODO
		System.out.println("CGC.askSelectText : " + Arrays.toString(tab));
		core.askSelectText(tab, userTextFocus);
	}

	/**
	 * Sends to the core the text the user wants to unselect as an int table
	 * where tab[i] = selection %age of the word number i and a boolean true if
	 * user selects from his own text
	 */
	public void askUnSelectText() {
		int[] tab = getActiveTextPanel().getSelection();
		//TODO
		System.out.println("CGC.askUnSelectText : " + Arrays.toString(tab));
		core.askUnSelectText(tab, userTextFocus);
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
	
	/**
	 * Prints the missing association number. if nb is less than 1, hide the
	 * missing association label
	 * 
	 * @param nb
	 *            number to show
	 */
	public void doShowMissingAssociationNumber(int nb) {
		textSectionPanel.setMissingAssociation(nb);
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
	public void askDeleteAbstractClass(Object id) {
		core.askDeleteAbstractClass(id);

		// System.out.println("askDeleteAbstractClass");
	}

	/**
	 * Sends to the core the user's request to delete the given interface instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteInterface(Object id) {
		core.askDeleteInterface(id);

		// System.out.println("askDeleteInterface");
	}

	/**
	 * Sends to the core the user's request to delete the given attribute instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteAttribute(Object id) {
		core.askDeleteAttribute(id);

		// System.out.println("askDeleteAttribute");
	}

	/**
	 * Sends to the core the user's request to delete the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteMethod(Object id) {
		core.askDeleteMethod(id);

		// System.out.println("askDeleteMethod");
	}
	
	/**
	 * Sends to the core the user's request to link the given attribute instance to the given class instance
	 * 
	 * @param attributeID
	 *            identifier of the attribute instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askLinkAttributeToClass(Object attributeID, Object classID) {
		core.askLinkAttributeToClass(attributeID, classID);
	}
	
	/**
	 * Sends to the core the user's request to unlink the given attribute instance to the given class instance
	 * 
	 * @param attributeID
	 *            identifier of the attribute instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askUnLinkAttributeToClass(Object attributeID, Object classID) {
		core.askUnLinkAttributeToClass(attributeID, classID);
	}
	
	/**
	 * Sends to the core the user's request to link the given method instance to the given class instance
	 * 
	 * @param methodID
	 *            identifier of the method instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askLinkMethodToClass(Object methodID, Object classID) {
		core.askLinkMethodToClass(methodID, classID);
	}
	
	/**
	 * Sends to the core the user's request to unlink the given method instance to the given class instance
	 * 
	 * @param methodID
	 *            identifier of the method instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askUnLinkMethodToClass(Object methodID, Object classID) {
		core.askUnLinkMethodToClass(methodID, classID);
	}
	
	/**
	 * Sends to the core the user's request to unlink the given method instance to the given class instance
	 * 
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askUnLinkAllElementToClass(Object classID) {
		core.askUnLinkAllElementToClass(classID);
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

	// *************//
	// Ask Relation // TODO
	// *************//
	
	/**
	 * Sends to the core the user's request to create a new relation
	 * 
	 * @param nature
	 *            relation nature
	 * @param classesID
	 *            classes list linked with this relation
	 * @param multiplicity
	 *            multiplicity list for each class
	 * @param text
	 *            text of relation
	 */

	// TODO A REVOIR ?
	public void askCreateRelation(Object nature, ArrayList<Object> classesID, ArrayList<String> multiplicity, String text) {
		core.askCreateRelation(nature, classesID, multiplicity, text);

		// System.out.println("askCreateRelation");
	}
	
	/**
	 * Sends to the core the user's request to create a new relation
	 * 
	 * @param id
	 *            relation id
	 * @param nature
	 *            relation nature
	 * @param multiplicity
	 *            multiplicity list for each class
	 * @param text
	 *            text of relation
	 */

	// TODO A REVOIR ?
	public void askEditRelation(Object id, ArrayList<String> multiplicity, String text) {
		core.askEditRelation(id, multiplicity, text);

		// System.out.println("askEditRelation");
	}
	
	/**
	 * Sends to the core the user's request to delete a relation
	 * 
	 * @param id
	 *            relation id
	 * @param nature
	 *            relation nature
	 */
	public void askDeleteRelation(Object id) {
		core.askDeleteRelation(id);

		// System.out.println("askDeletRelation");
	}
	
	/**
	 * Ask the core about the classes linked with the Uml relation
	 * First class is consider as main class (or target class) for drawing arrow
	 * 
	 * @param id
	 *            identifier of the relation
	 * @param nature
	 *            nature of the relation
	 * @return classes id linked by the relation
	 */
	public ArrayList<Object> askUMLRelationClasses(Object id) {
		return core.askUMLRelationClasses(id);
	}
	
	/**
	 * Ask the core about the multiplicity of the Uml relation
	 * List starts the main class (target, where arrow is draw)
	 * and ends with other class in same askUMLRelationClasses class order.
	 * 
	 * @param id
	 *            identifier of the relation
	 * @param nature
	 *            nature of the relation
	 * @return multiplicity of the relation
	 */
	public ArrayList<String> askUMLRelationMultiplicity(Object id) {
		return core.askUMLRelationMultiplicity(id);
	}
	
	/**
	 * Ask the core about the text of the Uml relation
	 * 
	 * @param id
	 *            identifier of the relation
	 * @param nature
	 *            nature of the relation
	 * @return text of the relation
	 */
	public String askUMLRelationText(Object id) {
		return core.askUMLRelationText(id);
		
		// return "DefaultText";
	}
	
	/**
	 * Ask the core to reverse direction of a relation
	 * 
	 * @param id
	 *            identifier of the relation
	 * @param nature
	 *            nature of the relation
	 */
	public void askReverseRelation(Object id) {
		core.askReverseRelation(id);
	}
	
	// ***//
	// Do //
	// ***//
	
	/**
	 * Set number of missing UML drawing to display
	 * If 0, it is not display
	 * 
	 * @param nb
	 *            number to show
	 */
	public void doShowMissingUmlInstanceNumber(int nb) {
		umlDrawingPanel.setMissingUmlInstance(nb);
	}

	/**
	 * Add an UML instance to element panel
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
	 * Remove an UML instance from element panel
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doRemoveElementFromPool(Object id, Object nature) {
		umlDrawingPanel.doRemoveElementFromPool(id, nature);
	}

	/**
	 * Add an UML relation to drawing panel
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doAddRelationToDrawingArea(Object id, Object nature) {
		umlDrawingPanel.doAddRelationToDrawingArea(id, nature);
	}
	
	/**
	 * Change color of UML instance to red in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 */
	public void doShowUMLInstanceInRed(Object id) {
		umlDrawingPanel.doShowUMLInstanceInRed(id);
	}

	/**
	 * Reset color of UML instances to black in element pool
	 */
	public void doResetUMLInstanceColor() {
		umlDrawingPanel.doResetUMLInstanceColor();
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
	 */
	public void doResetUMLDrawingColor() {
		umlDrawingPanel.doResetUMLDrawingColor();
	}

	/**
	 * Shows a pop-up allowing to specify properties of an existing uml instance
	 * of the given nature, bound to the specified expression. Calls the
	 * associated instance edition method if user validates.
	 * 
	 * @param id
	 *            identifier of the instance to edit
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
