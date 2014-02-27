package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import actions.ActChangeTextFont;
import actions.ActSelectText;
import actions.ActStartTextAddition;
import actions.ActUnSelectText;
import actions.ActValidateAssociation;
import actions.ActValidateKeywords;

public class TextSectionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel missingKeywordsLabel;

	public TextSectionPanel(ClassicGuiController controller, AbstractPanel textPanel, AbstractPanel userTextPanel) {
		super(controller);
		this.setLayout(new BorderLayout());

		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		centerPane.add(textPanel);
		centerPane.add(userTextPanel);
		centerPane.add(Box.createVerticalGlue());

		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.LINE_AXIS));

		this.add(centerPane, BorderLayout.CENTER);
		this.add(bottomPane, BorderLayout.SOUTH);

		JPanel sliderPane = new JPanel();
		sliderPane.setLayout(new BoxLayout(sliderPane, BoxLayout.LINE_AXIS));
		sliderPane.setPreferredSize(new Dimension(20, 20));
		JLabel fontLabel = new JLabel(" Police :");
		sliderPane.add(fontLabel);
		JSlider fontSlider = new JSlider(10, 30, 12);
		fontSlider.addChangeListener(new ActChangeTextFont(controller));
		sliderPane.add(fontSlider);
		bottomPane.add(sliderPane);
		JButton selectTextButton = new JButton(new ActSelectText(controller, "Selectionner"));
		bottomPane.add(selectTextButton);
		JButton deselectTextButton = new JButton(new ActUnSelectText(controller, "Deselectionner"));
		bottomPane.add(deselectTextButton);
		JButton validateKeywordsButton = new JButton(new ActValidateKeywords(controller, "Valider mots-cles"));
		bottomPane.add(validateKeywordsButton);
		missingKeywordsLabel = new JLabel();
		bottomPane.add(missingKeywordsLabel);
		bottomPane.add(Box.createHorizontalGlue());
		JButton addTextButton = new JButton(new ActStartTextAddition(controller, "Ajouter texte"));
		bottomPane.add(addTextButton);
		bottomPane.add(Box.createHorizontalGlue());
		JButton validateAssociationButton = new JButton(new ActValidateAssociation(controller, "Valider association"));
		bottomPane.add(validateAssociationButton);
	}

	public void setMissingKeywords(int nb) {
		if (nb < 1) {
			missingKeywordsLabel.setText("");
		} else {
			missingKeywordsLabel.setText(" Mots-cles manquants : " + nb);
		}
	}

}