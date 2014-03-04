package launcher;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import parser.Parser;

public class SimpleExerciseBrowser implements ExerciseBrowser {

	private JPanel panel;
	private JTextArea previewPane;
	
	public SimpleExerciseBrowser() {
		panel = new JPanel(new BorderLayout());
		previewPane = new JTextArea();
		previewPane.setEditable(false);
		JScrollPane scrollPreviewPane = new JScrollPane(previewPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPreviewPane);
	}
	
	@Override
	public JPanel getBrowsingPanel() {
		return panel;
	}

	@Override
	public File getExerciseFile() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Exercise Files", "xml");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(panel);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	File choosenFile = chooser.getSelectedFile();
	    	
	    	try {
				Parser parser = new Parser();
				previewPane.setText(parser.parse(new FileInputStream(choosenFile)).getPreview());
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return choosenFile;
	    }
		return null;
	}

}
