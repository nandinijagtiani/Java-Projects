///////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
import java.util.Iterator;

/**
 * This is a class that tests all other classes in this Project. It includes the main method
 * 
 * @author nandinihjagtiani
 *
 */
public class TwiteratorTester {

  /**
   * 
   * Tests the User class by verifying that a user can be created and verified, creating a user with
   * an invalid username and creating a user with a username that contains a *.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testUser() {

    // Test creating and verifying a user
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    User user1 = new User("testuser");
    if (user1.isVerified()) {
      return false;
    }
    user1.verify();
    if (!user1.isVerified()) {
      return false;
    }

    // Test creating a user with an invalid username
    try {
      User user2 = new User(null);
      return false;
    } catch (IllegalArgumentException e) {
      // Expected exception, do nothing
    }

    // Test creating a user with a username that contains a *
    try {
      User user3 = new User("user*name");
      return false;
    } catch (IllegalArgumentException e) {
      // Expected exception, do nothing
    }

    return true; // for now
  }

  /**
   * 
   * Tests the Tweet class by verifying that a tweet can be created with valid and invalid
   * arguments, and that the string representation of a tweet is generated correctly.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testTweet() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());


    // Initialize the date generator for testing
    Calendar testCalendar = Calendar.getInstance();
    testCalendar.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(testCalendar);

    // Test creating a tweet and getting its fields
    User user = new User("testuser");
    Tweet tweet1 = new Tweet(user, "Test tweet");
    // if (!tweet1.getUser().equals(user)) {
    // return false;
    // }
    if (!tweet1.getText().equals("Test tweet")) {
      return false;
    }
    if (tweet1.getTotalEngagement() != 0) {
      return false;
    }
    if (tweet1.isUserVerified()) {
      return false;
    }
    if (tweet1.getLikesRatio() != -1.0) {
      return false;
    }

    // Test creating a tweet with invalid arguments
    try {
      Tweet tweet2 = new Tweet(null, "Test tweet");
      return false;
    } catch (NullPointerException e) {
      // Expected exception, do nothing
    }

    try {
      Tweet tweet3 = new Tweet(user, null);
      return false;
    } catch (NullPointerException e) {
      // Expected exception, do nothing
    }

    // Test the string representation of a tweet
    String expected = "tweet from @testuser at Tue May 22 14:46:03 CDT 2012:\n" + "-- Test tweet\n"
        + "-- likes: 1\n" + "-- retweets: 1";

    // Like and retweet the tweet
    tweet1.like();
    tweet1.retweet();

    if (!tweet1.toString().equals(expected)) {
      return false;
    }

    return true;
  }

  /**
   * 
   * Tests the Tweet class by verifying that a tweet can be created with valid and invalid
   * arguments, and that the string representation of a tweet is generated correctly.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testNode() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    User user1 = new User("user1");
    Tweet tweet1 = new Tweet(user1, "Hello world!");

    User user2 = new User("user2");
    Tweet tweet2 = new Tweet(user2, "This is a test tweet.");

    User user3 = new User("user3");
    Tweet tweet3 = new Tweet(user3, "This is another test tweet.");

    TweetNode node1 = new TweetNode(tweet1);
    TweetNode node2 = new TweetNode(tweet2);

    // Test the two-argument constructor with a non-null nextTweet field
    TweetNode node3 = new TweetNode(tweet3, node2);
    try {
      if (!node3.getNext().equals(node2)) {
        System.out.println(
            "Error: nextTweet not set correctly in two-argument constructor with non-null value.");
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println(
          "Error: nextTweet not set correctly in two-argument constructor with non-null value.");
      return false;
    }

    // Test the two-argument constructor with a null nextTweet field
    TweetNode node4 = new TweetNode(tweet1, null);
    if (node4.getNext() != null) {
      System.out
          .println("Error: nextTweet should be null in two-argument constructor with null value.");
      return false;
    }

    // Set nextTweet field manually and verify
    node1.setNext(node2);

    return node1.getTweet().equals(tweet1) && node2.getTweet().equals(tweet2)
        && node1.getNext() != null && node1.getNext().equals(node2) && node2.getNext() == null;
  }

  /**
   * 
   * Tests the Tweet class by verifying that a tweet can be created with valid and invalid
   * arguments, and that the string representation of a tweet is generated correctly.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddTweet() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    TwitterFeed feed = new TwitterFeed();
    if (!feed.isEmpty() || feed.size() != 0) {
      return false;
    }
    Tweet tweet1 = new Tweet(new User("user1"), "Hello world!");
    feed.addFirst(tweet1);
    if (feed.isEmpty() || feed.size() != 1 || !feed.contains(tweet1) || feed.get(0) != tweet1
        || feed.getHead() != tweet1 || feed.getTail() != tweet1) {
      return false;
    }

    Tweet tweet2 = new Tweet(new User("user2"), "This is a test tweet.");
    feed.addLast(tweet2);
    if (feed.isEmpty() || feed.size() != 2 || !feed.contains(tweet2) || feed.get(1) != tweet2
        || feed.getHead() != tweet1 || feed.getTail() != tweet2) {
      return false;
    }

    return true;
  }

  /**
   * 
   * Tests the add() method in the TwitterFeed class by verifying that tweets can be inserted into a
   * TwitterFeed and that they are inserted at the correct index.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testInsertTweet() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    TwitterFeed feed = new TwitterFeed();

    Tweet tweet1 = new Tweet(new User("user1"), "Tweet 1");
    Tweet tweet2 = new Tweet(new User("user2"), "Tweet 2");
    Tweet tweet3 = new Tweet(new User("user3"), "Tweet 3");
    Tweet tweet4 = new Tweet(new User("user4"), "Tweet 4");
    Tweet tweet5 = new Tweet(new User("user5"), "Tweet 5");
    Tweet[] tweets = {tweet1, tweet2, tweet3, tweet4, tweet5};

    for (int i = 0; i < tweets.length; i++) {
      feed.add(i, tweets[i]);
    }
    return feed.size() == tweets.length && feed.get(0).equals(tweet1) && feed.get(1).equals(tweet2)
        && feed.get(2).equals(tweet3) && feed.get(3).equals(tweet4) && feed.get(4).equals(tweet5)
        && feed.getHead().equals(tweet1) && feed.getTail().equals(tweet5);
  }

  /**
   * 
   * Tests the delete() method in the TwitterFeed class by verifying that tweets can be deleted from
   * a TwitterFeed and that the head and tail pointers are updated correctly.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testDeleteTweet() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    TwitterFeed feed = new TwitterFeed();
    Tweet tweet1 = new Tweet(new User("user1"), "Tweet 1");
    Tweet tweet2 = new Tweet(new User("user2"), "Tweet 2");
    Tweet tweet3 = new Tweet(new User("user3"), "Tweet 3");
    Tweet tweet4 = new Tweet(new User("user4"), "Tweet 4");
    Tweet tweet5 = new Tweet(new User("user5"), "Tweet 5");

    Tweet[] tweets = {tweet1, tweet2, tweet3, tweet4, tweet5};
    for (Tweet tweet : tweets) {
      feed.addLast(tweet);
    }

    // Test removing last tweet
    Tweet deletedTweet = feed.delete(feed.size() - 1);
    if (!deletedTweet.equals(tweet5) || !feed.getTail().equals(tweet4)) {
      return false;
    }

    // Test removing first tweet
    deletedTweet = feed.delete(0);
    if (!deletedTweet.equals(tweet1) || !feed.getHead().equals(tweet2)) {
      return false;
    }

    // Test removing middle tweet
    deletedTweet = feed.delete(1);
    if (!deletedTweet.equals(tweet3) || !feed.get(1).equals(tweet4)) {
      return false;
    }

    return true;
  }

  /**
   * Tests the ChronoTwiterator class by iterating through a TwitterFeed and verifying that tweets
   * are returned in chronological order.
   * 
   * @return - true if all tests pass, false otherwise
   */
  public static boolean testChronoTwiterator() {
    // initialize date generator

    Tweet.setCalendar(Calendar.getInstance());

    // create TwitterFeed and add Tweets
    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("user1");
    User user2 = new User("user2");
    User user3 = new User("user3");
    feed.addLast(new Tweet(user1, "Tweet 1"));
    feed.addLast(new Tweet(user2, "Tweet 2"));
    feed.addLast(new Tweet(user3, "Tweet 3"));



    // iterate through feed using ChronoTwiterator
    Iterator<Tweet> iterator = feed.iterator();
    int i = 1;
    while (iterator.hasNext()) {
      Tweet tweet = iterator.next();
      if (!tweet.getText().equals("Tweet " + i)) {
        return false;
      }
      i++;
    }
    return true;
  }

