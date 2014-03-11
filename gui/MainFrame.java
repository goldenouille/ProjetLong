package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import actions.ActCloseExercise;
import actions.ActSaveExercise;

/**
 * @author Will
 * Sets the main layout of the exercise
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ImageIcon lune = new ImageIcon("images/lune.png");

	/** Creates a frame and lays out the given components for a classic exercise
	 * @param controller
	 * @param navigationPanel
	 * @param scorePanel
	 * @param timerPanel
	 * @param textSectionPanel
	 * @param umlPanel
	 * @throws HeadlessException
	 */
	public MainFrame(ClassicGuiController controller, AbstractPanel navigationPanel, AbstractPanel scorePanel, AbstractPanel timerPanel,
			AbstractPanel textSectionPanel, JPanel umlPanel) throws HeadlessException {
		super("L.U.N.E : an Uml Serious Game");

		this.setSize(1280, 720);
		this.setIconImage(lune.getImage());
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.setPreferredSize(new Dimension(180, 720));
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemSave = new JMenuItem(new ActSaveExercise(controller, "Enregistrer l'exercice en xml"));
		JMenuItem itemQuit = new JMenuItem(new ActCloseExercise(controller, "Quitter l'exercice"));
		menuFichier.add(itemSave);
		menuFichier.add(itemQuit);
		menuBar.add(menuFichier);
		
		JPanel botLeftPane = new JPanel(new GridLayout(2, 1));
		botLeftPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		botLeftPane.add(scorePanel);
		botLeftPane.add(timerPanel);
		leftPane.add(menuBar, BorderLayout.NORTH);
		leftPane.add(new JScrollPane(navigationPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		leftPane.add(botLeftPane, BorderLayout.SOUTH);
		
		textSectionPanel.setPreferredSize(new Dimension(900, 300));
		textSectionPanel.setMinimumSize(new Dimension(600, 25));
		umlPanel.setMinimumSize(new Dimension(600, 200));
		JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, textSectionPanel, umlPanel);

		this.add(leftPane, BorderLayout.WEST);
		this.add(rightPane, BorderLayout.CENTER);
	}

}
