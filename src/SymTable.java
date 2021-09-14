// SymTable.java
// Author: Aksel Torgerson
// Email: atorgerson@wisc.edu

// Imports
import java.util.*;

/**
 * Class: SymTable
 * This class represents a symbol table.
 */
public class SymTable {

	// Private LinkedList holding the HashMap for each scope.
	// A LinkedList will be used because of the need to add to the front of the list.
	private LinkedList<HashMap<String, Sym>> symList;

	/**
	 * Create a symList that holds one empty HashMap.
	 */
	public SymTable(){
		this.symList = new LinkedList<HashMap<String, Sym>>();
		this.symList.add(new HashMap<String, Sym>());
	}

	/**
	 * Add a given name and sym to the first HashMap in the symList.
	 * @param name
	 * @param sym
	 * @throws DuplicateSymException
	 * @throws EmptySymTableException
	 */
	public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException {
		if (this.symList.isEmpty()) {
			throw new EmptySymTableException();
		} else if (name == null || sym == null) {
			throw new NullPointerException();
		} else if (this.symList.getFirst().containsKey(name)) {
			throw new DuplicateSymException();
		} else {
			// Add the given <K, V> pair to the first HashMap in symList.
			this.symList.getFirst().put(name, sym);
		}
	}

	/**
	 * Add a new, empty scope to the front of the symList.
	 */
	public void addScope() {
		HashMap<String, Sym> newMap = new HashMap<String, Sym>();
		this.symList.addFirst(newMap);
	}

	/**
	 * Find a sym given the name in the local scope (first HashMap in symList).
	 * @param name
	 * @return Matching value given the key, name.
	 * @throws EmptySymTableException
	 */
	public Sym lookupLocal(String name) throws EmptySymTableException {
		if (this.symList.isEmpty()) {
			throw new EmptySymTableException();
		} else if (this.symList.getFirst().containsKey(name)) {
			return this.symList.getFirst().get(name);
		} else {
			return null;
		}
	}

	/**
	 * Find a sym given the name in the global scope (all HashMaps in symList).
	 * @param name
	 * @return First matching value given the key, name.
	 * @throws EmptySymTableException
	 */
	public Sym lookupGlobal(String name) throws EmptySymTableException {
		if (this.symList.isEmpty()) {
			throw new EmptySymTableException();
		} else {
			for (int i = 0; i < this.symList.size(); i++) {
				// Find the first HashMap that contains the key 'name' in the symList.
				if (this.symList.get(i).containsKey(name)) {
					// Return the matching value, return from the function immediately.
					return this.symList.get(i).get(name);
				}
			}
			// Return null if no match is found in any HashMap.
			return null;
		}
	}

	/**
	 * Remove the scope at the front of the symList.
	 * @throws EmptySymTableException
	 */
	public void removeScope() throws EmptySymTableException {
		if (this.symList.isEmpty()) {
			throw new EmptySymTableException();
		} else {
			this.symList.removeFirst();
		}
	}

	/**
	 * Used for debugging. Print out all symbols in the symList.
	 */
	public void print() {
		System.out.print("\nSym Table\n");
		for (int i = 0; i < this.symList.size(); i++) {
			System.out.print(this.symList.get(i).toString() + "\n");
		}
		System.out.print("\n");
	}

}
