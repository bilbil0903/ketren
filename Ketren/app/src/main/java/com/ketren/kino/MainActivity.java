package com.ketren.kino;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ketren.kino.Fragments.daris;
import com.ketren.kino.Fragments.mtv;
import com.ketren.kino.Fragments.awat;
import com.ketren.kino.Fragments.zonghe;
import com.ketren.kino.R;
import java.util.ArrayList;
import com.ketren.kino.Fragments.kisimlik;
import com.ketren.kino.Fragments.karton;
import com.ketren.kino.Fragments.kino;
import com.ketren.kino.Fragments.yigi;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.content.Intent;
import com.ketren.kino.Fragments.tuidao;
public class MainActivity extends Fragment
{
	private ViewPager viewpager;
	boolean vip_boolean1=true;
	boolean vip_boolean2=true;
	boolean  vip_boolean3=true;
	boolean  vip_boolean4=true;
	boolean vip_boolean5=true;
	boolean vip_boolean6=true;
	boolean  vip_boolean7=true;
	boolean  vip_boolean8=true;
	boolean isfirst=true;
	private View view;
    ImageButton izdax;
	private RadioGroup rg;
    int last_id=-1;
	private HorizontalScrollView hs;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_main,container,false);
		
	try{
	    viewpager = (ViewPager) view.findViewById(R.id.viewpager);
	    viewpager.setOffscreenPageLimit(9);
		 rg=(RadioGroup)view.findViewById(R.id.rg);
		 izdax=view.findViewById(R.id.izdax);
		for(int i=0;i<rg.getChildCount();i++){
			RadioButton v=(RadioButton)rg.getChildAt(i);
			v.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"UyghurFont.ttf"));
		}
	 hs=(HorizontalScrollView)view.findViewById(R.id.sc);
	 //scroll();
   //  closeAndroidPDialog();
		final ArrayList<Fragment> list = new ArrayList<>();
		list.add(new tur());
        list.add(new zonghe());
		list.add(new daris());
		list.add(new mtv());
		list.add(new kisimlik());
		list.add(new karton());
		list.add(new kino());
		list.add(new tuidao());
		list.add(new awat());
		list.add(new yigi());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int i, float v, int i11) {
					if(isfirst){
					scroll2(10);
					isfirst=false;
					}
					izdax.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View p1) {
							
								Intent i=new Intent();
								i.setClass(getContext(),izdax.class);
								startActivity(i);
							}
						});
					 }
				

				@Override
				public void onPageSelected(int i) {
					
					if(i==1){
						if(vip_boolean1){
							vip_boolean1=false;
							zonghe.Okux(getContext());
						}
					}else if(i==2){
						if(vip_boolean2){
							vip_boolean2=false;
							daris.Okux(getContext());
						}
					}else if(i==3){
						if(vip_boolean3){
							vip_boolean3=false;
							mtv.Okux(getContext());
						}
					}else if(i==4){
						if(vip_boolean4){
							vip_boolean4=false;
							
							kisimlik.Okux(getContext());
						}
					}else if(i==5){
						if(vip_boolean5){
							vip_boolean5=false;
							karton.Okux(getContext());
						}
					}else if(i==6){
						if(vip_boolean6){
							vip_boolean6=false;
							kino.Okux(getContext());
						}
					}else if(i==7){
						if(vip_boolean7){
							vip_boolean7=false;
							tuidao.Okux(getContext());
							}
						}else if(i==8){
						if(vip_boolean8){
							vip_boolean8=false;
							awat.Okux(getContext());
						}
						}
					
					scroll2(i+1);
				}

				@Override
				public void onPageScrollStateChanged(int i) {
                  
				}
			});

        viewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
				@Override
				public Fragment getItem(int i) {
					return list.get(i);
				}

				@Override
				public int getCount() {
					return list.size();
				}
			});
			viewpager.setCurrentItem(9);
   }catch(Exception e){
	   new AlertDialog.Builder(getContext()).setMessage(e.getMessage().toString()).show();
   }
		rg.setOnCheckedChangeListener(new   RadioGroup.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					RadioButton rb=(RadioButton)view.findViewById(checkedId);
					viewpager.setCurrentItem(panduan(rb.getText().toString()));
						}});
	   
   return view;
   }
   
	
	
	/*
	private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	*/
	
	public void scroll2(int i){
		rg.check(i);
		Display d =getActivity(). getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics(); d.getMetrics(dm);
		final int screenHalf = d.getWidth()/2;//屏幕宽度的一半
		final int scrollX = hs.getScrollX();
		RadioButton rb = (RadioButton)view. findViewById(rg.getCheckedRadioButtonId());
		int left = rb.getLeft();
		final int leftScreen = left-scrollX;
		if(rb!=null){
		rb.setTextColor(Color.WHITE);
		rb.setTextSize(25l);
		if(last_id!=-1&&last_id!=rg.getCheckedRadioButtonId()){
			RadioButton rb2=(RadioButton)view.findViewById(last_id);
			rb2.setTextColor(Color.BLACK);
			rb2.setTextSize(18l);
		}
		}
		hs.post(new Runnable() {
				@Override
				public void run() {
		hs.smoothScrollBy((leftScreen-screenHalf), 0);
		}
		});
		last_id=rg.getCheckedRadioButtonId();
		
	}
	public int panduan(String s){
		int i=0;
		if(s.equals("تۈرلەر")){
			i=0;
		}
		else if(s.endsWith("ئ‍ونۋېرسال")){
			i=1;
		}else if(s.equals("دەرىس")){
			i=2;
		}else if(s.equals("MTV")){
			i=3;
		}else if(s.equals("قىسىملىق")){
			i=4;
		}else if(s.equals("كارتون")){
			i=5;
		}else if(s.equals("كىنو")){
			i=6;
		}else if(s.equals("تەۋسىيە")){
			i=7;
		}else if(s.equals("ئاۋات")){
		i=8;
		}else if(s.equals("يىڭى")){
			i=9;
		}
		return i;
	}

	}
