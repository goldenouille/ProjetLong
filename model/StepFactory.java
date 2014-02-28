package model;

public class StepFactory {

	public Step createStep(String name) {
		if (name.equals("selectKeyWord")) {
			return new SelectKeyWordStep();
		}

 		return new SelectKeyWordStep();
 		
	}
}