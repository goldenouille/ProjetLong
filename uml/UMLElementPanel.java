package uml;

import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UMLElementPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> classes;
	private Vector<String> properties;
	private Vector<String> methods;
	
	public UMLElementPanel() {
		super();
		
		classes = new Vector<String>();
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.refresh();
		
		/*
		while (currentPart!=null) {	
			

			JPanel partNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			partNamePanel.setPreferredSize(new Dimension(100, 30));

			 JLabel iconLabel = new JLabel(section);
			 iconLabel.addMouseListener(new ActSelectPart(c, currentPart.toString(), currentPart));
			 partNamePanel.add(iconLabel);


			 JLabel partLabel = new JLabel(currentPart.toString());
			 partLabel.setToolTipText("Cliquez pour acceder directement a cette partie");
			 partLabel.addMouseListener(new ActSelectPart(c, currentPart.toString(), currentPart));
			 partNamePanel.add(partLabel);

			partPanel.add(partNamePanel);

			int nbStep = 0;
			while (currentStep!=null) {
				JPanel stepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				stepPanel.setPreferredSize(new Dimension(100, 30));
				stepPanel.add(Box.createRigidArea(new Dimension(5, 0)));

				IconButton correctButton = new IconButton(new ActCorrectStep(c, "", currentStep), correction);
				correctButton.setToolTipText("<html>Cliquez ici pour obtenir la correction de cette etape." +
						"<br>Attention ! Aucun point ne sera alors attribue !</html>");
				correctButton.setPreferredSize(new Dimension(20, 20));
				stepPanel.add(correctButton);

				JLabel stepLabel = new JLabel(currentStep.toString());
				//stepLabel.setToolTipText(currentStep.getToolTip());
				stepPanel.add(stepLabel);

				partPanel.add(stepPanel);
				currentStep = controller.askNextStep();
				nbStep++;
			}

			partPanel.setMaximumSize(new Dimension(200, 20*(1+nbStep)));
			this.add(partPanel);
		}
		*/
		this.add(Box.createGlue());
	}
	
	public void refresh() {
		this.removeAll();
		
		JPanel classesPanel = new JPanel();
		classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.PAGE_AXIS));
		classesPanel.add(new JLabel("Classes :"));
		for (int i = 0 ; i < classes.size() ; i++) {
			classesPanel.add(new JLabel(classes.get(i)));
		}
		this.add(classesPanel);
		
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.PAGE_AXIS));
		propertiesPanel.add(new JLabel("Attributs :"));
		for (int i = 0 ; i < properties.size() ; i++) {
			propertiesPanel.add(new JLabel(properties.get(i)));
		}
		this.add(propertiesPanel);
		
		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.PAGE_AXIS));
		methodPanel.add(new JLabel("Méthodes :"));
		for (int i = 0 ; i < methods.size() ; i++) {
			methodPanel.add(new JLabel(methods.get(i)));
		}
		this.add(methodPanel);
	}

	public void addClass(String c) {
		classes.add(c);
	}

	public boolean removeClass(String c) {
		return classes.remove(c);
	}

	public void addProperty(String property) {
		properties.add(property);
	}

	public boolean removeProperty(String property) {
		return properties.remove(property);
	}
	
	public void addMethod(String method) {
		methods.add(method);
	}

	public boolean removeMethod(String method) {
		return methods.remove(method);
	}

}
