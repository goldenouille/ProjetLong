package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import parser.Parser;
import controller.ModelController;
import model.*;

import gui.ClassicGuiController;

public class ModelController {
	private ClassicGuiController cgc;
	private Parser p;
	private StepFactory sf;
	private Exercise exo;

	public void setCGC(ClassicGuiController cgc) {
		this.cgc = cgc;
		this.sf = new StepFactory();
	}

	public void setParser(Parser p) {
		this.p = p;
	}

	public void setExercise(Exercise exo) {
		this.exo = exo;
	}

	public void askSelectText(int[] pourcentages, boolean userText) {
		System.out.println(Arrays.toString(pourcentages));
		exo.selectText(pourcentages, userText);
	}

	public void askUnSelectText(int[] pourcentages, boolean userText) {
		exo.unselectText(pourcentages, userText);
	}

	public Object[] askUmlInstancesNatures() {
		Object[] natures = { UMLNature.CLASS, UMLNature.ABSTRACT_CLASS, UMLNature.INTERFACE, UMLNature.ATTRIBUTE, UMLNature.METHOD };
		return natures;
	}

	public void askValidateKeywords() {
		//System.out.println("Correction de " + ((Step) s).getFrenchName());
		SelectKeyWordStep.getCorrection(exo);
	}
	
	public void askValidateAssociation() {
		//System.out.println("Correction de " + ((Step) s).getFrenchName());
		LinkKeyWordToUMLStep.getCorrection(exo);
	}
	
	public void askValidateDiagram() {
		//System.out.println("Correction de " + ((Step) s).getFrenchName());
		//((Step) s).getCorrection(exo);

		// step pas encore implemente
	}

	public void askAddText(String text, String comment) {
		System.out.println("askAddText " + text + "\n" + comment);
		exo.addText(text);
	}

	public String askUmlInstanceName(int firstWord, int lastWord, boolean userText, Object nature) {
		// TODO Auto-generated method stub
		return null;
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateClass(int firstWord, int lastWord, boolean userText, String name) {
		Vertex v = exo.addClass(firstWord,lastWord,userText,name);
		cgc.doAddElementToPool(v, UMLNature.CLASS);
		System.out.println("askCreateClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateAbstractClass(int firstWord, int lastWord, boolean userText, String name) {
		Vertex v = exo.addAbstractClass(firstWord, lastWord, userText, name);
		cgc.doAddElementToPool(v, UMLNature.ABSTRACT_CLASS);
		System.out.println("askCreateAbstractClass " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateInterface(int firstWord, int lastWord, boolean userText, String name) {
		Vertex v = exo.addInterface(firstWord, lastWord, userText, name);
		cgc.doAddElementToPool(v, UMLNature.INTERFACE);
		System.out.println("askCreateInterface " + name);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	public void askCreateAttribute(int firstWord, int lastWord, boolean userText, String name, String type, String visibility) {
		Attribute a = exo.addAttribute(firstWord, lastWord, userText, name, type, visibility);
		cgc.doAddElementToPool(a, UMLNature.Attribute);
		System.out.println("askCreateAttribute " + name + " " + type + " " + visibility);
	}

	// BEAUCOUP DE QUESTIONS EN SUSPENT ICI
	// pour l'instant ne peut prendre que des types de base comme type de retour et comme parametres
	public void askCreateMethod(int firstWord, int lastWord, boolean userText, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		Method m = exo.addMethod(firstWord, lastWord, userText, name,  paramTypes, returnType, visibility);
		cgc.doAddElementToPool(m, UMLNature.METHOD);
		System.out.println("askCreateMethod " + name + " " + paramTypes.toString() + " " + returnType + " " + visibility);
	}

	public void doPrintMessage(String title, String message) {
		cgc.doPrintMessage(title, message);
	}

	public void doSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doSelectText(firstWord, lastWord, userText);
	}

	public void doUnSelectText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnSelectText(firstWord, lastWord, userText);
	}

	public void doValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doValidateText(firstWord,lastWord,userText);
	}

	public void doUnValidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnValidateText(firstWord, lastWord, userText);
	}

	public void doInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doInvalidateText(firstWord, lastWord, userText);
	}

