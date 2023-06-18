package com.ketren.kino.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ketren.kino.Lists.yigiList;
import com.ketren.kino.R;
import com.ketren.kino.Utils.Y2;
import com.ketren.kino.kisim;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import com.ketren.kino.Utils.Y;


/**
 * Created by Bilbil on 2020/8/1.
 */
public class YigiAdapter extends RecyclerView.Adapter<YigiAdapter.ViewHolder> {
	static ArrayList<yigiList> msgList;
	private Context c;
	View view=null;
	public YigiAdapter(ArrayList<yigiList> msgList,Context context){
        this.msgList = msgList;
		this.c=context;
    }
	@Override
	public YigiAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
			 view = LayoutInflater.from(p1.getContext()).inflate(R.layout.kino_list2, p1, false);
		
		return new ViewHolder(view);
		
			
	}
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {
		
		String s=msgList.get(position).getKinovip();
		if(s.equals("0")){
			viewHolder.vip.setText("ھەقسىز");
			viewHolder.vip.setBackgroundResource(R.drawable.ramka2);
		}else if(s.equals("1")){
			viewHolder.vip.setText("VIP");
			viewHolder.vip.setBackgroundResource(R.drawable.ramka);
		}
		
	String st=msgList.get(position).getKinoQuxandurulixi();
	if(st.length()<50){
		
	}else{
		st=st.substring(0,50)+"...";
		
	}
		String st2=msgList.get(position).getKinoNami();
		if(st2.length()<50){

		}else{
			st2=st2.substring(0,50)+"...";

		}
		if(msgList.get(position).getTil().equals("oztil")){
			viewHolder.KinoTili.setText("ئۆزتىل");
		}else if(msgList.get(position).getTil().equals("uyghur")){
			viewHolder.KinoTili.setText("ئۇيغۇرچە");
		}
		viewHolder.see.setText(msgList.get(position).getKinoKoyulixi()+" قېتىم");
		viewHolder.KinoNami.setText(st2);
	viewHolder.jieshao.setText(st);
		//viewHolder.see.setText(msgList.get(position).getKinoKoyulixi()+" قېتىم");
		Picasso.with(c)
			.load(msgList.get(position).getkasma())
			.resize(360,480)
			.error(R.drawable.img1)
			.placeholder(R.drawable.img1)
			.transform(new Y2())
			.into(viewHolder.KinoRasimi);
			viewHolder.bat.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1) {
					Intent i=new Intent();
					i.setClass(c,kisim.class);
					i.putExtra("KinoNami",msgList.get(position).getKinoNami());
					i.putExtra("id",msgList.get(position).getKinoID());
					i.putExtra("KinoRasimi",msgList.get(position).getkasma());
					i.putExtra("KinoKoyulixi",msgList.get(position).getKinoKoyulixi());
					i.putExtra("KinoQuxandurilixi",msgList.get(position).getKinoQuxandurulixi());
					c.startActivity(i);
				}
			});
			viewHolder.bat.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View p1) {
						//ClipboardManager a=(ClipboardManager)c.getSystemService(c.CLIPBOARD_SERVICE);
						//a.setText("http://bilbil.top/kino/m/mazmun.php?hid="+msgList.get(position).getKinoID());
						//Toast.makeText(c,"مەنبەسى كۆچۈرۈلدى!",0).show();
						return false;
					}
					
			
		});
		}
	@Override
	public int getItemCount() {
		return msgList.size();
	}
	

	
    
	public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView KinoRasimi;
        TextView KinoNami;
		TextView vip;
		TextView jieshao;
		LinearLayout bat;
		TextView KinoTili;
		TextView see;
        public ViewHolder( View itemView) {
            super(itemView);
		   KinoNami = (TextView)itemView.findViewById(R.id.text);
			vip = (TextView)itemView.findViewById(R.id.vip);
			KinoTili = (TextView)itemView.findViewById(R.id.til);
			see = (TextView)itemView.findViewById(R.id.play);		
			KinoRasimi=(ImageView)itemView.findViewById(R.id.image);
			bat=(LinearLayout)itemView.findViewById(R.id.linearl);
			jieshao=(TextView)itemView.findViewById(R.id.jieshao);
             }
    }

	}
