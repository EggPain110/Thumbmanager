package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Recharge extends Activity{

	Button btn1,btn3,btn4;
	SharedPreferences sp;
	EditText edt;
	
	CheckBox cbx1;
	//private ProgressDialog progressDialog; 
	
	private MyProgressdialog myprodialog;

	
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
						String tn=jobject.getString("DataObj");
						Intent intent=new Intent(Recharge.this,JARActivity.class);
						intent.putExtra("tn", tn);
						intent.putExtra("isrecharge", true);
						startActivity(intent);
						finish();
					}else{
						Toast.makeText(getApplicationContext(), "系统错误，请稍后再试", 1000).show();
						finish();
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
		setContentView(R.layout.recharge);
		sp= getSharedPreferences("SP",MODE_PRIVATE);
		
		Editor editor=sp.edit();
		editor.putBoolean("isneedfreesh", true);
		editor.commit();
		
		edt=(EditText) findViewById(R.id.editText1);
		btn4=(Button) findViewById(R.id.button4);
		btn1=(Button) findViewById(R.id.button1);
		
		btn1.setEnabled(false);
		btn1.setBackgroundResource(R.drawable.redbutton2);
		
		btn3=(Button) findViewById(R.id.button3);
		
		cbx1=(CheckBox) findViewById(R.id.checkBox1);

		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Recharge.this,Serviceagreement.class));
			}
		});

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(edt.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入充值金额", 1000).show();
					return;
				}else{}
				
				if(Integer.parseInt(edt.getText().toString())>20000){
					Toast.makeText(getApplicationContext(), "单笔单次最大交易额为20000元", 1000).show();
					return;
				}else{}

				String urlstring="AddRecharge/Cust_ID="+sp.getString("custid","无")+"&Amount="+ edt.getText().toString().trim();

				MyAsyncTask myAsyncTask=new MyAsyncTask();	
				if(myAsyncTask.isNetworkConnected(getApplicationContext())){
					myAsyncTask.setHandler(handler1);
					myAsyncTask.execute(urlstring);

					myprodialog=new MyProgressdialog(Recharge.this);
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
		
		
		cbx1.setOnClickListener(new OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				if(cbx1.isChecked())
				{
					btn1.setEnabled(true);
					btn1.setBackgroundResource(R.drawable.hongseanniu);
				}else
				{
					btn1.setEnabled(false);
					btn1.setBackgroundResource(R.drawable.redbutton2);
				}
			}

		});

	}


}
