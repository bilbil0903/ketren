package com.ketren.kino.KaldukAdapterLists;

import android.graphics.*;
public class KaldukList
{
	private String KinoNami;
    private String kinoRasimi;
	private String kinowakti;
	private String KinoKisim;
	private String KinoID;
	
    public KaldukList(String KinoNami,String kinoRasimi,String kinowakti,String KinoID,String kisim) {
		this.KinoNami = KinoNami;
		this.kinoRasimi=kinoRasimi;
		this.KinoID=KinoID;
		this.KinoKisim=kisim;
		this.kinowakti=kinowakti;
    }
	public String getKinoWakti() {
        return kinowakti;
    }
    public void setkinoWakti(String kinowakti) {
        this.kinowakti = kinowakti;
    }
	public String getKinoKisim() {
        return KinoKisim;
    }
    public void setkinoKisim(String kisim) {
        this.KinoKisim = kisim;
    }
	
    public String getKinoNami() {
        return KinoNami;
    }
    public void setKinoNami(String KinoNami) {
        this.KinoNami = KinoNami;
    }
	
    public String getkasma() {
        return kinoRasimi;
    }
    public void setkasma(String kasma) {
        this.kinoRasimi = kasma;
    }
	public String getKinoID() {
        return KinoID;
    }
    public void setKinoID(String ID) {
        this.KinoID = ID;
    }
	}
