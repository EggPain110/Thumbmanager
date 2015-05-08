package com.example.thumb;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Systemmessageactivity extends FragmentActivity {

	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private TextView textView1, textView2;
	private List<Fragment> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpw = 0;// 动画图片长度
	private Fragment view1, view2;// 各个页卡
	
	RelativeLayout rlayout;

	Button btn1;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//Log.i("tag", "on create");
		setContentView(R.layout.systemmessageactivity);
		
		initTextView();
		initImage();
		initViewPager();
	}

	// 初始化页卡内容
	private void initViewPager() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		views = new ArrayList<Fragment>();
		view1 = new Page10();
		view2 = new Page11();
	
		views.add(view1);
		views.add(view2);
		
		viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new PageChangeLisener());
	}

	// 初始化动画图片
	private void initImage() {
		imageView = (ImageView) findViewById(R.id.image);
		bmpw = BitmapFactory.decodeResource(getResources(),
				R.drawable.slidingline).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int w = dm.widthPixels;// 获取屏幕分辨率的宽度
		offset = (w /2 - bmpw) / 2;// 计算偏移量
		//Log.i("tag", "w-->" + w + "," + "bmpw-->" + bmpw + "," + "offset-->"
		//		+ offset);
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);
	}

	// 初始化TextView
	private void initTextView() {
		textView1 = (TextView) findViewById(R.id.main_textview1);
		textView2 = (TextView) findViewById(R.id.main_textview2);


		textView1.setOnClickListener(new TextViewClickListener());
		textView2.setOnClickListener(new TextViewClickListener());
		
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	class TextViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.main_textview1:
				viewPager.setCurrentItem(0);
				break;
			case R.id.main_textview2:
				viewPager.setCurrentItem(1);
				break;
				
			default:
				break;
			}
		}

	}

	class ViewPagerAdapter extends FragmentPagerAdapter {

		private List<Fragment> views;

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
			views = Systemmessageactivity.this.views;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return views.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

	}

	class PageChangeLisener implements OnPageChangeListener {
		int one = offset * 2 + bmpw;// 页卡1 -> 页卡2 偏移量
		int two = offset * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Animation animation = new TranslateAnimation(one * currIndex, one
					* arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			imageView.startAnimation(animation);
		}

	}
}