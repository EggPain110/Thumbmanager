package com.example.thumb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Page7 extends Fragment {


	//private Button btn1,btn2;
	View view;
	ListView listView1;
	Typeface face ;
	public static int scrrenWidth  ;  // ��Ļ���
	public static int scrrenHeight ;  //��Ļ�߶�
	SharedPreferences sp;
	private MyProgressdialog myprodialog;
	BaseAdapter baseadapter;
	JSONObject  jobject1,jobject2;
	JSONArray jsonarray;
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
				
				/*	jobject1=new JSONObject(jsonarray.getJSONObject(0).toString());
					tv1.setText(jobject1.getString("returns"));
					tv5.setText(jobject1.getString("purchase_money"));*/
					baseadapter=new myadpter();
					
					listView1.setAdapter(baseadapter);
					listView1.setDivider(null);//�޷ָ���
					listView1.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							//Toast.makeText(getApplicationContext(), appList.get(position).packageName, 1000).show();
							//Toast.makeText(getActivity(), "hello", 1000).show();
							/*try {
								Intent intent=new Intent(getActivity(),Mymoneydetailactivity.class);
								jobject2=new JSONObject(jsonarray.getJSONObject(position+1).toString());
								intent.putExtra("proid", jobject2.getString("Ipro_id"));
								startActivity(intent);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
							
						}
					});
					
				}else if(statecode.equals("814")){
					Toast.makeText(getActivity(), "�㻹û���κι����¼���Ͽ�ȥ��ɣ�", 1000).show();
					getActivity().finish();
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

			//�ر�ProgressDialog  
			myprodialog.dismiss();

		}};  

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view=inflater.inflate(R.layout.page7, container, false);
		listView1=(ListView) view.findViewById(R.id.listView1);
		
		sp = getActivity().getSharedPreferences("SP",0);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);
		
		face = Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf" );
		
		String urlstring="GetPreProductList/";
		MyAsyncTask myAsyncTask=new MyAsyncTask();	

		if(myAsyncTask.isNetworkConnected(getActivity().getApplicationContext())){
			myAsyncTask.setHandler(handler1);
			myAsyncTask.execute(urlstring);
			
			myprodialog=new MyProgressdialog(getActivity());
			//myprodialog.setCancelable(false);
			myprodialog.show();
			//�½��߳�  
			new Thread(){  

				@Override  
				public void run() {  
					try {
						sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//��handler����Ϣ  
					handler10.sendEmptyMessage(0); 
				}
			}.start(); 
		}else{
			Toast.makeText(getActivity(), "����������", 1000).show();
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
			
			View v=getLayoutInflater(null).inflate(R.layout.productadvanceitem, null);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(scrrenWidth, (int) (scrrenHeight*0.18));
            v.setLayoutParams(lp);						//����item�ߴ�
            //v.setX((float) (scrrenWidth*0.02));
            
            TextView tv1=(TextView) v.findViewById(R.id.textView1);
			TextView tv2=(TextView) v.findViewById(R.id.textView2);
			tv2.setTypeface(face);
			TextView tv3=(TextView) v.findViewById(R.id.textView3);
			TextView tv4=(TextView) v.findViewById(R.id.textView4);
			tv4.setTypeface(face);
			TextView tv5=(TextView) v.findViewById(R.id.textView5);
			TextView tv6=(TextView) v.findViewById(R.id.textView6);
			TextView tv7=(TextView) v.findViewById(R.id.textView7);
            
            try {
				jobject1=jsonarray.getJSONObject(position);
				if(jobject1.getString("Ipro_name").length()>4){
					tv1.setText(jobject1.getString("Ipro_name").substring(0, 4));
				}else{
					tv1.setText(jobject1.getString("Ipro_name"));
				}
				tv2.setText(String.format("%.2f", jobject1.getDouble("pctInterest")));
				tv3.setText("ļ���ʽ�"+jobject1.getInt("Ipro_Ashare")/10000+"��");
				tv6.setText("����"+jobject1.getString("dayDiff")+"��");
				tv7.setText("�𹺽��"+jobject1.getString("Ipro_amount")+"Ԫ��һ����");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return v;
		}
		
		

	}
	


}
