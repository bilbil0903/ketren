package com.ketren.kino;

import android.content.*;
import android.util.*;
import android.view.View.*;
import android.widget.*;
import com.ketren.kino.Pull.PullableScrollView;

public class MyGridView extends GridView
 {

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context) {
        this(context, null);
    }
	@Override     
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>> 2,
													 MeasureSpec.AT_MOST);            
		super.onMeasure(widthMeasureSpec, expandSpec);
    }
	
}
