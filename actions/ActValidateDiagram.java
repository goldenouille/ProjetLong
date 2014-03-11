package actions;

import java.awt.event.ActionEvent;

import gui.ClassicGuiController;

public class ActValidateDiagram extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;

	public ActValidateDiagram(ClassicGuiController controller, String string) {
		super(controller, string);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.askValidateDiagram();
	}

}
