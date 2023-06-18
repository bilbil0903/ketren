package com.ketren.admin;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.app.AlertDialog.Builder;
import java.net.*;
import java.util.*;
import org.json.*;

public class kinobaxkuruxIzdax extends Activity
{
	private ArrayList<MyList> msgList;
	private MyAdapter mAdapter;
	private ListView gw;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.kinobaxkuruxizdax);
			}
	public void izdax(View v){
		Toast.makeText(this,"سەل ساقلاڭ…",0).show();
		EditText et=(EditText)findViewById(R.id.edit);
		if(!TextUtils.isEmpty(et.getText().toString())){
			Okux(et.getText().toString());
		}
	}
	public void Okux(String n){
		HttpUtils.httpRequest(this,"http://www.bilbil.top/kino.php?type=izdax_admin", "POST=ENTER&&KinoNami="+n+"&&admin_parol="+login.get_login(this), new Handler() {
				public   void handleMessage(Message msg) {
					try{
						if(msg.what!=0){
						String s=(String)msg.obj;
							s=Base64Coder.decodeString(s);
							gw=(ListView)findViewById(R.id.lv);
							msgList = new ArrayList<>();
							mAdapter = new MyAdapter(msgList,kinobaxkuruxIzdax.this);
							JSONArray ja=new JSONArray(s);
							for(int i=0;i<ja.length();i++){
								MyList msg1 = new MyList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoAdirisi"),ja.getJSONObject(i).getInt("id"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoTuri"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"),ja.getJSONObject(i).getString("nadir"));
								msgList.add(msg1);
								gw.setAdapter(mAdapter);
								mAdapter.notifyDataSetChanged();
							}
							gw.setOnItemClickListener(new OnItemClickListener(){

									@Override
									public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
									{
										Intent i=new Intent();
										i.setClass(kinobaxkuruxIzdax.this,kinobaxkuruxBat.class);
										i.putExtra("KinoNami",msgList.get(p3).getKinoNami());
										i.putExtra("KinoAdirisi",msgList.get(p3).getAdiris());
										i.putExtra("kasma",msgList.get(p3).getkasma());
										i.putExtra("tili",msgList.get(p3).getTili());
										i.putExtra("turi",msgList.get(p3).getTuri());
										i.putExtra("jieshao",msgList.get(p3).getkinoQuxandurulixi());
										i.putExtra("vip",msgList.get(p3).getVip());
										i.putExtra("id",msgList.get(p3).getID());
										i.putExtra("nadir",msgList.get(p3).getNadir());
										startActivity(i);
									}
								});
						}else{
							Toast.makeText(kinobaxkuruxIzdax.this,"ھالقىلىق سۆز تېپىلمىدى ياكى تور بىنۇرمال!",0).show();
						}

					}catch(Exception e){
						new AlertDialog.Builder(kinobaxkuruxIzdax.this).setMessage(e.getMessage()).show();
						Toast.makeText(kinobaxkuruxIzdax.this,""+e.getMessage(),0).show();
					}}

			}); 
	}
	
}

