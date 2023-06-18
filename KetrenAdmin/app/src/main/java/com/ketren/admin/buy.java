package com.ketren.admin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ketren.admin.login;

public class buy extends Activity {
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);
		e1=findViewById(R.id.time);
		e2=findViewById(R.id.phone);
    }
    public void SAVE(final String time,String user){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=buy", "POST=ENTER&&admin_parol="+login.get_login(this)+"&&time="+time+"&&user="+user, new Handler() {
				public   void handleMessage(Message msg) {
					if(((String)msg.obj).equals("ok")){
						Toast.makeText(buy.this,"قاچىلاندى!",0).show();
						finish();
					}else{
						
					}

				}});
	}
	public void ok(View v){
		String time=e1.getText().toString();
		String phone=e2.getText().toString();
		if(time!=null&&phone.length()==11){
			Toast.makeText(this,"سەل ساقلاڭ…",0).show();
			SAVE(time,phone);
		}
	}
}
