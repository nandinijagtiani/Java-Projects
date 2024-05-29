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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not
 * empty, Bottle objects are iterated over from the front to the back of the queue. No more Bottle
 * objects are returned by this iterator when all the Bottle objects are traversed (returned). This
 * Iterator iterates over any queue which implements the QueueADT<Bottle> interface. It uses the
 * QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 * 
 * @author nandinihjagtiani
 *
 */
public class BottleQueueIterator extends Object implements Iterator<Bottle> {

  /**
   * Initializing data fields
   */
  private QueueADT<Bottle> bottleQueue;

  /**
   * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
   * deep copy of the queue passed as input.
   * 
   * @param queue - The queue to iterate over, should implement the QueueADT interface.
   * @throws IllegalArgumentException - - when queue is null
   */
  public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
    if (queue == null) {
      throw new IllegalArgumentException("Queue cannot be null");
    }
    this.bottleQueue = queue.copy();
  }

  /**
   * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is not
   * iterated over
   * 
   * @return - boolean value
   */
  @Override
  public boolean hasNext() {
    return !bottleQueue.isEmpty();
  }

  /**
   * Returns the next bottle in the iteration
   * 
   * @throws - NoSuchElementException - if there are no more elements in the iteration
   * @return - Bottle The next bottle in the iteration
   */
  @Override
  public Bottle next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("There are no more elements in the iteration");
    }
    if (bottleQueue.isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return bottleQueue.dequeue();
  }

}
