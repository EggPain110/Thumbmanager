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
import android.widget.Button;
import android.widget.Toast;

import com.example.thumb.MyNewMainactivity;
import com.example.thumb.R;
import com.way.locus.LocusPassWordView.OnCompleteListener;
import com.way.util.StringUtil;

public class SetPasswordActivity extends Activity {
	private LocusPassWordView lpwv;
	private String password;
	private boolean needverify = true;
	private Toast toast;
	Button buttonSave,tvReset;
	SharedPreferences sp;
	private Vibrator vibrator; 
	boolean isfirstset=true;
	String firstpassword;
	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//�ر�ProgressDialog  
			vibrator.cancel(); 

		}};  


		private void showToast(CharSequence message) {
			if (null == toast) {
				toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
				//			toast.setGravity(Gravity.CENTER, 0, 0);
			} else {
				toast.setText(message);
			}

			toast.show();
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.setpassword_activity);
			sp=getSharedPreferences("SP", MODE_PRIVATE);
			lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
			lpwv.setOnCompleteListener(new OnCompleteListener() {
				@Override
				public void onComplete(String mPassword) {
					password = mPassword;
					if (needverify) {
						if (lpwv.verifyPassword(mPassword)) {
							showToast("����������ȷ,������������!");
							buttonSave.setVisibility(View.INVISIBLE);  
							tvReset.setVisibility(View.INVISIBLE);
							lpwv.clearPassword();
							needverify = false;


						} else {
							showToast("���������,����������!");
							lpwv.clearPassword();
							password = "";

							vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
							long [] pattern = {100,300,100,300};   // ֹͣ ���� ֹͣ ����   
							vibrator.vibrate(pattern,1); 


							//vibrator.cancel(); 
							//�½��߳�  
							new Thread(){  

								@Override  
								public void run() {  
									try {
										sleep(800);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//��handler����Ϣ  
									handler10.sendEmptyMessage(0); 
								}
							}.start(); 
						}
					}else{
						if(lpwv.getisfinish()){

							if(isfirstset){
								firstpassword=password;
								lpwv.myreset();
								isfirstset=false;
								Toast.makeText(getApplicationContext(), "���ٴ�������������", 1000).show();
							}else{
								
								if(firstpassword.equals(password)){
									if (StringUtil.isNotEmpty(password)) {

										lpwv.resetPassWord(password);

										lpwv.clearPassword();
										showToast("�����޸ĳɹ�,���ס����.");
										if(sp.getBoolean("is", false)){
											finish();

										}else{

											if(sp.getBoolean("openfrompersonal", false)){

												finish();
											}else{

												startActivity(new Intent(SetPasswordActivity.this,MyNewMainactivity.class));
												Editor editor=sp.edit();
												editor.putBoolean("issetcode", true);
												editor.commit();
												finish();
											}

										}

									} else {
										lpwv.clearPassword();
										showToast("���볤�ȹ���");
									}
								}else{
									Toast.makeText(getApplicationContext(), "������������벻һ�£�����������", 1000).show();
									isfirstset=true;
									lpwv.myreset();
									return;
								}
								

							}

						}else{}
					}
				}
			});

			OnClickListener mOnClickListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.tvSave:
						if (StringUtil.isNotEmpty(password)) {

							lpwv.resetPassWord(password);
							lpwv.clearPassword();
							showToast("�����޸ĳɹ�,���ס����.");
							if(sp.getBoolean("is", false)){

								finish();

							}else{

								if(sp.getBoolean("openfrompersonal", false)){

									finish();
								}else{

									startActivity(new Intent(SetPasswordActivity.this,MyNewMainactivity.class));
									Editor editor=sp.edit();
									editor.putBoolean("issetcode", true);
									editor.commit();
									finish();
								}

							}

						} else {
							lpwv.clearPassword();
							showToast("���볤�ȹ���");
						}
						break;
					case R.id.tvReset:
						lpwv.clearPassword();
						break;
					}
				}
			};
			buttonSave = (Button) this.findViewById(R.id.tvSave);
			buttonSave.setVisibility(View.INVISIBLE);  
			buttonSave.setOnClickListener(mOnClickListener);
			tvReset = (Button) this.findViewById(R.id.tvReset);
			tvReset.setVisibility(View.INVISIBLE);  
			tvReset.setOnClickListener(mOnClickListener);
			// �������Ϊ��,ֱ����������
			if (lpwv.isPasswordEmpty()) {
				this.needverify = false;
				Editor editor=sp.edit();
				editor.putBoolean("issetcode", true);
				editor.commit();
				showToast("����������");
			}
		}

		@Override
		protected void onStart() {
			super.onStart();
		}

		@Override
		protected void onStop() {
			super.onStop();
		}

}
