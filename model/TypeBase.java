package Model;

public enum TypeBase implements Type {
	CHAR("char",false),
	BYTE("byte",false),
	SHORT("short",false), 
	INT("int",false),
	LONG("long",false),
	FLOAT("float",false),
	DOUBLE("double",false),
	BOOLEAN("boolean",false),
	STRING("string",false),
	VOID("void",false),
	ARRAYLIST("arrayList",true),
	LIST("list",true);

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
}