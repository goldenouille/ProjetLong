package uml;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder()));
		this.setFloatable(false);
		this.setRollover(true);
		state = NO_LINK;

		// NO LINK BUTTON
		button = new JButton();
		button.setToolTipText("Deplacer des elements");// "Go back to mouse movement"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(NO_LINK);
			}
		});
		button.setText("M");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// LINK_EDITION BUTTON
		button = new JButton();
		button.setToolTipText("Editer les proprietes d'une relation");// "Edit link properties"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(LINK_EDITION);
			}
		});
		button.setText("Ed");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// CHANGE_DIRECTION BUTTON
		button = new JButton();
		button.setToolTipText("Changer la direction d'une relation");// "Change link direction"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(CHANGE_DIRECTION);
			}
		});
		button.setText("CD");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// REMOVE LINK BUTTON
		button = new JButton();
		button.setToolTipText("Supprimer une relation");// "Remove link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(REMOVE_LINK);
			}
		});
		button.setText("RL");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// REALIZATION BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation de realisation");// "Create realization link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(REALIZATION);
			}
		});
		button.setText("R");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// GENERALIZATION BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation de generalisation");// "Create generalization link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(GENERALIZATION);
			}
		});
		button.setText("G");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// DEPENDANCY BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation de dependance");// "Create dependancy link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(DEPENDANCY);
			}
		});
		button.setText("D");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// BINARY ASSOCIATION BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation d'association binaire");// "Create binary association link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(BINARY_ASSOCIATION);
			}
		});
		button.setText("BA");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// AGGREGATION BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation d'agregation");// "Create aggregation link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(AGGREGATION);
			}
		});
		button.setText("A");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// COMPOSITION BUTTON
		button = new JButton();
		button.setToolTipText("Creer une relation de composition");// "Create composition link");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(COMPOSITION);
			}
		});
		button.setText("C");
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
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

		if (this.state == NO_LINK || this.state == LINK_EDITION || this.state == CHANGE_DIRECTION || this.state == REMOVE_LINK) {
			result = false;
		}

		return result;
	}
}
