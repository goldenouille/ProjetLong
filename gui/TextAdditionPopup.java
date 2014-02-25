package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import actions.ActAddText;

public class TextAdditionPopup extends JFrame {

	private static final long serialVersionUID = 1L;

	public TextAdditionPopup(ClassicGuiController controller) throws HeadlessException {
		super("Ajout de texte");

		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		JPanel centerPane = new JPanel(new GridLayout(2, 1));
		
		JPanel textPane = new JPanel(new BorderLayout());
		textPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		JTextArea textArea = new JTextArea("Nouveau Texte");
		textPane.add(textArea, BorderLayout.CENTER);
		centerPane.add(textPane);
		
		JPanel commentPane = new JPanel(new BorderLayout());
		commentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		JTextArea commentArea = new JTextArea("Commentaire");
		commentPane.add(commentArea, BorderLayout.CENTER);
		centerPane.add(commentPane);
		
		this.add(centerPane, BorderLayout.CENTER);

		JPanel southPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton validationButton = new JButton(new ActAddText(controller, textArea, commentArea, this, "Valider"));
		southPane.add(validationButton);
		this.add(southPane, BorderLayout.SOUTH);

		this.setVisible(true);
	}

}
