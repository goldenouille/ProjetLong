package actions;

import gui.ClassicGuiController;
import gui.MethodEditionPanel;

import java.awt.event.ActionEvent;

public class ActRemParam extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;
	private MethodEditionPanel panel;

	public ActRemParam(ClassicGuiController controller, String name, MethodEditionPanel panel) {
		super(controller, name);
		this.panel=panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panel.remParam();
	}

}
