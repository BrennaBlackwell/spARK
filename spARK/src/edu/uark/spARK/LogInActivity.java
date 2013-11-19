package edu.uark.spARK;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.uark.spARK.JSONQuery.AsyncResponse;
import android.net.Uri;


public class LogInActivity extends Activity implements AsyncResponse {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
    private View mLoginView;
    //

    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        
        setContentView(R.layout.splash);
    	mLoginView = findViewById(R.id.includeLogin);
    	mLoginView.setAlpha(0f);
    	findViewById(R.id.likeButton).getBackground().setColorFilter(new LightingColorFilter(0xFFFF0000, 0xFFFF0000));
        findViewById(R.id.button2).getBackground().setColorFilter(new LightingColorFilter(0xFFFF0000, 0xFFFF0000));
        new Handler().postDelayed(new Runnable() {
       	 
            @Override
            public void run() {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                //float width = size.x;
                //float height = size.y;
                ImageView ivSplash = (ImageView) findViewById(R.id.profileImageView);
                //float spark_X = ivSplash.getLeft();
                float spark_Y = ivSplash.getTop();
            	float distance = (0-spark_Y);
            	ivSplash.animate().translationY(distance).withLayer();
            }
        }, (SPLASH_TIME_OUT));
        
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
            	mLoginView.animate().alpha(1f).setDuration(450).setListener(null);
                //Intent i = new Intent(SplashActivity.this, LogInActivity.class);
                //startActivity(i);
            	//findViewById(R.id.relativeLayoutLogin).setVisibility(View.VISIBLE);
                // close this activity
                //finish();
            }
        }, SPLASH_TIME_OUT + 450);

        SharedPreferences preferences = getSharedPreferences("MyPreferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        EditText txtUsername = (EditText)findViewById(R.id.Username);
        EditText txtPassword = (EditText)findViewById(R.id.Password);

        if (preferences.getString("currentUsername","").matches("Brenna")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=dQw4w9WgXcQ")));
        }

        txtUsername.setText(preferences.getString("currentUsername",""));
        txtPassword.setText(preferences.getString("currentPassword",""));

        if (preferences.getString("currentUsername","")!="" && preferences.getString("currentPassword","")!=""){
            Login(mLoginView);
        }

    }
    
	public void Login(View v){
		//debugging so we don't have to enter user/password every time. We will have to delete this later, but it might be useful for now
//		if (v.getId() == R.id.buttonDebug) {
//			Intent MainIntent = new Intent(this, MainActivity.class);
//			startActivity(MainIntent);
//			return;
//		}

		EditText txtUsername = (EditText)findViewById(R.id.Username);
		EditText txtPassword = (EditText)findViewById(R.id.Password);

		String Username = txtUsername.getText().toString().trim();
		String Password = txtPassword.getText().toString().trim();

		//make sure text has been added to the login screen
		if (Username.matches("")) {
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter a Username", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		else if (Password.matches("")) {
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter a Password", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
        else if (Username.matches("Brenna")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=dQw4w9WgXcQ")));
        }
		else {
			JSONQuery jquery = new JSONQuery(this);
			jquery.execute(ServerUtil.URL_AUTHENTICATE, Username, Password);
            finish();
		}
	}
	
	public void CreateAccount(View v){
		Intent CreateAccountIntent = new Intent(this, RegisterActivity.class);
		startActivityForResult(CreateAccountIntent, 1);
	}

	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
        	TextView text_view2 = (TextView)findViewById(R.id.accountCreated);
        	text_view2.setVisibility(View.VISIBLE);
        }
	}
	
	@Override
	public void processFinish(JSONObject result) {
		TextView text_view1 = (TextView)findViewById(R.id.invalidLogin);
		TextView text_view2 = (TextView)findViewById(R.id.accountCreated);
		text_view2.setVisibility(View.INVISIBLE);
		
		try {
			
			int success = result.getInt("success");

			if (success == 1) {
				text_view1.setVisibility(View.INVISIBLE);
				
				EditText userText = (EditText)findViewById(R.id.Username);
				String user = userText.getText().toString();
                EditText passText = (EditText)findViewById(R.id.Password);
                String pass = passText.getText().toString();
				SharedPreferences preferences = getSharedPreferences("MyPreferences", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
				
				editor.putString("currentUsername", user);
                editor.putString("currentPassword", pass);
				editor.apply();
				
				Intent MainIntent = new Intent(this, MainActivity.class);
				startActivity(MainIntent);
			} else {
				text_view1.setVisibility(View.VISIBLE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//hides the invalid login
		findViewById(R.id.invalidLogin).setVisibility(View.INVISIBLE);
	}
}
