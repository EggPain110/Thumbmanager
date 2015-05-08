package com.example.thumb;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



public class Setactivity extends Activity{
	ListView listView;
	String[] array;
	private int[] imgIdArray ;
	RelativeLayout relative1;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	ImageView img1,img2;
	Button btn1;
	AlertDialog dialog ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setactivity);
		sp =getSharedPreferences("SP",MODE_PRIVATE);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);


		array=new String[]{"清理缓存","检查版本","联系客服"};
		imgIdArray = new int[]{R.drawable.setactivity1, R.drawable.setactivity2, R.drawable.setactivity3};

		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		listView=(ListView) findViewById(R.id.listView1);

		listView.setAdapter(new myadpter());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), appList.get(position).packageName, 1000).show();
				switch (position) {
				case 0:
					final AlertDialog.Builder builder1 = new AlertDialog.Builder(Setactivity.this);

					View view1 = LayoutInflater.from(Setactivity.this).inflate(R.layout.cleandialog, null);

					Button btn1=(Button) view1.findViewById(R.id.button1);
					Button btn4=(Button) view1.findViewById(R.id.button2);
					btn1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});
					btn4.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});
					builder1.setView(view1);


					dialog = builder1.show();   

					break;
				case 1:
					
					final AlertDialog.Builder builder5 = new AlertDialog.Builder(Setactivity.this);

					View view5 = LayoutInflater.from(Setactivity.this).inflate(R.layout.checkversions, null);

					Button btn5=(Button) view5.findViewById(R.id.button1);
					
					btn5.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});
					
					builder5.setView(view5);


					dialog = builder5.show();   


					break;
				case 2:

					final AlertDialog.Builder builder3 = new AlertDialog.Builder(Setactivity.this);

					View view3 = LayoutInflater.from(Setactivity.this).inflate(R.layout.iscall, null);

					Button btn3=(Button) view3.findViewById(R.id.button1);
					btn3.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();   
						}
					});


					Button btn2=(Button) view3.findViewById(R.id.button2);
					btn2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"4008108688"));  
							startActivity(intent);
						}
					});
					builder3.setView(view3);

					dialog = builder3.show();   


					/* Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"4008108688"));  
					 startActivity(intent);  */

					break;

				default:
					break;
				}
			}
		});

	}

	class myadpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub

			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			View v=getLayoutInflater().inflate(R.layout.aboutthumbactivityitem, null);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth), (int) (scrrenHeight*0.09));
			v.setLayoutParams(lp);

			TextView tv1=(TextView) v.findViewById(R.id.textView1);
			img1=(ImageView) v.findViewById(R.id.imageView1);
			img2=(ImageView) v.findViewById(R.id.imageView2);
			tv1.setText(array[position]);
			img2.setImageResource(imgIdArray[position]);
			return v;
		}

	}
}

