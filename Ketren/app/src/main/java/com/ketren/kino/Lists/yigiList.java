package com.ketren.kino.Lists;

import android.graphics.*;
public class yigiList
{
	private String KinoNami;
	private String KinoTili;
    private String kinoRasimi;
	private String KinoKoyulixi;
	private String KinoID;
	private String vip;
	private String KinoQuxandurilixi;
    public yigiList() {
    }
    public yigiList(String KinoNami,String kinoRasimi,String KinoTili,String KinoKoyulixi,String KinoID,String KinoQuxandurulixi,String vip) {
        this.vip=vip;
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
    public void setKinoID(String ID) {
        this.KinoID = ID;
    }
	public String getKinovip() {
        return vip;
    }
    public void setKinovip(String vip) {
        this.vip = vip;
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
}
