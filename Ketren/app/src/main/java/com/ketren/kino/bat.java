package com.ketren.kino;
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
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import com.ketren.kino.adapter.MyAdapter;
import com.ketren.kino.adapter.MyList;
import com.ketren.kino.kisim;
import java.util.ArrayList;
import org.json.JSONArray;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
public class bat extends Activity
{
	private ArrayList<MyList> msgList;
	private MyAdapter mAdapter;
    private String ilan;
	private AlertDialog dlg;
	int SAN[]={0,0,0};
	private ImageButton ib;
	private String turi;

	private String Ghol;

	private RecyclerView recyclerView;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
				setContentView(R.layout.bat);
				try{
		show();
					recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
					recyclerView.setHasFixedSize(true);
					recyclerView.setNestedScrollingEnabled(false);
					recyclerView.setItemViewCacheSize(200);
					RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
					recyclerView.setRecycledViewPool(recycledViewPool);
					recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

					
					Intent in=getIntent();
					String nami=in.getStringExtra("nami");
					TextView tv=findViewById(R.id.title);
					tv.setText(nami);
					turi=in.getStringExtra("type");
		Okux();
		Get_Ilan();
		((PullToRefreshLayout)findViewById(R.id.refresh_view))
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
					}.sendEmptyMessageDelayed(0, 4500);
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
		ib=(ImageButton)findViewById(R.id.ilan);
		ib.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse(ilan); 
					Intent intent =new Intent(Intent.ACTION_VIEW, uri); 
					startActivity(intent);        
				}
			});
		
		 
				}catch(Exception e){
					new AlertDialog.Builder(this).setMessage(e.getMessage()).show();
				}}
	public void Okux(){
		HttpUtils.httpRequest(this,U.url+"kino.php?type=baxbat","POST=ENTER&&turi="+Base64Coder.encodeString(turi),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
					Ghol=s;
						if(s!=null){
							s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							SAN[0]=ja.length();
							int kimmat=0;
								msgList = new ArrayList<>();
								mAdapter = new MyAdapter(msgList,bat.this);
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
							MyList msg1 = new MyList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
								msgList.add(msg1);
							}
							dlg.dismiss();
							recyclerView.setAdapter(mAdapter);
						}else{
							dlg.dismiss();
						}
					}catch(Exception e){
						new AlertDialog.Builder(bat.this).setMessage(e.getMessage()).show();

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
				}else if(SAN[1]+9<ja.length()){
					SAN[1]=SAN[1]+9;
					kimmat=9;
				}else if(SAN[1]+6<ja.length()){
					SAN[1]=SAN[1]+6;
					kimmat=6;
				}else if(SAN[1]+3<ja.length()){
					SAN[1]=SAN[1]+3;
					kimmat=3;
				}else if(SAN[1]+2<ja.length()){
					SAN[1]=SAN[1]+2;
					kimmat=2;
				}else if(SAN[1]+1<ja.length()){
					SAN[1]=SAN[1]+1;
					kimmat=1;
				}
				for(int i=SAN[1]-kimmat;i<SAN[1];i++){
					MyList msg1 = new MyList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
					msgList.add(msg1);
					
				}
				dlg.dismiss();
				recyclerView.setAdapter(mAdapter);
			}else{
				dlg.dismiss();

			}}catch(Exception e){
			new AlertDialog.Builder(bat.this).setMessage(e.getMessage()).show();
		}}
	public void  show(){
		dlg= new Builder(bat.this,R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	}

	public void Get_Ilan(){
		HttpUtils.httpRequest((Activity)bat.this,U.url+"ilan.php","",new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							JSONArray jo=new JSONArray("["+s+"]");
							ilan=new JSONArray(jo.getString(0)).getString(0);
							get_ilan_image(new JSONArray(jo.getString(0)).getString(1));
						}
					}catch(Exception e){
						new AlertDialog.Builder(bat.this).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public   void get_ilan_image(final String adris){

		HttpUtils.getImage((Activity)bat.this,adris,new Handler(){
				@Override
				public void handleMessage( Message msg) {
					try{
						Bitmap INIKLAX=(Bitmap) msg.obj;
						if(INIKLAX!=null){
							ib.setBackground(new BitmapDrawable(INIKLAX));
						}
					}catch(Exception e){

					}};});
	}}
				
