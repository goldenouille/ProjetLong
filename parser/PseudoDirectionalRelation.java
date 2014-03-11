package parser;

public class PseudoDirectionalRelation extends PseudoEdge {
	protected int target;
	protected int source;
	
	public PseudoDirectionalRelation() {
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
}