	public void doUnInvalidateText(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doUnInvalidateText(firstWord, lastWord, userText);
	}
	
	public void doResetTextHighlight(int firstWord, int lastWord, boolean userText) throws BadLocationException {
		cgc.doResetTextHighlight(firstWord, lastWord, userText);
	}

	public void doShowUmlInstanceCreationPopup(int firstWord, int lastWord, Object nature) 
																			throws BadLocationException {
		cgc.doShowUmlInstanceCreationPopup(firstWord, lastWord, nature);
	}

	public void doShowUmlInstanceEditionPopup(Object id, Object nature) throws BadLocationException {
		cgc.doShowUmlInstanceEditionPopup(id, nature);
	}

	public void doShowTextAdditionPopup() {
		cgc.doShowTextAdditionPopup();
	}

	public void doAddText(boolean userText, String[] text) {
		cgc.doAddText(userText, text);
	}

	public void doSetTextFont(Font font) {
		cgc.doSetTextFont(font);
	}

	public void doShowMissingKeywordNumber(int nb) {
		cgc.doShowMissingKeywordNumber(nb);
	}


	// ******************************************************************************************************//
	// *****************************************Navigation_methods*******************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// fonction appelee pas encore implementee
	public void askSelectPart(Object part) {
		exo.selectPart(part);
		System.out.println("askSelectPart " + part.toString());
	}

	// fonction appelee pas encore implementee
	public void askCorrectStep(Object step) {
		System.out.println("askCorrectStep " + step.toString());
	}

	// fonction appele pas encore implementee
	public Object askNextPart() {
		return exo.nextPart();
	}

	public Object askNextStep() {
		return exo.nextStep();
	}


	// ****************************************************************************************************//
	// *********************************************Timer_methods******************************************//
	// ****************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// Timer pas encore gere, a implementer dans ce fichier
	public String askTimer() {
		// TODO Auto-generated method stub
		return "0";
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Font askTimerFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Color askTimerBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	public void doSetTimer(String timer) {
		cgc.doSetTimer(timer);
	}

	public void doSetTimerFont(Font font) {
		cgc.doSetTimerFont(font);
	}

	public void doSetTimerBgColor(Color color) {
		cgc.doSetTimerBgColor(color);
	}


	// ******************************************************************************************************//
	// *******************************************Score_methods**********************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	// fonction avec valeur par defaut pour l'instant
	public String askScore() {
		return exo.askScore();
	}

	// pour l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Font askScoreFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 40);
	}

	// par l'instant valeur par defaut... mais d'ou le controleur du model est suppose trouver cette info?
	public Color askScoreBgColor() {
		// TODO Auto-generated method stub
		return Color.GRAY;
	}

	// ***//
	// Do //
	// ***//

	public void doSetScore(String score) {
		cgc.doSetScore(score);
	}

	public void doSetScoreFont(Font font) {
		cgc.doSetScoreFont(font);
	}

	public void doSetScoreBgColor(Color color) {
		cgc.doSetScoreBgColor(color);
	}


	// ******************************************************************************************************//
	// ******************************************Uml_section_methods*****************************************//
	// ******************************************************************************************************//

	// ****//
	// Ask //
	// ****//

	public void askEditClass(Object id, String name) {
		if (id instanceof VertexClass) {
			((VertexClass) id).setName(name);
		}	
		System.out.println("askEditClass " + name);
	}


	public void askEditAbstractClass(Object id, String name) {
		if (id instanceof VertexAbstract) {
			((VertexAbstract) id).setName(name);
		}	

		System.out.println("askEditAbstractClass " + name);
	}


