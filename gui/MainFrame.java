package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame(ClassicGuiController controller, AbstractPanel navigationPanel, AbstractPanel scorePanel, AbstractPanel timerPanel, AbstractPanel textSectionPanel, AbstractPanel umlPanel) throws HeadlessException {
		super("Uml Serious Game");
		
		this.setSize(1280, 720);
		this.setMinimumSize(new Dimension(800,600));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		//this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.setPreferredSize(new Dimension(180,768));
		JPanel botLeftPane = new JPanel(new GridLayout(2,1));
		botLeftPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		botLeftPane.add(scorePanel);
		botLeftPane.add(timerPanel);
		leftPane.add(new JScrollPane(navigationPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		leftPane.add(botLeftPane, BorderLayout.SOUTH);
		
		textSectionPanel.setPreferredSize(new Dimension(900,300));
		textSectionPanel.setMinimumSize(new Dimension(600,25));
		umlPanel.setMinimumSize(new Dimension(600,200));
		JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, textSectionPanel, umlPanel);
		
		this.add(leftPane, BorderLayout.WEST);
		this.add(rightPane, BorderLayout.CENTER);
	}


}
