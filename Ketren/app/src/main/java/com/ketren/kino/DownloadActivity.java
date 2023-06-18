package com.ketren.kino;
import android.Manifest;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.ketren.kino.Permission.PermissionsManager;
import com.ketren.kino.Permission.PermissionsResultAction;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.json.JSONArray;
import android.widget.ImageButton;
import com.androidev.download.DownloadJobListener;
import com.androidev.download.sample.fragment.DownloadedFragment;
import com.androidev.download.sample.fragment.DownloadingFragment;
import com.androidev.download.DownloadManager;
import com.androidev.download.DownloadTask;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;                          
public class DownloadActivity extends FragmentActivity 
{
	private ViewPager viewpager;
	TextView t1,t2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			setContentView(R.layout.activity_download);
			viewpager = (ViewPager) findViewById(R.id.viewpager);
			viewpager.setOffscreenPageLimit(2);
			baxbat0.requestPermission(this);
			t1=findViewById(R.id.t1);
			t2=findViewById(R.id.t2);
			final ArrayList<Fragment> list = new ArrayList<>();
			//2.创建页面
			list.add(new DownloadingFragment());
			list.add(new DownloadedFragment());
			viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageScrolled(int i, float v, int i11) {

					if(i==0){
						t1.setTextColor(Color.BLUE);
						t2.setTextColor(Color.BLACK);
					}else if(i==1){
						t1.setTextColor(Color.BLACK);
						t2.setTextColor(Color.BLUE);
					}
					}

					@Override
					public void onPageSelected(int i) {

					}

					@Override
					public void onPageScrollStateChanged(int i) {

					}
				});

			viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
					@Override
					public Fragment getItem(int i) {
						return list.get(i);
					}

					@Override
					public int getCount() {
						return list.size();
					}
				});

		}catch(Exception e){
			new AlertDialog.Builder(this).setMessage(e.getMessage().toString());
		}}

	public void t1(View v){
		viewpager.setCurrentItem(0);
		t1.setTextColor(Color.BLUE);
		t2.setTextColor(Color.BLACK);
	}
	public void t2(View v){
		viewpager.setCurrentItem(1);
		t1.setTextColor(Color.BLACK);
		t2.setTextColor(Color.BLUE);
		}

public void back(View v){
	finish();
}
	public void  add(View v){
					final Dialog builder = new Dialog(this,R.style.myDialog);
					//builder.setIcon(R.mipmap.welcome_logo);
					builder.setTitle("چۈشۈرۈش");
					final View view = View.inflate(this, R.layout.add_dialog, null);
					final EditText url = view.findViewById(R.id.url);
		             final EditText name = view.findViewById(R.id.name);
		final Button ok = view.findViewById(R.id.ok);
		final Button no = view.findViewById(R.id.no);
		ok.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					String U = url.getText().toString();
					String N=name.getText().toString();
					if(U!=null&&N!=null&&U.length()>3&&N.length()>3){
						Toast.makeText(DownloadActivity.this,"چۈشۈرۈش باشلاندى!",0).show();
						DownloadManager controller = DownloadManager.getInstance();
						DownloadTask dt;
						dt=controller.newTask(hashCode(), U,N).extras("").create();
						dt.start();
						builder.dismiss();
					}
				}
				
			});
					builder.setContentView(view);
		no.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
				builder.dismiss();
				}
			});

					
					builder.show();
				}
	}
