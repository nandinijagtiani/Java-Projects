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
import java.util.Calendar;
import java.util.Date;

public class Tweet {

  /**
   * A shared Calendar object for this class to use to generate consistent dates. Initialized using
   * the setCalendar() method
   */
  private static Calendar dateGenerator;

  /**
   * The User associated with this tweet
   */
  private User user;

  /**
   * The text of this tweet
   */
  private String text;

  /**
   * The number of likes this tweet has
   */
  private int numLikes;

  /**
   * The number of retweets this tweet has
   */
  private int numRetweets;

  /**
   * The date and time this tweet was posted, a Date object created by calling
   * dateGenerator.getTime() at construction time
   */
  private Date timestamp;

  /**
   * A value determining the maximum length of a tweet. Set to 280 by default.
   * 
   */
  private static final int MAX_LENGTH = 280;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its
   * timestamp should be set to the current time.
   * 
   * @param user - the User posting this tweet
   * @param text - the text of the tweet
   * @throws IllegalArgumentException
   * @throws NullPointerException
   * @throws IllegalStateException
   */
  public Tweet(User user, String text)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (user == null) {
      throw new NullPointerException("User cannot be null");
    }

    if (text == null) {
      throw new NullPointerException("Text cannot be null");
    }

    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException(
          "Tweet text exceeds maximum length of " + MAX_LENGTH + " characters");
    }

    if (dateGenerator == null) {
      throw new IllegalStateException("dateGenerator field has not yet been initialized");
    }
    this.user = user;
    this.text = text;
    this.numLikes = 0;
    this.numRetweets = 0;
    this.timestamp = dateGenerator.getTime();
  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object. For tests which do
   * not require a consistent date, you can use Calendar.getInstance() to get a Calendar set to the
   * current time. If your tests require a consistent date, see the writeup for examples of how to
   * set the time.
   * 
   * @param c - the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }

  /**
   * Accesses the text of this tweet
   * 
   * @return - the text of this tweet
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * 
   * @return - the total engagement of this tweet
   */
  public int getTotalEngagement() {
    return numLikes + numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * 
   * @return - true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    return this.user.isVerified();
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do
   * positive, uplifting ratios around here.
   * 
   * @return - the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the
   *         total engagement is 0.
   */
  public double getLikesRatio() {
    int totalEngagement = numLikes + numRetweets;
    if (totalEngagement == 0) {
      return -1.0;
    } else {
      double likesRatio = (double) numLikes / totalEngagement;
      return likesRatio;
    }
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    numLikes++;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    numRetweets++;
  }

  @Override
  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
   * that contains the same text posted at the same time by the same User (use the toString() method
   * from User to compare these!) then the two Tweets are considered equal regardless of their
   * respective likes/retweets.
   * 
   * @param - o - the object to compare this Tweet to
   * @return - true if this Tweet is equivalent to the provided object, false otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof Tweet) {
      Tweet other = (Tweet) o;
      if (text.equals(other.text) && timestamp.equals(other.timestamp)
          && user.toString().equals(other.user.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * A string representation of this tweet. See writeup for examples.
   * 
   * @return - a formatted string representation of this tweet
   */
  @Override
  public String toString() {
    return "tweet from " + user.toString() + " at " + timestamp + ":\n" + "-- " + text + "\n"
        + "-- likes: " + numLikes + "\n" + "-- retweets: " + numRetweets;
  }



}
