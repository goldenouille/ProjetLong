package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import gui.ClassicGuiController;
import gui.TextAdditionPopup;

public class ActAddText extends BasicAbstractAction {

	private static final long serialVersionUID = 1L;
	private TextAdditionPopup textAdditionPopup;
	private JTextArea textArea;
	private JTextArea commentArea;

	public ActAddText(ClassicGuiController controller, JTextArea textArea, JTextArea commentArea, TextAdditionPopup textAdditionPopup, String string) {
		super(controller, string);
		this.textArea = textArea;
		this.commentArea = commentArea;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.askAddText(textArea.getText(), commentArea.getText());
		
	}
}
