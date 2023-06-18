package com.ketren.kino.Utils;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class BitmapUtils {
    public static  Bitmap bimapRound(Bitmap mBitmap,float index){
        Bitmap bitmap =Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        RectF rectf = new RectF(rect);
        canvas.drawRoundRect(rectf, 10, 10, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, rect, rect, paint);   
		return bitmap;

    }}
