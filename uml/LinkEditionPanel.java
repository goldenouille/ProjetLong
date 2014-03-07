package uml;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LinkEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField firstMultiplicityField;
	private JTextField secondMultiplicityField;
	private JTextField textField;

	/**
	 * Main constructor, create a LinkEditionPanel
	 * 
	 * @param firstClass
	 *            main class name
	 * @param firstMultiplicity
	 *            main class multiplicity
	 * @param displayFirstMultiplicityField
	 *            set display first multiplicity field
	 * @param secondClass
	 *            second class name
	 * @param secondMultiplicity
	 *            second class multiplicity
	 * @param displaySecondMultiplicityField
	 *            set display second multiplicity field
	 * @param text
	 *            previous link text
	 * @param displayTextField
	 *            set display text field
	 */
	public LinkEditionPanel(String firstClass,
			String firstMultiplicity, boolean displayFirstMultiplicityField,
			String secondClass,
			String secondMultiplicity, boolean displaySecondMultiplicityField,
			String text, boolean displayTextField){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		

		this.firstMultiplicityField = new JTextField(firstMultiplicity, 30);
		this.secondMultiplicityField = new JTextField(secondMultiplicity, 30);
		this.textField = new JTextField(text, 30);

		if (displayFirstMultiplicityField) {
			JPanel firstClassPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			firstClassPanel.add(new JLabel("Multiciplicité de " + firstClass + " : "));
			firstClassPanel.add(firstMultiplicityField);
			this.add(firstClassPanel);
		}

		if (displaySecondMultiplicityField) {
			JPanel daughterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			daughterPanel.add(new JLabel("Multiciplicité de " + secondClass + " : "));
			daughterPanel.add(secondMultiplicityField);
			this.add(daughterPanel);
		}

		if (displayTextField) {
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			textPanel.add(new JLabel("Texte : "));
			textPanel.add(textField);
			this.add(textPanel);
		}
	}

	/**
	 * Get main class multiplicity
	 * 
	 * @return multiplicity text
	 */
	public String getFirstClassMultiplicity() {
		return firstMultiplicityField.getText();
	}

	/**
	 * Get second class multiplicity
	 * 
	 * @return multiplicity text
	 */
	public String getSecondClassMultiplicity() {
		return secondMultiplicityField.getText();
	}

	/**
	 * Get link text
	 * 
	 * @return text
	 */
	public String getText() {
		return textField.getText();
	}
}