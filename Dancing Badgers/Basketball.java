//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Title: P05 Dancing Badgers |||
// Course: CS 300 Spring 2023
//
// Author: Nandini jagtiani
// Email: jagtiamo@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Ayushi Saigal
// Partner Email: asaigal2@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: No helped was given or received (identify each by name and describe how they helped)
// Online Sources: No helped was given or received (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class is a class for P05-Dancing Badgers 3.0
 *
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 */

// Import the PApplet class from the processing.core package
import processing.core.PApplet;

// Define a class named Basketball that extends the Thing class and implements the Clickable
// interface
public class Basketball extends Thing implements Clickable {

  // Declare an integer variable named rotations
  private int rotations;

  // Declare a float variable named rotation
  public float rotation;

  /**
   * Define a constructor that takes three arguments: a float x-coordinate, a float y-coordinate,
   * and a String imageFilename
   */

  public Basketball(float x, float y, String imageFilename) {
    // Call the constructor of the parent class (Thing) with the x and y coordinates and the image
    // filename
    super(x, y, imageFilename);

  }

  /**
   * Creates a new Basketball object located at (x,y) position whose image filename is
   * "basketball.png", and sets its rotation angle to PApplet.PI/2. Initially, when created, the
   * basketball has made zero rotations.
   */


  public Basketball(float x, float y) {
    super(x, y, "basketball.png"); // Call the constructor of the parent class (Thing)
    rotations = 0;
    rotation = PApplet.PI / 2; // Set the value of the rotation variable to half of pi
  }

  /**
   *
   * Draws this rotating Basketball object to the display window.
   */
  public void draw() {
    processing.pushMatrix();
    processing.translate(x, y);
    processing.rotate(this.rotations * rotation);
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Defines the behavior of this basketball when the mouse is pressed.
   */

  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      rotate();
    }
    // TODO Auto-generated method stub

  }

  /**
   * Called when the mouse is released.
   */

  // Override the mouseReleased method from the Clickable interface
  @Override
  public void mouseReleased() {

    // TODO Auto-generated method stub

  }

  /**
   * This method rotates this basketball object by incrementing the number of its rotations by one.
   */

  // Define a method named rotate that doesn't take any arguments
  public void rotate() {
    rotations++; // Increment the rotations variable by 1
  }

}
