package gui;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAdditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JTextArea commentArea;

	public TextAdditionPanel(ClassicGuiController controller) throws HeadlessException {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel textPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		textPane.add(new JLabel("Nouveau texte : "));
		this.textArea = new JTextArea("",5,50);
		this.textArea.setLineWrap(true);
		textPane.add(new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		this.add(textPane);
		
		JPanel commentPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		commentPane.add(new JLabel("Commentaire : "));
		this.commentArea = new JTextArea("",5,50);
		this.commentArea.setLineWrap(true);
		commentPane.add(new JScrollPane(commentArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		this.add(commentPane);
	}
	
	public String getNewText(){
		return textArea.getText();
	}

	public String getComment(){
		return commentArea.getText();
	}
}
