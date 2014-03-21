package uml;

import gui.AbstractPanel;
import gui.ClassicGuiController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import model.UMLNature;

/**
 * @author Vincent Roth
 * Panel in charge of class diagram drawing.
 * 
 * It contains a relation toolbar (LinkToolBar) and and element panel (UMLElementPanel)
 * In charge of relation drawing action, in function of toolbar status. 
 */
public class UMLDrawingPanel extends AbstractPanel implements MouseListener, MouseMotionListener {
	
	// Color constant
	public static Color COLOR_DEFAULT = Color.BLACK;
	public static Color COLOR_VALIDATE = Color.GREEN;
	public static Color COLOR_ERROR = Color.RED;
	
	private static final long serialVersionUID = 1L;
	
	private static final int NO_DRAGGED_ELEMENT = -1;
	private static final int NO_ANY_DRAGGED_ELEMENT = -2;
	private static final int DRAGGED_LINK_OFFSET = 1000;
	private static final int NO_CLICKED_CLASS = -1;
	
	private Vector<ClassDrawing> classes;
	private Vector<LinkDrawing> links;
	private Dimension previousMousePos = new Dimension(0, 0);
	private int currentDraggedElement = NO_DRAGGED_ELEMENT;
	private int previousClickedClass = NO_CLICKED_CLASS;
	
	private LinkToolBar toolBar;
	private UMLElementPanel poolPanel;

	// main for testing
	public static void main(final String[] args) {
		JFrame f = new JFrame("Test");
        f.setSize(1280,780);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        UMLDrawingPanel drawingPanel = new UMLDrawingPanel(null);
        drawingPanel.setSize(f.getSize());
        
        f.add(drawingPanel);
        
		drawingPanel.repaint();
		
		f.setVisible(true);
	}
	
	public UMLDrawingPanel(ClassicGuiController controller) {
		super(controller);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setLayout(new BorderLayout());
		
		classes = new Vector<ClassDrawing>();
		links = new Vector<LinkDrawing>();
		
		toolBar = new LinkToolBar(controller);
		this.add(toolBar,BorderLayout.NORTH);
		
		poolPanel = new UMLElementPanel(this, controller);
		this.add(poolPanel,BorderLayout.EAST);
		
		// TEST
/*		this.poolPanel.addClass(0, UMLNature.INTERFACE, "Interface");
		this.poolPanel.addClass(1, UMLNature.CLASS, "Class");
		this.poolPanel.addProperty(2, "myproperty", "int", "+");
		this.poolPanel.addMethod(3, "mymethod", new ArrayList<String>(), "", "+");
		//this.doShowUMLInstanceInRightColor(0);
		//this.doShowUMLInstanceInWrongColor(1);
		poolPanel.refresh();
*/		
/*		LinkEditionPanel linkEdition = new LinkEditionPanel("test1", "", true, "test2", "", true, "");
		JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
				"Nouvelle relation de test1 vers test2", JOptionPane.OK_CANCEL_OPTION);
*/		// END TEST
		
		this.repaint();
	}
	
	/**
	 * Change color of UML instance to validate color in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 */
	public void doShowUMLInstanceInRightColor(Object id) {
		poolPanel.addRightColoredElement(id);
		poolPanel.refresh();
	}
	
	/**
	 * Change color of UML instance to error color in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 */
	public void doShowUMLInstanceInWrongColor(Object id) {
		poolPanel.addWrongColoredElement(id);
		poolPanel.refresh();
	}
	
	/**
	 * Reset color of UML instance to default in element pool
	 * 
	 * @param id
	 *            identifier of the instance to reset
	 */
	public void doResetUMLInstanceColor(Object id) {
		poolPanel.removeColoredElement(id);
		poolPanel.refresh();
	}
	
	/**
	 * Reset color of UML instances to black in element pool
	 */
	public void doResetUMLInstanceColor() {
		poolPanel.removeAllColoredElement();
		poolPanel.refresh();
	}
	
