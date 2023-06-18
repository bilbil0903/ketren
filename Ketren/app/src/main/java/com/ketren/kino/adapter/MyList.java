package com.ketren.kino.adapter;

import android.graphics.*;
public class MyList
{
	private String KinoNami;
	private String KinoTili;
    private String kinoRasimi;
	private String KinoKoyulixi;
	private String KinoID;
	private String KinoQuxandurilixi;
   private String vip;
    
    public MyList(String KinoNami,String kinoRasimi,String KinoTili,String KinoKoyulixi,String KinoID,String KinoQuxandurulixi,String vip) {
        this.vip = vip;
		this.KinoNami = KinoNami;
		this.KinoTili=KinoTili;
		this.kinoRasimi=kinoRasimi;
		this.KinoKoyulixi=KinoKoyulixi;
		this.KinoID=KinoID;
		this.KinoQuxandurilixi=KinoQuxandurulixi;
    }
    public String getKinoNami() {
        return KinoNami;
    }
    public void setKinoNami(String KinoNami) {
        this.KinoNami = KinoNami;
    }
	public String getTil() {
        return KinoTili;
    }

    public void setTil(String til) {
        this.KinoTili = til;
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
    public void setKinoID(String KinoID) {
        this.KinoID = KinoID;
    }
	public String getKinoKoyulixi() {
        return KinoKoyulixi;
    }
    public void setKinoTuri(String KinoKoyulixi) {
        this.KinoKoyulixi = KinoKoyulixi;
    }
	public String getKinoQuxandurulixi() {
        return KinoQuxandurilixi;
    }
    public void srtKinoQuxandurulixi(String kinoQuxandurulixi) {
        this.KinoQuxandurilixi =kinoQuxandurulixi;
    }
	public void setKinovip(String Vip) {
        this.vip = Vip;
    }
	public String getKinovip() {
        return vip;
    }
}
