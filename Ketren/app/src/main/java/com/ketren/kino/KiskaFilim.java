package com.ketren.kino;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.heartfor.heartvideo.video.HeartVideo;
import com.heartfor.heartvideo.video.HeartVideoManager;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.kiska.MyAdapter;
import com.ketren.kino.kiska.myList;
import java.util.ArrayList;
import org.json.JSONArray;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
public class KiskaFilim extends Fragment
{
	private RecyclerView recyclerView;
    private LinearLayoutManager linearmanager;
    private int firstVisibleItem;
    private int lastVisibleItem;
    private int visibleCount;
	private View view;

	private HeartVideo heartVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.kiskafilim,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recy);
		linearmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearmanager);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
        
        Kisim();
        //重复使用监听
        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
				@Override
				public void onViewRecycled(RecyclerView.ViewHolder holder) {
					HeartVideo heartVideo = ((MyAdapter.ViewHolder) holder).myvideo;
					if (heartVideo == HeartVideoManager.getInstance().getCurrPlayVideo()) {
						HeartVideoManager.getInstance().release();
					}
				}
			});
        //滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
					super.onScrollStateChanged(recyclerView, newState);
					switch (newState) {
						case SCROLL_STATE_IDLE: //滚动停止
							autoPlayVideo(recyclerView);
							break;
					}
				}

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);
					firstVisibleItem = linearmanager.findFirstVisibleItemPosition();
					lastVisibleItem = linearmanager.findLastVisibleItemPosition();
					visibleCount = lastVisibleItem - firstVisibleItem;
				}
			});
		((PullToRefreshLayout)view.findViewById(R.id.refresh_view))
			.setOnRefreshListener(new OnRefreshListener(){

				@Override
				public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
				{   
					new Handler(){
						@Override
						public void handleMessage(Message msg)
						{
							Kisim();
							pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 2500);
				}

				@Override
				public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
				{

					new Handler()
					{
						@Override
						public void handleMessage(Message msg)
						{
							Kisim();
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 500);
				}
			});
        return view;

    }
	
	private void autoPlayVideo(RecyclerView recyclerview) {
		RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
		for (int i = 0; i < visibleCount; i++) {
			if (layoutManager != null && layoutManager.getChildAt(i) != null && layoutManager.getChildAt(i).findViewById(R.id.myvideo) != null) {
				 heartVideo = (HeartVideo) layoutManager.getChildAt(i).findViewById(R.id.myvideo);
				Rect rect = new Rect();
				heartVideo.getLocalVisibleRect(rect);
				int videoheight = heartVideo.getHeight();
				if (rect.top == 0 && rect.bottom == videoheight) {
                    //    heartVideo.start();
					return;
				}

			}
		}
	}

	public void Kisim(){
		HttpUtils.httpRequest((Activity)getContext(),"http://bilbil.top/kino.php?type=kiska","POST=ENTER&&turi="+Base64Coder.encodeString("0"),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String mazmun=(String)msg.obj;
						JSONArray ja=new JSONArray(mazmun);
						ArrayList<myList> mylist=new ArrayList<>();
						MyAdapter myadapter=new MyAdapter(getContext(),mylist);
						for(int i=0;i<ja.length();i++){
							myList kl=new myList(ja.getJSONObject(i).getString("KinoAdirisi"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoNami"));
							mylist.add(kl);	
						}
						recyclerView.setAdapter(myadapter);
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	}
