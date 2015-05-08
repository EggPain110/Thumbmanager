package com.example.thumb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Thumbintroduce extends Activity{
	String string1,string2;
	TextView tv1,tv2,tv3;
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.thumbintroduce);
		/*tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);
		string1="天天赚为长汇财富旗下推出的一款专业理财APP，为用户提供更方便" +
				"、更快捷的理财方式，用户可第一时间掌握理财产品最新信息，" +
				"时刻监测产品收益情况。";
		string2="长汇财富管理有限公司是一家专业的财富管理机构，总部位于北京。" +
				"长汇财富专注于为高净值人士甄选及配置理财产品，提供全方位、" +
				"多层次、个性化的财富管理服务。“全程风控、全员风控”的“双重”风控模式。" +
				"即从项目接触、立项、产品设计成立、发行募集、投后资产管理阶段，" +
				"每个阶段都有不同的团队开展工作，实行全程、闭合式风控。已在北京、" +
				"上海、杭州、厦门、嘉兴、义乌、永康等地设立了多家分公司，已陆续为500位以上境内高净值人士提供专属的财富管理服务。";
		tv2.setText(ToDBC(string1));

		tv3.setText(ToDBC(string2));*/
		
		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		


	}
	/*public static String ToDBC(String input) {  
		char[] c = input.toCharArray();  
		for (int i = 0; i < c.length; i++) {  
			if (c[i] == 12288) {  
				c[i] = (char) 32;  
				continue;  
			}  
			if (c[i] > 65280 && c[i] < 65375)  
				c[i] = (char) (c[i] - 65248);  
		}  
		return new String(c);  
	}  */

}
