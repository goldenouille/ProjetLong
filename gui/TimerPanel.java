package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class TimerPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JLabel timerLabel;

	public TimerPanel(ClassicGuiController c) {
		super(c);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());

		this.timerLabel = new JLabel(this.controller.askTimer());
		this.timerLabel.setFont(this.controller.askTimerFont());
		this.timerLabel.setForeground(this.controller.askTimerBgColor());
		this.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(timerLabel, BorderLayout.CENTER);
	}

	public void setTimer(String timer) {
		this.timerLabel.setText(timer);
	}

	public void setTimerFont(Font font) {
		this.timerLabel.setFont(font);
	}

	public void setTimerBgColor(Color color) {
		this.timerLabel.setForeground(color);
	}

}
