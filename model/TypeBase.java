package model;

public enum TypeBase implements Type {
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
	LIST("List",true);

	private String name;
	private boolean isList;
	private Type typeList;

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
			// pour l'instant ArrayList et List ne pourront prendre que des types de base
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
		return TypeBase.VOID; // valeur par defaut -> modifer plus tard
	}
}