package com.ketren.admin;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class MyAdapter extends BaseAdapter {

    static ArrayList<MyList> msgList;
    private LayoutInflater mInflater;
	private Context c;
	View view ;

    public MyAdapter(ArrayList<MyList> msgList,Context context){
        this.msgList = msgList;
        mInflater = LayoutInflater.from(context);
		this.c=context;
    }
    @Override
    public int getCount() {
        return msgList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if((convertView==null)){
			view = mInflater.inflate(R.layout.list,parent,false);
			viewHolder = new ViewHolder();

			view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		viewHolder.KinoNami = (TextView)view.findViewById(R.id.text);
		viewHolder.KinoNami.setText(msgList.get(position).getKinoNami());
		return view;
	}
	static  class ViewHolder{
		 
		TextView KinoNami;
		
	}
	}
