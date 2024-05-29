//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P02-Dancing Badgers 2
// Course: CS 300 Spring 2023
//
// Author: Ayushi Saigal
// Email: asaigal2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nandini Jagtiani
// Partner Email: jagtiani@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import processing.core.PApplet;
import processing.core.PImage;
/**
 * This class models Thing in the P03 Dancing Badgers programming assignment
 * 
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 */
public class Thing {
  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;

  /**
   * @param x             - x-coordinate of this thing in the display window
   * @param y             - y-coordinate of this thing in the display window
   * @param imageFilename - filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    this.x = x;
    this.y = y;
    this.image = processing.loadImage(imageFilename);


  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    processing.image(this.image, x, y);
  }

  /**
   * Returns the x-position of this thing in the display window
   *
   * @return - the X coordinate of the thing position
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this thing in the display window
   *
   * @return - the y-position of the thing
   */
  public float getY() {
    return y;
  }

  /**
   * Sets the x-position of this thing in the display window
   *
   * @param x - the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this thing in the display window
   *
   * @param y - the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Returns a reference to the image of this thing
   *
   * @return - the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return image;
  }

  /**
   * @param processing - Sets the PApplet object display window where this Thing will be drawn. The
   *                   processing PApplet static data field should be set to Badger.getProcessing()
   *                   since Badgers and Thing objects are going to be displayed to the same screen.
   */

  public static void setProcessing(PApplet processing) {
    Thing.processing = Badger.getProcessing();
  }
}


