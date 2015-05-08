package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;




import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Registeractivity extends Activity{
	private EditText edt1,edt2,edt3,edt4,edt5;

	Button btn1,btn2,btn3,btn5;
	long phonenum;
	private CheckBox check,cbx1;
	String captcha;
	int k;
	String registercode;
	private MyProgressdialog myprodialog;
	//private ProgressDialog progressDialog;  
	Handler handler = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");
				if(statecode.equals("812")){

					Toast.makeText(getApplicationContext(), "发送成功", 1000).show();

				}else{
					if(statecode.equals("802")){
						k=1;
						handler4.sendEmptyMessage(k);
					}else{}
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String statecode=myresult.getString("StateCode");
		};  
	};  
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					//Log.i("123123qwe",  806+"");
					rejister();

				}else{
					handler3.sendEmptyMessage(0);
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
		};  
	};  


	Handler handler2 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			handler3.sendEmptyMessage(0);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){

					Toast.makeText(getApplicationContext(), "注册成功", 1000).show();
					finish();
				}else{
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};  
	};  


	private Handler handler3 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  

			myprodialog.dismiss();



		}};  

		private Handler handler4 = new Handler(){  

			@Override  
			public void handleMessage(Message msg) {  


				btn2.setText(msg.what+"秒后");
				if(msg.what==59){
					btn2.setClickable(false);

				}else if(msg.what==1){
					btn2.setClickable(true);
					btn2.setText("获取验证码");
				}


			}};  



			@Override
			protected void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.registeractivity);

				edt1=(EditText)super.findViewById(R.id.editText1);
				
				edt1.setFocusable(true);
				edt1.setFocusableInTouchMode(true);   
				edt1.requestFocus();  
				
				edt2=(EditText)super.findViewById(R.id.editText2);

				edt2.addTextChangedListener(watcher);			//添加edittext变化监听；

				edt3=(EditText)super.findViewById(R.id.editText3);
				edt4=(EditText)super.findViewById(R.id.editText4);
				edt5=(EditText)super.findViewById(R.id.editText5);
				
				btn1=(Button) findViewById(R.id.button1);
				btn2=(Button) findViewById(R.id.button2);
				btn3=(Button) findViewById(R.id.button3);
				
				btn5= (Button) findViewById(R.id.button5);
				
				btn3.setEnabled(false);
				btn3.setBackgroundResource(R.drawable.redbutton2);
				
				check=(CheckBox)super.findViewById(R.id.checkBox1);
				cbx1=(CheckBox) super.findViewById(R.id.checkBox2);
				
				
				btn5.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Registeractivity.this,Rejistagreement.class));
					}
				});


				//为check设置监听选项，控制密码框的显示方式
				check.setOnClickListener(new OnClickListener()
				{ 
					@Override
					public void onClick(View v) 
					{
						if(check.isChecked())
						{
							//设置密码可见
							edt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
							edt3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
						}
						else
						{
							//设置密码隐藏
							edt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
							edt3.setTransformationMethod(PasswordTransformationMethod.getInstance());
						}
					}

				});
				
				
				cbx1.setOnClickListener(new OnClickListener()
				{ 
					@Override
					public void onClick(View v) 
					{
						if(cbx1.isChecked())
						{
							btn3.setEnabled(true);
							btn3.setBackgroundResource(R.drawable.hongseanniu);
						}else
						{
							btn3.setEnabled(false);
							btn3.setBackgroundResource(R.drawable.redbutton2);
						}
					}

				});

				
				btn1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finish();
					}
				});

				btn2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						

						try {
							phonenum = Long.parseLong(edt1.getText().toString());
							if(Long.toString(phonenum).length()!=11){
								Toast.makeText(getApplicationContext(), "请输入正确的手机号", 1000).show();
								return;
							}
							
							
							String urlstring="GetRegVerifyCode/Cust_Mobile="+phonenum;
							MyAsyncTask myAsyncTask=new MyAsyncTask();
							if(myAsyncTask.isNetworkConnected(getApplicationContext())){
								myAsyncTask.setHandler(handler);
								myAsyncTask.execute(urlstring);
								new Thread(){
									@Override  
									public void run() {  

										for (k =60; k > 0; k--) {

											handler4.sendEmptyMessage(k);
											try {
												sleep(1000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}


									}

								}.start();

							}else{
								Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(), "请输入手机号", 1000).show();
						}
						//demo("0");

					}
				});

				btn3.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub


						try {
							phonenum = Long.parseLong(edt1.getText().toString());

						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(), "请输入手机号", 1000).show();
						}

						
						
						registercode = edt2.getText().toString();

						String registercoderepite =edt3.getText().toString();

						captcha = edt4.getText().toString();     //验证码

						if("".equals(edt4.getText().toString().trim())){
							Toast.makeText(getApplicationContext(), "请输入验证码", 1000).show();
							return;
						}

						if("".equals(edt2.getText().toString().trim())||"".equals(edt3.getText().toString().trim())){
							Toast.makeText(getApplicationContext(), "请输入密码", 1000).show();
							return;
						}

						if(registercode.length()<=5||registercoderepite.length()<=5){
							Toast.makeText(getApplicationContext(), "密码长度过短", 1000).show();
							return;
						}

						if(captcha.length()<6){
							Toast.makeText(getApplicationContext(), "请输入正确的验证码", 1000).show();
						}

						if(captcha.length()==6&&registercode.length()>5&&registercoderepite.length()>5){

							if("".equals(edt5.getText().toString().trim())){
								rejister();

								myprodialog=new MyProgressdialog(Registeractivity.this);
								myprodialog.show();
								//新建线程  
								new Thread(){  

									@Override  
									public void run() {  
										try {
											sleep(10000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										//向handler发消息  
										handler3.sendEmptyMessage(0);  
									}
								}.start(); 

							}else{
								String urlstring="CheckRecoMark/RecoMark="+edt5.getText().toString().trim();
								MyAsyncTask myAsyncTask=new MyAsyncTask();	
								if(registercode.equals(registercoderepite)){
									if(myAsyncTask.isNetworkConnected(getApplicationContext())){
										myAsyncTask.setHandler(handler1);
										myAsyncTask.execute(urlstring);

										myprodialog=new MyProgressdialog(Registeractivity.this);
										myprodialog.show();
										//新建线程  
										new Thread(){  

											@Override  
											public void run() {  
												try {
													sleep(10000);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												//向handler发消息  
												handler3.sendEmptyMessage(0);  
											}
										}.start(); 

									}else{
										Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
									}
								}else{
									Toast.makeText(getApplicationContext(), "两次输入的密码不一致", 1000).show();
									return;
								}


							}

						}

					}


				});

			}

			TextWatcher watcher=new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub

					String passwordStr = edt2.getText().toString();

					if(passwordStr.length()>5){
						ascallcheckpassword(passwordStr);
					}else{

					}

				}
			};

			private void ascallcheckpassword(String passwordStr) {
				// TODO Auto-generated method stub
				int a=0,b=0,c=0;
				for (int i = 0; i < passwordStr.length(); i++) {

					char ch=passwordStr.charAt(i); 
					//Log.i("123123qwe", ch+"");
					int ascall=(int) ch;
					//Log.i("123123qwe", ascall+"");
					if(ascall>31&&ascall<=47){
						a=1;							//特殊符号
					}else if(ascall>47&&ascall<=57){
						b=1;							// 数字
					}else if(ascall>57&&ascall<=64){
						a=1;							//特殊符号
					}else if(ascall>64&&ascall<=90){
						c=1;							//大写字母
					}else if(ascall>90&&ascall<=96){
						a=1;							//特殊符号
					}else if(ascall>96&&ascall<=122){
						c=1;							//小写字母
					}else if(ascall>122&&ascall<=126){
						a=1;							//特殊符号
					}else{}
				}


			}


			private void rejister() {
				// TODO Auto-generated method stub
				Md5 md5=new Md5();
				String code=md5.stringToMD5(registercode);
				String urlstring="AddUser/Cust_Mobile="+phonenum+"&Cust_LoginPass="+code+"&Cust_APPVersion="+"1.0"+"&Cust_RegDevice="+"Android"+"&Cust_FromRecoMark="+edt5.getText().toString()+"&Cust_VerifyCode="+captcha;
				MyAsyncTask myAsyncTask=new MyAsyncTask();	

				if(myAsyncTask.isNetworkConnected(getApplicationContext())){
					myAsyncTask.setHandler(handler2);
					myAsyncTask.execute(urlstring);
				}

			}



}
