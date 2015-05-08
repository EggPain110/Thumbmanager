package com.example.thumb;

import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.Paint;  
import android.graphics.RectF;  
import android.graphics.Paint.Cap;
import android.graphics.Typeface;
import android.util.AttributeSet;  
import android.view.View;  

public class CircleProgressBarView extends View { 
	Typeface face ;
	public CircleProgressBarView(Context context) {
		super(context,null);
		paint = new Paint();  
		oval = new RectF(); 
		face = Typeface.createFromAsset (context.getAssets() , "fonts/fangz.ttf" );
	}
	public CircleProgressBarView(Context context, AttributeSet attrs) {  
		super(context, attrs);  
		paint = new Paint();  
		oval = new RectF();  
		face = Typeface.createFromAsset (context.getAssets() , "fonts/fangz.ttf" );
	}  



	private int progress;  
	private int max;  
	private Paint paint;  
	private RectF oval;  
	private int scrrenwidth,scrrenheight;
	private int personnum;
	public int getPersonnum() {
		return personnum;
	}
	public void setPersonnum(int personnum) {
		this.personnum = personnum;
	}
	public int getScrrenwidth() {
		return scrrenwidth;
	}
	public void setScrrenwidth(int scrrenwidth) {
		this.scrrenwidth = scrrenwidth;
	}
	public int getScrrenheight() {
		return scrrenheight;
	}
	public void setScrrenheight(int scrrenheight) {
		this.scrrenheight = scrrenheight;
	}
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
	protected void onDraw(Canvas canvas) {  
		super.onDraw(canvas);  
		oval.set((float) (0.02*scrrenwidth), (float) (0.02*scrrenwidth), (float) (0.333*scrrenwidth), (float) (0.333*scrrenwidth));
		paint.setAntiAlias(true);// 设置是否抗锯齿  
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿  
		paint.setColor(0XFF898989);// 设置画笔灰色  
		paint.setStrokeWidth((float) (0.02*scrrenwidth));// 设置画笔宽度  
		paint.setStrokeCap(Cap.ROUND);//画笔样式为圆
		paint.setStyle(Paint.Style.STROKE);// 设置中空的样式  
		canvas.drawArc(oval, -225, 270, false, paint);
		//canvas.drawCircle(85, 85, 75, paint);// 在中心为（100,100）的地方画个半径为55的圆，宽度为setStrokeWidth：10，也就是灰色的底边  
		paint.setColor(0XFFFFB200);// 设置画笔颜色  
		//oval.set(10, 10, 160, 160);// 设置类似于左上角坐标（45,45），右下角坐标（155,155），这样也就保证了半径为55  
		//oval.set(55, 55, 155, 155);

		canvas.drawArc(oval, -225, ((float) progress / max) * 270, false, paint);// 画圆弧，第二个参数为：起始角度，第三个为跨的角度，第四个为true的时候是实心，false的时候为空心  
		paint.reset();// 将画笔重置  
		paint.setStrokeWidth((float) (0.00625*scrrenwidth));// 再次设置画笔的宽度  
		paint.setTextSize((float) (0.0873*scrrenwidth));// 设置文字的大小  
		paint.setFakeBoldText(true);
		paint.setTypeface(face);
		paint.setColor(0XFFE11A2F);// 设置画笔颜色
		if(progress<10){
			canvas.drawText(progress + "%",(float) (0.12*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else if(progress>9&&progress<100){
			canvas.drawText(progress + "%",(float) (0.089*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else if(progress>=100){
			canvas.drawText(progress + "%",(float) (0.06*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else{}
		
		paint.reset();// 将画笔重置  
		paint.setColor(0XFFFA5C5B);// 设置画笔颜色  
		paint.setStrokeWidth((float) (0.00625*scrrenwidth));// 再次设置画笔的宽度  
		paint.setTextSize((float) (0.05*scrrenwidth));
		
		if(getPersonnum()<10){
			canvas.drawText(getPersonnum()+"人购买", (float) (0.09*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>9&&getPersonnum()<100){
			canvas.drawText(getPersonnum()+"人购买", (float) (0.078*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>99&&getPersonnum()<1000){
			canvas.drawText(getPersonnum()+"人购买", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>999&&getPersonnum()<10000){
			paint.setTextSize((float) (0.042*scrrenwidth));
			canvas.drawText(getPersonnum()+"人购买", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>9999&&getPersonnum()<100000){
			paint.setTextSize((float) (0.038*scrrenwidth));
			canvas.drawText(getPersonnum()+"人购买", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else{}
		
	}  

	

}  
