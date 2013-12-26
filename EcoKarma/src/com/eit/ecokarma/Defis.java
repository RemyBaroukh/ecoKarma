package com.eit.ecokarma;

import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MyLocationOverlay;



public class Defis extends FragmentActivity implements LocationListener, OnInfoWindowClickListener{

	private MyLocationOverlay myLocation = null;
	GoogleMap googleMap;
	LatLng myPosition;
	SessionManager session;

	public void addPoint(double x, double y, char type, String s){
		LatLng newPoint = new LatLng(x, y);
		googleMap.addMarker(new MarkerOptions().position(newPoint).title(s)
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	}

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.defis_activity);
		Log.d(null, "Defis");
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

		googleMap = fm.getMap();
		googleMap.setMyLocationEnabled(true);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);

		if(location!=null){
			// Getting latitude of the current location
			double latitude = location.getLatitude();

			// Getting longitude of the current location
			double longitude = location.getLongitude();

			// Creating a LatLng object for the current location
			LatLng latLng = new LatLng(latitude, longitude);

			myPosition = new LatLng(latitude, longitude);

			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));
			googleMap.addMarker(new MarkerOptions().position(new LatLng(43.608088,3.890388)).title("Start"));
		}
		LatLng autrepoint = new LatLng(43.608088,3.890388);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(autrepoint, 15.0f));
		//googleMap.addMarker(new MarkerOptions().position(autrepoint).title("Ttototo"));
		for (int i = 0; i < Assets.listedefi.size(); i++)
		{
			PointDefi tmp = Assets.listedefi.get(i);
			switch (tmp.getTy())
			{
			case 1 : {
				MarkerOptions markercurrent = new MarkerOptions().position(tmp.getPositiongeographique())
						.title("Accepter le défi n° " + String.valueOf(tmp.getId()))
						.snippet(tmp.getNom())
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.recycle));
				googleMap.addMarker(markercurrent);
				break;
			}
			case 2 :
			{
				MarkerOptions markercurrent = new MarkerOptions().position(tmp.getPositiongeographique())
						.title("Accepter le défi n° " + String.valueOf(tmp.getId()))
						.snippet(tmp.getNom())
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.tam ));
				googleMap.addMarker(markercurrent);
				break;
			}
			case 3 :
			{
				MarkerOptions markercurrent = new MarkerOptions().position(tmp.getPositiongeographique())
						.title("Accepter le défi n° " + String.valueOf(tmp.getId()))
						.snippet(tmp.getNom())
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.viewpoint));
				googleMap.addMarker(markercurrent);
				break;
			}
			case 4 :
			{	
				MarkerOptions markercurrent = new MarkerOptions().position(tmp.getPositiongeographique())
						.title("Accepter le défi n° " + String.valueOf(tmp.getId()))
						.snippet(tmp.getNom())
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike));
				googleMap.addMarker(markercurrent);
				break;

			}
			default : break;}
		}
		googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				
				session = new SessionManager(getApplicationContext());
				Scanner in = new Scanner(marker.getTitle()).useDelimiter("[^0-9]+");
				int integer = in.nextInt();
				session.setId(integer);
	              Intent i = new Intent(getApplicationContext(), DefisPage.class);
	              startActivity(i);	
			}
		});

	}

	@Override
	public void onLocationChanged(Location arg0) {


		if(arg0!=null){
			// Getting latitude of the current location
			double latitude = arg0.getLatitude();

			// Getting longitude of the current location
			double longitude = arg0.getLongitude();

			// Creating a LatLng object for the current location
			LatLng latLng = new LatLng(latitude, longitude);

			myPosition = new LatLng(latitude, longitude);

			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));
			//googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
			addPoint(43.608088, 3.890588, (char)0, "tata");
		}

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
