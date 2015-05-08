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
					Toast.makeText(getApplicationContext(), "��֤�����", 1000).show();
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
				{"�������䣿","����ְҵ�ǣ�","������","��ͥ����","��ҵ״��","Ͷ�ʾ���","Ͷ��֪ʶ"},
				{"25������","˽Ӫ��ҵ��","150������","δ��","�޷�������Ͷ�ʷ�","10������","��רҵִ��"},
				{"25~35��","����Ա","100��150��","˫н����Ů","��լ�޷���","6~10��","�ƾ�רҵ��ҵ"},
				{"36��-45��","����","50��100��","˫н����Ů","����<50","2~5��","�������ĵ�"},
				{"46��-60��","����ְҵ","25��50��","��н����Ů","����>50","һ������","��һЩ"},
				{"60������","����","25������","��/��������","����լ","�޾���","һ����֪"}};
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
				btn7.setText("��һ��");
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

			

			if(btn7.getText().equals("�ύ")){
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
				btn7.setText("�ύ");
			}else{}

			break;  

		default:  
			break;  
		}  


	}
	
	private void httpscore(int sum) {
		// TODO Auto-generated method stub
		String urlstring="UpdateRiskScore/Cust_ID="+sp.getString("custid","��")+"&RiskScore="+sum;
		MyAsyncTask myAsyncTask=new MyAsyncTask();	

		if(myAsyncTask.isNetworkConnected(getApplicationContext())){
			myAsyncTask.setHandler(handler1);
			myAsyncTask.execute(urlstring);
			
		
		}else{
			Toast.makeText(getApplicationContext(), "����������", 1000).show();
		}
	}
}
