package uml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UMLElementPanelButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UMLElementPanel mainpanel;
	private int action;
	private int type;
	private int id;
	
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

	public UMLElementPanel getMainpanel() {
		return mainpanel;
	}
	
	public int getElementAction() {
		return action;
	}

	public int getElementType() {
		return type;
	}

	public int getElementID() {
		return id;
	}

}
