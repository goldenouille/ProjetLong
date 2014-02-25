package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import actions.ActChangeUmlInstanceProperties;

public class NaturePopup extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected ClassicGuiController controller;
	protected JTextField nameField;
	
	public NaturePopup(Component parent, ClassicGuiController controller, int firstWord, int lastWord, String name) throws BadLocationException {
		super();
		this.controller = controller;
	
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String keyword = controller.getText(firstWord, lastWord);
		keywordPanel.add(new JLabel("Mot-cle : \" " + keyword + " \""));
		mainPanel.add(keywordPanel);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name,30);
		namePanel.add(nameField);
		mainPanel.add(namePanel);
		
		this.add(mainPanel, BorderLayout.CENTER);
		
		
		JPanel validationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton validationButton = new JButton(new ActChangeUmlInstanceProperties(controller, "Valider"));
		validationPanel.add(validationButton);
		this.add(validationPanel, BorderLayout.SOUTH);
	}

}
