/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
 * This is an iterator that moves through tweets in reverse chronological order
 * 
 * @author nandinihjagtiani
 *
 */
public class ChronoTwiterator implements Iterator<Tweet> {

  /**
   * The TweetNode containing the next tweet to be returned in the iteration
   */
  private TweetNode next;

  /**
   * Constructs a new twiterator at the given starting node
   * 
   * @param startNode - the node to begin the iteration at
   */
  public ChronoTwiterator(TweetNode startNode) {
    this.next = startNode;
  }

  @Override
  /**
   * Checks whether there is a next tweet to return
   * 
   * @return - true if there is a next tweet, false if the value of next is null
   */
  public boolean hasNext() {
    if (next == null) { // next node is null, no more tweets
      return false;
    } else { // next node is not null
      if (next.getTweet() == null) { // tweet is null, keep iterating
        return hasNext();
      } else { // tweet is not null, there is a next tweet
        return true;
      }
    }
  }

  @Override
  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet
   * 
   * @throws - NoSuchElementException - if there is not a next tweet to return
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    TweetNode current = next;
    next = next.getNext();
    return current.getTweet();
  }
}
