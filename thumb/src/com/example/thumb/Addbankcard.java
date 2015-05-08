package com.example.thumb;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Addbankcard extends Activity{
	EditText edt1,edt2;
	Button btn1,btn2;
	SharedPreferences sp;
	String custname;
	

	private MyProgressdialog progressDialog;  
	private Spinner province_spinner,bankname_spinner;
	private Spinner city_spinner;
	private Integer provinceId, cityId;
	private String strProvince, strCity,bankname;
	private int[] city = {R.array.beijin_province_item, R.array.tianjin_province_item, R.array.heibei_province_item, R.array.shanxi1_province_item, R.array.neimenggu_province_item, R.array.liaoning_province_item, R.array.jilin_province_item, R.array.heilongjiang_province_item, R.array.shanghai_province_item, R.array.jiangsu_province_item, R.array.zhejiang_province_item, R.array.anhui_province_item, R.array.fujian_province_item, R.array.jiangxi_province_item, R.array.shandong_province_item, R.array.henan_province_item, R.array.hubei_province_item, R.array.hunan_province_item, R.array.guangdong_province_item,  R.array.guangxi_province_item, R.array.hainan_province_item, R.array.chongqing_province_item, R.array.sichuan_province_item, R.array.guizhou_province_item, R.array.yunnan_province_item, R.array.xizang_province_item, R.array.shanxi2_province_item, R.array.gansu_province_item, R.array.qinghai_province_item, R.array.linxia_province_item, R.array.xinjiang_province_item, R.array.hongkong_province_item, R.array.aomen_province_item, R.array.taiwan_province_item};


	private ArrayAdapter<CharSequence> province_adapter;
	private ArrayAdapter<CharSequence> city_adapter;
	private ArrayAdapter<CharSequence> bankname_adapter;



	Handler handler1 = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			String string=msg.obj.toString();

			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");

				if(statecode.equals("800")){
					handler10.sendEmptyMessage(0);  
					Toast.makeText(getApplicationContext(),"添加成功",1000).show();
					Editor editor=sp.edit();
					editor.putBoolean("isfirst", false);
					editor.commit();
					
					finish();
				}else{
					
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain") , 1000).show();

				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String city=myresult.getString("citynm");
		};  
	};  


	private Handler handler10 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  
			progressDialog.dismiss();  

		}
	};  


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.addbankcard);
			sp=getSharedPreferences("SP",MODE_PRIVATE);

			btn1=(Button) findViewById(R.id.button1);
			btn2=(Button) findViewById(R.id.Buttonbangding);
			//btn3=(Button) findViewById(R.id.button3);

			edt1=(EditText) findViewById(R.id.editText1);
			edt2=(EditText) findViewById(R.id.editText2);



			edt1.setEnabled(false);
			//edt2.setEnabled(false);
			edt1.setHint(sp.getString("custname", "**"));
			//edt2.setHint(sp.getString("userid", "0000"));


			btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

			btn2.setEnabled(true);
			btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				

					if("".equals(edt2.getText().toString().trim())){
							Toast.makeText(getApplicationContext(), "请输入银行卡号", 1000).show();
							return;
						}else{}
					String cardcode=edt2.getText().toString().trim();

					String custid=sp.getString("custid","无");

				

					String urlstring="AddCustAccount/Cust_ID="+custid+"&Acc_Bank="+bankname+"&Acc_CardCode="+cardcode+"&Acc_BankProvince="+strProvince+"&Acc_BankCity="+strCity;
					Log.i("123132131231", urlstring);
					MyAsyncTask myAsyncTask=new MyAsyncTask();	

					progressDialog = new MyProgressdialog(Addbankcard.this);
					progressDialog.show();

					//新建线程  
					new Thread(){  

						@Override  
						public void run() {  
							try {
								sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//向handler发消息  
							handler10.sendEmptyMessage(0);  
						}
					}.start(); 

					if(myAsyncTask.isNetworkConnected(getApplicationContext())){
						myAsyncTask.setHandler(handler1);
						myAsyncTask.execute(urlstring);
					}else{
						Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
					}



				}
			});


			loadSpinner();

		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			//btn3.setText(sp.getString("bankname", "选择银行"));
		}


		private void loadSpinner()
		{
			province_spinner = (Spinner) findViewById(R.id.province_spinner);
			province_spinner.setPrompt("请选择省份");
			province_adapter = ArrayAdapter.createFromResource(this, R.array.province_item, android.R.layout.simple_spinner_item);
			province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			province_spinner.setAdapter(province_adapter);
			//select(province_spinner, province_adapter, R.array.province_item);
			province_spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
			{	
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) 
				{					
					provinceId = province_spinner.getSelectedItemPosition();
					strProvince = province_spinner.getSelectedItem().toString();
					city_spinner = (Spinner) findViewById(R.id.city_spinner);
					if(true)
					{	
					
						city_spinner = (Spinner) findViewById(R.id.city_spinner);
						city_spinner.setPrompt("请选择城市");
						select(city_spinner, city_adapter, city[provinceId]);
						city_spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
						{

							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								cityId = city_spinner.getSelectedItemPosition();
								strCity = city_spinner.getSelectedItem().toString();
								
							}

							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub

							}

						});							
					}
				}

				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});


			bankname_spinner=(Spinner) findViewById(R.id.bankname_spinner);
			bankname_adapter=ArrayAdapter.createFromResource(this, R.array.bankname_item, android.R.layout.simple_spinner_item);
			bankname_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			bankname_spinner.setAdapter(bankname_adapter);

			bankname_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {


					bankname=bankname_spinner.getSelectedItem().toString();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {

				}
			});
		}

		private void select(Spinner spin, ArrayAdapter<CharSequence> adapter, int arry)
		{
			adapter = ArrayAdapter.createFromResource(this, arry, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(adapter);
			//spin.setSelection(0,true);
		}


}
