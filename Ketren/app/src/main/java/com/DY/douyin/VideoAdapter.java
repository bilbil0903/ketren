package com.DY.douyin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.heartfor.heartvideo.video.HeartVideo;
import com.heartfor.heartvideo.video.HeartVideoInfo;
import com.heartfor.heartvideo.video.VideoControl;
import com.ketren.kino.R;
import java.util.ArrayList;
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Bean> mDatas;
    public VideoAdapter(Context context, ArrayList<Bean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.douyin_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
		HeartVideoInfo info=HeartVideoInfo.Builder().setTitle(mDatas.get(position).getNami()).setPath(mDatas.get(position).geturl()).setImagePath(mDatas.get(position).getRasim()).setSaveProgress(true).builder();
		VideoControl control=new VideoControl(mContext);
		control.setInfo(info);
		holder.videoView.setHeartVideoContent(control);
	}

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public HeartVideo videoView;

        public ViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.myvideo);
        }
    }
}