	/**
	 * Change color of UML drawing to error color in drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doShowUMLDrawingInErrorColor(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getInstanceID().equals(id)) {
					classes.get(j).setColored(true);
				}
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containProperty(id)) {
					classes.get(j).addColoredElement(id);
				}
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containMethod(id)) {
					classes.get(j).addColoredElement(id);
				}
			}
		} else if (nature.equals(UMLNature.AGGREGATION)
			|| nature.equals(UMLNature.ASSOCIATION)
			|| nature.equals(UMLNature.COMPOSITION)
			|| nature.equals(UMLNature.DEPENDANCY)
			|| nature.equals(UMLNature.GENERALIZATION)
			|| nature.equals(UMLNature.N_ASSOCIATION)
			|| nature.equals(UMLNature.REALIZATION)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (links.get(j).getInstanceID().equals(id)) {
					links.get(j).setColored(true);
				}
			}
		}
	}
	
	/**
	 * Reset color of UML drawing to red in drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doResetUMLDrawingColor(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getInstanceID().equals(id)) {
					classes.get(j).setColored(false);
				}
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containProperty(id)) {
					classes.get(j).removeColoredElement(id);
				}
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containMethod(id)) {
					classes.get(j).removeColoredElement(id);
				}
			}
		} else if (nature.equals(UMLNature.AGGREGATION)
			|| nature.equals(UMLNature.ASSOCIATION)
			|| nature.equals(UMLNature.COMPOSITION)
			|| nature.equals(UMLNature.DEPENDANCY)
			|| nature.equals(UMLNature.GENERALIZATION)
			|| nature.equals(UMLNature.N_ASSOCIATION)
			|| nature.equals(UMLNature.REALIZATION)) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (links.get(j).getInstanceID().equals(id)) {
					links.get(j).setColored(false);
				}
			}
		}
	}
	
	/**
	 * Reset color of UML drawings to black in drawing area
	 */
	public void doResetUMLDrawingColor() {
		for (int j = 0 ; j < classes.size() ; j++) {
			//System.out.println("doResetColor class " + j);
			classes.get(j).removeAllColoredElement();
		}
		
		for (int j = 0 ; j < links.size() ; j++) {
			links.get(j).setColored(false);
		}
	}
	
	/**
	 * Set number of missing UML drawing to display
	 * If 0, it is not display
	 * 
	 * @param number
	 *            number of missing drawing to display
	 */
	public void setMissingUmlInstance(int number) {
		poolPanel.setMissingUMLDrawing(number);
	}
	
	/**
	 * Get number of UML element drawn and linked
	 * 
	 * @return number
	 */
	public int getUmlDrawingElementNumber() {
		int result = classes.size() + links.size();
		
		for (int j = 0 ; j < classes.size() ; j++) {
			result += classes.get(j).getPropertyNumber() + classes.get(j).getMethodNumber();
		}
		
		return result;
	}
	
	/**
	 * Get number of missing UML element drawn and linked
	 * Relation are not count
	 * 
	 * @return number
	 */
	public int getMissingUmlDrawingElementNumber() {
		int result = poolPanel.getUMLInstanceNumber() - classes.size();
		
		for (int j = 0 ; j < classes.size() ; j++) {
			result -= classes.get(j).getPropertyNumber() + classes.get(j).getMethodNumber();
		}
		
		return result;
	}
	
	/**
	 * Get element name from id
	 * 
	 * @param id
	 *            element id
	 * @param nature
	 *            element nature
	 * @return element name
	 */
	public String getElementName(Object id, Object nature) {
		return poolPanel.getElementName(id, nature);
	}
	
