package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;

public class Huodongxiangqin extends Activity{
	private WebView webview; 
	
	SharedPreferences sp;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.huodong);
		Intent intent=getIntent();
		sp = getSharedPreferences("SP",MODE_PRIVATE);
		webview=(WebView) findViewById(R.id.webView1);
		
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		String  url="http://api.ddearn.com/Act/Detail.aspx?Act_ID=";
		url=url+sp.getString("huodong"+intent.getStringExtra("position"), "0");
		webview.loadUrl(url);
	}
}
