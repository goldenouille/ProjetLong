package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * @author Will
 * Panel allowing to enter a text and a comment
 */
public class TextAdditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JTextArea commentArea;

	
	/** Creates and lays out the panel
	 * @param controller link to gui controller
	 */
	public TextAdditionPanel(ClassicGuiController controller) {
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
	
	/**
	 * @return the text entered in the text area
	 */
	public String getNewText(){
		return textArea.getText();
	}

	/**
	 * @return the text entered in the comment area
	 */
	public String getComment(){
		return commentArea.getText();
	}
}
