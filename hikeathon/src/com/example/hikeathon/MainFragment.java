package com.example.hikeathon;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {

	// private static final String TAG = "MainFragment";
	private TextView userInfoTextView;
	TextView tvage, tvgender;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main, container, false);
		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("user_likes",
				"user_status", "public_profile", "user_location",
				"user_birthday"));

		userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
		tvage = (TextView) view.findViewById(R.id.tvage);
		tvgender = (TextView) view.findViewById(R.id.tvgender);
		return view;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			userInfoTextView.setVisibility(View.VISIBLE);
		} else if (state.isClosed()) {
			userInfoTextView.setVisibility(View.INVISIBLE);
		}
		if (state.isOpened()) {
			userInfoTextView.setVisibility(View.VISIBLE);

			// Request user data and show the results
			Request.executeMeRequestAsync(session,
					new Request.GraphUserCallback() {

						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							if (user != null) {
								// Display the parsed user info
								// userInfoTextView.setText(buildUserInfoDisplay(user));
								buildUserInfoDisplay(user);
							}
						}
					});
		}
	}

	private void buildUserInfoDisplay(GraphUser user) {

		/*
		 * StringBuilder userInfo = new StringBuilder("");
		 * userInfo.append(String.format("Name: %s\n\n", user.getName()));
		 */

		// userInfo.append(String.format("Birthday: %s\n\n",
		// user.getBirthday()));
		
		Date  birth = null;
		try {
		   DateFormat formatter ; 
		   formatter = new SimpleDateFormat("dd-MMM-yyyy");
		   birth = (Date)formatter.parse(user.getBirthday());   // birtDate is a string 
		} catch (ParseException e) {
		    System.out.println("Exception :"+e);
		}  
		
		tvage.setText(user.getBirthday());
		long ageInMillis = new Date().getTime() - birth.getTime();
		Date age = new Date(ageInMillis);
		
		tvage.setText(age.getYear());
		tvgender.setText(user.asMap().get("gender").toString()); // user.asMap().get("gender").toString();

		
		/*
		 * userInfo.append(String.format("Location: %s\n\n", user.getLocation()
		 * .getProperty("name")));
		 * 
		 * userInfo.append(String.format("Locale: %s\n\n",
		 * user.getProperty("locale")));
		 * 
		 * JSONArray languages = (JSONArray) user.getProperty("languages"); if
		 * (languages.length() > 0) { ArrayList<String> languageNames = new
		 * ArrayList<String>(); for (int i = 0; i < languages.length(); i++) {
		 * JSONObject language = languages.optJSONObject(i);
		 * languageNames.add(language.optString("name")); }
		 * userInfo.append(String.format("Languages: %s\n\n",
		 * languageNames.toString())); }
		 * 
		 * return userInfo.toString();
		 */
	}
	
	

}
