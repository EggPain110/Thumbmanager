package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Moneysafe extends Activity{
	TextView tv5,tv6,tv7;
	Button btn1;
	private WebView webview; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.moneysafe);
		webview=(WebView) findViewById(R.id.webView1);
		//设置WebView属性，能够执行Javascript脚本  
		 Intent intent=getIntent();
		 
		//webview.loadUrl("http://api.ddearn.com/"+"Page/"+intent.getStringExtra("productid")+".html");
		
		 webview.loadUrl("http://api.ddearn.com/Page/Safety.aspx?Product_ID="+intent.getStringExtra("productid"));
		
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
