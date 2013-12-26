package com.eit.ecokarma;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends Activity{
	
	SessionManager session;
	int points;
	String bio;
	String pseudo;
	private String urlb = "http://192.168.1.178/ecokarma/";
	TextView pseu;
	TextView level;
	TextView bi;
	ProgressBar pb;
	
	  public void onCreate(Bundle savedInstanceState) {
		  
	      super.onCreate(savedInstanceState);
	      session = new SessionManager(getApplicationContext());
	      setContentView(R.layout.profil_activity);
	      Log.d(null, "Profil");
	      session.checkLogin();
	      test_fonction("/get/account/" + session.getUserDetails().get(SessionManager.KEY_NAME));
	      pseudo =  session.getUserDetails().get(SessionManager.KEY_NAME);
	      pseu = (TextView) findViewById(R.id.textView1);
	      level = (TextView) findViewById(R.id.textView2);
	      bi = (TextView) findViewById(R.id.textView3);
	      pb = (ProgressBar) findViewById(R.id.progressBar1);
	  }
	  
		public void test_fonction(String url)
		{
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(urlb + url , new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					Log.d(null, response);
					try {
						JSONObject jObject = new JSONObject(response);
						points = jObject.getInt("points");
						bio = jObject.getString("bio");
						pseu.setText(pseudo);
						pseu.setTypeface(Assets.font);
						pseu.setTextSize(18);
						level.setText(String.valueOf(points));
						level.setTypeface(Assets.font);
						level.setTextSize(18);
						bi.setText(bio);
						bi.setTypeface(Assets.font);
						bi.setTextSize(18);
						pb.setProgress(40);
						} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
		}
}
