package parser;

import model.UMLNature;

public class PseudoComposition extends PseudoBinaryAssociation{

        public PseudoComposition() {
                super();
                this.nature = UMLNature.COMPOSITION;
        		this.frenchName = "Composition";
        }
}
