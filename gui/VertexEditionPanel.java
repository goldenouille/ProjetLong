package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class VertexEditionPanel extends AbstractPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	
	public VertexEditionPanel(ClassicGuiController controller, int firstWord, int lastWord, String name) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String keyword = controller.getText(firstWord, lastWord);
		keywordPanel.add(new JLabel("Mot-cle : \" " + keyword + " \""));
		this.add(keywordPanel);
		
		if (name == null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name,30);
		namePanel.add(nameField);
		this.add(namePanel);
	}

	public String getVertexName() {
		return nameField.getText();
	}
}