package com.example.hikeathon;

import android.graphics.Bitmap;

public class Friend {
	public String id;
	public String name;
	public byte[] picture;
	public Bitmap pictureBitmap;;
}
/*
 * public static final String APP_ID = "802583413135456";
 * 
 * private static final List<String> PERMISSIONS = new ArrayList<String>() { {
 * add("user_friends"); add("public_profile"); } };
 * 
 * private Facebook mFacebook; private AsyncFacebookRunner mAsyncRunner; private
 * UiLifecycleHelper lifecycleHelper; boolean pickFriendsWhenSessionOpened;
 * TextView age, gender;
 * 
 * 
 * @Override public void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); setContentView(R.layout.main);
 * 
 * age = (TextView) findViewById(R.id.tvage); gender = (TextView)
 * findViewById(R.id.tvgender);
 * 
 * mFacebook = new Facebook(APP_ID); mAsyncRunner = new
 * AsyncFacebookRunner(mFacebook);
 * 
 * lifecycleHelper = new UiLifecycleHelper(this, new Session.StatusCallback() {
 * 
 * @Override public void call(Session session, SessionState state, Exception
 * exception) { onSessionStateChanged(session, state, exception); } });
 * lifecycleHelper.onCreate(savedInstanceState);
 * 
 * ensureOpenSession(); }
 * 
 * private boolean ensureOpenSession() { if (Session.getActiveSession() == null
 * || !Session.getActiveSession().isOpened()) { Session.openActiveSession(this,
 * true, PERMISSIONS, new Session.StatusCallback() {
 * 
 * @Override public void call(Session session, SessionState state, Exception
 * exception) { onSessionStateChanged(session, state, exception); } }); return
 * false; } return true; }
 * 
 * public void onActivityResult(int requestCode, int resultCode, Intent data) {
 * super.onActivityResult(requestCode, resultCode, data); Log.d("FB Sample App",
 * "onActivityResult(): " + requestCode);
 * mFacebook.authorizeCallback(requestCode, resultCode, data); }
 * 
 * private boolean sessionHasNecessaryPerms(Session session) { if (session !=
 * null && session.getPermissions() != null) { for (String requestedPerm :
 * PERMISSIONS) { if (!session.getPermissions().contains(requestedPerm)) {
 * return false; } } return true; } return false; }
 * 
 * private List<String> getMissingPermissions(Session session) { List<String>
 * missingPerms = new ArrayList<String>(PERMISSIONS); if (session != null &&
 * session.getPermissions() != null) { for (String requestedPerm : PERMISSIONS)
 * { if (session.getPermissions().contains(requestedPerm)) {
 * missingPerms.remove(requestedPerm); } } } return missingPerms; }
 * 
 * private void onSessionStateChanged(final Session session, SessionState state,
 * Exception exception) { if (state.isOpened() &&
 * !sessionHasNecessaryPerms(session)) { AlertDialog.Builder builder = new
 * AlertDialog.Builder(this); builder.setMessage("Permissions required");
 * builder.setPositiveButton("need permissions", new
 * DialogInterface.OnClickListener() {
 * 
 * @Override public void onClick(DialogInterface dialog, int which) {
 * session.requestNewReadPermissions(new NewPermissionsRequest( Main.this,
 * getMissingPermissions(session))); } });
 * builder.setNegativeButton("need permissions", new
 * DialogInterface.OnClickListener() {
 * 
 * @Override public void onClick(DialogInterface dialog, int which) { finish();
 * } }); builder.show(); } else if (pickFriendsWhenSessionOpened &&
 * state.isOpened()) { pickFriendsWhenSessionOpened = false;
 * 
 * // startPickFriendsActivity(); } }
 */