import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LinkEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField motherMultiplicityField;
	private JTextField daughterMultiplicityField;
	private JTextField textField;

	public LinkEditionPanel(String motherClass, String motherMultiplicity, String daughterClass, String daughterMultiplicity, String text){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setSize(200, 100);;

		JPanel motherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		motherPanel.add(new JLabel("Multiciplicité de " + motherClass + " : "));
		this.motherMultiplicityField = new JTextField(motherMultiplicity, 30);
		motherPanel.add(motherMultiplicityField);
		this.add(motherPanel);

		JPanel daughterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		daughterPanel.add(new JLabel("Multiciplicité de " + daughterClass + " : "));
		this.daughterMultiplicityField = new JTextField(daughterMultiplicity, 30);
		daughterPanel.add(daughterMultiplicityField);
		this.add(daughterPanel);

		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		textPanel.add(new JLabel("Texte : "));
		this.textField = new JTextField(text, 30);
		textPanel.add(textField);
		this.add(textPanel);
	}

	public String getMotherMultiplicity() {
		return motherMultiplicityField.getText();
	}

	public String getDaughterMultiplicity() {
		return daughterMultiplicityField.getText();
	}

	public String getText() {
		return textField.getText();
	}
}