	public void askEditInterface(Object id, String name) {
		if (id instanceof Vertex) {
			((Vertex) id).setName(name);
		}	

		System.out.println("askEditInterface " + name);
	}


	public void askEditAttribute(Object id, String name, String type, String visibility) {
		if (id instanceof Attribute) {
			((Attribute) id).setName(name);
			((Attribute) id).setType(TypeBase.getByName(type));
			((Attribute) id).setVisibility(Visibility.getByName(visibility));
		}	

		System.out.println("askEditAttribute " + name + " " + type + " " + visibility);
	}


	public void askEditMethod(Object id, String name, ArrayList<String> paramTypes, String returnType, String visibility) {
		if (id instanceof Method) {
			((Method) id).setName(name);
			((Method) id).setReturnType(TypeBase.getByName(returnType));
			((Method) id).setVisibility(Visibility.getByName(visibility));
		}	

		System.out.println("askEditMethod " + name + " " + paramTypes.toString() + " " + returnType + " " + visibility);
	}


	public void askDeleteClass(Object id) {
		//core.askDeleteClass(id);
		if (id instanceof VertexClass) {
			exo.askDeleteClass((VertexClass) id);
		}
	}

	/**
	 * Sends to the core the user's request to delete the given abstract class
	 * instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteAbstractClass(Object id) {
		//core.askDeleteAbstractClass(id);
		if (id instanceof VertexAbstract) {
			exo.askDeleteAbstractClass((VertexAbstract) id);
		}
		// System.out.println("askDeleteAbstractClass");
	}

	/**
	 * Sends to the core the user's request to delete the given interface instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteInterface(Object id) {
		//core.askDeleteInterface(id);
		if (id instanceof Vertex) {
			exo.askDeleteInterface((Vertex) id);
		}
		// System.out.println("askDeleteInterface");
	}

	/**
	 * Sends to the core the user's request to delete the given attribute instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteAttribute(Object id) {
		//core.askDeleteAttribute(id);
		if (id instanceof Attribute) {
			exo.askDeleteAttribute((Attribute) id);
		}
		// System.out.println("askDeleteAttribute");
	}

	/**
	 * Sends to the core the user's request to delete the given method instance
	 * 
	 * @param id
	 *            identifier of the instance to delete
	 */
	public void askDeleteMethod(Object id) {
		//core.askDeleteMethod(id);
		if (id instanceof Method) {
			exo.askDeleteMethod((Method) id);
		}
		// System.out.println("askDeleteMethod");
	}

	public void askLinkAttributeToClass(Object attributeID, Object classID) {
		if ((attributeID instanceof Attribute) && (classID instanceof VertexClass)) {
			exo.askLinkAttributeToClass((Attribute) attributeID,(VertexClass) classID);
		}
	}
	
	public void askUnLinkAttributeToClass(Object attributeID, Object classID) {
		//if ((attributeID instanceof Attribute) && (classID instanceof VertexClass)) {
		//	exo.askUnLinkAttributeToClass(attributeID, classID);
		//}	
	}

	/**
	 * Sends to the core the user's request to link the given method instance to the given class instance
	 * 
	 * @param methodID
	 *            identifier of the method instance
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askLinkMethodToClass(Object methodID, Object classID) {
		if ((methodID instanceof Method) && (classID instanceof Vertex)) {
			exo.askLinkMethodToClass((Method) methodID, (Vertex) classID);
		}
	}

	public void askUnLinkMethodToClass(Object methodID, Object classID) {
		//core.askUnLinkMethodToClass(methodID, classID);
	}
	
	/**
	 * Sends to the core the user's request to unlink the given method instance to the given class instance
	 * 
	 * @param claasID
	 *            identifier of the class instance
	 */
	public void askUnLinkAllElementToClass(Object classID) {
		//core.askUnLinkAllElementToClass(classID);
	}

		/**
	 * Ask the core about the name of the Uml instance corresponding to the
	 * defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return name of the instance
	 */
	public String askUmlInstanceName(Object id) {
		if (id instanceof GraphItem) {
			return ((GraphItem) id).getName();
		}

		return "askUmlInstanceName " + id.toString();
	}

