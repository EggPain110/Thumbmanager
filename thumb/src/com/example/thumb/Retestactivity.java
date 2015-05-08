package com.example.thumb;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Retestactivity extends Activity{
	SharedPreferences sp;
	Button btn1,btn2;
	RatingBar ratingbar;
	TextView tv2,tv3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.retest);
		sp = getSharedPreferences("SP",MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isopen", true);
		
		
		tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);
		ratingbar=(RatingBar) findViewById(R.id.ratingBar1);
		ratingbar.setMax(100);
		int score=sp.getInt("uerscore", 21);

		if(score<=57){
			editor.putString("风险等级","保守型");
			tv2.setText("1R 保守型—低风险级别产品");
			tv2.setTextColor(0xffEC2F3D);
			tv3.setText("您的风险承担能力较低，资产的安全性应是您重要的考虑因素，并非收益较高的理财产品，建议您购买风险较低、稳定性较高、流动性较高、收益较平稳类的理财产品。");
			ratingbar.setProgress(20);
		}else if(score>57&&score<81){
			editor.putString("风险等级","稳健型");
			tv2.setText("2R 稳健型—中风险级别产品");
			tv2.setTextColor(0xff45CAEB);
			tv3.setText("您的风险承担能力一般，在资产安全性上可以承担较小风险，收益稳中增长的理财产品，建议您购买起购金额较高、理财周期较长、收益增加较稳定类的理财产品。");
			ratingbar.setProgress(60);
		}else if(score>=81){
			editor.putString("风险等级","激进型");
			tv2.setText("3R 激进型—高风险级别产品");
			tv2.setTextColor(0xff6ADC5E);
			tv3.setText("您的风险承担能力较高，在一定程度上可以承受高风险、高收益的理财产品，在购买理财产品时，建议您在购买理财产品时选择收益较高的理财产品，同时最大限度的增加投资金额，以便获得更高的收益。");
			ratingbar.setProgress(100);
		}
		editor.commit();
		
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				finish();
			}
		});
		btn2=(Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Log.i("wherein", sp.getString("wherein", "2"));
				if(sp.getString("wherein", "2").equals("2")){
					Personalactivity.instance.finish();
					Editor editor=sp.edit();
					editor.putBoolean("isneedgototwo", true);
					editor.commit();
				}else{
					JianyipeibiActivity.instance.finish();
					
				}
				
				finish();
				
			}
		});
	}
}
