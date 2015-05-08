package com.example.thumb;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Page5 extends Fragment{
	View view;
	Button btn1,btn2;
	Typeface face;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view=inflater.inflate(R.layout.page5, container, false);
		face= Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf");  
		btn1=(Button) view.findViewById(R.id.Button1);
		btn1.setTypeface (face);
		btn1.setTextColor(Color.WHITE);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "hello", 1000).show();
				startActivity(new Intent(getActivity(),Tendencyactivity.class));
				//startActivity(new Intent(getActivity(),Buyactivity1.class));
			}
		});
		
		return view;
	}

}
