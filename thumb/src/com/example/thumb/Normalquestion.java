package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Normalquestion extends Activity{
	ListView listView;
	String[] array;
	TextView tv1,tv2;
	Button btn1;
	private WebView webview; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.normalquestion);
		Intent intent = getIntent();
		array=new String[]{"常见问题","账户操作","安全保障","基金帮助"};
		tv1=(TextView) findViewById(R.id.textView1);

		tv1.setText(array[intent.getIntExtra("extra", 0)]);

		webview=(WebView) findViewById(R.id.webView1);


		switch (intent.getIntExtra("extra", 0)) {
		case 0:
			webview.loadUrl("http://api.ddearn.com/Page/Help/QA.html");
			break;
		case 1:
			webview.loadUrl("http://api.ddearn.com/Page/Help/Operate.html");
			break;
		case 2:
			webview.loadUrl("http://api.ddearn.com/Page/Help/Safety.html");
			break;
			
		case 3:
			webview.loadUrl("http://api.ddearn.com/Page/Help/DDBHelp.html");
			break;


		default:
			break;
		}




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