	/**
	 * Ask the core about the type or return type of the Uml instance
	 * corresponding to the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return type or return type of the instance
	 */
	public String askUmlInstanceType(Object id) {
		if (id instanceof Attribute) {
			return ((Attribute) id).getType().getName();
		}
		if (id instanceof Method) {
			return ((Method) id).getReturnType().getName();
		}
		return "askUmlInstanceType " + id.toString();
	}


		/**
	 * Ask the core about the visibility of the Uml instance corresponding to
	 * the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return visibility of the instance
	 */
	public String askUmlInstanceVisibility(Object id) {
		if (id instanceof Attribute) {
			return ((Attribute) id).getVisibility().getName();
		}
		if (id instanceof Method) {
			return ((Method) id).getVisibility().getName();
		}

		return "askUmlInstanceVisibility " + id.toString();
	}



	/**
	 * Ask the core about the param types of the Uml instance corresponding to
	 * the defined keyword
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 * @return param types of the instance
	 */
	public ArrayList<String> askUmlInstanceParamTypes(Object id) {
		if (id instanceof Method) {
			ArrayList<String> stringParams = new ArrayList<String>();
			ArrayList<Type> params = ((Method) id).getParamType();
			for (int i = 0; i<params.size();i++) {
				stringParams.add(params.get(i).getName());
			}
			return stringParams;
		}


		return null;
	}

		/**
	 * Sends to the core the user's request to create a new relation
	 * 
	 * @param nature
	 *            relation nature
	 * @param classesID
	 *            classes list linked with this relation
	 * @param multiplicity
	 *            multiplicity list for each class
	 * @param text
	 *            text of relation
	 */


	public void askCreateRelation(Object nature, ArrayList<Object> classesID, ArrayList<String> multiplicity, String text) {
		if (nature instanceof UMLNature) {
			ArrayList<Vertex> v = new ArrayList<Vertex>();
			for (Object object : classesID) {
    			v.add(object instanceof Vertex ? (Vertex) object : null); // enfait il faudrait throw
			}									// une exception, si on tombe sur le cas -> nullPointerException
			exo.askCreateRelation((UMLNature) nature, v, multiplicity, text);
		}


		System.out.println("askCreateRelation");
	}

	public void askDeleteRelation(Object id) {
		if (id instanceof Edge) {
			exo.askDeleteRelation((Edge)id);
		}
		
	}


	public ArrayList<Object> askUMLRelationClasses(Object id) {
		if (id instanceof Edge) {
			ArrayList<Vertex> l = exo.askUMLRelationClasses((Edge) id);
			ArrayList<Object> r = new ArrayList<Object>();
			for (Vertex v : l) {
				r.add((Object) v);
			}
			return r;
		}
	}

	public ArrayList<String> askUMLRelationMultiplicity(Object id) {
		if (id instanceof Edge) {
			return exo.askUMLRelationMultiplicity((Edge) id);
		}
	}

	public void askReverseRelation(Object id) {
		if ((id instanceof Edge) && (!(id instanceof NaryAssociation))) {
			exo.askReverseRelation((Edge) id);
		}
	}

		/**
	 * Change color of UML instance to red in element pool
	 * 
	 * @param id
	 *            identifier of the instance to edit
	 */
	public void doShowUMLInstanceInRed(Object id) {
		cgc.doShowUMLInstanceInRed(id);
	}
	
	/**
	 * Reset color of UML instance to default in element pool
	 * 
	 * @param id
	 *            identifier of the instance to reset
	 */
	public void doResetUMLInstanceColor(Object id) {
		cgc.doResetUMLInstanceColor(id);
	}

	/**
	 * Reset color of UML instances to black in element pool
	 */
	public void doResetUMLInstanceColor() {
		cgc.doResetUMLInstanceColor();
	}

}


