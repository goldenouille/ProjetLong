package actions;

import java.awt.event.ActionEvent;

import gui.ClassicGuiController;

public class ActCorrectStep extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;
	private Object step;

	public ActCorrectStep(ClassicGuiController controller, String string, Object step) {
		super(controller, string);
		this.step = step;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.askCorrectStep(step);
	}
}
