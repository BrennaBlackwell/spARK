package edu.uark.spARK.fragment;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.uark.spARK.R;
import edu.uark.spARK.R.id;
import edu.uark.spARK.R.layout;
import edu.uark.spARK.activity.MainActivity;
import edu.uark.spARK.data.JSONQuery;
import edu.uark.spARK.data.ServerUtil;
import edu.uark.spARK.data.JSONQuery.AsyncResponse;
import edu.uark.spARK.entity.User;


public class ProfileFragment extends Fragment implements AsyncResponse {
    public static final String ARG_FRAGMENT_TYPE = "fragment_type";

    public ProfileFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

	    //update the actionbar to show the up caret
	    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    	getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
    	//set options menu w/back caret
	    setHasOptionsMenu(true);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

	    // get selected item
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		getActivity().onBackPressed();
	    		break;
    		default:
    			break;
	    }
	    return true;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
 	final User u = (User) getArguments().getSerializable("ContentCreator");
    	
		View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
		ImageView imageView = (ImageView) profileView.findViewById(R.id.profileImageView);
		TextView userName = (TextView)profileView.findViewById(R.id.userName);
		TextView userFullName = (TextView)profileView.findViewById(R.id.userFullName);
		TextView aboutMeField = (TextView)profileView.findViewById(R.id.aboutMeField);
		
		imageView.setImageBitmap(u.getBitmap());
		userName.setText(u.getName());
		userFullName.setText(u.getFullname());
		aboutMeField.setText(u.getDesc());
        
        Button blockButton = (Button) profileView.findViewById(R.id.Block);
    	blockButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity())
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle("Block " + u.getName())
		        .setMessage("Are you sure you want to block " + u.getName() + "?")
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	JSONQuery jquery = new JSONQuery(ProfileFragment.this);
                    jquery.execute(ServerUtil.URL_BLOCK_CONTENT, "Block", Integer.toString(MainActivity.myUserID), Integer.toString(u.getId()), "User");
		        }

		    })
		    .setNegativeButton("No", null)
		    .show();
			}
			
		});
    	
    	Button reportButton = (Button) profileView.findViewById(R.id.Report);
    	reportButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity())
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle("Report " + u.getName())
		        .setMessage("Are you sure you want to report " + u.getName() + "?")
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	// TODO: Report functionality later
		        }

		    })
		    	.setNegativeButton("No", null)
		    	.show();
			}	
		});
    	
        
    	return profileView;
    }
    
	@Override
	public void processFinish(JSONObject result) {
		// TODO Auto-generated method stub
		
	}
}
