package splash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import actions.ActBrowseFiles;
import actions.ActStartExercise;
import actions.ActViewHistory;

public class SplashFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextPane previewPane;
	private File choosenExercise;

	public SplashFrame() {
		super("Uml Serious Game");

		this.setSize(1280, 720);
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel leftPane = new JPanel();
		leftPane.setPreferredSize(new Dimension(180, 720));
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));

		JButton historyButton = new JButton(new ActViewHistory(this,"Historique"));
		JButton browseButton = new JButton(new ActBrowseFiles(this,"Fournir un fichier exercice"));
		JButton startButton = new JButton(new ActStartExercise(this,"Commencer"));
		
		previewPane = new JTextPane();
		JScrollPane scrollPreviewPane = new JScrollPane(previewPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPreviewPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
		// BorderFactory.createLoweredBevelBorder()));

		JPanel rightPane = new JPanel();

		this.add(leftPane, BorderLayout.WEST);
		this.add(rightPane, BorderLayout.CENTER);
	}

	public void doShowHistory() {
		// TODO Auto-generated method stub
		
	}

	public void doStartExercise() {
		// TODO Auto-generated method stub
		
	}

	public void setExercise(File selectedFile) {
		// TODO Auto-generated method stub
		
	}
}
