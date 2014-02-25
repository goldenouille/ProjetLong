package actions;

import java.awt.event.ActionEvent;

import gui.ClassicGuiController;

public class ActValidateKeywords extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;

	public ActValidateKeywords(ClassicGuiController controller, String string) {
		super(controller, string);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.askValidateKeywords();
	}

}
