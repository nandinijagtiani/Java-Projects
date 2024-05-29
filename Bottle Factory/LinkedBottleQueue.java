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
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and removed
 * with respect to the First In First Out (FIFO) scheduling policy.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

  // reference to the front of the queue
  private LinkedNode<Bottle> front;

  // reference to the back of the queue
  private LinkedNode<Bottle> back;

  // indicating the number of bottles in the queue
  private int size;

  // defining the max number of bottles
  private int capacity;


  /**
   * Initializes the fields of this queue including its capacity. A newly created queue must be
   * empty, meaning that both its front and back are null and its size is zero.
   *
   * @param capacity - Positive integer defining the max number of bottles the queue can hold
   */
  public LinkedBottleQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.capacity = capacity;
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ...
   * SN:Filled/Empty:Capped/Open
   *
   * The string should not contain a newline character at the end.
   *
   * @return - String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    // If the queue is empty, throw a NoSuchElementException
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // Initialize an empty string variable to store the result
    String result = "";
    // Iterate through each bottle in the queue
    for (Bottle b : this) {
      // Append the string representation of the bottle to the result, with a space separator
      result += b.toString() + " ";
    }
    // Return the final result string
    return result;
  }

  /**
   * Returns true if and only if the queue contains no elements
   *
   * @return boolean value
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns true if and only if the queue has reached its capacity
   *
   * @return boolean value
   */
  @Override
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Returns the number of elements in the queue
   *
   * @return the size of the queue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle to add to the queue
   */
  @Override
  public void enqueue(Bottle bottle) {
    // Check if the queue is already full
    if (isFull()) {
      // Throw an exception if it is
      throw new IllegalStateException("The queue is full");
    }
    // Check if the bottle being added is null
    if (bottle == null) {
      // Throw an exception if it is
      throw new NullPointerException("The Bottle cannot be null");
    }
    // Create a new node for the bottle
    LinkedNode<Bottle> newNode = new LinkedNode(bottle);
    // Check if the queue is empty
    if (isEmpty()) {
      // If it is, set the front node to be the new node
      front = newNode;
      // If the queue is not empty
    } else {
      // Set the next node of the back node to be the new node
      back.setNext(newNode);
    }
    // Set the back node to be the new node
    back = newNode;
    // Increase the size of the queue
    size++;
  }

  /**
   * Removes and returns the first bottle in the queue
   *
   * @return Top/First bottle in the queue
   */
  @Override

  public Bottle dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // Get the data of the front node
    Bottle removedBottle = front.getData();
    // Set the front node to be the next node
    front = front.getNext();
    // Decrease the size of the queue
    size--;
    // If the queue is now empty, set the back node to null
    if (isEmpty()) {
      back = null;
    }
    // Return the removed bottle
    return removedBottle;
  }


  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   */
  @Override
  public Bottle peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // Return the data of the front node
    return front.getData();
  }

  /**
   * Returns a deep copy of this queue.
   *
   * @return deep copy of this queue.
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copy = new LinkedBottleQueue(capacity);
    LinkedNode<Bottle> current = front;
    // Iterate through the queue and enqueue a copy of each bottle
    while (current != null) {
      copy.enqueue(current.getData());
      current = current.getNext();
    }
    // Return the copy of the queue
    return copy;
  }

  /**
   * Returns an iterator for traversing the queue's items
   *
   * @return iterator to traverse the LinkedListQueue
   */
  @Override
  public Iterator<Bottle> iterator() {
    // Return a new iterator for the copy of the queue
    return new BottleQueueIterator(copy());
  }
}
