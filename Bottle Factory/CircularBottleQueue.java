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
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 * 
 * @author nandinihjagtiani
 *
 */
public class CircularBottleQueue extends Object implements QueueADT<Bottle>, Iterable<Bottle> {

  /**
   * Initializing data fields
   */
  private Bottle[] bottles;
  private int front;
  private int back;
  private int size;

  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows: the bottles
   * oversize array to an empty array of Bottle objects whose length is the input capacity, its size
   * to zero, and both its front and back to -1.
   * 
   * @param capacity - defining the number of bottles the queue can hold
   * @throws IllegalArgumentException - when capacity is not positive
   */
  public CircularBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    // Initializing the data fields
    bottles = new Bottle[capacity];
    size = 0;
    front = -1;
    back = -1;
  }

  /**
   * Returns an iterator to traverse the queue.
   * 
   * @return - An Iterator instance to traverse a deep copy of this CircularBottleQueue.
   */
  @Override
  public Iterator<Bottle> iterator() {
    // Returning an Iterator instance to traverse a deep copy of this CircularBottleQueue
    return new BottleQueueIterator(this.copy());
  }

  /**
   * Checks and returns true if the queue is empty
   * 
   * @return - boolean value
   */
  @Override
  public boolean isEmpty() {
    // Returns true if the size of the queue is zero
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   * 
   * @return - boolean value
   */
  @Override
  public boolean isFull() {
    // Returns true if the size of the queue is equal to the capacity of the queue
    return size == bottles.length;
  }

  /**
   * Returns the number of bottles in the queue
   * 
   * @return - size of the queue
   */
  @Override
  public int size() {
    // Returns the size of the queue
    return size;
  }

  /**
   * Add a bottle to the end of the queue
   * 
   * @param - bottle - Bottle to add to the queue
   * @throws - IllegalStateException - when queue is full
   * @throws - NullPointerException - when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (bottle == null) {
      throw new NullPointerException("Bottle cannot be null.");
    }
    if (size == bottles.length) {
      throw new IllegalStateException("Queue is full.");
    }
    // Adding the bottle to the end of the queue and updating the data fields
    back = (back + 1) % bottles.length;
    bottles[back] = bottle;
    size++;

    if (front == -1) {
      front = back;
    }
  }

  /**
   * Removes and returns the first bottle in the queue.
   *
   * @throws - NoSuchElementException - when queue is empty
   * @return - Top/First bottle in the queue
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }

    // Removing the first bottle in the queue and updating the data fields
    Bottle removeB = bottles[front];
    bottles[front] = null;
    size--;

    if (isEmpty()) {
      front = -1;
      back = -1;
    } else {
      front = (front + 1) % bottles.length;
    }
    return removeB;
  }

  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @throws - NoSuchElementException - when queue is empty
   * @return - Top/First bottle in the queue
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    // Check if the queue is empty
    if (isEmpty()) {
      // Throw a NoSuchElementException with a message
      throw new NoSuchElementException("Queue is empty");
    }
    // Return the first element in the queue
    return bottles[front];
  }

  /**
   * Returns a deep copy of this Queue
   * 
   * @return - a deep copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    // Create a new queue with the same capacity as the current queue
    CircularBottleQueue copyQueue = new CircularBottleQueue(bottles.length);

    // Copy each element of the current queue to the new queue
    for (int i = 0; i < size; i++) {
      // Enqueue the element at the front of the current queue to the new queue
      copyQueue.enqueue(bottles[front]);
      // Move the front pointer to the next element in the current queue
      front = (front + 1) % bottles.length;
    }

    // Return the new queue
    return copyQueue;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ...
   * SN:Filled/Empty:Capped/Open The string should not contain a newline character at the end.
   * 
   * @return - String in expected format, empty string when queue is empty
   */
  public String toString() {
    // Check if the queue is empty
    if (isEmpty()) {
      // Return an empty string
      return "";
    }
    // Initialize a string to hold the result
    String result = "";
    // Iterate over each element in the queue from front to back
    for (int i = front; i != back; i = (i + 1) % bottles.length) {
      // Append the string representation of the current element to the result string, followed by a
      // newline character
      result += bottles[i].toString() + "\n";
    }
    // Append the string representation of the last element in the queue to the result string
    // (without a newline character)
    result += bottles[back].toString();
    // Return the result string
    return result;
  }
}
