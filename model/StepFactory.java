package model;

public class StepFactory {

	public Step createStep(String name) {
		switch (variable) {

		case "selectKeyWord" :
 			return new SelectKeyWord();
 			break;

		default: 
		// TODO rajouter une exception
 			return new SelectKeyWord();
 			break;

}
	}
}