package com.ketren.kino;


import android.content.*;
import android.graphics.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.graphics.drawable.BitmapDrawable;


public class MyImageView
extends ImageView
{

    public MyImageView(Context context) {
        super(context);
        this.Yumilak(context);
    }

    public MyImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Yumilak(context);
    }

    public MyImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.Yumilak(context);
        
    }

    private void Yumilak(Context context) {
        BitmapDrawable bd=(BitmapDrawable)this.getBackground();
        Bitmap b=bd.getBitmap();
        b=toRoundCorner(b,360);
        this.setBackground(new BitmapDrawable(b));
        }
    
    
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {  
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
                                            bitmap.getHeight(),Bitmap. Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
        final int color = 0xff424242;  
        final Paint paint = new Paint();  
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        final RectF rectF = new RectF(rect);  
        final float roundPx = pixels;  
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(color);  
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));  
        canvas.drawBitmap(bitmap, rect, rect, paint);  
        return output;  
    }
    }