	/**
	 * Add an UML instance to element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doAddElementToPool(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS)) {
			poolPanel.addClass(id, UMLNature.CLASS, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			poolPanel.addClass(id, UMLNature.ABSTRACT_CLASS, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.INTERFACE)) {
			poolPanel.addClass(id, UMLNature.INTERFACE, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			poolPanel.addProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		} else if (nature.equals(UMLNature.METHOD)) {
			poolPanel.addMethod(id, controller.askUmlInstanceName(id), controller.askUmlInstanceParamTypes(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		}
		
		poolPanel.refresh();
	}
	
	/**
	 * Add an UML instance to element pool on element pool request
	 * 
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doAddNewElementFromPool(Object nature) {
		try {
			controller.doShowUmlInstanceCreationPopupWithNoKeyWord(nature);
		} catch (BadLocationException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		poolPanel.refresh();
	}
	
	/**
	 * Ask core to edit an UML instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void askEditElementFromPool(Object id, Object nature) {
		try {
			controller.doShowUmlInstanceEditionPopup(id, nature);
		} catch (BadLocationException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Do edition of an UML instance
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doEditElementFromPool(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.INTERFACE)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			// System.out.println("doEditElement Attribute");
			poolPanel.modifyProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		} else if (nature.equals(UMLNature.METHOD)) {
			// System.out.println("doEditElement Method");
			poolPanel.modifyMethod(id, controller.askUmlInstanceName(id), controller.askUmlInstanceParamTypes(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		}
		
		poolPanel.refresh();
	}
	
	/**
	 * Remove an UML instance from element panel
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void askRemoveElementFromPool(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS)) {
			controller.askDeleteClass(id);
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			controller.askDeleteAbstractClass(id);
		} else if (nature.equals(UMLNature.INTERFACE)) {
			controller.askDeleteInterface(id);
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			controller.askDeleteAttribute(id);
		} else if (nature.equals(UMLNature.METHOD)) {
			controller.askDeleteMethod(id);
		}
	}
	
	/**
	 * Remove an UML instance from element panel
	 * and eventualy from drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doRemoveElementFromPool(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS)) {
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.INTERFACE)) {
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeProperty(id);
		} else if (nature.equals(UMLNature.METHOD)) {
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeMethod(id);
		}
		
		poolPanel.refresh();
	}
	
	/**
	 * Remove ALL UML instance from element panel
	 * and eventualy from drawing area
	 */
	public void doRemoveAllElementFromPool() {
		this.doRemoveAllElementFromDrawingArea();
		
		poolPanel.removeAllElements();
		poolPanel.refresh();
	}
	
