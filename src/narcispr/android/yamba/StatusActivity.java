package narcispr.android.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class StatusActivity extends Activity {
	private static final String TAG = "StatusActivity";
	EditText editText;
	Button updateButton;
	Twitter twitter;
	
    /** Called when the activity is first created. */
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        
        //Find Views
        editText = (EditText) findViewById(R.id.editText);
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        
        //Init twitter
        twitter = new Twitter("narcispr", "password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
    }
    
 // Asynchronously posts to twitter
    class PostToTwitter extends AsyncTask<String, Integer, String> { // <1>
      // Called to initiate the background activity
      @Override
      protected String doInBackground(String... statuses) { // <2>
        	try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return "done";
      }

      // Called when there's a status to be updated
      @Override
      protected void onProgressUpdate(Integer... values) { // <3>
        super.onProgressUpdate(values);
        // Not used in this case
      }

      // Called once the background activity has completed
      @Override
      protected void onPostExecute(String result) { // <4>
        Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
      }
    }
    
    public void myClickHandler(View view) {
    	//twitter.setStatus(editText.getText().toString());
    	String status = "fet!";
    	new PostToTwitter().execute(status);
    	Log.d(TAG, "myClickHandler");
    }
}