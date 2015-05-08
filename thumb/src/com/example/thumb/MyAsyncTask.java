package com.example.thumb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class MyAsyncTask extends AsyncTask<String, Void, String>{
		StringBuffer sb;
		Handler handler;
		public Handler getHandler() {
			return handler;
		}
		public void setHandler(Handler handler) {
			this.handler = handler;
		}
		@Override
		protected String doInBackground(String... arg0) {
		sb=new StringBuffer();
			try {
				sb.setLength(0);
			
				HttpGet  get=new HttpGet("http://api.ddearn.com/AppService.svc/"+arg0[0]);
				HttpClient client=new DefaultHttpClient();
		
				HttpResponse res=client.execute(get);
				//Logi("123123123", arg0[0]+"");
				//Logi("123123123", get+"");
		
				
				if(res.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					InputStream is=res.getEntity().getContent();
					BufferedReader brf=new BufferedReader(new InputStreamReader(is,"UTF-8"));
					String str;
					while((str=brf.readLine())!=null){
						sb.append(str);
						
					}
					//Toast.makeText(getApplicationContext(), sb.toString(), 1000).show();
					//Logi("json123123123123",sb+"");
					return sb.toString();
					
				}else{
					//Toast.makeText(getApplicationContext(),"出错", 1000).show();
					//Logi("123123123", res.getStatusLine().getStatusCode()+"");
					return "{'StateCode':900,'StateExplain':'数据错误，请稍后再试'}";
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			 Message msg = handler.obtainMessage(0x01);  
			 msg.obj = result;  
			 msg.sendToTarget();
		
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
}
