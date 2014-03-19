package model;

// TypeBase is a class reprensenting the basic java types
public enum TypeBase implements Type {
				// list of the constances with their name and if they are a list or not
	CHAR("char",false),
	BYTE("byte",false),
	SHORT("short",false), 
	INT("int",false),
	LONG("long",false),
	FLOAT("float",false),
	DOUBLE("double",false),
	BOOLEAN("boolean",false),
	STRING("String",false),
	VOID("void",false),
	ARRAYLIST("ArrayList",true),
	LIST("List",true),
	ANY("Any",false);

	private String name;	// java name of the type
	private boolean isList;	// if the type is a list or not
	private Type typeList;	// type of the list

	/**
	 * Create a TypeBase with its name and if it is a list or not
	 *
	 * @param name
	 *			java name of the created type
	 * @param b
	 *			is true if the created TypeBase is a list
	 */
	TypeBase(String name, boolean b) {
		this.name = name;
		this.isList = b;
		this.typeList = null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean getIsList() {
		return this.isList;
	}

	public void setIsList(boolean b) {
		this.isList = b;
	}

	public void setTypeList(Type t) {
		this.typeList = t;
	}

	public Type getTypeList() {
		return this.typeList;
	}
	
	/**
	 * Compare a type to another
	 * 
	 * @param type
	 *			type to which it has to be compare
	 * @return if they are equals
	 */
	public boolean compareTo(Type type) {
		return (this.getName().equalsIgnoreCase(type.getName()) || this.getName().equalsIgnoreCase("any"));
	}

	/**
	 * Verify if the TypeBase is valide with regard to the structure of a list
	 */
	public boolean isValide() {
		if (this == TypeBase.LIST || this == TypeBase.ARRAYLIST) {
			if (this.typeList == TypeBase.LIST || this.typeList == TypeBase.ARRAYLIST) {
				return ((TypeBase) this.typeList).isValide() && this.isList;
			} else {
				return (this.typeList != null && this.typeList != TypeBase.VOID && this.isList);
			}
		} else {
			return !(this.isList);
		}
	}

	/**
	 * Create one of the TypeBase constants from its java name
	 *
	 * @param s
	 *			java name of the TypeBase constant which has to be created
	 * @return the TypeBase corresponding to the input string name, the default value is TypeBase.ANY
	 */
	public static TypeBase getByName(String s) {
		if (s.equalsIgnoreCase("char")) {
			return TypeBase.CHAR;
		}
		if (s.equalsIgnoreCase("byte")) {
			return TypeBase.BYTE;
		}
		if (s.equalsIgnoreCase("short")) {
			return TypeBase.SHORT;
		}
		if (s.equalsIgnoreCase("int")) {
			return TypeBase.INT;
		}
		if (s.equalsIgnoreCase("String")) {
			return TypeBase.STRING;
		}
		if (s.equalsIgnoreCase("long")) {
			return TypeBase.LONG;
		}
		if (s.equalsIgnoreCase("float")) {
			return TypeBase.FLOAT;
		}
		if (s.equalsIgnoreCase("double")) {
			return TypeBase.DOUBLE;
		}
		if (s.equalsIgnoreCase("boolean")) {
			return TypeBase.BOOLEAN;
		}
		if (s.equalsIgnoreCase("void")) {
			return TypeBase.VOID;
		}
			// in this version of LUNE, List and ArrayList only take basic Java Type, which are TypeBas
		if (s.startsWith("ArrayList")) {
			String[] t = s.split("<");
			String[] tt = (t[2]).split(">");
			TypeBase tList = getByName(tt[0]);
			TypeBase arrayList = TypeBase.ARRAYLIST;
			arrayList.setTypeList(tList);
			return arrayList;
		}
		if (s.startsWith("List")) {
			String[] t = s.split("<");
			String[] tt = (t[2]).split(">");
			TypeBase tList = getByName(tt[0]);
			TypeBase list = TypeBase.LIST;
			list.setTypeList(tList);
			return list;
			
		}
		return TypeBase.ANY; // default value
	}
}