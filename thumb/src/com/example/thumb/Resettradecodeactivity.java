package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Resettradecodeactivity extends Activity implements View.OnClickListener{
	Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn13,btn15;
	ImageView img1,img2,img3,img4,img5,img6;
	String[] array;
	ImageView[] img;
	TextView tv1,tv2,tv4;
	int j;
	SharedPreferences sp;
	StringBuffer code3,code1,code2;
	
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			btn15.setEnabled(true);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					Editor editor=sp.edit();
					editor.putString("istradepass","true");
					editor.commit();
					Toast.makeText(getApplicationContext(), "重置成功", 1000).show();
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
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tradecode);
		sp=getSharedPreferences("SP",MODE_PRIVATE);
		tv1=(TextView) findViewById(R.id.textView1);
		tv2=(TextView) findViewById(R.id.textView2);
		tv4=(TextView) findViewById(R.id.textView4);
		
		if(sp.getString("istradepass", "false").equals("true")){
			tv1.setText("修改交易密码");
			tv2.setText("");
			tv4.setText("请输入原密码");
			
		}else{}
		
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

		btn0=(Button) findViewById(R.id.button1);
		btn0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		
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

		case R.id.button13: 




		case R.id.button15: 
			if(array[5].equals("a")){
				Toast.makeText(getApplicationContext(), "交易密码长度为6位", 1000).show();
				return;
			}else{}

			if(btn15.getText().toString().equals("下一步")){
				
				
				if(tv4.getText().toString().equals("请输入新的交易的密码")){
					btn15.setText("完成");
					tv4.setText("请再次输入新的交易的密码");
					
					code3 = new StringBuffer();

					for (int i = 0; i < array.length; i++) {
						code3.append(array[i]);
					}
					for (int i = 0; i < array.length; i++) {
						array[i]="a";
					}
				}else{
					tv4.setText("请输入新的交易的密码");
					
					code2 = new StringBuffer();

					for (int i = 0; i < array.length; i++) {
						code2.append(array[i]);
					}
					for (int i = 0; i < array.length; i++) {
						array[i]="a";
					}
				}
				
				j=0;
				code1();
			
				//Log.i("tradcode",code2.toString());
			}else if(btn15.getText().toString().equals("完成")){
				code1 = new StringBuffer();

				for (int i = 0; i < array.length; i++) {
					code1.append(array[i]);
				}
				if(code3.toString().equals(code1.toString())){
					String custid=sp.getString("custid", "无");
					if(custid.equals("无")){
						this.finish();
						startActivity(new Intent(Resettradecodeactivity.this,Loginactivity.class));
					}else{}
					Md5 md5=new Md5();
					String md5tradecode=md5.stringToMD5(code1.toString());
					String yuanmd5tradecode=md5.stringToMD5(code2.toString());
					String urlstring="UpdateTradePassNew/Cust_ID="+custid+"&Cust_TradePassNew="+md5tradecode+"&Cust_TradePass="+yuanmd5tradecode;
					MyAsyncTask myAsyncTask=new MyAsyncTask();
					if(myAsyncTask.isNetworkConnected(getApplicationContext())){
						btn15.setEnabled(false);
						myAsyncTask.setHandler(handler1);
						myAsyncTask.execute(urlstring);

					}else{
						Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
					}
					
				}else{
					for (int i = 0; i < array.length; i++) {
						array[i]="a";
					}
					Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入", 1000).show();
					j=0;
					code1();
					btn15.setText("下一步");
					tv4.setText("");
				}
				//Log.i("tradcode1",code1.toString());
			}else{}
		
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


