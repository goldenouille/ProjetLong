package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import launcher.LaunchController;


public class ActStartExercise extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private LaunchController splash;

	public ActStartExercise(LaunchController splash, String string) {
		super(string);
		this.splash = splash;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		splash.doStartExercise();
	}

}