package com.ketren.admin;
import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import java.io.*;
import java.net.*;
import org.json.*;
import android.widget.*;

public class HttpUtils extends Activity 
{
	public  static void httpRequest(Activity A,final String url, final String params, final Handler HD) {
        try {
            new Thread() {
                public   void run() {
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
                        try {
                            MS.obj = new String(S.toString().getBytes("ISO-8859-1"), "UTF-8");
                        } catch (Exception ignored) {
                        }
                        HD.sendMessage(MS);
                        DI.close();
                    } catch (Exception e) {
                        Message MS = new Message();
                        MS.what = 0;
                        HD.sendMessage(MS);
                    }
                }
            }.start();
        } catch (Exception e) {
			new AlertDialog.Builder((Context)A).setMessage(e.getMessage()).show();
		}
    }
}


