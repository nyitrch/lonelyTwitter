package ca.ualberta.cs.lonelytwitter;

/**
 * Created by shida3 on 1/19/17.
 */

/**
 * Interface for basic Tweet object,
 * All Tweetables must provide the capabilities getMessage() and setMessage().
 */
public interface Tweetable {
    public String getMessage();
    public void setMessage(String string) throws TweetTooLongException;
}
