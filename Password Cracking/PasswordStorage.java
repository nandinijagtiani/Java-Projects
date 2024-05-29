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
import java.util.NoSuchElementException;

public class PasswordStorage {

  protected PasswordNode root; // the root of this BST that contains passwords
  private int size; // how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; // what password information to use to determine
                                               // order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * 
   * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.COMPARISON_CRITERIA = comparisonCriteria;
    this.root = null;
    this.size = 0;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * 
   * @return the Attribute that is being used to make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * 
   * @return the size of this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines whether or not this tree is empty.
   * 
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Provides in-order String representation of this BST, with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * 
   * @return this BST as a string
   */
  @Override
  public String toString() {
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to create the string representation of this
   * tree.
   * 
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
    if (currentNode == null) {
      return "";
    }
    String leftString = toStringHelper(currentNode.getLeft());
    String currentString = currentNode.getPassword().toString() + "\n";
    String rightString = toStringHelper(currentNode.getRight());
    return leftString + currentString + rightString;
  }

  /**
   * Determines whether or not this tree is actually a valid BST.
   * 
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword(),
        COMPARISON_CRITERIA);
  }

  /**
   * Recurisvely determines if the the tree rooted at the current node is a valid BST. That is,
   * every value to the left of currentNode is "less than" the value in currentNode and every value
   * to the right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound,  the smallest possible password
   * @param upperBound,  the largest possible password
   * @return true if the tree rooted at currentNode is a BST, false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound,
      Password upperBound, Attribute a) {
    if (currentNode == null) {
      // BASE CASE 1: the tree rooted at currentNode is empty, which does not violate any BST rules
      return true;
    }

    Password currentPassword = currentNode.getPassword();
    if (currentPassword.compareTo(lowerBound, a) < 0
        || currentPassword.compareTo(upperBound, a) > 0) {
      // BASE CASE 2: the current Password is outside of the upper OR lower bound for this subtree,
      // which is against the rules for a valid BST
      return false;
    }

    // If we do not have a base case situation, we must use recursion to verify currentNode's child
    // subtrees
    Password leftUpperBound = currentPassword;
    Password rightLowerBound = currentPassword;

    // RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater than lowerBound
    // and less than
    // currentNode's Password; return false if this property is NOT satisfied
    if (!isValidBSTHelper(currentNode.getLeft(), lowerBound, leftUpperBound, a)) {
      return false;
    }

    // RECURSIVE CASE 2: Check that the right subtree contains only Passwords greater than
    // currentNode's Password
    // and less than upperBound; return false if this property is NOT satisfied
    if (!isValidBSTHelper(currentNode.getRight(), rightLowerBound, upperBound, a)) {
      return false;
    }

    // COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case 1 and 2 are
    // valid
    return true;
  }

  /**
   * Returns the password that matches the criteria of the provided key. Ex. if the COMPARISON
   * CRITERIA is OCCURRENCE and the key has an occurrence of 10 it will return the password stored
   * in the tree with occurrence of 10
   * 
   * @param key, the password that contains the information for the password we are searching for
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  }

  /**
   * Recursive helper method to find the matching password in this BST
   * 
   * @param key,         password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
    // Base case: if the current node is null, the key is not in the tree
    if (currentNode == null) {
      return null;
    }

    // Compare the key to the current node's password using the compareTo method
    int cmp = key.compareTo(currentNode.getPassword(), getComparisonCriteria());

    if (cmp == 0) {
      // If the key matches the current node's password, return the password
      return currentNode.getPassword();
    } else if (cmp < 0) {
      // If the key is less than the current node's password, search the left subtree
      return lookupHelper(key, currentNode.getLeft());
    } else {
      // If the key is greater than the current node's password, search the right subtree
      return lookupHelper(key, currentNode.getRight());
    }
  }

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() {
    if (root == null) {
      throw new NoSuchElementException("BST is empty");
    }

    PasswordNode currentNode = root;
    while (currentNode.getRight() != null) {
      currentNode = currentNode.getRight();
    }

    return currentNode.getPassword();
  }

  /**
   * Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() {
    if (root == null) {
      throw new NoSuchElementException("BST is empty");
    }

    PasswordNode currentNode = root;
    while (currentNode.getLeft() != null) {
      currentNode = currentNode.getLeft();
    }

    return currentNode.getPassword();
  }

  /**
   * Adds the Password to this BST.
   * 
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  public void addPassword(Password toAdd) {
    if (root == null) {
      root = new PasswordNode(toAdd);
      size++;
    } else {
      if (addPasswordHelper(toAdd, root)) {
        size++;
      } else {
        throw new IllegalArgumentException("The password is already in the tree");
      }
    }
  }


  /**
   * Recursive helper that traverses the tree and adds the password where it belongs
   * 
   * @param toAdd,       the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
    Password currentPassword = currentNode.getPassword();
    int comparison = currentPassword.compareTo(toAdd, this.COMPARISON_CRITERIA);
    if (comparison > 0) {
      // The new password is smaller than the current password, so it should be added to the left
      // subtree
      if (currentNode.getLeft() == null) {
        currentNode.setLeft(new PasswordNode(toAdd));
        return true;
      } else {
        // Recursively add the password to the left subtree
        return addPasswordHelper(toAdd, currentNode.getLeft());
      }
    } else if (comparison < 0) {
      // The new password is larger than the current password, so it should be added to the right
      // subtree
      if (currentNode.getRight() == null) {
        currentNode.setRight(new PasswordNode(toAdd));
        return true;
      } else {
        // Recursively add the password to the right subtree
        return addPasswordHelper(toAdd, currentNode.getRight());
      }
    } else {
      // The new password is equal to the current password, so it should not be added
      return false;
    }
  }

  /**
   * Removes the matching password from the tree
   * 
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
    if (isEmpty()) {
      throw new NoSuchElementException("BST is empty.");
    }
    root = removePasswordHelper(toRemove, root);
    size--;
  }

  /**
   * Recursive helper method to that removes the password from this BST.
   * 
   * @param toRemove,    the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of this subtree now that toRemove has been
   *         removed. This may still be currentNode, or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
    if (currentNode == null) {
      throw new NoSuchElementException("Password not found.");
    }

    int comparison = toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);
    if (comparison < 0) {
      // The password to remove is smaller than the current password, so it must be in the left
      // subtree
      currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
    } else if (comparison > 0) {
      // The password to remove is larger than the current password, so it must be in the right
      // subtree
      currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
    } else {
      // We found the password to remove
      if (!currentNode.hasLeftChild()) {
        // If the current node has no left child, we can replace it with its right child
        return currentNode.getRight();
      } else if (!currentNode.hasRightChild()) {
        // If the current node has no right child, we can replace it with its left child
        return currentNode.getLeft();
      } else {
        // The current node has both left and right children, so we need to find its predecessor
        Password predecessor = getPredecessor(currentNode);
        // Replace the current node with the predecessor and remove the predecessor from the left
        // subtree
        currentNode = new PasswordNode(predecessor, currentNode.getLeft(), currentNode.getRight());
        currentNode.setLeft(removePasswordHelper(predecessor, currentNode.getLeft()));
      }
    }
    return currentNode;
  }

  /**
   * Gets the predecessor of a given node in this BST. The predecessor is the node with the largest
   * value that is smaller than the given node's value.
   *
   * @param currentNode The node for which we want to find the predecessor.
   * @return The predecessor's password.
   */
  private Password getPredecessor(PasswordNode currentNode) {
    // Start at the left child of the currentNode, as the predecessor must be in the left subtree
    PasswordNode predecessor = currentNode.getLeft();

    // Traverse down the right subtree of the currentNode's left child to find the node with the
    // maximum value
    // (predecessor) in the left subtree
    while (predecessor.hasRightChild()) {
      predecessor = predecessor.getRight();
    }

    // Return the password of the predecessor node
    return predecessor.getPassword();
  }

}
