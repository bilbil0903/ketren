package com.ketren.kino;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
public class tagga extends Activity {

	private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagga);
		iv=(ImageView)findViewById(R.id.gul);
		GET(this);
    }
    public  void GET(final Activity c){
		String  user=tizimlitix.get_login((Activity)c);
		HttpUtils.httpRequest(c,U.url+"kino.php?type=imza","POST=ENTER&&user="+user,new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
                          if(s.equals("ok")){
							  iv.setBackgroundResource(R.drawable.gul_2);
						  }
					}catch(Exception e){
						//new AlertDialog.Builder((Context)c).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public  void gul(View v){
		String  user=tizimlitix.get_login((Activity)this);
		HttpUtils.httpRequest(this,U.url+"kino.php?type=imza_koyux","POST=ENTER&&user="+user,new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s.equals("ok")){
							iv.setBackgroundResource(R.drawable.gul_1);
							Toast.makeText(tagga.this,"گۈلنى غەلبىلىك ئ‍ۈزدىڭىز!",0).show();
						}else{
							Toast.makeText(tagga.this,"ئ‍ەتە ئ‍ەپكە كىرىپ ئ‍ۈزۈڭ!",0).show();
						}
					}catch(Exception e){
						//new AlertDialog.Builder((Context)c).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	
}
