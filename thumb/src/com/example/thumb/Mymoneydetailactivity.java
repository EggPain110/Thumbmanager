package com.example.thumb;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Mymoneydetailactivity extends Activity{
	Button btn1;
	ListView listView;
	BaseAdapter baseadapter;
	//private ProgressDialog progressDialog;  

	private MyProgressdialog myprodialog;

	JSONArray jsonarray;
	SharedPreferences sp;
	private int[] imgIdArray ;
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			handler10.sendEmptyMessage(0);  
			//	Log.i("mymoneydetail",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					jsonarray=jobject.getJSONArray("DataObj");

					JSONObject jobject3=jsonarray.getJSONObject(0);
					TextView tv1=(TextView) findViewById(R.id.textView1);
					if(jobject3.getString("Ipro_name").length()>8){
						tv1.setText(jobject3.getString("Ipro_name").substring(0, 8));
					}else{
						tv1.setText(jobject3.getString("Ipro_name"));
					}

					listView=(ListView) findViewById(R.id.listView1);

					listView.setDivider(null);//无分割线
					baseadapter=new myadpter();
					listView.setAdapter(baseadapter);
					listView.setOnItemClickListener(new OnItemClickListener() {


						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub

						}
					});

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
			setContentView(R.layout.mymoneydetail);
			btn1=(Button) findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();

				}
			});
			Intent intent=getIntent();
			sp=getSharedPreferences("SP", MODE_PRIVATE);
			String urlstring="GetRecordDetail/Cust_ID="+sp.getString("custid", "无")+"&Product_ID="+intent.getStringExtra("proid");
			MyAsyncTask myAsyncTask=new MyAsyncTask();
			if(myAsyncTask.isNetworkConnected(getApplicationContext())){
				myAsyncTask.setHandler(handler1);
				myAsyncTask.execute(urlstring);


				myprodialog=new MyProgressdialog(Mymoneydetailactivity.this);
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


			imgIdArray = new int[]{R.drawable.bankofzhaoshang, R.drawable.bankofzhongguo, R.drawable.bankofgongshang, R.drawable.bankofjianshe};

			Editor editor=sp.edit();
			editor.putInt("招商银行", 0);
			editor.putInt("中国银行", 1);
			editor.putInt("工商银行", 2);
			editor.putInt("建设银行", 3);
			editor.commit();



		}


		class myadpter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return jsonarray.length();    //list.lengh
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub

				return position;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub

				View v=getLayoutInflater().inflate(R.layout.mymoneydetailitem, null);

				TextView tv2=(TextView) v.findViewById(R.id.textView2);			//购买时间
				TextView tv3=(TextView) v.findViewById(R.id.textView3);			//到期时间
				TextView tv5=(TextView) v.findViewById(R.id.textView5);			//本金
				TextView tv7=(TextView) v.findViewById(R.id.textView7);			//收益
				//TextView tv9=(TextView) v.findViewById(R.id.textView9);			//银行卡号 
				TextView tv14=(TextView) v.findViewById(R.id.textView14);			//债务人名称
				TextView tv15=(TextView) v.findViewById(R.id.textView15);			//债券总额
				TextView tv16=(TextView) v.findViewById(R.id.textView16);			//受让金额
				TextView tv18=(TextView) v.findViewById(R.id.textView18);			//购买时间
				//ImageView  img3=(ImageView) v.findViewById(R.id.imageView3);
				try {
					JSONObject jobject2=jsonarray.getJSONObject(position);
					SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
					sdf.applyPattern("yyyy-MM-dd");
					long l0=new Long(jobject2.getString("purchase_startdate").substring(6, 19));
					long ll=new Long(jobject2.getString("purchase_enddate").substring(6, 19));
					String date0 = sdf.format(l0);
					String date1 = sdf.format(ll);
					tv2.setText("起息："+date0);
					tv3.setText("到期："+date1);
					tv5.setText(jobject2.getString("purchase_money"));
					tv7.setText(jobject2.getString("returns"));
					//tv9.setText(jobject2.getString("purchase_bankaccount"));
					if(jobject2.getString("Ipro_name").length()>12){
						tv14.setText(jobject2.getString("Ipro_name").substring(0, 12));  
					}else{
						tv14.setText(jobject2.getString("Ipro_name"));  
					}

					tv15.setText(jobject2.getInt("Ipro_Ashare")/10000+"万");
					tv16.setText(jobject2.getString("purchase_money"));
					SimpleDateFormat sdf1 = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
					sdf1.applyPattern("yyyy-MM-dd HH:mm:ss");
					long l=new Long(jobject2.getString("purchase_date").substring(6, 19));
					String date2 = sdf1.format(l);
					tv18.setText(date2);
					//img3.setImageResource(imgIdArray[sp.getInt(jobject2.getString("purchase_bank"),2)]);


				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				return v;
			}



		}

}
