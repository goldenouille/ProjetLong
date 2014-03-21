package uml;

import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.UMLNature;

/**
 * @author Vincent Roth
 * Sensitive label used by UMLElementPanel
 * 
 * Do a particular action by the element panel.
 */
public class UMLElementListenedLabel extends JLabel {

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
	public UMLElementListenedLabel(UMLElementPanel mainpanel, int action, UMLNature nature, Object id, String text, String toolTipText) {
		super(text);
		/*
		String textLabel = "";
		if (nature.equals(UMLNature.CLASS)) {
			textLabel += "C ";
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			textLabel += "CA ";
		} else if (nature.equals(UMLNature.INTERFACE)) {
			textLabel += "I ";
		}
		textLabel += text;
		this.setText(textLabel);
		*/
		
		this.mainpanel = mainpanel;
		this.action = action;
		this.nature = nature;
		this.id = id;

		// this.setPreferredSize(new Dimension(40, 40));
		this.setToolTipText(toolTipText);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				getMainpanel().setSelectedElementAction(getElementAction());
				getMainpanel().setSelectedElementType(getElementNature());
				getMainpanel().setSelectedElementID(getElementID());
				getMainpanel().doAction();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {	
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
