package com.example.thumb;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.thumb.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buyactivity1 extends Activity{
	Button btn1,btn3,btn4,btn20;
	CheckBox cbx1;
	TextView tv2,tv3,tv4,tv5,tv6,tv7;
	EditText edt1,edt2;
	String productid,fengxiandengji,isedite;
	SharedPreferences sp;
	int beginmoney;

	private MyProgressdialog myprodialog;

	//private ProgressDialog progressDialog;  
	AlertDialog dialog;

	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  

			myprodialog.dismiss();

		}};  


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

					if(statecode.equals("800")){
						Intent intent=new Intent(Buyactivity1.this,InputTradecode.class);
						intent.putExtra("isAddOutCharge", false);						                    //提现
						intent.putExtra("productid", productid);
						intent.putExtra("amount", edt1.getText().toString().trim());
						if("".equals(edt2.getText().toString().trim())){

						}else{
							intent.putExtra("managerid", edt2.getText().toString().trim());
						}
						startActivity(intent);
						finish();
						//startActivity(new Intent(Loginactivity.this,Buyactivity1.class));
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

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.buy_activity1);
			sp= getSharedPreferences("SP",MODE_PRIVATE);
			final Intent intent = getIntent();
			tv2=(TextView) findViewById(R.id.textView2);
			tv3=(TextView) findViewById(R.id.textView3);
			tv4=(TextView) findViewById(R.id.textView4);
			tv5=(TextView) findViewById(R.id.textView5);
			tv6=(TextView) findViewById(R.id.textView6);
			tv7=(TextView) findViewById(R.id.textView7);
			cbx1=(CheckBox) findViewById(R.id.checkBox1);


			edt1=(EditText) findViewById(R.id.editText1);
			
			edt1.setFocusable(true);
			edt1.setFocusableInTouchMode(true);   
			edt1.requestFocus(); 
			
			edt2=(EditText) findViewById(R.id.editText2);


			tv2.setText(intent.getStringExtra("productname"));

			beginmoney=intent.getIntExtra("beginmoney", 1);
			if(beginmoney>9999){
				edt1.setHint("最低"+beginmoney/10000+"万元起，每"+beginmoney/10000+"万元累加");
				tv3.setText("起投金额:"+beginmoney/10000+"万元");
			}else {
				edt1.setHint("最低"+beginmoney+"元起，每"+beginmoney+"元累加");
				tv3.setText("起投金额:"+beginmoney);
			}


			tv4.setText("手续费:"+intent.getStringExtra("shouxufei"));
			//tv5.setText("预期收益产生时间:"+TimeStamp2Date(intent.getStringExtra("yuqishouyichanshengshijian")));
			tv6.setText("年化:"+intent.getStringExtra("yuqishouyi"));
			tv7.setText("理财期限:"+intent.getStringExtra("licaiqixian")+"天");
			productid=intent.getStringExtra("productid");
			fengxiandengji=intent.getStringExtra("fengxiandengji");
			isedite=intent.getStringExtra("iscouldedit");
			SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
			sdf.applyPattern("yyyy年MM月dd日 ");
			long l=new Long(intent.getStringExtra("yuqishouyichanshengshijian").substring(6, 19));
			String date = sdf.format(l);


			if(isedite.equals("false")){
				edt1.setText(beginmoney+"");
				edt1.setEnabled(false);
			}else{}

			tv5.setText("预期收益产生时间:"+date);

			btn1=(Button) findViewById(R.id.button1);

			btn3=(Button) findViewById(R.id.button3);
			btn4=(Button) findViewById(R.id.button4);
			btn20=(Button) findViewById(R.id.button20);
			switch (fengxiandengji) {

			case "01":
				btn20.setBackgroundResource(drawable.high);
				btn20.setText("1R 低风险");
				break;
			case "02":
				btn20.setBackgroundResource(drawable.midle);
				btn20.setText("2R 中风险");
				break;
			case "03":
				btn20.setBackgroundResource(drawable.low);
				btn20.setText("3R 高风险");
				break;

			default:
				break;
			}
			btn4.setBackgroundResource(R.drawable.redbutton2);

			btn1.setOnClickListener(new View.OnClickListener() {

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
					startActivity(new Intent(Buyactivity1.this,Buyagreement.class));
				}
			});

			btn4.setEnabled(false);
			btn4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(intent.getStringExtra("iscouldbuy").equals("false")){
						//Toast.makeText(getApplicationContext(), intent.getStringExtra("explan"), 1000).show();
						mydialog(intent.getStringExtra("explan"));
						return;
					}else if(intent.getStringExtra("iscouldbuy").equals("true")){
						if("".equals(edt1.getText().toString().trim())){
							Toast.makeText(getApplicationContext(), "请输入购买金额", 1000).show();
							return;
						}else{}

						if(Integer.parseInt(edt1.getText().toString())%intent.getIntExtra("beginmoney", 1)!=0&&Integer.parseInt(edt1.getText().toString())!=0){
							Toast.makeText(getApplicationContext(), "累加金额为"+intent.getIntExtra("beginmoney", 1), 1000).show();

							return;
						}else{}


						/*	String urlstring="AddPayInfo/Cust_ID="+sp.getString("custid","无")+"&Product_ID="+productid+"&Acc_ID="+sp.getString("chosencardid","无")+"&Amount="+edt1.getText().toString();
					MyAsyncTask myAsyncTask=new MyAsyncTask();	
					if(myAsyncTask.isNetworkConnected(getApplicationContext())){
						myAsyncTask.setHandler(handler1);
						myAsyncTask.execute(urlstring);
					}else{
						Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
					}
						 */
						if(sp.getString("istradepass", "false").equals("false")){
							startActivity(new Intent(Buyactivity1.this,Tradecodeactivity.class));
						}else{
							//startActivity(new Intent(Buyactivity1.this,InputTradecode.class));



							if("".equals(edt2.getText().toString().trim())){

								Intent intent=new Intent(Buyactivity1.this,InputTradecode.class);
								intent.putExtra("isAddOutCharge", false);						                    //提现
								intent.putExtra("productid", productid);
								intent.putExtra("amount", edt1.getText().toString().trim());

								startActivity(intent);
								finish();
							}else{
								//intent.putExtra("managerid", edt2.getText().toString().trim());

								if(edt2.getText().toString().trim().equals(sp.getString("custinvite", "0"))){
									Toast.makeText(getApplicationContext(), "不可填自己的邀请码", 1000).show();
									return;
								}else{}
								String urlstring="CheckRecoMark/Cust_ID="+sp.getString("custid", "0")+"&RecoMark="+edt2.getText().toString().trim();

								MyAsyncTask myAsyncTask=new MyAsyncTask();	

								if(myAsyncTask.isNetworkConnected(getApplicationContext())){
									myAsyncTask.setHandler(handler);
									myAsyncTask.execute(urlstring);


									myprodialog=new MyProgressdialog(Buyactivity1.this);
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


						}

					}

				}
			});
			//为check设置监听选项，控制密码框的显示方式
			cbx1.setOnClickListener(new OnClickListener()
			{ 
				@Override
				public void onClick(View v) 
				{
					if(cbx1.isChecked())
					{
						btn4.setEnabled(true);
						btn4.setBackgroundResource(R.drawable.hongseanniu);
					}else
					{
						btn4.setEnabled(false);
						btn4.setBackgroundResource(R.drawable.redbutton2);
					}
				}

			});


		}

		private void mydialog(String explan) {
			// TODO Auto-generated method stub
			final AlertDialog.Builder builder5 = new AlertDialog.Builder(Buyactivity1.this);

			View view5 = LayoutInflater.from(Buyactivity1.this).inflate(R.layout.checkversions, null);
			Button btn5=(Button) view5.findViewById(R.id.button1);
			TextView tv1=(TextView) view5.findViewById(R.id.textView2);
			tv1.setText(explan);
			btn5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();   
				}
			});

			builder5.setView(view5);


			dialog = builder5.show();   
		}


}
