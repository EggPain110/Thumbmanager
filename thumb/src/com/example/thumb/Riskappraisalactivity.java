package com.example.thumb;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Riskappraisalactivity extends Activity{
	RatingBar ratingbar;
	TextView tv2;
	Button btn1,btn2;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.riskappraisal);
		ratingbar=(RatingBar) findViewById(R.id.ratingBar1);
		ratingbar.setMax(100);
		ratingbar.setProgress(20);
		tv2=(TextView) findViewById(R.id.textView2);
		String str="为了保证您的权益，我们将默认设置您的投资风险承受为 保守型";
		SpannableStringBuilder style=new SpannableStringBuilder(str); 
		//SpannableStringBuilder实现CharSequence接口 
		style.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 26,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE ); 
		style.setSpan(new ForegroundColorSpan(Color.RED), 26, 29,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		//style.setSpan(new RelativeSizeSpan((float) 1.5),26, 29,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE ); 
		tv2.setText(style);
		sp=getSharedPreferences("SP",0x0000);
		Editor editor=sp.edit();
		editor.putString("wherein", "2");
		editor.commit();
		
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Riskappraisalactivity.this,Risktestactivity.class));
				finish();
			}
		});
		btn2=(Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
