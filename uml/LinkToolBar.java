import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class LinkToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862266067470549075L;
	
	private JPanel panel;
	private int state;
	
	public static final int NO_LINK = -1;
	public static final int CHANGE_DIRECTION = -2;
	public static final int REMOVE_LINK = -3;
	public static final int REALIZATION = 2;
	public static final int GENERALIZATION = 3;
	public static final int DEPENDANCY = 4;
	public static final int BINARY_ASSOCIATION = 7;
	public static final int AGGREGATION = 8;
	public static final int COMPOSITION = 9;

	public LinkToolBar(JPanel panel) {
		JButton button = null;
		
		this.panel = panel;
		this.setSize(600, 26);
		this.setFloatable(false);
		this.setRollover(true);
		state = NO_LINK;

		// NO LINK BUTTON
		button = new JButton();
	    button.setToolTipText("Go back to mouse movement");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(NO_LINK);
        	}
	    });
	    button.setText("M");
	    //button.setIcon(new ImageIcon(imageURL, altText));
		this.add(button);
		
		// CHANGE_DIRECTION BUTTON
		button = new JButton();
	    button.setToolTipText("Change link direction");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(CHANGE_DIRECTION);
        	}
	    });
	    button.setText("CD");
		this.add(button);
		
		// REMOVE LINK BUTTON
		button = new JButton();
	    button.setToolTipText("Remove link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(REMOVE_LINK);
        	}
	    });
	    button.setText("RL");
		this.add(button);
		
		// REALIZATION BUTTON
		button = new JButton();
	    button.setToolTipText("Create realization link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(REALIZATION);
        	}
	    });
	    button.setText("R");
		this.add(button);
		
		// GENERALIZATION BUTTON
		button = new JButton();
	    button.setToolTipText("Create generalization link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(GENERALIZATION);
        	}
	    });
	    button.setText("G");
		this.add(button);
		
		// DEPENDANCY BUTTON
		button = new JButton();
	    button.setToolTipText("Create dependancy link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(DEPENDANCY);
        	}
	    });
	    button.setText("D");
		this.add(button);
		
		// BINARY ASSOCIATION BUTTON
		button = new JButton();
	    button.setToolTipText("Create binary association link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(BINARY_ASSOCIATION);
        	}
	    });
	    button.setText("BA");
		this.add(button);
		
		// AGGREGATION BUTTON
		button = new JButton();
	    button.setToolTipText("Create aggregation link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(AGGREGATION);
        	}
	    });
	    button.setText("A");
		this.add(button);
		
		// COMPOSITION BUTTON
		button = new JButton();
	    button.setToolTipText("Create composition link");
	    button.addActionListener(new ActionListener() {
	    	@Override
        	public void actionPerformed(ActionEvent ae) {
        		setState(COMPOSITION);
        	}
	    });
	    button.setText("C");
		this.add(button);
		
		// TODO System.out.println("toolbar: " + String.valueOf(this.getX()) + " - " + String.valueOf(this.getX()) + " | " + String.valueOf(this.getSize().width) + " - " + String.valueOf(this.getSize().height));
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		switch (state) {
		case CHANGE_DIRECTION:
			this.state = CHANGE_DIRECTION;
			break;
		case REMOVE_LINK:
			this.state = REMOVE_LINK;
			break;
		case REALIZATION:
			this.state = REALIZATION;
			break;
		case GENERALIZATION:
			this.state = GENERALIZATION;
			break;
		case DEPENDANCY:
			this.state = DEPENDANCY;
			break;
		case BINARY_ASSOCIATION:
			this.state = BINARY_ASSOCIATION;
			break;
		case AGGREGATION:
			this.state = AGGREGATION;
			break;
		case COMPOSITION:
			this.state = COMPOSITION;
			break;
		default:
			this.state = NO_LINK;
			break;
		}
		
	}
}