	/**
	 * Add an UML element to drawing panel
	 * Position affect X and Y position for class drawing
	 * For attribute and method, position while affect it to class under this position
	 * 		if not class is under, attribute or method is not affected
	 * 
	 * @param id
	 *            identifier of the instance to add
	 * @param nature
	 *            UMLNature of the instance
	 * @param position
	 *            position where to draw element
	 */
	public void doAddElementToDrawingArea(Object id, Object nature, Dimension position) {
		int i = 0;
		boolean find = false;
		
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			boolean classIsNotDrawn = true;
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getInstanceID().equals(id)) {
					classIsNotDrawn = false;
				}
			}
			if (classIsNotDrawn) {
				classes.add(new ClassDrawing(this, id, nature, position.width, position.height));
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			boolean attributeIsNotDrawn = true;
			// check if not already draw
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containProperty(id)) {
					attributeIsNotDrawn = false;
				}
			}
			if (attributeIsNotDrawn) {
				// find which class is under position
				while (i < classes.size() && !find) {
					if (classes.get(i).isUnder(position)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					controller.askLinkAttributeToClass(id, classes.get(i).getInstanceID());
				}
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			boolean methodIsNotDrawn = true;
			// check if not already draw
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).containMethod(id)) {
					methodIsNotDrawn = false;
				}
			}
			if (methodIsNotDrawn) {
				// find which class is under position
				while (i < classes.size() && !find) {
					if (classes.get(i).isUnder(position)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					controller.askLinkMethodToClass(id, classes.get(i).getInstanceID());
				}
			}
		}
	}
	
	/**
	 * Link the given attribute instance to the given class instance
	 * 
	 * @param attributeID
	 *            identifier of the attribute instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void doLinkAttributeToClass(Object attributeID, Object classID) {
		int i = 0;
		boolean find = false;
		
		while (i < classes.size() && !find) {
			if (classes.get(i).getInstanceID().equals(classID)) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			classes.get(i).addProperty(attributeID);
		}
		// System.out.println("doLinkAttributeToClass");
	}
	
	/**
	 * Unlink the given attribute instance to the given class instance
	 * 
	 * @param attributeID
	 *            identifier of the attribute instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void doUnLinkAttributeToClass(Object attributeID, Object classID) {
		int i = 0;
		boolean find = false;
		
		while (i < classes.size() && !find) {
			if (classes.get(i).getInstanceID().equals(classID)) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			classes.get(i).removeProperty(attributeID);
		}

		// System.out.println("doUnLinkAttributeToClass");
	}
	
	/**
	 * Link the given method instance to the given class instance
	 * 
	 * @param methodID
	 *            identifier of the method instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void doLinkMethodToClass(Object methodID, Object classID) {
		int i = 0;
		boolean find = false;
		
		while (i < classes.size() && !find) {
			if (classes.get(i).getInstanceID().equals(classID)) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			classes.get(i).addMethod(methodID);
		}

		// System.out.println("doLinkMethodToClass");
	}
	
	/**
	 * Unlink the given method instance to the given class instance
	 * 
	 * @param methodID
	 *            identifier of the method instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void doUnLinkMethodToClass(Object methodID, Object classID) {
		int i = 0;
		boolean find = false;
		
		while (i < classes.size() && !find) {
			if (classes.get(i).getInstanceID().equals(classID)) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			classes.get(i).removeMethod(methodID);
		}
		// System.out.println("doUnLinkMethodToClass");
	}
	
	/**
	 * Add an UML relation to drawing panel
	 * Ask core about values for classes linked, multiplicity and text
	 * 
	 * @param id
	 *            identifier of the instance to add
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doAddRelationToDrawingArea(Object id, Object nature) {
		ArrayList<ClassDrawing> classesLinked = new ArrayList<ClassDrawing>();
		ArrayList<Object> classesID = controller.askUMLRelationClasses(id);
		
		for (int i = 0 ; i < classesID.size() ; i++) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getInstanceID().equals(classesID.get(i))) {
					classesLinked.add(classes.get(j));
				}
			}
		}
		
		links.add(new LinkDrawing(id, nature, classesLinked));
		links.lastElement().setText(controller.askUMLRelationText(id));
		// limit to necessary relation nature
		if (nature.equals(UMLNature.ASSOCIATION)
				|| nature.equals(UMLNature.AGGREGATION)
				|| nature.equals(UMLNature.COMPOSITION)) {
			links.lastElement().setMultiplicity(controller.askUMLRelationMultiplicity(id));
		}
		
	}
	
	/**
	 * Remove an UML drawing to drawing panel
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doRemoveElementFromDrawingArea(Object id, Object nature) {
		int i = 0;
		boolean find = false;
		
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			for(int j = 0 ; j < links.size() ; j++) {
				if(links.get(j).getMotherClassID().equals(classes.get(j).getInstanceID())
						|| links.get(j).getDaughterClassID().equals(classes.get(j).getInstanceID())) {
					controller.askDeleteRelation(links.get(j).getInstanceID());
					links.remove(j);
					j--;
				}
			}
			while (i < classes.size() && !find) {
				if(id.equals(classes.get(i).getInstanceID())) {
					find = true;
				}
				i++;
			}
			if (find) {
				i--;
				controller.askUnLinkAllElementToClass(id);
				// TODO let core in charge of this ?
				classes.remove(i);
			}
			
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			while (i < classes.size() && !find) {
				if (classes.get(i).containProperty(id)) {
					find = true;
				}
				i++;
			}
			if (find) {
				i--;
				controller.askUnLinkAttributeToClass(id, classes.get(i).getInstanceID());
			}
			
		} else if (nature.equals(UMLNature.METHOD)) {
			while (i < classes.size() && !find) {
				if (classes.get(i).containMethod(id)) {
					find = true;
				}
				i++;
			}
			if (find) {
				i--;
				controller.askUnLinkMethodToClass(id, classes.get(i).getInstanceID());
			}
			
		} else if (nature.equals(UMLNature.AGGREGATION)
				|| nature.equals(UMLNature.ASSOCIATION)
				|| nature.equals(UMLNature.COMPOSITION)
				|| nature.equals(UMLNature.DEPENDANCY)
				|| nature.equals(UMLNature.GENERALIZATION)
				|| nature.equals(UMLNature.N_ASSOCIATION)
				|| nature.equals(UMLNature.REALIZATION)) {
			while (i < links.size() && !find) {
				if(id.equals(links.get(i).getInstanceID())) {
					find = true;
				}
				i++;
			}
			if (find) {
				i--;
				controller.askDeleteRelation(id);
			}
		}
		
		this.repaint();
	}
	
	/**
	 * Remove ALL UML drawing to drawing panel
	 * DO not ask controller to unlink element
	 */
	public void doRemoveAllElementFromDrawingArea() {
		classes.removeAllElements();
		links.removeAllElements();
		this.repaint();
	}
	
	/**
	 * Ask core to create a new relation
	 * 
	 * @param toolBarType
	 *            relation type from LinkToolBar
	 * @param firstClassID
	 *            id of main class
	 * @param secondClassID
	 *            id of second class
	 * @param firstMultiplicity
	 *            multiplicity of main class
	 * @param secondMultiplicity
	 *            multiplicity of second class
	 * @param text
	 *            relation text
	 */
	public void askCreateRelation(int toolBarType, Object firtsClassID, Object secondClassID,
			String firstMultiplicity, String secondMultiplicity, String text) {
		UMLNature nature = null;
		
		if (toolBarType == LinkToolBar.AGGREGATION) {
			nature = UMLNature.AGGREGATION;
		} else if (toolBarType == LinkToolBar.BINARY_ASSOCIATION) {
			nature = UMLNature.ASSOCIATION;
		} else if (toolBarType == LinkToolBar.COMPOSITION) {
			nature = UMLNature.COMPOSITION;
		} else if (toolBarType == LinkToolBar.DEPENDANCY) {
			nature = UMLNature.DEPENDANCY;
		} else if (toolBarType == LinkToolBar.GENERALIZATION) {
			nature = UMLNature.GENERALIZATION;
/*		} else if (toolBarType == LinkToolBar.N_ASSOCIATION) {
			nature = UMLNature.N_ASSOCIATION;
*/		} else if (toolBarType == LinkToolBar.REALIZATION) {
			nature = UMLNature.REALIZATION;
		}
		
		ArrayList<Object> classesID = new ArrayList<Object>();
		classesID.add(firtsClassID);
		classesID.add(secondClassID);
		
		ArrayList<String> multiplicity = new ArrayList<String>();
		multiplicity.add(firstMultiplicity);
		multiplicity.add(secondMultiplicity);
		
		controller.askCreateRelation(nature, classesID, multiplicity, text);
		
		// TEST
/*		int id = 5;
		ArrayList<ClassDrawing> classesLinked = new ArrayList<ClassDrawing>();
		for (int i = 0 ; i < classesID.size() ; i++) {
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getInstanceID().equals(classesID.get(i))) {
					classesLinked.add(classes.get(j));
				}
			}
		}
		
		links.add(new LinkDrawing(id, nature, classesLinked));
		links.lastElement().setMultiplicity(multiplicity);
		links.lastElement().setText(text);
*/	}
	
	/**
	 * Ask core to edit a relation
	 * 
	 * @param id
	 *            relation core id
	 * @param firstMultiplicity
	 *            multiplicity of main class
	 * @param secondMultiplicity
	 *            multiplicity of second class
	 * @param text
	 *            relation text
	 */
	public void askEditRelation(Object id, String firstMultiplicity, String secondMultiplicity, String text) {
		ArrayList<String> multiplicity = new ArrayList<String>();
		multiplicity.add(firstMultiplicity);
		multiplicity.add(secondMultiplicity);
		
		controller.askEditRelation(id, multiplicity, text);
		
		// TEST
/*		int i = 0;
		boolean find = false;
		while (i < links.size() && !find) {
			if(id.equals(links.get(i).getInstanceID())) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			links.get(i).setMultiplicity(multiplicity);
			links.get(i).setText(text);
		}
*/	}
	
	/**
	 * Do edition of a relation
	 * Ask core about new values for multiplicity and text
	 * Use in this.askEditRelation
	 * 
	 * @param id
	 *            relation core id
	 */
	public void doEditRelation(Object id) {
		int i = 0;
		boolean find = false;
		
		while (i < links.size() && !find) {
			if(id.equals(links.get(i).getInstanceID())) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			links.get(i).setText(controller.askUMLRelationText(id));
			// limit to necessary relation nature
			if (links.get(i).getType().equals(UMLNature.ASSOCIATION)
					|| links.get(i).getType().equals(UMLNature.AGGREGATION)
					|| links.get(i).getType().equals(UMLNature.COMPOSITION)) {
				links.get(i).setMultiplicity(controller.askUMLRelationMultiplicity(id));
			}
		}
	}
	
	/**
	 * Delete a relation from drawing panel
	 * 
	 * @param id
	 *            relation core id
	 */
	public void doDeleteRelation(Object id) {
		int i = 0;
		boolean find = false;
		
		while (i < links.size() && !find) {
			if(id.equals(links.get(i).getInstanceID())) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			links.remove(i);
		}
	}
	
	/**
	 * Reverse a relation
	 * 
	 * @param id
	 *            relation core id
	 */
	public void doReverseRelation(Object id) {
		int i = 0;
		boolean find = false;
		
		while (i < links.size() && !find) {
			if(id.equals(links.get(i).getInstanceID())) {
				find = true;
			}
			i++;
		}
		if (find) {
			i--;
			links.get(i).reverseClass();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(COLOR_DEFAULT);
		Graphics2D g2d = (Graphics2D)g;
		
		// Draw classes
		for (int i = 0; i < classes.size() ; i++) {
			classes.get(i).draw(g2d);
		}
		
		// Draw relations
		for (int i = 0; i < links.size() ; i++) {
			links.get(i).draw(g2d);
		}
		
		// Draw a line in case of relation class selection
		if (toolBar.isInLinkRelationState() && previousClickedClass != NO_CLICKED_CLASS) {
			g2d.drawLine(classes.get(previousClickedClass).getX(), classes.get(previousClickedClass).getY(), previousMousePos.width, previousMousePos.height);
		}
		
		// Draw UML element name on Add selection
		if (poolPanel.getSelectedElementAction() == UMLElementPanel.ACTION_ADD) {
			g2d.drawString(getElementName(poolPanel.getSelectedElementID(), poolPanel.getSelectedElementType()), previousMousePos.width, previousMousePos.height);
		}
		
		//System.out.println("Nombre d'element dessinés : " + getUmlDrawingElementNumber());
		//System.out.println("Nombre d'element non dessinés : " + getMissingUmlDrawingElementNumber());
		
		//poolPanel.refresh();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Calculate mouse motion delta
		Dimension mousePos = new Dimension(e.getX(), e.getY());
		Dimension delta = new Dimension(e.getX() - previousMousePos.width, e.getY() - previousMousePos.height);
		
		if (toolBar.getState() == LinkToolBar.NO_LINK) {
			// Select affected element
			if (currentDraggedElement == NO_DRAGGED_ELEMENT) { // No element are currently dragged
				boolean find = false;
				int i = 0;
				while (i < classes.size() && !find) {
					if (classes.get(i).isUnder(mousePos)) {
						find = true;
						currentDraggedElement = i;
						classes.get(i).move(delta);
					}
					i++;
				}
				i = 0;
				while (i < links.size() && !find) {
					if (links.get(i).isUnder(mousePos)) {
						find = true;
						currentDraggedElement = i + DRAGGED_LINK_OFFSET;
						links.get(i).move(mousePos, delta);
					}
					i++;
				}
				if (!find) {
					currentDraggedElement = NO_ANY_DRAGGED_ELEMENT;
				}
			} else if (currentDraggedElement != NO_ANY_DRAGGED_ELEMENT) {
				if (currentDraggedElement <= classes.size()) {
					classes.get(currentDraggedElement).move(delta);
				} else {
					links.get(currentDraggedElement - DRAGGED_LINK_OFFSET).move(mousePos, delta);
				}
			}
			else {
				for (int i = 0 ; i < classes.size() ; i++) {
					classes.get(i).move(delta);
				}
			}
		}
		
		// Memorize mouse position for next iteration
		previousMousePos.width	= e.getX();
		previousMousePos.height	= e.getY();
		
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Memorize mouse position, use as initialization of position before dragging
		previousMousePos.width	= e.getX();
		previousMousePos.height	= e.getY();
		currentDraggedElement = NO_DRAGGED_ELEMENT;
		
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Dimension mousePos = new Dimension(e.getX(), e.getY());
		int i = 0;
		boolean find = false;
		
		if (toolBar.getState() != LinkToolBar.NO_LINK) {
			if (!toolBar.isInLinkRelationState()) {
				while (i < links.size() && !find) {
					if (links.get(i).isUnder(mousePos)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					if (toolBar.getState() == LinkToolBar.LINK_EDITION) {
						// Open Link Edition Panel
						
						// limit to necessary relation nature
						boolean displayMultiplicityField = false;
						if (links.get(i).getType().equals(UMLNature.ASSOCIATION)
								|| links.get(i).getType().equals(UMLNature.AGGREGATION)
								|| links.get(i).getType().equals(UMLNature.COMPOSITION)) {
							displayMultiplicityField = true;
						}
						
						LinkEditionPanel linkEdition = new LinkEditionPanel(
								getElementName(links.get(i).getMotherClassID(), UMLNature.CLASS),
								links.get(i).getMotherMultiplicity(), displayMultiplicityField,
								getElementName(links.get(i).getDaughterClassID(), UMLNature.CLASS),
								links.get(i).getDaughterMultiplicity(), displayMultiplicityField,
								links.get(i).getText());
						
						int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
								"Edition de relation de " + getElementName(links.get(i).getMotherClassID(), UMLNature.CLASS) + " vers " + getElementName(links.get(i).getDaughterClassID(), UMLNature.CLASS), JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							this.askEditRelation(links.get(i).getInstanceID(), linkEdition.getFirstClassMultiplicity(), linkEdition.getSecondClassMultiplicity(), linkEdition.getText());
						}
					}
					else if (toolBar.getState() == LinkToolBar.CHANGE_DIRECTION) {
						controller.askReverseRelation(links.get(i).getInstanceID());
					} else { // toolBar.getState() == LinkToolBar.REMOVE_LINK
						controller.askDeleteRelation(links.get(i).getInstanceID());
					}
				}
				
			}
			else { // create new link
				while (i < classes.size() && !find) {
					if (classes.get(i).isUnder(mousePos)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					if (previousClickedClass == NO_CLICKED_CLASS) {
						previousClickedClass = i;
					} else {
						if (previousClickedClass != i) { // TODO link drawing from one class to the same one is not implemented
							// Link Edition Panel
							
							// limit to necessary relation nature
							boolean displayMultiplicityField = false;
							if (toolBar.getState() == LinkToolBar.BINARY_ASSOCIATION
									|| toolBar.getState() == LinkToolBar.AGGREGATION
									|| toolBar.getState() == LinkToolBar.COMPOSITION) {
								displayMultiplicityField = true;
							}
							
							LinkEditionPanel linkEdition = new LinkEditionPanel(
									getElementName(classes.get(previousClickedClass).getInstanceID(), UMLNature.CLASS),
									"", displayMultiplicityField,
									getElementName(classes.get(i).getInstanceID(), UMLNature.CLASS),
									"", displayMultiplicityField, "");
							
							int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
									"Nouvelle relation de " + getElementName(classes.get(previousClickedClass).getInstanceID(), UMLNature.CLASS)
									+ " vers " + getElementName(classes.get(i).getInstanceID(), UMLNature.CLASS), JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								this.askCreateRelation(toolBar.getState(), classes.get(previousClickedClass).getInstanceID(), classes.get(i).getInstanceID(),
										linkEdition.getFirstClassMultiplicity(), linkEdition.getSecondClassMultiplicity(), linkEdition.getText());
							}
							
							previousClickedClass = NO_CLICKED_CLASS;
						}
					}
				}
			}
		} else {
			// linking variables reset
			previousClickedClass = NO_CLICKED_CLASS;
			
			// on left click
			if (e.getButton() == MouseEvent.BUTTON1) {
				// check for element to add
				if(poolPanel.getSelectedElementAction() == UMLElementPanel.ACTION_ADD) {
					this.doAddElementToDrawingArea(poolPanel.getSelectedElementID(), poolPanel.getSelectedElementType(), mousePos);
					poolPanel.resetSelectedElement();
				}
			}
			
			// on right click
			if (e.getButton() == MouseEvent.BUTTON3) {
				// check for class reducing 
				while (i < classes.size() && !find) {
					if (classes.get(i).isUnder(mousePos)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					classes.get(i).setReduced( !classes.get(i).isReduced() );
				}
			}
		}
		
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		toolBar.setState(LinkToolBar.NO_LINK);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Auto-generated method stub
		
	}

	public void setValidateDiagramButtonEnabled(boolean enabled) {
		toolBar.setValidateDiagramButtonEnabled(enabled);
	}

	public void setValidateAssociationButtonEnabled(boolean enabled) {
		poolPanel.setValidateAssociationButtonEnabled(enabled);
	}

	public void setMissingAssociation(int nb) {
		poolPanel.setMissingAssociation(nb);
	}
}