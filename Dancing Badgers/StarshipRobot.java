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
// __X_ Write-up states that pair programming is allowed for this assignment.
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
import java.io.File;

/**
 * This class models a Starship Robot in the P03 Dancing Badgers programming assignment
 * 
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 *
 */
public class StarshipRobot {
  // declaring class variables
  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private Thing source;
  private Thing destination;
  private int speed;

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The (x,y) position of
   * this StarshipRobot is set to the (x,y) position of source.
   *
   * @param source      - Creates a new StarshipRobot and sets its source, destination, and speed.
   *                    The (x,y) position of this StarshipRobot is set to the (x,y) position of
   *                    source.
   * @param destination - Thing object representing the destination point of this StarshipRobot
   * @param speed       - movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    this.image = processing.loadImage("images" + File.separator + "starshipRobot.png");
    this.source = source;
    this.destination = destination;
    this.speed = speed;
    this.x = source.getX();
    this.y = source.getY();


  }

  /**
   * Draws this StarshipRobot to the display window while it is in action delivering food
   */
  public void draw() {
    processing.image(this.image, x, y);
    go();
  }

  /**
   * Returns the x-position of this starship robot in the display window
   *
   * @return - the X coordinate of the starship robot position
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this starship robot in the display window
   *
   * @return - the y-position of the starship robot
   */
  public float getY() {
    return y;
  }

  /**
   * Sets the x-position of this starship robot in the display window
   *
   * @param x - the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this starship robot in the display window
   *
   * @param y - the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Returns a reference to the image of this starship robot
   *
   * @return - Returns a reference to the image of this starship robot
   */
  public PImage image() {
    return image;
  }

  /**
   *
   * @param processing - Sets the PApplet object display window where this StarshipRobot will be
   *                   drawn. The processing PApplet data field is set to Badger.processing since
   *                   Badger and StarshipRobot objects are going to be displayed to the same
   *                   screen.
   */
  public static void setProcessing(PApplet processing) {
    StarshipRobot.processing = Badger.getProcessing();
  }

  /**
   * Implements the behavior of this StarshipRobot going back-and-forth between its source and
   * destination.
   */
  private void moveTowardsDestination() {
    // Calculates the distance between the current position and the destination in the x and y axes
    float dx, dy;
    dx = ((destination.getX()) - (this.getX()));
    dy = (destination.getY()) - (this.getY());
    int d = (int) Math.sqrt(dx * dx + dy * dy);
    // Calculates the new position by moving towards the destination at the specified speed
    float newX = (float) (this.getX() + (this.speed * (dx) / d));
    float newY = (float) (this.getY() + (this.speed * (dy) / d));
    // Sets the new position
    this.setX(newX);
    this.setY(newY);

  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   *
   * @param thing - a given Thing object
   * @return - true if this StarshipRobot is over the Thing object passed as input, otherwise,
   *         returns false.
   */
  public boolean isOver(Thing thing) {
    // Calculating the coordinates of the edges of the robot and the image
    float x1 = this.getX() - this.image.width / 2;
    float x2 = this.getX() + this.image.width / 2;

    float x3 = thing.getX() - thing.image().width / 2;
    float x4 = thing.getX() + thing.image().width / 2;

    float y1 = this.getY() - this.image.height / 2;
    float y2 = this.getY() + this.image.height / 2;

    float y3 = thing.getY() - thing.image().height / 2;
    float y4 = thing.getY() + thing.image().height / 2;

    // checks whether the robot and the image overlap
    return (x1 < x4 && x3 < x2 && y1 < y4 && y3 < y2);

  }

  /**
   * Implements the behavior of this StarshipRobot going back-and-forth between its source and
   * destination.
   */
  public void go() {
    if (isOver(destination) == false) {
      this.moveTowardsDestination(); // moves the robot towards its destination
    } else {
      Thing temp = source; // swaps the source and destination locations
      source = destination;
      destination = temp;
    }

  }

}
