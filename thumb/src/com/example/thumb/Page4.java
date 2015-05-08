package com.example.thumb;



import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Page4 extends Fragment {

	View view;
	ListView listView;
	Typeface face ;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	ImageView img1;
	//private ProgressDialog progressDialog;  
	BaseAdapter baseadapter;
	JSONObject  jobject1;
	JSONArray jsonarray;
	
	private MyProgressdialog myprodialog;

	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			handler10.sendEmptyMessage(0);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					jsonarray=jobject.getJSONArray("DataObj");

					jobject1=new JSONObject(jsonarray.getJSONObject(0).toString());

					baseadapter=new myadpter();

					listView.setAdapter(baseadapter);

					/*listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							//Toast.makeText(getApplicationContext(), appList.get(position).packageName, 1000).show();
							//Toast.makeText(getActivity(), "hello", 1000).show();
							startActivity(new Intent(getActivity(),Mymoneydetailactivity.class));
						}
					});*/

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
			//progressDialog.dismiss();  
			myprodialog.dismiss();
		}};  
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			view=inflater.inflate(R.layout.page4, container, false);
			listView=(ListView) view.findViewById(R.id.listView1);

			listView.setDivider(null);//无分割线
			face = Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf" );
			sp = getActivity().getSharedPreferences("SP",0);
			scrrenWidth=sp.getInt("ScrrenWidth", 1080);
			scrrenHeight=sp.getInt("ScrrenHeight", 1920);
			//listView.setAdapter(new myadpter());


			String urlstring="GetHistoryList/Cust_ID="+sp.getString("custid", "无");
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
			return view;
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
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub

				View v=getLayoutInflater(null).inflate(R.layout.transactionrecorditem, null);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth*0.9583), (int) (scrrenHeight*0.15));
				v.setLayoutParams(lp);						//设置item尺寸
				v.setY(0);
				img1=(ImageView) v.findViewById(R.id.imageView2);
				
				TextView tv1=(TextView) v.findViewById(R.id.textView1);
				TextView tv2=(TextView) v.findViewById(R.id.textView2);
				TextView tv3=(TextView) v.findViewById(R.id.textView3);
				TextView tv4=(TextView) v.findViewById(R.id.textView4);

				SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
				sdf.applyPattern("yyyy/MM/dd/HH:mm:ss");
				long l;
				try {
					JSONObject jobject2=jsonarray.getJSONObject(position);
					if(jobject2.getString("Ipro_name").length()>4){
						tv1.setText(jobject2.getString("Ipro_name").substring(0, 4));
					}else{
						tv1.setText(jobject2.getString("Ipro_name"));
					}
					
					l = new Long(jobject2.getString("purchase_date").substring(6, 19));
					String date = sdf.format(l);
					tv2.setText(date);
					String state=jobject2.getString("opertype");
					String str=state+"（确认成功）";
					SpannableStringBuilder style=new SpannableStringBuilder(str); 
					style.setSpan(new ForegroundColorSpan(0xFFE11A2F), 0, 2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE ); 
					style.setSpan(new ForegroundColorSpan(Color.GRAY), 2, 8,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE ); 
					tv3.setText(style);
					tv4.setText(jobject2.getString("purchase_money"));
					
					if(jobject2.getString("Isread").equals("true")){
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
