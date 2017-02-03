package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */

/**
 * Class that inherits from Tweet; a kind of Tweet that is noted as not important.
 */
public class NormalTweet extends Tweet {
    /**
     * NormalTweet constructor that takes just text and auto generates the date to the current
     * time.
     * @param message The text message of the Tweet.
     */
    public NormalTweet(String message) {
        super(message);
    }

    /**
     * NormalTweet constructor that takes the test of the Tweet and a custom date.
     * @param date The date of the Tweet.
     * @param message The text message of the Tweet.
     */
    public NormalTweet(Date date, String message) {
        super(date, message);
    }

    @Override
    public Boolean isImportant(){
        return false;
    }
}
