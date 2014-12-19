package com.example.hikeathon;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

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

	//private static final String TAG = "MainFragment";
	private TextView userInfoTextView;

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
								userInfoTextView
										.setText(buildUserInfoDisplay(user));
							}
						}
					});
		}
	}

	private String buildUserInfoDisplay(GraphUser user) {
		StringBuilder userInfo = new StringBuilder("");

		// Example: typed access (name)
		// - no special permissions required
		userInfo.append(String.format("Name: %s\n\n", user.getName()));

		// Example: typed access (birthday)
		// - requires user_birthday permission
		userInfo.append(String.format("Birthday: %s\n\n", user.getBirthday()));

		// Example: partially typed access, to location field,
		// name key (location)
		// - requires user_location permission
		userInfo.append(String.format("Location: %s\n\n", user.getLocation()
				.getProperty("name")));

		// Example: access via property name (locale)
		// - no special permissions required
		userInfo.append(String.format("Locale: %s\n\n",
				user.getProperty("locale")));

		// Example: access via key for array (languages)
		// - requires user_likes permission
		JSONArray languages = (JSONArray) user.getProperty("languages");
		if (languages.length() > 0) {
			ArrayList<String> languageNames = new ArrayList<String>();
			for (int i = 0; i < languages.length(); i++) {
				JSONObject language = languages.optJSONObject(i);
				// Add the language name to a list. Use JSON
				// methods to get access to the name field.
				languageNames.add(language.optString("name"));
			}
			userInfo.append(String.format("Languages: %s\n\n",
					languageNames.toString()));
		}

		return userInfo.toString();
	}

}
