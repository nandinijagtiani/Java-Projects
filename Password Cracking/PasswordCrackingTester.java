////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Password Cracking
// Course: CS 300 Spring 2023
//
// Author: Nandini jagtiani
// Email: jagtiani@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: No helped was given or received (identify each by name and describe how they helped)
// Online Sources: No helped was given or received (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the protected data
   * field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * 
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {
    // Create PasswordStorage objects with different Attributes
    PasswordStorage storageOccurrence = new PasswordStorage(Attribute.OCCURRENCE);
    PasswordStorage storageStrengthRating = new PasswordStorage(Attribute.STRENGTH_RATING);
    PasswordStorage storageHashedPassword = new PasswordStorage(Attribute.HASHED_PASSWORD);

    // Test getComparisonCriteria(), size(), and isEmpty() methods for each object
    if (storageOccurrence.getComparisonCriteria() != Attribute.OCCURRENCE
        || storageOccurrence.size() != 0 || !storageOccurrence.isEmpty()) {
      return false;
    }

    if (storageStrengthRating.getComparisonCriteria() != Attribute.STRENGTH_RATING
        || storageStrengthRating.size() != 0 || !storageStrengthRating.isEmpty()) {
      return false;
    }

    if (storageHashedPassword.getComparisonCriteria() != Attribute.HASHED_PASSWORD
        || storageHashedPassword.size() != 0 || !storageHashedPassword.isEmpty()) {
      return false;
    }

    // Check if the root of each object is null
    if (storageOccurrence.root != null || storageStrengthRating.root != null
        || storageHashedPassword.root != null) {
      return false;
    }

    return true;
  }

  /**
   * Validates the Password class compareTo() method. Create at least two DIFFERENT Password objects
   * and compare them on each of the Attribute values. See the writeup for details on how the
   * various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, false otherwise
   */
  public static boolean testPasswordCompareTo() {
    // Create two different Password objects for testing
    Password p1 = new Password("password", 1000);
    Password p2 = new Password("StronkPass12#", 23);

    // Test comparison by occurrence
    if (p1.compareTo(p2, Attribute.OCCURRENCE) <= 0
        || p2.compareTo(p1, Attribute.OCCURRENCE) >= 0) {
      return false;
    }

    // Test comparison by strength rating
    if (p1.compareTo(p2, Attribute.STRENGTH_RATING) >= 0
        || p2.compareTo(p1, Attribute.STRENGTH_RATING) <= 0) {
      return false;
    }

    // Test comparison by hashed password
    if (p1.compareTo(p2, Attribute.HASHED_PASSWORD) >= 0
        || p2.compareTo(p1, Attribute.HASHED_PASSWORD) <= 0) {
      return false;
    }

    // All comparisons passed
    return true;
  }



  /**
   * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all possible
   * configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
    // Create some Password objects to use in the nodes
    Password password1 = new Password("password1", 1000);
    Password password2 = new Password("password2", 2000);
    Password password3 = new Password("password3", 3000);

    // Create some PasswordNode objects to use in the binary tree
    PasswordNode leafNode1 = new PasswordNode(password1);
    PasswordNode leafNode2 = new PasswordNode(password2);
    PasswordNode internalNode1 = new PasswordNode(password3, leafNode1, leafNode2);
    PasswordNode internalNode2 = new PasswordNode(password1, null, internalNode1);

    // Test isLeafNode() method on different nodes
    if (leafNode1.isLeafNode() != true) {
      return false;
    }
    if (internalNode1.isLeafNode() != false) {
      return false;
    }

    // Test numberOfChildren() method on different nodes
    if (leafNode1.numberOfChildren() != 0) {
      return false;
    }
    if (leafNode2.numberOfChildren() != 0) {
      return false;
    }
    if (internalNode1.numberOfChildren() != 2) {
      return false;
    }
    if (internalNode2.numberOfChildren() != 1) {
      return false;
    }

    // Test hasLeftChild() method on different nodes
    if (leafNode1.hasLeftChild() != false) {
      return false;
    }
    if (leafNode2.hasLeftChild() != false) {
      return false;
    }
    if (internalNode1.hasLeftChild() != true) {
      return false;
    }
    if (internalNode2.hasLeftChild() != false) {
      return false;
    }

    // Test hasRightChild() method on different nodes
    if (leafNode1.hasRightChild() != false) {
      return false;
    }
    if (leafNode2.hasRightChild() != false) {
      return false;
    }
    if (internalNode1.hasRightChild() != true) {
      return false;
    }
    if (internalNode2.hasRightChild() != true) {
      return false;
    }

    // All tests passed
    return true;
  }

  /**
   * Provided by the professor. Tests the toString() method of the PasswordStorage class.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
          + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();

      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Provided by the professor. Tests the isValidBST() method of the PasswordStorage class.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep


      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
            .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }


      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }


    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the lookup() method of the PasswordStorage class.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testLookup() {
    // Create a new PasswordStorage instance with Attribute.OCCURRENCE as comparison criteria
    PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

    // Add a few Password objects to the storage
    Password password1 = new Password("password1", 3);
    Password password2 = new Password("password123", 5);
    Password password3 = new Password("pass123", 7);
    Password password4 = new Password("password456", 1); // This password has lower occurrence

    // Add the passwords to the storage
    storage.addPassword(password1);
    storage.addPassword(password2);
    storage.addPassword(password3);
    storage.addPassword(password4); // Add password4 to force the lookup to look left

    // Create dummy keys for looking up the stored passwords
    Password key1 = new Password("dummy1", 3); // Occurrence matches password1
    Password key2 = new Password("dummy12345", 5); // Occurrence matches password2
    Password key3 = new Password("dummy123", 7); // Occurrence matches password3
    Password key4 = new Password("dummy1234", 1); // Occurrence matches password4
    Password keyNotFound = new Password("dummy", 9); // Occurrence does not match any password

    // Test lookup method
    boolean test1 = storage.lookup(key1) != null && storage.lookup(key1).equals(password1);
    boolean test2 = storage.lookup(key2) != null && storage.lookup(key2).equals(password2);
    boolean test3 = storage.lookup(key3) != null && storage.lookup(key3).equals(password3);
    boolean test4 = storage.lookup(key4) != null && storage.lookup(key4).equals(password4); // Test
                                                                                            // if it
                                                                                            // looks
                                                                                            // left
                                                                                            // correctly
    boolean testNotFound = storage.lookup(keyNotFound) == null;

    // Return true if all tests passed, otherwise return false
    return test1 && test2 && test3 && test4 && testNotFound;
  }

  /**
   * Tests the addPassword() method of the PasswordStorage class.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddPassword() {
    // Create a PasswordStorage object with OCCURRENCE as the comparison criteria
    PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

    // Create password instances with different occurrences
    Password password1 = new Password("Strong1", 15);
    Password password2 = new Password("S3cur3P", 20);
    Password password3 = new Password("C0mplexP", 25);
    Password password4 = new Password("R@nd0mP@", 30);
    Password password5 = new Password("Nandini123", 35);
    // Test 1: Add the first password to the password storage object
    storage.addPassword(password1);

    // Check if the first password was added correctly and the size is updated
    if (storage.root.getPassword() != password1 || storage.size() != 1 || storage.isEmpty()) {
      return false;
    }

    // Test 2: Add more passwords to the password storage object
    storage.addPassword(password2);
    storage.addPassword(password3);
    storage.addPassword(password4);
    storage.addPassword(password5);

    // Check if the passwords were added correctly using the lookup method
    if (!password1.equals(storage.lookup(new Password("password1", 15)))
        || !password2.equals(storage.lookup(new Password("password2", 20)))
        || !password3.equals(storage.lookup(new Password("password3", 25)))
        || !password4.equals(storage.lookup(new Password("password4", 30)))
        || !password5.equals(storage.lookup(new Password("password4", 35)))) {
      return false;
    }

    // Test 3: Try to add an existing password and expect an IllegalArgumentException
    try {
      storage.addPassword(password1);
      return false; // Test failed if no exception was thrown
    } catch (IllegalArgumentException e) {
      // Expected exception was thrown
    }

    // Check if all passwords were added correctly and the size is updated
    if (storage.root.getPassword() != password1 || storage.size() != 5 || storage.isEmpty()) {
      return false;
    }

    // All tests passed
    return true;
  }

  /**
   * Tests the removePassword method of PasswordStorage by performing various removal operations on
   * the tree. The method checks the tree structure and size after each removal to ensure
   * correctness.
   *
   * @return true if all removal tests pass, false otherwise
   */

  public static boolean testRemovePassword() {
    try {
      PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

      // Initialize passwords with different occurrencesF
      Password password1 = new Password("Strong1", 1);
      Password password2 = new Password("S3cur3P", 2);
      Password password3 = new Password("C0mplexP", 3);
      Password password4 = new Password("R@nd0mP@", 4);
      Password password5 = new Password("Nandini123", 5);
      Password password6 = new Password("TestPass6", 6);
      Password password7 = new Password("TestPass7", 7);

      // Add passwords to the storage
      storage.addPassword(password4);
      storage.addPassword(password2);
      storage.addPassword(password6);
      storage.addPassword(password1);
      storage.addPassword(password3);
      storage.addPassword(password5);
      storage.addPassword(password7);

      // Test 1: Remove a leaf node (password7) and check the tree structure and size
      storage.removePassword(new Password("uwmadison", 7));
      if (storage.size() != 6) {
        return false;
      }
      if (storage.root.getRight().hasRightChild()) {
        return false;
      }

      // Test 2: Remove a node with 1 child (password6) and check the tree structure and size
      storage.removePassword(new Password("uwmadison", 6));
      if (storage.size() != 5) {
        return false;
      }
      if (!storage.root.getRight().isLeafNode()
          || !storage.root.getRight().getPassword().equals(password5)) {
        return false;
      }

      // Test 3: Remove the root node (password4) and check the tree structure and size
      storage.removePassword(new Password("uwmadison", 4));
      if (storage.size() != 4) {
        return false;
      }
      if (!storage.root.getPassword().equals(password3)) {
        return false;
      }
      if (!storage.root.getLeft().getPassword().equals(password2)
          || !storage.root.getLeft().getLeft().getPassword().equals(password1)
          || !storage.root.getRight().getPassword().equals(password5)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    // All tests passed
    return true;
  }

  public static void main(String[] args) {
    runAllTests();
  }

  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out
        .println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
