package uml;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;


public class ClassDrawing {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean reduced;
	
	private String classtype = "";
	private String name = "";
	private Vector<String> properties;
	private Vector<String> methods;

	/**
	 * Main constructor, create a ClassDrawing representation of a class
	 * 
	 * @param name
	 *            class name
	 * @param x
	 *            class x-axis drawing position
	 * @param y
	 *            class y-axis drawing position
	 */
	public ClassDrawing(String name, int x, int y) {
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		setX(x);
		setY(y);
		setReduced(false);
		setName(name);
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
		return reduced;
	}

	/**
	 * Set if class drawing is reduced (properties and methods are not drawn)
	 * 
	 * @param reduced
	 *            if true, class drawing is reduced
	 */
	public void setReduced(boolean reduced) {
		if (properties.size() != 0 || methods.size() != 0) {
			this.reduced = reduced;
		} else {
			this.reduced = false;
		}
	}
	
	/**
	 * Get name of class drawing
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of class drawing
	 * 
	 * @param name
	 *            class name to draw
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get type text of class drawing
	 * 
	 * @return class type
	 */
	public String getClasstype() {
		return classtype;
	}

	/**
	 * Set type text of class drawing
	 * 
	 * @param type
	 *            <<type>> of class
	 */
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	
	/**
	 * Add a property to draw
	 * 
	 * @param property
	 *            "+ myproperty" text property
	 */
	public void addProperty(String property) {
		properties.add(property);
	}
	
	/**
	 * Remove specified property to draw
	 * 
	 * @param property
	 *            property text to remove
	 */
	public void removeProperty(String property) {
		properties.remove(property);
		if (properties.size() == 0 && methods.size() == 0){
			reduced = false;
		}
	}
	
	/**
	 * Add a method to draw
	 * 
	 * @param method
	 *            "+ mymethod" text method
	 */
	public void addMethod(String method) {
		methods.add(method);
	}
	
	/**
	 * Remove specified method to draw
	 * 
	 * @param method
	 *            method text to remove
	 */
	public void removeMethod(String method) {
		methods.remove(method);
		if (properties.size() == 0 && methods.size() == 0){
			reduced = false;
		}
	}
	
	/**
	 * Draw class in specified Graphics
	 * 
	 * @param g
	 *            Graphics where to draw
	 */
	public void draw(Graphics g) {
		int actualHeight = 0; // for drawing String
		int stringWidth = g.getFontMetrics().stringWidth(name);
		
		if (g.getFontMetrics().stringWidth(classtype) > stringWidth) {
			stringWidth = g.getFontMetrics().stringWidth(classtype);
		}
		for (int i = 0; i < properties.size() ; i++) {
			if (g.getFontMetrics().stringWidth(properties.get(i)) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(properties.get(i));
			}
		}
		for (int i = 0; i < methods.size() ; i++) {
			if (g.getFontMetrics().stringWidth(methods.get(i)) > stringWidth) {
				stringWidth = g.getFontMetrics().stringWidth(methods.get(i));
			}
		}
		width = stringWidth + 8;
		
		//g.drawRect(x, y, width, height);
		if (classtype != "" && classtype != null) {
			actualHeight += g.getFont().getSize();
			g.drawString(classtype, x + width/2 - g.getFontMetrics().stringWidth(classtype)/2, y + actualHeight);
		}
		actualHeight += g.getFont().getSize();
		g.drawString(name, x + width/2 - g.getFontMetrics().stringWidth(name)/2, y + actualHeight);
		
		if (properties.size() != 0) {
			actualHeight += 4;
			g.drawLine(x, y + actualHeight, x + width, y + actualHeight);
			if (!this.isReduced()) {
				for (int i = 0; i < properties.size() ; i++) {
					actualHeight += g.getFont().getSize();
					g.drawString(properties.get(i), x + 4, y + actualHeight);
				}
			}
		}
		if (methods.size() != 0) {
			actualHeight += 4;
			g.drawLine(x, y + actualHeight, x + width, y + actualHeight);
			if (!this.isReduced()) {
				for (int i = 0; i < methods.size() ; i++) {
					actualHeight += g.getFont().getSize();
					g.drawString(methods.get(i), x + 4, y + actualHeight);
				}
			}
		}

		height = actualHeight + 4;
		g.drawRect(x, y, width, height);
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
}
