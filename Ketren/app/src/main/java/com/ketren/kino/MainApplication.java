package com.ketren.kino;

import android.app.Application;

import com.androidev.download.DefaultNotifier;
import com.androidev.download.DownloadManager;
import android.support.v7.app.AlertDialog;

/**
 * Created by 4ndroidev on 17/4/20.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
		try{
        DownloadManager.getInstance().initialize(this, 50);
        //DownloadManager.getInstance().setDownloadNotifier(new DefaultNotifier(this));
           
		}catch(Exception e){
			new AlertDialog.Builder(getApplicationContext()).setMessage("e:"+e.getMessage()).show();
		}}
}
