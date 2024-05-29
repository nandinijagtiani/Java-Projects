import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class DancingBadgers {

  // PImage object that represents the background image
  private static PImage backgroundImage;

  // perfect sized array storing the badgers in this application
  private static Badger[] badgers;

  // generates random numbers
  private static Random randGen;

  // setup method that is called once to set up the sketch
  public static void setup() {

    // initializing the random variable
    randGen = new Random();

    // load the image of the background
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    // creates an array of badgers with a length of 5
    badgers = new Badger[5];

  }

  // draw method that is called repeatedly to render the sketch
  public static void draw() {

    // sets the background color
    Utility.background(Utility.color(255, 218, 185));

    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // draw each badger in the array, if it's not null
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null)
        badgers[i].draw();
    }
  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter
   *
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   *         image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {

    // gets the image of the badger
    PImage badgerPicture = Badger.image();

    // gets the height and width of the image
    float height = badgerPicture.height;
    float width = badgerPicture.width;

    // calculates the upper and lower bounds of the image
    float upperSide = Badger.getY() + (height / 2);
    float lowerSide = Badger.getY() - (height / 2);

    // calculates the left and right bounds of the image
    float leftSide = Badger.getX() - (width / 2);
    float rightSide = Badger.getX() + (width / 2);

    // returns true if the mouse is within the bounds of the image, false otherwise
    return (Utility.mouseX() >= leftSide && Utility.mouseX() <= rightSide)
        && (Utility.mouseY() >= lowerSide && Utility.mouseY() <= upperSide);

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {

    // loops through all elements in the badgers array
    for (int i = 0; i < badgers.length; i++) {
      // checks if the current element is not null
      if (badgers[i] != null) {
        // checks if the mouse is over the current element
        if (isMouseOver(badgers[i])) {
          // starts dragging the current element
          badgers[i].startDragging();
        }
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {

    // loops through all elements in the badgers array
    for (int i = 0; i < badgers.length; i++) {
      // checks if the current element is not null
      if (badgers[i] != null) {
        // stops dragging the current element
        badgers[i].stopDragging();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    // defining user inputs
    char userInput1 = 'b';
    char userInput2 = 'B';
    char userInput3 = 'r';
    char userInput4 = 'R';

    // checks if the user presses b or B
    if (Utility.key() == userInput1 || Utility.key() == userInput2) {
      // loops through all elements in the badgers array
      for (int i = 0; i < badgers.length; i++) {
        // checks if the current element is null
        if (badgers[i] == null) {
          // generates a random width and height
          float randomWidth = (float) randGen.nextInt(Utility.width());
          float randomHeight = (float) randGen.nextInt(Utility.height());
          // create a new Badger at the random position and draws it
          badgers[i] = new Badger(randomWidth, randomHeight);
          badgers[i].draw();
          // breaks the loop since a Badger has been added
          break;
        }
      }
    }

    // checks if the user presses r or R
    if (Utility.key() == userInput3 || Utility.key() == userInput4) {
      // loops through all elements in the badgers array 
      for (int i = 0; i < badgers.length; i++) {
        // checks if the current element is not null and the mouse is over it 
        if (badgers[i] != null && isMouseOver(badgers[i])) {
          // removes the badger by setting it to null 
          badgers[i] = null;
        }
      }
    }
  }



  public static void main(String[] args) {
    // TODO Auto-generated method stub

    // starts the application
    Utility.runApplication();
  }

}
