package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class of the project. <br> In this class, user interaction
 * and file manipulation is performed.
 * All files are in the form of "json" files that are stored in the Emulator's accessible
 * from Android Device Monitor.
 * <pre>
 *     pre-formatted text: <br>
 *         File Explorer -> data -> data -> ca.ualberta.ca.cs.lonelytwitter -> files -> file.sav
 * </pre>
 * <code> begin <br>
 * some pseudo code <br>
 * end.</code>
 * The file name is indicated in the &nbsp &nbsp &nbsp FILENAME constant.
 * <ul>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 * </ul>
 * <ol>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 * </ol>
 *
 * @author nyitrai
 * @version 1.0
 * @see Tweet
 * @since 0.5
 */
public class LonelyTwitterActivity extends Activity {
	/**
	 * The file that all the tweets are saved in. The format of the file is JSON.
	 * @see #loadFromFile
	 * @see #saveInFile
	 */
	private static final String FILENAME = "file.sav";

	/** Used to sort a tweetList.
	 * @see #sortTweetListItems
	 */
	private enum TweetListOrdering {DATE_ASCENDING, DATE_DESCENDING,
		TEXT_ASCENDING, TEXT_DESCENDING};

	/** Text that becomes the text message of the Tweet. */
	private EditText bodyText;

	/** The list of older Tweets that is stored on the Android device. */
	private ListView oldTweetsList;

	/**An ArrayList of Tweet objects. Used to store, read, etc., many Tweets at a time. */
	private ArrayList<Tweet> tweetList;

	/**Adapter that backs tweetList. Used in notification of observers. */
	private ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = trimExtraSpaces(text);

				Tweet tweet = new NormalTweet(text);

				tweetList.add(tweet);
				adapter.notifyDataSetChanged();
				saveInFile();

			}
		});

		clearButton.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {

				setResult(RESULT_OK);
				tweetList.clear();
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});
	}

	/**
	 * Called after onCreate() or onRestart(). That is, after the activity
	 * comes into view for the first time, or again after being stopped.
	 * <br><br>The basic function of onStart() here is to load the tweetList form the file stored
	 * on the Android device.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loadFromFile();

		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * Trims excess spaces using regular expression.
	 * @param inputString string that needs to be trimmed of extra spaces.
	 * @return Resulting string without extra spaces.
     */
	private String trimExtraSpaces(String inputString){
		inputString = inputString.replaceAll("\\s+", " ");
		return inputString;
	}

	/**
	 * This method sorts items in the tweet list and refreshes the adapter.
	 * @param ordering Ordering to be used
     */
	private void sortTweetListItems(TweetListOrdering ordering) {

	}

	/**
	 * Loads tweet from specified file.
	 *
	 * @throws TweetTooLongException if the text is too long to load.
	 * @exception FileNotFoundException if the file is not already created.
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();

			// The following lines were taken on Jan 24, 2017 18:19 from:
			// http://stackoverflow.com/
			// questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO: Handle exception later
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO: Handle exception later.
			throw new RuntimeException();
		}
	}

	/**
	 * Saves tweet to specified file in JSON format.
	 *
	 * @throws FileNotFoundException if file folder doesn't exist.
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO: Handle the exception later.
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO: Handle the exception later.
			throw new RuntimeException();
		}
	}
}