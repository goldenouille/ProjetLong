package parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

import model.*;

import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.xml.sax.SAXException;




public class Parser {
	
	public class MyRulesModule
		extends FromXmlRulesModule
	{

//		@Override
		protected void loadRules()
		{
			loadXMLRules( new File( "/parser.xml" ) );
			System.out.println("rule loaded");
		}
	}

  public Exercise parse (InputStream file) throws IOException, SAXException {
  	DigesterLoader loader = newLoader( new MyRulesModule() );
  	Digester digester= loader.newDigester();
  	digester.setValidating( false );
  	return digester.parse(file);
  }
  
  public void parse (Exercise exo, OutputStream file) {
  	
  	String res = "<exercise name=\"" + exo.getName() + "\">\n";
  	Part p;
  	Word w;
  	boolean isBody = false;
  	int nbKeyWord=0;
  	
  	for (int i=0; i<exo.getParts().size(); i++) {
  		p = exo.getParts().get(i);
  		res += "\t<part name=\"" + p.getName() + "\">\n";
  		for(int j=0; j<p.getText().size(); j++) {
  			w = p.getText().get(j);
  			if (w.isKeyWord()) {
  				if (isBody) {
  					res += " \"/>";
  				}
  				res += "\t\t<kw id='" + nbKeyWord + "' word=\"" + w.getWord() + " \"/>";
  				nbKeyWord++;
  				isBody = false;
  			}
  			else {
  				if (!(isBody)) {
  					res += "<text body=\"";
  				}
  				isBody = true;
  				res += w.getWord() + " ";
  			}
  		}
  		if (isBody) {
  			res += " \"/>";
  		}
  		res ="\n\n";
  		
  		//TODO UML
  	res += "\t</part>";
  	}
  	
  	res += "</exercise>";
  	
  }
}
