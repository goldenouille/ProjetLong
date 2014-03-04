package parser;

import gui.ClassicGuiController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

import controller.ModelController;

import model.Exercise;
import controller.*;

public class test {

	public static void main (String[] args) throws FileNotFoundException, IOException, SAXException {
		Parser p = new Parser();
		Exercise e = p.parse(new FileInputStream(args[0]));
		
		/*ModelController mc = new ModelController();
	   mc.setExercise(e);
		
		ClassicGuiController gc = new ClassicGuiController(mc);
		mc.setCGC(gc);
		
		e.init();
		*/
		System.out.println(p.parse(e));
	}
}
