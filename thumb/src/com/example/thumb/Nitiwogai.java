package com.example.thumb;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nitiwogai extends Activity{
	Button btn1,btn2;
	//private ProgressDialog progressDialog;  
	
	private MyProgressdialog myprodialog;
	
	SharedPreferences sp;
	EditText edt;
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
						Toast.makeText(getApplicationContext(), "提交成功", 1000).show();
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
		setContentView(R.layout.nitiwogai);
		sp =getSharedPreferences("SP",MODE_PRIVATE);
		edt=(EditText) findViewById(R.id.editText1);
		
		edt.setHint("您可向我们提出您的建议，一经采纳，我们会给予您丰厚的奖品！(120字)");
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if("".equals(edt.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入内容", 1000).show();
					return;
				}
				
				String msg=edt.getText().toString().replaceAll(" ", "").replaceAll("\n","");  
				String urlstring="AddIdea/Cust_ID="+sp.getString("custid", "0")+"&Idea_Content="
				+msg+"&Idea_Versions="+"android1.0"
						+"&Idea_Device="+android.os.Build.MODEL+"&Idea_OS="+android.os.Build.VERSION.RELEASE;
				//String urlstring="GetProductDetail/Product_ID=";
				
				MyAsyncTask myAsyncTask=new MyAsyncTask();	

				if(myAsyncTask.isNetworkConnected(getApplicationContext())){
					myAsyncTask.setHandler(handler);
					myAsyncTask.execute(urlstring);
					
					myprodialog=new MyProgressdialog(Nitiwogai.this);
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
		});
	}
}
