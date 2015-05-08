package com.example.thumb;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;
import com.example.thumb.R;
import com.igexin.sdk.PushManager;
import com.way.locus.LoginActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Firstactivity extends Activity{
	boolean isconect=true;
	StringBuffer sb =new StringBuffer();
	String productid;
	boolean isget=false,isontime=false,isfinish=false,isreturn=true;
	int k=0;
	int imagetimes=0;
	JSONObject jobject8;
	SharedPreferences sp;
	JSONArray jarray6;
	boolean isneedupdate=false;
	String currentupdate;

	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	AlertDialog dialog ;
	String custcertid,cid;

	


	Handler handler3 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  
			if(msg.what==1){
				isontime=true;
			}else{}

			if(msg.what==2){
				isfinish=true;
			}else{}

			if(msg.what==3){
				isreturn=false;
			}else{}

			if(isontime&&isget&&isreturn){
				if(sp.getBoolean("iswatch", false)){

					if(sp.getBoolean("islogin", false)){
						Intent intent=new Intent(Firstactivity.this,com.way.locus.LoginActivity.class);
						//intent.putExtra("detailjson", jobject8.toString());
						startActivity(intent);
					}else{
						startActivity(new Intent(Firstactivity.this,MyNewMainactivity.class));

					}


					isontime=false;
					isget=false;
					finish();
				}else{
					Intent intent=new Intent(Firstactivity.this,Teachactivity.class);
					//intent.putExtra("detailjson", jobject8.toString());
					startActivity(intent);
					isontime=false;
					isget=false;
					finish();
				} 
			}


			if(isfinish&&isontime&&isreturn){
				if(sp.getBoolean("iswatch", false)){

					Intent intent=new Intent(Firstactivity.this,LoginActivity.class);

					startActivity(intent);
					finish();
				}else{
					Intent intent=new Intent(Firstactivity.this,Teachactivity.class);

					startActivity(intent);
					finish();
				} 
			}



		}};  


		Handler handler5 = new Handler() {  
			public void handleMessage(android.os.Message msg) { 

				String string=msg.obj.toString();

				try {
					JSONObject jobject5=new JSONObject(string);
					String statecode=jobject5.getString("StateCode");

					if(statecode.equals("808")){
						JSONArray jsonarray=jobject5.getJSONArray("DataObj");

						JSONObject  jobject1=new JSONObject(jsonarray.getJSONObject(0).toString());

						Editor editor = sp.edit();
						editor.putBoolean("islogin", true);
						Long tsLong = System.currentTimeMillis()/1000;
						editor.putLong("lastlogintime", tsLong);

						//Log.i("loginmessage", jobject1.getString("Cust_ID"));
						editor.putString("custid", jobject1.getString("Cust_ID"));
						editor.putString("istradepass", jobject1.getString("IsTradePass"));
						editor.putString("custmobile", jobject1.getString("Cust_Mobile").substring(0, 3)+"****"+jobject1.getString("Cust_Mobile").substring(7, 11));
						editor.putBoolean("istruename", jobject1.getString("IsRealNameAuth").equals("true"));
						editor.putString("pin", jobject1.getString("Cust_PIN"));
						editor.putString("custinvite", jobject1.getString("Cust_RecoMark"));
						//editor.putString("custname", "*"+jobject1.getString("Cust_Name").substring(1,jobject1.getString("Cust_Name").length()));
						if("".equals(jobject1.getString("Cust_Name"))){

						}else{
							editor.putBoolean("isfirst", false);
							editor.putString("custname", "*"+jobject1.getString("Cust_Name").substring(1,jobject1.getString("Cust_Name").length()));
							editor.putString("userid", jobject1.getString("Cust_CertID").substring(0, 5)+"*********"+jobject1.getString("Cust_CertID").substring(jobject1.getString("Cust_CertID").length()-4, jobject1.getString("Cust_CertID").length()));
						}
						editor.commit();
					}else{

						Editor editor=sp.edit();
						scrrenWidth=sp.getInt("ScrrenWidth", 1080);
						scrrenHeight=sp.getInt("ScrrenHeight", 1920);

						custcertid=sp.getString("userid", "****");
						cid=sp.getString("ClientID", "0");

						editor.clear();
						editor.commit();

						editor.putInt("ScrrenWidth", scrrenWidth);
						editor.putInt("ScrrenHeight",scrrenHeight);
						editor.putBoolean("iswatch", true);
						editor.putString("ClientID", cid);
						//editor.putString("userid", custcertid);
						//editor.putString("custname", custname);
						editor.commit();
						//Log.i("456qweqwe",  msg.obj+"");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};  
		}; 


		Handler handler1 = new Handler(){
			public void handleMessage(Message msg) {

				//Log.i("qweqweqweqweqwe", "00000000000");
				//关闭ProgressDialog  
				String string=msg.obj.toString();

				try {
					JSONObject jobject=new JSONObject(string);
					String statecode=jobject.getString("StateCode");
					//Log.i("qweqweqweqweqwe", statecode);
					if(statecode.equals("800")){
						JSONArray jsonarray1=jobject.getJSONArray("DataObj");
						final JSONObject jsonobject2=jsonarray1.getJSONObject(0);
						String versname=jsonobject2.getString("Vers_Name");

						try {
							String locationname=getVersionName();
							//Log.i("3213213213", locationname);
							if(versname.equals(locationname)){
								isneedupdate=false;
							}else{
								isneedupdate=true;
							}

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}



						if(isneedupdate){
							final AlertDialog.Builder builder5 = new AlertDialog.Builder(Firstactivity.this);

							View view5 = LayoutInflater.from(Firstactivity.this).inflate(R.layout.checkversions, null);

							Button btn5=(Button) view5.findViewById(R.id.button1);
							TextView tv1=(TextView) view5.findViewById(R.id.textView2);
							tv1.setText(jsonobject2.getString("Vers_Remark"));

							btn5.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									if(isneedupdate){
										Uri uri;
										try {
											uri = Uri.parse(jsonobject2.getString("Vers_Url"));
											Intent it = new Intent(Intent.ACTION_VIEW, uri);  
											startActivity(it);

											if(jsonobject2.getString("Vers_IsForce").equals("true")){
												finish();
											}else{}

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}  


									}else{}
									dialog.dismiss();   
								}
							});

							builder5.setView(view5);
							dialog = builder5.show();   

						}else{
							//Toast.makeText(getApplicationContext(), "当前已是最新版本", 1000).show();
							secontimse();
						}


					}else if(statecode.equals("814")){
						Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
					}


				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		};



		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.firstactivity);



			mybaidutongji();

			firsttimse();
			//secontimse(); 



		}

		public boolean isNetworkConnected(Context context) {  
			if (context != null) {  
				ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
						.getSystemService(Context.CONNECTIVITY_SERVICE);  
				NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
				if (mNetworkInfo != null) {  
					return mNetworkInfo.isAvailable();  
				}  
			}  
			return false;  
		} 

		class MynewAsyncTask extends AsyncTask<String, Void, String>{					  //访问网络

			@Override
			protected String doInBackground(String... arg0) {
				if(isNetworkConnected(getApplicationContext())){
					try {
						sb.setLength(0);
						HttpGet get =new HttpGet("http://api.ddearn.com/AppService.svc/"+arg0[0]+"/");
						HttpClient client=new DefaultHttpClient();
						HttpResponse res=client.execute(get);
						if(res.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
							InputStream is=res.getEntity().getContent();
							BufferedReader brf=new BufferedReader(new InputStreamReader(is,"UTF-8"));
							String str;
							isget=true;


							while((str=brf.readLine())!=null){
								sb.append(str);
								if(arg0[1].equals("0")){
									write(str,0);

								}else{
									write(str,1);
								}

							}
							//Log.i("123123qwe",  sb.toString());
							return sb.toString();

						}else{
							write("{'StateCode':900,'StateExplain':'数据错误，请稍后再试'}",0);
							isget=false;
							return "{'StateCode':900,'StateExplain':'数据错误，请稍后再试'}";
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{

				}

				return null;


			}
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);


			}
		}

		String road;
		public void write(String str,int x){					// 写文件


			try {
				if(x==0){
					road=getApplicationContext().getFilesDir().getAbsolutePath()+"product"+".txt";
				}else{
					road=getApplicationContext().getFilesDir().getAbsolutePath()+"longproduct"+".txt";
				}

				File saveFile=new File(road);
				FileOutputStream outStream = new FileOutputStream(saveFile);
				outStream.write(str.getBytes());
				outStream.close();
				//Log.i("tag",Environment.getExternalStorageDirectory()+"");//获取sd卡相对路径
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

		private void mybaidutongji() {
			// TODO Auto-generated method stub
			StatService.setAppKey("36d285c752"); // appkey必须在mtj网站上注册生成，该设置建议在AndroidManifest.xml中填写，代码设置容易丢失

			/*
			 * 设置渠道的推荐方法。该方法同setAppChannel（String）， 如果第三个参数设置为true（防止渠道代码设置会丢失的情况），将会保存该渠道，每次设置都会更新保存的渠道，
			 * 如果之前的版本使用了该函数设置渠道，而后来的版本需要AndroidManifest.xml设置渠道，那么需要将第二个参数设置为空字符串,并且第三个参数设置为false即可。
			 * appChannel是应用的发布渠道，不需要在mtj网站上注册，直接填写就可以 该参数也可以设置在AndroidManifest.xml中
			 */
			StatService.setAppChannel(this, "RepleceWithYourChannel", true);
			// 测试时，可以使用1秒钟session过期，这样不断的间隔1S启动退出会产生大量日志。
			StatService.setSessionTimeOut(1);
			// setOn也可以在AndroidManifest.xml文件中填写，BaiduMobAd_EXCEPTION_LOG，打开崩溃错误收集，默认是关闭的
			StatService.setOn(this, StatService.EXCEPTION_LOG);
			/*
			 * 设置启动时日志发送延时的秒数<br/> 单位为秒，大小为0s到30s之间<br/> 注：请在StatService.setSendLogStrategy之前调用，否则设置不起作用
			 * 
			 * 如果设置的是发送策略是启动时发送，那么这个参数就会在发送前检查您设置的这个参数，表示延迟多少S发送。<br/> 这个参数的设置暂时只支持代码加入，
			 * 在您的首个启动的Activity中的onCreate函数中使用就可以。<br/>
			 */
			StatService.setLogSenderDelayed(0);
			/*
			 * 用于设置日志发送策略<br /> 嵌入位置：Activity的onCreate()函数中 <br />
			 * 
			 * 调用方式：StatService.setSendLogStrategy(this,SendStrategyEnum. SET_TIME_INTERVAL, 1, false); 第二个参数可选：
			 * SendStrategyEnum.APP_START SendStrategyEnum.ONCE_A_DAY SendStrategyEnum.SET_TIME_INTERVAL 第三个参数：
			 * 这个参数在第二个参数选择SendStrategyEnum.SET_TIME_INTERVAL时生效、 取值。为1-24之间的整数,即1<=rtime_interval<=24，以小时为单位 第四个参数：
			 * 表示是否仅支持wifi下日志发送，若为true，表示仅在wifi环境下发送日志；若为false，表示可以在任何联网环境下发送日志
			 */

			StatService.setSendLogStrategy(this,SendStrategyEnum. SET_TIME_INTERVAL, 1, false);
			StatService.setSendLogStrategy(this, SendStrategyEnum.APP_START, 0);
			// 调试百度统计SDK的Log开关，可以在Eclipse中看到sdk打印的日志，发布时去除调用，或者设置为false
			StatService.setDebugOn(true);
		}


		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				finish();
				handler3.sendEmptyMessage(3);
				return false;
			}
			return super.onKeyDown(keyCode, event);
		}



		private void secontimse() {
			// TODO Auto-generated method stub

			sp=getSharedPreferences("SP", MODE_PRIVATE);
			PushManager.getInstance().initialize(this.getApplicationContext());
			Long tsLong = System.currentTimeMillis()/1000;
			if(tsLong-sp.getLong("lastlogintime", 0)>259200){
				Editor editor = sp.edit();
				editor.putBoolean("islogin", false);
				editor.commit();
			}

			String[] urlstring={"GetProductList","0"};

			String urlpinstring="PINLogin/Cust_ID="+sp.getString("custid","无")+"&Cust_PIN="+sp.getString("pin","0")+"&ClientId="+sp.getString("ClientID", "0");

			if(isNetworkConnected(getApplicationContext())){
				new MynewAsyncTask().execute(urlstring);
				//new MynewAsyncTask().execute(urlstring1);
				MyAsyncTask myAsyncTask=new MyAsyncTask();	
				myAsyncTask.setHandler(handler5);
				myAsyncTask.execute(urlpinstring);

			}else{
				//read();
				isconect=false;

				Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
			}

			//新建线程  
			new Thread(){  

				@Override  
				public void run() {

					try {
						sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					handler3.sendEmptyMessage(1);
					try {
						sleep(7000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler3.sendEmptyMessage(2);
				}
			}.start(); 

		}

		private void firsttimse() {
			// TODO Auto-generated method stub
			String urlstring="GetAndroidVers";

			MyAsyncTask myasyncyask=new MyAsyncTask();

			if(myasyncyask.isNetworkConnected(getApplicationContext())){
				myasyncyask.setHandler(handler1);
				myasyncyask.execute(urlstring);
			}else{
				Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
			}

		}

		private String getVersionName() throws Exception
		{
			// 获取packagemanager的实例
			PackageManager packageManager = getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			String version = packInfo.versionName;
			return version;
		}

}
