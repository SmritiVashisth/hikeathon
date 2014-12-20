package com.example.hikeathon;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {

	// private static final String TAG = "MainFragment";
	private TextView userInfoTextView;
	TextView tvage, tvgender, tvsports, tvbooks, tvmovies;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main, container, false);
		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("user_likes",
				"user_status", "public_profile", "user_location",
				"user_birthday", "user_interests", "user_actions.books"));

		userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
		tvage = (TextView) view.findViewById(R.id.tvage);
		tvgender = (TextView) view.findViewById(R.id.tvgender);
		tvsports = (TextView) view.findViewById(R.id.tvsports);
		tvbooks = (TextView) view.findViewById(R.id.tvbooks);
		tvmovies = (TextView) view.findViewById(R.id.tvmovies);
		return view;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {

		if (state.isOpened()) {

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

			new Request(session, "/me/books", null, HttpMethod.GET,
					new Request.Callback() {
						public void onCompleted(Response response) {
							/* handle the result */
							GraphObject graphObject = response.getGraphObject();
							String s = null;
							if (graphObject != null) {
								if (graphObject.getProperty("id") != null) {
									s = s
											+ String.format("%s\n", graphObject
													.getProperty("name"));
								}
							}
							tvbooks.setText(s);

						}
					}).executeAsync();

			new Request(session, "/me/movies", null, HttpMethod.GET,
					new Request.Callback() {
						public void onCompleted(Response response) {
							/* handle the result */
							GraphObject graphObject = response.getGraphObject();
							String s = null;
							if (graphObject != null) {
								if (graphObject.getProperty("id") != null) {
									s = s
											+ String.format("%s\n", graphObject
													.getProperty("name"));
								}
							}
							tvmovies.setText(s);
						}
					}).executeAsync();
		}
	}

	private void buildUserInfoDisplay(GraphUser user) {

		Date birth = null;
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd-MMM-yyyy");
			birth = (Date) formatter.parse(user.getBirthday()); // birtDate is a
																// string
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}

		tvage.setText(user.getBirthday());
		long ageInMillis = new Date().getTime() - birth.getTime();
		Date age = new Date(ageInMillis);

		tvage.setText(age.getYear());
		
		tvgender.setText(user.asMap().get("gender").toString());

		StringBuilder userInfo = null;

		JSONArray sports = (JSONArray) user.getProperty("sports");
		if (sports.length() > 0) {
			ArrayList<String> sportsList = new ArrayList<String>();
			for (int i = 0; i < sports.length(); i++) {
				JSONObject language = sports.optJSONObject(i);
				sportsList.add(language.optString("name"));
			}
			userInfo.append(sportsList.toString());
		}

		tvsports.setText(userInfo.toString());
	}

}
