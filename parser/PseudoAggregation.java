package parser;

import model.UMLNature;
import model.Vertex;

public class PseudoAggregation extends PseudoBinaryAssociation{

	public PseudoAggregation() {
		super();
		this.nature = UMLNature.AGGREGATION;
		this.frenchName = "aggregation";
	}
}
