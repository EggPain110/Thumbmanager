package com.example.thumb;

import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ThirdPage extends Fragment{

	View view;
	ScrollView sc;
	SharedPreferences sp;
	public static int scrrenWidth;  // ��Ļ���
	public static int scrrenHeight;  //��Ļ�߶�
	boolean isfirstopen=true;
	CircleProgressBarView circleprogressbar;
	RelativeLayout relativelayout;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11;
	private TextView textView1, textView2;
	Typeface face;
	private int bmpw = 0;// ����ͼƬ����
	private int offset = 0;// ����ͼƬƫ����
	int i;
	private ViewPager viewPager;// ҳ������
	private ImageView imageView;// ����ͼƬ
	private Fragment view1, view2;// ����ҳ��
	private List<Fragment> views;// Tabҳ���б�
	private int currIndex = 0;// ��ǰҳ�����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.myproperty, container, false);
		sp = getActivity().getSharedPreferences("SP",0x0000);
		initTextView();
		initImage();
		initViewPager();

		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(sp.getBoolean("isneedfreesh", false)){
			Editor editor=sp.edit();
			editor.putBoolean("isneedfreesh", false);
			editor.commit();
			initViewPager();
		}else{}
	}
	
	private void initViewPager() {
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		views = new ArrayList<Fragment>();
		view1 = new Page3();
		view2 = new Page4();

		views.add(view1);
		views.add(view2);

		viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager()));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new PageChangeLisener());
		
	}
	
	private void initImage() {
		imageView = (ImageView) view.findViewById(R.id.image);
		bmpw = BitmapFactory.decodeResource(getResources(),
				R.drawable.slidingline).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int w = dm.widthPixels;// ��ȡ��Ļ�ֱ��ʵĿ��
		offset = (w /2 - bmpw) / 2;// ����ƫ����
		//Log.i("tag", "w-->" + w + "," + "bmpw-->" + bmpw + "," + "offset-->"
		//		+ offset);
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);
	}

	
	private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter{
		/**
		 * ���췽��
		 * @param fm
		 */
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		/**
		 * ��ȡ��ǰҳ��
		 */
		@Override
		public Fragment getItem(int arg0) {
			return views.get(arg0);
		}
		/**
		 * ��ȡ��ʾ��ҳ������
		 */
		@Override
		public int getCount() {
			return views.size();
		}
		
	}
	
	private void initTextView() {
		textView1 = (TextView) view.findViewById(R.id.main_textview1);
		textView2 = (TextView) view.findViewById(R.id.main_textview2);
		
		textView1.setOnClickListener(new TextViewClickListener());
		textView2.setOnClickListener(new TextViewClickListener());
		
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
	
	
	class PageChangeLisener implements OnPageChangeListener {
		int one = offset * 2 + bmpw;// ҳ��1 -> ҳ��2 ƫ����
		int two = offset * 2;// ҳ��1 -> ҳ��3 ƫ����

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
			
			switch (arg0) {
			case 0:
				textView1.setTextColor(0XFFC51627);
				textView2.setTextColor(0XFF858585);
				
				break;
			case 1:
				textView2.setTextColor(0XFFC51627);
				textView1.setTextColor(0XFF858585);
				break;

			default:
				break;
			}
		}

	}
	
	
	

	
	
	
	
}

