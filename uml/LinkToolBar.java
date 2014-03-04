package uml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;


public class LinkToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private int state;
	
	// State constants
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

	/**
	 * Main constructor, create a LinkToolBar
	 */
	public LinkToolBar() {
		JButton button = null;
		
		this.setFloatable(false);
		this.setRollover(true);
		state = NO_LINK;

		// NO LINK BUTTON
		button = new JButton();
	    button.setToolTipText("Déplacer des éléments");//"Go back to mouse movement"
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
			    button.setToolTipText("Editer les propriétés d'une relation");//"Edit link properties"
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
	    button.setToolTipText("Changer la direction d'une relation");//"Change link direction"
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
	    button.setToolTipText("Supprimer une relation");//"Remove link"
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
	    button.setToolTipText("Créer une ralation de réalisation");//"Create realization link"
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
	    button.setToolTipText("Créer une relation de généralisation");//"Create generalization link"
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
	    button.setToolTipText("Créer une relation de dépendance");//"Create dependancy link"
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
	    button.setToolTipText("Créer une relation d'association binaire");//"Create binary association link"
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
	    button.setToolTipText("Créer une relation d'agrégation");//"Create aggregation link"
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
	    button.setToolTipText("Créer une relation de composition");//"Create composition link");
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

	/**
	 * Get tool bar state
	 * 
	 * @return state as defined in LinkToolBar
	 */
	public int getState() {
		return state;
	}

	/**
	 * Set tool bar state, with checking
	 * 
	 * @param state
	 *            state to set
	 */
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
	
	/**
	 * Get if tool bar state is a link setting state
	 * 
	 * @return state as defined in LinkToolBar
	 */
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
