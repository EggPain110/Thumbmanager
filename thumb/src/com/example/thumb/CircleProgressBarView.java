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
		paint.setAntiAlias(true);// �����Ƿ񿹾��  
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);// �����������  
		paint.setColor(0XFF898989);// ���û��ʻ�ɫ  
		paint.setStrokeWidth((float) (0.02*scrrenwidth));// ���û��ʿ��  
		paint.setStrokeCap(Cap.ROUND);//������ʽΪԲ
		paint.setStyle(Paint.Style.STROKE);// �����пյ���ʽ  
		canvas.drawArc(oval, -225, 270, false, paint);
		//canvas.drawCircle(85, 85, 75, paint);// ������Ϊ��100,100���ĵط������뾶Ϊ55��Բ�����ΪsetStrokeWidth��10��Ҳ���ǻ�ɫ�ĵױ�  
		paint.setColor(0XFFFFB200);// ���û�����ɫ  
		//oval.set(10, 10, 160, 160);// �������������Ͻ����꣨45,45�������½����꣨155,155��������Ҳ�ͱ�֤�˰뾶Ϊ55  
		//oval.set(55, 55, 155, 155);

		canvas.drawArc(oval, -225, ((float) progress / max) * 270, false, paint);// ��Բ�����ڶ�������Ϊ����ʼ�Ƕȣ�������Ϊ��ĽǶȣ����ĸ�Ϊtrue��ʱ����ʵ�ģ�false��ʱ��Ϊ����  
		paint.reset();// ����������  
		paint.setStrokeWidth((float) (0.00625*scrrenwidth));// �ٴ����û��ʵĿ��  
		paint.setTextSize((float) (0.0873*scrrenwidth));// �������ֵĴ�С  
		paint.setFakeBoldText(true);
		paint.setTypeface(face);
		paint.setColor(0XFFE11A2F);// ���û�����ɫ
		if(progress<10){
			canvas.drawText(progress + "%",(float) (0.12*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else if(progress>9&&progress<100){
			canvas.drawText(progress + "%",(float) (0.089*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else if(progress>=100){
			canvas.drawText(progress + "%",(float) (0.06*scrrenwidth), (float) (0.15625*scrrenwidth), paint);
		}else{}
		
		paint.reset();// ����������  
		paint.setColor(0XFFFA5C5B);// ���û�����ɫ  
		paint.setStrokeWidth((float) (0.00625*scrrenwidth));// �ٴ����û��ʵĿ��  
		paint.setTextSize((float) (0.05*scrrenwidth));
		
		if(getPersonnum()<10){
			canvas.drawText(getPersonnum()+"�˹���", (float) (0.09*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>9&&getPersonnum()<100){
			canvas.drawText(getPersonnum()+"�˹���", (float) (0.078*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>99&&getPersonnum()<1000){
			canvas.drawText(getPersonnum()+"�˹���", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>999&&getPersonnum()<10000){
			paint.setTextSize((float) (0.042*scrrenwidth));
			canvas.drawText(getPersonnum()+"�˹���", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else if(getPersonnum()>9999&&getPersonnum()<100000){
			paint.setTextSize((float) (0.038*scrrenwidth));
			canvas.drawText(getPersonnum()+"�˹���", (float) (0.066*scrrenwidth),(float) (0.25*scrrenwidth), paint);
		}else{}
		
	}  

	

}  
