package com.eit.ecokarma;

import com.google.android.gms.maps.model.LatLng;

public class PointDefi {
	LatLng	positiongeographique;
	String desc;
	int rec, ty, id;
	String nom;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTy() {
		return ty;
	}

	public void setTy(int ty) {
		this.ty = ty;
	}

	PointDefi(double x, double y, String Description, int Recompense, String Nom, int type, int i)
	{
		positiongeographique = new LatLng(x, y);
		desc = Description;
		rec = Recompense;
		nom = Nom;
		ty = type;
		id = i;
	}

	public LatLng getPositiongeographique() {
		return positiongeographique;
	}

	public void setPositiongeographique(LatLng positiongeographique) {
		this.positiongeographique = positiongeographique;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRec() {
		return rec;
	}

	public void setRec(int rec) {
		this.rec = rec;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
