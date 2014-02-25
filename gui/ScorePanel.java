package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class ScorePanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;

	public ScorePanel(ClassicGuiController c) {
		super(c);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());

		this.scoreLabel = new JLabel(this.controller.askScore());
		this.scoreLabel.setFont(this.controller.askScoreFont());
		this.scoreLabel.setForeground(this.controller.askScoreBgColor());
		this.scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(scoreLabel, BorderLayout.CENTER);

	}

	public void setScore(String score) {
		this.scoreLabel.setText(score);
	}

	public void setScoreFont(Font font) {
		this.scoreLabel.setFont(font);
	}

	public void setScoreBgColor(Color color) {
		this.scoreLabel.setForeground(color);
	}

}
