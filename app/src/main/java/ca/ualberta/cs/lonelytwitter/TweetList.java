package ca.ualberta.cs.lonelytwitter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public TweetList getTweets() {

        Collections.sort(tweets, new Comparator<NormalTweet>() {
           public int compare(NormalTweet tweet1, NormalTweet tweet2) {
               return tweet1.getDate().compareTo(tweet2.getDate());
           }
        });
        return this;
    }

    public void delete(NormalTweet tweet) {
        tweets.remove(tweet);
    }
}
