package com.ketren.admin;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.net.*;
import android.content.Intent;

public class kinoyollax extends Activity 
{
	String s1[]={"كىنو","كارتون","تېلىۋېزىيە قانىلى","MTV","دەرىس","ناخشا","قىسقا فىلىم"};
	String s11[]={"kino","karton","tv","mtv","daris","nahxa","kiska"};
	String s2[]={"ئۆزتىل","ئۇيغۇرتىلى"};
	String s22[]={"oztil","uyghur"};
	String s3[]={"بىر قىسىملىق","كۆپ قىسىملىق"};
	String s33[]={"0","1"};
	String s4[]={"ئامرىكا","ياپونىيە","كورىيە","جوڭگۇ","ياۋرۇپا","ئۆزبەك","ھىندىستان","شىنجاڭ"};
	String s44[]={"1","2","3","4","5","6","7","8"};
	String s5[]={"ھەقسىز","VIP"};
	String s55[]={"0","1"};
	String s6[]={"2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999"};
	private Spinner kinoturi,tur1,tur2,vip,time,tili;
	ArrayAdapter a1,a2,a3,a4,a5,a6;
//BilQut Tori 
//بىلقۇت تورى پىروگىرامما دۇنياسى
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.kinoyollax);
		kinoturi=findViewById(R.id.kinoturi);
		tur1=findViewById(R.id.tur1);
		tur2=findViewById(R.id.tur2);
		vip=findViewById(R.id.vip);
		time=findViewById(R.id.time);
		tili=findViewById(R.id.til);
		a1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s1);
		kinoturi.setAdapter(a1);
		a2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s2);
		tili.setAdapter(a2);
		a3=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s3);
		tur1.setAdapter(a3);
		a4=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s4);
		tur2.setAdapter(a4);
		a5=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s5);
		vip.setAdapter(a5);
		a6=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s6);
		time.setAdapter(a6);
	}  
	public void Yollax(String KinoNami,String Kinoadirisi,String KinoRasimi,String KinoTuri,String KinoTili,String KinoQuxandurilixi,String t1,String t2,String v,String t){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=yezix", "POST=ENTER&&KinoNami="+KinoNami+"&&KinoAdirisi="+Kinoadirisi+"&&KinoRasimi="+KinoRasimi+"&&KinoTuri="+KinoTuri+"&&KinoTili="+KinoTili+"&&KinoKoyulixi=0&&KinoQuxandurilixi="+KinoQuxandurilixi+"&&tur1="+t1+"&&tur2="+t2+"&&vip="+v+"&&time="+t+"&admin_parol="+login.get_login(this), new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s.equals("ok")){
							Toast.makeText(kinoyollax.this,"يوللاندى!",0).show();
							Intent i=new Intent();
							i.setClass(kinoyollax.this,kinoyollax.class);
							startActivity(i);
							finish();
						}else{
							Toast.makeText(kinoyollax.this,s,0).show();
						}
					}catch(Exception e){
						new AlertDialog.Builder(kinoyollax.this).setMessage(e.getMessage()).show();

					}}

			}); 

	}

	public void yollax(View v){
		EditText kinoNami=(EditText)findViewById(R.id.nami);
		EditText KinoAdirisi=(EditText)findViewById(R.id.adiris);
		EditText KinoRasimi=(EditText)findViewById(R.id.rasim);
		EditText KinoQuxandurulixi=(EditText)findViewById(R.id.jieshao);
		if( kinoNami.getText().toString().length()>0&&KinoAdirisi.getText().toString().length()>0){
			Toast.makeText(kinoyollax.this,"سەل ساقلاڭ…",0).show();
			Yollax(URLEncoder.encode(kinoNami.getText().toString()),URLEncoder.encode(KinoAdirisi.getText().toString()),URLEncoder.encode(KinoRasimi.getText().toString()),s11[kinoturi.getSelectedItemPosition()],s22[tili.getSelectedItemPosition()],URLEncoder.encode(KinoQuxandurulixi.getText().toString()),s33[tur1.getSelectedItemPosition()],s44[tur2.getSelectedItemPosition()],s55[vip.getSelectedItemPosition()],s6[time.getSelectedItemPosition()]);
		}else{
			Toast.makeText(kinoyollax.this,"مەزمۇن يېزىڭ!",0).show();
		}

	}}

