package com.ketren.kino.Nahxa2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.androidev.download.DownloadManager;
import com.androidev.download.DownloadTask;
import com.ketren.kino.DownloadActivity;
import com.ketren.kino.HttpUtils;
import com.ketren.kino.R;
import com.ketren.kino.baxbat0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.ketren.kino.Nahxa.NahxaAdapter;

/**
 * Created by Bilbil on 2020/8/1.
 */
public class NahxaAdapter2 extends RecyclerView.Adapter<NahxaAdapter2.ViewHolder> {

	public static final String APPURL = "http://bilbil.top/";
	public static final int DOWNLOAD_MESSAGE_CODE = 100001;
	public static final int DOWNLOAD_MESSAGE_FAIL_CODE =100002 ;
	private NahxaAdapter2.DownloaderHadle handler;
	public static View vh;
//处理进度条更新  
	private Handler mHandler = new Handler();
	Runnable runnable = new Runnable() {
        @Override
        public void run() {
			TextView hazirki=vh.findViewById(R.id.hazirki);
			SeekBar seek=vh.findViewById(R.id.seekbar);
			hazirki.setText(calculateTime(mMediaPlayer.getCurrentPosition()/1000));
			seek.setProgress(mMediaPlayer.getCurrentPosition());
            mHandler.postDelayed(this, 1000);
			if(mMediaPlayer.getDuration()==mMediaPlayer.getCurrentPosition()){
				mHandler.removeCallbacks(runnable);
			}

        }

    };



	//private boolean isSeekbarChaning;
	static ArrayList<NahxaList2> msgList;
    public static MediaPlayer mMediaPlayer;

