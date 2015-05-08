package com.example.thumb;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class InputTradecode extends Activity implements View.OnClickListener{
	Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn13,btn15;
	ImageView img1,img2,img3,img4,img5,img6;
	String[] array;
	ImageView[] img;
	TextView tv1,tv2,tv4;
	int j;
	SharedPreferences sp;
	StringBuffer code;
	String urlstring;
	Intent intent;

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

						if(intent.getBooleanExtra("isAddOutCharge", false)){
							Toast.makeText(getApplicationContext(), "提现申请已成功提交，请注意关注您的账户动态", 1000).show();

							finish();
						}else{
							Toast.makeText(getApplicationContext(), "购买成功", 1000).show();
							finish();
						}

					}else if(statecode.equals("821")){
						Intent intent1=new Intent(InputTradecode.this,JARActivity.class);
						String tn=jobject.getString("DataObj");
						intent1.putExtra("tn", tn);
						intent1.putExtra("isrecharge", false);
						startActivity(intent1);
						finish();

					}else{
						btn15.setEnabled(true);
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
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.tradecode);
			sp=getSharedPreferences("SP",MODE_PRIVATE);


			tv1=(TextView) findViewById(R.id.textView1);
			tv2=(TextView) findViewById(R.id.textView2);
			tv4=(TextView) findViewById(R.id.textView4);

			tv1.setText("输入交易密码");
			tv2.setText("");
			tv4.setText("");
			array=new String[]{"a","a","a","a","a","a"};

			img1=(ImageView) findViewById(R.id.imageView1);
			img2=(ImageView) findViewById(R.id.imageView2);
			img3=(ImageView) findViewById(R.id.imageView3);
			img4=(ImageView) findViewById(R.id.imageView4);
			img5=(ImageView) findViewById(R.id.imageView5);
			img6=(ImageView) findViewById(R.id.imageView6);

			img=new ImageView[]{img1,img2,img3,img4,img5,img6};
			j=0;
			code1();

			btn0=(Button) findViewById(R.id.button1);
			btn1=(Button) findViewById(R.id.button2);
			btn2=(Button) findViewById(R.id.button3);
			btn3=(Button) findViewById(R.id.button4);
			btn4=(Button) findViewById(R.id.button5);
			btn5=(Button) findViewById(R.id.button6);
			btn6=(Button) findViewById(R.id.button7);
			btn7=(Button) findViewById(R.id.button8);
			btn8=(Button) findViewById(R.id.button9);
			btn9=(Button) findViewById(R.id.button10);
			btn10=(Button) findViewById(R.id.button11);
			btn11=(Button) findViewById(R.id.button12);

			btn13=(Button) findViewById(R.id.button13);
			btn15=(Button) findViewById(R.id.button15);

			btn0.setOnClickListener(this);
			btn1.setOnClickListener(this);
			btn2.setOnClickListener(this);
			btn3.setOnClickListener(this);
			btn4.setOnClickListener(this);
			btn5.setOnClickListener(this);
			btn6.setOnClickListener(this);
			btn7.setOnClickListener(this);
			btn8.setOnClickListener(this);
			btn9.setOnClickListener(this);
			btn10.setOnClickListener(this);
			btn11.setOnClickListener(this);

			btn13.setOnClickListener(this);
			btn15.setOnClickListener(this);
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {  
			case R.id.button1: 
				finish();

				break;
			case R.id.button2: 
				if(j<6&&j>=0){
					code(1+"");
					j++;
				}

				break;  
			case R.id.button3:  
				if(j<6&&j>=0){
					code(2+"");
					j++;
				}

				break;  
			case R.id.button4:  
				if(j<6&&j>=0){
					code(3+"");
					j++;
				}

				break;  
			case R.id.button5:  
				if(j<6&&j>=0){
					code(4+"");
					j++;
				}

				break;  
			case R.id.button6:  
				if(j<6&&j>=0){
					code(5+"");
					j++;
				}

				break;  
			case R.id.button7:  
				if(j<6&&j>=0){
					code(6+"");
					j++;
				}

				break;  

			case R.id.button8:  
				if(j<6&&j>=0){
					code(7+"");
					j++;
				}


				break;  
			case R.id.button9:  
				if(j<6&&j>=0){
					code(8+"");
					j++;
				}

				break;  
			case R.id.button10:  
				if(j<6&&j>=0){
					code(9+"");
					j++;
				}

				break;  
			case R.id.button11:  
				if(j<6&&j>=0){
					code(0+"");
					j++;
				}


				break;  
			case R.id.button12: 
				if(j<7&&j>0){
					j--;
					code("a");
				}
				break; 
			case R.id.button15:
				btn15.setEnabled(false);
				if(array[5].equals("a")){
					Toast.makeText(getApplicationContext(), "交易密码长度为6位", 1000).show();
					return;
				}else{
					code = new StringBuffer();
					for (int i = 0; i < array.length; i++) {
						code.append(array[i]);
					}
					//Log.i("code", code.toString());
					intent = getIntent();
					Md5 md5=new Md5();
					String md5tradecode=Md5.stringToMD5(code.toString());
					if(intent.getBooleanExtra("isAddOutCharge", false)){


						String card=intent.getStringExtra("card");
						String number=intent.getStringExtra("number");
						String verifycode=intent.getStringExtra("verifycode");
						urlstring="AddOutCharge/Cust_ID="+sp.getString("custid","无")+"&Cust_TradePass="+ md5tradecode+"&Amount="+number+"&Cust_VerifyCode="+verifycode+"&Acc_ID="+card;
					}else{
						urlstring="AddPayInfo/Cust_ID="+sp.getString("custid","无")+"&Cust_TradePass="+ md5tradecode +"&Product_ID="+intent.getStringExtra("productid")+"&Amount="+intent.getStringExtra("amount")+"&Trade_Referrer="+intent.getStringExtra("managerid");
					}

					//Log.i("123123231", urlstring);
					//String urlstring="AddPayInfo/Cust_ID=16&Cust_TradePass=96E79218965EB72C92A549DD5A330112&Product_ID=IP000015&Acc_ID=41&Amount=1000000";
					//String urlstring="AddPayInfo/Cust_ID="+16+"&Cust_TradePass="+"96E79218965EB72C92A549DD5A330112"+"&Product_ID="+intent.getStringExtra("productid")+"&Acc_ID="+sp.getString("chosencardid","无")+"&Amount="+intent.getStringExtra("amount");
					MyAsyncTask myAsyncTask=new MyAsyncTask();	
					if(myAsyncTask.isNetworkConnected(getApplicationContext())){
						myAsyncTask.setHandler(handler1);
						myAsyncTask.execute(urlstring);



						myprodialog=new MyProgressdialog(InputTradecode.this);
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

				break;  
			default:  
				break;  
			}  
		}

		private void code(String k) {
			// TODO Auto-generated method stub
			if(j<6&&j>=0){
				array[j]=k;
				for (int i = 0; i < 6; i++) {
					if(array[i].equals("a")){
						img[i].setImageResource(R.drawable.white);

					}else{

						img[i].setImageResource(R.drawable.codethumb);

					}
				}

			}else{}
		}
		private void code1() {
			// TODO Auto-generated method stub

			for (int i = 0; i < 6; i++) {

				img[i].setImageResource(R.drawable.white);

			}

		}

}


