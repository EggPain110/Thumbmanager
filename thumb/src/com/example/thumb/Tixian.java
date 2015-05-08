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
import android.widget.TextView;
import android.widget.Toast;

public class Tixian  extends Activity{
	Button btn1,btn2;
	EditText edt1,edt3,edt4;
	SharedPreferences sp;
	private CheckBox check;
	TextView tv5;
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
		setContentView(R.layout.tixian);
		sp=getSharedPreferences("SP", MODE_PRIVATE);
		
		Editor editor=sp.edit();
		editor.putBoolean("isneedfreesh", true);
		editor.commit();
		edt1=(EditText) findViewById(R.id.editText1);
		edt1.setEnabled(false);
		edt1.setHint(sp.getString("custname", ""));
		//edt2=(EditText) findViewById(R.id.editText2);
		edt3=(EditText) findViewById(R.id.editText3);
		edt4=(EditText) findViewById(R.id.editText4);
		
		tv5=(TextView) findViewById(R.id.textview5);
		
		tv5.setText(sp.getString("chosencard", "请输入收款人储蓄卡号"));
		tv5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Tixian.this,Chosebankcard.class));
			}
		});
		
		Button btn4=(Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		check=(CheckBox)super.findViewById(R.id.checkBox1);
		
		check.setOnClickListener(new OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				if(check.isChecked())
				{
					btn1.setEnabled(true);
					btn1.setBackgroundResource(R.drawable.hongseanniu);
				}
				else
				{
					btn1.setEnabled(false);
					btn1.setBackgroundResource(R.drawable.redbutton2);
				}
			}

		});

		btn1=(Button) findViewById(R.id.button1);
		btn1.setEnabled(false);
		btn1.setBackgroundResource(R.drawable.redbutton2);
		btn2=(Button) findViewById(R.id.button2);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(Tixian.this,Tishixiangqing.class));
				
				
				
				if("".equals(edt3.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入提现金额", 1000).show();
					return;
				}

				if("".equals(edt4.getText().toString().trim())||"".equals(edt3.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入短信验证码", 1000).show();
					return;
				}
				
				if("请输入收款人储蓄卡号".equals(tv5.getText())){
					Toast.makeText(getApplicationContext(), "请选择银行卡", 1000).show();
					return;
				}else{}
				
				//String card=edt2.getText().toString().trim();
				String number=edt3.getText().toString().trim();
				String verifycode=edt4.getText().toString().trim();
				
				Intent intent=new Intent(Tixian.this,InputTradecode.class);
				intent.putExtra("isAddOutCharge", true);
			
				intent.putExtra("card", sp.getString("chosencardid", "无"));
				intent.putExtra("number", number);
				intent.putExtra("verifycode", verifycode);
				startActivity(intent);
				finish();
				//String urlstring="AddOutCharge/Cust_ID="+sp.getString("custid","无")+"&Cust_TradePass="+ edt.getText().toString().trim();

			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

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

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		tv5.setText(sp.getString("chosencard", "请输入收款人储蓄卡号"));
	}
}
