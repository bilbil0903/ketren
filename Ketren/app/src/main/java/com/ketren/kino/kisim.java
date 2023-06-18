package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.dl7.player.media.IjkPlayerView;
import com.ketren.kino.Inkas.inkasAdapter;
import com.ketren.kino.Inkas.inkasList;
import com.ketren.kino.Tawsiya.TawsiyaAdapter;
import com.ketren.kino.Tawsiya.TawsiyaList;
import com.squareup.picasso.Picasso;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import com.dl7.player.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import static tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener;
import com.dl7.player.media.MediaPlayerParams;
public class kisim extends AppCompatActivity implements OnInfoListener
{

	@Override
	public boolean onInfo(IMediaPlayer p1, int p2, int p3) {
		
		
		if(336==p2){
			p1.pause();
		}
		return false;
	}
	
	

	
	private ArrayList<TawsiyaList> msgList;
	private TawsiyaAdapter mAdapter;
	
	private ArrayList<inkasList> msgList2;
	private inkasAdapter mAdapter2;
	private String ilan;
	private static RadioGroup rg;
	private ImageButton ib;
	public  IjkPlayerView mPlayerView;
	private String nami;
	private RecyclerView recyclerView,recyclerView2;
	private  int SAN=0;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    try{
		//uqup kalmaslik
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        setContentView(R.layout.kisim);
		mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
		rg=(RadioGroup)findViewById(R.id.rg);
		recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		recyclerView2= (RecyclerView)findViewById(R.id.inkas_list);
		recyclerView2.setHasFixedSize(true);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setItemViewCacheSize(200);
		RecyclerView.RecycledViewPool recycledViewPool2= new RecyclerView.RecycledViewPool();
        recyclerView2.setRecycledViewPool(recycledViewPool2);
		recyclerView2.setLayoutManager(new GridLayoutManager(this, 1));
		rg=(RadioGroup)findViewById(R.id.rg);
		Okux(this);
		GET_INKAS(this);
		Intent in=getIntent(); 
		String jieshao=in.getStringExtra("KinoQuxandurilixi");
		 nami=in.getStringExtra("KinoNami");
		String RasimAdirisi=in.getStringExtra("KinoRasimi");
		Kisim(this);
		TextView t=(TextView)findViewById(R.id.jieshao);
		t.setText(jieshao);
		Get_Ilan();
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
		
		Picasso.with(this).load(RasimAdirisi).fit().into(mPlayerView.mPlayerThumb);
		}catch(Exception e){
		// new AlertDialog.Builder((Context)this).setMessage(e.getMessage().toString()+"/").show();
		  
		}}
	public static void See(final Activity c){
		Intent m=c.getIntent();
		String id=m.getStringExtra("id");
      String  user=tizimlitix.get_login((Activity)c);
		HttpUtils.httpRequest(c,U.url+"kino.php?type=see","POST=ENTER&&id="+id+"&&user="+user,new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						
					}catch(Exception e){
						//new AlertDialog.Builder((Context)c).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public void Kisim(final Activity c){
		Intent m=c.getIntent();
		final String id=m.getStringExtra("id");
		
		HttpUtils.httpRequest(c,U.url+"kino.php?type=kisim","POST=ENTER&&id="+id+"&&android="+tizimlitix.get_login((Activity)c),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String mazmun=(String)msg.obj;
					  int mi=Integer.parseInt(mazmun);
					int  ii=  get_C(Integer.parseInt(id),c);
					
					int SS=0;
					if(mi!=-1){
						if(ii!=0){
							SS=ii;
							SAN=ii;
						}
						if(mi>0){
						for(int i=0;i<mi;i++){
							RadioButton rb=new RadioButton(c);
							rb.setText(""+(i+1));
							rb.setTag((int)i);
							rb.setBackgroundResource(R.drawable.rb_bg);
							rb.setButtonDrawable(null);
							rb.setTextColor(Color.WHITE);
							rb.setTextSize(20l);
							LayoutParams lp=new LayoutParams();
							lp.setMargins(5,5,5,5);
							rb.setGravity(Gravity.CENTER);
							rb.setWidth(120);
							rb.setLayoutParams(lp);
							rg.addView(rb);
						}
							play_get(id+","+SS,c);
							rg.check((SS+1));
							RadioButton r=(RadioButton)rg.getChildAt(SS);
							r.setChecked(true);
							rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
									@Override
									public void onCheckedChanged(RadioGroup p1, int p2) {
									RadioButton rb=(RadioButton)findViewById(p2);
									//rb.setBackgroundResource(R.drawable.play_kok);
									//new AlertDialog.Builder(kisim.this).setMessage(""+rb.getTag()).show();
									mPlayerView.reset();
									play_get(id+","+(((int)rb.getTag())),c);
									SAN=Integer.parseInt(rb.getTag().toString());
									}
								});
								
				}
						}
						
					}catch(Exception e){
						//new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 
			
	}
	public  void Kisim2(final String ID){
		SAN=SAN+2;
		HttpUtils.httpRequest(this,U.url+"kino.php?type=kisim","POST=ENTER&&id="+ID+"&&android="+tizimlitix.get_login((Activity)this),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String mazmun=(String)msg.obj;
						int mi=Integer.parseInt(mazmun);
						if(SAN<mi){
							rg.check(SAN);
							RadioButton r=(RadioButton)rg.getChildAt(SAN);
							r.setChecked(true);
							Toast.makeText(kisim.this,"iiiil!",0).show();
				        // 	play_get(ID+","+SAN,c);
								}else{
							man.TOAST(kisim.this,"كىنو تۈگىدى!");
						}

					}catch(Exception e){
						//new AlertDialog.Builder(c).setMessage("E:"+e.getMessage()).show();

					}}

			}); 

	}
	
	
	public static Bitmap openImage(String path){
		Bitmap bitmap = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
			bitmap = BitmapFactory.decodeStream(bis);
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return bitmap;
	}
	
	public void Get_Ilan(){
		HttpUtils.httpRequest((Activity)this, U.url+"ilan.php", "", new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							JSONArray jo=new JSONArray("["+s+"]");
							ilan=new JSONArray(jo.getString(3)).getString(0);
							get_ilan_image(new JSONArray(jo.getString(3)).getString(1));
						}
					}catch(Exception e){
						//new AlertDialog.Builder(kisim.this).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public   void get_ilan_image(final String adris){
		HttpUtils.getImage((Activity)this,adris,new Handler(){
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
	
	
	public static void save_kino(String title,String rasim,int wakit,String id,int kisim,Activity A){
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS kalduk(san int auto_increment primary key,title varchar,rasim varchar,id integer,wakit integer,kisim integer)");
			if(can(Integer.parseInt(id),A)){
			sql.execSQL("INSERT INTO kalduk VALUES(null,'"+title+"','"+rasim+"','"+id+"','"+wakit+"','"+kisim+"');");
		}else{
			sql.execSQL("update kalduk set wakit='"+wakit+"' where id="+id+"");
			sql.execSQL("update kalduk set kisim='"+kisim+"' where id="+id+"");
		}
	}
	
	public  static  boolean can(int id,Activity A){
		boolean b=false;
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS kalduk(san int auto_increment primary key,title varchar,rasim varchar,id integer,wakit integer,kisim integer)");
		Cursor	c=sql.rawQuery("select*from kalduk where id="+id+"",null);
		if(c.getCount()==0){
		b=true;
			}else{
				b=false;
		}
		return b;
	}

	public  static  int get_C(int id,Activity A){
		int i=0;
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS kalduk(san int auto_increment primary key,title varchar,rasim varchar,id integer,wakit integer,kisim integer)");
		Cursor	c=sql.rawQuery("select*from kalduk where id="+id+"",null);
		if(c.getCount()!=0){
		c.moveToFirst();
		 i= c.getInt(c.getColumnIndex("kisim"));
		 }
		 return i;
		}
	public  static  int get_C2(int id,int kisim,Activity A){
		int i=0;
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS kalduk(san int auto_increment primary key,title varchar,rasim varchar,id integer,wakit integer,kisim integer)");
		Cursor	c=sql.rawQuery("select wakit from kalduk where id="+id+" and kisim="+kisim+"",null);
		if(c.getCount()!=0){
			c.moveToFirst();
			i= c.getInt(c.getColumnIndex("wakit"));
		}
		return i;
	}
	public  void  play(String url,int i,Activity a){
		Intent m=a.getIntent();
		String id=m.getStringExtra("id");
		String nami=m.getStringExtra("KinoNami");
		mPlayerView.init()
			.setTitle(nami)
			.setkisim(i)
			.setID(id)
			.setVideoPath(url);
			mPlayerView.start();
		 int ii2= get_C2(Integer.parseInt(id),SAN,a);
		 if(ii2!=0){
			 mPlayerView.seekTo(ii2);
		 }
		mPlayerView.setOnInfoListener(this);
	}
	
	@Override
    protected void onResume() {
        super.onResume();
		if(!mPlayerView.isPlaying()){
        mPlayerView.start();
		}
    }

    @Override
    protected void onPause() {
        super.onPause();
       mPlayerView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
		try{
		Intent in=getIntent(); 
		int wakit=mPlayerView.getCurPosition();
		nami=in.getStringExtra("KinoNami");
		String RasimAdirisi=in.getStringExtra("KinoRasimi");
		String id=in.getStringExtra("id");
		save_kino(nami,RasimAdirisi,wakit,id,SAN,this);
		mPlayerView.stop();
		mPlayerView.setOnInfoListener(null);
		}catch(Exception e){
			//Toast.makeText(this,""+e.getMessage(),0).show();
			}
		
    }

 

  
    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
		if(mPlayerView!=null){
		mPlayerView.stop();
		}
        super.onBackPressed();
    }
	public  void play_get(String id,final Activity a){
		final String st=id.split(",")[1];
		//new AlertDialog.Builder(a).setMessage(id).show();
		
		HttpUtils.httpRequest(a,U.url+"kino.php?type=get_kisim","POST=ENTER&&id="+id+"&&android="+tizimlitix.get_login((Activity)a),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String mazmun=(String)msg.obj;
						See(a);
				//new AlertDialog.Builder(a).setMessage(Base64Coder.decodeString(mazmun)).show();
						if(mazmun!=null&&!mazmun.equals("NONE_VIP")){
							play(Base64Coder.decodeString(mazmun),Integer.parseInt(st),a);
						}else if(mazmun.equals("NONE_VIP")){
							man.Dialog(kisim.this,"كەچۈرۈڭ،ئەزالىق سېتىۋالمىسىڭىز كۆرەلمەيسىز!",true);
							mPlayerView.stop();
						}
					}catch(Exception e){
						//new AlertDialog.Builder(a).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public  void Okux(final Context c){
		if(msgList!=null){
			msgList.clear();
		}
		HttpUtils.httpRequest((Activity)c, U.url + "kino.php?type=tawsiya", "POST=ENTER", new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							msgList = new ArrayList<>();
							mAdapter = new TawsiyaAdapter(msgList,c);
							s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							for(int i=0;i<ja.length();i++){
								TawsiyaList msg1 = new TawsiyaList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
								msgList.add(msg1);
							}
							recyclerView.setAdapter(mAdapter);
							
						}
					}catch(Exception e){
						//new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public void share(View v){
		Intent m=this.getIntent();
		String id=m.getStringExtra("id");
		nami=m.getStringExtra("KinoNami");
		StartShareApp(kisim.this, "ھەمبەھىرلەيدىغان يەرنى تاللاڭ", "","http://bilbil.top/kino/m/mazmun.php?hid="+id+"\n\nكىنو نامى: "+nami+""+"\n\n"+"http://bilbil.top/ketren.apk");
	}
	static public void StartShareApp(Context context,
									 final String szChooserTitle, final String title, final String msg) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, msg);
		context.startActivity(Intent.createChooser(intent, szChooserTitle));
	}
	
	public  void send(View v){
		String an=android.os.Build.BRAND+" "+android.os.Build.MODEL+" "+Build.VERSION.RELEASE;
		Intent m=getIntent();
		String id=m.getStringExtra("id");
		final EditText ik=(EditText) findViewById(R.id.inkas);
		man.TOAST(this,"سەل ساقلاڭ!");
      String  user=tizimlitix.get_login((Activity)this);
		if(user!=null&&user!=""){
			if(ik.getText().toString().length()>5){
	  HttpUtils.httpRequest(this,U.url+"kino.php?type=inkas","POST=ENTER&&id="+id+"&&user="+user+"&&inkas="+Base64Coder.encodeString(ik.getText().toString())+"&&android="+an,new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						man.TOAST(kisim.this,"يوللاندى!");
						ik.setText("");
					}catch(Exception e){
						//new AlertDialog.Builder((Context)c).setMessage(e.getMessage()).show();

					}}

			}); 
			}else{
				man.TOAST(kisim.this,"خەتنى يىتەرلىك يېزىڭ!");
			}
		}else{
			man.TOAST(kisim.this,"كىرىڭ");
			Intent i=new Intent();
			i.setClass(this,tizimlitix.class);
			startActivity(i);
		}
	}
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }
	
	public  void GET_INKAS(final Context c){
		Intent m=getIntent();
		String id=m.getStringExtra("id");
		if(msgList2!=null){
			msgList2.clear();
		}
		HttpUtils.httpRequest((Activity)c, U.url + "kino.php?type=get_inkas", "POST=ENTER&&id="+id, new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							//new AlertDialog.Builder(c).setMessage(s).show();
							msgList2 = new ArrayList<>();
							mAdapter2 = new inkasAdapter(msgList2,c);
							//s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							for(int i=0;i<ja.length();i++){
								inkasList msg1 = new inkasList(ja.getJSONObject(i).getString("userID"),ja.getJSONObject(i).getString("time"),ja.getJSONObject(i).getString("android"),ja.getJSONObject(i).getString("inkas"));
								msgList2.add(msg1);
							}
							recyclerView2.setAdapter(mAdapter2);

						}
					}catch(Exception e){
						//new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	
	}
	
	
