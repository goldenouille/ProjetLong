package uml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UMLElementPanelButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private UMLElementPanel mainpanel;
	private int action;
	private int type;
	private int id;
	
	/**
	 * Main constructor, create an UMLElementPanel dedicated button.
	 * 
	 * @param mainpanel
	 *            UMLElementPanel
	 * @param action
	 *            UMLElementPanel action set by button click
	 * @param type
	 *            UMLDrawingPanel type set by button click
	 * @param id
	 *            intern ID set by button click
	 * @param text
	 *            text of button
	 * @param toolTipText
	 *            text show as tooltip
	 */
	public UMLElementPanelButton (UMLElementPanel mainpanel, int action, int type, int id, String text, String toolTipText) {
		this.mainpanel = mainpanel;
		this.action = action;
		this.type = type;
		this.id = id;
		
		//this.setPreferredSize(new Dimension(40, 40));
		this.setToolTipText(toolTipText);
		this.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent ae) {
	    		getMainpanel().setSelectedElementAction(getElementAction());
	    		getMainpanel().setSelectedElementType(getElementType());
	    		getMainpanel().setSelectedElementID(getElementID());
	    		getMainpanel().doAction();
	    	}
	    });
		this.setText(text);
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
	}

	/**
	 * Get UMLElementPanel
	 * 
	 * @return UMLElementPanel set by click
	 */
	public UMLElementPanel getMainpanel() {
		return mainpanel;
	}
	
	/**
	 * Get Element Action
	 * 
	 * @return action int as define in ULMElementPanel
	 */
	public int getElementAction() {
		return action;
	}

	/**
	 * Get Element UML Type
	 * 
	 * @return UML type int as define in ULMDrawingPanel
	 */
	public int getElementType() {
		return type;
	}

	/**
	 * Get Element ID
	 * 
	 * @return id of corresponding element in main UMLElementPanel
	 */
	public int getElementID() {
		return id;
	}

}
