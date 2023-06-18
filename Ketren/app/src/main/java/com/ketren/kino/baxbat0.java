package com.ketren.kino;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.androidev.download.DownloadManager;
import com.androidev.download.DownloadTask;
import com.ketren.kino.Nahxa.NahxaAdapter;
import com.ketren.kino.Permission.PermissionsManager;
import com.ketren.kino.Permission.PermissionsResultAction;
import java.util.List;
public class baxbat0 extends FragmentActivity implements View.OnClickListener {

    private RadioButton image1;
    private RadioButton image2;
	private RadioButton image33;
	private RadioButton image4;
    private man bat0;
    private kiskaMainActivity bat1;
    private MainActivity bat3;
	private NahxaBaxbat bat5;
	private List<DownloadTask> tasks;
	private DownloadManager manager;
//Writed by Snail On 2020 www.bilbil.top
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		baxbat0.requestPermission(this);
		//boolean b=isServiceRunning(this,"KinoService");
		//if(b){
		startService(new Intent(this,KinoService.class));
		//}
        initViews();
        initEvents();
        //首先 我们先选定一个
        select(3);
    }
    //初始化  各种个 View
    private void initViews(){
        image1 = (RadioButton) findViewById(R.id.tab_image1);
        image2 = (RadioButton) findViewById(R.id.tab_image2);
       	image33 = (RadioButton) findViewById(R.id.tab_image5);
		image4 = (RadioButton) findViewById(R.id.tab_image4);
    }
    //初始化 监听事件
    private void initEvents(){
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
		image33.setOnClickListener(this);
		image4.setOnClickListener(this);
    }
    // 初始化 各种图片
    private void initImageBack(){
        image1.setBackgroundResource(R.drawable.ic_account);
        image2.setBackgroundResource(R.drawable.ic_diamond_outline);
   		image33.setBackgroundResource(R.drawable.music_kara);
		image4.setBackgroundResource(R.drawable.baxbat1);
    }
    long waitTime = 2000;
	long touchTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if((currentTime-touchTime)>=waitTime) {
				man.TOAST(this,"يەنە بىر باسسىڭىز چىكىنىدۇ");
				touchTime = currentTime;
			}else {
			finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    private void select(int i){
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务

        hidtFragment(ft);   //先隐藏 Fragment

        switch (i){
            case 0:
                image1.setBackgroundResource(R.drawable.ic_account_blue);
                if (bat0== null){
                    bat0 = new man();
                    ft.add(R.id.fragment_container,bat0);
                }else{
                    ft.show(bat0);
                }
                break;
            case 1:
                image2.setBackgroundResource(R.drawable.ic_diamond);
                if (bat1 == null){
                    bat1 = new kiskaMainActivity();
                    ft.add(R.id.fragment_container,bat1);
                }else {
                    ft.show(bat1);
                }
                break;
           
				case 2:
				image33.setBackgroundResource(R.drawable.music_kok);
                if (bat5 == null){
                    bat5 = new NahxaBaxbat();
                    ft.add(R.id.fragment_container,bat5);
                }else {
                    ft.show(bat5);
                }
                break;
			case 3:
				image4.setBackgroundResource(R.drawable.baxbat2);
                if (bat3 == null){
                    bat3 = new MainActivity();
                    ft.add(R.id.fragment_container,bat3);
                }else {
                    ft.show(bat3);
                }
                break;
        }
        ft.commit();   //提交事务
	
    }
    //隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction){
        if (bat0 != null){
            fragmentTransaction.hide(bat0);
        }
        if (bat1 != null){
            fragmentTransaction.hide(bat1);
        }
		if (bat3 != null){
            fragmentTransaction.hide(bat3);
        }
		if(bat5!=null){
			fragmentTransaction.hide(bat5);
		}
    }
    //重写监听
    @Override
    public void onClick(View v) {

        initImageBack(); //初始化 图片背景
        switch (v.getId()){
            case R.id.tab_image1:
                select(0);
                break;
            case R.id.tab_image2:
                select(1);
                break;
			case R.id.tab_image5:
                select(2);
                break;
        case R.id.tab_image4:
			select(3);
			break;
    }}
	public static  void requestPermission(final Context c) {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary((Activity)c, new PermissionsResultAction() {
				@Override
				public void onGranted() {
//				Toast.makeText(getContext(), "All permissions have been granted", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onDenied(String permission) {
					man.TOAST(c,"ھوقوق بېرىلمىدى،ئەپ ئىشلىتىشتە بەزى مەسىلىلەرگە يولىقىشىڭىز مۇمكىن !");	}
			});
    }
	@Override
	public void onDestroy() {
		super.onDestroy();
		manager = DownloadManager.getInstance();
        tasks = manager.getAllTasks();
		for (DownloadTask task : tasks) {
			task.pause();
		}
		NahxaAdapter na=new NahxaAdapter();
		na.stop();
	}

    /**
     * 判断服务是否开启
     *
     * @return
     */
    /* 
     * 判断服务是否启动,context上下文对象 ，className服务的name 
     */  
    public static boolean isServiceRunning(Context mContext, String className) {  

        boolean isRunning = false;  
        ActivityManager activityManager = (ActivityManager) mContext  
			.getSystemService(Context.ACTIVITY_SERVICE);  
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager  
			.getRunningServices(30);  

        if (!(serviceList.size() > 0)) {  
            return false;  
        }  

        for (int i = 0; i < serviceList.size(); i++) {  
            if (serviceList.get(i).service.getClassName().equals(className) == true) {  
                isRunning = true;  
                break;  
            }  
        }  
        return isRunning;  
    }  
	
	}

  


  

