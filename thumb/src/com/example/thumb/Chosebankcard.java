package com.example.thumb;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Chosebankcard extends Activity{
	ListView listView;
	Button btn1,btn2;
	SharedPreferences sp;
	JSONArray jsonArray;
	JSONObject jobject1;
	
	boolean[] ischeck;
	BaseAdapter baseadapter;
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					//Log.i("123123qwe", string);
					jsonArray=jobject.getJSONArray("DataObj");
					
					mylistview();
					
					ischeck=new boolean[jsonArray.length()];
					for (int i = 0; i < jsonArray.length(); i++) {
						ischeck[i]=false;
					}
				}else if(statecode.equals("814")){
					Toast.makeText(getApplicationContext(), "您还没有添加任何银行卡", 1000).show();
				}else{
					Toast.makeText(getApplicationContext(), "error", 1000).show();
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chosebankcard);
		sp= getSharedPreferences("SP",MODE_PRIVATE);
		
		
	
		btn1=(Button) findViewById(R.id.button1);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		btn2=(Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Chosebankcard.this,Addbankcard.class));
			}
		});
		
		String urlstring="GetAccountList/Cust_ID="+sp.getString("custid","无");
		MyAsyncTask myAsyncTask=new MyAsyncTask();	
		if(myAsyncTask.isNetworkConnected(getApplicationContext())){
			myAsyncTask.setHandler(handler1);
			myAsyncTask.execute(urlstring);
		}else{
			Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
		}

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		String urlstring="GetAccountList/Cust_ID="+sp.getString("custid","无");
		MyAsyncTask myAsyncTask=new MyAsyncTask();	
		if(myAsyncTask.isNetworkConnected(getApplicationContext())){
			myAsyncTask.setHandler(handler1);
			myAsyncTask.execute(urlstring);
		}else{
			Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
		}
	}
	
	
	private void mylistview() {
		// TODO Auto-generated method stub
		baseadapter=new myadpter();
		
		listView=(ListView) findViewById(R.id.listView1);
		listView.setAdapter(baseadapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				for (int i = 0; i < jsonArray.length(); i++) {
					ischeck[i]=false;
				}
				ischeck[position]=true;
				baseadapter.notifyDataSetChanged();       			//listview 动态刷新；
				
			}
		});
	}
	
	
	class myadpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return jsonArray.length();
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
			
			View v=getLayoutInflater().inflate(R.layout.bankcarditem, null);
			ImageView img2=(ImageView) v.findViewById(R.id.imageView2);
			
			
			
			try {
				jobject1=jsonArray.getJSONObject(position);
				TextView tv=(TextView) v.findViewById(R.id.textView1);
			
				//Log.i("chosebankcard", jobject1.getString("Acc_Info").substring(0, 4));
				tv.setText(jobject1.getString("Acc_Info"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ischeck[position]){
				img2.setVisibility(View.VISIBLE);
				Editor editor=sp.edit();
				try {
					editor.putString("chosencardid", jobject1.getString("Acc_ID"));
					editor.putString("chosencard", jobject1.getString("Acc_Info"));
					finish();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editor.commit();
			}else{
				img2.setVisibility(View.INVISIBLE);
			}
			
			return v;
		}

	
	}

}
