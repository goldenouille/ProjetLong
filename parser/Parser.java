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
			loadXMLRules( new File( "ProjetLong\\parser\\parser.xml" ) );
			System.out.println("rule loaded");
		}
	}

  public Exercise parse (InputStream file) throws IOException, SAXException {
	  Digester digester = new Digester();
	  digester.setValidating( false );
	  digester.addObjectCreate( "exercise", "model.Exercise" );
	  digester.addSetProperties( "exercise" );
	  digester.addObjectCreate( "exercise/part", "model.Part" );
      digester.addSetProperties( "exercise/part" );
      digester.addObjectCreate( "exercise/part/text", "parser.Text" );
      digester.addSetProperties( "exercise/part/text" );
	  digester.addSetNext( "exercise/part/text", "addText");
	  digester.addObjectCreate( "exercise/part/kw", "model.KeyWord" );
      digester.addSetProperties( "exercise/part/kw" );
	  digester.addSetNext( "exercise/part/kw", "addKeyWord");
	  digester.addObjectCreate( "exercise/part/UML", "parser.PseudoGraph" );
	  digester.addObjectCreate( "exercise/part/UML/uml-class", "parser.PseudoClass" );
      digester.addSetProperties( "exercise/part/UML/uml-class" );
	  digester.addSetNext( "exercise/part/UML/uml-class", "addClass");
	  digester.addObjectCreate( "exercise/part/UML/uml-abstract", "parser.PseudoAbstract" );
      digester.addSetProperties( "exercise/part/UML/uml-abstract" );
	  digester.addSetNext( "exercise/part/UML/uml-abstract", "addAbstract");
	  digester.addObjectCreate( "exercise/part/UML/uml-interface", "parser.PseudoInterface" );
      digester.addSetProperties( "exercise/part/UML/uml-interface" );
	  digester.addSetNext( "exercise/part/UML/uml-interface", "addInterface");
	  digester.addObjectCreate( "exercise/part/UML/uml-attribute", "parser.PseudoAttribute" );
      digester.addSetProperties( "exercise/part/UML/uml-attribute" );
	  digester.addSetNext( "exercise/part/UML/uml-attribute", "addAttribute");
	  digester.addObjectCreate( "exercise/part/UML/uml-method", "parser.PseudoMethod" );
      digester.addSetProperties( "exercise/part/UML/uml-method" );
	  digester.addSetNext( "exercise/part/UML/uml-method", "addMethod");
	  digester.addSetNext( "exercise/part/UML", "initGraph");
	  digester.addSetNext( "exercise/part", "addPart");
	  
  	System.out.println("parsing ...");
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
