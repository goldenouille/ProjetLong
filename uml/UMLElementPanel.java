package uml;

import java.util.Vector;

import javax.swing.JPanel;


public class UMLElementPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> classes;
	private Vector<String> properties;
	private Vector<String> methods;
	
	public UMLElementPanel() {
		super();
		
		classes = new Vector<String>();
		properties = new Vector<String>();
		methods = new Vector<String>();
		
		
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

}
