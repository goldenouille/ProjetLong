package launcher;

import java.io.File;

import javax.swing.JPanel;

/**
 * @author Will
 * An exercise browser should allow the user to choose an exercise to play.
 */
public interface ExerciseBrowser {

	/**
	 * Should return a panel to be layed out in the launcher. Can be used to preview the choosen exercise or display a nice chooser.
	 * @return the panel to display
	 */
	public JPanel getBrowsingPanel();
	
	/**
	 * Should return a valid exercise File. Will be called when the user clicks the "select exercise" button.
	 * @return the exercise File
	 */
	public File getExerciseFile();
}
