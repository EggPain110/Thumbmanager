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
		string1="����׬Ϊ����Ƹ������Ƴ���һ��רҵ���APP��Ϊ�û��ṩ������" +
				"������ݵ���Ʒ�ʽ���û��ɵ�һʱ��������Ʋ�Ʒ������Ϣ��" +
				"ʱ�̼���Ʒ���������";
		string2="����Ƹ��������޹�˾��һ��רҵ�ĲƸ�����������ܲ�λ�ڱ�����" +
				"����Ƹ�רע��Ϊ�߾�ֵ��ʿ��ѡ��������Ʋ�Ʒ���ṩȫ��λ��" +
				"���Ρ����Ի��ĲƸ�������񡣡�ȫ�̷�ء�ȫԱ��ء��ġ�˫�ء����ģʽ��" +
				"������Ŀ�Ӵ��������Ʒ��Ƴ���������ļ����Ͷ���ʲ�����׶Σ�" +
				"ÿ���׶ζ��в�ͬ���Ŷӿ�չ������ʵ��ȫ�̡��պ�ʽ��ء����ڱ�����" +
				"�Ϻ������ݡ����š����ˡ����ڡ������ȵ������˶�ҷֹ�˾����½��Ϊ500λ���Ͼ��ڸ߾�ֵ��ʿ�ṩר���ĲƸ��������";
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
