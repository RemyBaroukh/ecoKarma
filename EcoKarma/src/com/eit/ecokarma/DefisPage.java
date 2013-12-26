package com.eit.ecokarma;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.TextView;

public class DefisPage extends Activity
{
	SessionManager session;
	TextView t1;
	TextView t2;
	TextView t3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	      setContentView(R.layout.defispage_activity);
	      t1 = (TextView) findViewById(R.id.textNom);
	      t2 = (TextView) findViewById(R.id.textRec);
	      t3 = (TextView) findViewById(R.id.textDesc);
 	      	
	      
	      session = new SessionManager(getApplicationContext());
	      Log.d(null, String.valueOf(session.ID_ACTION));
			AsyncHttpClient client = new AsyncHttpClient();
			client.get("http://192.168.1.178/ecokarma/get/action/" + String.valueOf(session.ID_ACTION), new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					Log.d(null, response);
						JSONObject jObject;
						try {
							jObject = new JSONObject(response);
							int type =  jObject.getInt("type");
							String description = jObject.getString("desc");
							int recompense = jObject.getInt("recompense");
							String nom = jObject.getString("nom");
							Log.d(null, "NOM == " + nom + " recompense = " + String.valueOf(recompense) + " desc = " + description);
							t1.setTextSize(20);
							t1.setText(nom);
							t2.setTextSize(13);
							t2.setText("Cette action apporte " + String.valueOf(recompense) + " points de karma");
							t3.setTextSize(16);
							t3.setText(description);
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}});
	}
	@Override
	  public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			if (inBounds(event, 234,714, 296,122))
			{
				AsyncHttpClient client = new AsyncHttpClient();
				client.post( "http://192.168.1.178/ecokarma/push/histo/" + session.getUserDetails().get(SessionManager.KEY_NAME)+
						"/"+ String.valueOf(session.ID_ACTION), new AsyncHttpResponseHandler()
				{
					@Override
					public void onSuccess(String response) {
				        Intent i = new Intent(getApplicationContext(), Profil.class);
				        startActivity(i);	

			}});}
			}
		return true;
	}
	  private boolean inBounds(MotionEvent event, int x, int y, int width,
	            int height) {
	        if (event.getX()> x && event.getX() < x + width - 1 && event.getY() > y
	                && event.getY() < y + height - 1)
	            return true;
	        else
	            return false;
	    }
}