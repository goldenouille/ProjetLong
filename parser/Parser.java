package parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.*;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;




public class Parser {
	
	private Digester digester;
	
	
	/** This constructor initialize the xml parser with all the rules nedded to create an exercise. 
	 *	 
	 */
	public Parser() {
		this.digester = new Digester();
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
			    	digester.addObjectCreate("exercise/part/UML/uml-method/param", "parser.PseudoParam");
			    	digester.addSetProperties("exercise/part/UML/uml-method/param");
			    	digester.addSetNext("exercise/part/UML/uml-method/param","addParam");
				digester.addSetNext( "exercise/part/UML/uml-method", "addMethod");
				
				digester.addObjectCreate( "exercise/part/UML/uml-realization", "parser.PseudoRealization" );
			    digester.addSetProperties( "exercise/part/UML/uml-realization" );
				digester.addSetNext( "exercise/part/UML/uml-realization", "addRealization");
				digester.addObjectCreate( "exercise/part/UML/uml-aggregation", "parser.PseudoAggregation" );
			    digester.addSetProperties( "exercise/part/UML/uml-aggregation" );
				digester.addSetNext( "exercise/part/UML/uml-aggregation", "addAggregation");
				digester.addObjectCreate( "exercise/part/UML/uml-generalization", "parser.PseudoGeneralization" );
			    digester.addSetProperties( "exercise/part/UML/uml-generalization" );
				digester.addSetNext( "exercise/part/UML/uml-generalization", "addGeneralization");
				digester.addObjectCreate( "exercise/part/UML/uml-dependancy", "parser.PseudoDependancy" );
			    digester.addSetProperties( "exercise/part/UML/uml-dependancy" );
				digester.addSetNext( "exercise/part/UML/uml-dependancy", "addDependancy");
				digester.addObjectCreate( "exercise/part/UML/uml-association", "parser.PseudoBinaryAssociation" );
			    digester.addSetProperties( "exercise/part/UML/uml-association" );
				digester.addSetNext( "exercise/part/UML/uml-association", "addAssociation");
				digester.addObjectCreate( "exercise/part/UML/uml-composition", "parser.PseudoComposition" );
			    digester.addSetProperties( "exercise/part/UML/uml-composition" );
				digester.addSetNext( "exercise/part/UML/uml-composition", "addComposition");
				
			digester.addSetNext( "exercise/part/UML", "initGraph");
		digester.addSetNext( "exercise/part", "addPart");
		  
	}


  /**	This fonction create an exercise from a xml file given
 * @param file the input file
 * @return the exercise created
 * @throws IOException
 * @throws SAXException
 */
public Exercise parse (InputStream file) throws IOException, SAXException {
  	System.out.println("parsing ...");
  	return digester.parse(file);
  }
  

  /**	This fonction create an xml String describing the given exercise
 * @param exo The exercise to parse
 * @return	The string respecting the xml format describing the exercise
 */
public String parse (Exercise exo) {
  	
  	String res = "<exercise name=\"" + exo.getName();
  	if (exo.getPreview()!=null) {
  		res += "\" preview=\""+ exo.getPreview();
  	}
  		res += "\">\n";
  	Part p;
  	Word w;
  	boolean isBody = false;
  	
  	//The loop on the parts
  	for (int i=0; i<exo.getParts().size(); i++) {
  		p = exo.getParts().get(i);
  		res += "\t<part name=\"" + p.getName() + "\">\n\t\t";
  		
  		//The loop on the text
  		for(int j=0; j<p.getText().size(); j++) {
  			w = p.getText().get(j);
  			if (w.isKeyWord()) {
  				if (isBody) {
  					res += " \"/>";
  				}
  				res += "<kw id='" + w.getId() + "' word=\"" + w.getWord() + " \"/>\n\t\t";
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
  		res +="\n\n\t\t<UML>\n";
  		
  		//The loop on the attributes
  		Graph g = p.getGraph();
  		ArrayList<Attribute> al = g.getAttributes();
  		for (int j=0; j<al.size(); j++) {
  			res += "\t\t\t<uml-attribute name= \"" + al.get(j).getName();
  			res += "\" id= '" + al.get(j).getId();
  			res += "' Type= \"" + al.get(j).getType();
  			res += "\" visibility= \"" +al.get(j).getVisibility();
  			res += "\" motherId= '" + al.get(j).getMotherClass().getId() + "' />\n"; 
  		}
  		
  		//The loop on the methods
  		ArrayList<Method> ml = g.getMethods();
  		for (int j=0; j<ml.size(); j++) {
  			res += "\t\t\t<uml-method name= \"" + ml.get(j).getName();
  			res += "\" id= '" + ml.get(j).getId();
  			res += "' Type= \"" + ml.get(j).getReturnType();
  			res += "\" visibility= \"" +ml.get(j).getVisibility();
  			res += "\" motherId= '" + ml.get(j).getMotherClass().getId();
  			if (ml.get(j).getParamType().size() == 0) {
  				res += "' />\n"; 
  			}
  			
  			else {
  				res += "'>\n";
	  			for (int k=0; k<ml.get(j).getParamType().size(); k++) {
	  				res += "\t\t\t\t<param Type = \"" + ml.get(j).getParamType().get(k)  + "\"/>\n";
	  			}
	  			res += "\t\t\t</uml-method>\n";
  			}
  		}
  		
  		//The loop on the Vertex (class, interface, abstract class)
  		ArrayList<Vertex> vl = g.getVertex();
  		for (int j=0; j<vl.size(); j++) {
  			res += "\t\t\t<uml-" + vl.get(j).getUml();
  			res += " name= \"" + vl.get(j).getName();
  			res += "\" id= '" + vl.get(j).getId() + "' />\n";
  		}
  		
  		//The loop on the edges
  		ArrayList<Edge> el = g.getEdges();
  		for (int j=0; j<el.size(); j++) {
  			Edge e = el.get(j);
  			if (e instanceof BinaryAssociation) {
  				res += "\t\t\t<uml-" + e.getUml();
  				res += " name= \"" + e.getName();
  				res += "\" id= '" + e.getId();
  				res += "' source= \"" + ((BinaryAssociation) e).getSource().getId();
  				res += "' target= \"" + ((BinaryAssociation) e).getTarget().getId();
  				res += "' sourceMult= \"" + ((BinaryAssociation) e).getSourceMult();
  				res += "' targetMult= \"" + ((BinaryAssociation) e).getTargetMult() + "\" />\n";
  			}
  			if (e instanceof DirectionalRelation) {
  				res += "\t\t\t<uml-" + e.getUml();
  				res += " name= \"" + e.getName();
  				res += "\" id= '" + e.getId();
  				res += "' source= \"" + ((DirectionalRelation) e).getSource().getId();
  				res += "' target= \"" + ((DirectionalRelation) e).getTarget().getId() + "\" />\n";
  			}
  		}
  		
  		
  		res += "\t\t</UML>\n";
  		
  		
  	res += "\t</part>\n";
  	}
  	
  	res += "</exercise>\n";
  
  	return res;
  }
  
}
