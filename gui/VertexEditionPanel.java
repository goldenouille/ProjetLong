package gui;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Will
 * This panel allows to view and edit Vertex (class, abstract class and interface) properties
 */
public class VertexEditionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	protected JTextField nameField;

	/** Classic constructor
	 * @param controller link to controller
	 * @param keyword the keyword linked to the vertex, null otherwise
	 * @param name name of the vertex, can be null
	 */
	public VertexEditionPanel(ClassicGuiController controller, String keyword, String name) {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if (name == null && keyword != null)
			name = keyword.trim().toLowerCase().replaceAll("[^\\w ]", "");

		JPanel keywordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (keyword == null) {
			keyword = "Element non lie a un mot-cle.";
		} else {
			keyword = "Mot-cle : \" " + keyword + " \"";
		}
		keywordPanel.add(new JLabel(keyword));
		this.add(keywordPanel);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Nom : "));
		this.nameField = new JTextField(name, 30);
		nameField.setToolTipText("Entrez le nom voulu pour l'element UML");
		namePanel.add(nameField);
		this.add(namePanel);
	}

	/**
	 * @return choosen name for the vertex
	 */
	public String getVertexName() {
		return nameField.getText();
	}
}
