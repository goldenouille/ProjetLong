package uml;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UMLElementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private UMLDrawingPanel mainpanel;
	private JPanel subPanel;
	
	// Action constants
	public static final int ACTION_NONE = 0;
	public static final int ACTION_ADD = 1;
	public static final int ACTION_REMOVE = 2;
	public static final int ACTION_EDIT = 3;
	public static final int ACTION_DELETE = 4;
	public static final int ACTION_ADD_NEW_ELEMENT = 5;

	private int selectedElementAction;
	private int selectedElementType; //use UMLDrawingPanel element type
	private int selectedElementID;
	
	private Vector<String> classes;
	private Vector<String> properties;
	private Vector<String> methods;
	
	/**
	 * Main constructor, create an UMLElementPanel dedicated button.
	 * 
	 * @param mainpanel
	 *            UMLDrawingPanel
	 */
	public UMLElementPanel(UMLDrawingPanel mainpanel) {
		super();
		this.mainpanel = mainpanel;
		this.setSelectedElementID(0);
		this.setSelectedElementType(UMLDrawingPanel.ELEMENT_NOTYPE);
		this.subPanel=new JPanel();
		this.add(subPanel);
		
		classes = new Vector<String>();
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		this.refresh();
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
		classeNamePanel.add(new UMLElementPanelButton(this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_CLASS, -1, "+", "Ajouter une nouvelle classe"));
		classeNamePanel.add(new JLabel("Classes :"));
		classesPanel.add(classeNamePanel);
		
		for (int i = 0 ; i < classes.size() ; i++) {
			JPanel classesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//classesOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			//classesOnePanel.setLayout(new BoxLayout(classesOnePanel, BoxLayout.PAGE_AXIS));
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_CLASS, i, "+", "Ajouter à la zone de dessin, au clique"));
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_CLASS, i, "-", "Retirer de la zone de dessin"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_CLASS, i, "E", "Editer les proprietes"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_CLASS, i, "D", "Supprimer l'element"));	
			classesOnePanel.add(new JLabel(classes.get(i)));
			
			classesPanel.add(classesOnePanel);
		}
		classesPanel.add(Box.createVerticalGlue());
		subPanel.add(classesPanel);
		subPanel.add(Box.createVerticalGlue());
		
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.PAGE_AXIS));
		
		JPanel propertiesNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		propertiesNamePanel.add(new UMLElementPanelButton (this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_PROPERTY, -1, "+", "Ajouter un nouvel attribut"));
		propertiesNamePanel.add(new JLabel("Attributs :"));
		propertiesPanel.add(propertiesNamePanel);
		for (int i = 0 ; i < properties.size() ; i++) {
			JPanel propertiesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//propertiesOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_PROPERTY, i, "+", "Ajouter à la zone de dessin, au clique"));
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_PROPERTY, i, "-", "Retirer de la zone de dessin"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_PROPERTY, i, "E", "Editer les proprietes"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_PROPERTY, i, "D", "Supprimer l'element"));	
			propertiesOnePanel.add(new JLabel(properties.get(i)));
			
			propertiesPanel.add(propertiesOnePanel);
		}
		propertiesPanel.add(Box.createVerticalGlue());
		subPanel.add(propertiesPanel);
		subPanel.add(Box.createVerticalGlue());
		
		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.PAGE_AXIS));
		JPanel methodNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		methodNamePanel.add(new UMLElementPanelButton (this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_METHOD, -1, "+", "Ajouter une nouvelle methode"));
		methodNamePanel.add(new JLabel("Méthodes :"));
		methodPanel.add(methodNamePanel);
		for (int i = 0 ; i < methods.size() ; i++) {
			JPanel methodsOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//methodsOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_METHOD, i, "+", "Ajouter à la zone de dessin, au clique"));
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_METHOD, i, "-", "Retirer de la zone de dessin"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_METHOD, i, "E", "Editer les proprietes"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_METHOD, i, "D", "Supprimer l'element"));	
			methodsOnePanel.add(new JLabel(methods.get(i)));
			
			methodPanel.add(methodsOnePanel);
		}
		methodPanel.add(Box.createVerticalGlue());
		subPanel.add(methodPanel);
		subPanel.add(Box.createVerticalGlue());

		subPanel.revalidate();
	}

	/**
	 * Add a class to element pool
	 * 
	 * @param class
	 *            class name
	 */
	public void addClass(String c) {
		classes.add(c);
	}

	/**
	 * Remove specified class from element pool
	 * 
	 * @param class
	 *            class name to remove
	 */
	public boolean removeClass(String c) {
		return classes.remove(c);
	}

	/**
	 * Add a property to element pool
	 * 
	 * @param property
	 *            property name
	 */
	public void addProperty(String property) {
		properties.add(property);
	}

	/**
	 * Remove specified property from element pool
	 * 
	 * @param property
	 *            property name to remove
	 */
	public boolean removeProperty(String property) {
		return properties.remove(property);
	}
	
	/**
	 * Add a method to element pool
	 * 
	 * @param method
	 *            method name
	 */
	public void addMethod(String method) {
		methods.add(method);
	}

	/**
	 * Remove specified method from element pool
	 * 
	 * @param method
	 *            method name to remove
	 */
	public boolean removeMethod(String method) {
		return methods.remove(method);
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
	 * @return element type as defined in UMLDrawingPanel
	 */
	public int getSelectedElementType() {
		return selectedElementType;
	}

	/**
	 * Set current selected element type
	 * 
	 * @param type
	 *            type as defined in UMLDrawingPanel
	 */
	public void setSelectedElementType(int selectedElementType) {
		this.selectedElementType = selectedElementType;
	}
	
	/**
	 * Get current selected element id
	 * 
	 * @return element id
	 */
	public int getSelectedElementID() {
		return selectedElementID;
	}
	
	/**
	 * Get current selected element name, from id
	 * 
	 * @return element name
	 */
	public String getSelectedElementName() {
		String elementName = "";
		if (selectedElementType == UMLDrawingPanel.ELEMENT_CLASS) {
			elementName = classes.get(selectedElementID);
		}
		else if (selectedElementType == UMLDrawingPanel.ELEMENT_PROPERTY) {
			elementName = properties.get(selectedElementID);
		}
		else if (selectedElementType == UMLDrawingPanel.ELEMENT_METHOD) {
			elementName = methods.get(selectedElementID);
		}
		return elementName;
	}

	/**
	 * Set current selected element id
	 * 
	 * @param element id
	 */
	public void setSelectedElementID(int selectedElementID) {
		this.selectedElementID = selectedElementID;
	}
	
	/**
	 * Reset current selected element to ACTION_NONE, ELEMENT_NOTYPE and ID 0
	 */
	public void resetSelectedElement() {
		this.selectedElementAction = ACTION_NONE;
		this.selectedElementType = UMLDrawingPanel.ELEMENT_NOTYPE;
		this.selectedElementID = 0;
	}
	
	/**
	 * Perform action in consideration of current selected element action, type and id
	 */
	public void doAction() {
		switch (selectedElementAction) {
		case ACTION_ADD_NEW_ELEMENT:
			//  open editing panel
			mainpanel.doAddingNewElementFromPool(selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_ADD:
			// let UMLDrawingPanel in charge of this action
			break;
		case ACTION_REMOVE:
			mainpanel.doRemovingElementFromDrawingArea(getSelectedElementName(), selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_EDIT:
			mainpanel.doEditingElementFromPool(getSelectedElementName(), selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_DELETE:
			mainpanel.doRemovingElementFromDrawingArea(getSelectedElementName(), selectedElementType);
			if (selectedElementType == UMLDrawingPanel.ELEMENT_CLASS) {
				classes.remove(selectedElementID);
			}
			else if (selectedElementType == UMLDrawingPanel.ELEMENT_PROPERTY) {
				properties.remove(selectedElementID);
			}
			else if (selectedElementType == UMLDrawingPanel.ELEMENT_METHOD) {
				methods.remove(selectedElementID);
			}
			this.resetSelectedElement();
			this.refresh();
			break;
		default:
			this.resetSelectedElement();
			break;
		}
		
		mainpanel.repaint();
	}

}
