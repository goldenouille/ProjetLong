package actions;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.ClassicGuiController;

public class ActSelectPart extends BasicAbstractAction implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Object part;

	public ActSelectPart(ClassicGuiController controller, String string, Object part) {
		super(controller, string);
		this.part = part;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		controller.askSelectPart(part);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
