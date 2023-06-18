package com.ketren.kino;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ketren.kino.DouYin;
import com.ketren.kino.KiskaFilim;
import com.ketren.kino.R;
import java.util.ArrayList;
public class kiskaMainActivity extends Fragment 
{

	private View view;
	private ViewPager viewpager;
	private RadioGroup rg;
	private int last_id=-1;
	private boolean isfirst;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.kiskamain,container,false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager2);
		rg=(RadioGroup)view.findViewById(R.id.rg);
		viewpager.setOffscreenPageLimit(2);
		for(int i=0;i<rg.getChildCount();i++){
			RadioButton v=(RadioButton)rg.getChildAt(i);
			v.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"UyghurFont.ttf"));
		}
		final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new KiskaFilim());
		list.add(new DouYin());
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

				

				

				

				@Override
				public void onPageScrollStateChanged(int p1) {
					}

				@Override
				public void onPageScrolled(int i, float v, int i11) {
				}


				@Override
				public void onPageSelected(int i) {
					scroll2(i);
					}});
		viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
				@Override
				public Fragment getItem(int i) {
					return list.get(i);
				}

				@Override
				public int getCount() {
					return list.size();
				}
			});
		rg.setOnCheckedChangeListener(new   RadioGroup.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					RadioButton rb=view.findViewById(checkedId);
					viewpager.setCurrentItem(rb.getTag());
				}});
				scroll2(0);
				viewpager.setCurrentItem(1);
		return view;
		
		}
		
	public void scroll2(int i){
		RadioButton rb = (RadioButton)rg.getChildAt(i);
		rb.setTag(i);
		if(rb!=null&&!isfirst){
			rb.setTextColor(Color.BLUE);
			rb.setTextSize(15l);
			if(last_id!=-1&&last_id!=i){
				RadioButton rb2=(RadioButton)rg.getChildAt(last_id);
				rb2.setTextColor(Color.BLACK);
				rb2.setTextSize(15l);
			}
		}
		last_id=i;
   isfirst=false;
	}
		}
