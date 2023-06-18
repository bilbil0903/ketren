package com.ketren.kino;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.ketren.kino.R;
import com.ketren.kino.Turlar;
import com.ketren.kino.bat;

public class tur extends Fragment implements OnClickListener 
{



	
	ImageButton kino,kisimlik,karton,nahxa,vip,zonghe,daris,tv,mtv,turlar;
	private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tur,container,false);
		kisimlik=view.findViewById(R.id.kisimlik);
		kino=view.findViewById(R.id.kino);
		karton=view.findViewById(R.id.karton);
		nahxa=view.findViewById(R.id.nahxa);
		vip=view.findViewById(R.id.vip);
		zonghe=view.findViewById(R.id.zonghe);
		daris=view.findViewById(R.id.daris);
		tv=view.findViewById(R.id.tv);
		mtv=view.findViewById(R.id.mtv);
		turlar=view.findViewById(R.id.tur);
		
		kisimlik.setOnClickListener(this);
		kino.setOnClickListener(this);
		karton.setOnClickListener(this);
		nahxa.setOnClickListener(this);
		vip.setOnClickListener(this);
		zonghe.setOnClickListener(this);
		daris.setOnClickListener(this);
		tv.setOnClickListener(this);
		mtv.setOnClickListener(this);
		turlar.setOnClickListener(this);
        return view;
    }
	
	
	@Override
	public void onClick(View p1){
		if(p1==kisimlik){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","قىسىملىق");
			i.putExtra("type","where tur1=1 and KinoTuri='kino'");
			startActivity(i);
		}else if(p1==kino){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","كىنو");
			i.putExtra("type","where KinoTuri='kino' ");
			startActivity(i);
		}else if(p1==karton){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","كارتون");
			i.putExtra("type","where KinoTuri='karton'");
			startActivity(i);
		}else if(p1==nahxa){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","ناخشا");
			i.putExtra("type","where KinoTuri='nahxa'");
			startActivity(i);
		}else if(p1==vip){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","VIP");
			i.putExtra("type","where vip=1");
			startActivity(i);
		}else if(p1==zonghe){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","ئ‍ونۋېرسال");
			i.putExtra("type","where KinoTuri='zonghe'");
			startActivity(i);
		}else if(p1==daris){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","دەرىس");
			i.putExtra("type","where KinoTuri='daris'");
			startActivity(i);
		}else if(p1==tv){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","تېلىۋېزىيە قانىلى");
			i.putExtra("type","where KinoTuri='tv'");
			startActivity(i);
		}else if(p1==mtv){
			Intent i=new Intent();
			i.setClass(getContext(),bat.class);
			i.putExtra("nami","MTV");
			i.putExtra("type","where KinoTuri='mtv'");
			startActivity(i);
		}else if(p1==turlar){
			Intent i=new Intent();
			i.setClass(getContext(),Turlar.class);
			startActivity(i);
		}
		

	}
	
}
