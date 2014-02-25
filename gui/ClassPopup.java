package gui;

import java.awt.Component;

import javax.swing.text.BadLocationException;

public class ClassPopup extends NaturePopup {

	private static final long serialVersionUID = 1L;

	public ClassPopup(Component parent, ClassicGuiController controller, int firstWord, int lastWord, String name) throws BadLocationException {
		super(parent, controller, firstWord, lastWord, name);
		this.setTitle("Classe");
		this.pack();
		this.setVisible(true);
	}



}
