package com.ketren.kino;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.ketren.kino.Nahxa.NahxaAdapter;
import com.ketren.kino.Nahxa.NahxaList;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import java.util.ArrayList;
import org.json.JSONArray;
import android.widget.Toast;
public class nahxa extends Fragment 
{
	
	private ArrayList<NahxaList> msgList;
	private NahxaAdapter mAdapter;
	private View view;
	private RecyclerView recyclerView;
boolean isFirst =true;
	private String Ghol;
	int SAN[]={0,0,0};
	private AlertDialog dlg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.nahxa,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(100);
		// RecyclerView可以设置自己所需要的ViewHolder数量
        recyclerView.setItemViewCacheSize(20);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
		show();
		Refresh();
		((PullToRefreshLayout)view.findViewById(R.id.refresh_view))
			.setOnRefreshListener(new OnRefreshListener(){

				@Override
				public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
				{   
					new Handler(){
						@Override
						public void handleMessage(Message msg)
						{
							SAN[1]=0;
							Refresh();
							pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 1000);
				}

				@Override
				public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
				{

					new Handler()
					{
						@Override
						public void handleMessage(Message msg)
						{
							Load();
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 500);
				}
			});
	return view;
	}
	public void Refresh(){
		HttpUtils.httpRequest((Activity)getContext(), "http://bilbil.top/kino.php?type=nahxa", "POST=ENTER", new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(mAdapter!=null){
							//if(mAdapter.mMediaPlayer.isPlaying()){
								mAdapter.stop();
						}
						Ghol=s;
						if(s!=null){
						JSONArray ja=new JSONArray(s);
						msgList = new ArrayList<>();
						mAdapter = new NahxaAdapter(msgList,getContext());
							for(int i=0;i<60;i++){
							NahxaList msg1 = new NahxaList(ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("nahxa"),ja.getJSONObject(i).getString("adiris"),ja.getJSONObject(i).getString("nahxiqi"),ja.getJSONObject(i).getString("see"));
							msgList.add(msg1);
						}
						
						dlg.dismiss();
						
						recyclerView.setAdapter(mAdapter);
}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public void Load(){
		try{
						String s=Ghol;
			if(mAdapter!=null){
				//if(mAdapter.mMediaPlayer.isPlaying()){
				mAdapter.stop();
			}
						if(s!=null){
							JSONArray ja=new JSONArray(s);
							int kimmat=0;
							if(SAN[1]+60<=ja.length()){
								SAN[1]=SAN[1]+60;
								kimmat=60;
							}else if(SAN[1]+30<=ja.length()){
								SAN[1]=SAN[1]+30;
								kimmat=30;
							}else if(SAN[1]+15<=ja.length()){
								SAN[1]=SAN[1]+15;
								kimmat=15;
							}else if(SAN[1]+10<=ja.length()){
								SAN[1]=SAN[1]+10;
								kimmat=10;
							}else if(SAN[1]+5<=ja.length()){
								SAN[1]=SAN[1]+5;
								kimmat=5;
							}else if(SAN[1]+1<=ja.length()){
								SAN[1]=SAN[1]+1;
								kimmat=1;
							}
							//msgList = new ArrayList<>();
							//mAdapter = new NahxaAdapter(msgList,getContext());
							for(int i=SAN[1]-kimmat;i<SAN[1];i++){
								NahxaList msg1 = new NahxaList(ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("nahxa"),ja.getJSONObject(i).getString("adiris"),ja.getJSONObject(i).getString("nahxiqi"),ja.getJSONObject(i).getString("see"));
								msgList.add(msg1);
							}
							recyclerView.setAdapter(mAdapter);
						}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

	
	
	
	public void  show(){
		dlg= new Builder(getContext(),R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	
}
	}
