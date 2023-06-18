package com.ketren.kino.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ketren.kino.Lists.kinoList;
import com.ketren.kino.R;
import com.ketren.kino.Utils.Y2;
import com.ketren.kino.kisim;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Bilbil on 2020/8/1.
 */
public class KinoAdapter extends RecyclerView.Adapter<KinoAdapter.ViewHolder> {
	static ArrayList<kinoList> msgList;
	private Context c;
	public KinoAdapter(ArrayList<kinoList> msgList,Context context){
        this.msgList = msgList;
		this.c=context;
    }
	@Override
	public KinoAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
		View view = LayoutInflater.from(p1.getContext()).inflate(R.layout.kino_list4, p1, false);
        return new ViewHolder(view);
	}
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {
		viewHolder.KinoNami.setText(msgList.get(position).getKinoNami());
		String s=msgList.get(position).getKinovip();
		String s1=msgList.get(position).getTil();
		if(s.equals("0")){
			viewHolder.vip.setText("ھەقسىز");
			viewHolder.vip.setBackgroundResource(R.drawable.ramka2);
		}else if(s.equals("1")){
			viewHolder.vip.setText("VIP");
			viewHolder.vip.setBackgroundResource(R.drawable.ramka);
		}
		
		
		
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

	}



	@Override
	public int getItemCount() {
		return msgList.size();
	}




	public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView KinoRasimi;
        TextView KinoNami;
		TextView vip;
		LinearLayout bat;
        public ViewHolder( View itemView) {
            super(itemView);
			KinoNami = (TextView)itemView.findViewById(R.id.text);
			vip = (TextView)itemView.findViewById(R.id.vip);
			KinoRasimi=(ImageView)itemView.findViewById(R.id.image);
			bat=(LinearLayout)itemView.findViewById(R.id.linearl);
		}
    }
	

}
