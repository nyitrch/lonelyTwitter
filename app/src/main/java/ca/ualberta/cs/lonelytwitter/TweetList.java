package ca.ualberta.cs.lonelytwitter;

import android.util.Log;
import android.widget.ArrayAdapter;

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
        return tweets.get(index);
    }

    public TweetList getTweets() {

        ArrayList<NormalTweet> sortedTweets = new ArrayList<NormalTweet>();
        TweetList sortedTweetList = new TweetList();

        // Making a copy of the ArrayList.
        for (NormalTweet tweet : tweets) {
            NormalTweet newTweet = new NormalTweet(tweet.getDate(), tweet.getMessage());
            sortedTweets.add(newTweet);
        }

        // Sorting this new copied list.
        Collections.sort(sortedTweets, new Comparator<NormalTweet>() {
           public int compare(NormalTweet tweet1, NormalTweet tweet2) {
               return tweet1.getDate().compareTo(tweet2.getDate());
           }
        });

        // Build sorted TweetList.
        for (NormalTweet tweet : sortedTweets) {
            sortedTweetList.add(tweet);
        }
        return sortedTweetList;
    }

    public int getCount() {
        return tweets.size();
    }

    public void delete(NormalTweet tweet) {
        tweets.remove(tweet);
    }
}
