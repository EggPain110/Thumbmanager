package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Erweima extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.erweima);
		Intent intent=getIntent();
		int x=intent.getIntExtra("positon", 2);
		ImageView ima=(ImageView) findViewById(R.id.imageView2);
		switch (x) {
		case 2:
			ima.setBackgroundResource(R.drawable.sinaerweima);
			break;
		case 3:
			ima.setBackgroundResource(R.drawable.weixinerweima);
			break;

		default:
			break;
		}
		
		Button btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}

}
