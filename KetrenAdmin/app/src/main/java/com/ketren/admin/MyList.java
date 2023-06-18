package com.ketren.admin;
import android.graphics.*;
public class MyList
{
	private String KinoNami;
	private String KinoAdirisi;
	private String KinoTuri;
	private String KinoQuxandurilixi;
	private String KinoTili;
	private String vip;
    private String kinoRasimi;
	private int id;
	private String nadir;
    public MyList(String KinoNami,String kinoRasimi,String KinoAdirisi,int id,String KinoTili,String kinoTuri,String KinoQuxandurilixi,String vip,String nadir) {
		this.KinoNami = KinoNami;
		this.KinoAdirisi=KinoAdirisi;
		this.kinoRasimi=kinoRasimi;
		this.id=id;
		this.KinoTuri=kinoTuri;
		this.KinoTili=KinoTili;
		this.KinoQuxandurilixi=KinoQuxandurilixi;
		this.vip=vip;
		this.nadir=nadir;
    }
    public String getKinoNami() {
        return KinoNami;
    }
    public void setKinoNami(String KinoNami) {
        this.KinoNami = KinoNami;
    }
	public String getNadir() {
        return nadir;
    }
    public void setNadir(String nadir) {
        this.nadir = nadir;
    }
	public String getAdiris() {
        return KinoAdirisi;
    }

    public void setAdiris(String Adiris) {
        this.KinoAdirisi = Adiris;
    }


    public String getkasma() {
        return kinoRasimi;
    }
    public void setkasma(String kasma) {
        this.kinoRasimi = kasma;
    }
	public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
	
	public String getTili() {
        return KinoTili;
    }
    public void setTili(String til) {
        this.KinoTili = til;
    }
	public String getTuri() {
        return KinoTuri;
    }
    public void setKinoTuri(String turi) {
        this.KinoTuri =turi;
    }
	public String getkinoQuxandurulixi() {
        return KinoQuxandurilixi;
    }
    public void setkinoQuxandurulixi(String q) {
        this.KinoQuxandurilixi = q;
    }
	public String getVip() {
        return vip;
    }
    public void setVip(String v) {
        this.vip = v;
    }
	}

