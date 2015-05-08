package com.example.thumb;


import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgettradecode extends Activity{
	Button btn1,btn2,btn3;
	EditText edt1,edt2;
	//private ProgressDialog progressDialog;  
	String custcertid;
	SharedPreferences sp;




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

					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String statecode=myresult.getString("StateCode");
		};  
	};  


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
			setContentView(R.layout.fogettradecode);
			sp=getSharedPreferences("SP", MODE_PRIVATE);
			edt1=(EditText) findViewById(R.id.editText1);



			btn1=(Button) findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

			btn2=(Button) findViewById(R.id.button3);
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					String urlstring="GetVerifyCode/Cust_ID="+sp.getString("custid","无");
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

				}
			});

			btn3=(Button) findViewById(R.id.button2);
			btn3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if("".equals(edt1.getText().toString().trim())){
						Toast.makeText(getApplicationContext(), "请输入验证码", 1000).show();
						return;
					}

					finish();
					Intent intent=new Intent(Forgettradecode.this,Tradecodeactivity.class);
					intent.putExtra("verifycode", edt1.getText().toString().trim());
					startActivity(intent);	

				}	
			});


		}

}
