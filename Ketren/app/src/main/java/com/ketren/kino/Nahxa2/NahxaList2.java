package com.ketren.kino.Nahxa2;

import android.graphics.*;
public class NahxaList2
{
	private String id;
	private String nahxa;
    private String adiris;
	private String nahxiqi;
	private String see;

    public NahxaList2(String id,String nahxa,String adiris,String nahxiqi,String see) {
        this.id = id;
		this.nahxa = nahxa;
		this.adiris=adiris;
		this.nahxiqi=nahxiqi;
		this.see=see;
    }
    public String getID() {
        return id;
    }
    public void setID(String ID) {
        this.id = ID;
    }
	public String getNahxa() {
        return nahxa;
    }

    public void setNahxa(String nahxa) {
        this.nahxa = nahxa;
    }


    public String getAdiris() {
        return adiris;
    }
    public void setAdiris(String adiris) {
        this.adiris = adiris;
    }
	public String getNahxiqi() {
        return nahxiqi;
    }
    public void setNahxiqi(String nahxiqi) {
        this.nahxiqi = nahxiqi;
    }
	public String getSee() {
        return see;
    }
    public void setSee(String see) {
        this.see = see;
    }
}

