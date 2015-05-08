package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Serviceagreement extends Activity{

Button btn1;
private WebView webview; 
TextView tv1;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.projectintroduce);
	Intent intent=getIntent();
	webview=(WebView) findViewById(R.id.webView1);
	tv1=(TextView) findViewById(R.id.textView1);
	tv1.setText("银联服务协议");
	webview.loadUrl("http://api.ddearn.com/Page/Pact/Pay.html");
	btn1=(Button) findViewById(R.id.button1);
	btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
}}
