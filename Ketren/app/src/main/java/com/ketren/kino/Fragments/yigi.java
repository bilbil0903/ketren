package com.ketren.kino.Fragments;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import cn.studyou.library.view.BannerLayout;
import com.ketren.kino.Adapters.YigiAdapter;
import com.ketren.kino.Base64Coder;
import com.ketren.kino.HttpUtils;
import com.ketren.kino.Lists.yigiList;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import com.ketren.kino.U;
import com.ketren.kino.bat;
import com.ketren.kino.kisim;
import com.ketren.kino.tizimlitix;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
public class yigi extends Fragment implements OnClickListener
{
	private View view;
	private ArrayList<yigiList> msgList;
	private YigiAdapter mAdapter;
	//private GridView gw;
	private String ilan;
	private AlertDialog dlg;
	//private ImageButton ib;
	private String Ghol;
	int SAN[]={0,0,0};
	Button kino,karton,daris,tv,zonghe;

	
	private RecyclerView recyclerView;

	private BannerLayout banner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		try{
		view = inflater.inflate(R.layout.yigi,container,false);
		show();
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(100);
		
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
         Okux();
    
	   msgList = new ArrayList<>();
		mAdapter = new YigiAdapter(msgList,getContext());
		
		//Get_Ilan();
		if(tizimlitix.get_login((Activity)getContext())!=null){
	   AND();
	   }
		//ib=(ImageButton)view.findViewById(R.id.ilan);
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
							
							Okux();
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
							ReFresh();
						
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						}
					}.sendEmptyMessageDelayed(0, 500);
				}
			});
	/*	ib.setOnClickListener(new View.OnClickListener(){

       

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse(ilan); 
					Intent intent =new Intent(Intent.ACTION_VIEW, uri); 
					startActivity(intent);        
				}
			});
		*/
        banner = (BannerLayout) view.findViewById(R.id.banner);
		kino=view.findViewById(R.id.kino);
		karton=view.findViewById(R.id.karton);
		daris=view.findViewById(R.id.daris);
		tv=view.findViewById(R.id.tv);
		zonghe=view.findViewById(R.id.zonghe);
		kino.setOnClickListener(this);
		karton.setOnClickListener(this);
		daris.setOnClickListener(this);
		tv.setOnClickListener(this);
		zonghe.setOnClickListener(this);
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
       
    }catch(Exception e){
		new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
	}
	return view;
	}
	public void Okux(){
		HttpUtils.httpRequest((Activity)getContext(), U.url+"kino.php?type=baxbat", "POST=ENTER&turi="+Base64Coder.encodeString("where KinoTuri!='daris' and KinoTuri!='kiska' and KinoTuri!='daris'"), new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						Ghol=s;
						if(s!=null){
							s=Base64Coder.decodeString(s);
						JSONArray ja=new JSONArray(s);
						 int kimmat=0;
						if(SAN[1]+12<=ja.length()){
							SAN[1]=SAN[1]+12;
							kimmat=12;
						}else if(SAN[1]+9<=ja.length()){
							SAN[1]=SAN[1]+9;
							kimmat=9;
						}else if(SAN[1]+6<=ja.length()){
							SAN[1]=SAN[1]+6;
							kimmat=6;
						}else if(SAN[1]+4<=ja.length()){
							SAN[1]=SAN[1]+4;
							kimmat=3;
						}else if(SAN[1]+2<=ja.length()){
							SAN[1]=SAN[1]+2;
							kimmat=2;
						}else if(SAN[1]+1<=ja.length()){
							SAN[1]=SAN[1]+1;
							kimmat=1;
						}
							banner();
				
						msgList = new ArrayList<>();
						mAdapter = new YigiAdapter(msgList,getContext());
						for(int i=SAN[1]-kimmat;i<SAN[1];i++){
							yigiList msg1 = new yigiList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
							msgList.add(msg1);
						}
						recyclerView.setAdapter(mAdapter);
                        dlg.dismiss();
						/*
						gw.setOnItemClickListener(new OnItemClickListener(){

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
							}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
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
								yigiList msg1 = new yigiList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
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
	
	public void  show(){
		dlg= new Builder(getContext(),R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	}
	/*
	public void Get_Ilan(){
		HttpUtils.httpRequest((Activity)getContext(), U.url+"ilan.php", "", new Handler() {

				
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							JSONArray jo=new JSONArray("["+s+"]");
							ilan=new JSONArray(jo.getString(2)).getString(0);
							get_ilan_image(new JSONArray(jo.getString(2)).getString(1));
						}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	
	public   void get_ilan_image(final String adris){
		HttpUtils.getImage((Activity)getContext(),adris,new Handler(){
				@Override
				public void handleMessage( Message msg) {
					try{
						Bitmap INIKLAX=(Bitmap) msg.obj;
						if(INIKLAX!=null){
							ib.setBackground(new BitmapDrawable(INIKLAX));
						}
					}catch(Exception e){

					}};});
	}
	*/
	public   void AND(){
		String phone = android.os.Build.BRAND;
		String naxir = android.os.Build.VERSION.RELEASE;
		HttpUtils.httpRequest((Activity)getContext(),"http://www.bilbil.top/kino.php?type=AND","POST=ENTER&&android="+tizimlitix.get_login((Activity)getContext())+"&&phone="+phone+"/"+naxir,new Handler(){
				@Override
				public void handleMessage( Message msg) {
					};});
	}
	

	@Override
	public void onClick(View p1){
		if(p1==kino){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("turi","kino");
			i.putExtra("nami","كىنو");
			i.putExtra("type","read");
			startActivity(i);
		}else if(p1==daris){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("turi","daris");
			i.putExtra("nami","دەرىس");
			i.putExtra("type","read");
			startActivity(i);
		}else if(p1==karton){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("turi","karton");
			i.putExtra("nami","كارتون");
			i.putExtra("type","read");
			startActivity(i);
		}else if(p1==tv){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("turi","tv");
			i.putExtra("type","read");
			i.putExtra("nami","تېلىۋېزور");
			startActivity(i);
		}else if(p1==zonghe){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("turi","zonghe");
			i.putExtra("type","read");
			i.putExtra("nami","ئونۋېرسال");
			startActivity(i);
		}
		
	}
	public  void banner()    {
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
			new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
		}
	}
	}
