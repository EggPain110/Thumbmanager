package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Risktestactivity extends Activity implements View.OnClickListener{
	String[][] array;
	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
	TextView tv2,tv3;
	int[] score;
	int i;
	SharedPreferences sp;
	

	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					
					//Log.i("123123qwe",  806+"");
					

				}else if(statecode.equals("807")){
					Toast.makeText(getApplicationContext(), "验证码错误", 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
			finish();
			startActivity(new Intent(Risktestactivity.this,Retestactivity.class));
		};  
	}; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.risktest);
		sp=getSharedPreferences("SP", MODE_PRIVATE);
		i=0;
		array=new String[][]{{"1/7","2/7","3/7","4/7","5/7","6/7","7/7"},
				{"您的年龄？","您的职业是？","年收入","家庭负担","置业状况","投资经验","投资知识"},
				{"25岁以下","私营企业主","150万以上","未婚","无房贷且有投资房","10年以上","有专业执照"},
				{"25~35岁","公务员","100到150万","双薪无子女","自宅无房贷","6~10年","财经专业毕业"},
				{"36岁-45岁","白领","50到100万","双薪有子女","房贷<50","2~5年","自修有心得"},
				{"46岁-60岁","自由职业","25万到50万","单薪有子女","房贷>50","一年以内","懂一些"},
				{"60岁以上","退休","25万以下","赡/抚养三代","无自宅","无经验","一无所知"}};
		score=new int[]{3,3,3,3,3,3,3};

		tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);

		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn3=(Button) findViewById(R.id.button3);
		btn4=(Button) findViewById(R.id.button4);
		btn5=(Button) findViewById(R.id.button5);
		btn6=(Button) findViewById(R.id.button6);
		btn7=(Button) findViewById(R.id.button7);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);


	}
	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub

		switch (v.getId()) {  
		case R.id.button1: 
			if(i>0){
				i--;
			}else {}
			
			tv2.setText(array[0][i]);
			tv3.setText(array[1][i]);
			btn2.setText(array[2][i]);
			btn3.setText(array[3][i]);
			btn4.setText(array[4][i]);
			btn5.setText(array[5][i]);
			btn6.setText(array[6][i]);

			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn5.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			
			if(i<6){
				btn7.setText("下一题");
			}else{}

			break;  
		case R.id.button2:  
			score[i]=15;
			btn2.setBackgroundResource(R.drawable.choseboxred);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn5.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			break;  
		case R.id.button3:
			score[i]=12;
			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxred);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn5.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			break;  
		case R.id.button4:  
			score[i]=9;
			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxred);
			btn5.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			break;  
		case R.id.button5:  
			score[i]=6;
			btn5.setBackgroundResource(R.drawable.choseboxred);
			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			break;  
		case R.id.button6: 
			score[i]=3;
			btn6.setBackgroundResource(R.drawable.choseboxred);
			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn5.setBackgroundResource(R.drawable.choseboxhui);

			break;  

		case R.id.button7:  
			
			
			if(i<6){
				i++;
			}else {}

			

			if(btn7.getText().equals("提交")){
				int sum=0;
				for (int i = 0; i < array.length; i++) {
					sum+=score[i];
				}
				//Log.i("sum", sum+"");
				Editor editor=sp.edit();
				editor.putInt("uerscore", sum);
				editor.commit();
				httpscore(sum);
				
				
			}else{}


			tv2.setText(array[0][i]);
			tv3.setText(array[1][i]);
			btn2.setText(array[2][i]);
			btn3.setText(array[3][i]);
			btn4.setText(array[4][i]);
			btn5.setText(array[5][i]);
			btn6.setText(array[6][i]);

			btn2.setBackgroundResource(R.drawable.choseboxhui);
			btn3.setBackgroundResource(R.drawable.choseboxhui);
			btn4.setBackgroundResource(R.drawable.choseboxhui);
			btn5.setBackgroundResource(R.drawable.choseboxhui);
			btn6.setBackgroundResource(R.drawable.choseboxhui);
			
			if(i==6){
				btn7.setText("提交");
			}else{}

			break;  

		default:  
			break;  
		}  


	}
	
	private void httpscore(int sum) {
		// TODO Auto-generated method stub
		String urlstring="UpdateRiskScore/Cust_ID="+sp.getString("custid","无")+"&RiskScore="+sum;
		MyAsyncTask myAsyncTask=new MyAsyncTask();	

		if(myAsyncTask.isNetworkConnected(getApplicationContext())){
			myAsyncTask.setHandler(handler1);
			myAsyncTask.execute(urlstring);
			
		
		}else{
			Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
		}
	}
}
