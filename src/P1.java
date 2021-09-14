// P1.java
// Author: Aksel Torgerson
// Email: atorgerson@wisc.edu

// Imports
import java.util.*;

/**
 * Class: P1
 *
 * This class is a test class for SymTable.java and Sym.java.
 */
public class P1 {
    public static void main(String [] args) {
        int numError = 0;
        SymTable S = new SymTable();

        // ----------------------------------------------- Test 1: adding null scope ----------------------------------
        try {
            S.addDecl(null, new Sym(null));
            numError++;
            System.out.println("Error: SymTable.java did not throw an EmptySymTableException!");
        } catch (DuplicateSymException e) {
            numError++;
            System.out.println("Error: SymTable should not contain a duplicate!");
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            // This should be caught.
        }

        // ----------------------------------------------- Test 2: adding duplicate value -----------------------------
        try {
            S.addDecl("1", new Sym("One"));
            S.addDecl("1", new Sym("One"));
        } catch (DuplicateSymException e) {
            // This should be caught.
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: values should not be null!");
        }

        // ----------------------------------------------- Test 3: adding more scopes----------------------------------
        try {
            S.addDecl("3", new Sym("Three"));
            S.addDecl("4", new Sym("Four"));
            S.addDecl("5", new Sym("Five"));
            S.addDecl("6", new Sym("Six"));
            S.addDecl("7", new Sym("Seven"));
            S.addDecl("8", new Sym("Eight"));
            S.addScope();
            S.addDecl("1", new Sym("One"));
            S.addDecl("2", new Sym("Two"));
            S.addDecl("333", new Sym("3x3"));
            S.addDecl("4444", new Sym("4x4"));
            S.addScope();
            S.addScope();
            S.addDecl("1", new Sym("One"));
            S.addDecl("2", new Sym("Two"));
            S.addDecl("3", new Sym("Three"));
            S.addDecl("4", new Sym("Four"));
            S.addDecl("5", new Sym("Five"));
        } catch (DuplicateSymException e) {
            numError++;
            System.out.println("Error: SymTable should not contain a duplicate!");
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 4: removing scopes ------------------------------------
        try {
            S.removeScope();
            S.removeScope();
            S.removeScope();
            S.removeScope();
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 5: removing scope from empty table---------------------
        try {
            S.removeScope();
            numError++;
            System.out.println("Error: SymTable should've been empty!");
        } catch (EmptySymTableException e) {
            // This should be caught.
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 6: adding to empty symTable ---------------------------
        try {
            S.addDecl("1", new Sym("One"));
            numError++;
            System.out.println("Error: SymTable should've been empty!");
        } catch (DuplicateSymException e) {
            numError++;
            System.out.println("Error: SymTable should not contain a duplicate!");
        } catch (EmptySymTableException e) {
            // This should be caught.
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 7: adding lots of data---------------------------------
        try {
            S.addScope();
            S.addDecl("3", new Sym("Three"));
            S.addDecl("4", new Sym("Four"));
            S.addDecl("5", new Sym("Five"));
            S.addDecl("6", new Sym("Six"));
            S.addDecl("7", new Sym("Seven"));
            S.addDecl("8", new Sym("Eight"));
            S.addScope();
            S.addDecl("1", new Sym("One"));
            S.addDecl("2", new Sym("Two"));
            S.addScope();
            S.addScope();
            S.addDecl("1", new Sym("One"));
            S.addDecl("2", new Sym("Two"));
            S.addDecl("3", new Sym("Three"));
            S.addDecl("4", new Sym("Four"));
            S.addDecl("5", new Sym("Five"));
            S.addScope();
            S.addDecl("1", new Sym("One"));
            S.addScope();
            S.addDecl("11", new Sym("OneOne"));
            S.addScope();
            S.addDecl("111", new Sym("OneOneOne"));
            S.addScope();
            S.addDecl("1111", new Sym("OneOneOneOne"));
            S.addDecl("2222", new Sym("TwoTwoTwoTwo"));
        } catch (DuplicateSymException e) {
            numError++;
            System.out.println("Error: SymTable should not contain a duplicate!");
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 8: look up local --------------------------------------
        try {
            if (S.lookupLocal("1111") == null) {
                numError++;
                System.out.println("Error: Key should have been found in SymTable!");
            } else {
                if (S.lookupLocal("1111").getType().compareTo("OneOneOneOne") != 0) {
                    numError++;
                    System.out.println("Error: Incorrect value was returned!");
                }
            }
            if (S.lookupLocal("2222") == null) {
                numError++;
                System.out.println("Error: Key should have been found in SymTable!");
            } else {
                if (S.lookupLocal("2222").getType().compareTo("TwoTwoTwoTwo") != 0) {
                    numError++;
                    System.out.println("Error: Incorrect value was returned!");
                }
            }
            if (S.lookupLocal("3333") != null) {
                numError++;
                System.out.println("Error: Key shouldn't have been found in SymTable!");
            } else {
                if (S.lookupLocal("3333").getType().compareTo("OneOneOneOne") != 0) {
                    numError++;
                    System.out.println("Error: Incorrect value was returned!");
                }
            }
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            // This should get here.
        }

        // ----------------------------------------------- Test 9: removing and checking local-------------------------
        try {
            S.removeScope();
            S.removeScope();
            S.removeScope();
            S.removeScope();

            if (S.lookupLocal("1") == null) {
                numError++;
                System.out.println("Error: Key should have been found in SymTable!");
            }

            if (S.lookupLocal("1").getType().compareTo("One") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupLocal("5") == null) {
                numError++;
                System.out.println("Error: Key should have been found in SymTable!");
            }

            if (S.lookupLocal("5").getType().compareTo("Five") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupLocal("6") != null) {
                numError++;
                System.out.println("Error: Key shouldn't have been found in SymTable!");
            }

        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            numError++;
            System.out.println("Error: Values should not be null!");
        }

        // ----------------------------------------------- Test 10: look up global ------------------------------------
        try {
            S.addDecl("11", new Sym("1x2"));
            S.addDecl("22", new Sym("2x2"));
            S.addDecl("333", new Sym("3x3"));
            S.addDecl("4444", new Sym("4x4"));
            S.addScope();
            S.addDecl("1", new Sym("!"));
            S.addDecl("2", new Sym("@"));
            S.addDecl("3", new Sym("#"));
            S.addDecl("4", new Sym("$"));
            S.addScope();
            S.addDecl("1", new Sym("!!"));
            S.addDecl("5", new Sym("%"));
            S.addDecl("6", new Sym("^"));
            S.addDecl("7", new Sym("&"));
            S.addScope();
            S.addScope();
            S.addDecl("9", new Sym("("));

            if (S.lookupGlobal("9").getType().compareTo("(") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("1").getType().compareTo("!!") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("4").getType().compareTo("$") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("6").getType().compareTo("^") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("8").getType().compareTo("Eight") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("333").getType().compareTo("3x3") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            if (S.lookupGlobal("4444").getType().compareTo("4x4") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }

            // This should throw a null pointer exception.
            if (S.lookupGlobal("DOES NOT EXIST").getType().compareTo("NOTHING") != 0) {
                numError++;
                System.out.println("Error: Incorrect value was returned!");
            }


        } catch (DuplicateSymException e) {
            numError++;
            System.out.println("Error: SymTable should not contain a duplicate!");
        } catch (EmptySymTableException e) {
            numError++;
            System.out.println("Error: SymTable should not be empty!");
        } catch (NullPointerException e) {
            // We should get caught here.
        }


        if (numError == 0) {
            System.out.println("YAHOO! All tests passed!");
        } else {
            System.out.println("Number of errors: " + numError);
        }
    }
}
