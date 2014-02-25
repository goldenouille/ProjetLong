package actions;

import gui.ClassicGuiController;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * classe de base, affiche un message d'erreur.
 */
public class BasicAbstractAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	protected ClassicGuiController controller;
	
	public BasicAbstractAction(ClassicGuiController controller, String string) {
		super(string);
		this.controller = controller;
	}

	public BasicAbstractAction(ClassicGuiController controller, String string, Icon icon) {
		super(string,icon);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.doPrintMessage("Erreur", "Non encore implante");
	}

}
