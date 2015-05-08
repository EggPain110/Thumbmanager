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
			editor.putString("���յȼ�","������");
			tv2.setText("1R �����͡��ͷ��ռ����Ʒ");
			tv2.setTextColor(0xffEC2F3D);
			tv3.setText("���ķ��ճе������ϵͣ��ʲ��İ�ȫ��Ӧ������Ҫ�Ŀ������أ���������ϸߵ���Ʋ�Ʒ��������������սϵ͡��ȶ��Խϸߡ������Խϸߡ������ƽ�������Ʋ�Ʒ��");
			ratingbar.setProgress(20);
		}else if(score>57&&score<81){
			editor.putString("���յȼ�","�Ƚ���");
			tv2.setText("2R �Ƚ��͡��з��ռ����Ʒ");
			tv2.setTextColor(0xff45CAEB);
			tv3.setText("���ķ��ճе�����һ�㣬���ʲ���ȫ���Ͽ��Գе���С���գ�����������������Ʋ�Ʒ�������������𹺽��ϸߡ�������ڽϳ����������ӽ��ȶ������Ʋ�Ʒ��");
			ratingbar.setProgress(60);
		}else if(score>=81){
			editor.putString("���յȼ�","������");
			tv2.setText("3R �����͡��߷��ռ����Ʒ");
			tv2.setTextColor(0xff6ADC5E);
			tv3.setText("���ķ��ճе������ϸߣ���һ���̶��Ͽ��Գ��ܸ߷��ա����������Ʋ�Ʒ���ڹ�����Ʋ�Ʒʱ���������ڹ�����Ʋ�Ʒʱѡ������ϸߵ���Ʋ�Ʒ��ͬʱ����޶ȵ�����Ͷ�ʽ��Ա��ø��ߵ����档");
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
