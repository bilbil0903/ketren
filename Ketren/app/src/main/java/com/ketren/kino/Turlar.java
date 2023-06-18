package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import com.ketren.kino.adapter.MyAdapter;
import com.ketren.kino.adapter.MyList;
import com.ketren.kino.kisim;
import java.util.ArrayList;
import org.json.JSONArray;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
public class Turlar extends Activity
{
	private ArrayList<MyList> msgList;
	private MyAdapter mAdapter;
    private String ilan;
	private AlertDialog dlg;
	int SAN[]={0,0,0};
	private ImageButton ib;
	String vip[]={"where vip=1","where vip=0  "," where tur2!=10 "};
	String til[]={" and KinoTili='oztil'  "," and KinoTili='uyghur'  ",""};
String wakit[]={" and time=2014 "," and time=2015  "," and time=2016  "," and time=2017  "," and  time=2018  "," and time=2019  "," and time=2020  "," and time=2021   ",""};
	String  rayun[]={" and tur2=8 "," and tur2=7 "," and tur2=3 "," and tur2=4 "," and tur2=1 ",""};
	String tur[]={" and KinoTuri='nahxa' "," and KinoTuri='mtv' "," and KinoTuri='karton' "," and KinoTuri='kino' ",""};
	String tur_s[]={"ناخشا","MTV","كارتون","كىنو","بارلىق"};
	String vip_s[]={"ھەقلىق","ھەقسىز","بارلىق"};
	String til_s[]={"ئ‍ۆزتىل","ئ‍ۇيغۇرچە","بارلىق"};
	String wakit_s[]={"2014","2015","2016","2017","2018","2019","2020","2021","بارلىق"};
	String rayun_s[]={"شىنجاڭ","ھىندىستان","كورىيە","جۇڭگۇ","ئ‍امرىكا","بارلىق"};
	
	private String Ghol;
	RadioGroup rg1,rg2,rg3,rg4,rg5;

	private RadioButton r;

	private RecyclerView recyclerView;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.turlar);
	try{
		rg1=findViewById(R.id.rg1);
		rg2=findViewById(R.id.rg2);
		rg3=findViewById(R.id.rg3);
		rg4=findViewById(R.id.rg4);
		rg5=findViewById(R.id.rg5);
		ijra_radio(rg1,vip,vip_s);
		ijra_radio(rg2,til,til_s);
		ijra_radio(rg3,wakit,wakit_s);
		ijra_radio(rg4,rayun,rayun_s);
		ijra_radio(rg5,tur,tur_s);
		Okux(get_tag());
			rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(RadioGroup p1, int p2) {
					SAN[0]=0;
					SAN[1]=0;
					Okux(get_tag());
						
					}
				});
			rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(RadioGroup p1, int p2) {
					SAN[0]=0;
					SAN[1]=0;
					Okux(get_tag());
					}
				});
			rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(RadioGroup p1, int p2) {
					SAN[0]=0;
					SAN[1]=0;
					Okux(get_tag());
					}
				});
			rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(RadioGroup p1, int p2) {
					SAN[0]=0;
					SAN[1]=0;
					Okux(get_tag());
					}
				});
		rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(RadioGroup p1, int p2) {
					SAN[0]=0;
					SAN[1]=0;
					Okux(get_tag());
				}
			});
		recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setItemViewCacheSize(200);
		RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
		recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		
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
								Okux(get_tag());
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
	public void Okux(String pram){
		show();
		HttpUtils.httpRequest(this,U.url+"kino.php?type=baxbat","POST=ENTER&&turi="+Base64Coder.encodeString(pram),new Handler() {
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
							mAdapter = new MyAdapter(msgList,Turlar.this);
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
							recyclerView.setAdapter(mAdapter);
							dlg.dismiss();
							
						}else{
							dlg.dismiss();
						}
					}catch(Exception e){
						dlg.dismiss();
						new AlertDialog.Builder(Turlar.this).setMessage(e.getMessage()).show();

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
			new AlertDialog.Builder(Turlar.this).setMessage(e.getMessage()).show();
		}}
	public void  show(){
		dlg= new Builder(Turlar.this,R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	}

	public void Get_Ilan(){
		HttpUtils.httpRequest((Activity)Turlar.this,U.url+"ilan.php","",new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							JSONArray jo=new JSONArray("["+s+"]");
							ilan=new JSONArray(jo.getString(0)).getString(0);
							get_ilan_image(new JSONArray(jo.getString(0)).getString(1));
						}
					}catch(Exception e){
						new AlertDialog.Builder(Turlar.this).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public   void get_ilan_image(final String adris){

		HttpUtils.getImage((Activity)Turlar.this,adris,new Handler(){
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
public void ijra_radio(RadioGroup rg,String[] s1,String[] s2){
	int san=s1.length;
	for(int i=0;i<san;i++){
		RadioButton rb=new RadioButton(Turlar.this);
		rb.setText(s2[i]);
		rb.setBackgroundResource(R.drawable.rb_bg);
		rb.setButtonDrawable(null);
		rb.setTextColor(Color.WHITE);
		rb.setPadding(5,2,5,2);
		rb.setTextSize(10l);
		rb.setTypeface(Typeface.createFromAsset(this.getAssets(),"UyghurFont.ttf"));
		RadioGroup.LayoutParams lp= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
									RadioGroup.LayoutParams.WRAP_CONTENT, 1f);
		
		lp.setMargins(6,5,6,5);
		rb.setGravity(Gravity.CENTER);
		rb.setLayoutParams(lp);
		rb.setTag(s1[i]);
		rg.addView(rb);
	}
	if(rg==rg1||rg==rg2){
		 r=(RadioButton)rg.getChildAt(2);
		rg.check(3);
	}else if(rg==rg3){
		r=(RadioButton)rg.getChildAt(8);
		rg.check(9);
	}else if(rg==rg4){
		rg.check(6);
		r=(RadioButton)rg.getChildAt(5);
	}else if(rg==rg5){
		rg.check(5);
		r=(RadioButton)rg.getChildAt(4);
	}
	r.setChecked(true);
	}
					
					
	public String get_tag(){
		RadioButton r1=(RadioButton)findViewById(rg1.getCheckedRadioButtonId());
		RadioButton r2=(RadioButton)findViewById(rg2.getCheckedRadioButtonId());
		RadioButton r3=(RadioButton)findViewById(rg3.getCheckedRadioButtonId());
		RadioButton r4=(RadioButton)findViewById(rg4.getCheckedRadioButtonId());
		RadioButton r5=(RadioButton)findViewById(rg5.getCheckedRadioButtonId());
		String tag=(r1.getTag().toString()+r2.getTag().toString()+r3.getTag().toString()+r4.getTag().toString()+r5.getTag().toString())+" and KinoTuri!='kiska'";
		return tag;
	}
}
