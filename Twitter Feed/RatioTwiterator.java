////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
 * This is an iterator that moves through tweets in reverse chronological order by high like ratio
 * only
 * 
 * @author nandinihjagtiani
 *
 */
public class RatioTwiterator implements Iterator<Tweet> {

  /**
   * The TweetNode containing the next tweet to be returned in the iteration
   */
  private TweetNode next;

  /**
   * The minimum threshold value for the ratio of likes to total engagement
   */
  private final double THRESHOLD;

  /**
   * Constructs a new twiterator at the given starting node
   * 
   * @param startNode - the node to begin the iteration at
   * @param threshold - the minimum threshold value for the ratio of likes to total engagement,
   *                  assumed to be between 0.0 and 1.0 thanks to range checking in Timeline
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {

    this.next = startNode;
    this.THRESHOLD = threshold;
  }

  @Override
  /**
   * Checks whether there is a next tweet to return
   * 
   * @return - true if there is a next tweet, false if the value of next is null
   */
  public boolean hasNext() {
    while (next != null) {
      double likesRatio = next.getTweet().getLikesRatio();
      if (likesRatio > THRESHOLD) {
        return true;
      }
      next = next.getNext();
    }
    return false;
  }


  @Override
  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet with
   * a likes ratio in excess of the given threshold
   * 
   * @throws - NoSuchElementException - if there is not a next tweet to return
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No tweet found with likes ratio in excess of " + THRESHOLD);
    }
    TweetNode current = next;
    next = next.getNext();
    return current.getTweet();
  }
}
