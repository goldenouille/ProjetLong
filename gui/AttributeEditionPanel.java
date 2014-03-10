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

	public AttributeEditionPanel(ClassicGuiController controller, String keyword, String name, String type, String visibility) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if (name == null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");

		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (keyword == null) {
			keyword = "Element non lie a un mot-cle.";
		} else {
			keyword = "Mot-cle : \" " + keyword + " \"";
		}
		keywordPanel.add(new JLabel(keyword));
		this.add(keywordPanel);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name, 30);
		nameField.setToolTipText("Entrez le nom voulu pour l'attribut");
		namePanel.add(nameField);
		this.add(namePanel);

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		typePanel.add(new JLabel("Type : "));
		this.typeField = new JTextField(type, 30);
		typeField.setToolTipText("Type Java de l'attribut");
		typePanel.add(typeField);
		this.add(typePanel);

		JPanel visibilityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		visibilityPanel.add(new JLabel("Visibilite : "));
		this.visibilityField = new JTextField(visibility, 30);
		visibilityField.setToolTipText("Visibilite de l'attribut");
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
