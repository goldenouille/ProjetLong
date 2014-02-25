package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class ClassEditionPanel extends AbstractPanel {
	
	private static final long serialVersionUID = 1L;
	protected JTextArea nameField;
	
	public ClassEditionPanel(ClassicGuiController controller, int firstWord, int lastWord, String name) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String keyword = controller.getText(firstWord, lastWord);
		keywordPanel.add(new JLabel("Mot-cle : \" " + keyword + " \""));
		this.add(keywordPanel);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextArea(name,3,30);
		this.nameField.setLineWrap(true);
		namePanel.add(new JScrollPane(nameField,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		this.add(namePanel);
	}

}
