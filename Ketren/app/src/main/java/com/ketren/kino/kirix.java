package com.ketren.kino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ketren.kino.kirix;
import android.app.AlertDialog;

public class kirix extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirix);
    }
    public void ok(View v){
        EditText isim=(EditText)findViewById(R.id.isim);
        EditText parol=(EditText)findViewById(R.id.parol);
		if(isim.getText().toString().length()>0&&parol.getText().toString().length()>0){
        Toast.makeText(this,"سەل ساقلاڭ!",0).show();
        HttpUtils.httpRequest(this,U.url+"kino.php?type=kirix","POST=ENTER&&isim="+isim.getText().toString()+"&&parol1="+parol.getText().toString(),new Handler() {
                public   void handleMessage(Message msg) {
                    try{
                    String s=(String)msg.obj;
                    if(s!=""&&s!=null&&s.length()==11){
                     tizimlitix.login(s,kirix.this);
                    man.TOAST(kirix.this,"غەلبىلىك بولدى!");
                    finish();
                }else{
                   man.TOAST(kirix.this,"پارول خاتا ياكى تور بىنۇرمال!");
                }
                }catch(Exception e){
                    new AlertDialog.Builder(kirix.this).setMessage(""+e.getMessage()).show();
                }
}
            }); 
}else{
	man.TOAST(this,"ئىسىم ياكى پارولنى كىرگۈزۈڭ !");
}
	}
    public void baxka(View v){
        Intent i=new Intent();
        i.setClass(this,tizimlitix.class);
        startActivity(i);
        finish();
    }
}
