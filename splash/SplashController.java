package splash;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import org.xml.sax.SAXException;

import parser.Parser;

import model.Exercise;

import actions.ActBrowseFiles;
import actions.ActStartExercise;
import actions.ActViewHistory;

public class SplashController extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextPane previewPane;
	private Exercise choosenExercise;

	public SplashController() {
		super("Uml Serious Game");

		this.setSize(1280, 720);
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel leftPane = new JPanel();
		leftPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()));
		leftPane.setPreferredSize(new Dimension(180, 720));
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		
		leftPane.add(Box.createRigidArea(new Dimension(0,20)));

		JButton historyButton = new JButton(new ActViewHistory(this,"Historique"));
		historyButton.setPreferredSize(new Dimension(50,20));
		historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(historyButton);
		
		leftPane.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton browseButton = new JButton(new ActBrowseFiles(this,"Fournir un fichier exercice"));
		browseButton.setPreferredSize(new Dimension(100,50));
		browseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(browseButton);
		
		leftPane.add(Box.createVerticalGlue());
		
		previewPane = new JTextPane();
		JScrollPane scrollPreviewPane = new JScrollPane(previewPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		leftPane.add(scrollPreviewPane);
		
		leftPane.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton startButton = new JButton(new ActStartExercise(this,"Commencer"));
		startButton.setPreferredSize(new Dimension(50,20));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPane.add(startButton);

		JPanel rightPane = new JPanel();

		this.add(leftPane, BorderLayout.WEST);
		this.add(rightPane, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	public void doShowHistory() {
		// TODO Auto-generated method stub
		
	}

	public void doStartExercise() {
		// TODO Auto-generated method stub
		
	}

	public void setExercise(File selectedFile) {
		try {
			Parser parser = new Parser();
			this.choosenExercise = parser.parse(new FileInputStream(selectedFile));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SplashController controller = new SplashController();
	}
}
