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
}
