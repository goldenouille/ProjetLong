package actions;

import gui.ClassicGuiController;
import java.awt.event.ActionEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActChangeTextFont extends BasicAbstractAction implements ChangeListener {

	private static final long serialVersionUID = 1L;

	public ActChangeTextFont(ClassicGuiController controller) {
		super(controller, "ActClickText");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		controller.doSetTextFont(controller.getTextFont().deriveFont((float) ((JSlider) event.getSource()).getValue()));
	}

}
