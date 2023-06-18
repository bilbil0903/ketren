package com.ketren.admin;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	public void yollax(View v){
		Intent i=new Intent();
		i.setClass(MainActivity.this,kinoyollax.class);
	    startActivity(i);
	}
	public void yollax2(View v){
		Intent i=new Intent();
		i.setClass(MainActivity.this,nahxayollax.class);
	    startActivity(i);
	}
	public void baxkurux(View v){
		Intent i=new Intent();
		i.setClass(MainActivity.this,kinobaxkuruxIzdax.class);
	    startActivity(i);
	}
	public void ilan(View v){
		Intent i=new Intent();
		i.setClass(MainActivity.this,ilanbaxkurux.class);
	    startActivity(i);
	}
	public void buy(View v){
		Intent i=new Intent();
		i.setClass(MainActivity.this,buy.class);
	    startActivity(i);
	}
}
