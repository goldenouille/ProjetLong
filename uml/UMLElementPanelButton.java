package uml;

import gui.IconButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import model.UMLNature;

/**
 * @author Vincent Roth
 * Button used by UMLElementPanel
 * 
 * Do a particular action by the element panel.
 */
public class UMLElementPanelButton extends IconButton {
	
	private static final long serialVersionUID = 1L;
	
	private UMLElementPanel mainpanel;
	private int action;
	private UMLNature nature;
	private Object id;
	
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
	 *            object ID set by button click
	 * @param text
	 *            text of button
	 * @param toolTipText
	 *            text show as tooltip
	 */
	public UMLElementPanelButton (UMLElementPanel mainpanel, int action, UMLNature nature, Object id, String text, String toolTipText, ImageIcon icon) {
		super(null,icon);
		this.mainpanel = mainpanel;
		this.action = action;
		this.nature = nature;
		this.id = id;
		
		//this.setPreferredSize(new Dimension(40, 40));
		this.setToolTipText(toolTipText);
		this.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent ae) {
	    		getMainpanel().setSelectedElementAction(getElementAction());
	    		getMainpanel().setSelectedElementType(getElementNature());
	    		getMainpanel().setSelectedElementID(getElementID());
	    		getMainpanel().doAction();
	    	}
	    });
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
	 * @return UMLNature
	 */
	public UMLNature getElementNature() {
		return nature;
	}

	/**
	 * Get Element ID
	 * 
	 * @return id of corresponding element in main UMLElementPanel
	 */
	public Object getElementID() {
		return id;
	}

}
