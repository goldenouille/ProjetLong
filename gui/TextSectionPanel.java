package gui;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import actions.ActSelectText;
import actions.ActStartTextAddition;
import actions.ActUnSelectText;
import actions.ActValidateAssociation;
import actions.ActValidateKeywords;

public class TextSectionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	
	public TextSectionPanel(ClassicGuiController controller, AbstractPanel textPanel, AbstractPanel userTextPanel) {
		super(controller);
		this.setLayout(new BorderLayout());
		
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		centerPane.add(textPanel);
		centerPane.add(userTextPanel);
		
		JScrollPane scrollPane = new JScrollPane(centerPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.LINE_AXIS));
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(bottomPane, BorderLayout.SOUTH);

		JButton selectTextButton = new JButton(new ActSelectText(controller, "Selectionner"));
		bottomPane.add(selectTextButton);
		JButton deselectTextButton = new JButton(new ActUnSelectText(controller, "Deselectionner"));
		bottomPane.add(deselectTextButton);
		JButton validateKeywordsButton = new JButton(new ActValidateKeywords(controller, "Valider mots-cles"));
		bottomPane.add(validateKeywordsButton);
		bottomPane.add(Box.createHorizontalGlue());
		JButton addTextButton = new JButton(new ActStartTextAddition(controller, "Ajouter texte"));
		bottomPane.add(addTextButton);
		bottomPane.add(Box.createHorizontalGlue());
		JButton validateAssociationButton = new JButton(new ActValidateAssociation(controller, "Valider association"));
		bottomPane.add(validateAssociationButton);
	}
	

}