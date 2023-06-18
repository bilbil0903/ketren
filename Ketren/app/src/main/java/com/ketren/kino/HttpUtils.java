package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils extends Activity 
{

	public  static void httpRequest(final Activity A,final String url, final String params, final Handler HD) {
        try {
            new Thread() {
                public   void run() {
					Looper.prepare();
                    try {
                        URL U = new URL(url);
                        HttpURLConnection H = (HttpURLConnection) U.openConnection();
                        if (params == "") {
                            H.setRequestMethod("GET");
                        } else {
                            H.setRequestMethod("POST");
                            H.setDoOutput(true);
                            OutputStream O = H.getOutputStream();
                            O.write(params.getBytes());
                            O.flush();
                            O.close();
                        }
                        DataInputStream DI = new DataInputStream(H.getInputStream());
                        StringBuilder S = new StringBuilder();
                        String s;
                        while ((s = DI.readLine()) != null) {
                            S.append(s);

                        }
				      Message MS = new Message();
                        MS.what = 1;
						if(S!=null){
                        try {
                            MS.obj = new String(S.toString().getBytes("ISO-8859-1"), "UTF-8");
                        } catch (Exception ignored) {
                        }
						}else{
							MS.what = 2;
							HD.sendMessage(MS);
						}
                        HD.sendMessage(MS);
                        DI.close();
                    } catch (Exception e) {
                        Message MS = new Message();
                        MS.what = 0;
						MS.obj=null;
                        HD.sendMessage(MS);
                    }
					Looper.loop();
                }
            }.start();
        } catch (Exception e) {
			new AlertDialog.Builder((Context)A).setMessage(e.getMessage()).show();
		}
    }
	public static void getImage(final Activity iv,final String prams,final Handler h) {
		new Thread(new Runnable(){
				public void run(){

					try {
						URL url = new URL(prams);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.connect();
						if(conn.getResponseCode() == 200)
						{
							InputStream is = conn.getInputStream();
							Bitmap bm = BitmapFactory.decodeStream(is);
							Message msg=Message.obtain();
							msg.obj =bm;
							h.sendMessage(msg);
						}
					} catch (Exception e) {
						Message m=Message.obtain();
						m.obj=null;
						h.sendMessage(m);
						//new AlertDialog.Builder(iv).setMessage("error_get:"+e.getMessage()).show();
					}
				}}).start();}
	
	
}

