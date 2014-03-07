package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class VertexEditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	protected JTextField nameField;

	public VertexEditionPanel(ClassicGuiController controller, String keyword, String name) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (keyword == null) {
			keyword = "Element non lie a un mot-cle.";
		} else {
			keyword = "Mot-cle : \" " + keyword + " \"";
		}
		keywordPanel.add(new JLabel(keyword));
		this.add(keywordPanel);

		if (name == null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name, 30);
		nameField.setToolTipText("Entrez le nom voulu pour l'element UML");
		namePanel.add(nameField);
		this.add(namePanel);
	}

	public String getVertexName() {
		return nameField.getText();
	}
}
