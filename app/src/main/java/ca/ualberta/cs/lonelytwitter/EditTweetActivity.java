package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by nyitrai on 3/2/17.
 */

public class EditTweetActivity extends Activity {

    private TextView tweetInfo;
    private NormalTweet tweet;

    public NormalTweet getTweet() {
        return tweet;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        tweetInfo = (TextView) findViewById(R.id.tweetInfo);

        // Get the tweet from the previous activity.
        String tweetJson = getIntent().getStringExtra("tweet");
        Gson gson = new Gson();
        tweet = gson.fromJson(tweetJson, NormalTweet.class);

        // Set the text on screen for the tweet.
        tweetInfo.setText(tweet.toString());
    }

}
