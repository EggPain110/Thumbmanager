package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Forgetcode extends Activity{
	private EditText edt1,edt2,edt3,edt4;
	private CheckBox check;
	Button btn1,btn2,btn3;
	long phonenum;
	ImageView img9,img10,img11;
	TextView tv10,tv1;
	String registercode;
	private MyProgressdialog myprodialog;

	//private ProgressDialog progressDialog;  
	Handler handler = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");

			//String statecode=myresult.getString("StateCode");
		};  
	};  
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			handler3.sendEmptyMessage(0);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){

					Toast.makeText(getApplicationContext(), "修改成功", 1000).show();
					finish();
				}else if(statecode.equals("801")){

					Toast.makeText(getApplicationContext(), "修改失败", 1000).show();
				}else if(statecode.equals("807")){
					Toast.makeText(getApplicationContext(), "验证码失效，请重新获取", 1000).show();
				}



			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
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

				//edt2.addTextChangedListener(watcher);			//添加edittext变化监听；

				edt3=(EditText)super.findViewById(R.id.editText3);
				edt4=(EditText)super.findViewById(R.id.editText4);
				
				Button btn5=(Button) findViewById(R.id.button5);
				
				check=(CheckBox)super.findViewById(R.id.checkBox1);
				
				CheckBox check1=(CheckBox) findViewById(R.id.checkBox2);
				
				
				check.setVisibility(View.INVISIBLE);
				check1.setVisibility(View.INVISIBLE);
				btn5.setVisibility(View.INVISIBLE);

				tv1=(TextView) findViewById(R.id.textView1);
				tv1.setText("修改密码");
				edt2.setHint("请输入新的密码");
				edt3.setHint("请再次输入新的密码");

				RelativeLayout relaytive=(RelativeLayout) findViewById(R.id.layout12);
				relaytive.setVisibility(View.INVISIBLE);

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

				btn1=(Button) findViewById(R.id.button1);
				btn2=(Button) findViewById(R.id.button2);
				btn3=(Button) findViewById(R.id.button3);
				btn3.setText("修改密码");
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
							String urlstring="GetVerifyCode/Cust_Mobile="+phonenum;
							MyAsyncTask myAsyncTask=new MyAsyncTask();
							if(myAsyncTask.isNetworkConnected(getApplicationContext())){
								myAsyncTask.setHandler(handler);
								myAsyncTask.execute(urlstring);
								new Thread(){
									@Override  
									public void run() {  

										for (int k =60; k > 0; k--) {

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

						String captcha = edt4.getText().toString();     //验证码

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

						if(!registercode.equals(registercoderepite)){
							Toast.makeText(getApplicationContext(), "两次输入的密码不一致", 1000).show();
							return;
						}else{}


						Md5 md5=new Md5();
						String code=md5.stringToMD5(registercode);
						String urlstring="ResetLoginPass/Cust_Mobile="+phonenum+"&Cust_VerifyCode="+captcha+"&Cust_LoginPass="+code;
						MyAsyncTask myAsyncTask=new MyAsyncTask();	

						if(registercode.equals(registercoderepite)){
							if(myAsyncTask.isNetworkConnected(getApplicationContext())){
								myAsyncTask.setHandler(handler1);
								myAsyncTask.execute(urlstring);

								myprodialog=new MyProgressdialog(Forgetcode.this);
								myprodialog.show();
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
							}


						}else{
							Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
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
						//ascallcheckpassword(passwordStr);
					}else{
						img9.setImageResource(R.drawable.huipoin);
						img10.setImageResource(R.drawable.huipoin);
						img11.setImageResource(R.drawable.huipoin);
						tv10.setText("无");
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
				switch (a+b+c) {
				case 0:
					img9.setImageResource(R.drawable.huipoin);
					img10.setImageResource(R.drawable.huipoin);
					img11.setImageResource(R.drawable.huipoin);
					tv10.setText("无");

					break;
				case 1:
					img9.setImageResource(R.drawable.coderedpiont);
					img10.setImageResource(R.drawable.huipoin);
					img11.setImageResource(R.drawable.huipoin);
					tv10.setText("低");
					break;
				case 2:
					img9.setImageResource(R.drawable.coderedpiont);
					img10.setImageResource(R.drawable.codeyellowpiont);
					img11.setImageResource(R.drawable.huipoin);
					tv10.setText("中");

					break;
				case 3:
					img9.setImageResource(R.drawable.coderedpiont);
					img10.setImageResource(R.drawable.codeyellowpiont);
					img11.setImageResource(R.drawable.codegerrenpiont);
					tv10.setText("高");

					break;

				default:
					break;
				}

			}




			public void jason(String result){

				try {
					JSONObject jobject=new JSONObject(result);
					JSONObject myresult=jobject.getJSONObject("result");
					String city=myresult.getString("citynm");
					//tv5.setText(city);
					String tm=myresult.getString("temperature");
					String[] arr=tm.split("/");
					//tv6.setText(arr[0]);
					//tv7.setText(arr[1]);
					String weather=myresult.getString("weather");
					//tv8.setText(weather);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}




}

