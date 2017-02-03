package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */

/**
 * Class that inherits from Tweet; a kind of Tweet that is noted as important.
 */
public class ImportantTweet extends Tweet {
    /**
     * ImportantTweet constructor that takes just text and auto generates the date to the current
     * time.
     * @param message The text message of the Tweet.
     */
    public ImportantTweet(String message) {
        super(message);
    }

    /**
     * ImportantTweet constructor that takes the test of the Tweet and a custom date.
     * @param date The date of the Tweet.
     * @param message The text message of the Tweet.
     */
    public ImportantTweet(Date date, String message) {
        super(date, message);
    }

    @Override
    public Boolean isImportant(){
        return true;
    }
}
