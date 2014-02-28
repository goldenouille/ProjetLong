package uml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;


public class LinkToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private JPanel panel;
	private int state;
	
	public static final int NO_LINK = -1;
	public static final int LINK_EDITION = -2;
	public static final int CHANGE_DIRECTION = -3;
	public static final int REMOVE_LINK = -4;
	public static final int REALIZATION = 2;
	public static final int GENERALIZATION = 3;
	public static final int DEPENDANCY = 4;
	public static final int BINARY_ASSOCIATION = 7;
	public static final int AGGREGATION = 8;
	public static final int COMPOSITION = 9;

	public LinkToolBar(){//JPanel panel) {
		JButton button = null;
		
		// TODO get right size from panel
		//this.panel = panel;
		//this.setSize(600, 26);
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
		this.add(button);
		
		// LINK_EDITION BUTTON
				button = new JButton();
			    button.setToolTipText("Edit link properties");
			    button.addActionListener(new ActionListener() {
			    	@Override
		        	public void actionPerformed(ActionEvent ae) {
		        		setState(LINK_EDITION);
		        	}
			    });
			    button.setText("Ed");
			    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
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
	    // TODO button.setIcon(new ImageIcon(imageURL, altText));
		this.add(button);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		switch (state) {
		case LINK_EDITION:
			this.state = LINK_EDITION;
			break;
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
	
	public boolean isInLinkRelationState() {
		boolean result = true;
		
		if (this.state == NO_LINK
				|| this.state == LINK_EDITION
				|| this.state == CHANGE_DIRECTION
				|| this.state == REMOVE_LINK) {
			result = false;
		}
		
		return result;
	}
}
