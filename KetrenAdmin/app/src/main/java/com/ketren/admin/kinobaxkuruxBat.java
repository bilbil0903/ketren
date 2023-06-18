package com.ketren.admin;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class kinobaxkuruxBat extends Activity 
{
	EditText nami,adirisi,kasma,tili,turi,jieshao,vip,nadir;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinobaxkuruxbat);
		nami=findViewById(R.id.name);
		adirisi=findViewById(R.id.adiris);
		kasma=findViewById(R.id.kasma);
		tili=findViewById(R.id.til);
		turi=findViewById(R.id.tur);
		jieshao=findViewById(R.id.jieshao);
		vip=findViewById(R.id.vip);
		nadir=findViewById(R.id.nadir);
		Intent i=getIntent();
		String kinonami=i.getStringExtra("KinoNami");
		String kinoadirisi=i.getStringExtra("KinoAdirisi");
		String Kinokasma=i.getStringExtra("kasma");
		String til=i.getStringExtra("tili");
		String tur=i.getStringExtra("turi");
		String j=i.getStringExtra("jieshao");
		String v=i.getStringExtra("vip");
		String n=i.getStringExtra("nadir");
		nami.setText(kinonami);
		tili.setText(til);
		turi.setText(tur);
		jieshao.setText(j);
		vip.setText(v);
		kasma.setText(Kinokasma);
		nadir.setText(n);
		adirisi.setText(kinoadirisi);
    }
	public void Save(String KinoNami,String KinoAdirisi,String KinoRasimi,final int id,String KinoTili,String KinoTuri,String j,String vip,String n){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=save_kino", "POST=ENTER&&KinoNami="+KinoNami+"&&KinoAdirisi="+KinoAdirisi+"&&KinoRasimi="+KinoRasimi+"&&id="+id+"&KinoTili="+KinoTili+"&KinoTuri="+KinoTuri+"&KinoQuxandurilixi="+j+"&vip="+vip+"&nadir="+n+"&admin_parol="+login.get_login(this), new Handler() {
				public   void handleMessage(Message msg) {
					if(((String)msg.obj).equals("ok")){
					Toast.makeText(kinobaxkuruxBat.this,"ساقلاندى!",0).show();
					finish();
					
				}else{
				new AlertDialog.Builder(kinobaxkuruxBat.this).setMessage((String)msg.obj).show();
				}}

			}); 
}

public void save(View v){
	Intent i=getIntent();
	int id=i.getIntExtra("id",0);
	Toast.makeText(this,"سەل ساقلاڭ…",0).show();
	Save(nami.getText().toString(),adirisi.getText().toString(),kasma.getText().toString(),id,tili.getText().toString(),turi.getText().toString(),jieshao.getText().toString(),vip.getText().toString(),nadir.getText().toString());
}
	public void delete(int id){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=delete", "POST=ENTER&admin_parol="+login.get_login(this)+"&id="+id, new Handler() {
				public   void handleMessage(Message msg) {
					if(((String)msg.obj).equals("ok")){
						Toast.makeText(kinobaxkuruxBat.this,"ئ‍ۆچۈرۈلدى!",0).show();
						finish();

					}else{
						new AlertDialog.Builder(kinobaxkuruxBat.this).setMessage((String)msg.obj).show();
					}}

			}); 
	}
	public void delete(View v) {
		Intent i=getIntent();
		int id=i.getIntExtra("id",0);
		Toast.makeText(this,"سەل ساقلاڭ…",0).show();
		delete(id);
	}
}
