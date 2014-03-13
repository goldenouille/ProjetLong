package model;

import java.util.ArrayList;

import controller.ModelController;

public class BuildGraphUMLStep extends Step {

	public BuildGraphUMLStep() {
		this.name = "BuildGraphUMLStep";
		this.frenchName = "Construction du graphe";
	}
	
	public static void getCorrection(Exercise exo) {
		ArrayList<Word> text = exo.getText();
		ModelController mc = exo.getModelController();
		Score score = exo.getScore();
		String errormsg="";
		Graph graph = exo.getGraph();
		Graph usergraph = exo.getUserGraph();
		ArrayList<Edge> edges = graph.getEdges();
		ArrayList<Edge> useredges = usergraph.getEdges();

		mc.doResetUMLDrawingColor();
		int missing = mc.doGetMissingUmlDrawingElementNumber();
		if (missing != 0) {
			errormsg += mc.doGetMissingUmlDrawingElementNumber() + " n'ont pas ete dessines.\n";
		}
		
		for (int i = 0; i< text.size() ; i++) {
			Word word=text.get(i);
			GraphItem gi = exo.getCurrentPart().getIdTable().get(text.get(i).getId());
			if (word.isKeyWord()) {
				boolean error=false;
				switch (gi.getUmlNature()) {
				case ATTRIBUTE :
					Attribute attribute = ((Attribute)gi);
					Attribute userattribute = ((Attribute)word.getUserGraphItem());
					if (userattribute.getMotherClass()==null){
						error=true;
						errormsg+=("l'attribut "+attribute.getName()+" n'est pas placé.\n");
					}
					else if (attribute.getType()==userattribute.getType()) {
						error=true;
						errormsg+=("le type de retour de "+attribute.getName()+" est faux.\n");
					}
					else if (attribute.getMotherClass().getId()!=userattribute.getMotherClass().getId()) {
						error=true;
						errormsg+=("la classe de "+attribute.getName()+" est fausse.\n");
					}
					if (error) {
						mc.doShowUMLDrawingInErrorColor(userattribute,UMLNature.ATTRIBUTE);
						score.removeScoreGraph(attribute.getScore()/2);
					}
					break;
				case METHOD :
					Method method = ((Method)gi);
					Method usermethod = ((Method)word.getUserGraphItem());
					if (usermethod.getMotherClass()==null){
						error=true;
						errormsg+=("la methode "+method.getName()+" n'est pas placé.\n");
					}
					else if (method.getReturnType()==usermethod.getReturnType()) {
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
						score.removeScoreGraph(method.getScore()/2);
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
		if (errormsg.equals("")) {
			mc.doPrintMessage("Succes !", "Vous pouvez passer a la suite.");
		} else {
			mc.doPrintMessage("Echec", errormsg);
		}
		mc.doSetScore(score.getCurrScore() + "/" + score.getScoreMax());
	}

	private static boolean compare(Edge useredge, ArrayList<Edge> edges){
		boolean correct=false;
		for (Edge edge:edges){
			if (edge.getNature()==useredge.getNature()&&!edge.getIsCorrected()){
				ArrayList<Vertex> edgevertex=edge.getVertex();
				ArrayList<Vertex> useredgevertex=edge.getVertex();
				if (edgevertex.size()==useredgevertex.size()){
					correct=true;
					for (int i = 0; i< edgevertex.size() ; i++){
						if (edgevertex.get(i).getId()!=useredgevertex.get(i).getId()){
							correct = false;
						}
					}
					ArrayList<String> edgemultiplicity = edge.getMultiplicity();
					ArrayList<String> useredgemultiplicity = edge.getMultiplicity();
					if (edgemultiplicity.size()==useredgemultiplicity.size()){
						for (int i = 0; i< edgemultiplicity.size() ; i++){
							if (!edgemultiplicity.get(i).equals(useredgemultiplicity.get(i))){
								correct = false;
							}
						}
					}
				}
				if (correct) {
					edge.setIsCorrected(true);
					useredge.setIsCorrected(true);
					return true;
				}
			}
		}
		return false;
	}
	

	public String toString() {
		return this.frenchName;
	}
}
