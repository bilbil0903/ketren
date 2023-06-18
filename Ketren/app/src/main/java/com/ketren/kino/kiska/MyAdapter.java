package com.ketren.kino.kiska;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ketren.kino.R;
import com.heartfor.heartvideo.video.HeartVideo;
import com.heartfor.heartvideo.video.HeartVideoInfo;
import com.heartfor.heartvideo.video.VideoControl;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
	ArrayList<myList> kl;
    public MyAdapter(Context context,ArrayList<myList> al){
        this.context=context;
		this.kl=al;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.kiska_layout_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HeartVideoInfo info=HeartVideoInfo.Builder().setTitle(kl.get(position).getNami()).setPath(kl.get(position).getAdiris()).setImagePath(kl.get(position).getRasim()).setSaveProgress(true).builder();
        VideoControl control=new VideoControl(context);
        control.setInfo(info);
        holder.myvideo.setHeartVideoContent(control);
    }

    @Override
    public int getItemCount() {
        return kl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public HeartVideo myvideo;
        public ViewHolder(View itemView) {
            super(itemView);
            myvideo=(HeartVideo)itemView.findViewById(R.id.myvideo);
        }
    }
}
