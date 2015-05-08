package com.example.thumb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebviewActivityByYY extends Activity{
private WebView webview; 
TextView tv1;
Button btn1;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.projectintroduce);
		webview=(WebView) findViewById(R.id.webView1);
		webview.loadUrl("http://api.ddearn.com/Page/Pact/Reg.html");
		
		webview.setWebViewClient(new WebViewClient(){
	         @Override
	         public boolean shouldOverrideUrlLoading(WebView view, String url) {
	 
	          view.loadUrl(url);   //在当前的webview中跳转到新的url
	          
	          return true;
	         }
	        });
		
		
		tv1=(TextView) findViewById(R.id.textView1);
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	public void Settoptitle(String title) {
		// TODO Auto-generated method stub
		tv1.setText(title);
	}
	
	public void SetUrl(String url) {
		// TODO Auto-generated method stub
		webview.loadUrl(url);
	}
}
