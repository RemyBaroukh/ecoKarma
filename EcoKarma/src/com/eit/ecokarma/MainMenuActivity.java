package com.eit.ecokarma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainMenuActivity extends Activity{
	  public void onCreate(Bundle savedInstanceState) {
		  
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.mainmenuactivity);
	  }
	  
	@Override
	  public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			if (inBounds(event, 34, 66, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), Amis.class);
	              startActivity(i);	
			}
			if (inBounds(event, 294, 66, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), Profil.class);
	              startActivity(i);
			}
			if (inBounds(event, 552, 66, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), EnterDefi.class);
	              startActivity(i);
			}
			if (inBounds(event, 34, 320, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), LeSaviezVous.class);
	              startActivity(i);
			}
			if (inBounds(event, 294, 320, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), Contact.class);
	              startActivity(i);
			}
			if (inBounds(event, 552, 320, 182, 186))
			{
	              Intent i = new Intent(getApplicationContext(), APropos.class);
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
