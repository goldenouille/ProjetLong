package actions;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import gui.ClassicGuiController;

public class ActExpandPart extends BasicAbstractAction implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel partPanel;

	public ActExpandPart(ClassicGuiController controller, JPanel partPanel) {
		super(controller, null);
		this.partPanel = partPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		partPanel.setVisible(true);
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
