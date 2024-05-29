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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list. Note that while we CALL this "reverse
 * chronological" order, this is NOT enforced. You can add Tweets anywhere in the list relative to
 * each other, regardless of their respective timestamps.
 * 
 * @author nandinihjagtiani
 *
 */
public class TwitterFeed extends Object implements ListADT<Tweet>, Iterable<Tweet> {

  /**
   * The node containing the most recent tweet
   */
  private TweetNode head;

  /**
   * The node containing the oldest tweet
   */
  private TweetNode tail;

  /**
   * The number of tweets in this linked list
   */
  private int size;

  /**
   * The iteration mode for the timeline display
   * 
   */
  private TimelineMode mode;

  /**
   * The ratio of likes to total engagement that we want to see; set to .5 by default
   */
  private static double ratio = 0.5;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default
   * values, and the timeline mode to CHRONOLOGICAL.
   */
  public TwitterFeed() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.mode = TimelineMode.CHRONOLOGICAL;
  }

  @Override
  /**
   * Accessor for the size of the feed
   * 
   * @return - the number of TweetNodes in this TwitterFeed
   */
  public int size() {
    return this.size;
  }

  @Override
  /**
   * Determines whether this feed is empty. Recommend checking MORE than just the size field to get
   * this information, though if all methods are implemented correctly the size field's value will
   * be sufficient.
   * 
   * @return - true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  public boolean isEmpty() {
    return (head == null && tail == null);
  }

  @Override
  /**
   * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's equals() method!
   * 
   * @return - true if the Tweet is present, false otherwise
   */
  public boolean contains(Tweet findObject) {
    TweetNode current = head;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  @Override
  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   * 
   * @param - findObject - the Tweet to search for
   * @return - the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  public int indexOf(Tweet findObject) {
    int index = 0;
    TweetNode curr = head;
    while (curr != null) {
      if (curr.getTweet().equals(findObject)) {
        return index;
      }
      index++;
      curr = curr.getNext();
    }
    return -1;
  }

  @Override
  /**
   * Accessor method for the Tweet at a given index
   * 
   * @param - index - the index of the Tweet in question
   * @return - the Tweet object at that index (NOT its TweetNode!)
   * @throws - IndexOutOfBoundsException - if the index is negative or greater than the largest
   *           index of the TwitterFeed
   */
  public Tweet get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(
          "index is negative or greater than the largest  index of the TwitterFeed");
    }

    TweetNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    return current.getTweet();
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   * 
   * @return - the Tweet object at the head of the linked list
   * @throws NoSuchElementException - if the TwitterFeed is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (head == null) {
      throw new NoSuchElementException("The TwitterFeed is empty.");
    }
    return head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   * 
   * @return - the Tweet object at the tail of the linked list
   * 
   * @throws NoSuchElementException - if the TwitterFeed is empty
   */
  public Tweet getTail() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Twitter feed is empty");
    }
    return tail.getTweet();
  }

  @Override
  /**
   * Adds the given Tweet to the tail of the linked list
   * 
   * @param - newObject - the Tweet to adds
   */
  public void addLast(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      head = newNode;
    } else {
      tail.setNext(newNode);
    }
    tail = newNode;
    size++;
  }

  @Override
  /**
   * Adds the given Tweet to the head of the linked list
   * 
   * @param - newObject - the Tweet to add
   */
  public void addFirst(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject, head);
    head = newNode;
    if (tail == null) {
      tail = newNode;
    }
    size++;
  }

  @Override
  /**
   * Adds the given Tweet to a specified position in the linked list
   * 
   * @param - index - the position at which to add the new Tweet
   * @param - newObject - the Tweet to add
   * @throws - IndexOutOfBoundsException - if the index is negative or greater than the size of the
   *           linked list
   */
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    TweetNode newNode = new TweetNode(newObject);
    if (size == 0) {
      head = tail = newNode;
    } else if (index == 0) {
      newNode.setNext(head);
      head = newNode;
    } else if (index == size) {
      tail.setNext(newNode);
      tail = newNode;
    } else {
      TweetNode prev = head;
      for (int i = 0; i < index - 1; i++) {
        prev = prev.getNext();
      }
      newNode.setNext(prev.getNext());
      prev.setNext(newNode);
    }
    size++;

  }

  @Override
  /**
   * Removes and returns the Tweet at the given index
   * 
   * @param - index - the position of the Tweet to remove
   * @return - he Tweet object that was removed from the list
   * @throws - IndexOutOfBoundsException - if the index is negative or greater than the largest
   *           index currently present in the linked list
   */
  public Tweet delete(int index) throws IndexOutOfBoundsException {
    if (isEmpty() || index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    Tweet deletedTweet;
    if (index == 0) { // deleting head node
      deletedTweet = head.getTweet();
      head = head.getNext();
      if (head == null) { // if deleting the only node
        tail = null;
      }
    } else {
      TweetNode prevNode = head;
      for (int i = 1; i < index; i++) {
        prevNode = prevNode.getNext();
        if (prevNode == null) {
          throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
      }
      TweetNode currentNode = prevNode.getNext();
      deletedTweet = currentNode.getTweet();
      prevNode.setNext(currentNode.getNext());
      if (currentNode == tail) { // if deleting tail node
        tail = prevNode;
      }
    }
    size--;
    return deletedTweet;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   * 
   * @param m - the iteration mode; any value from the TimelineMode enum
   */
  public void setMode(TimelineMode m) {
    this.mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   * 
   * @return - any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the
   *         head of this TwitterFeed list
   */
  public Iterator<Tweet> iterator() {
    switch (mode) {
      case CHRONOLOGICAL:
        return new ChronoTwiterator(head);
      case VERIFIED_ONLY:
        return new VerifiedTwiterator(head);
      case LIKE_RATIO:
        return new RatioTwiterator(head, ratio);
      default:
        throw new IllegalStateException("Unsupported timeline mode: " + mode);
    }
  }
}
