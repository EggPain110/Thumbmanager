package com.example.thumb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;


public class MyNewMainactivity extends FragmentActivity {
	static FragmentTabHost mTabHost;
	Button btn1,btn2,btn3,btn4;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	SharedPreferences sp;
	static Page1 two;
	static FragmentTransaction ft;
	static MyNewMainactivity instance;
	static FragmentManager fm;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mynewmainactivity);

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);

		instance=this;

		scrrenWidth = metric.widthPixels ;
		scrrenHeight = metric.heightPixels;		

		sp = getSharedPreferences("SP",MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("ScrrenWidth", scrrenWidth);
		editor.putInt("ScrrenHeight",scrrenHeight);
		editor.commit();


		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn3=(Button) findViewById(R.id.button3);
		btn4=(Button) findViewById(R.id.button4);
		btn1.setBackgroundResource(R.drawable.redstar1);

		InitView();
		mybutton();
	}

	private void InitView() {
		// TODO Auto-generated method stub
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		mTabHost.getTabWidget().setVisibility(View.GONE);	//隐藏系统的TabWidget

		mTabHost.addTab(mTabHost.newTabSpec("one").setIndicator("first"),
				FirstPage.class, null);

		mTabHost.addTab(mTabHost.newTabSpec("two").setIndicator("second"),
				Page1.class, null);

		mTabHost.addTab(mTabHost.newTabSpec("three").setIndicator("third"),
				ThirdPage.class, null);

		mTabHost.addTab(mTabHost.newTabSpec("four").setIndicator("fouth"),
				FouthPage.class, null);


		mTabHost.setCurrentTabByTag("one");

		mTabHost.setOnTabChangedListener(new TabChangeListener());

	}

	class TabChangeListener implements OnTabChangeListener{

		@Override
		public void onTabChanged(String arg0) {
			// TODO Auto-generated method stub
			Log.i("1231321321321321", arg0);

			switch (arg0) {
			case "one":
				btn1.setBackgroundResource(R.drawable.redstar1);
				btn2.setBackgroundResource(R.drawable.redmoney2);
				btn3.setBackgroundResource(R.drawable.human2);
				btn4.setBackgroundResource(R.drawable.point2);
				break;
			case "two":

				btn1.setBackgroundResource(R.drawable.redstar2);
				btn2.setBackgroundResource(R.drawable.redmoney1);
				btn3.setBackgroundResource(R.drawable.human2);
				btn4.setBackgroundResource(R.drawable.point2);

				break;
			case "three":

				btn1.setBackgroundResource(R.drawable.redstar2);
				btn2.setBackgroundResource(R.drawable.redmoney2);
				btn3.setBackgroundResource(R.drawable.human1);
				btn4.setBackgroundResource(R.drawable.point2);

				break;
			case "four":

				btn1.setBackgroundResource(R.drawable.redstar2);
				btn2.setBackgroundResource(R.drawable.redmoney2);
				btn3.setBackgroundResource(R.drawable.human2);
				btn4.setBackgroundResource(R.drawable.point1);

				break;

			default:
				break;
			}
		}

	}

	private void mybutton() {

		fm = getSupportFragmentManager();
		final FirstPage one = (FirstPage) fm.findFragmentByTag("one");
		two = (Page1) fm.findFragmentByTag("two");
		final ThirdPage three = (ThirdPage) fm.findFragmentByTag("three");
		final FouthPage four = (FouthPage) fm.findFragmentByTag("four");

		ft = fm.beginTransaction();

		//** Detaches the androidfragment if exists */
		if (one != null)
			ft.detach(one);
		if (two != null)
			ft.detach(two);
		if (three != null)
			ft.detach(three);
		if (four != null)
			ft.detach(four);
		// TODO Auto-generated method stub

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (one == null) {
					ft.add(android.R.id.tabcontent, new FirstPage(), "one");
				} else {
					ft.attach(one);
				}
				mTabHost.setCurrentTabByTag("one");  
			}
		});


		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub


				if (two == null) {
					ft.add(android.R.id.tabcontent, new Page1(), "two");
				} else {
					ft.attach(two);
				}
				mTabHost.setCurrentTabByTag("two");  


			}
		});


		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(sp.getBoolean("islogin", false)){
					if (three == null) {
						ft.add(android.R.id.tabcontent, new  ThirdPage(), "three");
					} else {
						ft.attach(three);
					}
					mTabHost.setCurrentTabByTag("three");

				}else{
					startActivity(new Intent(MyNewMainactivity.this,Loginactivity.class));
				}
			}
		});


		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (four == null) {
					ft.add(android.R.id.tabcontent, new FouthPage(), "four");
				} else {
					ft.attach(four);
				}
				mTabHost.setCurrentTabByTag("four");  
			}
		});
	}


	private static boolean isExit = false;

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

	/*	public static void mycallback() {
		// TODO Auto-generated method stub

		if (two == null) {
			ft.add(android.R.id.tabcontent, new Page1(), "two");
		} else {
			ft.attach(two);
		}
		mTabHost.setCurrentTabByTag("two");  

		//mTabHost.setCurrentTabByTag("two");  
	}
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(sp.getBoolean("isneedgototwo", false)){
			if (two == null) {
				ft.add(android.R.id.tabcontent, new Page1(), "two");
			} else {
				ft.attach(two);
			}
			mTabHost.setCurrentTabByTag("two");  


			Editor editor=sp.edit();
			editor.putBoolean("isneedgototwo", false);
			editor.commit();

		}
	}
}


