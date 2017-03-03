package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */

/**
 * Used in testing the LonelyTwitterActivity.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    /**
     * Constructor that tests the LonelyTwitter Activity.
     */
    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * Called when the test is started, and gets the Activity.
     * @throws Exception
     */
    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testTweet() {
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);

        solo.clickOnButton("Clear");
        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet");

        solo.clickOnButton("Save");

        solo.clearEditText((EditText) solo.getView(R.id.body));
        assertTrue(solo.waitForText("Test Tweet"));

        solo.clickOnButton("Clear");

        assertFalse(solo.waitForText("Test Tweet"));
    }

    public void testClickTweetList() {

        LonelyTwitterActivity activity = (LonelyTwitterActivity)solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);

        solo.clickOnButton("Clear");
        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet");

        solo.clickOnButton("Save");
        solo.waitForText("TextView");

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("Test Tweet", tweet.getMessage());

        solo.clickInList(0);

        solo.assertCurrentActivity("Wrong Activity", EditTweetActivity.class);
        assertTrue(solo.waitForText("Test Tweet"));

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
    }

    public void testEditTweet() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity)solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);

        // Add test tweet.
        solo.clickOnButton("Clear");
        solo.enterText((EditText) solo.getView(R.id.body), "This is my favorite tweet.");
        solo.clickOnButton("Save");
        solo.waitForText("This is my favorite tweet.");

        // Get it so we know what we are looking for.
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet oldTweet = (Tweet) oldTweetsList.getItemAtPosition(0);

        // Click on the test tweet.
        solo.clickInList(0);
        solo.waitForText("This is my favorite tweet.");
        solo.assertCurrentActivity("Wrong Activity", EditTweetActivity.class);

        EditTweetActivity editTweetActivity = (EditTweetActivity)solo.getCurrentActivity();

        // Check that what is in this Activity is the same as the tweet we entered earlier.
        final Tweet newTweet = editTweetActivity.getTweet();
        assertEquals(oldTweet.toString(), newTweet.toString());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
