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


public class UMLDrawingPanel extends AbstractPanel implements MouseListener, MouseMotionListener {
	
	/* Visibility
	 * "+"       Public 
	 * "-"       Private 
	 * "#"       Protected 
	 * "/"       Derived (can be combined with one of the others)
	 * "_"       Static
	 * "~"       Package
	 */
	
	// Color constant
	public static Color COLOR_DEFAULT = Color.BLACK;
	public static Color COLOR_ALT = Color.RED;
	
	private static final long serialVersionUID = 1L;
	
	private static final int NO_DRAGGED_ELEMENT = -1;
	private static final int NO_ANY_DRAGGED_ELEMENT = -2;
	private static final int DRAGGED_LINK_OFFSET = 1000;
	private static final int NO_CLICKED_CLASS = -1;
	
	/*
	public static final int ELEMENT_NOTYPE = 0;
	public static final int ELEMENT_CLASS = 1;
	public static final int ELEMENT_PROPERTY = 2;
	public static final int ELEMENT_METHOD = 3;
	*/
	
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
		
		toolBar = new LinkToolBar();
		this.add(toolBar,BorderLayout.NORTH);
		
		poolPanel = new UMLElementPanel(this);
		this.add(poolPanel,BorderLayout.EAST);
		
		// TEST
		/*
		this.poolPanel.addClass(0, UMLNature.INTERFACE, "Interface");
		this.poolPanel.addClass(1, UMLNature.CLASS, "Class");
		this.poolPanel.addProperty(2, "myproperty", "int", "+");
		this.poolPanel.addMethod(3, "mymethod", new ArrayList<String>(), "", "+");
		//this.doShowUMLInstanceInRed(1);
		poolPanel.refresh();
		*/
		// END TEST
		
