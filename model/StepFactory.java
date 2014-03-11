package model;

public class StepFactory {

	public static Step createStep(String name) {
		if (name.equalsIgnoreCase("selectKeyWord")) {
			return new SelectKeyWordStep();
		}
		if (name.equalsIgnoreCase("LinkKeyWordToUML")) {
			return new LinkKeyWordToUMLStep();
		}
		if (name.equalsIgnoreCase("BuildGraphUML")) {
			return new BuildGraphUMLStep();
		}

 		return new SelectKeyWordStep();
 		
	}
}