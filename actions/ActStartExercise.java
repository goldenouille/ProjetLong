package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import splash.SplashController;

public class ActStartExercise extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private SplashController splash;

	public ActStartExercise(SplashController splash, String string) {
		super(string);
		this.splash = splash;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		splash.doStartExercise();
	}

}