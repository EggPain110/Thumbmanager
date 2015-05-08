package com.example.thumb;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Page10 extends Fragment {
	View view;
	ListView listView;
	Typeface face ;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	ImageView img1;
	BaseAdapter baseadapter;
	//private ProgressDialog progressDialog;  
	private MyProgressdialog myprodialog;
	JSONArray jsonArray;

	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			handler3.sendEmptyMessage(0);  
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					JSONObject jobject1=jobject.getJSONObject("DataObj");
					jsonArray=jobject1.getJSONArray("List");
					baseadapter=new myadpter();
					listView.setAdapter(baseadapter);


				}else if(statecode.equals("807")){
					Toast.makeText(getActivity(), "", 1000).show();
					handler3.sendEmptyMessage(0);
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
		};  
	};  


	private Handler handler3 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  

			myprodialog.dismiss();



		}};  



		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			view=inflater.inflate(R.layout.page10, container, false);
			listView=(ListView) view.findViewById(R.id.listView1);

			//listView.setDivider(null);//无分割线
			face = Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf" );
			sp = getActivity().getSharedPreferences("SP",0);
			scrrenWidth=sp.getInt("ScrrenWidth", 1080);
			scrrenHeight=sp.getInt("ScrrenHeight", 1920);


			String urlstring="GetMessageList/Cust_ID="+sp.getString("custid", "无");
			if(sp.getString("custid", "无").equals("无")){
				startActivity(new Intent(getActivity(),Loginactivity.class));

			}else{}

			MyAsyncTask myAsyncTask=new MyAsyncTask();	


			if(myAsyncTask.isNetworkConnected(getActivity())){
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
						handler3.sendEmptyMessage(0);  
					}
				}.start(); 
			}else{
				Toast.makeText(getActivity(), "请连接网络", 1000).show();
			}




			return view;
		}

		class myadpter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return jsonArray.length();    //list.lengh
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
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub

				View v=getLayoutInflater(null).inflate(R.layout.systemmessageitem, null);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth*0.9583), (int) (scrrenHeight*0.15));
				v.setLayoutParams(lp);						//设置item尺寸
				v.setY(0);
				TextView tv2=(TextView) v.findViewById(R.id.textView2);
				TextView tv3=(TextView) v.findViewById(R.id.textView3);
				TextView tv4=(TextView) v.findViewById(R.id.textView4);
				// tv2.setText("2014/12/04  14:43：59");


				try {
					JSONObject jobject2=jsonArray.getJSONObject(position);
					SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
					sdf.applyPattern("yyyy/MM/dd/HH:mm:ss");
					long l=new Long(jobject2.getString("Mess_DateTime").substring(6, 19));
					String date = sdf.format(l);
					tv2.setText(date);
					tv4.setText(jobject2.getString("Mess_Content"));


					img1=(ImageView) v.findViewById(R.id.imageView2);
					if(jobject2.getString("isRead").equals("true")){

						img1.setImageResource(R.drawable.graytransactionmoney);
					}else{
						img1.setImageResource(R.drawable.redtransactionmoney);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return v;
			}



		}


}