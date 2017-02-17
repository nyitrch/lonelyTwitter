package ca.ualberta.cs.lonelytwitter;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nyitrai on 2/16/17.
 */

public class TweetList {
    private ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();

    public void add(NormalTweet tweet) {
        if (this.hasTweet(tweet)) {
            //Throw exception
            throw new IllegalArgumentException("Attempt at adding a duplicate tweet.");
        } else {
            tweets.add(tweet);
        }
    }

    public boolean hasTweet(NormalTweet tweet) {
        return tweets.contains(tweet);
    }

    public NormalTweet getTweet(int index) {
        // return new NormalTweet("not the tweet");
        return tweets.get(index);
    }

    public void delete(NormalTweet tweet) {
        tweets.remove(tweet);
    }
}
