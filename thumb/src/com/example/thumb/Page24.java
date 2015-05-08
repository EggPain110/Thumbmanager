package com.example.thumb;

import com.way.locus.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Page24 extends Fragment{
	//private Button btn1,btn2;
	View view;
	Button btn1;
	String detailjson;
	SharedPreferences sp;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.page24, container, false);
		btn1=(Button) view.findViewById(R.id.button1);
		Intent intent=getActivity().getIntent();
		detailjson=intent.getStringExtra("detailjson");
		sp=getActivity().getSharedPreferences("SP", 0);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(sp.getBoolean("iswatch", false)){
					getActivity().finish();
				}else{
					Intent intent1=new Intent(getActivity(),LoginActivity.class);
					intent1.putExtra("detailjson", detailjson);
					startActivity(intent1);
					Editor editor=sp.edit();
					editor.putBoolean("iswatch", true);
					editor.commit();
					getActivity().finish();
				}
				
			}
		});

		return view;

	}

}