package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import splash.SplashController;

public class ActBrowseFiles extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private SplashController splash;

	public ActBrowseFiles(SplashController splash, String string) {
		super(string);
		this.splash = splash;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
	    //FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	    //chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(splash);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	splash.setExercise(chooser.getSelectedFile());
	    }
	}

}
