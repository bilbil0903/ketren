package com.ketren.kino;

import android.Manifest;
import android.annotation.NonNull;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.ketren.kino.Utils.Base64Object;
import org.json.JSONArray;

public class manMain extends Activity {

    private ImageView icon;
    private EditText edit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.man_main);
     icon=(ImageView)findViewById(R.id.bax);
      edit=(EditText)findViewById(R.id.user);
        
     GET();
		}
    //打开相册选择图片
    public void image(View v) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else{
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);//打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this, "You denied the permision.", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if(resultCode == RESULT_OK){
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT >= 19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else{
                        //4.4以下系统使用这个方法处理图片
                        handeleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
//    Toast.makeText(this,"到了handleImageOnKitKat(Intent data)方法了", Toast.LENGTH_LONG).show();
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            //如果是 document 类型的 Uri，则通过 document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的 id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是 content 类型的 uri ， 则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是 file 类型的 Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//显示选中的图片
    }

    private void handeleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过 Uri 和 selection 来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            icon.setImageBitmap(toRoundCorner(zoomBitmap(bitmap,120,120),360));
            //bitmap=Base64Object.bitmapToBase641(bitmap);
            String str=Base64Object.bitmapToBase641(toRoundCorner(zoomBitmap(bitmap,120,120),360));
            Toast.makeText(this,"سەل ساقلاڭ!",0).show();
          upload(str);
        }else{
            Toast.makeText(this,"failed to get image", Toast.LENGTH_LONG).show();
        }
	}
    
    public void upload(String icon){
        String user=tizimlitix.get_login((Activity)this);
        HttpUtils.httpRequest(this,U.url+"kino.php?type=xiugai","POST=ENTER&&user="+user+"&&icon="+icon,new Handler() {
                public   void handleMessage(Message msg) {
                   Toast.makeText(manMain.this,"غەلبىلىك بولدى!",0).show();
                   finish();
                    }

            }); 

	}
    public void ok(View  v){
        Toast.makeText(this,"سەل ساقلاڭ!",0).show();
        EditText e1=(EditText)findViewById(R.id.user);
        EditText e3=(EditText)findViewById(R.id.parol2);
        String user=tizimlitix.get_login((Activity)this);
        HttpUtils.httpRequest(this,U.url+"kino.php?type=xiugai","POST=ENTER&&user="+user+"&&isim="+e1.getText().toString()+"&&parol2="+e3.getText().toString(),new Handler() {
                public   void handleMessage(Message msg) {
                    Toast.makeText(manMain.this,"غەلبىلىك بولدى!",0).show();
                    finish();
                    }
            }); 

	}
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
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
    
    public void GET(){
        String user=tizimlitix.get_login((Activity)this);
        HttpUtils.httpRequest((Activity)this,U.url+"kino.php?type=get_user","POST=ENTER&&user="+user,new Handler() {
                public   void handleMessage(Message msg) {
                    try{
                        String s=(String)msg.obj;
                        if(s!=""&&s!=null&&s.length()>5){}
                        JSONArray ja=new JSONArray(s);
                        String S=ja.getString(1);
                        String S0=ja.getString(0);
                        if(S!=null&&S!=""&&S.length()>10){
                            Bitmap b=Base64Object.base64ToBitmap(S.replaceAll(" ","+"));
                            icon.setImageDrawable(new BitmapDrawable(manMain.toRoundCorner(b,360)));
                        }
                        edit.setText(S0);
                    }catch(Exception e){
                        new AlertDialog.Builder(manMain.this).setMessage(e.getMessage()).show();
                    }
                }
            }); 

    }
    
}
