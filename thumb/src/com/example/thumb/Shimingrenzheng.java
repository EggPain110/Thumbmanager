package com.example.thumb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Shimingrenzheng extends Activity{
	Button btn1,btn2;
	EditText edt1,edt2;
	SharedPreferences sp;
	private MyProgressdialog myprodialog;

	//private ProgressDialog progressDialog; 

	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  

			myprodialog.dismiss();


		}};  


		Handler handler1 = new Handler() {  
			public void handleMessage(android.os.Message msg) { 
				//xx.setProgress(msg.what);
				handler10.sendEmptyMessage(0);
				String string=msg.obj.toString();
				//Log.i("123123qwe", string);
				try {
					JSONObject jobject=new JSONObject(string);
					String statecode=jobject.getString("StateCode");

					if(statecode.equals("800")){
						Editor editor=sp.edit();
						editor.putBoolean("istruename", true);
						editor.putString("custname", "*"+edt1.getText().toString().trim().substring(1,edt1.getText().toString().trim().length()));
						editor.commit();
						Toast.makeText(getApplicationContext(), "实名认证成功", 1000).show();
						finish();

					}else{

						Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
					}


				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//String city=myresult.getString("citynm");
			};  
		};  


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.shimingrenzheng);
			sp=getSharedPreferences("SP", MODE_PRIVATE);

			edt1=(EditText) findViewById(R.id.editText1);
			
			edt1.setFocusable(true);
			edt1.setFocusableInTouchMode(true);   
			edt1.requestFocus();  
			
			
			edt2=(EditText) findViewById(R.id.editText2);
			btn1=(Button) findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			btn2=(Button) findViewById(R.id.button2);
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if("".equals(edt1.getText().toString().trim())){
						Toast.makeText(getApplicationContext(), "请输入您的姓名", 1000).show();
						return;
					}else{}


					if("".equals(edt2.getText().toString().trim())){
						Toast.makeText(getApplicationContext(), "请输入您的身份证号", 1000).show();
						return;
					}else{}

					String custcertid=edt2.getText().toString().trim();

					String id18="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)$";
					if(custcertid.length()==18){
						Pattern pattern = Pattern.compile(id18);
						Matcher matcher = pattern.matcher(custcertid);
						if(!matcher.matches()){
							Toast.makeText(getApplicationContext(), "请输入正确的身份证号码", 1000).show();
							return;
						}
					}else{
						Toast.makeText(getApplicationContext(), "请输入正确的身份证号码", 1000).show();
						return;
					}

					String urlstring="RealNameAuth/Cust_ID="+sp.getString("custid","无")+"&Cust_Name="+ edt1.getText().toString().trim()+"&Cust_CertID="+edt2.getText().toString().trim();

					MyAsyncTask myAsyncTask=new MyAsyncTask();	
					if(myAsyncTask.isNetworkConnected(getApplicationContext())){
						myAsyncTask.setHandler(handler1);
						myAsyncTask.execute(urlstring);

						myprodialog=new MyProgressdialog(Shimingrenzheng.this);
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


				}
			});

		}

}
