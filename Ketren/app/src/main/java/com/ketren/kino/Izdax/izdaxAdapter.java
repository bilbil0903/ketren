package com.ketren.kino.Izdax;
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

/**
 * Created by Bilbil on 2020/8/1.
 */
public class izdaxAdapter extends RecyclerView.Adapter<izdaxAdapter.ViewHolder> {
	static ArrayList<izdaxList> msgList;
	private Context c;
	private String S;
	public izdaxAdapter(ArrayList<izdaxList> msgList,Context context,String s){
        this.msgList = msgList;
		this.c=context;
		 S=s;
    }
	@Override
	public izdaxAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
		View view = LayoutInflater.from(p1.getContext()).inflate(R.layout.kino_list, p1, false);
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
		if(s1.equals("oztil")){
			viewHolder.KinoTili.setText("ئۆزتىل");
		}else if(s1.equals("uyghur")){
			viewHolder.KinoTili.setText("ئۇيغۇرچە");
		}
		viewHolder.see.setText(msgList.get(position).getKinoKoyulixi()+" قېتىم");
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
		TextView KinoTili;
		TextView see;
		TextView vip;
		LinearLayout bat;
        public ViewHolder( View itemView) {
            super(itemView);
			KinoTili = (TextView)itemView.findViewById(R.id.til);
			KinoNami = (TextView)itemView.findViewById(R.id.text);
			see = (TextView)itemView.findViewById(R.id.play);
			vip = (TextView)itemView.findViewById(R.id.vip);
			KinoRasimi=(ImageView)itemView.findViewById(R.id.image);
			bat=(LinearLayout)itemView.findViewById(R.id.linearl);
		}
    }
	
	}

