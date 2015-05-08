package com.example.thumb;

import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Teachactivity extends FragmentActivity{

	private ViewPager viewPager;// ҳ������
	private ImageView imageView1,imageView2,imageView3,imageView4,imageView5;// ����ͼƬ
	
	Button btn1,btn2,btn3,btn4;
	RelativeLayout rlayout;
	private List<Fragment> views;// Tabҳ���б�
	private int offset = 0;// ����ͼƬƫ����

	private int bmpw = 0;// ����ͼƬ����
	private Fragment view1, view2,view3,view4,view5;// ����ҳ��
	SharedPreferences sp;

	public static int scrrenWidth  ;  // ��Ļ���
	public static int scrrenHeight ;  //��Ļ�߶�

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			super.onCreate(savedInstanceState);
			//Log.i("tag", "on create");
			setContentView(R.layout.teachactivity);
			
			sp = getSharedPreferences("SP",MODE_PRIVATE);
			scrrenWidth=sp.getInt("ScrrenWidth", 1080);
			scrrenHeight=sp.getInt("ScrrenHeight", 1920);
			imageView1 = (ImageView) findViewById(R.id.imageview1);
			imageView2 = (ImageView) findViewById(R.id.imageview2);
			imageView3 = (ImageView) findViewById(R.id.imageview3);
			imageView4 = (ImageView) findViewById(R.id.imageview4);
			imageView5 = (ImageView) findViewById(R.id.imageview5);
			//initImage();
			initViewPager();
		}

	

		// ��ʼ��ҳ������
		private void initViewPager() {
			viewPager = (ViewPager) findViewById(R.id.viewpager);
			views = new ArrayList<Fragment>();
			view1 = new Page21();
			view2 = new Page22();
			view3 = new Page23();
			view5 = new Page25();
			view4 = new Page24();

			views.add(view1);
			views.add(view2);
			views.add(view3);
			views.add(view5);
			views.add(view4);

			viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
			viewPager.setCurrentItem(0);
			viewPager.setOnPageChangeListener(new PageChangeLisener());
		}

		// ��ʼ������ͼƬ
		private void initImage() {
			imageView1 = (ImageView) findViewById(R.id.image);
			bmpw = BitmapFactory.decodeResource(getResources(),R.drawable.slidingckeck1).getWidth();
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			int w = dm.widthPixels;// ��ȡ��Ļ�ֱ��ʵĿ��
			offset = (w / 2 - bmpw) / 2;// ����ƫ����
			//Log.i("tag", "w-->" + w + "," + "bmpw-->" + bmpw + "," + "offset-->"
			//		+ offset);
			Matrix matrix = new Matrix();
			matrix.postTranslate(offset, 0);
			imageView1.setImageMatrix(matrix);
		}



	

		class ViewPagerAdapter extends FragmentPagerAdapter {

			private List<Fragment> views;

			public ViewPagerAdapter(FragmentManager fm) {
				super(fm);
				// TODO Auto-generated constructor stub
				views = Teachactivity.this.views;
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
				/*Animation animation = new TranslateAnimation(one * currIndex, one
				 * arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(0);
			imageView.startAnimation(animation);*/
				switch (arg0) {
				case 0:
					imageView1.setBackgroundResource(R.drawable.firstcheck2);
					imageView2.setBackgroundResource(R.drawable.firstcheck1);
					imageView3.setBackgroundResource(R.drawable.firstcheck1);
					imageView4.setBackgroundResource(R.drawable.firstcheck1);
					imageView5.setBackgroundResource(R.drawable.firstcheck1);
					break;
				case 1:
					imageView1.setBackgroundResource(R.drawable.firstcheck1);
					imageView2.setBackgroundResource(R.drawable.firstcheck2);
					imageView3.setBackgroundResource(R.drawable.firstcheck1);
					imageView4.setBackgroundResource(R.drawable.firstcheck1);
					imageView5.setBackgroundResource(R.drawable.firstcheck1);
				
					break;
					
				case 2:
					
					imageView1.setBackgroundResource(R.drawable.firstcheck1);
					imageView2.setBackgroundResource(R.drawable.firstcheck1);
					imageView3.setBackgroundResource(R.drawable.firstcheck2);
					imageView4.setBackgroundResource(R.drawable.firstcheck1);
					imageView5.setBackgroundResource(R.drawable.firstcheck1);
					break;
					
				case 3:
					imageView1.setVisibility(View.VISIBLE);
					imageView2.setVisibility(View.VISIBLE);
					imageView3.setVisibility(View.VISIBLE);
					imageView4.setVisibility(View.VISIBLE);
					imageView5.setVisibility(View.VISIBLE);
					imageView1.setBackgroundResource(R.drawable.firstcheck1);
					imageView2.setBackgroundResource(R.drawable.firstcheck1);
					imageView3.setBackgroundResource(R.drawable.firstcheck1);
					imageView4.setBackgroundResource(R.drawable.firstcheck2);
					imageView5.setBackgroundResource(R.drawable.firstcheck1);
					break;
				case 4:
					imageView1.setVisibility(View.INVISIBLE);
					imageView2.setVisibility(View.INVISIBLE);
					imageView3.setVisibility(View.INVISIBLE);
					imageView4.setVisibility(View.INVISIBLE);
					imageView5.setVisibility(View.INVISIBLE);
					break;

				default:
					break;
				}
			}

		}

	


}
