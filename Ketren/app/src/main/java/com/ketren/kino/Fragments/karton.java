package com.ketren.kino.Fragments;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import cn.studyou.library.view.BannerLayout;
import com.ketren.kino.Base64Coder;
import com.ketren.kino.HttpUtils;
import com.ketren.kino.Lists.kartonList;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import com.ketren.kino.U;
import com.ketren.kino.kisim;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import com.ketren.kino.Adapters.kartonAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
public class karton extends Fragment
{

	private View view;
	private static  ArrayList<kartonList> msgList;
	private static kartonAdapter mAdapter;
    private String ilan;
	private static AlertDialog dlg;
	static int SAN[]={0,0,0};
	//private ImageButton ib;
	private static BannerLayout banner;
	private static String Ghol;

	private static RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.kino,container,false);

		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

 
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
							Okux(getContext());
							pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 500);
				}

				@Override
				public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
				{


					new Handler()
					{
						@Override
						public void handleMessage(Message msg)
						{

							ReFresh();
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 2500);
				}
			});
		banner = (BannerLayout) view.findViewById(R.id.banner);

		banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener(){

				@Override
				public void onItemClick(int position)
				{
					try{
						if(Ghol!=null){
							String s=Ghol;
							s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							String kinonami=ja.getJSONObject(position).getString("KinoNami");
							String kinorasimi=ja.getJSONObject(position).getString("KinoRasimi");
							String kinonquxandurilixi=ja.getJSONObject(position).getString("KinoQuxandurilixi");
							String kinoid=ja.getJSONObject(position).getString("id");
							Intent i=new Intent();
							i.setClass(getContext(),kisim.class);
							i.putExtra("KinoNami",kinonami);
							i.putExtra("id",kinoid);
							i.putExtra("KinoRasimi",kinorasimi);
							i.putExtra("KinoQuxandurilixi",kinonquxandurilixi);
							startActivity(i);
						}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
					}
				}
			});
        return view;
    }
	public static void Okux(final Context c){
		show(c);
		HttpUtils.httpRequest((Activity)c,U.url+"kino.php?type=baxbat","POST=ENTER&turi="+Base64Coder.encodeString("where KinoTuri='karton'"),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
	                    Ghol=s;
						banner(c);
						if(s!=null){
							msgList = new ArrayList<>();
							mAdapter = new kartonAdapter(msgList,c);
							s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							SAN[0]=ja.length();
							int kimmat=0;
							if(SAN[1]+15<=ja.length()){
								SAN[1]=SAN[1]+15;
								kimmat=15;
							}else if(SAN[1]+9<=ja.length()){
								SAN[1]=SAN[1]+9;
								kimmat=9;
							}else if(SAN[1]+6<=ja.length()){
								SAN[1]=SAN[1]+6;
								kimmat=6;
							}else if(SAN[1]+3<=ja.length()){
								SAN[1]=SAN[1]+3;
								kimmat=3;
							}else if(SAN[1]+2<=ja.length()){
								SAN[1]=SAN[1]+2;
								kimmat=2;
							}else if(SAN[1]+1<=ja.length()){
								SAN[1]=SAN[1]+1;
								kimmat=1;
							}

							for(int i=SAN[1]-kimmat;i<SAN[1];i++){
								kartonList msg1 = new kartonList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
								msgList.add(msg1);
							}
							recyclerView.setAdapter(mAdapter);
							dlg.dismiss();
							/*
							recyclerView.setOnItemClickListener(new OnItemClickListener(){

									@Override
									public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
									{
										Intent i=new Intent();
										i.setClass(c,kisim.class);
										i.putExtra("KinoNami",msgList.get(p3).getKinoNami());
										i.putExtra("id",msgList.get(p3).getKinoID());
										i.putExtra("KinoRasimi",msgList.get(p3).getkasma());
										i.putExtra("KinoKoyulixi",msgList.get(p3).getKinoKoyulixi());
										i.putExtra("KinoQuxandurilixi",msgList.get(p3).getKinoQuxandurulixi());
										c.startActivity(i);
									}



								});
								*/
						}else{
							dlg.dismiss();
						}
					}catch(Exception e){
						new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public static void  show(Context c){
		dlg= new Builder(c,R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	}



	public static void banner(Context c)    {
		try{
			List<String> urls = new ArrayList<>();
			String s=Ghol;
			if(Ghol!=null){
				s=Base64Coder.decodeString(s);
				JSONArray ja=new JSONArray(s);
				for(int i=0;i<6;i++){
					urls.add(ja.getJSONObject(i).getString("KinoRasimi"));
				}
				banner.setViewUrls(urls);
			}
		}catch(Exception e){
			new AlertDialog.Builder(c).setMessage(e.getMessage()).show();
		}
	}
	public void ReFresh(){
		String s=Ghol;
		try{
			if(s!=null){
				s=Base64Coder.decodeString(s);
				JSONArray ja=new JSONArray(s);
				int kimmat=0;
				if(SAN[1]+12<ja.length()){
					SAN[1]=SAN[1]+12;
					kimmat=12;
				}else if(SAN[1]+9<=ja.length()){
					SAN[1]=SAN[1]+9;
					kimmat=9;
				}else if(SAN[1]+6<=ja.length()){
					SAN[1]=SAN[1]+6;
					kimmat=6;
				}else if(SAN[1]+3<=ja.length()){
					SAN[1]=SAN[1]+3;
					kimmat=3;
				}else if(SAN[1]+2<=ja.length()){
					SAN[1]=SAN[1]+2;
					kimmat=2;
				}else if(SAN[1]+1<=ja.length()){
					SAN[1]=SAN[1]+1;
					kimmat=1;
				}
				for(int i=SAN[1]-kimmat;i<SAN[1];i++){
					kartonList msg1 = new kartonList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
					msgList.add(msg1);
				}
				recyclerView.setAdapter(mAdapter);
				mAdapter.notifyDataSetChanged();
				dlg.dismiss();
				/*recyclerView.setOnItemClickListener(new OnItemClickListener(){

						@Override
						public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
						{
							Intent i=new Intent();
							i.setClass(getContext(),kisim.class);
							i.putExtra("KinoNami",msgList.get(p3).getKinoNami());
							i.putExtra("id",msgList.get(p3).getKinoID());
							i.putExtra("KinoRasimi",msgList.get(p3).getkasma());
							i.putExtra("KinoKoyulixi",msgList.get(p3).getKinoKoyulixi());
							i.putExtra("KinoQuxandurilixi",msgList.get(p3).getKinoQuxandurulixi());
							startActivity(i);
						}



					});
					*/
			}else{
				dlg.dismiss();

			}}catch(Exception e){
			new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
		}}

}
