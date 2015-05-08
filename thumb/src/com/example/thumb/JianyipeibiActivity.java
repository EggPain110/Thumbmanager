package com.example.thumb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class JianyipeibiActivity extends Activity{
	Button btn1,btn2;
	SharedPreferences sp;
	static JianyipeibiActivity instance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.jianyipeibiactivity);
		instance=this;
		sp=getSharedPreferences("SP", MODE_PRIVATE);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(sp.getBoolean("islogin", false)){
					
					startActivity(new Intent(JianyipeibiActivity.this,Risktestactivity.class));
				}else{
					startActivity(new Intent(JianyipeibiActivity.this,Loginactivity.class));
				}
				
			}
		});
	}
}
