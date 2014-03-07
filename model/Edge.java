package model;

import java.util.ArrayList;

public class Edge implements GraphItem{

	protected String name;
	protected int id;
	protected String frenchName;
	protected UMLNature nature;
	
	public Edge() {
		this.id = -1;
	}

	public UMLNature getNature() {
		return nature;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getUml() {
		return null;
	}

	public int getId() {
		return id;
	}
	
	public ArrayList<Vertex> getVertex() {
		return new ArrayList<Vertex>();
	}

	public ArrayList<String> getMultiplicity() {
		return new ArrayList<String>();
	}


	public static Edge createEdge(UMLNature nature,ArrayList<Vertex> v,ArrayList<String> multiplicity,String name, int id) {
		if (nature.equals(UMLNature.AGGREGATION)) {
			return (new Aggregation(v.get(0), multiplicity.get(0), v.get(1), multiplicity.get(1), id, name));
		}
		if (nature.equals(UMLNature.ASSOCIATION)) {
			System.out.println("Une association n'est pas suffisamment specifie pour etre instancie");
			return null;
		}
		if (nature.equals(UMLNature.COMPOSITION)) {
			return (new Composition(v.get(0), multiplicity.get(0), v.get(1), multiplicity.get(1), id, name));
		}
		if (nature.equals(UMLNature.DEPENDANCY)) {
			return (new Dependancy(v.get(0), v.get(1), id, name));
		}
		if (nature.equals(UMLNature.GENERALIZATION)) {
			return (new Generalization(v.get(0), v.get(1), id, name));
		}
		if (nature.equals(UMLNature.N_ASSOCIATION)) {
			System.out.println("La creation des association n-aire n'est pas gere pour l'instant");
			return null;
		}
		if (nature.equals(UMLNature.REALIZATION)) {
			return (new Realization(v.get(0), v.get(1), id, name));
		}
		return null;
	}

	@Override
	public UMLNature getUmlNature() {
		return this.nature;

	}

}
