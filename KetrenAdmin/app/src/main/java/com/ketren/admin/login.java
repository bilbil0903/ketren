package com.ketren.admin;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
		EditText e=(EditText)findViewById(R.id.et);
		if(this.get_login(this)==null||this.get_login(this)==""){
		e.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
				}

				@Override
				public void afterTextChanged(Editable p1) {
					if(p1.toString().length()>5){
					SAVE(p1.toString());
					}
				}
			});
			}else{
				Intent i=new Intent();
				i.setClass(login.this,MainActivity.class);
				startActivity(i);
				finish();
			}
    }
    public void SAVE(final String parol){
		HttpUtils.httpRequest(this, "http://www.bilbil.top/kino.php?type=admin_enter", "POST=ENTER&&admin_parol="+parol, new Handler() {
				public   void handleMessage(Message msg) {
					if(((String)msg.obj).equals("ok")){
						login(parol,login.this);
					Toast.makeText(login.this,"غەلبىلىك كىردىڭىز!",0).show();
					Intent i=new Intent();
					i.setClass(login.this,MainActivity.class);
					startActivity(i);
					finish();
					}else{
					//Toast.makeText(login.this,"……؟"+msg.obj,0).show();

				}

}});
}

	public static void login(String str,Activity A){
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS user(post)");
		if(get_login(A)==null){
			sql.execSQL("INSERT INTO user VALUES('"+str+"');");
		}
	}
	
	public  static  String get_login(Activity A){
		String s=null;
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS user(post)");
		Cursor	c=sql.rawQuery("select*from user",null);
		if(c.getCount()>0){
			while(c.moveToNext()){
				s=c.getString(0);
			}}else{
			s=null;
		}
		return s;
	}

}
