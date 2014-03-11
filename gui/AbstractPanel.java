package gui;

import javax.swing.JPanel;

/**
 * @author Will
 * Panel linking to the gui controller
 */
public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ClassicGuiController controller;

	/** Simple constructor
	 * @param controller link to gui controller
	 */
	public AbstractPanel(ClassicGuiController controller) {
		this.controller = controller;
	}
}
