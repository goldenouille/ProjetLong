package model;

import java.util.ArrayList;

import controller.ModelController;

public class BuildGraphUMLStep extends Step {

	public BuildGraphUMLStep() {
		this.name = "BuildGraphUMLStep";
		this.frenchName = "Construction du graphe UML.";
	}
	
	public static void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		Score score = exo.getScore();
		ModelController mc = exo.getModelController();
		String errormsg="";
		Graph graph = exo.getGraph();
		Graph usergraph = exo.getUserGraph();
		ArrayList<Edge> edges = graph.getEdges();
		ArrayList<Edge> useredges = usergraph.getEdges();

		mc.doResetUMLDrawingColor();
		errormsg += mc.doGetMissingUmlDrawingElementNumber() + " n'ont pas ete dessines.\n";
		
		for (int i = 0; i< text.size() ; i++) {
			Word word=text.get(i);
			if (word.isKeyWord()) {
				boolean error=false;
				switch (word.getUmlNature()) {
				case ATTRIBUTE :
					Attribute attribute = ((Attribute)word.getGraphItem());
					Attribute userattribute = ((Attribute)word.getUserGraphItem());
					if (attribute.getType()!=userattribute.getType()) {
						error=true;
						errormsg+=("le type de "+attribute.getName()+" est faux.\n");
					}
					if (attribute.getMotherClass().getId()==userattribute.getMotherClass().getId()) {
						error=true;
						errormsg+=("la classe de "+attribute.getName()+" est fausse.\n");
					}
					if (error) {
						mc.doShowUMLDrawingInErrorColor(userattribute,UMLNature.ATTRIBUTE);
					}
					break;
				case METHOD :
					Method method = ((Method)word.getGraphItem());
					Method usermethod = ((Method)word.getUserGraphItem());
					if (method.getReturnType()==usermethod.getReturnType()) {
						error=true;
						errormsg+=("le type de retour de "+method.getName()+" est faux.\n");
					}
					
					if (method.getMotherClass().getId()==usermethod.getMotherClass().getId()){
						error=true;
						errormsg+=("la classe de "+method.getName()+" est fausse.\n");
					}
					if (method.getParamType().containsAll(usermethod.getParamType())&&usermethod.getParamType().containsAll(method.getParamType())){
						error=true;
						errormsg+=("les types d'entrees de "+method.getName()+" sont faux.\n");
					}
					if (error) {
						mc.doShowUMLDrawingInErrorColor(usermethod,UMLNature.METHOD);
					}
					break;
				default :
					break;
				}
				for (Edge useredge:useredges){
					if (!compare(useredge,edges)){
						//warn error !
					}
				}
			}
		}
		if (errormsg=="") {
			mc.doPrintMessage("Succes !", "Vous pouvez passer a la suite.");
		} else {
			mc.doPrintMessage("Echec", errormsg);
		}
	}

	private static boolean compare(Edge useredge, ArrayList<Edge> edges){
		return true;
	}
	
	public String toString() {
		return this.frenchName;
	}
}
