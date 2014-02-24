import java.util.ArrayList;

public class VertexClass extends Vertex {

	private ArrayList<Attribute> attributes;
	private boolean isAbstract;

	public VertexClass(String name, int id, boolean isAbstract) {
		super(name, id);
		this.attributes = new ArrayList<Attribute>();
		this.isAbstract = isAbstract;
	}

	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	public void addAttributes(Attribute attribute) {
		this.attributes.add(attribute);
	}

	public boolean isAbstract() {
		return this.isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

}