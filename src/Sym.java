// Sym.java
// Author: Aksel Torgerson
// Email: atorgerson@wisc.edu

/**
 * Class: Sym
 * This class represents a symbol in the symbol table.
 */
public class Sym {

  // Private identifier to represent the type of the symbol.
  private String type;

  /**
   * Initialize the type upon creation of a Sym.
   * @param type
   */
	public Sym(String type) {
    this.type = type;
	}

  /**
   * Getter for the type field.
   * @return type
   */
	public String getType() {
	  return this.type;
	}

  /**
   * Public toString() method.
   * @return string representing the Sym.
   */
	public String toString() {
		return type;
	}
}
