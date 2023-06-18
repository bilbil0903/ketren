package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.DY.douyin.widget.OnViewPagerListener;
import com.DY.douyin.widget.PagerLayoutManager;
import com.heartfor.heartvideo.video.HeartVideo;
import com.heartfor.heartvideo.video.HeartVideoInfo;
import com.heartfor.heartvideo.video.VideoControl;
import com.ketren.kino.R;
import com.ketren.kino.kiska.MyAdapter;
import com.ketren.kino.kiska.myList;
import java.util.ArrayList;
import org.json.JSONArray;
import com.DY.douyin.VideoAdapter;
import com.DY.douyin.Bean;
public class DouYin extends Fragment
{
	
	private View view;
	private RecyclerView recyclerView;
	private HeartVideo heartVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.douyin_layout,container,false);
		recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);
        PagerLayoutManager mLayoutManager = new PagerLayoutManager(getContext(), OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        Kisim();
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
				@Override
				public void onInitComplete(View view) {
				
					heartVideo=view.findViewById(R.id.myvideo);
					heartVideo.start();
					
				}

				@Override
				public void onPageSelected(int position, boolean isBottom, View view) {
					//if(heartVideo!=null){
						heartVideo=view.findViewById(R.id.myvideo);
						heartVideo.start();
					//}
					
				}

				@Override
				public void onPageRelease(boolean isNext, int position, View view) {
					if(isNext){
					heartVideo.releasePlayer();
					}
				}
			});


	
		return view;
		}
		
	public void Kisim(){
		HttpUtils.httpRequest((Activity)getContext(),"http://bilbil.top/kino.php?type=kiska","POST=ENTER&&turi="+Base64Coder.encodeString("1"),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String mazmun=(String)msg.obj;
						JSONArray ja=new JSONArray(mazmun);
						ArrayList<Bean> mylist=new ArrayList<>();
						VideoAdapter myadapter=new VideoAdapter(getContext(),mylist);
						for(int i=0;i<ja.length();i++){
							Bean kl=new Bean(ja.getJSONObject(i).getString("KinoAdirisi"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoNami"));
							mylist.add(kl);	
						}
						recyclerView.setAdapter(myadapter);
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if(heartVideo!=null){
	heartVideo.start();
	}
		} else {
			//相当于Fragment的onPause
			if(heartVideo!=null){
			heartVideo.pause();
			}
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if(heartVideo!=null){
			heartVideo.pause();
		}
	}

	

	@Override
	public void onStop() {
		super.onStop();
		if(heartVideo!=null){
			heartVideo.pause();
		}
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(heartVideo!=null){
			heartVideo.releasePlayer();
		}
	}
	
	
	}

