package com.ketren.kino.Inkas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ketren.kino.HttpUtils;
import com.ketren.kino.Inkas.inkasAdapter;
import com.ketren.kino.Inkas.inkasList;
import com.ketren.kino.R;
import com.ketren.kino.U;
import com.ketren.kino.Utils.Base64Object;
import com.ketren.kino.manMain;
import java.util.ArrayList;
import com.ketren.kino.Base64Coder;

/**
 * Created by Bilbil on 2020/8/1.
 */
public class inkasAdapter extends RecyclerView.Adapter<inkasAdapter.ViewHolder> {
	static ArrayList<inkasList> msgList;
	private Context c;
	public inkasAdapter(ArrayList<inkasList> msgList,Context context){
        this.msgList = msgList;
		this.c=context;
    }
	@Override
	public inkasAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
		
		View view = LayoutInflater.from(p1.getContext()).inflate(R.layout.inkas_list, p1, false);
        return new ViewHolder(view);
	}
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {
		try{
	 viewHolder.mazmun.setText(Base64Coder.decodeString( msgList.get(position).getMazmun().replaceAll(" ","+")));
	 //viewHolder.phone.setText(msgList.get(position).getandroid());
	 viewHolder.time.setText(msgList.get(position).gettime());
		GET(c,viewHolder.icon,viewHolder.user,msgList.get(position).getUserID());
	}catch(Exception e){
		new AlertDialog.Builder(c).setMessage(e.getMessage()).show();
		}
		
	}



	@Override
	public int getItemCount() {
		return msgList.size();
	}




	public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        //TextView phone;
		TextView mazmun;
		TextView user;
		ImageView icon;
        public ViewHolder( View itemView) {
            super(itemView);
			time = (TextView)itemView.findViewById(R.id.time);
			mazmun = (TextView)itemView.findViewById(R.id.mazmun);
			//phone = (TextView)itemView.findViewById(R.id.phone);
			icon=(ImageView)itemView.findViewById(R.id.icon);
			user=(TextView)itemView.findViewById(R.id.isim);
			
		}
    }
	public  void GET(final Context c,final ImageView iv,final TextView tv,String id){
		HttpUtils.httpRequest((Activity)c, U.url + "kino.php?type=inkas_uqur", "POST=ENTER&&id="+id, new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(s!=null&&s.length()>5){
							String[] S=s.split(",");
							if(S[0]!=null){
							tv.setText(S[0]);
							}
							if(S[1]!=null&&s.length()>5){
							Bitmap b=Base64Object.base64ToBitmap((S[1]).replaceAll(" ","+"));
							iv.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(b,120)));
							}
							}
					}catch(Exception e){
						new AlertDialog.Builder(c).setMessage(e.getMessage()).show();

					}}

			}); 

	}
}
