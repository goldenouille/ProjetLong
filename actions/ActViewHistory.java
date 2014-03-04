package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import splash.SplashController;

public class ActViewHistory extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private SplashController splash;
	

	public ActViewHistory(SplashController splash, String string) {
		super(string);
		this.splash = splash;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		splash.doShowHistory();
	}

}
