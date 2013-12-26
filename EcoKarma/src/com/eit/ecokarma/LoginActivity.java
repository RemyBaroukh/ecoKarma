package com.eit.ecokarma;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	String userName, passWord;
	EditText username, password;
	Button login, register;    
	SessionManager session;
	private String urlb = "http://192.168.1.178/ecokarma/";
	 protected ProgressDialog myProgressDialog; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(loginListener);
		Drawable alpha = login.getBackground();
		alpha.setAlpha(100);
		login.setBackground(alpha);
		register = (Button)findViewById(R.id.register);
		register.setOnClickListener(registerListener);
		alpha = register.getBackground();
		alpha.setAlpha(100);
		register.setBackground(alpha);
		session = new SessionManager(getApplicationContext());
	}

	private OnClickListener registerListener = new OnClickListener()
	{
		public void onClick(View V)
		{
			Toast.makeText(getApplicationContext(), "Register demande!", Toast.LENGTH_LONG).show();
		}
	};

	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {

			try {
				test_fonction("login/" + username.getText().toString() + "/" + password.getText().toString());
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	};

	public void test_fonction(String url)
	{
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(urlb + url , new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Assets.load(getApplicationContext()); 
				session.createLoginSession(username.getText().toString());
				Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
};
