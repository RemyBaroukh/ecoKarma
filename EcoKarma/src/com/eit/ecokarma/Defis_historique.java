package com.eit.ecokarma;

import android.util.Log;

public class Defis_historique {

	private String TypeEtNomDefi;
	private String Date;

	public Defis_historique(int type, String nom, String dat) {

		switch (type)
		{
		case 1 : 
		{
			this.TypeEtNomDefi = "Ecolo -- " + " " + nom;
			break;
		}
		case 2 : 
		{
			this.TypeEtNomDefi = "Transport -- " + " " + nom;
			break;
		}
		case 3 : 
		{
			this.TypeEtNomDefi = "Culture -- " + " " + nom;
			break;
		}
		case 4 : 
		{
			this.TypeEtNomDefi = "Velomagg -- " + " " + nom;
			break;
		}
		default :
			{
				Log.d(null, "ffdfd");
				break;
			}
		}
		this.Date = "    " + dat;
	}

	public	String getFirstTab()
	{
		return TypeEtNomDefi;
	}

	public String getDate()
	{
		return Date;
	}

	public void setFirstTab(String ne)
	{
		this.TypeEtNomDefi = ne;
	}

	public void setDate(String ne)
	{
		this.Date = ne;
	}
}