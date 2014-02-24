import java.util.ArrayList;

public class Vertex implements Type, GraphItem {

	private String name;
	private int id;
	private ArrayList<Method> methods;
	private ArrayList<Edge> edges;

	public Vertex(String name, int id) {
		this.name = name;
		this.id = id;
		this.methods = new ArrayList<Method>();
		this.edges = new ArrayList<Edge>();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

/*	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}
*/	

	public ArrayList<Method> getMethods() {
		return this.methods;
	}

	public void addMethode(Method method) {
		this.methods.add(method);
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}


}