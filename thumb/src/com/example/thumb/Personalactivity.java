package com.example.thumb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Personalactivity extends Activity{
	ListView listView;
	String[] array;
	private int[] imgIdArray ;
	RelativeLayout relative1;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度

	String custcertid,cid;
	ImageView img1,img2;
	Button btn1,btn2;
	TextView tv1,tv2,tv3,tv4;
	String custname;


	AlertDialog dialog ;

	private MyProgressdialog myprodialog;


	//private ProgressDialog progressDialog;  
	static Personalactivity instance;
	boolean isneedupdate=false;
	android.os.Handler handler=new android.os.Handler(){


		@Override  
		public void handleMessage(Message msg) {  

			//关闭ProgressDialog  


			myprodialog.dismiss();


		}
	};

	android.os.Handler handler1=new android.os.Handler(){


		@Override  
		public void handleMessage(Message msg) {  
			//Log.i("qweqweqweqweqwe", "00000000000");
			//关闭ProgressDialog  
			String string=msg.obj.toString();
			handler.sendEmptyMessage(0);  
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");
				//Log.i("qweqweqweqweqwe", statecode);
				if(statecode.equals("800")){
					JSONArray jsonarray1=jobject.getJSONArray("DataObj");
					final JSONObject jsonobject2=jsonarray1.getJSONObject(0);
					String versname=jsonobject2.getString("Vers_Name");
					
					try {
						String locationname=getVersionName();
						//Log.i("3213213213", locationname);
						if(versname.equals(locationname)){
							isneedupdate=false;
						}else{
							isneedupdate=true;
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					final AlertDialog.Builder builder5 = new AlertDialog.Builder(Personalactivity.this);

					View view5 = LayoutInflater.from(Personalactivity.this).inflate(R.layout.checkversions, null);

					Button btn5=(Button) view5.findViewById(R.id.button1);

					if(isneedupdate){
						TextView tv1=(TextView) view5.findViewById(R.id.textView2);
						tv1.setText(jsonobject2.getString("Vers_Remark"));
					}else{
						//Toast.makeText(getApplicationContext(), "当前已是最新版本", 1000).show();
					}
					btn5.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(isneedupdate){
								Uri uri;
								try {
									uri = Uri.parse(jsonobject2.getString("Vers_Url"));
									Intent it = new Intent(Intent.ACTION_VIEW, uri);  
									startActivity(it);
									
									if(jsonobject2.getString("Vers_IsForce").equals("true")){
										finish();
										MyNewMainactivity.instance.finish();
									}else{}
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
								
								

							}else{}
							dialog.dismiss();   
						}
					});

					builder5.setView(view5);


					dialog = builder5.show();   

				}else if(statecode.equals("814")){
					Toast.makeText(getApplicationContext(), jobject.getString("StateExplain"), 1000).show();
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};



	android.os.Handler handler2 = new android.os.Handler() {  
		public void handleMessage(android.os.Message msg) { 
			//xx.setProgress(msg.what);
			handler.sendEmptyMessage(0);
			String string=msg.obj.toString();
			//Log.i("123123qwe", string);
			try {
				JSONObject jobject=new JSONObject(string);
				String statecode=jobject.getString("StateCode");



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
		setContentView(R.layout.personalmessage);
		instance=this;
		sp =getSharedPreferences("SP",MODE_PRIVATE);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);


		array=new String[]{"个人信息","实名认证","风险测评","设置","清理缓存","检查版本","联系客服","安全中心","设置登录密码","设置手势密码","找回交易密码","设置交易密码"};
		imgIdArray = new int[]{R.drawable.personalactivity0,R.drawable.personalactivity0,R.drawable.personalactivity1,R.drawable.personalactivity1,
				R.drawable.setactivity1, R.drawable.setactivity2, R.drawable.setactivity3, R.drawable.personalactivity2,
				R.drawable.safecenteractivity1, R.drawable.safecenteractivity3, R.drawable.safecenteractivity4,R.drawable.safecenteractivity2};
		tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);
		tv4=(TextView) findViewById(R.id.textView4);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn2.setVisibility(View.INVISIBLE);
		if(sp.getBoolean("islogin", false)){
			tv2.setText(sp.getString("custname", "未认证"));
			tv3.setText("邀请码："+sp.getString("custinvite", ""));
			tv4.setText("风险等级:"+sp.getString("风险等级","保守型"));

			btn2.setText("退出登录");
		}else{
			tv2.setText("");
			tv3.setText("");
			tv4.setText("");
			btn2.setText("登录");
		}
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});


		listView=(ListView) findViewById(R.id.listView1);

		listView.setAdapter(new myadpter());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), appList.get(position).packageName, 1000).show();
				//Toast.makeText(getApplicationContext(), "nihao", 1000).show();

				switch (position) {

				case 1:
					if(sp.getBoolean("islogin", false)){
						if(sp.getBoolean("istruename", false)){
							Toast.makeText(getApplicationContext(), "您已完成实名认证，不可重复认证", 1000).show();
						}else{
							startActivity(new Intent(Personalactivity.this,Shimingrenzheng.class));
						}
					}else{
						startActivity(new Intent(Personalactivity.this,Loginactivity.class));

					}

					break;

				case 2:
					if(sp.getBoolean("islogin", false)){
						startActivity(new Intent(Personalactivity.this,Riskappraisalactivity.class));
					}else{
						startActivity(new Intent(Personalactivity.this,Loginactivity.class));

					}


					break;


				case 4:
					final AlertDialog.Builder builder1 = new AlertDialog.Builder(Personalactivity.this);

					View view1 = LayoutInflater.from(Personalactivity.this).inflate(R.layout.cleandialog, null);

					Button btn1=(Button) view1.findViewById(R.id.button1);
					Button btn4=(Button) view1.findViewById(R.id.button2);
					btn1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});
					btn4.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});
					builder1.setView(view1);


					dialog = builder1.show();   

					break;
				case 5:
					String urlstring="GetAndroidVers";
					MyAsyncTask myasyncyask=new MyAsyncTask();
					myasyncyask.setHandler(handler1);
					myasyncyask.execute(urlstring);
					myprodialog=new MyProgressdialog(Personalactivity.this);
					myprodialog.show();

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
							handler.sendEmptyMessage(0);  
						}
					}.start(); 




					break;
				case 6:

					final AlertDialog.Builder builder3 = new AlertDialog.Builder(Personalactivity.this);

					View view3 = LayoutInflater.from(Personalactivity.this).inflate(R.layout.iscall, null);

					Button btn3=(Button) view3.findViewById(R.id.button1);
					btn3.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});


					Button btn2=(Button) view3.findViewById(R.id.button2);
					btn2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"4008108688"));  
							startActivity(intent);
						}
					});
					builder3.setView(view3);

					dialog = builder3.show();   


					/* Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"4008108688"));  
					 startActivity(intent);  */

					break;

					/*	case 0:
					if(sp.getBoolean("islogin", false)){

						startActivity(new Intent(Personalactivity.this,Modificationcode.class));

					}else{
						startActivity(new Intent(Personalactivity.this, Loginactivity.class));
					}
					break;*/
				case 8:
					if(sp.getBoolean("islogin", false)){

						startActivity(new Intent(Personalactivity.this,Modificationcode.class));

					}else{
						startActivity(new Intent(Personalactivity.this, Loginactivity.class));
					}
					break;


				case 9:

					Editor editor=sp.edit();
					editor.putBoolean("openfrompersonal", true);
					editor.commit();
					startActivity(new Intent(Personalactivity.this,com.way.locus.SetPasswordActivity.class));

					break;

				case 10:
					startActivity(new Intent(Personalactivity.this,Forgettradecode.class));


					break;

				case 11:
					if(sp.getBoolean("islogin", false)){

						startActivity(new Intent(Personalactivity.this,Resettradecodeactivity.class));
						Log.i("132132", "resettradecode");


					}else{
						startActivity(new Intent(Personalactivity.this, Loginactivity.class));
					}
					break;
				default:
					break;
				}
			}
		});

	}

	class myadpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			if(sp.getString("istradepass", "false").equals("true")){

				return array.length+1;

			}else{

				return array.length-1;
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub

			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		View v;
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if(listView.getCount()==position+1){
				v=getLayoutInflater().inflate(R.layout.tuichuitem, null);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth), (int) (scrrenHeight*0.12));
				v.setLayoutParams(lp);
				Button btn2=(Button) v.findViewById(R.id.button2);
				
				if(sp.getBoolean("islogin", false)){
					btn2.setText("退出登录");
				}else{
					btn2.setText("登录");
				}

				btn2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(sp.getBoolean("islogin", false)){

							outlogin();

							Editor editor=sp.edit();
							scrrenWidth=sp.getInt("ScrrenWidth", 1080);
							scrrenHeight=sp.getInt("ScrrenHeight", 1920);
							custname=sp.getString("custname", "**");
							custcertid=sp.getString("userid", "****");
							cid=sp.getString("ClientID", "0");

							editor.clear();
							editor.commit();

							editor.putInt("ScrrenWidth", scrrenWidth);
							editor.putInt("ScrrenHeight",scrrenHeight);
							editor.putBoolean("iswatch", true);
							editor.putString("ClientID", cid);
							editor.putBoolean("personactivityneedfresh", true);
							//editor.putString("userid", custcertid);
							//editor.putString("custname", custname);
							editor.commit();
							//btn2.setText("登录");

							finish();
						}else{
							Editor editor=sp.edit();
							editor.putBoolean("personactivityneedfresh", true);
							editor.commit();
							startActivity(new Intent(Personalactivity.this,Loginactivity.class));
						}
					}
				});


				return v;
			}

			if(position==0||position==3||position==7){
				v=getLayoutInflater().inflate(R.layout.biaotiitem, null);
				TextView tv2=(TextView) v.findViewById(R.id.textView1);
				tv2.setText(array[position]);
				return v;
			}else{
				v=getLayoutInflater().inflate(R.layout.aboutthumbactivityitem, null);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth), (int) (scrrenHeight*0.09));
				v.setLayoutParams(lp);
				TextView tv1=(TextView) v.findViewById(R.id.textView1);
				img1=(ImageView) v.findViewById(R.id.imageView1);
				img2=(ImageView) v.findViewById(R.id.imageView2);
				tv1.setText(array[position]);
				img2.setImageResource(imgIdArray[position]);

				if(position==1&&sp.getBoolean("istruename", false)){
					TextView tv2=(TextView) v.findViewById(R.id.textView2);
					tv2.setText("已认证");
					return v;
				}else{
					return v;
				}

			}

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(sp.getBoolean("islogin", false)){
			tv2.setText(sp.getString("custname", ""));
			tv3.setText("邀请码："+sp.getString("custinvite", ""));
			tv4.setText("风险等级:"+sp.getString("风险等级","保守型"));
			//btn2.setText("退出登录");
		}else{
			tv2.setText("");
			tv3.setText("");
			tv4.setText("");
			//btn2.setText("登录");
		}
		if(sp.getBoolean("personactivityneedfresh", false)){
			listView.setAdapter(new myadpter());
			Editor editor=	sp.edit();
			editor.putBoolean("personactivityneedfresh", false);
			editor.commit();

		}else{}
	}

	private void outlogin() {
		// TODO Auto-generated method stub
		String urlstring="LoginOut/Cust_ID="+sp.getString("custid","无");

		MyAsyncTask myAsyncTask=new MyAsyncTask();	
		if(myAsyncTask.isNetworkConnected(getApplicationContext())){
			myAsyncTask.setHandler(handler2);
			myAsyncTask.execute(urlstring);

			myprodialog=new MyProgressdialog(Personalactivity.this);
			myprodialog.show();
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
					handler.sendEmptyMessage(0);  
				}
			}.start(); 
		}else{
			Toast.makeText(getApplicationContext(), "请连接网络", 1000).show();
		}

	}
	
	 private String getVersionName() throws Exception
	   {
	           // 获取packagemanager的实例
	           PackageManager packageManager = getPackageManager();
	           // getPackageName()是你当前类的包名，0代表是获取版本信息
	           PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
	           String version = packInfo.versionName;
	           return version;
	   }


}

