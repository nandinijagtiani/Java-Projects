//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Twitter Feed
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

/**
 * This (very basic) data type models a Twitter user.
 * @author nandinihjagtiani
 *
 */
public class User extends Object {

  // initializing the fields

  /**
   * The username this User tweets under
   */
  private String username;

  /**
   * The verified status of this User (whether they have a blue checkmark or not)
   */
  private boolean isVerified;

  // constructor

  /**
   * Constructs a new User object with a given username. All Users are unverified by default.
   * 
   * @param username - the username of this User.
   * @throws IllegalArgumentException
   */
  public User(String username) {
    if (username == null || username.isBlank() || username.contains("*")) {
      throw new IllegalArgumentException("Invalid username: " + username);
    }
    this.username = username;
    this.isVerified = false;
  }

  /**
   * Accesses the username of this User
   * 
   * @return - the username this User tweets under
   */
  public String getUsername() {
    return username;
  }

  /**
   * Determines whether the User is a verified user or not
   * 
   * @return - true if this User is verified
   */
  public boolean isVerified() {
    return isVerified;
  }

  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    isVerified = true;
  }

  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    isVerified = false;
  }

  /**
   * Creates a String representation of this User for display. For example, if this User's username
   * is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's
   * username is "dril" and they are not verified, this method will return "@dril" (with no
   * asterisk).
   * 
   * @return - a String representation of this User as described above
   */
  @Override
  public String toString() {
    if (isVerified) {
      return "@" + username + "*";
    } else {
      return "@" + username;
    }
  }
}
