package com.ketren.admin;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import org.json.*;

public class ilanbaxkurux extends Activity 
{
	EditText e1,e11,e2,e22,e3,e33,e4,e44;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ilanbaxkurux);
		e1=findViewById(R.id.i1);
		e11=findViewById(R.id.i11);
		e2=findViewById(R.id.i2);
		e22=findViewById(R.id.i22);
		e3=findViewById(R.id.i3);
		e33=findViewById(R.id.i33);
		e4=findViewById(R.id.i4);
		e44=findViewById(R.id.i44);
		GET();
    }
	public void SAVE(String ilan){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=save_ilan", "POST=ENTER&&ilan="+ilan+"&&admin_parol="+login.get_login(this), new Handler() {
				public   void handleMessage(Message msg) {
					//if(((String)msg.obj).equals("ok")){
						Toast.makeText(ilanbaxkurux.this,"ساقلاندى!",0).show();
						finish();

					//}else{
					Toast.makeText(ilanbaxkurux.this,"ساقلاندى!"+msg.obj,0).show();
					
					}

			}); 
	}
	public void GET(){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/ilan.php", "", new Handler() {
				public   void handleMessage(Message msg) {
	             try{
					String s=(String)msg.obj;				
					if(s!=null){
						JSONArray ja=new JSONArray("["+s+"]");
						e1.setText(new JSONArray(ja.getString(0)).getString(0));
						e11.setText(new JSONArray(ja.getString(0)).getString(1));
						e2.setText(new JSONArray(ja.getString(1)).getString(0));
						e22.setText(new JSONArray(ja.getString(1)).getString(1));
						e3.setText(new JSONArray(ja.getString(2)).getString(0));
					    e33.setText(new JSONArray(ja.getString(2)).getString(1));
						e4.setText(new JSONArray(ja.getString(3)).getString(0));
						e44.setText(new JSONArray(ja.getString(3)).getString(1));
						}
				}catch(Exception e){
					new AlertDialog.Builder(ilanbaxkurux.this).setMessage(e.getMessage()).show();
				}}

			}); 
	}
	public void save(View v){
		Toast.makeText(this,"سەل ساقلاڭ…",0).show();
		String mazmun1="[\""+e1.getText().toString()+"\",\""+e11.getText().toString()+"\"],";
		String mazmun2="[\""+e2.getText().toString()+"\",\""+e22.getText().toString()+"\"],";
		String mazmun3="[\""+e3.getText().toString()+"\",\""+e33.getText().toString()+"\"],";
		String mazmun4="[\""+e4.getText().toString()+"\",\""+e44.getText().toString()+"\"]";
		SAVE(mazmun1+mazmun2+mazmun3+mazmun4);
		}
}
