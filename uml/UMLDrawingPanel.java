package uml;

import gui.AbstractPanel;
import gui.ClassicGuiController;

import java.awt.BorderLayout;
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

	// TODO main for testing
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
		
		// TODO TEST to remove
		//classes.add(new ClassDrawing("Class1", 10, 30));
		//classes.add(new ClassDrawing("Class2", 200, 50));
		//classes.add(new ClassDrawing("Class3", 200, 150));
		//links.add(new LinkDrawing(classes.get(0), classes.get(1), LinkDrawing.DEPENDANCY));
		//links.add(new LinkDrawing(classes.get(1), classes.get(2)));
		
		/*classes.get(0).setClasstype("<<Interface>>");
		classes.get(0).setName("Interface1");
		classes.get(0).addProperty("+ property1");
		classes.get(0).addProperty("+ property2");
		classes.get(0).removeProperty("+ property1");
		classes.get(0).addProperty(" - property3");
		classes.get(0).addMethod(" - method1");
		classes.get(0).addMethod(" - method2");
		*/
		/*classes.get(1).addProperty(" - myproperty");
		classes.get(1).addMethod("+ mymethod");
		*/
		/*links.get(0).setMotherMultiplicity("1..n");
		links.get(0).setDaughterMultiplicity("O..n");
		links.get(0).setText("testing");
		*/
		
		this.poolPanel.addClass(0, UMLNature.INTERFACE, "Interface");
		this.poolPanel.addClass(1, UMLNature.CLASS, "Class");
		this.poolPanel.addProperty(2, "myproperty", "int", "+");
		this.poolPanel.addMethod(3, "mymethod", new ArrayList<String>(), "", "+");
		poolPanel.refresh();
		
		// END TEST
		
		this.repaint();
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
		//TODO
	}
	
	/**
	 * Reset color of UML drawings to black in drawing area
	 */
	public void doResetUMLDrawingColor() {
		//TODO
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
	 * Get attribute name from id
	 * 
	 * @return attribute name
	 */
	public String getAttributeName(Object id) {
		return poolPanel.getElementName(id, UMLNature.ATTRIBUTE);
	}
	
	/**
	 * Get method name from id
	 * 
	 * @return method name
	 */
	public String getMethodName(Object id) {
		return poolPanel.getElementName(id, UMLNature.METHOD);
	}
	
	/**
	 * Sends to the controller the user's request to validate his Uml diagram
	 */
	public void askValidateDiagram() {
		controller.askValidateDiagram();
	}
	
	public void doAddNewElementFromPool(Object nature) {
		// TODO
		if (nature.equals(UMLNature.CLASS)) {
			//poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			//poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.INTERFACE)) {
			//poolPanel.modifyClass((id, controller.askUmlInstanceName(id));
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			//poolPanel.modifyProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
		} else if (nature.equals(UMLNature.METHOD)) {
			//poolPanel.modifyMethod(id, controller.askUmlInstanceName(id), controller.askUmlInstanceParamTypes(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
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
			// TODO edit drawing
		} else if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
			// TODO edit drawing
		} else if (nature.equals(UMLNature.INTERFACE)) {
			poolPanel.modifyClass(id, controller.askUmlInstanceName(id));
			// TODO edit drawing
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			poolPanel.modifyProperty(id, controller.askUmlInstanceName(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
			// TODO edit drawing
		} else if (nature.equals(UMLNature.METHOD)) {
			poolPanel.modifyMethod(id, controller.askUmlInstanceName(id), controller.askUmlInstanceParamTypes(id), controller.askUmlInstanceType(id), controller.askUmlInstanceVisibility(id));
			// TODO edit drawing
		}
		
		poolPanel.refresh();
	}
	
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
	
	public void doAddElementToDrawingArea(Object id, Object nature, Dimension posistion) {
		int i = 0;
		boolean find = false;
		
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			boolean classIsNotDrawn = true;
			for (int j = 0 ; j < classes.size() ; j++) {
				if (classes.get(j).getName() == poolPanel.getElementName(id, nature)) {
					classIsNotDrawn = false;
				}
			}
			if (classIsNotDrawn) {
				classes.add(new ClassDrawing(this, poolPanel.getElementName(id, nature), posistion.width, posistion.height));
				if (nature.equals(UMLNature.ABSTRACT_CLASS)) {
					classes.lastElement().setClasstype("<<classe abstraite>>");
				} else if (nature.equals(UMLNature.INTERFACE)) {
					classes.lastElement().setClasstype("<<interface>>");
				}
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
					if (classes.get(i).isUnder(posistion)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					controller.askLinkAttributeToClass(id, classes.get(i).getName());
					classes.get(i).addProperty(id);
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
					if (classes.get(i).isUnder(posistion)) {
						find = true;
					}
					i++;
				}
				if (find) {
					i--;
					controller.askLinkMethodToClass(id, classes.get(i).getName());
					classes.get(i).addMethod(id);
				}
			}
		}
	}
	
	public void doRemoveElementFromDrawingArea(Object id, Object nature) {
		if (nature.equals(UMLNature.CLASS) || nature.equals(UMLNature.ABSTRACT_CLASS) || nature.equals(UMLNature.INTERFACE)) {
			// TODO remove links
			for(int i = 0 ; i < links.size() ; i++) {
				if(links.get(i).getMotherClass() == classes.get(i).getName()
						|| links.get(i).getDaughterClass() == classes.get(i).getName()) {
					links.remove(i);
					i--;
				}
			}
			for(int i = 0 ; i < classes.size() ; i++) {
				if(poolPanel.getElementName(id, nature) == classes.get(i).getName()) {
					classes.remove(i);
					i--;
				}
			}
		} else if (nature.equals(UMLNature.ATTRIBUTE)) {
			// TODO remove from class and unlink
			for(int i = 0 ; i < classes.size() ; i++) {
				classes.get(i).removeProperty(id);
			}
		} else if (nature.equals(UMLNature.METHOD)) {
			// TODO remove from class and unlink
			for(int i = 0 ; i < classes.size() ; i++) {
				classes.get(i).removeMethod(id);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//g2d.fillRect(0, 0, 2, 2);
		//g2d.fillRect(this.getWidth()-2, this.getHeight()-2, 2, 2);
		
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

						// Open Link Edition Panel
						LinkEditionPanel linkEdition = new LinkEditionPanel(links.get(i).getMotherClass(), links.get(i).getMotherMultiplicity(), links.get(i).getDaughterClass(), links.get(i).getDaughterMultiplicity(), links.get(i).getText());
						
						int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
								"Edition de relation de " + links.get(i).getMotherClass() + " vers " + links.get(i).getDaughterClass(), JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							links.get(i).setMotherMultiplicity(linkEdition.getMotherMultiplicity());
							links.get(i).setDaughterMultiplicity(linkEdition.getDaughterMultiplicity());
							links.get(i).setText(linkEdition.getText());
							// TODO this.askEditLink(i);
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
						links.add(new LinkDrawing(classes.get(previousClickedClass), classes.get(i), toolBar.getState()));
						
						// Link Edition Panel
						LinkEditionPanel linkEdition = new LinkEditionPanel(links.lastElement().getMotherClass(), links.lastElement().getMotherMultiplicity(), links.lastElement().getDaughterClass(), links.lastElement().getDaughterMultiplicity(), links.lastElement().getText());
						
						int result = JOptionPane.showConfirmDialog(null, new JScrollPane(linkEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
								"Nouvelle relation de " + links.lastElement().getMotherClass() + " vers " + links.lastElement().getDaughterClass(), JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							links.lastElement().setMotherMultiplicity(linkEdition.getMotherMultiplicity());
							links.lastElement().setDaughterMultiplicity(linkEdition.getDaughterMultiplicity());
							links.lastElement().setText(linkEdition.getText());
							// TODO this.askLinkClass(links.size() - 1);
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
}