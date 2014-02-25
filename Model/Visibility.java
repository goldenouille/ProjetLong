package Model;

public enum Visibility {
	PRIVATE("-"),
	PUBLIC("+"),
	PROTECTED("#"),
	DERIVED("/"), 
	STATIC("_"),
	PACKAGE("~");
// Derived can be combined with one of the others

	private String symbol;

	Visibility(String symbol) {
		this.symbol = symbol;
	}
}