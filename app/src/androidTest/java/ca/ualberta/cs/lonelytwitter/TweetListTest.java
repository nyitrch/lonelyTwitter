package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nyitrai on 2/16/17.
 */
// Test driven development: Fail -> Code -> Pass -> Test -> Fail etc.
public class TweetListTest extends ActivityInstrumentationTestCase2{

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    // Methods that are not testing something and just provide some other functionality,
    // should not start with "test".

    // All methods that test something should start with the word "test".
    public void testAddTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        // Testing basic addition.
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));

        // Testing duplicate addition.
        try {
            tweets.add(tweet);

            // If the duplicate is not caught, fail the test.
            fail();
        } catch (IllegalArgumentException e) {
            // All good! The duplicate has been caught and the test is passed.
            Log.d("lonelyTwitter", "Duplicate caught.");
        }
    }

    public void testHasTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        // Makes sure we don't have the tweet before we add it.
        assertFalse(tweets.hasTweet(tweet));

        tweets.add(tweet);
        // Makes sure we do have the tweet after we add it.
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        NormalTweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());
    }

    public void testGetTweets() {
        TweetList tweets = new TweetList();
        NormalTweet tweet1 = new NormalTweet("some tweet");
        NormalTweet tweet2 = new NormalTweet("some other tweet");
        NormalTweet tweet3 = new NormalTweet("some other other tweet");

        // Create the tweets with dates for testing.
        tweet1.setDate(new Date(2000, 1, 3));
        tweet2.setDate(new Date(2000, 1, 2));
        tweet3.setDate(new Date(2000, 1, 1));
        tweets.add(tweet1);
        tweets.add(tweet2);
        tweets.add(tweet3);

        TweetList sortedTweets = tweets.getTweets();

        // Actually test if the list is sorted.
        assertTrue(tweets.getTweet(0).getDate().compareTo(sortedTweets.getTweet(0).getDate()) > 0);
        assertTrue(tweets.getTweet(1).getDate().compareTo(sortedTweets.getTweet(1).getDate()) == 0);
        assertTrue(tweets.getTweet(2).getDate().compareTo(sortedTweets.getTweet(2).getDate()) < 0);
    }

    public void testDeleteTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        tweets.delete(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }
}
