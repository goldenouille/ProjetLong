package uml;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import model.UMLNature;


public class ClassDrawing {
	
	private static String STRING_ABSTRACT = "<<class abastraite>>";
	private static String STRING_INTERFACE = "<<interface>>";
	
	private UMLDrawingPanel drawingPanel;

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isReduced;
	
	private Object id;
	private Object type;
	private Vector<Object> propertiesID;
	private Vector<Object> methodsID;
	
	private boolean isColored;
	private Vector<Object> coloredID;

	/**
	 * Main constructor, create a ClassDrawing representation of a class
	 * 
	 * @param drawingPanel
	 *            UMLDrawingPanel component
	 * @param id
	 *            class id
	 * @param nature
	 *            class nature
	 * @param x
	 *            class x-axis drawing position
	 * @param y
	 *            class y-axis drawing position
	 */
	public ClassDrawing(UMLDrawingPanel drawingPanel, Object id, Object nature, int x, int y) {
		this.drawingPanel = drawingPanel;
		
		propertiesID = new Vector<Object>();
		methodsID = new Vector<Object>();
		
		this.x = x;
		this.y = y;
		isReduced = false;
		this.id = id;
		this.type = nature;
		
		isColored = false;
		coloredID = new Vector<Object>();
	}

	/**
	 * Get x-axis position of class drawing
	 * 
	 * @return position in x-axis
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set x-axis position of class drawing
	 * 
	 * @param x
	 *            position in x-axis
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get y-axis position of class drawing
	 * 
	 * @return position in y-axis
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set y-axis position of class drawing
	 * 
	 * @param y
	 *            position in y-axis
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get width of class drawing
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get height of class drawing
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get if class drawing is reduced (properties and methods are not drawn)
	 * 
	 * @return reduced
	 */
	public boolean isReduced() {
		return isReduced;
	}

	/**
	 * Set if class drawing is reduced (properties and methods are not drawn)
	 * 
	 * @param reduced
	 *            if true, class drawing is reduced
	 */
	public void setReduced(boolean reduced) {
		if (propertiesID.size() != 0 || methodsID.size() != 0) {
			this.isReduced = reduced;
		} else {
			this.isReduced = false;
		}
	}
	
	/**
	 * Get UML instance id of class drawing
	 * 
	 * @return id
	 */
	public Object getInstanceID() {
		return id;
	}
	
	/**
	 * Get nature of class drawing
	 * 
	 * @return class type
	 */
	public Object getClasstype() {
		return type;
	}
	
	/**
	 * Add a property to draw
	 * 
	 * @param property
	 *            property id to add
	 */
	public void addProperty(Object id) {
		propertiesID.add(id);
	}
	
	/**
	 * Remove specified property to draw
	 * 
	 * @param property
	 *            property id to remove
	 */
	public void removeProperty(Object id) {
		propertiesID.remove(id);
		if (propertiesID.size() == 0 && methodsID.size() == 0){
			isReduced = false;
		}
	}
	
	/**
	 * Return true if contains id property
	 * 
	 * @param id
	 *            property id
	 */
	public boolean containProperty(Object id) {
		return propertiesID.contains(id);
	}
	
	/**
	 * Add a method to draw
	 * 
	 * @param method
	 *            method id to add
	 */
	public void addMethod(Object id) {
		methodsID.add(id);
	}
	
	/**
	 * Remove specified method to draw
	 * 
	 * @param method
	 *            method id to remove
	 */
	public void removeMethod(Object id) {
		methodsID.remove(id);
		if (propertiesID.size() == 0 && methodsID.size() == 0){
			isReduced = false;
		}
	}
	
	/**
	 * Return true if contains id method
	 * 
	 * @param id
	 *            method id
	 */
	public boolean containMethod(Object id) {
		return methodsID.contains(id);
	}
	
