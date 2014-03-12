package model;

public class Heritage extends DirectionalRelation {

	/**
	 * Create a Heritage
	 * 
	 * @param trg
	 *            	'target' Vertex of the heritage edge 
	 * @param src
	 *				'source' Vertex of the heritage edge
	 * @param id
	 *				id of the heritage edge
	 * @param name
	 *				name of the heritage edge
	 */
	public Heritage(Vertex trg, Vertex src, int id, String name) {
		super(trg,src,id,name);
	}
}