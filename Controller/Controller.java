import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Controler {

	private ModelControler modelControler;
	private ClassicGuiController guiControler;
	private Parser parser;

	public Controler() {
		this.modelControler = new ModelControler();
		this.guiControler = new ClassicGuiController(this.modelControler);
		this.modelControler.setCGC(this.guiControler);
		this.parser = new Parser();
		this.modelControler.setParser(this.parser);
	}
}