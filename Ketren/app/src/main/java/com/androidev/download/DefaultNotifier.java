package com.androidev.download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.ketren.kino.R;
import java.util.List;

import static com.androidev.download.DownloadState.STATE_RUNNING;

public class DefaultNotifier implements DownloadNotifier {

    private Context context;
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 10000;
    private static final int REQUEST_CODE = 100;

    public DefaultNotifier(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void notify(List<DownloadInfo> infos) {
        int activeCount = infos.size();
        if (activeCount == 0) {
            notificationManager.cancel(NOTIFY_ID);
        } else {
            Notification.Builder builder = new Notification.Builder(context);
            Intent intent = new Intent();
            intent.setAction(DownloadManager.INTENT_ACTION_DOWNLOAD);
            PendingIntent downloadIntent = PendingIntent.getActivity(context, REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            String title = "چۈشۈۋاتىدۇ";
            String summary =activeCount+"دانە كىنو";
            builder.setSmallIcon(R.drawable.notify_download)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setContentText(summary)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(downloadIntent)
                    .setOngoing(true);
            Notification.InboxStyle style = new Notification.InboxStyle();
            style.setBigContentTitle(title);
            style.setSummaryText(summary);
            for (DownloadInfo info : infos) {
                if (info.state == STATE_RUNNING) {
                    style.addLine(info.name);
                }
            }
            builder.setStyle(style);
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
    }
}
