package com.DY.douyin;

public class Bean {
    public String url;
	public String nam;
	public String rasim;

    public Bean(String url,String r,String nami) {
        this.url = url;
		this.nam=nami;
		this.rasim=r;
    }
public String geturl(){
return url;
}
	public String getNami(){
		return nam;
	}
	public String getRasim(){
		return rasim;
	}
}
