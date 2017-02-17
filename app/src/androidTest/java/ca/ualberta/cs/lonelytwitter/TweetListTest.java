package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

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

    public void testDeleteTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        tweets.delete(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }
}
