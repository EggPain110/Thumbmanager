package com.example.thumb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Page3 extends Fragment {
	View view;
	ListView listView1;
	TextView tv1,tv2,tv3,tv4,tv5,tv12,tv15,tv18;
	Typeface face ;
	//private ProgressDialog progressDialog;  
	
	
	private MyProgressdialog myprodialog;
	
	
	SharedPreferences sp;
	
	Button  btn1,btn2;

	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	BaseAdapter baseadapter;
	JSONObject  jobject1,jobject2;
	JSONArray jsonarray,jsonarray1;
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			if(msg==null){
				return;
			}
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			handler10.sendEmptyMessage(0);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					JSONObject jobject3=jobject.getJSONObject("DataObj");
					jsonarray=jobject3.getJSONArray("profit");
					jsonarray1=jobject3.getJSONArray("profit1");

					jobject1=jsonarray.getJSONObject(0);


					tv1.setText(jobject1.getString("SumBalance"));
					tv5.setText(jobject1.getString("CapitalTotal"));
					tv12.setText(jobject1.getString("RedPacketTotal"));
					tv15.setText(jobject1.getString("CapitalEarn"));
					tv18.setText(jobject1.getString("RedPacketEarn"));

					baseadapter=new myadpter();

					listView1.setAdapter(baseadapter);

					listView1.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							//Toast.makeText(getApplicationContext(), appList.get(position).packageName, 1000).show();
							//Toast.makeText(getActivity(), "hello", 1000).show();
							try {
								Intent intent=new Intent(getActivity(),Mymoneydetailactivity.class);
								jobject2=new JSONObject(jsonarray1.getJSONObject(position).toString());
								intent.putExtra("proid", jobject2.getString("Ipro_id"));
								startActivity(intent);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});

				}else{
					Toast.makeText(getActivity(), jobject.getString("StateExplain"), 1000).show();

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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			view=inflater.inflate(R.layout.page3, container, false);

			listView1=(ListView) view.findViewById(R.id.listView1);

			face = Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf" );
			sp = getActivity().getSharedPreferences("SP",0);
			scrrenWidth=sp.getInt("ScrrenWidth", 1080);
			scrrenHeight=sp.getInt("ScrrenHeight", 1920);
			tv1=(TextView) view.findViewById(R.id.textview1);
			tv1.setTypeface(face);
			//tv1.setTextColor(color.white);
			tv5=(TextView) view.findViewById(R.id.textview5);
			tv5.setTypeface(face);

			tv12=(TextView) view.findViewById(R.id.textView12);
			tv12.setTypeface(face);

			tv15=(TextView) view.findViewById(R.id.textview15);
			tv15.setTypeface(face);

			tv18=(TextView) view.findViewById(R.id.textView18);
			tv18.setTypeface(face);

			btn1=(Button) view.findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(getActivity(),Recharge.class));
				}
			});

			btn2=(Button) view.findViewById(R.id.button2);
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(sp.getBoolean("istruename", false)){
						startActivity(new Intent(getActivity(),Tixian.class));
					}else{
						startActivity(new Intent(getActivity(),Shimingrenzheng.class));
					}

				}
			});


			String urlstring="GetProfitList/Cust_ID="+sp.getString("custid", "无");
			MyAsyncTask myAsyncTask=new MyAsyncTask();	

			if(myAsyncTask.isNetworkConnected(getActivity().getApplicationContext())){
				myAsyncTask.setHandler(handler1);
				myAsyncTask.execute(urlstring);

				myprodialog=new MyProgressdialog(getActivity());
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
				Toast.makeText(getActivity(), "请连接网络", 1000).show();
			}



			// TODO Auto-generated method stub
			return view;

		}


		class myadpter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return jsonarray1.length();    //list.lengh
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

				View v=getLayoutInflater(null).inflate(R.layout.product, null);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(scrrenWidth, (int) (0.1*scrrenHeight));
				v.setLayoutParams(lp);						//设置item尺寸
				TextView tv1=(TextView) v.findViewById(R.id.textView1);
				TextView tv2=(TextView) v.findViewById(R.id.textView2);
				TextView tv3=(TextView) v.findViewById(R.id.textView3);
				try {
					JSONObject jobject2=jsonarray1.getJSONObject(position);
					if(jobject2.getString("Ipro_name").length()>10){
						tv1.setText(jobject2.getString("Ipro_name").substring(0, 10));
					}else{
						tv1.setText(jobject2.getString("Ipro_name"));
					}

					tv2.setText(jobject2.getString("purchase_money"));
					tv3.setText(jobject2.getString("returns"));

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return v;
			}



		}




}
