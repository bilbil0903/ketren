package com.ketren.kino;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;


public class UyTextView
extends TextView
{

    public UyTextView(Context context) {
        super(context);
        this.loadTypeFace(context);
    }

    public UyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.loadTypeFace(context);
    }

    public UyTextView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.loadTypeFace(context);
    }

    private void loadTypeFace(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "UyghurFont.ttf"));
	}
	
}

