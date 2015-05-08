package com.example.thumb;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class Helpcenteractivity extends Activity{
	ListView listView;
	String[] array;
	private int[] imgIdArray ;
	RelativeLayout relative1;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	ImageView img1,img2;
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.helpcenteractivity);
		sp =getSharedPreferences("SP",MODE_PRIVATE);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);
		
		
		array=new String[]{"常见问题","账户操作","安全保障","天天币使用规则"};
		imgIdArray = new int[]{R.drawable.helpcenteractivity1, R.drawable.helpcenteractivity2, R.drawable.helpcenteractivity3, R.drawable.helpcenteractivity4};
		
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
				Intent intent=new Intent();
				intent.putExtra("extra", position);
				intent.setClass(Helpcenteractivity.this, Normalquestion.class);
				startActivity(intent);
			}
		});
		
	}
	
	class myadpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
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
