package com.ketren.kino.Inkas;

import android.graphics.*;
public class inkasList
{
	private String userID;
    private String time;
	private String android;
	private String mazmun;

    public inkasList(String ud,String time,String android,String mazmun) {
		this.userID = ud;
		this.time=time;
		this.android=android;
		this.mazmun=mazmun;
    }
	public String getUserID() {
        return userID;
    }
    public void setUserID(String id) {
        this.userID = id;
    }
	public String gettime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }

    public String getandroid() {
        return android;
    }
    public void setAndroid(String android) {
        this.android = android;
    }

    public String getMazmun() {
        return mazmun;
    }
    public void setklMazmun(String mazmun) {
        this.mazmun = mazmun;
    }
}
