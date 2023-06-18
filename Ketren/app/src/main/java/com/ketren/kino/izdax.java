package com.ketren.kino;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import com.ketren.kino.Izdax.izdaxAdapter;
import com.ketren.kino.Izdax.izdaxList;
import com.ketren.kino.izdax;
import com.ketren.kino.kisim;
import java.util.ArrayList;
import org.json.JSONArray;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.text.TextWatcher;
import android.text.Editable;

public class izdax extends Activity
{
	private ArrayList<izdaxList> msgList;
	private izdaxAdapter mAdapter;
	private AlertDialog dlg;

	private RecyclerView recyclerView;
	String []list;
	private AutoCompleteTextView et;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			setContentView(R.layout.izdax);
		recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		 et=(AutoCompleteTextView)findViewById(R.id.izdax);
		et.setTypeface(Typeface.createFromAsset(izdax.this.getAssets(),"UyghurFont.ttf"));
		et.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
				Get_Key(p1.toString());
					
				}

				@Override
				public void afterTextChanged(Editable p1) {
				}
			});
		}
			public void izdax(View v){
				show();

				if(!TextUtils.isEmpty(et.getText().toString())){
				Okux(et.getText().toString());
				}
			}
	public void Okux(String n){
		HttpUtils.httpRequest(this, "http://bilbil.top/kino.php?type=izdax", "POST=ENTER&&KinoNami="+ n, new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(msg.what!=2&&msg.what!=0){
							s=Base64Coder.decodeString(s);
							msgList = new ArrayList<>();
							mAdapter = new izdaxAdapter(msgList,izdax.this,et.getText().toString());
							JSONArray ja=new JSONArray(s);
						for(int i=0;i<ja.length();i++){
							izdaxList msg1 = new izdaxList(ja.getJSONObject(i).getString("KinoNami"),ja.getJSONObject(i).getString("KinoRasimi"),ja.getJSONObject(i).getString("KinoTili"),ja.getJSONObject(i).getString("KinoKoyulixi"),ja.getJSONObject(i).getString("id"),ja.getJSONObject(i).getString("KinoQuxandurilixi"),ja.getJSONObject(i).getString("vip"));
							msgList.add(msg1);
						}
						recyclerView.setAdapter(mAdapter);
                       dlg.dismiss();
							}else{
								man.TOAST(izdax.this,"ھالقىلىق سۆز تېپىلمىدى ياكى تور بىنۇرمال!");
							dlg.dismiss();
					}
						
					}catch(Exception e){
						//new AlertDialog.Builder(izdax.this).setMessage(e.getMessage()).show();
						man.TOAST(izdax.this,"ھالقىلىق سۆز تېپىلمىدى ياكى تور بىنۇرمال!");
						dlg.dismiss();
						}}

			}); 
	}
	
	public void Get_Key(String n){
		HttpUtils.httpRequest(this, "http://bilbil.top/kino.php?type=izdax_key", "POST=ENTER&&KinoNami="+ n, new Handler() {
				public   void handleMessage(Message msg) {
					try{
						String s=(String)msg.obj;
						if(msg.what!=2&&msg.what!=0){
							s=Base64Coder.decodeString(s);
							JSONArray ja=new JSONArray(s);
							list=new String[ja.length()];
							for(int i=0;i<ja.length();i++){
								list[i]=ja.getJSONObject(i).getString("KinoNami");
							}
							ArrayAdapter aa=new ArrayAdapter(izdax.this,R.layout.kur,list);
							aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							et.setAdapter(aa);
					}

					}catch(Exception e){
					//Toast.makeText(izdax.this,"ھالقىلىق سۆز تېپىلمىدى ياكى تور بىنۇرمال!",0).show();
					}}

			}); 
	}
	public void  show(){
		dlg= new Builder(this,R.style.myDialog).create();
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView
		(R.layout.progress);
	}
			}
