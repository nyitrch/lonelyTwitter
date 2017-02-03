package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */

/**
 * Used in testing the LonelyTwitterActivity.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    /**
     * Constructor that tests the LonelyTwitter Activity.
     */
    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    /**
     * Called when the test is started, and gets the Activity.
     * @throws Exception
     */
    public void testStart() throws Exception {
        Activity activity = getActivity();

    }
}