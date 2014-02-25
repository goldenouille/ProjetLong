package actions;

import gui.ClassicGuiController;
import gui.TextPanel;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class ActClickText extends BasicAbstractAction implements MouseListener {

	private static final long serialVersionUID = 1L;

	public ActClickText(ClassicGuiController controller) {
		super(controller, "ActClickText");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (SwingUtilities.isRightMouseButton(event)) {
			int[] keywordPosition = controller.getKeyword(event.getPoint());
			if (keywordPosition != null) {
				JPopupMenu menu = new JPopupMenu();
				for (Object nature : controller.askUmlInstancesNatures()) {
					menu.add(new ActStartUmlInstanceCreation(controller, nature.toString(), keywordPosition, nature));
				}
				menu.show(event.getComponent(), event.getX(), event.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
		controller.setUserTextFocus(((TextPanel) ((JComponent) event.getSource()).getParent().getParent().getParent()).isUserText());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
