package com.ketren.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ketren.admin.kinoyollax;
import java.net.URLEncoder;

public class nahxayollax extends Activity {
    EditText nahxa,nahxiqi,adiris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nahxayollax);
		nahxa=findViewById(R.id.nahxa);
		nahxiqi=findViewById(R.id.nahxiqi);
		adiris=findViewById(R.id.adiris);
    }
    public void Yollax(String nahxa,String nahxiqi,String adiris ){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=nahxa_yollax", "POST=ENTER&&nahxa="+nahxa+"&&nahxiqi="+nahxiqi+"&&adiris="+adiris+"&admin_parol="+login.get_login(this), new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s.equals("ok")){
							Toast.makeText(nahxayollax.this,"يوللاندى!",0).show();
							Intent i=new Intent();
							i.setClass(nahxayollax.this,nahxayollax.class);
							startActivity(i);
							finish();
						}else{
							Toast.makeText(nahxayollax.this,s,0).show();
						}
					}catch(Exception e){
						new AlertDialog.Builder(nahxayollax.this).setMessage(e.getMessage()).show();

					}}

			}); 

	}

	public void yollax(View v){
			if(nahxa.getText().toString().length()>0&&nahxiqi.getText().length()>0&&adiris.getText().toString().length()>0){
			Toast.makeText(nahxayollax.this,"سەل ساقلاڭ…",0).show();
			Yollax(nahxa.getText().toString(),nahxiqi.getText().toString(),adiris.getText().toString());
			}else{
			Toast.makeText(nahxayollax.this,"مەزمۇن يېزىڭ!",0).show();
		}
		
}}
