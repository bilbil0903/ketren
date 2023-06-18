package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;
import android.widget.ListView;
import android.widget.Toast;
import com.ketren.kino.KaldukAdapterLists.KaldukAdapter;
import com.ketren.kino.KaldukAdapterLists.KaldukList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
public class Kalduk extends Activity
{

	private   ArrayList<KaldukList> msgList;
	private  KaldukAdapter mAdapter;
	private RecyclerView recyclerView;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			setContentView(R.layout.kalduk);
			recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
			recyclerView.setHasFixedSize(true);
			recyclerView.setNestedScrollingEnabled(false);
			recyclerView.setItemViewCacheSize(200);
			RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
			recyclerView.setRecycledViewPool(recycledViewPool);
			recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
		ReFresh();
		}
	
	public void ReFresh(){
		try{
			msgList = new ArrayList<>();
			mAdapter = new KaldukAdapter(msgList,Kalduk.this);
			SQLiteDatabase sql=this.openOrCreateDatabase("user.db",0,null);
			sql.execSQL("CREATE TABLE IF NOT EXISTS kalduk(san int auto_increment primary key,title varchar,rasim varchar,id integer,wakit integer,kisim integer)");
			Cursor	c=sql.rawQuery("select*from kalduk order by san desc",null);
			if(c.getCount()>0){
						while(c.moveToNext()){
							String KinoNami=c.getString(c.getColumnIndex("title"));
							int kinoWakti=c.getInt(c.getColumnIndex("wakit"));
							String KinoRasimi=c.getString(c.getColumnIndex("rasim"));
							int id=c.getInt(c.getColumnIndex("id"));
							int kisim=c.getInt(c.getColumnIndex("kisim"));
					KaldukList msg1 = new KaldukList(KinoNami,KinoRasimi,""+kinoWakti,""+id,""+(kisim+1));
					msgList.add(msg1);
				}
				recyclerView.setAdapter(mAdapter);
				mAdapter.notifyDataSetChanged();
			}else{
				
       man.TOAST(this,"تېخى كىنو كۆرمەپسىز!");
			}}catch(Exception e){
			new AlertDialog.Builder(this).setMessage(e.getMessage()).show();
		}}
	public void back(View v){
		finish();
	}
	public void delete(View v){
		SQLiteDatabase sql=this.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("delete from kalduk");
		man.TOAST(this,"تازلاندى!");
		finish();
	}
	}
