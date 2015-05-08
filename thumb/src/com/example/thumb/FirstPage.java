package com.example.thumb;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPage extends Fragment {

	View view;
	ScrollView sc;
	SharedPreferences sp;
	public static int scrrenWidth;  // 屏幕宽度
	public static int scrrenHeight;  //屏幕高度
	boolean isonshow=true;
	CircleProgressBarView circleprogressbar;
	private MyProgressdialog myprogressDialog;  
	RelativeLayout relativelayout;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tvscroll1,tvscroll2,tvscroll3,tvscroll4,tvscroll5;
	String productid,productname,shouxufei,yuqishouyi,licaiqixian,fengxiandengji,iscouldbuy,explan,iscouldedit,shenyufengshu,str4;
	String yuqishouyichanshengshijian;
	Button btn1;
	Typeface face;
	int i,beginmoney;
	boolean isconect;
	int scrollnum;
	String[][] chasedList;
	JSONObject jobject8,jobject1;
	int pro;
	Mythtead mythread;
	Handler handler = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			circleprogressbar.setProgress(msg.what);

		};  
	}; 


	private Handler handler11 = new Handler(){  

		@Override  
		public void handleMessage(Message msg) {

			if((chasedList==null)||(chasedList.length==0)){

			}else{

				if(scrollnum<chasedList.length-1){
					scrollnum++;
				}else{
					scrollnum=0;
				}

				tvscroll1.setText(chasedList[scrollnum][0]);
				tvscroll2.setText("  投资  ");
				tvscroll3.setText(chasedList[scrollnum][1]);
				tvscroll4.setText("  年化   ");
				tvscroll5.setText(chasedList[scrollnum][2]);
			}

		}}; 


		Handler handler5 = new Handler() {  
			public void handleMessage(android.os.Message msg) { 
				//xx.setProgress(msg.what);
				String string=msg.obj.toString();
				handler10.sendEmptyMessage(0);  
				
				
				final int pro1=pro;

				new Thread(){
					public void run() {
						while(i<=pro1){
							try {
								sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handler.sendEmptyMessage(i);  
							i++;
						}

					};
				}.start();
				
				
				
				isconect=true;

				try {
					JSONObject jobject5=new JSONObject(string);
					String statecode=jobject5.getString("StateCode");

					if(statecode.equals("800")){
						//Log.i("456456465",  msg.obj+"");
						JSONObject jobject6=jobject5.getJSONObject("DataObj");
						//Log.i("6666666",  jobject6.toString()+"");
						JSONArray jobject7=jobject6.getJSONArray("ProductDetail");
						//	Log.i("7777777",  jobject7.toString()+"");
						jobject8=new JSONObject(jobject7.getJSONObject(0).toString());

						if(jobject8.getString("Ipro_name").length()>4){

							productname=new String(jobject8.getString("Ipro_name").substring(0,4));													// 产品名称
						}else{
							productname=new String(jobject8.getString("Ipro_name"));					// 产品名称
						}

						//productname=new String(jobject8.getString("Ipro_name"));		//产品名称
						beginmoney=Integer.parseInt(String.format("%.0f", jobject8.getDouble("Ipro_amount")));		// 起购金额
						shouxufei=new String("无");										//手续费
						//yuqishouyi=new String(String.format("%.2f", jobject1.getDouble("pctInterest"))+"%");									//预期收益
						yuqishouyi=str4;
						licaiqixian=new String(jobject8.getString("dayDiff"));			//理财期限
						yuqishouyichanshengshijian=jobject8.getString("Ipro_accrualstart");	//预期收益时间
						fengxiandengji=jobject8.getString("Ipro_suggest");
						iscouldbuy=jobject8.getString("isAble");
						explan=jobject8.getString("specialRemark");
						iscouldedit=jobject8.getString("isAuto");
						tv5.setText("剩余"+jobject8.getString("IproRemaindCopy")+"份");		// 剩余份数

						JSONArray jsonarray2=jobject6.getJSONArray("PurchasedList");

						chasedList=new String[jsonarray2.length()][3];
						for (int i = 0; i < jsonarray2.length(); i++) {
							JSONObject jobject9=jsonarray2.getJSONObject(i);
							chasedList[i][0]=jobject9.getString("mobilePhone");
							chasedList[i][1]=jobject9.getString("purchase_money");
							chasedList[i][2]=jobject9.getString("pctInterest")+"%";

						}


						/*mythread=new Mythtead();
						mythread.start();
						Log.i("thread", "thread");*/

					}else{
						//Log.i("456qweqwe",  msg.obj+"");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*	btn7.setClickable(true);
				btn8.setClickable(true);
				btn9.setClickable(true);
				btn10.setClickable(true);*/
			};

		};  


		private Handler handler10 = new Handler(){  

			@Override  
			public void handleMessage(Message msg) {  

				//关闭ProgressDialog  
				myprogressDialog.dismiss();  

			}};  


			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				view=inflater.inflate(R.layout.firstpage, container, false);

				sc=(ScrollView) view.findViewById(R.id.scrollview);
				sc.setVerticalScrollBarEnabled(false);
				sp = getActivity().getSharedPreferences("SP",0x0000);
				scrrenWidth=sp.getInt("ScrrenWidth", 1080);
				scrrenHeight=sp.getInt("ScrrenHeight", 1920);
				relativelayout=(RelativeLayout) view.findViewById(R.id.relativeLayout1);

				scrollnum=0;

				tvscroll1 = (TextView) view.findViewById(R.id.textview11);
				tvscroll2 = (TextView) view.findViewById(R.id.textview12);
				tvscroll3 = (TextView) view.findViewById(R.id.textview13);
				tvscroll4 = (TextView) view.findViewById(R.id.textview14);
				tvscroll5 = (TextView) view.findViewById(R.id.textview15);

				tv1=(TextView) view.findViewById(R.id.textView3);
				tv2=(TextView) view.findViewById(R.id.textView4);
				face= Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf");  
				tv1.setTypeface (face);
				tv2.setTypeface(face);


				tv3=(TextView) view.findViewById(R.id.textView5);
				tv4=(TextView) view.findViewById(R.id.textView6);
				tv5=(TextView) view.findViewById(R.id.textView7);
				tv6=(TextView) view.findViewById(R.id.textView8);

				circleprogressbar=(CircleProgressBarView) view.findViewById(R.id.circleProgressBarView1);
				circleprogressbar.setVisibility(View.VISIBLE);
				circleprogressbar.setMax(100);
				circleprogressbar.setProgress(0);
				circleprogressbar.setScrrenwidth(scrrenWidth);


				btn1=(Button) view.findViewById(R.id.button1);
				btn1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						//startActivity(new Intent(MainActivity.this,Buyactivity1.class));
						if(null == jobject8 || "".equals(jobject8) || "{}".equals(jobject8) || "[]".equals(jobject8)){ 
							return;
						}



						if(sp.getBoolean("islogin", false)){
							if(isconect){
								Intent intent=new Intent(getActivity(),Buyactivity1.class);
								intent.putExtra("productname", productname);
								intent.putExtra("beginmoney", beginmoney);
								intent.putExtra("shouxufei", shouxufei);
								intent.putExtra("yuqishouyi", yuqishouyi+"%");
								intent.putExtra("licaiqixian", licaiqixian);
								intent.putExtra("yuqishouyichanshengshijian", yuqishouyichanshengshijian);
								intent.putExtra("productid", productid);
								intent.putExtra("fengxiandengji", fengxiandengji);
								intent.putExtra("iscouldbuy", iscouldbuy);
								intent.putExtra("explan", explan);
								intent.putExtra("iscouldedit", iscouldedit);
								startActivity(intent);
							}else{}


						}else{

							startActivity(new Intent(getActivity(),Loginactivity.class));
						}




					}
				});

				relativelayout.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Toast.makeText(getActivity(), "321321321321", 1000).show();
						Intent intent=new Intent(getActivity(),Productdetailactivity.class);
						try {
							intent.putExtra("productid", jobject1.getString("Ipro_id"));
							intent.putExtra("productname", jobject1.getString("Ipro_name"));
							intent.putExtra("buynum","30天购买人数    "+jobject1.getString("purchaseNum"));
							intent.putExtra("day", "期限（天）"+jobject1.getString("dayDiff"));
							intent.putExtra("shouyi", String.format("%.2f", jobject1.getDouble("pctInterest"))+"%");
							startActivity(intent);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					}
				});

				read();
				return view;
			}
			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				isonshow=false;

			}

			@Override
			public void onResume() {
				// TODO Auto-generated method stub
				super.onResume();
				isonshow=true;
				Mythtead mythread=new Mythtead();
				mythread.start();
				
				circleprogressbar.setProgress(pro);

			}


			class  Mythtead extends Thread {
				// TODO Auto-generated method stub
				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					while (isonshow) {
						for (int i = 0; i<(0.0416*scrrenHeight); i++) {
							//Log.i("thread", i+"");
							sc.scrollTo(0, i);
							//handler11.sendEmptyMessage(i);
							try {
								sleep(20);

								if(!isonshow){
									return;
								}else{}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}


						try {

							for (int i = 0; i < 20; i++) {

								sleep(40);
								if(!isonshow){
									return;
								}else{}
							}

						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						sc.scrollTo(0, 0);

						handler11.sendEmptyMessage(0);
						try {
							sleep(80);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}



			private void read() {
				// TODO Auto-generated method stub

				String road=getActivity().getFilesDir().getAbsolutePath()+"product"+".txt";

				try {
					File file = new File(road);    
					FileInputStream fis = new FileInputStream(file);    
					int length = fis.available();   
					byte [] buffer = new byte[length];   
					fis.read(buffer);       
					String res1 = EncodingUtils.getString(buffer, "UTF-8");   
					fis.close();       
					json(res1.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			}

			public void json(String result){
				//Log.i("1321321321", result);
				try {
					JSONObject jobject=new JSONObject(result);
					String statecode=jobject.getString("StateCode");

					if("800".equals(statecode)){

						JSONArray myresult=jobject.getJSONArray("DataObj");
						jobject1=new JSONObject(myresult.getJSONObject(0).toString());
						
						str4=String.format("%.2f", jobject1.getDouble("pctInterest"));
						tv1.setText(str4);												// 预期年化收益
						if(jobject1.getString("Ipro_name").length()>6){
							tv3.setText(jobject1.getString("Ipro_name").substring(0, 6));
							// 产品名称
						}else{
							tv3.setText(jobject1.getString("Ipro_name"));
						}
						
					
						
						String productid=jobject1.getString("Ipro_id");
						tv4.setText(jobject1.getString("Ipro_amount")+"元起购");				// 起购金额
						tv6.setText("期限"+jobject1.getString("dayDiff")+"天");				// 期限
						circleprogressbar.setPersonnum(jobject1.getInt("purchaseNum"));					//progressbar  人数

						pro=(int) (jobject1.getDouble("pctPurchased")*100)+1;		//完成进度
						if(pro>100){
							pro=100;
						}

						/*final int pro1=pro;

						new Thread(){
							public void run() {
								while(i<=pro1){
									try {
										sleep(10);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									handler.sendEmptyMessage(i);  
									i++;
								}

							};
						}.start();*/

						askfordetail(productid);

					}else{
						isconect=false;
						Toast.makeText(getActivity(), "无可用网络", 1000).show();
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void askfordetail(String productid) {
				// TODO Auto-generated method stub
				String urlstring="GetProductDetail/Product_ID="+productid+"&IndexTag="+"1"+"&Cust_ID="+sp.getString("custid", "0");
				MyAsyncTask myAsyncTask=new MyAsyncTask();	

				if(myAsyncTask.isNetworkConnected(getActivity())){
					myAsyncTask.setHandler(handler5);
					myAsyncTask.execute(urlstring);
					myprogressDialog = new MyProgressdialog(getActivity());
					myprogressDialog.show();

				}else{
					isconect=false;
					Toast.makeText(getActivity(), "请连接网络", 1000).show();
				}
			}

			@Override
			public void setUserVisibleHint(boolean isVisibleToUser) {
				// TODO Auto-generated method stub
				super.setUserVisibleHint(isVisibleToUser);
				isonshow=isVisibleToUser;
				//Log.i("isonshow", isVisibleToUser+"");

			}

}
