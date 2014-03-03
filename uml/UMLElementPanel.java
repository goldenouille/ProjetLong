package uml;

import java.awt.FlowLayout;
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
	private UMLDrawingPanel mainpanel;
	private JPanel subPanel;
	
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
	
	public void refresh() {
		// TODO why does it is not visible on refresh done by user action !
		subPanel.removeAll();
		subPanel.revalidate();
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
		
		JPanel classesPanel = new JPanel();
		classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.PAGE_AXIS));
		
		JPanel classeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		classeNamePanel.add(new UMLElementPanelButton (this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_CLASS, -1, "+", "Add new class"));
		classeNamePanel.add(new JLabel("Classes :"));
		classesPanel.add(classeNamePanel);
		
		for (int i = 0 ; i < classes.size() ; i++) {
			JPanel classesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//classesOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			//classesOnePanel.setLayout(new BoxLayout(classesOnePanel, BoxLayout.PAGE_AXIS));
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_CLASS, i, "+", "Add to drawing panel on click"));
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_CLASS, i, "-", "Remove to drawing panel"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_CLASS, i, "E", "Edit element properties"));	
		    classesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_CLASS, i, "D", "Delete element"));	
			classesOnePanel.add(new JLabel(classes.get(i)));
			
			classesPanel.add(classesOnePanel);
		}
		classesPanel.add(Box.createVerticalGlue());
		subPanel.add(classesPanel);
		subPanel.add(Box.createVerticalGlue());
		
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.PAGE_AXIS));
		
		JPanel propertiesNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		propertiesNamePanel.add(new UMLElementPanelButton (this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_PROPERTY, -1, "+", "Add new property"));
		propertiesNamePanel.add(new JLabel("Attributs :"));
		propertiesPanel.add(propertiesNamePanel);
		for (int i = 0 ; i < properties.size() ; i++) {
			JPanel propertiesOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//propertiesOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_PROPERTY, i, "+", "Add to drawing panel on click"));
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_PROPERTY, i, "-", "Remove to drawing panel"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_PROPERTY, i, "E", "Edit element properties"));	
			propertiesOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_PROPERTY, i, "D", "Delete element"));	
			propertiesOnePanel.add(new JLabel(properties.get(i)));
			
			propertiesPanel.add(propertiesOnePanel);
		}
		propertiesPanel.add(Box.createVerticalGlue());
		subPanel.add(propertiesPanel);
		subPanel.add(Box.createVerticalGlue());
		
		JPanel methodPanel = new JPanel();
		methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.PAGE_AXIS));
		JPanel methodNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		methodNamePanel.add(new UMLElementPanelButton (this, ACTION_ADD_NEW_ELEMENT, UMLDrawingPanel.ELEMENT_METHOD, -1, "+", "Add new method"));
		methodNamePanel.add(new JLabel("Méthodes :"));
		methodPanel.add(methodNamePanel);
		for (int i = 0 ; i < methods.size() ; i++) {
			JPanel methodsOnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			//methodsOnePanel.setPreferredSize(new Dimension(this.getSize().width, 30));
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_ADD, UMLDrawingPanel.ELEMENT_METHOD, i, "+", "Add to drawing panel on click"));
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_REMOVE, UMLDrawingPanel.ELEMENT_METHOD, i, "-", "Remove to drawing panel"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_EDIT, UMLDrawingPanel.ELEMENT_METHOD, i, "E", "Edit element properties"));	
			methodsOnePanel.add(new UMLElementPanelButton (this, ACTION_DELETE, UMLDrawingPanel.ELEMENT_METHOD, i, "D", "Delete element"));	
			methodsOnePanel.add(new JLabel(methods.get(i)));
			
			methodPanel.add(methodsOnePanel);
		}
		methodPanel.add(Box.createVerticalGlue());
		subPanel.add(methodPanel);
		subPanel.add(Box.createVerticalGlue());

		subPanel.revalidate();
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
	
	public int getSelectedElementAction() {
		return selectedElementAction;
	}

	public void setSelectedElementAction(int selectedElementAction) {
		this.selectedElementAction = selectedElementAction;
	}

	public int getSelectedElementType() {
		return selectedElementType;
	}

	public void setSelectedElementType(int selectedElementType) {
		this.selectedElementType = selectedElementType;
	}
	
	public int getSelectedElementID() {
		return selectedElementID;
	}
	
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

	public void setSelectedElementID(int selectedElementID) {
		this.selectedElementID = selectedElementID;
	}
	
	public void resetSelectedElement() {
		this.selectedElementAction = ACTION_NONE;
		this.selectedElementType = UMLDrawingPanel.ELEMENT_NOTYPE;
		this.selectedElementID = 0;
	}
	
	public void doAction() {
		
		// TODO
		switch (selectedElementAction) {
		case ACTION_ADD_NEW_ELEMENT:
			// TODO  open editing panel
			break;
		case ACTION_ADD:
			// let UMLDrawingPanel in charge of this action
			// TODO
			break;
		case ACTION_REMOVE:
			// TODO
			mainpanel.doRemovingElementFromDrawingArea(getSelectedElementName(), selectedElementType);
			this.resetSelectedElement();
			break;
		case ACTION_EDIT:
			// TODO open editing panel
			break;
		case ACTION_DELETE:
			// TODO
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
