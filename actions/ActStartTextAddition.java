package actions;

import java.awt.event.ActionEvent;

import gui.ClassicGuiController;

public class ActStartTextAddition extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;

	public ActStartTextAddition(ClassicGuiController controller, String string) {
		super(controller, string);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.doShowTextAdditionPopup();
	}
}
