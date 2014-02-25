package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import actions.ActCorrectStep;
import actions.ActSelectPart;

public class NavigationPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	public NavigationPanel(ClassicGuiController c) {
		super(c);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		ImageIcon section = new ImageIcon("images/section.gif");
		ImageIcon correction = new ImageIcon("images/correction.gif");

		Object currentPart = controller.askNextPart();
		Object currentStep = controller.askNextStep();

		while (currentPart!=null) {	
			JPanel partPanel = new JPanel();
			partPanel.setLayout(new BoxLayout(partPanel, BoxLayout.PAGE_AXIS));

			JPanel partNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			partNamePanel.setPreferredSize(new Dimension(100, 30));

			 JLabel iconLabel = new JLabel(section);
			 iconLabel.addMouseListener(new ActSelectPart(c, currentPart.toString(), currentPart));
			 partNamePanel.add(iconLabel);


			 JLabel partLabel = new JLabel(currentPart.toString());
			 partLabel.addMouseListener(new ActSelectPart(c, currentPart.toString(), currentPart));
			 partNamePanel.add(partLabel);

			partPanel.add(partNamePanel);

			int nbStep = 0;
			while (currentStep!=null) {
				JPanel stepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				stepPanel.setPreferredSize(new Dimension(100, 30));
				stepPanel.add(Box.createRigidArea(new Dimension(5, 0)));

				IconButton correctButton = new IconButton(new ActCorrectStep(c, "", currentStep), correction);
				correctButton.setPreferredSize(new Dimension(20, 20));
				stepPanel.add(correctButton);

				JLabel stepLabel = new JLabel(currentStep.toString());
				stepPanel.add(stepLabel);

				partPanel.add(stepPanel);
				currentStep = controller.askNextStep();
				nbStep++;
			}

			partPanel.setMaximumSize(new Dimension(200, 20*(1+nbStep)));
			this.add(partPanel);
			currentPart = controller.askNextPart();
			currentStep = controller.askNextStep();
		}
		this.add(Box.createGlue());

	}

}