	private Context c;
	int Last_Id=-1;
	public NahxaAdapter2(ArrayList<NahxaList2> msgList,Context context){
        this.msgList = msgList;
		this.c=context;
		mMediaPlayer=new MediaPlayer();
    }
	public NahxaAdapter2(){
		//stopMusic();
	}    
	@Override
	public NahxaAdapter2.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
		View view = LayoutInflater.from(p1.getContext()).inflate(R.layout.nahxa_list, p1, false);
        return new ViewHolder(view);
	}
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {
		viewHolder.nahxa.setText(msgList.get(position).getNahxa());
		viewHolder.nahxiqi.setText(msgList.get(position).getNahxiqi());
		viewHolder.see.setText(""+(position+1));
		if(position==getItemCount()-1){
			viewHolder.kayni.setVisibility(View.GONE);
		}else if(position==0){
			viewHolder.aldi.setVisibility(View.GONE);
		}
		viewHolder.stop.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if(mMediaPlayer.isPlaying()){
						p1.setBackgroundResource(R.drawable.koy);
						pause();
					}else{
						p1.setBackgroundResource(R.drawable.tohta);
						play();
					}
				}
			});
		viewHolder.aldi.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Tagxak(Last_Id,position-1);
					if(Last_Id!=position-1){
						handler = new DownloaderHadle( msgList.get(position-1).getID(),position-1);
						new Thread(new Runnable() {
								@Override
								public void run() {
									download(msgList.get(position-1).getAdiris(),msgList.get(position-1).getID());
								}
							}).start();
					}

					Last_Id=position-1;
				}


			});
		viewHolder.kayni.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Tagxak(Last_Id,position+1);
					if(Last_Id!=position+1){
						handler = new DownloaderHadle( msgList.get(position+1).getID(),position+1);
						new Thread(new Runnable() {
								@Override
								public void run() {
									download(msgList.get(position+1).getAdiris(),msgList.get(position+1).getID());
								}
							}).start();
					}

					Last_Id=position+1;
				}


			});

		viewHolder.bat.setOnClickListener(new OnClickListener(){


				@Override
				public void onClick(View p1) {
					Tagxak(Last_Id,position);
					see(msgList.get(position).getID());
					if(Last_Id!=position){
						handler = new DownloaderHadle( msgList.get(position).getID(),position);
						new Thread(new Runnable() {
								@Override
								public void run() {
									download(msgList.get(position).getAdiris(),msgList.get(position).getID());
								}
							}).start();
					}

					Last_Id=position;
				}

			});
		viewHolder.bat.setOnLongClickListener(new OnLongClickListener(){
				@Override
				public boolean onLongClick(View p1) {
					DOWN(msgList.get(position).getAdiris(),msgList.get(position).getNahxa(),msgList.get(position).getNahxiqi());
					return false;
				}
			});
		viewHolder.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					int duration2 = mMediaPlayer.getDuration() / 1000;//获取音乐总时长
					int position = mMediaPlayer.getCurrentPosition();//获取当前播放的位置
					viewHolder.hazirki.setText(calculateTime(position / 1000));//开始时间
					viewHolder.omumi.setText(calculateTime(duration2));//总时长

				}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					//isSeekbarChaning = true;
				}  
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					//isSeekbarChaning = false;
					mMediaPlayer.seekTo(seekBar.getProgress());//在当前位置播放
					viewHolder.hazirki.setText(calculateTime(mMediaPlayer.getCurrentPosition() / 1000));
				}
			});

	}



	@Override
	public int getItemCount() {
		return msgList.size();
	}

	public static String calculateTime(int time){

        int minute;
        int second;
        if(time > 60){
            minute = time / 60;
            second = time % 60;
            //分钟再0~9
            if(minute >= 0 && minute < 10){
                //判断秒
                if(second >= 0 && second < 10){
                    return "0"+minute+":"+"0"+second;
                }else {
                    return "0"+minute+":"+second;
                }
            }else {
                //分钟大于10再判断秒
                if(second >= 0 && second < 10){
                    return minute+":"+"0"+second;
                }else {
                    return minute+":"+second;
                }
            }
        }else if(time < 60){
            second = time;
            if(second >= 0 && second < 10){
                return "00:"+"0"+second;
            }else {
                return "00:"+ second;
            }
        }
        return null;
    }


	public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nahxa;
		TextView nahxiqi;
		TextView omumi;
		ImageButton aldi;
		ImageButton kayni;
		ImageButton stop;
		TextView hazirki;
		TextView see;
		SeekBar seekbar;
		LinearLayout bat;
		LinearLayout koyguq;
		LinearLayout koy;
		RelativeLayout loading;
		protected SeekBar seekBar;
        public ViewHolder( View itemView) {
            super(itemView);
			nahxa = (TextView)itemView.findViewById(R.id.nahxa);
			nahxiqi = (TextView)itemView.findViewById(R.id.nahxiqi);
			see=(TextView)itemView.findViewById(R.id.see);
			omumi = (TextView)itemView.findViewById(R.id.omumi);
			hazirki = (TextView)itemView.findViewById(R.id.hazirki);
			aldi = (ImageButton)itemView.findViewById(R.id.last);
			kayni = (ImageButton)itemView.findViewById(R.id.next);
			stop = (ImageButton)itemView.findViewById(R.id.pauseandstart);
			bat=(LinearLayout)itemView.findViewById(R.id.linearl);
			koyguq=(LinearLayout)itemView.findViewById(R.id.koyguq);
			koy=(LinearLayout)itemView.findViewById(R.id.koy);
			loading=(RelativeLayout)itemView.findViewById(R.id.loading);
			seekbar=(SeekBar)itemView.findViewById(R.id.seekbar);
		}

		//计算播放时间

    }
	/*
	 * 初始化MediaPlayer
	 * */


	public void playAudio(final String path,final int position) {

		RecyclerView mRecyclerView = (RecyclerView) ((Activity)c).findViewById(R.id.recycle2);
		final View vh =mRecyclerView.getLayoutManager().findViewByPosition(position);
		this.vh=vh;
		RelativeLayout r1=vh.findViewById(R.id.loading);
		r1.setVisibility(View.GONE);
		LinearLayout r2=vh.findViewById(R.id.koy);
		r2.setVisibility(View.VISIBLE);
		//new AlertDialog.Builder(c).setMessage("R:"+path).show();
		try {
			if(mMediaPlayer!=null){
				mMediaPlayer.release();
			}
			//Toast.makeText(c,position+"/"+vh.getPosition(),0).show();
			mMediaPlayer=new MediaPlayer();
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
			mMediaPlayer.prepare();
			int duration2 = mMediaPlayer.getDuration() / 1000;
			int pos = mMediaPlayer.getCurrentPosition();
			TextView hazirki=vh.findViewById(R.id.hazirki);
			TextView omumi=vh.findViewById(R.id.omumi);
			hazirki.setText(calculateTime(pos / 1000));
			omumi.setText(calculateTime(duration2));
			mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						if(getItemCount()-1==position){
							Tagxak(Last_Id,0);
							if(Last_Id!=0){
								handler = new DownloaderHadle( msgList.get(0).getID(),0);
								new Thread(new Runnable() {
										@Override
										public void run() {
											download(msgList.get(0).getAdiris(),msgList.get(0).getID());
										}
									}).start();
							}

							Last_Id=0;
						}else{
							Tagxak(Last_Id,position+1);
							if(Last_Id!=position+1){
								handler = new DownloaderHadle( msgList.get(position+1).getID(),position+1);
								new Thread(new Runnable() {
										@Override
										public void run() {
											download(msgList.get(position+1).getAdiris(),msgList.get(position+1).getID());
										}
									}).start();
							}

							Last_Id=position+1;
						}
					}


				});
			mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						mMediaPlayer=mp;
						play();
						int duration = mMediaPlayer.getDuration();//获取音乐总时间
						SeekBar seek=vh.findViewById(R.id.seekbar);
						seek.setMax(duration);//将音乐总时间设置为Seekbar的最大值
						//Toast.makeText(c,"R:"+duration,0).show();
						mHandler.postDelayed(runnable,0);
					}});
		} catch (Exception e) {
			//   new AlertDialog.Builder(c).setMessage(e.getMessage()).show();
		}


	}
	private void download(String appurl,String ID) {

		String downloadFolderName = c.getExternalCacheDir().getPath()+"/mp3/";
		File f=new File(downloadFolderName+ID+".mpk");
		if(!f.exists()){
			try {
				URL url = new URL(appurl);
				URLConnection urlConnection = url.openConnection();
				InputStream inputStream = urlConnection.getInputStream();

				/**
				 * 获取文件大小,获取本地下载路径文件命名
				 */
				int contentLength = urlConnection.getContentLength();
				File file = new File(downloadFolderName);
				if (!file.exists()) {
					file.mkdir();
				}


				byte[] bytes = new  byte[10240];//创建一个字节缓存
				int downloadSize = 0; //进度当前长度默认0  当前进度长度/文件总长度
				int length;
				OutputStream outputStream =new FileOutputStream(downloadFolderName+ID+".mpk");
				while ((length=inputStream.read(bytes))!= -1) {
					outputStream.write(bytes, 0, length);
					downloadSize += length;

					/**
					 更新ui进度条
					 */

				}
				Message message = Message.obtain();
				//这个地方注意downloadSize*100 文件超过20m会出现数据溢出 数值就到不了100%
				message.arg1=contentLength/100;
				message.arg2=downloadSize;
				Log.e("TAG", String.valueOf(downloadSize));
				message.what = DOWNLOAD_MESSAGE_CODE;
				handler.sendMessage(message);

				inputStream.close();
				outputStream.close();

			} catch (MalformedURLException e) {
				notifyDownloadFaild();//报错的时候调用这个handler发送消息
				e.printStackTrace();
			} catch (IOException e) {
				notifyDownloadFaild();
				e.printStackTrace();
			}
		}else{
			Message message = Message.obtain();
			//这个地方注意downloadSize*100 文件超过20m会出现数据溢出 数值就到不了100%
			message.arg1=1;
			message.arg2=2;
			handler.sendMessage(message);
		}
	}
	/**
	 * 自定义的静态handler
	 */
	public  class DownloaderHadle extends Handler {
		String ID;
		int position ;
		DownloaderHadle(String ID,int position){
			this.ID=ID;
			this.position=position;
		}
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.arg1<msg.arg2){

				String downloadFolderName = c.getExternalCacheDir().getPath()+"/mp3/";

				playAudio(downloadFolderName+ID+".mpk",position);

			}else{
				//Toast.makeText(c,"/"+msg.arg1+"/"+msg.arg2,0).show();
			}
		}
	}
	private void notifyDownloadFaild() {
		Message message =Message.obtain();
		message.what = DOWNLOAD_MESSAGE_FAIL_CODE;
		handler.sendMessage(message);
	}


	public void Tagxak(int p1,int p2){
		RecyclerView mRecyclerView = (RecyclerView) ((Activity)c).findViewById(R.id.recycle2);
		View view = mRecyclerView.getLayoutManager().findViewByPosition(p2);
		LinearLayout r=(LinearLayout)view.findViewById(R.id.koyguq);
		if(r.getVisibility()==8&p1==p2){//Itik
			r.setVisibility(View.VISIBLE);
			play();
			//Toast.makeText(c,"itik:"+ r.getVisibility(),0).show();
		}else if(r.getVisibility()==0&p1==p2){//Oquk
			r.setVisibility(View.GONE);
			pause();
			//Toast.makeText(c,"oquk:"+ r.getVisibility(),0).show();
		}else{
			//Toast.makeText(c,""+ r.getVisibility(),0).show();
			r.setVisibility(View.VISIBLE);
			if(p1!=-1){
				View view2 = mRecyclerView.getLayoutManager().findViewByPosition(p1);
				LinearLayout r2=(LinearLayout)view2.findViewById(R.id.koyguq);
				r2.setVisibility(View.GONE);
				stopMusic();
				//Toast.makeText(c,""+ r.getVisibility(),0).show();

			}}}
    public void play(){
		if(mMediaPlayer!=null){
			ImageButton ib=(ImageButton) vh.findViewById(R.id.pauseandstart);
			ib.setBackgroundResource(R.drawable.tohta);
			mMediaPlayer.start();
		}
		
	
			if(NahxaAdapter.mMediaPlayer.isPlaying()){
				NahxaAdapter.mMediaPlayer.pause();
				ImageButton ib=(ImageButton) NahxaAdapter.vh.findViewById(R.id.pauseandstart);
				ib.setBackgroundResource(R.drawable.koy);
			}
	}
	public void pause(){
		if(mMediaPlayer.isPlaying()){
			mMediaPlayer.pause();
			ImageButton ib=(ImageButton) vh.findViewById(R.id.pauseandstart);
			ib.setBackgroundResource(R.drawable.koy);
		}
	}
	public void stopMusic() {
        if(mMediaPlayer!=null& mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mHandler.removeCallbacks(runnable);
        }
    }
	public void stop() {
        if(mMediaPlayer!=null& mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mHandler.removeCallbacks(runnable);
			}
			if(Last_Id!=-1){
			RecyclerView mRecyclerView = (RecyclerView) ((Activity)c).findViewById(R.id.recycle2);
			View view = mRecyclerView.getLayoutManager().findViewByPosition(Last_Id);
			LinearLayout r=(LinearLayout)view.findViewById(R.id.koyguq);
			if(r.getVisibility()==0){
				r.setVisibility(View.GONE);
			
        }
		}
    }
	public void see(String id){
		HttpUtils.httpRequest((Activity)c, "http://bilbil.top/kino.php?type=nahxa_see", "POST=ENTER&&id="+id, new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						//	new AlertDialog.Builder(c).setMessage(""+s).show();

					}catch(Exception e){
						new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 
	}
	public void DOWN(String path,String title,String nahxiqi){
		baxbat0.requestPermission(c);
		Toast.makeText(c,"چۈشۈرۈش باشلاندى!",0).show();
		DownloadManager controller = DownloadManager.getInstance();
		DownloadTask dt;
		dt=controller.newTask(hashCode(), path, title+"_"+nahxiqi+".mp3").extras("").create();
		dt.start();
		Intent i=new Intent();
		i.setClass(c,DownloadActivity.class);
		c.startActivity(i);
	}
}

