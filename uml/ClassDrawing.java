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

	
	public ClassDrawing(String name, int x, int y) {
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		setX(x);
		setY(y);
		setReduced(false);
		setName(name);
	}
	
	public ClassDrawing(int x, int y, int width, int height, String name) {
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setReduced(false);
		setName(name);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isReduced() {
		return reduced;
	}

	public void setReduced(boolean reduced) {
		if (properties.size() != 0 || methods.size() != 0) {
			this.reduced = reduced;
		} else {
			this.reduced = false;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	
	public void addProperty(String property) {
		properties.add(property);
	}
	
	public void removeProperty(String property) {
		properties.remove(property);
	}
	
	public void addMethod(String method) {
		methods.add(method);
	}
	
	public void removeMethod(String method) {
		methods.remove(method);
	}
	
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
	
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void move(Dimension delta) {
		this.move (delta.width, delta.height);
	}
	
	public boolean isUnder(Dimension pos) {
		boolean under = false;
		
		if (this.x <= pos.width && pos.width < (this.x + this.width)
				&& this.y <= pos.height && pos.height < (this.y + this.height)) {
			under = true;
		}
		
		return under;
	}
}
