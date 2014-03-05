package uml;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UMLNature;


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
	private UMLNature selectedElementType;
	private Object selectedElementID;
	
	private Vector<Object> classesID;
	private Vector<String> classes;
	private Vector<Object> propertiesID;
	private Vector<String> properties;
	private Vector<Object> methodsID;
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
		this.resetSelectedElement();
		this.subPanel=new JPanel();
		this.add(subPanel);
		
		classesID = new Vector<Object>();
		classes = new Vector<String>();
		propertiesID = new Vector<Object>();
		properties = new Vector<String>();
		methodsID = new Vector<Object>();
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
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_CLASS, classesID.get(i), "+", "Ajouter à la zone de dessin, au clique"));
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_CLASS, classesID.get(i), "-", "Retirer de la zone de dessin"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_CLASS, classesID.get(i), "E", "Editer les proprietes"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_CLASS, classesID.get(i), "D", "Supprimer l'element"));	
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
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_PROPERTY, propertiesID.get(i), "+", "Ajouter à la zone de dessin, au clique"));
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_PROPERTY, propertiesID.get(i), "-", "Retirer de la zone de dessin"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_PROPERTY, propertiesID.get(i), "E", "Editer les proprietes"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_PROPERTY, propertiesID.get(i), "D", "Supprimer l'element"));	
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
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_METHOD, methodsID.get(i), "+", "Ajouter à la zone de dessin, au clique"));
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_METHOD, methodsID.get(i), "-", "Retirer de la zone de dessin"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_METHOD, methodsID.get(i), "E", "Editer les proprietes"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_METHOD, methodsID.get(i), "D", "Supprimer l'element"));	
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
	 * @param id
	 *            class id
	 * @param name
	 *            class name
	 */
	public void addClass(Object id, String name) {
		classesID.add(id);
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
		return classesID.remove((Integer)id);
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
		return propertiesID.remove((Integer)id);
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
		for (int i = 0 ; i < paramTypes.size() ; i++) {
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
		for (int i = 0 ; i < paramTypes.size() ; i++) {
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
		return methodsID.remove((Integer)id);
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
	 * Get current selected element name, from id
	 * 
	 * @return element name
	 */
	public String getSelectedElementName() {
		String elementName = "";
		if (selectedElementType.equals(UMLNature.CLASS)) {
			elementName = classes.get(selectedElementID);
		}
		else if (selectedElementType.equals(UMLNature.ATTRIBUTE)) {
			elementName = properties.get(selectedElementID);
		}
		else if (selectedElementType.equals(UMLNature.METHOD)) {
			elementName = methods.get(selectedElementID);
		}
		return elementName;
	}

	/**
	 * Set current selected element id
	 * 
	 * @param element id
	 */
	public void setSelectedElementID(Object selectedElementID) {
		this.selectedElementID = selectedElementID;
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
	 * Perform action in consideration of current selected element action, type and id
	 */
	public void doAction() {
		switch (selectedElementAction) {
		case ACTION_ADD_NEW_ELEMENT:
			//  open editing panel
			mainpanel.doAddNewElementFromPool(selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_ADD:
			// let UMLDrawingPanel in charge of this action
			break;
		case ACTION_REMOVE:
			mainpanel.doRemoveElementFromDrawingArea(getSelectedElementName(), selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_EDIT:
			mainpanel.doEditElementFromPool(getSelectedElementID(), selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_DELETE:
			mainpanel.doRemoveElementFromPool(getSelectedElementID(), selectedElementType);
			this.resetSelectedElement();
			break;
		default:
			this.resetSelectedElement();
			break;
		}
		
		mainpanel.repaint();
	}

}
