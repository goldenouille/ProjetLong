package gui;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ClassicGuiController controller;

	public AbstractPanel(ClassicGuiController controller) {
		this.controller = controller;
	}
}
