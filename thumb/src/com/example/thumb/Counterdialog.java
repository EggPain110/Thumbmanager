package com.example.thumb;



import java.lang.reflect.Field;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Counterdialog {
	EditText et1;
	public Dialog buildDialog(final Context context,final int x,final double y) {
		/* Dialog alertDialog = new AlertDialog.Builder(context).
			     setTitle("����Ӧ����").


			     setPositiveButton("���", new DialogInterface.OnClickListener() {

			      @Override
			      public void onClick(DialogInterface dialog, int which) {
			       // TODO Auto-generated method stub

			      }
			     }).
			     setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			      @Override
			      public void onClick(DialogInterface dialog, int which) {
			       // TODO Auto-generated method stub
			      }
			     }).
			     setNeutralButton("ɾ��", new DialogInterface.OnClickListener() {

			      @Override
			      public void onClick(DialogInterface dialog, int which) {
			       // TODO Auto-generated method stub

			      }
			     }).
			     create();*/

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		/*  builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("�������û���������");*/
		//    ͨ��LayoutInflater������һ��xml�Ĳ����ļ���Ϊһ��View����
		View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
		//    ���������Լ�����Ĳ����ļ���Ϊ�������Content
		builder.setView(view);

		final EditText username = (EditText)view.findViewById(R.id.editText1);
		username.setHint(x+"��");
		username.setKeyListener(null);

		final EditText password = (EditText)view.findViewById(R.id.editText2);

		final TextView tv5=(TextView) view.findViewById(R.id.textView5);
		builder.setPositiveButton("����", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
				
				try
				{
					Field field = dialog.getClass()
							.getSuperclass().getDeclaredField(
									"mShowing");
					field.setAccessible(true);
					// ��mShowing������Ϊfalse����ʾ�Ի����ѹر�
					field.set(dialog, false);
					dialog.dismiss();

				}
				catch (Exception e)
				{

				}
				
				
				if("".equals(password.getText().toString().trim())){
					Toast.makeText(context, "��������", 1000).show();
					return;
				}

				
				String a = username.getText().toString().trim();
				String b = password.getText().toString().trim();
				tv5.setText(String.format("%.2f", (x/36500.00)*y*Integer.parseInt(b)));
				



			} 
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				try
				{
					Field field = dialog.getClass()
							.getSuperclass().getDeclaredField(
									"mShowing");
					field.setAccessible(true);
					// ��mShowing������Ϊfalse����ʾ�Ի����ѹر�
					field.set(dialog, true);
					dialog.dismiss();

				}
				catch (Exception e)
				{

				}
			}
		});
		return builder.show();



	}
}
