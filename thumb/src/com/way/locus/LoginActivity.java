package com.way.locus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


import com.example.thumb.MyNewMainactivity;
import com.example.thumb.R;
import com.way.locus.LocusPassWordView.OnCompleteListener;

public class LoginActivity extends Activity {
	private LocusPassWordView lpwv;
	private Vibrator vibrator; 
	private Toast toast;
	SharedPreferences sp;
	int x;

	private void showToast(CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}

		toast.show();
	}

	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  
			vibrator.cancel(); 

		}};  

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.login_activity);
			sp=getSharedPreferences("SP", MODE_PRIVATE);
			/*if(!sp.getBoolean("issetcode", false)){
			startActivity(new Intent(LoginActivity.this,SetPasswordActivity.class));
			finish();
		}else{}*/
			x=4;
			TextView tv1=(TextView) findViewById(R.id.login_toast);
			TextView tv2=(TextView) findViewById(R.id.login_toast2);

			tv1.setText(sp.getString("custname", ""));
			tv2.setText(sp.getString("custmobile", ""));
			lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
			lpwv.setOnCompleteListener(new OnCompleteListener() {
				@Override
				public void onComplete(String mPassword) {
					// 如果密码正确,则进入主页面。
					if (lpwv.verifyPassword(mPassword)) {
						showToast("解锁成功！");
						Intent intent = new Intent(LoginActivity.this,
								MyNewMainactivity.class);


						// 打开新的Activity
						startActivity(intent);
						finish();
					} else {
						x--;
						if(x==0){
							lpwv.resetPassWord("");
							Editor editor=sp.edit();
							editor.clear();
							editor.putBoolean("isfaulttoomuch", true);
							editor.commit();
							
							finish();
						}else{
							showToast("密码输入错误,请重新输入,还有"+x+"次机会");
						}

						lpwv.myreset();
						lpwv.markError();
						vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
						long [] pattern = {100,300,100,300};   // 停止 开启 停止 开启   
						vibrator.vibrate(pattern,1); 


						//vibrator.cancel(); 
						//新建线程  
						new Thread(){  

							@Override  
							public void run() {  
								try {
									sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//向handler发消息  
								handler10.sendEmptyMessage(0); 
							}
						}.start(); 
					}
				}
			});

		}

		@Override
		protected void onStart() {
			super.onStart();
			// 如果密码为空,则进入设置密码的界面
			View noSetPassword = (View) this.findViewById(R.id.tvNoSetPassword);
			TextView toastTv = (TextView) findViewById(R.id.login_toast);
			TextView toastTv2 = (TextView) findViewById(R.id.login_toast2);
			if (lpwv.isPasswordEmpty()) {
				lpwv.setVisibility(View.GONE);
				noSetPassword.setVisibility(View.VISIBLE);
				toastTv.setText("请先绘制手势密码");
				
				Editor editor=sp.edit();
				editor.putBoolean("openfrompersonal", false);
				editor.commit();
				noSetPassword.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(LoginActivity.this,
								SetPasswordActivity.class);
						// 打开新的Activity
						startActivity(intent);
						finish();
					}

				});
			} else {
			
				lpwv.setVisibility(View.VISIBLE);
				noSetPassword.setVisibility(View.GONE);
			}
		}

		@Override
		protected void onStop() {
			super.onStop();
		}

}
