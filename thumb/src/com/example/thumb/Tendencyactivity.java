package com.example.thumb;


import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;

public class Tendencyactivity extends Activity {
	DrawView dv;
	int i;
	float[] m;
	float[] array;
	float[] array2;
	float width;
	float height;
	float left;
	float top;
	float[] tendency;
	float viewwidth;
	float viewheight;
	Handler handler = new Handler() {  
		public void handleMessage(android.os.Message msg) { 
			dv.setProgress(msg.what);

		};  
	}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mytendencyactvity);

		new Thread(){
			public void run() {
				while(i<201){
					try {
						sleep(16);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(i);  
					i++;
				}

			};
		}.start();

		dv=(DrawView) findViewById(R.id.drawView1);
		DisplayMetrics dm = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		tendency=new float[]{22,24,25,23,26,27,18};
		dv.setTendency(tendency);

		width=dm.widthPixels*dm.density;   
		height=dm.heightPixels*dm.density; 


	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		array=new float[8];
		array2=new float[10];
		//tendency=new float[7];
		left=dv.getLeft();
		top=dv.getTop();
		viewwidth=(dv.getRight()-left)/8;
		viewheight=(dv.getBottom()-top)/9;
		float tendencyheight=(dv.getBottom()-top)/30;
		tendency[0]=tendency[0]*tendencyheight;
		tendency[1]=tendency[1]*tendencyheight;
		tendency[2]=tendency[2]*tendencyheight;
		tendency[3]=tendency[3]*tendencyheight;
		tendency[4]=tendency[4]*tendencyheight;
		tendency[5]=tendency[5]*tendencyheight;
		tendency[6]=tendency[6]*tendencyheight;



		array[0]=(float) ((viewwidth*1.5)+left);
		array[1]=(float) ((viewwidth*2.5)+left);
		array[2]=(float) ((viewwidth*3.5)+left);
		array[3]=(float) ((viewwidth*4.5)+left);
		array[4]=(float) ((viewwidth*5.5)+left);
		array[5]=(float) ((viewwidth*6.5)+left);
		array[6]=(float) ((viewwidth*7.5)+left);

		array2[0]=(float) ((height/2)+top);
		array2[1]=(float) ((height*1.5)+top);
		array2[2]=(float) ((height*2.5)+top);
		array2[3]=(float) ((height*3.5)+top);
		array2[4]=(float) ((height*4.5)+top);
		array2[5]=(float) ((height*5.5)+top);
		array2[6]=(float) ((height*6.5)+top);
		array2[7]=(float) ((height*7.5)+top);
		array2[8]=(float) ((height*8.5)+top);
		array2[9]=(float) ((height*9.5)+top);


	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		m=new float[2];
		/*m[0]=event.getRawX()-dv.getLeft();
		m[1]=event.getRawY()-dv.getTop()-statusBarHeight;*/
		if(left<event.getRawX()&&event.getRawX()<array[0]){

			if(Math.abs(event.getRawY()-statusBarHeight-tendency[0])<30){
				m[0]=array[0]-viewwidth/2;
				m[1]=tendency[0];

			}else{}
		}else{}

		if(array[0]<event.getRawX()&&event.getRawX()<array[1]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[1])<30){
				m[0]=array[1]-viewwidth/2;
				m[1]=tendency[1];

			}else{}
		}else{}

		if(array[1]<event.getRawX()&&event.getRawX()<array[2]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[2])<30){
				m[0]=array[2]-viewwidth/2;
				m[1]=tendency[2];

			}else{}
		}else{}

		if(array[2]<event.getRawX()&&event.getRawX()<array[3]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[3])<30){
				m[0]=array[3]-viewwidth/2;
				m[1]=tendency[3];

			}else{}
		}else{}

		if(array[3]<event.getRawX()&&event.getRawX()<array[4]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[4])<30){
				m[0]=array[4]-viewwidth/2;
				m[1]=tendency[4];

			}else{}
		}else{}

		if(array[4]<event.getRawX()&&event.getRawX()<array[5]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[5])<30){
				m[0]=array[5]-viewwidth/2;
				m[1]=tendency[5];

			}else{}
		}else{}

		if(array[5]<event.getRawX()&&event.getRawX()<array[6]){
			if(Math.abs(event.getRawY()-statusBarHeight-tendency[6])<30){
				m[0]=array[6]-viewwidth/2;
				m[1]=tendency[6];

			}else{}
		}else{}

		if(array[6]<event.getRawX()&&event.getRawY()<dv.getRight()){
			if(Math.abs(event.getRawY()-tendency[0])<30){
				m[0]=array[7];
				m[1]=tendency[7];
			}else{}
		}else{}

		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//dv.setCurrentxy(m);
		}else if(event.getAction()==MotionEvent.ACTION_UP){
			dv.setCurrentxy(m);
			//Log.i("tag", top+"");
		}
		else{
			dv.setCurrentxy(m);
		}


		return super.onTouchEvent(event);
	}
}
