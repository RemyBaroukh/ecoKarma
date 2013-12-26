package com.eit.ecokarma;

import java.util.ArrayList;
import java.util.List;

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

public class EnterDefi extends Activity{

	SessionManager session;
	ListView lw;
	List<Defis_historique> maBibliotheque = new ArrayList<Defis_historique>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterdefi_activity);
		session = new SessionManager(getApplicationContext());
		lw = (ListView) findViewById(R.id.lvListe);

		//		setContentView(R.layout.defispage);
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://192.168.1.178/ecokarma/get/histo/" + session.getUserDetails().get(SessionManager.KEY_NAME) , new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					Log.d(null, response);
					if (!response.isEmpty())
					{
						JSONObject jobj = new JSONObject(response);
						JSONArray jsonMainNode = jobj.optJSONArray("Historique");
						maBibliotheque.clear();
						for (int i = 0; i < jsonMainNode.length(); i++)
						{
							JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
							String nom = jsonChildNode.optString("nom").toString();
							int type = jsonChildNode.optInt("type");
							int id = jsonChildNode.optInt("id");
							String date = jsonChildNode.optString("date");
					    	maBibliotheque.add(new Defis_historique(type, nom, date));
						}

					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		HistoAdapter adapter = new HistoAdapter(this, maBibliotheque);
		lw.setAdapter(adapter);
		adapter.notifyDataSetChanged();	

	}
	
	@Override
	  public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			if (inBounds(event, 36, 172, 682, 186))
			{
	              Intent i = new Intent(getApplicationContext(), Defis.class);
	              startActivity(i);	
			}
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