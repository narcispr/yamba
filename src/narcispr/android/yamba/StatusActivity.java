package narcispr.android.yamba;

//import winterwell.jtwitter.Twitter;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;


public class StatusActivity extends Activity implements TextWatcher {
	private static final String TAG = "StatusActivity";
	EditText editText;
	Button updateButton;
	ProgressBar progressBar;
	//Twitter twitter;
	
    /** Called when the activity is first created. */
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        
        //Find Views
        editText = (EditText) findViewById(R.id.editText);
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText.addTextChangedListener(this);
        //Init twitter
        //twitter = new Twitter("narcispr", "password");
        //twitter.setAPIRootUrl("http://yamba.marakana.com/api");
    }
    
 // Asynchronously posts to twitter
    class PostToTwitter extends AsyncTask<String, Integer, String> { // <1>
      // Called to initiate the background activity
      @Override
      protected void onPreExecute() {
    	  progressBar.setProgress(0);
       	  progressBar.setVisibility(View.VISIBLE);
      }
      
      protected String doInBackground(String... statuses) { // <2>
        	try {
    			for(int i = 0; i < 10; i++) {
    				Thread.sleep(300);
    				publishProgress(i*10) ;
    			}
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return "done";
      }

      // Called when there's a status to be updated
      @Override
      protected void onProgressUpdate(Integer... values) { // <3>
        progressBar.setProgress(values[0]);
      }

      // Called once the background activity has completed
      @Override
      protected void onPostExecute(String result) { // <4>
    	progressBar.setVisibility(View.GONE);
        Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
      }
    }
    
    public void myClickHandler(View view) {
    	//twitter.setStatus(editText.getText().toString());
    	String status = "fet!";
    	new PostToTwitter().execute(status);
    	Log.d(TAG, "myClickHandler");
    }

    //Implements TextWatcher
	public void afterTextChanged(Editable statusText) {
		if(statusText.length() <= 10) editText.setTextColor(Color.BLACK);
		else if((statusText.length() > 12) && (statusText.length() < 16)) editText.setTextColor(Color.rgb(255, 140, 0));
		else if (statusText.length() >= 16) editText.setTextColor(Color.RED);
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
}