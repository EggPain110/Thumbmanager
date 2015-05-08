package com.example.thumb;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity implements Callback,
Runnable {
	public static final String LOG_TAG = "PayDemo";
	private Context mContext = null;
	private int mGoodsIdx =0;
	private Handler mHandler = null;
	//private ProgressDialog mLoadingDialog = null;
	private MyProgressdialog myprodialog;
	public static final int PLUGIN_VALID = 0;
	public static final int PLUGIN_NOT_INSTALLED = -1;
	public static final int PLUGIN_NEED_UPGRADE = 2;
	SharedPreferences sp;
	String str;
	String msg1;
	Intent intent;
	String urlstring;
	//private ProgressDialog progressDialog;  
	/*****************************************************************
	 * mMode�������ͣ�
	 *      "00" - ����������ʽ����
	 *      "01" - �����������Ի���
	 *****************************************************************/
	private String mMode = "00";
	private static final String TN_URL_01 = "http://202.101.25.178:8080/sim/gettn";

	String mytn;

	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			handler10.sendEmptyMessage(0);
			String string=msg.obj.toString();
			//Log.i("123123qwe", string);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
					finish();

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

		private View.OnClickListener mClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				issecond();
			}
		};

		public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
			UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null, null,
					tn, mode);
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mContext = this;
			mHandler = new Handler(this);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.baseactivity);
			TextView tv=(TextView) findViewById(R.id.textView2);
			intent=getIntent();
			mytn=intent.getStringExtra("tn");
			
			if(intent.getBooleanExtra("isrecharge", false)){
				tv.setText("����ͨ������֧����ֵ����������׬�˻�");
			}else{}
			sp=getSharedPreferences("SP",MODE_PRIVATE);
			Button btn0 = (Button) findViewById(R.id.button1);
			btn0.setTag(0);
			btn0.setOnClickListener(mClickListener);
			Button btn2=(Button) findViewById(R.id.button2);
			btn2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		}

		public abstract void updateTextView(TextView tv);


		private void issecond() {
			// TODO Auto-generated method stub

			String tn = "";
			if (mytn == null || ((String) mytn).length() == 0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("������ʾ");
				builder.setMessage("��������ʧ��,������!");
				builder.setNegativeButton("ȷ��",
						new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			} else {
				tn = mytn;
				/************************************************* 
				 * 
				 *  ����2��ͨ����������������֧����� 
				 *  
				 ************************************************/
				doStartUnionPayPlugin(this, tn, mMode);
			}




		}


		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			/************************************************* 
			 * 
			 *  ����3�����������ֻ�֧���ؼ����ص�֧����� 
			 *  
			 ************************************************/
			if (data == null) {
				return;
			}


			/*
			 * ֧���ؼ������ַ���:success��fail��cancel
			 *      �ֱ����֧���ɹ���֧��ʧ�ܣ�֧��ȡ��
			 */
			str = data.getExtras().getString("pay_result");
			
			
			
			if(intent.getBooleanExtra("isrecharge", false)){
				urlstring="AddRechargeResult/Cust_ID="+sp.getString("custid","��")+"&PayResult="+str+"&TN="+mytn;
			}else{
				urlstring="AddBankPayInfo/Cust_ID="+sp.getString("custid","��")+"&PayResult="+str+"&TN="+mytn;
			}
			
			MyAsyncTask myAsyncTask=new MyAsyncTask();	
			if(myAsyncTask.isNetworkConnected(getApplicationContext())){
				myAsyncTask.setHandler(handler1);
				myAsyncTask.execute(urlstring);

				myprodialog=new MyProgressdialog(BaseActivity.this);
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
				Toast.makeText(getApplicationContext(), "����������", 1000).show();
			}
			if (str.equalsIgnoreCase("success")) {
				msg1 = "֧���ɹ���";
			} else if (str.equalsIgnoreCase("fail")) {
				msg1 = "֧��ʧ�ܣ�";
			} else if (str.equalsIgnoreCase("cancel")) {
				msg1 = "��ȡ����֧��";
			}


		}

		@Override
		public void run() {
			String tn = null;
			InputStream is;
			try {

				String url = TN_URL_01;

				URL myURL = new URL(url);
				URLConnection ucon = myURL.openConnection();
				ucon.setConnectTimeout(120000);
				is = ucon.getInputStream();
				int i = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((i = is.read()) != -1) {
					baos.write(i);
				}

				tn = baos.toString();
				is.close();
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Message msg = mHandler.obtainMessage();
			msg.obj = tn;
			mHandler.sendMessage(msg);
		}

		int startpay(Activity act, String tn, int serverIdentifier) {
			return 0;
		}

		/*    private void showdialog() {
		// TODO Auto-generated method stub
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("֧�����֪ͨ");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        //builder.setCustomTitle();
        builder.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
	}*/
}
