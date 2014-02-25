package actions;

import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;

import gui.ClassicGuiController;

public class ActStartUmlInstanceCreation extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;
	private int[] keywordPosition;
	private Object nature;
	
	public ActStartUmlInstanceCreation(ClassicGuiController controller, String name, int[] keywordPosition, Object nature) {
		super(controller, name);
		this.keywordPosition = keywordPosition;
		this.nature = nature;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			controller.doShowUmlInstanceCreationPopup(keywordPosition[0], keywordPosition[1], nature);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
