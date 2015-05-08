package com.example.thumb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Productdetailactivity extends Activity{

	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
	Typeface face;
	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
	SharedPreferences sp;
	ImageView img1;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	Intent intent;

	private MyProgressdialog myprodialog;
	//private ProgressDialog progressDialog;  
	JSONObject  jobject1,jobject2;
	JSONArray jsonarray;
	static Productdetailactivity instance;

	Handler handler1 = new Handler() {  

		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			handler10.sendEmptyMessage(0);  
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){

					jobject1=jobject.getJSONObject("DataObj");
					jsonarray=jobject1.getJSONArray("ProductDetail");

					jobject2=jsonarray.getJSONObject(0);
					//Log.i("productdetail",  jobject2.toString());


				}else {
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
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
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			super.onCreate(savedInstanceState);
			setContentView(R.layout.productdetail2);
			instance=this;
			sp = getSharedPreferences("SP",MODE_PRIVATE);
			scrrenWidth=sp.getInt("ScrrenWidth", 1080);
			scrrenHeight=sp.getInt("ScrrenHeight", 1920);

			face= Typeface.createFromAsset (getAssets() , "fonts/fangz.ttf");  

			intent=getIntent();
			String productid=intent.getStringExtra("productid");

			String urlstring="GetProductDetail/Product_ID="+productid+"&Cust_ID="+sp.getString("custid", "0");

			String str4=intent.getStringExtra("shouyi");
			SpannableStringBuilder style3=new SpannableStringBuilder(str4); 
			style3.setSpan(new RelativeSizeSpan((float) 0.5),str4.length()-1, str4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

			tv1=(TextView) findViewById(R.id.textView1);
			if(intent.getStringExtra("productname").length()>8){
				tv1.setText(intent.getStringExtra("productname").substring(0, 8));
			}else{
				tv1.setText(intent.getStringExtra("productname"));
			}


			tv2=(TextView) findViewById(R.id.textView2);
			tv2.setTextColor(Color.WHITE);
			tv2.setTextSize(60);
			tv2.setTypeface (face);
			tv2.setGravity(Gravity.CENTER_HORIZONTAL);
			tv2.setY((float) (scrrenHeight*0.1));
			tv2.setText(style3);

			tv3=(TextView) findViewById(R.id.textView3);
			tv3.setTextColor(Color.WHITE);
			tv3.setTypeface (face);
			/*	tv3.setX((float) (scrrenWidth*0.7291));
		tv3.setY((float) (scrrenHeight*0.1525));
			 */
			tv4=(TextView) findViewById(R.id.textView4);
			tv4.setTextColor(Color.WHITE);
			tv4.setGravity(Gravity.CENTER_HORIZONTAL);;
			tv4.setY((float) (scrrenHeight*0.225));

			tv5=(TextView) findViewById(R.id.textView5);
			tv5.setTextColor(Color.WHITE);
			tv5.setX((float) (scrrenWidth*0.04167));
			tv5.setY((float) (scrrenHeight*0.27));
			tv5.setText(intent.getStringExtra("buynum"));

			tv6=(TextView) findViewById(R.id.textView6);
			tv6.setTextColor(Color.WHITE);
			tv6.setX((float) (scrrenWidth*0.625));
			tv6.setY((float) (scrrenHeight*0.27));
			tv6.setText(intent.getStringExtra("day"));

			tv7=(TextView) findViewById(R.id.textView7);
			tv7.setTextColor(0xFFFA5C5B);
			tv7.setY((float) (scrrenHeight*0.325));

			img1=(ImageView) findViewById(R.id.imageView1);
			img1.setX((float) (scrrenWidth*0.09));
			img1.setY((float) (scrrenHeight*0.325));

			btn1=(Button) findViewById(R.id.button1);
			btn1.setX((float) (scrrenWidth*0.1875));
			btn1.setY((float) (scrrenHeight*0.425));
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}
					Intent intent=new Intent(Productdetailactivity.this,Projectintroduce.class);
					try {

						intent.putExtra("productid", jobject2.getString("Ipro_id"));
						startActivity(intent);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

			btn2=(Button) findViewById(R.id.button2);
			btn2.setX((float) (scrrenWidth*0.66667));
			btn2.setY((float) (scrrenHeight*0.425));
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}

					Editor editor=sp.edit();
					editor.putString("wherein", "1");
					editor.commit();
					startActivity(new Intent(Productdetailactivity.this,JianyipeibiActivity.class));
				}
			});
			btn3=(Button) findViewById(R.id.button3);
			btn3.setX((float) (scrrenWidth*0.1875));
			btn3.setY((float) (scrrenHeight*0.61875));
			btn3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}
					Intent intent=new Intent(Productdetailactivity.this,Moneysafe.class);
					try {

						intent.putExtra("productid", jobject2.getString("Ipro_id"));

						startActivity(intent);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			btn4=(Button) findViewById(R.id.button4);
			btn4.setX((float) (scrrenWidth*0.66667));
			btn4.setY((float) (scrrenHeight*0.61875));
			btn4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}

					Intent intent=new Intent(Productdetailactivity.this,Producttextactivity.class);
					try {

						intent.putExtra("productid", jobject2.getString("Ipro_id"));
						startActivity(intent);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});


			btn5=(Button) findViewById(R.id.button5);
			btn5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}
					try {
						int day=jobject2.getInt("dayDiff");
						double interest=jobject2.getDouble("pctInterest");
						Counterdialog counterdialog=new Counterdialog();
						counterdialog.buildDialog(Productdetailactivity.this,day,interest);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			btn6=(Button) findViewById(R.id.button6);
			btn6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if(null == jobject2 || "".equals(jobject2) || "{}".equals(jobject2) || "[]".equals(jobject2)){ 
						return;
					}
					Intent intent=new Intent(Productdetailactivity.this,Buyactivity1.class);
					try {
						if(jobject2.getString("Ipro_name").length()>4){
							intent.putExtra("productname",jobject2.getString("Ipro_name").substring(0, 4));
						}else{
							intent.putExtra("productname",jobject2.getString("Ipro_name"));
						}

						intent.putExtra("beginmoney",Integer.parseInt(String.format("%.0f", jobject2.getDouble("Ipro_amount"))));
						//Log.i("beginmoney123", Integer.parseInt(String.format("%.0f", jobject2.getDouble("Ipro_amount")))+"");
						intent.putExtra("shouxufei", "无");
						intent.putExtra("yuqishouyi", String.format("%.2f", jobject2.getDouble("pctInterest"))+"%");
						intent.putExtra("licaiqixian", jobject2.getString("dayDiff"));
						intent.putExtra("yuqishouyichanshengshijian", jobject2.getString("Ipro_accrualstart"));
						intent.putExtra("productid", jobject2.getString("Ipro_id"));
						intent.putExtra("fengxiandengji", jobject2.getString("Ipro_suggest")); 
						intent.putExtra("iscouldbuy", jobject2.getString("isAble"));
						intent.putExtra("explan", jobject2.getString("specialRemark"));
						intent.putExtra("iscouldedit", jobject2.getString("isAuto"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startActivity(intent);


				}
			});

			btn7=(Button) findViewById(R.id.button7);
			btn7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

			MyAsyncTask myAsyncTask=new MyAsyncTask();	

			if(myAsyncTask.isNetworkConnected(getApplicationContext())){
				myAsyncTask.setHandler(handler1);
				myAsyncTask.execute(urlstring);
			}else{
				Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
			}

			myprodialog=new MyProgressdialog(Productdetailactivity.this);
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

		}
}
