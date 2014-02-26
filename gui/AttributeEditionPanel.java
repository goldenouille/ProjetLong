package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class AttributeEditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField typeField;
	private JTextField visibilityField;

	public AttributeEditionPanel(ClassicGuiController controller, int firstWord, int lastWord, String name, String type, String visibility) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String keyword = controller.getText(firstWord, lastWord);
		keywordPanel.add(new JLabel("Mot-cle : \" " + keyword + " \""));
		this.add(keywordPanel);

		if (name == null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name, 30);
		namePanel.add(nameField);
		this.add(namePanel);

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		typePanel.add(new JLabel("Type : "));
		this.typeField = new JTextField(type, 30);
		typePanel.add(typeField);
		this.add(typePanel);

		JPanel visibilityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		visibilityPanel.add(new JLabel("Visibilite : "));
		this.visibilityField = new JTextField(visibility, 30);
		visibilityPanel.add(visibilityField);
		this.add(visibilityPanel);
	}

	public String getAttributeName() {
		return nameField.getText();
	}

	public String getAttributeType() {
		return typeField.getText();
	}

	public String getAttributeVisibility() {
		return visibilityField.getText();
	}
}
