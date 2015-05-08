package com.example.thumb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MyProgressdialog extends Dialog{
	
	Context context;
	
	public MyProgressdialog(Context context, int theme) {
		super(context, R.style.loading_dialog);
		 this.context = context;

		// TODO Auto-generated constructor stub
	}

	public MyProgressdialog(Context context) {

		super(context, R.style.loading_dialog);
		this.context=context;
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.myprogressdialog);
		this.setCancelable(false);
		

		View v1 = findViewById(R.id.framelayout1);
		AnimationDrawable ad = (AnimationDrawable) v1.getBackground();
		ad.start();
	}




	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
