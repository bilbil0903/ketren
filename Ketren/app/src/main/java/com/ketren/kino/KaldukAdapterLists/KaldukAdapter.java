package com.ketren.kino.KaldukAdapterLists;

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
import com.ketren.kino.R;
import com.ketren.kino.Utils.Y2;
import com.ketren.kino.kisim;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Bilbil on 2020/8/1.
 */
public class KaldukAdapter extends RecyclerView.Adapter<KaldukAdapter.ViewHolder> {
	static ArrayList<KaldukList> msgList;
	private Context c;
	public KaldukAdapter(ArrayList<KaldukList> msgList,Context context){
        this.msgList = msgList;
		this.c=context;
    }
	@Override
	public KaldukAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
		View view = LayoutInflater.from(p1.getContext()).inflate(R.layout.kino_list3, p1, false);
        return new ViewHolder(view);
	}
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {
		viewHolder.KinoNami.setText(msgList.get(position).getKinoNami());
		viewHolder.KinoWakti.setText(msgList.get(position).getKinoKisim()+"-"+"قىسىم"+"     "+buildTimeMilli((long)Integer.parseInt(msgList.get(position).getKinoWakti())));
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
					i.putExtra("KinoKoyulixi","");
					i.putExtra("KinoQuxandurilixi","");
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
		TextView KinoWakti;
		LinearLayout bat;
        public ViewHolder( View itemView) {
            super(itemView);
			KinoWakti = (TextView)itemView.findViewById(R.id.wakit);
			KinoNami = (TextView)itemView.findViewById(R.id.text);
			KinoRasimi=(ImageView)itemView.findViewById(R.id.image);
			bat=(LinearLayout)itemView.findViewById(R.id.linearl);
		}
    }
	private String buildTimeMilli(long duration) {
        long total_seconds = duration / 1000;
        long hours = total_seconds / 3600;
        long minutes = (total_seconds % 3600) / 60;
        long seconds = total_seconds % 60;
        if (duration <= 0) {
            return "تېخى كۆرمىگەن";
        }
        if (hours >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
    }
	}
