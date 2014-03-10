package uml;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.UMLNature;

public class UMLElementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private UMLDrawingPanel mainPanel;
	private JPanel subPanel;
	private JButton validationButton;
	private JLabel missingUMLDrawingLabel;

	// Action constants
	public static final int ACTION_NONE = 0;
	public static final int ACTION_ADD = 1;
	public static final int ACTION_REMOVE = 2;
	public static final int ACTION_EDIT = 3;
	public static final int ACTION_DELETE = 4;
	public static final int ACTION_ADD_NEW_ELEMENT = 5;

	private int selectedElementAction;
	private UMLNature selectedElementType;
	private Object selectedElementID;

	private Vector<Object> classesID;
	private Vector<UMLNature> classesNature;
	private Vector<String> classes;
	private Vector<Object> propertiesID;
	private Vector<String> properties;
	private Vector<Object> methodsID;
	private Vector<String> methods;

	private Vector<Object> rightColoredID;
	private Vector<Object> wrongColoredID;
	private int missingUMLDrawing;

	// icons
	private ImageIcon plus = new ImageIcon("images/plus.png");
	private ImageIcon minus = new ImageIcon("images/minus.png");
	private ImageIcon bin = new ImageIcon("images/bin.png");

	/**
	 * Main constructor, create an UMLElementPanel dedicated button.
	 * 
	 * @param mainPanel
	 *            UMLDrawingPanel
	 */
	public UMLElementPanel(UMLDrawingPanel mainPanel) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		
		this.mainPanel = mainPanel;
		this.resetSelectedElement();
		this.subPanel = new JPanel();
		this.add(new JScrollPane(subPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		classesID = new Vector<Object>();
		classesNature = new Vector<UMLNature>();
		classes = new Vector<String>();
		propertiesID = new Vector<Object>();
		properties = new Vector<String>();
		methodsID = new Vector<Object>();
		methods = new Vector<String>();

		rightColoredID = new Vector<Object>();
		wrongColoredID = new Vector<Object>();
		missingUMLDrawing = 0;

		this.refresh();
		this.add(Box.createRigidArea(new Dimension(140, 20)));

		validationButton = new JButton("Valider diagramme");
		validationButton.setToolTipText("Valider le diagramme UML");
		validationButton.setAlignmentX(CENTER_ALIGNMENT);
		validationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getmainPanel().askValidateDiagram();
			}
		});
		this.add(validationButton);
		this.add(Box.createRigidArea(new Dimension(0, 20)));

		missingUMLDrawingLabel = new JLabel("");
		missingUMLDrawingLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(missingUMLDrawingLabel);

		this.add(Box.createRigidArea(new Dimension(0, 20)));
	}

	/**
	 * Refresh panel components
	 */
	public void refresh() {
		subPanel.removeAll();
		subPanel.revalidate();

		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));

		JPanel classesPanel = new JPanel();
		classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.PAGE_AXIS));

		JPanel classeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		UMLElementPanelButton newClass = new UMLElementPanelButton(this, ACTION_ADD_NEW_ELEMENT, UMLNature.CLASS, null, "+", "Ajouter une nouvelle classe", plus);
		newClass.setPreferredSize(new Dimension(20, 20));
		classeNamePanel.add(newClass);
		classeNamePanel.add(new JLabel("Classes :"));
		classesPanel.add(classeNamePanel);

		for (int i = 0; i < classes.size(); i++) {
			JPanel classesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			UMLElementPanelButton addClass = new UMLElementPanelButton(this, ACTION_ADD, classesNature.get(i), classesID.get(i), "+",
					"Ajouter à la zone de dessin, au clique", plus);
			addClass.setPreferredSize(new Dimension(20, 20));
			classesOnePanel.add(addClass);

			UMLElementPanelButton remClass = new UMLElementPanelButton(this, ACTION_REMOVE, classesNature.get(i), classesID.get(i), "-", "Retirer de la zone de dessin",minus);
			remClass.setPreferredSize(new Dimension(20, 20));
			classesOnePanel.add(remClass);
			
			UMLElementListenedLabel label = new UMLElementListenedLabel(this, ACTION_EDIT, classesNature.get(i), classesID.get(i), classes.get(i),
					"Editer les proprietes");
			if (rightColoredID.contains(classesID.get(i))) {
				// System.out.println("class " + i + " is paint in right color");
				label.setForeground(UMLDrawingPanel.COLOR_VALIDATE);
			} else if (wrongColoredID.contains(classesID.get(i))) {
				// System.out.println("class " + i + " is paint in wrong color");
				label.setForeground(UMLDrawingPanel.COLOR_ERROR);
			}
			classesOnePanel.add(label);
			
			UMLElementPanelButton binClass = new UMLElementPanelButton(this, ACTION_DELETE, classesNature.get(i), classesID.get(i), "D", "Supprimer l'element", bin);
			binClass.setPreferredSize(new Dimension(20, 20));
			classesOnePanel.add(binClass);

			classesPanel.add(classesOnePanel);
		}
		classesPanel.add(Box.createVerticalGlue());
		subPanel.add(classesPanel);
		subPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		subPanel.add(Box.createVerticalGlue());

		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.PAGE_AXIS));

		JPanel propertiesNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		UMLElementPanelButton newAtt = new UMLElementPanelButton(this, ACTION_ADD_NEW_ELEMENT, UMLNature.ATTRIBUTE, null, "+", "Ajouter un nouvel attribut", plus);
		newAtt.setPreferredSize(new Dimension(20, 20));
		propertiesNamePanel.add(newAtt);
		propertiesNamePanel.add(new JLabel("Attributs :"));
		propertiesPanel.add(propertiesNamePanel);
		for (int i = 0; i < properties.size(); i++) {
			JPanel propertiesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			UMLElementPanelButton addAtt = new UMLElementPanelButton(this, ACTION_ADD, UMLNature.ATTRIBUTE, propertiesID.get(i), "+",
					"Ajouter à la zone de dessin, au clique", plus);
			addAtt.setPreferredSize(new Dimension(20, 20));
			propertiesOnePanel.add(addAtt);
			
			UMLElementPanelButton remAtt = new UMLElementPanelButton(this, ACTION_REMOVE, UMLNature.ATTRIBUTE, propertiesID.get(i), "-",
					"Retirer de la zone de dessin", minus);
			remAtt.setPreferredSize(new Dimension(20, 20));
			propertiesOnePanel.add(remAtt);
			
			JLabel label = new UMLElementListenedLabel(this, ACTION_EDIT, UMLNature.ATTRIBUTE, propertiesID.get(i), properties.get(i), "Editer les proprietes");
			if (rightColoredID.contains(propertiesID.get(i))) {
				// System.out.println("attribute " + i + " is paint in right color");
				label.setForeground(UMLDrawingPanel.COLOR_VALIDATE);
			} else if (wrongColoredID.contains(propertiesID.get(i))) {
				// System.out.println("attribute " + i + " is paint in wrong color");
				label.setForeground(UMLDrawingPanel.COLOR_ERROR);
			}
			propertiesOnePanel.add(label);
			
			UMLElementPanelButton binAtt = new UMLElementPanelButton(this, ACTION_DELETE, UMLNature.ATTRIBUTE, propertiesID.get(i), "D", "Supprimer l'element", bin);
			binAtt.setPreferredSize(new Dimension(20, 20));
			propertiesOnePanel.add(binAtt);

			propertiesPanel.add(propertiesOnePanel);
		}
		propertiesPanel.add(Box.createVerticalGlue());
		subPanel.add(propertiesPanel);
		subPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		subPanel.add(Box.createVerticalGlue());

		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.PAGE_AXIS));
		JPanel methodNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		UMLElementPanelButton newMethod = new UMLElementPanelButton(this, ACTION_ADD_NEW_ELEMENT, UMLNature.METHOD, null, "+", "Ajouter une nouvelle methode", plus);
		newMethod.setPreferredSize(new Dimension(20, 20));
		methodNamePanel.add(newMethod);
		methodNamePanel.add(new JLabel("Méthodes :"));
		methodPanel.add(methodNamePanel);
		for (int i = 0; i < methods.size(); i++) {
			JPanel methodsOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			UMLElementPanelButton addMethod = new UMLElementPanelButton(this, ACTION_ADD, UMLNature.METHOD, methodsID.get(i), "+", "Ajouter à la zone de dessin, au clique",
					plus);
			addMethod.setPreferredSize(new Dimension(20, 20));
			methodsOnePanel.add(addMethod);
			
			UMLElementPanelButton remMethod = new UMLElementPanelButton(this, ACTION_REMOVE, UMLNature.METHOD, methodsID.get(i), "-", "Retirer de la zone de dessin", minus);
			remMethod.setPreferredSize(new Dimension(20, 20));
			methodsOnePanel.add(remMethod);
			
			JLabel label = new UMLElementListenedLabel(this, ACTION_EDIT, UMLNature.METHOD, methodsID.get(i), methods.get(i), "Editer les proprietes");
			if (rightColoredID.contains(methodsID.get(i))) {
				// System.out.println("method " + i + " is paint in right color");
				label.setForeground(UMLDrawingPanel.COLOR_VALIDATE);
			} else if (wrongColoredID.contains(methodsID.get(i))) {
				// System.out.println("method " + i + " is paint in wrong color");
				label.setForeground(UMLDrawingPanel.COLOR_ERROR);
			}
			methodsOnePanel.add(label);
			
			UMLElementPanelButton binMethod = new UMLElementPanelButton(this, ACTION_DELETE, UMLNature.METHOD, methodsID.get(i), "D", "Supprimer l'element", bin);
			binMethod.setPreferredSize(new Dimension(20, 20));
			methodsOnePanel.add(binMethod);

			methodPanel.add(methodsOnePanel);
		}
		methodPanel.add(Box.createVerticalGlue());
		subPanel.add(methodPanel);
		subPanel.add(Box.createVerticalGlue());

		subPanel.revalidate();
	}

	/**
	 * Get UMLDrawingPanel
	 * 
	 * @return UMLDrawingPanel
	 */
	public UMLDrawingPanel getmainPanel() {
		return mainPanel;
	}

	/**
	 * Add a class to element pool
	 * 
	 * @param id
	 *            class id
	 * @param name
	 *            class name
	 */
	public void addClass(Object id, UMLNature nature, String name) {
		classesID.add(id);
		classesNature.add(nature);
		classes.add(name);
	}

	/**
	 * Modify a class from element pool
	 * 
	 * @param id
	 *            class id
	 * @param name
	 *            class name
	 */
	public void modifyClass(Object id, String name) {
		classes.set(classesID.indexOf(id), name);
	}

	/**
	 * Remove specified class from element pool
	 * 
	 * @param id
	 *            class id to remove
	 */
	public boolean removeClass(Object id) {
		classes.remove(classesID.indexOf(id));
		classesNature.remove(classesID.indexOf(id));
		return classesID.remove(id);
	}

	/**
	 * Return uml class nature
	 * 
	 * @param id
	 *            class id to remove
	 * @return UMLNature
	 */
	public UMLNature getClassNature(Object id) {
		return classesNature.get(classesID.indexOf(id));
	}

	/**
	 * Add a property to element pool
	 * 
	 * @param id
	 *            property id
	 * @param name
	 *            property name
	 * @param type
	 *            property type
	 * @param visibility
	 *            property visibility
	 */
	public void addProperty(Object id, String name, String type, String visibility) {
		propertiesID.add(id);
		properties.add(visibility + " " + type + " " + name);
	}

	/**
	 * Modify a property from element pool
	 * 
	 * @param id
	 *            property id
	 * @param name
	 *            property name
	 * @param type
	 *            property type
	 * @param visibility
	 *            property visibility
	 */
	public void modifyProperty(Object id, String name, String type, String visibility) {
		properties.set(propertiesID.indexOf(id), (visibility + " " + type + " " + name));
	}

	/**
	 * Remove specified property from element pool
	 * 
	 * @param id
	 *            property id to remove
	 */
	public boolean removeProperty(Object id) {
		properties.remove(propertiesID.indexOf(id));
		return propertiesID.remove(id);
	}

	/**
	 * Add a method to element pool
	 * 
	 * @param id
	 *            method id
	 * @param name
	 *            method name
	 * @param paramTypes
	 *            method parameter types
	 * @param type
	 *            method return type
	 * @param visibility
	 *            method visibility
	 */
	public void modifyMethod(Object id, String name, ArrayList<String> paramTypes, String type, String visibility) {
		String method = visibility + " " + type + " " + name + "(";
		for (int i = 0; i < paramTypes.size(); i++) {
			method += paramTypes.get(i);
			if (i < paramTypes.size() - 1) {
				method += ", ";
			}
		}
		method += ")";

		methods.set(methodsID.indexOf(id), method);
	}

	/**
	 * Modify a method from element pool
	 * 
	 * @param id
	 *            method id
	 * @param name
	 *            method name
	 * @param paramTypes
	 *            method parameter types
	 * @param type
	 *            method return type
	 * @param visibility
	 *            method visibility
	 */
	public void addMethod(Object id, String name, ArrayList<String> paramTypes, String type, String visibility) {
		String method = visibility + " " + type + " " + name + "(";
		for (int i = 0; i < paramTypes.size(); i++) {
			method += paramTypes.get(i);
			if (i < paramTypes.size() - 1) {
				method += ", ";
			}
		}
		method += ")";

		methodsID.add(id);
		methods.add(method);
	}

	/**
	 * Remove specified method from element pool
	 * 
	 * @param method
	 *            method id to remove
	 */
	public boolean removeMethod(Object id) {
		methods.remove(methodsID.indexOf(id));
		return methodsID.remove(id);
	}

	/**
	 * Get element name
	 * 
	 * @param id
	 *            element id
	 * @param nature
	 *            element nature
	 * @return element name
	 */
	public String getElementName(Object id, Object nature) {
		String elementName = "";
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			elementName = classes.get(classesID.indexOf(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			elementName = properties.get(propertiesID.indexOf(id));
		} else if (nature.equals(UMLNature.METHOD)) {
			elementName = methods.get(methodsID.indexOf(id));
		}
		return elementName;
	}

	/**
	 * Get current selected element action
	 * 
	 * @return element action as defined in UMLElementPanel
	 */
	public int getSelectedElementAction() {
		return selectedElementAction;
	}

	/**
	 * Set current selected element action
	 * 
	 * @param action
	 *            action as defined in UMLElementPanel
	 */
	public void setSelectedElementAction(int selectedElementAction) {
		this.selectedElementAction = selectedElementAction;
	}

	/**
	 * Get current selected element type
	 * 
	 * @return UMLNature
	 */
	public UMLNature getSelectedElementType() {
		return selectedElementType;
	}

	/**
	 * Set current selected element type
	 * 
	 * @param type
	 *            UMLNature
	 */
	public void setSelectedElementType(UMLNature selectedElementType) {
		this.selectedElementType = selectedElementType;
	}

	/**
	 * Get current selected element id
	 * 
	 * @return element id
	 */
	public Object getSelectedElementID() {
		return selectedElementID;
	}

	/**
	 * Set current selected element id
	 * 
	 * @param element
	 *            id
	 */
	public void setSelectedElementID(Object selectedElementID) {
		this.selectedElementID = selectedElementID;
	}

	/**
	 * Get current selected element name, from id
	 * 
	 * @return element name
	 */
	public String getSelectedElementName() {
		return getElementName(selectedElementID, selectedElementType);
	}

	/**
	 * Reset current selected element to ACTION_NONE, ELEMENT_NOTYPE and ID 0
	 */
	public void resetSelectedElement() {
		this.selectedElementAction = ACTION_NONE;
		this.selectedElementType = null;
		this.selectedElementID = null;
	}

	/**
	 * Perform action in consideration of current selected element action, type
	 * and id
	 */
	public void doAction() {
		switch (selectedElementAction) {
		case ACTION_ADD_NEW_ELEMENT:
			// open editing panel
			boolean actionCanceled = false;
			if (selectedElementType.equals(UMLNature.CLASS)) {
				String[] options = new String[] {"Classe", "Classe abstraite", "Interface", "Annuler"};
				int result = JOptionPane.showOptionDialog(null, "De quel type de classe s'agit-il ?", "Type de classe", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
				switch (result) {
				case 0:
					selectedElementType = UMLNature.CLASS;
					// System.out.println("ElementPanel : new class");
					break;
				case 1:
					selectedElementType = UMLNature.ABSTRACT_CLASS;
					// System.out.println("ElementPanel : new abstract class");
					break;
				case 2:
					selectedElementType = UMLNature.INTERFACE;
					// System.out.println("ElementPanel : new interface");
					break;
				default:
					actionCanceled = true;
					break;
				}}
			if (!actionCanceled) {
				mainPanel.doAddNewElementFromPool(selectedElementType);
			}
			this.resetSelectedElement();
			break;
		case ACTION_ADD:
			// let UMLDrawingPanel in charge of this action
			break;
		case ACTION_REMOVE:
			mainPanel.doRemoveElementFromDrawingArea(selectedElementID, selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_EDIT:
			mainPanel.askEditElementFromPool(selectedElementID, selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_DELETE:
			mainPanel.askRemoveElementFromPool(selectedElementID, selectedElementType);
			this.resetSelectedElement();
			break;
		default:
			this.resetSelectedElement();
			break;
		}

		mainPanel.repaint();
	}
	
	/**
	 * Add an element to wrong coloring
	 * 
	 * @param id
	 *            element id
	 */
	public void addRightColoredElement(Object id) {
		if (wrongColoredID.contains(id)) {
			wrongColoredID.remove(id);
		}
		rightColoredID.add(id);
		
		// System.out.println("some element will be paint in green");
	}

	/**
	 * Add an element to wrong coloring
	 * 
	 * @param id
	 *            element id
	 */
	public void addWrongColoredElement(Object id) {
		if (rightColoredID.contains(id)) {
			rightColoredID.remove(id);
		}
		wrongColoredID.add(id);
		
		// System.out.println("some element will be paint in red");
	}

	/**
	 * Remove an element from coloring
	 * 
	 * @param id
	 *            element id
	 */
	public void removeColoredElement(Object id) {
		rightColoredID.remove(id);
		wrongColoredID.remove(id);
		
		// System.out.println("some element will no more be paint in red");
	}

	/**
	 * Remove all element from coloring
	 */
	public void removeAllColoredElement() {
		rightColoredID.removeAllElements();
		wrongColoredID.removeAllElements();
		
		// System.out.println("all element will no more be paint in color");
	}
	
	/**
	 * Get number for UML element in panel
	 * 
	 * @return number
	 */
	public int getUMLInstanceNumber() {
		return (classesID.size() + methodsID.size() + propertiesID.size());
	}

	/**
	 * Get displayed number for missing UML drawing
	 * 
	 * @return number
	 */
	public int getMissingUMLDrawing() {
		return missingUMLDrawing;
	}

	/**
	 * Set number to display for missing UML drawing
	 * 
	 * @param number
	 *            number to display
	 */
	public void setMissingUMLDrawing(int number) {
		this.missingUMLDrawing = number;

		if (missingUMLDrawing > 0) {
			missingUMLDrawingLabel.setText("Objets non traces : " + missingUMLDrawing);
		} else {
			missingUMLDrawingLabel.setText("");
		}
	}

	/**
	 * Enable validate diagram button
	 * 
	 * @param enabled
	 *            enable button
	 */
	public void setValidateDiagramButtonEnabled(boolean enabled) {
		validationButton.setEnabled(enabled);
	}
}
