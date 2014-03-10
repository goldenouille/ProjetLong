package launcher;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import controller.Controller;

import parser.Parser;

import model.Exercise;

import actions.ActChooseExercise;
import actions.ActStartExercise;
import actions.ActViewHistory;

public class LaunchController extends JFrame {

	private static final long serialVersionUID = 1L;
	private ImageIcon lune = new ImageIcon("images/lune.png");
	private ExerciseBrowser browser;
	private Exercise choosenExercise;

	public LaunchController(ExerciseBrowser browser) {
		super("L.U.N.E : an Uml Serious Game");

		this.browser = browser;
		this.setSize(1280, 720);
		this.setIconImage(lune.getImage());
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (Exception e) {
		}
		// com.jgoodies.looks.windows.WindowsLookAndFeel
		// com.jgoodies.looks.plastic.PlasticLookAndFeel
		// com.jgoodies.looks.plastic.Plastic3DLookAndFeel
		// com.jgoodies.looks.plastic.PlasticXPLookAndFeel

		JPanel leftPane = new JPanel();
		leftPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		leftPane.setPreferredSize(new Dimension(180, 720));
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		leftPane.add(Box.createRigidArea(new Dimension(0, 20)));

		JButton historyButton = new JButton(new ActViewHistory(this, "Historique"));
		historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(historyButton);
		leftPane.add(Box.createRigidArea(new Dimension(0, 20)));

		JButton browseButton = new JButton(new ActChooseExercise(this, "Choisir exercice"));
		browseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(browseButton);
		leftPane.add(Box.createVerticalGlue());

		JButton startButton = new JButton(new ActStartExercise(this, "Commencer"));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(startButton);
		leftPane.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel rightPane = browser.getBrowsingPanel();

		this.add(leftPane, BorderLayout.WEST);
		this.add(rightPane, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	public void doShowHistory() {
		// TODO Auto-generated method stub
	}

	public void doStartExercise() {
		if (choosenExercise != null) {
			@SuppressWarnings("unused")
			Controller controller = new Controller(choosenExercise);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez choisir un exercice", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void chooseExercise() {
		File selectedFile = browser.getExerciseFile();
		try {
			Parser parser = new Parser();
			this.choosenExercise = parser.parse(new FileInputStream(selectedFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
