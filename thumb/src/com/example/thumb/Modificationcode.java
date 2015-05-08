package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Modificationcode extends Activity{
	private EditText edt1,edt2,edt3;
	private CheckBox check;
	ImageView img9,img10,img11;
	TextView tv10;
	Button btn1,btn4;
	SharedPreferences sp;
	
	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();
			
			btn4.setEnabled(true);
			//Log.i("123123qwe",  msg.obj+"");
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					Toast.makeText(getApplicationContext(), "修改成功", 1000).show();
					finish();
				}else{
					
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
		};  
	};  

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.modificationcode);
		edt1=(EditText)findViewById(R.id.editText1);
		edt2=(EditText)findViewById(R.id.editText2);

		edt2.addTextChangedListener(watcher);			//添加edittext变化监听；

		edt3=(EditText)findViewById(R.id.editText3);
		
	
		
		check=(CheckBox)super.findViewById(R.id.checkBox1);
		img9=(ImageView) findViewById(R.id.imageView1);
		img10=(ImageView) findViewById(R.id.imageView2);
		img11=(ImageView) findViewById(R.id.imageView3);
		tv10=(TextView) findViewById(R.id.textView10);
		
		img9.setVisibility(View.INVISIBLE);
		img10.setVisibility(View.INVISIBLE);
		img11.setVisibility(View.INVISIBLE);
		tv10.setVisibility(View.INVISIBLE);
		
		sp=getSharedPreferences("SP", MODE_PRIVATE);
		check.setOnClickListener(new OnClickListener()
		{ 
			@Override
			public void onClick(View v) 
			{
				if(check.isChecked())
				{
					//设置密码可见
					edt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					edt3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				else
				{
					//设置密码隐藏
					edt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
					edt3.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			}

		});
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		btn4=(Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(edt1.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入原密码", 1000).show();
					return;
				}

				if("".equals(edt2.getText().toString().trim())||"".equals(edt3.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请输入新密码", 1000).show();
					return;
				}
				
				if("".equals(edt3.getText().toString().trim())||"".equals(edt3.getText().toString().trim())){
					Toast.makeText(getApplicationContext(), "请再次输入新密码", 1000).show();
					return;
				}
				if(edt2.getText().toString().equals(edt3.getText().toString())){
					if(edt2.getText().toString().length()>5){
						String logincode=Md5.stringToMD5(edt1.getText().toString());
						String newcode=Md5.stringToMD5(edt2.getText().toString());
						String custid=sp.getString("custid", "无");
						if(custid.equals("无")){
							startActivity(new Intent(Modificationcode.this,Loginactivity.class));
							finish();
						}
						String urlstring="UpdateLoginPass/Cust_ID="+custid+"&Cust_LoginPass="+logincode+"&Cust_NewPass="+newcode;
						MyAsyncTask myAsyncTask=new MyAsyncTask();	
						if(myAsyncTask.isNetworkConnected(getApplicationContext())){
							btn4.setEnabled(false);
							myAsyncTask.setHandler(handler1);
							myAsyncTask.execute(urlstring);
						}else{
							Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
						}
						
					}else{
						Toast.makeText(getApplicationContext(), "密码长度过短", 1000).show();
					}
					
				}else{
					Toast.makeText(getApplicationContext(), "两次输入的密码不一致", 1000).show();
				}
				
				
			}
		});
		

		
	}
	
	TextWatcher watcher=new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

			String passwordStr = edt2.getText().toString();

			if(passwordStr.length()>5){
				ascallcheckpassword(passwordStr);
			}else{
				img9.setImageResource(R.drawable.huipoin);
				img10.setImageResource(R.drawable.huipoin);
				img11.setImageResource(R.drawable.huipoin);
				tv10.setText("无");
			}

		}
	};

	private void ascallcheckpassword(String passwordStr) {
		// TODO Auto-generated method stub
		int a=0,b=0,c=0;
		for (int i = 0; i < passwordStr.length(); i++) {

			char ch=passwordStr.charAt(i); 
			//Log.i("123123qwe", ch+"");
			int ascall=(int) ch;
			//Log.i("123123qwe", ascall+"");
			if(ascall>31&&ascall<=47){
				a=1;							//特殊符号
			}else if(ascall>47&&ascall<=57){
				b=1;							// 数字
			}else if(ascall>57&&ascall<=64){
				a=1;							//特殊符号
			}else if(ascall>64&&ascall<=90){
				c=1;							//大写字母
			}else if(ascall>90&&ascall<=96){
				a=1;							//特殊符号
			}else if(ascall>96&&ascall<=122){
				c=1;							//小写字母
			}else if(ascall>122&&ascall<=126){
				a=1;							//特殊符号
			}else{}
		}
		switch (a+b+c) {
		case 0:
			img9.setImageResource(R.drawable.huipoin);
			img10.setImageResource(R.drawable.huipoin);
			img11.setImageResource(R.drawable.huipoin);
			tv10.setText("无");

			break;
		case 1:
			img9.setImageResource(R.drawable.coderedpiont);
			img10.setImageResource(R.drawable.huipoin);
			img11.setImageResource(R.drawable.huipoin);
			tv10.setText("低");
			break;
		case 2:
			img9.setImageResource(R.drawable.coderedpiont);
			img10.setImageResource(R.drawable.codeyellowpiont);
			img11.setImageResource(R.drawable.huipoin);
			tv10.setText("中");

			break;
		case 3:
			img9.setImageResource(R.drawable.coderedpiont);
			img10.setImageResource(R.drawable.codeyellowpiont);
			img11.setImageResource(R.drawable.codegerrenpiont);
			tv10.setText("高");

			break;

		default:
			break;
		}

	}
	
	


}
