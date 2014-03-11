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
 * Generic Panel allowing to display a timer and change its display properties
 */
public class TimerPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel timerLabel;

	/**
	 * Creates and layout the timer panel. Will ask the controller for the starting timer to show.
	 * @param controller link to gui conttroller
	 */
	public TimerPanel(ClassicGuiController c) {
		super(c);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());

		this.timerLabel = new JLabel(this.controller.askTimer());
		this.timerLabel.setFont(this.controller.askTimerFont());
		this.timerLabel.setForeground(this.controller.askTimerBgColor());
		this.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(timerLabel, BorderLayout.CENTER);
		
		this.setToolTipText("Le temps restant");
	}

	/** Sets the painted timer
	 * @param timer
	 */
	public void setTimer(String timer) {
		this.timerLabel.setText(timer);
	}

	/** Sets the font of the painted timer
	 * @param font
	 */
	public void setTimerFont(Font font) {
		this.timerLabel.setFont(font);
	}

	/** Sets the bg color of the painted timer
	 * @param color
	 */
	public void setTimerBgColor(Color color) {
		this.timerLabel.setForeground(color);
	}

}
