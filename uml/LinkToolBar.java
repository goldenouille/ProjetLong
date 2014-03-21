package uml;

import gui.ClassicGuiController;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import actions.ActValidateDiagram;

/**
 * @author Vincent Roth
 * Toolbar for ralation drawing buttons
 * 
 * Each button set the toolbar status.
 * This status have to be check by the drawing panel to know which action to perform.
 * The drawing panel is in charge of the action performing.
 */
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

	// icons
	private ImageIcon arrow_aggregation = new ImageIcon("images/arrow_aggregation.png");
	private ImageIcon arrow_association = new ImageIcon("images/arrow_association.png");
	private ImageIcon arrow_composition = new ImageIcon("images/arrow_composition.png");
	private ImageIcon arrow_delete = new ImageIcon("images/arrow_delete.png");
	private ImageIcon arrow_dependancy = new ImageIcon("images/arrow_dependancy.png");
	private ImageIcon arrow_generalization = new ImageIcon("images/arrow_generalization.png");
	private ImageIcon arrow_realization = new ImageIcon("images/arrow_realization.png");
	private ImageIcon arrow_reverse = new ImageIcon("images/arrow_reverse.png");
	private ImageIcon edition = new ImageIcon("images/edition.png");
	private ImageIcon move = new ImageIcon("images/move.png");
	private ImageIcon validation = new ImageIcon("images/validation.png");

	// vars
	private JButton validationButton;
	
	
	/**
	 * Main constructor, create a LinkToolBar
	 * 
	 * @param controller
	 */
	public LinkToolBar(ClassicGuiController controller) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder()));
		//this.setFloatable(false);
		state = NO_LINK;

		// NO LINK BUTTON
		JButton button = new JButton(move);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Deplacer des elements");// "Go back to mouse movement"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(NO_LINK);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// LINK_EDITION BUTTON
		button = new JButton(edition);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Editer les proprietes d'une relation");// "Edit link properties"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(LINK_EDITION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// CHANGE_DIRECTION BUTTON
		button = new JButton(arrow_reverse);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Changer la direction d'une relation");// "Change link direction"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(CHANGE_DIRECTION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// REMOVE LINK BUTTON
		button = new JButton(arrow_delete);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Supprimer une relation");// "Remove link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(REMOVE_LINK);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// REALIZATION BUTTON
		button = new JButton(arrow_realization);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation de realisation");// "Create realization link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(REALIZATION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// GENERALIZATION BUTTON
		button = new JButton(arrow_generalization);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation de generalisation");// "Create generalization link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(GENERALIZATION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// DEPENDANCY BUTTON
		button = new JButton(arrow_dependancy);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation de dependance");// "Create dependancy link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(DEPENDANCY);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// BINARY ASSOCIATION BUTTON
		button = new JButton(arrow_association);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation d'association binaire");// "Create binary association link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(BINARY_ASSOCIATION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// AGGREGATION BUTTON
		button = new JButton(arrow_aggregation);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation d'agregation");// "Create aggregation link"
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(AGGREGATION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// COMPOSITION BUTTON
		button = new JButton(arrow_composition);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("Creer une relation de composition");// "Create composition link");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setState(COMPOSITION);
			}
		});
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(10, 0)));

		// Validation BUTTON
		validationButton = new JButton();
		validationButton.setAction(new ActValidateDiagram(controller, null));
		validationButton.setIcon(validation);
		validationButton.setPreferredSize(new Dimension(20, 20));
		validationButton.setToolTipText("Valider le diagramme");
		this.add(validationButton);
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

	public void setValidateDiagramButtonEnabled(boolean enabled) {
		validationButton.setEnabled(enabled);
	}
}
