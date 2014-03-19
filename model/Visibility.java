package model;

public enum Visibility {
	PRIVATE("private"),
	PUBLIC("public"),
	PROTECTED("protected"),
	DERIVED("derived"), 
	STATIC("static"),
	PACKAGE("package");
// Derived can be combined with one of the others

	private String name;

	Visibility(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Create the Visibility from its name
	 * 
	 * @param n
	 *            	the name of the Visibility which has to be created
	 * @return the asked Visibility, if the name doesn't correspond to a expected word, the default value is public
	 */
	public static Visibility getByName(String n) {
		if (n.equalsIgnoreCase("private")) {
			return Visibility.PRIVATE;
		}
		if (n.equalsIgnoreCase("public")) {
			return Visibility.PUBLIC;
		}
		if (n.equalsIgnoreCase("protected")) {
			return Visibility.PROTECTED;
		}
		if (n.equalsIgnoreCase("derived")) {
			return Visibility.DERIVED;
		}
		if (n.equalsIgnoreCase("static")) {
			return Visibility.STATIC;
		}
		if (n.equalsIgnoreCase("package")) {
			return Visibility.PACKAGE;
		}
		return Visibility.PUBLIC; 
	}
}