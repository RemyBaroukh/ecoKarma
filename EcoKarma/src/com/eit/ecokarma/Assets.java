package com.eit.ecokarma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class Assets {

	public static ArrayList<String> saviezvous;
	public static Typeface font;
	public static ArrayList<PointDefi> listedefi;

	public static void load(Context context)
	{
		saviezvous = new ArrayList<String>();
		listedefi = new ArrayList<PointDefi>();
		FileParser(context); 
		font = Typeface.createFromAsset(context.getAssets(), "HelveticaNeue.ttf");
		Listedefi(context);
	}

	private static void Listedefi(Context context) {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://192.168.1.178/ecokarma/get/scan/43.608088/3.890388" , new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					Log.d(null, response);
					if (!response.isEmpty())
					{
						JSONObject jobj = new JSONObject(response);
						JSONArray jsonMainNode = jobj.optJSONArray("Points proches");

						for (int i = 0; i < jsonMainNode.length(); i++)
						{
							JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
							String nom = jsonChildNode.optString("nom").toString();
							String desc = jsonChildNode.optString("desc").toString();
							Double latitude = jsonChildNode.optDouble("latitude");
							Double longitude = jsonChildNode.optDouble("longitude");
							int recompense = jsonChildNode.optInt("recompense");
							int type = jsonChildNode.optInt("type");
							int id = jsonChildNode.optInt("id");
							listedefi.add(new PointDefi(latitude, longitude, desc, recompense, nom, type, id));
						}

					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//					JSONObject jObject = new JSONObject(response);
				//					points = jObject.getInt("points");
				//					bio = jObject.getString("bio");
				//					Log.d(null, "POINTS == " + String.valueOf(points) + " BIO === " + bio);
			}});



	}

	private static void FileParser(Context cont) {
		BufferedReader flux;
		try {
			flux = new BufferedReader(new InputStreamReader(cont.getAssets().open("saviezvous.txt"), "UTF-8"));
			String ligne;
			while((ligne=flux.readLine()) !=null)
			{
				if (ligne != "/n" || ligne != "/r/n" || ligne != "")
					saviezvous.add(ligne);
				Log.d(null, String.valueOf(saviezvous.size()));

			}

			for(int i = 0; i < saviezvous.size(); i++)
			{
				Log.d(null, saviezvous.get(i));
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public static String getrandomQuote()
	{
		Random r = new Random();
		int a = r.nextInt(31);
		Log.d(null, String.valueOf(a));
		return saviezvous.get(a);
	}
}
