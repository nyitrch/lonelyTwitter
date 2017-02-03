package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */

/**
 * Basic Tweet class that provides implementation of a Tweet.
 * <br><br>
 *     A Tweet is defined in this project as a text message with an attached date
 *     and an 'isImportant()" boolean, demarcating whether or not the tweet is important.
 *
 */
public abstract class Tweet implements Tweetable{
    /**
     * The Tweet's attached date. The date that the Tweet was made or a custom date.
     */
    private Date date;
    /**
     * The text message of the Tweet.
     */
    private String message;

    /**
     * Tweet constructor that takes just a message. Auto-generates the date of the tweet
     * to be time when the constructor is called.
     * @param message The text message of the Tweet.
     */
    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    /**
     * Tweet constructor that takes a message as well as a custom date.
     * @param date The Tweet's attached date.
     * @param message The text message of the Tweet.
     */
    public Tweet(Date date, String message){
        this.message = message;
        this.date = date;
    }

    /**
     * Tweet date getter. Gets the date of the Tweet.
     * @return The Tweet's attached date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Tweet date setter. Sets the date of the Tweet.
     * @param date The Tweet's attached date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Tweet message getter. Gets the text message of the Tweet.
     * @return The text message of the Tweet.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Tweet message setter. Sets the text message of the Tweet. Text message must not
     * be longer than 140 characters, or else TweetTooLongException is thrown.
     * @param message The text message of the Tweet.
     * @throws TweetTooLongException If the message is over 140 characters.
     */
    public void setMessage(String message) throws TweetTooLongException{
        if (message.length() > 140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    /**
     * Demarcates if the Tweet is important or not.
     * @return True if the Tweet is important, False if the Tweet is not.
     */
    public abstract Boolean isImportant();

    /**
     * Conversion of a Tweet (date and message) into a string for printing.
     * @return 'date of Tweet' | 'message of Tweet'
     */
    @Override
    public String toString() {
        return date.toString() + " | " + message;

    }


}
