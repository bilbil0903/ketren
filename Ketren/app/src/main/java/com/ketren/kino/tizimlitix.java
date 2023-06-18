package com.ketren.kino;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import com.ketren.kino.Utils.CountDownTimerUtils;
import com.mob.MobSDK;
import java.util.HashMap;
import android.content.Intent;

public class tizimlitix extends Activity implements View.OnClickListener {

    private EditText et_number;
    private EditText et_checkCode;
    private TextView tv_getCheckCode;
    private TextView tv_sendCheckCode;
    private String phoneNumber;
    private String checkCode;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.tizimlitix);
			et_number = (EditText) findViewById(R.id.et_number);
			et_checkCode = (EditText) findViewById(R.id.et_checkCode);
			tv_getCheckCode = (TextView) findViewById(R.id.tv_getCheckCode);
			tv_sendCheckCode = (TextView) findViewById(R.id.tv_sendCheckCode);
			checkCode = et_checkCode.getText().toString().trim();
			tv_getCheckCode.setOnClickListener(this);
			tv_sendCheckCode.setOnClickListener(this);
			MobSDK.init(this,"30c3ac303c308","83c4dd9dbe02c13171e206a0daac04d5");
			//注册短信回调
			SMSSDK.registerEventHandler(ev);
		}catch(Exception e){
			new AlertDialog.Builder(this).setMessage(e.getMessage()).show();
		}}
    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    final String phone = (String) mData.get("phone");//返回用户注册的手机
                    if (phone.equals(phoneNumber)) {
                        runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
								@Override
								public void run() {
									man.TOAST(tizimlitix.this,"غەلبىلىك تىزىملاتتىڭىز!");
									dialog.dismiss();
									login(phone,tizimlitix.this);
									finish();

								}
							});
                    } else {
                        runOnUiThread(new Runnable() {
								@Override
								public void run() {
									showDailog("مەغلۇپ بولدى!");
									dialog.dismiss();
								}
							});
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    private void showDailog(String text) {
        new AlertDialog.Builder(this)
			.setTitle(text)
			.setPositiveButton("بىلدىم", null)
			.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getCheckCode:
                getCheckCode();
                break;
            case R.id.tv_sendCheckCode:
                sendCheckCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    public void getCheckCode() {
        phoneNumber = et_number.getText().toString().trim();
        //发送短信，传入国家号和电话号码
        if (TextUtils.isEmpty(phoneNumber)&&phoneNumber.length()!=11) {
            man.TOAST(this,"يانفۇن نۇمۇرى قۇرۇق قالمىسۇن!");
        } else {
			CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_getCheckCode, 60000, 1000);
			mCountDownTimerUtils.start();
			SMSSDK.getVerificationCode("+86", phoneNumber);
            man.TOAST(this,"يوللاندى!");
        }
    }

    /**
     * 向服务器提交验证码，在监听回调中监听是否验证
     */
    private void sendCheckCode() {
        checkCode = et_checkCode.getText().toString();
        if (!TextUtils.isEmpty(checkCode)) {
            dialog = ProgressDialog.show(this, null, "كىرىۋاتىدۇ...", false, true);
			dialog.show();
            //提交短信验证码
            SMSSDK.submitVerificationCode("+86", phoneNumber, checkCode);//国家号，手机号码，验证码
		} else {
            man.TOAST(this, "يانفۇن نۇمۇرى قۇرۇق قالمىسۇن!");
        }
    }

    /**
     * Toast
     * @param info
     */
    

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(ev);
        super.onDestroy();
    }
	public static void login(String str,Activity A){
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS user(phone)");
		if(get_login(A)==null){
			sql.execSQL("INSERT INTO user VALUES('"+str+"');");
		}
	}
	public static void delete(Activity A){
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
			sql.execSQL("delete from user");
	}
	public  static  String get_login(Activity A){
		String s=null;
		SQLiteDatabase sql=A.openOrCreateDatabase("user.db",0,null);
		sql.execSQL("CREATE TABLE IF NOT EXISTS user(phone)");
		Cursor	c=sql.rawQuery("select*from user",null);
		if(c.getCount()>0){
			while(c.moveToNext()){
				s=c.getString(0);
			}}else{
			s=null;
		}
		return s;
	}
    public void baxka(View v){
        Intent i=new Intent();
        i.setClass(this,kirix.class);
        startActivity(i);
        finish();
    }
}
