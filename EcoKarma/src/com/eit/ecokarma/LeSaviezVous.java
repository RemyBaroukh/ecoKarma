package com.eit.ecokarma;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class LeSaviezVous extends Activity{
	private TextView edittext;

	public void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.lesaviezvous_activity);
		Log.d(null, "Le Saviez vous");
		edittext = (TextView) findViewById(R.id.textView1);
		edittext.setText(Assets.getrandomQuote());
		edittext.setMovementMethod(new ScrollingMovementMethod());
		edittext.setTypeface(Assets.font);
		edittext.setTextSize(18);
		
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev){
	    super.dispatchTouchEvent(ev);    
	    return this.onTouchEvent(ev); 
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		Log.d(null, "nfsdsfdqd");
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			if (inBounds(event, 241, 714, 282, 114))
			{
				edittext.setText(Assets.getrandomQuote());
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
