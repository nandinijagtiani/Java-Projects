import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Bangers II program
 * 
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 */

public class DancingBadgers {
  // declaring class variables
  private static PImage backgroundImage; // backgound image
  private static ArrayList<Badger> badgers; // new ArrayList storing badger objects
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax;
Instructor
| 02/28 at 12:20 am
Grading comment:
No comment provided for the data field.

  private static ArrayList<Thing> things;
  private static ArrayList<StarshipRobot> robots;


  /**
   * Driver method to run this graphic application
   *
   * @param args
   */
  public static void main(String[] args) {
    // Calls the "runApplication" method in the "Utility" class to start the application
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    PApplet processing = null;
    // Sets the processing field in the StarshipRobot and Thing classes to the processing object
    StarshipRobot.setProcessing(processing);
    Thing.setProcessing(processing);

    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    robots = new ArrayList<StarshipRobot>();
    randGen = new Random();
    badgersCountMax = 5;
    things = new ArrayList<Thing>();
    // Creates and add the Thing and StarshipRobot objects to their respective ArrayLists
    Thing obj1 = new Thing(50, 50, "images" + File.separator + "target.png");
    Thing obj2 = new Thing(750, 550, "images" + File.separator + "target.png");
    Thing obj3 = new Thing(750, 50, "images" + File.separator + "shoppingCounter.png");
    Thing obj4 = new Thing(50, 550, "images" + File.separator + "shoppingCounter.png");
    things.add(obj1);
    things.add(obj2);
    things.add(obj3);
    things.add(obj4);
    StarshipRobot obj10 = new StarshipRobot(obj3, obj1, 3);
    StarshipRobot obj11 = new StarshipRobot(obj4, obj2, 3);
    robots.add(obj10);
    robots.add(obj11);

  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    // Sets the background color and display the background image
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    // Calls the "draw" method for each Thing, StarshipRobot, and Badger object in their respective
    // ArrayLists
    for (int i = 0; i < things.size(); i++) {
      things.get(i).draw();
    }
    for (int i = 0; i < robots.size(); i++) {
      robots.get(i).draw();
    }
    for (int i = 0; i < badgers.size(); i++) {
      badgers.get(i).draw();

    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // If a Badger object is clicked, call its "startDragging" method to start the dragging process
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i).isMouseOver()) {
        badgers.get(i).startDragging();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // If a Badger object is clicked, call its "mouseReleased" method to stop the dragging process
    for (int i = 0; i < badgers.size(); i++) {
      badgers.get(i).stopDragging();
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
        // present in the field is not reached
        for (int i = 0; i <= badgers.size(); i++) {
          if (badgers.size() < badgersCountMax) {
            badgers.add(
                new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
            break;
          }
        }
        break;
      case 'R': // deletes the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i).isMouseOver()) {
            badgers.remove(i);
            break;
          }
        }
        break;
    }
  }
}
