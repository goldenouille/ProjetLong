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
import actions.ActValidateKeywords;

public class TextSectionPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel missingKeywordsLabel;
	private JButton validateKeywordsButton;
	private JButton selectTextButton;
	private JButton deselectTextButton;

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
		sliderPane.setToolTipText("Glissez pour ajuster la taille des caracteres");
		sliderPane.setLayout(new BoxLayout(sliderPane, BoxLayout.LINE_AXIS));
		sliderPane.setPreferredSize(new Dimension(20, 20));
		JLabel fontLabel = new JLabel(" Police :");
		sliderPane.add(fontLabel);
		JSlider fontSlider = new JSlider(10, 30, 12);
		fontSlider.addChangeListener(new ActChangeTextFont(controller));
		sliderPane.add(fontSlider);
		bottomPane.add(sliderPane);
		selectTextButton = new JButton(new ActSelectText(controller, "Selectionner"));
		selectTextButton.setToolTipText("Selectionnez une expression importante du texte avec le curseur puis cliquez ici pour memoriser votre selection");
		bottomPane.add(selectTextButton);
		deselectTextButton = new JButton(new ActUnSelectText(controller, "Deselectionner"));
		deselectTextButton.setToolTipText("Selectionnez du texte avec le curseur puis cliquez ici pour l'effacer de votre selection");
		bottomPane.add(deselectTextButton);
		validateKeywordsButton = new JButton(new ActValidateKeywords(controller, "Valider mots-cles"));
		validateKeywordsButton.setToolTipText("<html>Cliquez ici pour valider votre selection de mots cles devant etre traduits en elements UML."
				+ "<br>Attention ! S'il en manque ou si votre selection est incorrecte vous perdrez des points !</html>");
		bottomPane.add(validateKeywordsButton);
		missingKeywordsLabel = new JLabel();
		bottomPane.add(missingKeywordsLabel);
		bottomPane.add(Box.createHorizontalGlue());
		JButton addTextButton = new JButton(new ActStartTextAddition(controller, "Ajouter texte"));
		addTextButton.setToolTipText("<html>Vous permet d'ajouter du texte a l'enonce pour justifier votre solution."
				+ "<br>Attention ! Il y a en général une solution ne nécessitant pas d'ajouter de texte !</html>");
		bottomPane.add(addTextButton);
	}

	public void setMissingKeywords(int nb) {
		if (nb < 1) {
			missingKeywordsLabel.setText("");
		} else {
			missingKeywordsLabel.setText(" Mots-cles manquants : " + nb);
		}
	}

	public void setValidateKeywordsButtonEnabled(boolean enabled) {
		//validateKeywordsButton.setEnabled(enabled);
		//selectTextButton.setEnabled(enabled);
		//deselectTextButton.setEnabled(enabled);
	}

}