package com.example.thumb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Huodongactivity extends Activity{
	private WebView webview; 
	Button btn;
	boolean isclick;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.huodong);
		isclick=false;
		webview=(WebView) findViewById(R.id.webView1);
		
		webview.setWebViewClient(new WebViewClient(){
	         @Override
	         public boolean shouldOverrideUrlLoading(WebView view, String url) {
	 
	          view.loadUrl(url);   //在当前的webview中跳转到新的url
	          isclick=true;
	          return true;
	         }
	        });
		
		webview.loadUrl("http://api.ddearn.com/Act/List.aspx");
		
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isclick){
					webview.loadUrl("http://api.ddearn.com/Act/List.aspx");
					isclick=false;
				}else{
					finish();
				}
				
				
			}
		});
	}
}
