package actions;

import java.awt.event.ActionEvent;

import gui.ClassicGuiController;

public class ActValidateAssociation extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;

	public ActValidateAssociation(ClassicGuiController controller, String string) {
		super(controller, string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.askValidateAssociation();
	}
	
}
