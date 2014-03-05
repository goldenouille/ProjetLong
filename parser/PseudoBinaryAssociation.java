package parser;

import model.UMLNature;
import model.Vertex;

public class PseudoBinaryAssociation extends PseudoAssociation{
	protected int target;
	protected int source;
	protected String targetMult;
	protected String sourceMult;
	
	public PseudoBinaryAssociation() {
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getTargetMult() {
		return targetMult;
	}

	public void setTargetMult(String targetMult) {
		this.targetMult = targetMult;
	}

	public String getSourceMult() {
		return sourceMult;
	}

	public void setSourceMult(String sourceMult) {
		this.sourceMult = sourceMult;
	}
}
