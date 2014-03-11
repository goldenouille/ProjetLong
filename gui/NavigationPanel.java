package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;

import actions.ActCorrectStep;
import actions.ActSelectPart;

/**
 * @author Will
 * Generic Panel allowing to display select and ask for correction of generic parts and steps
 */
public class NavigationPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates and layout the navigation panel. Will ask the controller for the parts and steps to show.
	 * @param controller link to gui conttroller
	 */
	public NavigationPanel(ClassicGuiController controller) {
		super(controller);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		ImageIcon section = new ImageIcon("images/section.png");
		ImageIcon correction = new ImageIcon("images/correction.png");

		Object currentPart = controller.askNextPart();
		Object currentStep = controller.askNextStep();
		
		String partTitle;
		String stepTitle;

		while (currentPart != null) {
			JPanel partPanel = new JPanel();
			partPanel.setLayout(new BoxLayout(partPanel, BoxLayout.PAGE_AXIS));

			JPanel partNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			partNamePanel.setPreferredSize(new Dimension(100, 30));

			JXCollapsiblePane stepsPanel = new JXCollapsiblePane();
			stepsPanel.getContentPane().setLayout(new BoxLayout(stepsPanel.getContentPane(), BoxLayout.PAGE_AXIS));

			IconButton sectionButton = new IconButton(stepsPanel.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION), section);
			sectionButton.setPreferredSize(new Dimension(15, 15));
			partNamePanel.add(sectionButton);

			partTitle = currentPart.toString();
			JLabel partLabel = new JLabel(partTitle.substring(0, Math.min(partTitle.length(),25)));
			partLabel.setToolTipText("Cliquez pour acceder directement a cette partie");
			partLabel.addMouseListener(new ActSelectPart(controller, currentPart.toString(), currentPart));
			partNamePanel.add(partLabel);

			partPanel.add(partNamePanel);

			int nbStep = 0;
			while (currentStep != null) {
				JPanel stepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				stepPanel.setPreferredSize(new Dimension(100, 30));
				stepPanel.add(Box.createRigidArea(new Dimension(5, 0)));

				IconButton correctButton = new IconButton(new ActCorrectStep(controller, "", currentStep), correction);
				correctButton.setToolTipText("<html>Cliquez ici pour obtenir la correction de cette etape."
						+ "<br>Attention ! Aucun point ne sera alors attribue !</html>");
				correctButton.setPreferredSize(new Dimension(20, 20));
				stepPanel.add(correctButton);

				stepTitle = currentStep.toString();
				JLabel stepLabel = new JLabel(stepTitle.substring(0, Math.min(stepTitle.length(),23)));
				// stepLabel.setToolTipText(currentStep.getToolTip());
				stepPanel.add(stepLabel);

				stepsPanel.add(stepPanel);
				currentStep = controller.askNextStep();
				nbStep++;
			}

			partPanel.add(stepsPanel);
			partPanel.setMaximumSize(new Dimension(200, 20 * (1 + nbStep)));
			this.add(partPanel);
			currentPart = controller.askNextPart();
			currentStep = controller.askNextStep();
		}
		this.add(Box.createGlue());

	}

}
