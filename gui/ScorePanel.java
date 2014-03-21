package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * @author Will
 * Generic Panel allowing to display a score and change its display properties
 */
public class ScorePanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;

	/**
	 * Creates and layout the score panel. Will ask the controller for the starting score to show.
	 * @param controller link to gui conttroller
	 */
	public ScorePanel(ClassicGuiController c) {
		super(c);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());

		this.scoreLabel = new JLabel("...");
		this.scoreLabel.setFont(this.controller.askScoreFont());
		this.scoreLabel.setForeground(this.controller.askScoreBgColor());
		this.scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(scoreLabel, BorderLayout.CENTER);
		
		this.setToolTipText("Votre score actuel");
	}

	/** Sets the painted score
	 * @param score
	 */
	public void setScore(String score) {
		this.scoreLabel.setText(score);
	}

	/** Sets the font of the painted score
	 * @param font
	 */
	public void setScoreFont(Font font) {
		this.scoreLabel.setFont(font);
	}

	/** Sets the bg color of the painted score
	 * @param color
	 */
	public void setScoreBgColor(Color color) {
		this.scoreLabel.setForeground(color);
	}

}
