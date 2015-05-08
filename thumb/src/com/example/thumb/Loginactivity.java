package com.example.thumb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginactivity extends Activity{
	Button btn1,btn2,btn3;
	EditText edt1,edt2;
	//private ProgressDialog progressDialog;
	SharedPreferences sp;

	private MyProgressdialog myprodialog;

	Handler handler = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			handler10.sendEmptyMessage(0);
			String string=msg.obj.toString();
			//Log.i("loginmessage",  msg.obj+"");
			String statecode;
			try {
				JSONObject jobject=new JSONObject(string);
				statecode = jobject.getString("StateCode");


				if(statecode.equals("808")){
					JSONArray jsonarray=jobject.getJSONArray("DataObj");
					
					JSONObject  jobject1=new JSONObject(jsonarray.getJSONObject(0).toString());

					Editor editor = sp.edit();
					editor.putBoolean("islogin", true);
					Long tsLong = System.currentTimeMillis()/1000;
					editor.putLong("lastlogintime", tsLong);
					

					//Log.i("loginmessage", jobject1.getString("Cust_ID"));
					editor.putString("custid", jobject1.getString("Cust_ID"));
					editor.putString("istradepass", jobject1.getString("IsTradePass"));
					editor.putString("custmobile", jobject1.getString("Cust_Mobile").substring(0, 3)+"****"+jobject1.getString("Cust_Mobile").substring(7, 11));
					editor.putBoolean("istruename", jobject1.getString("IsRealNameAuth").equals("true"));
					editor.putString("pin", jobject1.getString("Cust_PIN"));
					editor.putString("custinvite", jobject1.getString("Cust_RecoMark"));

					//editor.putString("custname", "*"+jobject1.getString("Cust_Name").substring(1,jobject1.getString("Cust_Name").length()));
					if("".equals(jobject1.getString("Cust_Name"))){

					}else{
						editor.putBoolean("isfirst", false);
						editor.putString("custname", "*"+jobject1.getString("Cust_Name").substring(1,jobject1.getString("Cust_Name").length()));
						editor.putString("userid", jobject1.getString("Cust_CertID").substring(0, 5)+"*********"+jobject1.getString("Cust_CertID").substring(jobject1.getString("Cust_CertID").length()-4, jobject1.getString("Cust_CertID").length()));
					}
					editor.commit();
					Toast.makeText(getApplicationContext(), "登录成功", 1000).show();
					finish();
					//startActivity(new Intent(Loginactivity.this,Buyactivity1.class));
				}else{
					Toast toast = Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"),
							Toast.LENGTH_LONG);
					//可以控制toast显示的位置
					toast.setGravity(Gravity.BOTTOM, 0, 500);
					toast.show();
					//Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			//String statecode=myresult.getString("StateCode");
		};  
	};

	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  

			myprodialog.dismiss();
		}};  


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.login);
			sp = getSharedPreferences("SP",MODE_PRIVATE);
			btn1=(Button) findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Loginactivity.this,Registeractivity.class));
				}
			});
			btn2=(Button) findViewById(R.id.button2);
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//startActivity(new Intent(Loginactivity.this,Buyactivity1.class));


					edt1=(EditText) findViewById(R.id.editText1);
					edt1.setFocusable(true);
					edt1.setFocusableInTouchMode(true);   
					edt1.requestFocus(); 
					
					
					
					edt2=(EditText) findViewById(R.id.editText2);
					String phonenum=edt1.getText().toString().trim();
					if("".equals(edt1.getText().toString().trim())){
						Toast.makeText(getApplicationContext(), "请输入手机号", 1000).show();
						return;
					}

					if("".equals(edt2.getText().toString().trim())){
						Toast.makeText(getApplicationContext(), "请输入密码", 1000).show();
						return;
					}

					if(edt1.getText().toString().length()==11){


						Md5 md5=new Md5();
						String code=md5.stringToMD5(edt2.getText().toString());
						String urlstring="CheckLogin/Cust_Mobile="+phonenum+"&Cust_LoginPass="+code+"&ClientId="+
								sp.getString("ClientID", "0");
						MyAsyncTask myAsyncTask=new MyAsyncTask();	

						if(myAsyncTask.isNetworkConnected(getApplicationContext())){
							myAsyncTask.setHandler(handler);
							myAsyncTask.execute(urlstring);


							myprodialog=new MyProgressdialog(Loginactivity.this);
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
									handler10.sendEmptyMessage(0);  
								}
							}.start(); 
						}else{
							Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
						}

					}else{
						Toast.makeText(getApplicationContext(), "用户名错误", 1000).show();
					}


				}
			});


			btn3=(Button) findViewById(R.id.button3);
			btn3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Loginactivity.this,Forgetcode.class));
				}
			});

		}

}
