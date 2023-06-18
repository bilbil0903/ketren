package com.ketren.kino;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.androidev.download.DefaultNotifier;
import com.androidev.download.DownloadManager;
import com.androidev.download.DownloadTask;
import com.ketren.kino.R;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class KinoService extends Service {
	int SAN=-1;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//tongzhi
		GET_OK("POST=ENTER");
		//下载
		DownloadManager.getInstance().initialize(getApplicationContext(), 50);
		//DownloadManager.getInstance().setDownloadNotifier(new DefaultNotifier(getApplicationContext()));
		
		return START_STICKY;// 这个返回值其实并没有什么卵用，除此以外还有START_NOT_STICKY与START_REDELIVER_INTENT
	}
public void TongZhi(String Tima, String Mazmun,String id){
	Intent in=new Intent(getApplicationContext(),kisim.class);
	in.putExtra("id",id);
	in.putExtra("KinoNami",Mazmun);
	PendingIntent contentIntent =PendingIntent.getActivity(getApplicationContext(),0,
														   new Intent(in),PendingIntent.FLAG_UPDATE_CURRENT);
	Notification.Builder builder =new Notification.Builder(getApplicationContext());
	builder.setContentIntent(contentIntent);
	builder.setSmallIcon(R.drawable.ic_launcher);
	builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
	builder.setTicker(Tima);//添加相关的提示信息
	builder.setWhen(System.currentTimeMillis());
	builder.setContentText(Mazmun);
	NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	if (Build.VERSION.SDK_INT>=26){
		String channelid ="channelid";
		builder.setChannelId(channelid);
		NotificationChannel channel =new NotificationChannel(channelid,"Chanle of exmple",
															 NotificationManager.IMPORTANCE_DEFAULT);
		notificationManager.createNotificationChannel(channel);
		Notification msg=builder.build();
		notificationManager.notify(1,msg);
	}else {
		Notification msg=builder.build();
		notificationManager.notify(1,msg);
	}
	
}
	
	public  void GET(final String prams,final Handler h) {
		 new Thread(new Runnable(){
				public void run(){
       while(true){
					try {
						Thread.sleep(60000*5);
						URL Ur = new URL(U.url+"kino.php?type=tongzhi");
                        HttpURLConnection H = (HttpURLConnection) Ur.openConnection();
                        if (prams == "") {
                            H.setRequestMethod("GET");
                        } else {
                            H.setRequestMethod("POST");
                            H.setDoOutput(true);
                            OutputStream O = H.getOutputStream();
                            O.write(prams.getBytes());
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
							h.sendMessage(MS);
						}
                        h.sendMessage(MS);
                        DI.close();
                    } catch (Exception e) {
                        Message MS = new Message();
                        MS.what = 0;
						MS.obj=null;
                        h.sendMessage(MS);
                    }
					
					}
				}}).start();
				
				}
				
	public   void GET_OK(final String post){
		GET(post,new Handler(){
				@Override
				public void handleMessage( Message msg) {
					try{
					String s=(String)msg.obj;
					if(s!=null){
					String[] S=s.split(",");
					String san=S[0];
					String isim=S[1];
					if(SAN!=Integer.parseInt(san)&&SAN!=-1){
    TongZhi("كەترەنگە كىنو يوللاندى!",isim,san);
	
	}
						SAN=Integer.parseInt(san);
	}
	}catch(Exception e){
		
	}
					}});}
	
	
}

