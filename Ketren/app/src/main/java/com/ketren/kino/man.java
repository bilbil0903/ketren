package com.ketren.kino;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ketren.kino.Pull.PullToRefreshLayout;
import com.ketren.kino.Pull.PullToRefreshLayout.OnRefreshListener;
import com.ketren.kino.R;
import com.ketren.kino.Utils.Base64Object;
import java.io.File;
import java.math.BigDecimal;
import org.json.JSONArray;
public class man extends Fragment implements View.OnClickListener
{
	private Handler mHandler = new Handler();
	Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 1000);
			String s=tizimlitix.get_login((Activity)getContext());
			if(s!=null&&s!=""){
				GET_VIP();
				t.setText(s.substring(0,3)+"*****"+s.substring(9,11));
                GET();
				mHandler.removeCallbacks(runnable);
				ib.setVisibility(View.VISIBLE);
				TG.setVisibility(View.VISIBLE);
                GUL((Activity)getContext());
			}else {
				TextView tv=view.findViewById(R.id.tv2);
				tv.setText("ئادەتتىكى ئەزا");
				tv.setBackground(null);
                iv.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(BitmapFactory.decodeResource(getContext().getResources(),(R.drawable.ic_launcher)),360)));
				t.setText("كىرىڭ");
				ib.setVisibility(View.INVISIBLE);
				TG.setVisibility(View.INVISIBLE);
				//mHandler.removeCallbacks(runnable);
			}

        }

    };
	private View view;
	ImageButton ib;
	TextView t,tg;
	ImageView iv;
	LinearLayout bat1,bat2,bat3,bat4,bat5,bat7,bat8,TG;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		    view = inflater.inflate(R.layout.man,container,false);
		try{
			bat1=view.findViewById(R.id.bat1);
		 bat2=view.findViewById(R.id.bat2);
		 bat3=view.findViewById(R.id.bat3);
		 bat4=view.findViewById(R.id.bat4);
		 bat5=view.findViewById(R.id.bat5);
		 bat7=view.findViewById(R.id.bat7);
		 bat8=view.findViewById(R.id.bat8);
		 TG=view.findViewById(R.id.tg);
		 iv=view.findViewById(R.id.baxrasim);
		  t=view.findViewById(R.id.phone);
		  tg=view.findViewById(R.id.tagga);
		  t.setOnClickListener(this);
		  ib=view.findViewById(R.id.exit);
		  bat1.setOnClickListener(this);
		  bat2.setOnClickListener(this);
		  bat3.setOnClickListener(this);
		  bat4.setOnClickListener(this);
		  bat5.setOnClickListener(this);
		  bat7.setOnClickListener(this);
		  bat8.setOnClickListener(this);
		 ib.setOnClickListener(this);
		 iv.setOnClickListener(this);
         GET();
		 mHandler.postDelayed(runnable, 1000);
         ((PullToRefreshLayout)view.findViewById(R.id.refresh_view))
            .setOnRefreshListener(new OnRefreshListener(){

   @Override
   public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
   {   
       new Handler(){
           @Override
           public void handleMessage(Message msg)
           {
               String s=tizimlitix.get_login((Activity)getContext());
               if(s!=null&&s!=""){
                   GET_VIP();
                   t.setText(s.substring(0,3)+"*****"+s.substring(9,11));
                   GET();
                   mHandler.removeCallbacks(runnable);
				   GUL((Activity)getContext());
               }else {
                   TextView tv=view.findViewById(R.id.tv2);
                   tv.setText("ئادەتتىكى ئەزا");
                   tv.setBackground(null);
                   iv.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(BitmapFactory.decodeResource(getContext().getResources(),(R.drawable.ic_launcher)),360)));
                   t.setText("كىرىڭ");
                  // mHandler.removeCallbacks(runnable);
               }
               pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
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

               String s=tizimlitix.get_login((Activity)getContext());
               if(s!=null&&s!=""){
                   GET_VIP();
                   t.setText(s.substring(0,3)+"*****"+s.substring(9,11));
                   GET();
                   mHandler.removeCallbacks(runnable);

               }else {
                   TextView tv=view.findViewById(R.id.tv2);
                   tv.setText("ئادەتتىكى ئەزا");
                   tv.setBackground(null);
                   iv.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(BitmapFactory.decodeResource(getContext().getResources(),(R.drawable.ic_launcher)),360)));
                   t.setText("كىرىڭ");
                   //mHandler.removeCallbacks(runnable);
               }
               pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
           }
       }.sendEmptyMessageDelayed(0, 500);
   }
			});
		 }catch(Exception e){
			 new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
		 }
		 
		 
        return view;
		
    }
	
		
		
	@Override
	public void onClick(View p1){
        String s=tizimlitix.get_login((Activity)getContext());
		switch(p1.getId()){
			case R.id.phone:
					if(s!=null&&s!=""){
				}else{
					Intent i=new Intent();
					i.setClass(getContext(),tizimlitix.class);
					startActivity(i);
				}
			break;
			case R.id.exit:
              		if(s==null||s==""){
                        TOAST(getContext(),"كىرىڭ");
                        Intent in=new Intent();
                        in.setClass(getContext(),tizimlitix.class);
                        startActivity(in);
                    }else{
				tizimlitix.delete((Activity)getContext());
				mHandler.postDelayed(runnable, 1000);
				TOAST(getContext(),"غەلبىلىك چىكىندىڭىز!");
                }
				break;
				case R.id.bat2:
		try{
						Intent in=new Intent();
					in.setClass(getContext(),DownloadActivity.class);
					startActivity(in);
					}catch(Exception e){
					new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
					}
					break;
					case R.id.bat1:
				Intent in=new Intent();
				in.setClass(getContext(),Kalduk.class);
				startActivity(in);
						break;
						case R.id.bat3:
							naxir();
							break;
							case R.id.baxrasim:
                String str=tizimlitix.get_login((Activity)getContext());
                if(str!=null&&str!=""){
				Intent i=new Intent();
				i.setClass(getContext(),manMain.class);
				startActivity(i);
                }else{
                    Intent i=new Intent();
                    i.setClass(getContext(),tizimlitix.class);
                    startActivity(i);
                }
				break;
				case R.id.bat4:
					Dialog(getContext(),"ئ‍ەپتىكى بىرقىسىم فىلىملەر تور دۇنياسىدىن يىغىلغان،ئ‍ەگەر نەشىر ھوقوقىمىزغا دەخلى قىلدى دەپ قارىسىڭىز بىر 24سائ‍ەت ئ‍ىچىدە  مەزكۇر فىلىمنى ئ‍ۆچۈرۈپ تاشلايمىز،VIPسېتىۋالسىڭىز ئ‍ورگان توپىدىكى باشقۇرغۇچىلارنى ئ‍ىزدەڭ!",false
					);
					break;
								case R.id.bat5:
									share();	
				break;
				case R.id.bat7:
				String url = "mqqwpa://im/chat?chat_type=wpa&uin=3266711833&version=1&src_type=web&web_src=oicqzone.com";
				getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
				//Toast.makeText(getContext(),"nnnn",0).show();
				break;
				case R.id.bat8:
				String st=tizimlitix.get_login((Activity)getContext());
				if(st==null||st==""){
					TOAST(getContext(),"كىرىڭ");
					Intent inn=new Intent();
					inn.setClass(getContext(),tizimlitix.class);
					startActivity(inn);
				}else{
					Intent im=new Intent();
					im.setClass(getContext(),tagga.class);
					startActivity(im);
					}
					break;
		}
	}
	
	public void GET_VIP(){
			HttpUtils.httpRequest((Activity)getContext(),U.url+"kino.php?type=root","POST=ENTER&&android="+tizimlitix.get_login((Activity)getContext()),new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						TextView tv=view.findViewById(R.id.tv2);
						if(s.equals("NONE_VIP")){
							tv.setText("ئادەتتىكى ئەزا");
						}else if(s.equals("USED_VIP")){
							tv.setText("ئەزالىق ۋاقتى توشقان");
							tv.setTextColor(Color.RED);
						}else if(s!=null){
							tv.setBackgroundResource(R.drawable.ramka);
							tv.setTextColor(Color.WHITE);
							tv.setText(s);
							tv.setText("ۋاقتى: "+s+" كۈن");
						}

					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 

	}
	public void naxir(){
		HttpUtils.httpRequest((Activity)getContext(),U.url+"naxir.php","",new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
                            int i=Integer.parseInt(s);
							if(i>106){
								naxir2();
							}else{
								TOAST(getContext(),"بۇ ئ‍ەڭ يىڭى نەشىرى!");
							}
							
							}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public void naxir2(){
		HttpUtils.httpRequest((Activity)getContext(),U.url+"gengxin.txt","",new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							AlertDialog ad=new Builder(getContext()).create();
							ad.setTitle("كەترەن يىڭىلاندى!");  
							ad.setCancelable(true);
							ad.setMessage(s);  
							ad.setButton2("چۈشۈرەي", new DialogInterface.OnClickListener() {           
									@Override  
									public void onClick(DialogInterface dialog, int which) {  
										dialog.dismiss();  
										Uri uri = Uri.parse(U.url+"ketren.apk"); //url为你要链接的地址 
										Intent intent =new Intent(Intent.ACTION_VIEW, uri); 
										startActivity(intent);         
									}  
								});  

							ad.show();  
						}
					}catch(Exception e){
						new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public void share(){
		Intent shareIntent =new Intent();
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(getContext().getPackageCodePath()));
		shareIntent.setType("*/*");
		startActivity(Intent.createChooser(shareIntent, "ھەمبەھىرلەيدىغان يەرنى تاللاڭ"));

			}
	
            
    public void GET(){
        String user=tizimlitix.get_login((Activity)getContext());
        HttpUtils.httpRequest((Activity)getContext(),U.url+"kino.php?type=get_user","POST=ENTER&&user="+user,new Handler() {
                public   void handleMessage(Message msg) {
              try{
                  String s=(String)msg.obj;
                      if(s!=""&&s!=null&&s.length()>5){}
                  JSONArray ja=new JSONArray(s);
                  String S=ja.getString(1);
                 if(S!=null&&S!=""&&S.length()>10){
                  Bitmap b=Base64Object.base64ToBitmap(S.replaceAll(" ","+"));
                    iv.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(b,360)));
                    }
                }catch(Exception e){
                    new AlertDialog.Builder(getContext()).setMessage(e.getMessage()).show();
                }
}
            }); 

	}
   
    private void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
			try {
				File file = new File(filePath);
				if (file.isDirectory()) {
					File files[] = file.listFiles();
					for (File file1 : files) {
						deleteFolderFile(file1.getAbsolutePath(), true);
					}
				}
				if (deleteThisPath) {
					if (!file.isDirectory()) {
						file.delete();
					} else {
						if (file.listFiles().length == 0) {
							file.delete();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}

	/**
	 * 格式化单位
	 *
	 * @param size size
	 * @return size
	 */
	private static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
			return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}
	
	private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
			File[] fileList = file.listFiles();
			for (File aFileList : fileList) {
				if (aFileList.isDirectory()) {
					size = size + getFolderSize(aFileList);
				} else {
					size = size + aFileList.length();
				}
			}
        } catch (Exception e) {
			e.printStackTrace();
        }
        return size;
	}
	
	
	
	
	public  void GUL(final Activity c){
		String  user=tizimlitix.get_login((Activity)c);
		HttpUtils.httpRequest(c,U.url+"kino.php?type=tagga","POST=ENTER&&user="+user,new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null){
							tg.setText(s);
						}
					}catch(Exception e){
						//new AlertDialog.Builder((Context)c).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public static void Dialog(final Context c,String str,final boolean b){
		final  AlertDialog dlg = new Builder(c,R.style.myDialog).create();
		if(!b){
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		}else{
			dlg.setCancelable(false);
			dlg.setCanceledOnTouchOutside(false);
		}
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.dialog);
		TextView mazmun=window.findViewById(R.id.mazmun);
		mazmun.setText(str);
        ((Button) window.findViewById(R.id.bb)).setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dlg.cancel();
					if(b){
						((Activity)c).finish();
					}
				}
			});





	}
	public static void TOAST(Context c,String s){
		LayoutInflater inflater =((Activity)c). getLayoutInflater();
		View layout = inflater.inflate(R.layout.askartix, (ViewGroup)((Activity)c). findViewById(R.id.bb));
		TextView text = (TextView) layout.findViewById(R.id.mazmun);
		text.setText(s);
		Toast t = new Toast(c);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.setDuration(Toast.LENGTH_LONG);
		t.setView(layout);
		t.show();

	}
	
	}

	
	
