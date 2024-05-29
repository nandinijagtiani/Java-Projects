////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Bottle Factory
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

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {

    // test constructor - verify fields and exception behavior (when capacity is invalid)
    Bottle.resetBottleCounter();
    Bottle bottle1 = new Bottle("Green");
    Bottle bottle2 = new Bottle("Yellow");
    try {
      Bottle bottle3 = new Bottle("");
      System.out.println("BottleTester: bottle3 should not be created - empty color");
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      Bottle bottle4 = new Bottle(null);
      System.out.println("BottleTester: bottle4 should not be created - null color");
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    if (!bottle1.getSerialNumber().equals("SN1Green")) {
      System.out.println("BottleTester: bottle1.getSerialNumber() should be SN1Green");
      return false;
    }

    // test fillBottle method
    bottle1.fillBottle();
    if (!bottle1.isFilled()) {
      System.out.println("BottleTester: bottle1.isFilled() should be true");
      return false;
    }

    // test sealBottle method
    try {
      bottle1.sealBottle();
      if (!bottle1.isCapped()) {
        System.out.println("BottleTester: bottle1.isCapped() should be true");
        return false;
      }
    } catch (IllegalStateException e) {
      System.out.println("BottleTester: bottle1.sealBottle() should not throw an exception");
      return false;
    }

    // test toString method
    if (!bottle1.toString().equals("SN1Green:Filled:Capped")) {
      System.out.println("BottleTester: bottle1.toString() should be SN1Green:Filled:Capped");
      return false;
    }

    // test equals method
    Bottle.resetBottleCounter();
    Bottle bottle5 = new Bottle("Green");
    Bottle.resetBottleCounter();
    Bottle bottle6 = new Bottle("Yellow");
    if (!bottle1.equals(bottle5)) {
      System.out.println("BottleTester: bottle1 should be equal to bottle5");
      return false;
    }
    if (bottle1.equals(bottle2)) {
      System.out.println("BottleTester: bottle1 should not be equal to bottle2");
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean linkedBottleQueueTester() {

    // test constructor - verify fields and exception behavior (when capacity is invalid)
    try {
      LinkedBottleQueue invalidQ = new LinkedBottleQueue(-1);
      return false;
    } catch (IllegalArgumentException e) {

    }
    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all methods on an empty
     * queue 2) all methods on a full queue 3) all methods on a partially filled queue 4) Verify
     * queue contents (using peek and size) after a sequence of enqueue-dequeue and dequeue-enqueue
     * 5) Enqueue until queue is full and dequeue until queue is empty
     */

    try {
      LinkedBottleQueue ValidQueue = new LinkedBottleQueue(3);

      if (!ValidQueue.isEmpty()) {
        return false;
      }
      if (ValidQueue.isFull()) {
        return false;
      }

      Bottle b1 = new Bottle("Purple");
      Bottle b2 = new Bottle("Orange");
      Bottle b3 = new Bottle("Pink");

      ValidQueue.enqueue(b1);
      ValidQueue.enqueue(b2);

      if (ValidQueue.size() != 2) {
        return false;
      }
      if (ValidQueue.peek() != b1) {
        return false;
      }

      ValidQueue.enqueue(b3);

      if (!ValidQueue.isFull()) {
        return false;
      }
      if (ValidQueue.size() != 3) {
        return false;
      }

      ValidQueue.dequeue();

      if (ValidQueue.size() != 2) {
        return false;
      }
      if (ValidQueue.peek() != b2) {
        return false;
      }

      ValidQueue.enqueue(b1);

      if (ValidQueue.size() != 3) {
        return false;
      }
      if (ValidQueue.peek() != b2) {
        return false;
      }

      ValidQueue.dequeue();
      ValidQueue.enqueue(b2);
      ValidQueue.dequeue();

      if (ValidQueue.size() != 2) {
        return false;
      }
      if (ValidQueue.peek() != b1) {
        return false;
      }

      ValidQueue.enqueue(b2);
      ValidQueue.dequeue();
      ValidQueue.dequeue();
      ValidQueue.dequeue();

      if (!ValidQueue.isEmpty()) {
        return false;
      }

      // test copy method
      QueueADT<Bottle> copy = ValidQueue.copy();
      if (copy.size() != ValidQueue.size()) {
        return false;
      }

      // test contents of copied queue
      ValidQueue.enqueue(b1);
      ValidQueue.enqueue(b2);
      ValidQueue.enqueue(b3);
      copy.enqueue(b1);
      copy.enqueue(b2);
      copy.enqueue(b3);

      while (!ValidQueue.isEmpty() && !copy.isEmpty()) {
        if (!ValidQueue.dequeue().equals(copy.dequeue())) {
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {

    // test constructor - verify fields and exception behavior

    CircularBottleQueue queue = new CircularBottleQueue(3);
    if (queue.size() != 0) {
      return false;
    }
    if (queue.isFull()) {
      return false;
    }
    if (queue.isEmpty() == false) {
      return false;
    }
    boolean exceptionCaught = false;
    try {
      CircularBottleQueue invalidQueue = new CircularBottleQueue(0);
    } catch (IllegalArgumentException ex) {
      exceptionCaught = true;
    }
    if (exceptionCaught == false) {
      return false;
    }


    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all 3 methods on an empty
     * queue 2) all 3 methods on a full queue 3) all 3 methods on a partially filled queue 4) Verify
     * queue contents and size after a sequence of enqueue-dequeue and dequeue-enqueue 5) Enqueue
     * until queue is full and dequeue until queue is empty
     */

    // 1) all 3 methods on an empty queue
    queue = new CircularBottleQueue(3);
    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException ex) {
      // expected exception
    }
    try {
      queue.peek();
      return false;
    } catch (NoSuchElementException ex) {
      // expected exception
    }
    Bottle b1 = new Bottle("Pink");
    queue.enqueue(b1);
    if (queue.size() != 1 || queue.isEmpty()) {
      return false;
    }
    if (queue.peek() != b1) {
      return false;
    }
    if (queue.dequeue() != b1) {
      return false;
    }
    if (queue.size() != 0 || !queue.isEmpty()) {
      return false;
    }

    // 2) all 3 methods on a full queue
    queue = new CircularBottleQueue(3);
    Bottle b2 = new Bottle("Yellow");
    Bottle b3 = new Bottle("Purple");
    Bottle b4 = new Bottle("Green");
    queue.enqueue(b1);
    queue.enqueue(b2);
    queue.enqueue(b3);
    try {
      queue.enqueue(b4);
      return false;
    } catch (IllegalStateException ex) {
      // expected exception
    }
    if (queue.peek() != b1) {
      return false;
    }
    if (queue.dequeue() != b1) {
      return false;
    }
    if (queue.peek() != b2) {
      return false;
    }
    if (queue.dequeue() != b2) {
      return false;
    }
    if (queue.peek() != b3) {
      return false;
    }
    if (queue.dequeue() != b3) {
      return false;
    }
    if (queue.size() != 0 || !queue.isEmpty()) {
      return false;
    }

    // 3) all 3 methods on a partially filled queue
    queue = new CircularBottleQueue(3);
    queue.enqueue(b1);
    queue.enqueue(b2);
    if (queue.size() != 2 || queue.isEmpty()) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {

    /*
     * test 01: Create a LinkedBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */

    LinkedBottleQueue testQ = new LinkedBottleQueue(3);
    Bottle b1 = new Bottle("Pink");
    Bottle b2 = new Bottle("Yellow");
    Bottle b3 = new Bottle("Purple");

    testQ.enqueue(b1);
    testQ.enqueue(b2);
    testQ.enqueue(b3);

    BottleQueueIterator iterator = new BottleQueueIterator(testQ);

    int count = 0;
    while (iterator.hasNext()) {
      Bottle currentBottle = iterator.next();
      if (currentBottle != testQ.dequeue()) {
        return false;
      }
      count++;
    }

    if (count != 3) {
      return false;
    }

    /*
     * test 02: Create a CircularBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the testQueue. Verify if all the bottles are returned correctly
     */
    CircularBottleQueue testQ2 = new CircularBottleQueue(3);
    Bottle b4 = new Bottle("Green");
    Bottle b5 = new Bottle("Blue");
    Bottle b6 = new Bottle("Red");

    testQ2.enqueue(b4);
    testQ2.enqueue(b5);
    testQ2.enqueue(b6);

    BottleQueueIterator iterator2 = new BottleQueueIterator(testQ2);

    int count2 = 0;
    while (iterator2.hasNext()) {
      Bottle currentBottle = iterator2.next();
      if (currentBottle != testQ2.dequeue()) {
        return false;
      }
      count2++;
    }

    if (count2 != 3) {
      return false;
    }

    return true;
  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out
        .println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}

