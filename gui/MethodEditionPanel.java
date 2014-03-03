package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import actions.ActAddParam;
import actions.ActRemParam;

public class MethodEditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField typeField;
	private JTextField visibilityField;
	private ArrayList<JTextField> paramFields;
	private JPanel paramsPanel;

	public MethodEditionPanel(ClassicGuiController controller, String keyword, String name, ArrayList<String> paramTypes, String returnType,
			String visibility) throws BadLocationException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		keywordPanel.add(new JLabel("Mot-cle : \" " + keyword + " \""));
		this.add(keywordPanel);

		if (name == null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom :"));
		namePanel.add(Box.createRigidArea(new Dimension(56, 0)));
		this.nameField = new JTextField(name, 30);
		nameField.setToolTipText("Entrez le nom voulu pour la methode");
		namePanel.add(nameField);
		this.add(namePanel);

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		typePanel.add(new JLabel("Type de retour :"));
		typePanel.add(Box.createRigidArea(new Dimension(0, 0)));
		this.typeField = new JTextField(returnType, 30);
		typeField.setToolTipText("Type Java de retour de la methode");
		typePanel.add(typeField);
		this.add(typePanel);

		JPanel visibilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		visibilityPanel.add(new JLabel("Visibilite :"));
		visibilityPanel.add(Box.createRigidArea(new Dimension(34, 0)));
		this.visibilityField = new JTextField(visibility, 30);
		visibilityField.setToolTipText("Visibilite de la methode");
		visibilityPanel.add(visibilityField);
		this.add(visibilityPanel);

		JPanel paramsHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		paramsHeaderPanel.add(new JLabel("Types des parametres : "));
		JButton addParamButton = new JButton(new ActAddParam(controller, "+", this));
		addParamButton.setToolTipText("Ajouter un parametre");
		paramsHeaderPanel.add(addParamButton);
		JButton removeParamButton = new JButton(new ActRemParam(controller, "-", this));
		removeParamButton.setToolTipText("Retirer le dernier parametre");
		paramsHeaderPanel.add(removeParamButton);
		this.add(paramsHeaderPanel);

		paramFields = new ArrayList<JTextField>();
		paramsPanel = new JPanel();
		paramsPanel.setLayout(new BoxLayout(paramsPanel, BoxLayout.PAGE_AXIS));
		this.add(paramsPanel);

		this.add(Box.createRigidArea(new Dimension(0, 50)));

		if (paramTypes != null)
			for (String param : paramTypes) {
				addParam(param);
			}
	}

	public String getMethodName() {
		return nameField.getText();
	}

	public String getMethodReturnType() {
		return typeField.getText();
	}

	public String getMethodVisibility() {
		return visibilityField.getText();
	}

	public ArrayList<String> getMethodParams() {
		ArrayList<String> paramTypes = new ArrayList<String>();
		for (JTextField field : paramFields) {
			paramTypes.add(field.getText());
		}
		return paramTypes;
	}

	public void addParam(String param) {
		JTextField field = new JTextField(param, 30);
		field.setToolTipText("Type Jave du parametre");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(field);
		paramFields.add(field);
		paramsPanel.add(panel);
		this.revalidate();
	}

	public void remParam() {
		paramFields.remove(paramFields.size() - 1);
		paramsPanel.remove(paramsPanel.getComponentCount() - 1);
		this.revalidate();
	}
}
