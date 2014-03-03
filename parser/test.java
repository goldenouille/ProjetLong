package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

import model.Exercise;

public class test {

	public static void main (String[] args) throws FileNotFoundException, IOException, SAXException {
		Parser p = new Parser();
		Exercise e = p.parse(new FileInputStream(args[0]));
		System.out.println(e);
	}
}