		this.repaint();
	}
	
	/**
	 * Change color of UML instance to red in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 */
	public void doShowUMLInstanceInRed(Object id) {
		poolPanel.addColoredElement(id);
	}
	
	/**
	 * Reset color of UML instances to black in element pool
	 */
	public void doResetUMLInstanceColor() {
		poolPanel.removeAllColoredElement();
	}
	
	/**
	 * Change color of UML drawing to red in drawing area
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @param nature
	 *            UMLNature of the instance
	 */
	public void doShowUMLDrawingInRed(Object id, Object nature) {
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
	 * Reset color of UML drawings to black in drawing area
	 */
	public void doResetUMLDrawingColor() {
		for (int j = 0 ; j < classes.size() ; j++) {
			classes.get(j).removeAllColoredElement();
		}
		
		for (int j = 0 ; j < classes.size() ; j++) {
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
	 * Sends to the controller the user's request to validate his Uml diagram
	 */
	public void askValidateDiagram() {
		controller.askValidateDiagram();
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
		// TODO
		if (nature.equals(UMLNature.CLASS)) {
			//poolPanel.addClass(id, UMLNature.CLASS, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			//poolPanel.addClass(id, UMLNature.ABSTRACT_CLASS, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.INTERFACE)) {
			//poolPanel.addClass(id, UMLNature.INTERFACE, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			//poolPanel.addProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		} else if (nature.equals(UMLNature.METHOD)) {
			//poolPanel.addMethod(id, controller.askUmlInstanceName(id), controller.askUmlInstanceParamTypes(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		}
		
		poolPanel.refresh();
	}
	
	public void doEditElementFromPool(Object id, Object nature) {
		try {
			controller.doShowUmlInstanceEditionPopup(id, nature);
		} catch (BadLocationException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		if (nature.equals(UMLNature.CLASS)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.INTERFACE)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			poolPanel.modifyProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		} else if (nature.equals(UMLNature.METHOD)) {
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
	public void doRemoveElementFromPool(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS)) {
			controller.askDeleteClass(id);
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			controller.askDeleteAbstractClass(id);
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.INTERFACE)) {
			controller.askDeleteInterface(id);
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeClass(id);
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			controller.askDeleteAttribute(id);
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeProperty(id);
		} else if (nature.equals(UMLNature.METHOD)) {
			controller.askDeleteMethod(id);
			this.doRemoveElementFromDrawingArea(id, nature);
			poolPanel.removeMethod(id);
		}
		
		poolPanel.refresh();
	}
	
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
					// Interface cannot have attribute
					if (classes.get(i).getClasstype().equals(UMLNature.CLASS) || classes.get(i).getClasstype().equals(UMLNature.ABSTRACT_CLASS)) {
						controller.askLinkAttributeToClass(id, classes.get(i).getInstanceID());
						classes.get(i).addProperty(id);
					}
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
					classes.get(i).addMethod(id);
				}
			}
		}
	}
	
	public void doAddRelationToDrawingArea(Object id, Object nature) {
		// TODO
	}
	
	public void doRemoveElementFromDrawingArea(Object id, Object nature) {
		int i = 0;
		boolean find = false;
		
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			for(int j = 0 ; j < links.size() ; j++) {
				if(links.get(j).getMotherClassID().equals(classes.get(j).getInstanceID())
						|| links.get(j).getDaughterClassID().equals(classes.get(j).getInstanceID())) {
					// TODO delete links
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
				classes.get(i).removeProperty(id);
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
				classes.get(i).removeMethod(id);
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
				// TODO controller.askDeleteRelation(id);
				links.remove(i);
			}
		}
	}
	
	// TODO
	public void askCreateRelation() { // and other properties Text, Multiplicities
		
	}
	
	// TODO
	public void askEditRelation(Object id) { // and other properties Text, Multiplicities
		
	}
	
	// TODO
	public void askDeleteRelation(Object id) {
		
	}
	
	// TODO
	public void askRevertLink(Object id) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(COLOR_DEFAULT);
		Graphics2D g2d = (Graphics2D)g;
		
		for (int i = 0; i < classes.size() ; i++) {
			classes.get(i).draw(g2d);
		}
		
		for (int i = 0; i < links.size() ; i++) {
			links.get(i).draw(g2d);
		}
		
		if (toolBar.isInLinkRelationState() && previousClickedClass != NO_CLICKED_CLASS) {
			g2d.drawLine(classes.get(previousClickedClass).getX(), classes.get(previousClickedClass).getY(), previousMousePos.width, previousMousePos.height);
		}
		
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
						// TODO askEditRelation

						// Open Link Edition Panel
						LinkEditionPanel linkEdition = new LinkEditionPanel(
								getElementName(links.get(i).getMotherClassID(), UMLNature.CLASS),
								links.get(i).getMotherMultiplicity(),
								getElementName(links.get(i).getDaughterClassID(), UMLNature.CLASS),
								links.get(i).getDaughterMultiplicity(),
								links.get(i).getText());
						
						int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
								"Edition de relation de " + getElementName(links.get(i).getMotherClassID(), UMLNature.CLASS) + " vers " + getElementName(links.get(i).getDaughterClassID(), UMLNature.CLASS), JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							links.get(i).setMotherMultiplicity(linkEdition.getMotherMultiplicity());
							links.get(i).setDaughterMultiplicity(linkEdition.getDaughterMultiplicity());
							links.get(i).setText(linkEdition.getText());
						}
					}
					else if (toolBar.getState() == LinkToolBar.CHANGE_DIRECTION) {
						links.get(i).invertClass();
					} else { // toolBar.getState() == LinkToolBar.REMOVE_LINK
						links.remove(i);
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
						// TODO askCreateRelation
						//links.add(new LinkDrawing(classes.get(previousClickedClass), classes.get(i), toolBar.getState()));
						
						// Link Edition Panel
						LinkEditionPanel linkEdition = new LinkEditionPanel(
								getElementName(links.lastElement().getMotherClassID(), UMLNature.CLASS),
								links.lastElement().getMotherMultiplicity(),
								getElementName(links.lastElement().getDaughterClassID(), UMLNature.CLASS),
								links.lastElement().getDaughterMultiplicity(),
								links.lastElement().getText());
						
						int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
								"Nouvelle relation de " + getElementName(links.lastElement().getMotherClassID(), UMLNature.CLASS) + " vers " + getElementName(links.lastElement().getDaughterClassID(), UMLNature.CLASS), JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							links.lastElement().setMotherMultiplicity(linkEdition.getMotherMultiplicity());
							links.lastElement().setDaughterMultiplicity(linkEdition.getDaughterMultiplicity());
							links.lastElement().setText(linkEdition.getText());
						}
						
						previousClickedClass = NO_CLICKED_CLASS;
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
		poolPanel.setValidateDiagramButtonEnabled(enabled);
	}
}