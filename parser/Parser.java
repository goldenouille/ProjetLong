package parser;

import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;

package parser;

public class Parser {

	public class MyRulesModule
		extends FromXmlRulesModule
	{

		@Override
		protected void loadRules()
		{
			loadXMLRules( new File( "/parser.xml" ) );
		}
	}

  public Exercise parse (InputStream file) {
  	DigesterLoader loader = newLoader( new MyRulesModule() );
  	Digester digester= loader.newDigester();
  	digester.setValidating( false );
  	return digester.parse(file);
  }
  
  public void parse (Exercise exo, OutputStream file) {
  	
  	private String res = "<exercise name=\"" + exo.getName() + "\">\n";
  	private Part p;
  	private Word w;
  	private boolean isBody = false;
  	private int nbKeyWord=0;
  	
  	for (int i=0; i<exo.getParts.size(); i++) {
  		p = exo.getParts.get(i);
  		res += "\t<part name=\"" + p.getname() + "\">\n";
  		for(int j; j<p.getText.size(); j++) {
  			w = p.getText.get(j);
  			if (w.isKeyWord()) {
  				if isBody {
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
