package com.example.thumb;


import android.content.Context;  

import android.graphics.Canvas;  

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;  
import android.graphics.Paint;  
import android.graphics.Path;
import android.graphics.Paint.Cap;

import android.util.AttributeSet;
import android.view.View;  

public class DrawView extends View   
{
	private static final String Tag = "123";
	int width,height,first,second,third,forth,fifth,sixth,seventh;
	//SharedPreferences sp;
	
	
	//public static int scrrenWidth  ;  // ÆÁÄ»¿í¶È
	//public static int scrrenHeight ;  //ÆÁÄ»¸ß¶È
	private int progress;  
	private int max; 
	float[] currentxy;
	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		//width=context.getResources().getDisplayMetrics().widthPixels;

	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//sp = context.getSharedPreferences("SP",0);
		width=this.getResources().getDisplayMetrics().widthPixels/8;

		first=width;
		second=width*2;
		third=width*3;
		forth=width*4;
		fifth=width*5;
		sixth=width*6;
		seventh=width*7;
		currentxy=new float[2];
		// TODO Auto-generated constructor stub
	}  
	public DrawView(Context mContext) {  
		super(mContext);

	} 


	public float[] getCurrentxy() {
		return currentxy;
	}

	public void setCurrentxy(float[] currentxy) {
		this.currentxy = currentxy;
		invalidate();
	}

	private float[] tendency;
	public float[] getTendency() {
		return tendency;
	}

	public void setTendency(float[] tendency) {
		this.tendency = tendency;

	}

	Paint paint=new Paint();  
	public int getMax() {  
		return max;  
	}  
	public void setMax(int max) {  
		this.max = max;  
	}  
	public int getProgress() {  
		return progress;  
	}  
	public void setProgress(int progress) {  
		this.progress = progress;  
		invalidate();

	}  


	@Override 
	protected void onDraw(Canvas mCanvas)   
	{  
		super.onDraw(mCanvas);  
		paint.setAntiAlias(true);// ÉèÖÃÊÇ·ñ¿¹¾â³Ý  
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);// °ïÖúÏû³ý¾â³Ý  

		paint.setStrokeWidth(3);// ÉèÖÃ»­±Ê¿í¶È  

		paint.setColor(0x7FE11A2F);
		paint.setStrokeCap(Cap.ROUND);//»­±ÊÑùÊ½ÎªÔ²

		Path path1=new Path(); 
        path1.moveTo(first,tendency[0]);  
		path1.lineTo(second,tendency[1]);  
		path1.lineTo(third,tendency[2]);  
		path1.lineTo(forth,tendency[3]);  
		path1.lineTo(fifth,tendency[4]);  
		path1.lineTo(sixth,tendency[5]);
		path1.lineTo(seventh,tendency[6]);
		path1.lineTo(seventh,800);
		path1.lineTo(first,800);
		path1.moveTo(first,tendency[0]);  
		path1.close();//·â±Õ  



		mCanvas.drawPath(path1, paint);  
		paint.setColor(Color.WHITE);
		mCanvas.drawRect((progress-70)*8, 0, width*8, 800, paint);
		paint.setColor(0xFFE11A2F);
		paint.setStrokeWidth(1);
		float[] mPoints=new float[]{first,tendency [0],second,tendency [1],
									second,tendency[1],third,tendency  [2],
									third,tendency [2],forth,tendency  [3],
									forth,tendency [3],fifth,tendency  [4],
									fifth,tendency [4],sixth,tendency  [5],
									sixth,tendency [5],seventh,tendency[6]
		};
		
		mCanvas.drawLines(mPoints, paint); 
		
		/*mCanvas.drawCircle(first, tendency[0], 5, paint);
		mCanvas.drawCircle(second, tendency[1], 5, paint);
		mCanvas.drawCircle(third, tendency[2], 5, paint);
		mCanvas.drawCircle(forth, tendency[3], 5, paint);
		mCanvas.drawCircle(fifth, tendency[4], 5, paint);
		mCanvas.drawCircle(sixth, tendency[5], 5, paint);
		mCanvas.drawCircle(seventh, tendency[6], 5, paint);*/
		paint.setColor(Color.WHITE);
		mCanvas.drawRect(progress*8, 0, width*8, 800, paint);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redmessagebox); 
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.redmessagebox2); 
		if(currentxy[0]>width*5){
			mCanvas.drawBitmap(bitmap2, currentxy[0]-4*bitmap.getWidth()/5,(float) (currentxy[1]-bitmap.getHeight()*1.1), paint);
			paint.setColor(Color.WHITE);
			mCanvas.drawCircle(currentxy[0], currentxy[1], 5, paint);
			paint.setColor(0xFFE11A2F);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(3);
			mCanvas.drawCircle(currentxy[0], currentxy[1], 5, paint);
			paint.setStyle(Paint.Style.FILL);
		}else{
			mCanvas.drawBitmap(bitmap, currentxy[0]-bitmap.getWidth()/5,(float) (currentxy[1]-bitmap.getHeight()*1.1), paint); 
			paint.setColor(Color.WHITE);
			mCanvas.drawCircle(currentxy[0], currentxy[1], 5, paint);
			paint.setColor(0xFFE11A2F);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(3);
			mCanvas.drawCircle(currentxy[0], currentxy[1], 5, paint);
			paint.setStyle(Paint.Style.FILL);
		}
	
		

		//Log.i(Tag, width+"");
	}  
	


}

