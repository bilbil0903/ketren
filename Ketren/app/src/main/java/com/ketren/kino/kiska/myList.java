package com.ketren.kino.kiska;

public class myList
{
	private String KinoAdirisi;
	private String KinoRasimi;
	private String KinoNami;


    public myList(String adiris,String rasim,String nami) {
        this.KinoAdirisi=adiris;
		this.KinoRasimi=rasim;
		KinoNami=nami;
    }
    public String getAdiris() {
        return KinoAdirisi;
    }

	public String getRasim() {
        return KinoRasimi;
    }

	public String getNami() {
        return KinoNami;
    }


}