	/**
	 * Draw class in specified Graphics
	 * 
	 * @param g
	 *            Graphics where to draw
	 */
	public void draw(Graphics g) {
		int actualHeight = 0; // for drawing String
		int stringWidth = g.getFontMetrics().stringWidth(drawingPanel.getElementName(id, type));
		
		if (type.equals(UMLNature.ABSTRACT_CLASS)) {
			if (g.getFontMetrics().stringWidth(STRING_ABSTRACT) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(STRING_ABSTRACT);
			}
		} else if (type.equals(UMLNature.INTERFACE)) {
			if (g.getFontMetrics().stringWidth(STRING_INTERFACE) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(STRING_INTERFACE);
			}
		}
		for (int i = 0; i < propertiesID.size() ; i++) {
			if (g.getFontMetrics().stringWidth(drawingPanel.getElementName(propertiesID.get(i), UMLNature.ATTRIBUTE)) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(drawingPanel.getElementName(propertiesID.get(i), UMLNature.ATTRIBUTE));
			}
		}
		for (int i = 0; i < methodsID.size() ; i++) {
			if (g.getFontMetrics().stringWidth(drawingPanel.getElementName(methodsID.get(i), UMLNature.METHOD)) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(drawingPanel.getElementName(methodsID.get(i), UMLNature.METHOD));
			}
		}
		width = stringWidth + 8;
		
		if (isColored) {
			g.setColor(UMLDrawingPanel.COLOR_ALT);
		}
		
		// class type drawing
		if (type.equals(UMLNature.ABSTRACT_CLASS) || type.equals(UMLNature.INTERFACE)) {
			actualHeight += g.getFont().getSize();
			if (type.equals(UMLNature.ABSTRACT_CLASS)) {
				g.drawString(STRING_ABSTRACT, x + width/2 - g.getFontMetrics().stringWidth(STRING_ABSTRACT)/2, y + actualHeight);
			} else if (type.equals(UMLNature.INTERFACE)) {
				g.drawString(STRING_INTERFACE, x + width/2 - g.getFontMetrics().stringWidth(STRING_INTERFACE)/2, y + actualHeight);
			}
		}
		
		// class name drawing
		actualHeight += g.getFont().getSize();
		g.drawString(drawingPanel.getElementName(id, type), x + width/2 - g.getFontMetrics().stringWidth(drawingPanel.getElementName(id, type))/2, y + actualHeight);
		
		if (isColored) {
			g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
		}
		
		// properties drawing
		if (propertiesID.size() != 0) {
			actualHeight += 4;
			if (isColored) {
				g.setColor(UMLDrawingPanel.COLOR_ALT);
			}
			g.drawLine(x, y + actualHeight, x + width, y + actualHeight);
			if (isColored) {
				g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
			}
			if (!this.isReduced()) {
				for (int i = 0; i < propertiesID.size() ; i++) {
					if (coloredID.contains(propertiesID.get(i))) {
						g.setColor(UMLDrawingPanel.COLOR_ALT);
					}
					actualHeight += g.getFont().getSize();
					g.drawString(drawingPanel.getElementName(propertiesID.get(i), UMLNature.ATTRIBUTE), x + 4, y + actualHeight);
					if (coloredID.contains(propertiesID.get(i))) {
						g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
					}
				}
			}
		}
		
		// methods drawing
		if (methodsID.size() != 0) {
			actualHeight += 4;
			if (isColored) {
				g.setColor(UMLDrawingPanel.COLOR_ALT);
			}
			g.drawLine(x, y + actualHeight, x + width, y + actualHeight);
			if (isColored) {
				g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
			}
			if (!this.isReduced()) {
				for (int i = 0; i < methodsID.size() ; i++) {
					if (coloredID.contains(methodsID.get(i))) {
						g.setColor(UMLDrawingPanel.COLOR_ALT);
					}
					actualHeight += g.getFont().getSize();
					g.drawString(drawingPanel.getElementName(methodsID.get(i), UMLNature.METHOD), x + 4, y + actualHeight);
					if (coloredID.contains(methodsID.get(i))) {
						g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
					}
				}
			}
		}
		
		// borders drawing
		height = actualHeight + 4;
		if (isColored) {
			g.setColor(UMLDrawingPanel.COLOR_ALT);
		}
		g.drawRect(x, y, width, height);
		if (isColored) {
			g.setColor(UMLDrawingPanel.COLOR_DEFAULT);
		}
	}
	
	/**
	 * Move class position of X and Y
	 * 
	 * @param x
	 *            move on X-axis
	 * @param y
	 *            move on Y-axis
	 */
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Move class position of Dimension width (X) and height (Y)
	 * 
	 * @param delta
	 *            movement delta
	 */
	public void move(Dimension delta) {
		this.move (delta.width, delta.height);
	}
	
	/**
	 * Return if the class is under a position
	 * 
	 * @param true if under position
	 */
	public boolean isUnder(Dimension pos) {
		boolean under = false;
		
		if (this.x <= pos.width && pos.width < (this.x + this.width)
				&& this.y <= pos.height && pos.height < (this.y + this.height)) {
			under = true;
		}
		
		return under;
	}
	
	/**
	 * Get if class drawing is colored
	 * properties and methods are not affected
	 * 
	 * @return colored
	 */
	public boolean isColored() {
		return isColored;
	}

	/**
	 * Set if class drawing is colored
	 * properties and methods are not affected, use addColoredElement(id) instead
	 * 
	 * @param colored
	 *            if true, class borders and name are colored
	 */
	public void setColored(boolean colored) {
		this.isColored = colored;
	}
	
	/**
	 * Add an element (attribute or method) to coloring
	 * 
	 * @param id
	 *            element id
	 */
	public void addColoredElement(Object id) {
		coloredID.add(id);
	}
	
	/**
	 * Remove an element (attribute or method) from coloring
	 * 
	 * @param id
	 *            element id
	 * @return true if remove
	 */
	public boolean removeColoredElement(Object id) {
		return coloredID.remove(id);
	}
	
	/**
	 * Remove all element (class, attribute or method) from coloring
	 */
	public void removeAllColoredElement() {
		isColored = false;
		coloredID.removeAllElements();
	}
}