  /**
   * 
   * Tests the VerifiedTwiterator class by iterating through a TwitterFeed and verifying that only
   * tweets from verified users are returned.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVerifiedTwiterator() {
    // initialize date generator
    Calendar calendar = Calendar.getInstance();
    Tweet.setCalendar(calendar);

    // create TwitterFeed and add Tweets
    TwitterFeed feed = new TwitterFeed();
    User verifiedUser = new User("verifiedUser");
    User unverifiedUser = new User("unverifiedUser");
    feed.addLast(new Tweet(verifiedUser, "Verified tweet"));
    feed.addLast(new Tweet(unverifiedUser, "Unverified tweet"));
    feed.addLast(new Tweet(verifiedUser, "Another verified tweet"));

    // iterate through feed using VerifiedTwiterator
    feed.setMode(TimelineMode.VERIFIED_ONLY);
    Iterator<Tweet> iterator = feed.iterator();
    int i = 1;
    while (iterator.hasNext()) {
      Tweet tweet = iterator.next();
      if (!tweet.getText().equals("Verified tweet " + i)) {
        return false;
      }
      i++;
    }
    return true;
  }

  /**
   * 
   * Tests the RatioTwiterator class by iterating through a TwitterFeed and verifying that only
   * tweets with a likes ratio above a specified threshold are returned.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRatioTwiterator() {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    // create TwitterFeed and add Tweets
    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("user1");
    User user2 = new User("user2");
    User user3 = new User("user3");
    Tweet tweet1 = new Tweet(user1, "Tweet 1");
    Tweet tweet2 = new Tweet(user2, "Tweet 2");
    Tweet tweet3 = new Tweet(user3, "Tweet 3");

    // like and retweet some of the tweets to ensure non-zero engagement
    tweet1.like();
    tweet2.like();
    tweet2.like();
    tweet3.like();
    tweet3.retweet();
    tweet3.retweet();
    tweet3.retweet();

    feed.addLast(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);

    // iterate through feed using RatioTwiterator with threshold of 0.6
    double threshold = 0.6;
    feed.setMode(TimelineMode.LIKE_RATIO);
    Iterator<Tweet> iterator = feed.iterator();
    while (iterator.hasNext()) {
      Tweet tweet = iterator.next();
      double likesRatio = tweet.getLikesRatio();
      if (likesRatio < threshold) {
        return false;
      }
    }
    return true;
  }

  /**
   * The main method that calls all the test methods and prints their results to the console.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // initialize date generator
    Tweet.setCalendar(Calendar.getInstance());

    System.out.println("Test User " + testUser());
    System.out.println("Test Tweet " + testTweet());
    System.out.println("Test Node " + testNode());
    System.out.println("Test AddTweet " + testAddTweet());
    System.out.println("Test InsertTweet " + testInsertTweet());
    System.out.println("Test DeleteTweet " + testDeleteTweet());
    System.out.println("Test ChronoTwiterator " + testChronoTwiterator());
    System.out.println("Test VerifiedTwiterator " + testVerifiedTwiterator());
    System.out.println("Test RatioTwiterator " + testRatioTwiterator());
  }

}
