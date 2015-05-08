package com.example.thumb;



import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Aboutthumbactivity extends Activity{
	ListView listView;
	String[] array,arrayname;
	private int[] imgIdArray ;
	RelativeLayout relative1;
	SharedPreferences sp;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	ImageView img1,img2;
	Button btn1;
	Intent intent;
	ClipboardManager  clipboard ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aboutthumbactivity);

		sp =getSharedPreferences("SP",MODE_PRIVATE);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);

		clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

		array=new String[]{"你提我改","天天赚简介","关注微博","关注微信","启动页面"};

		arrayname=new String[]{"你提我改","天天赚简介","天天赚理财","长汇天天赚","启动页面"};
		imgIdArray = new int[]{R.drawable.aboutactivity4, R.drawable.aboutactivity3, R.drawable.aboutactivity2, R.drawable.aboutactivity5, R.drawable.aboutactivity1};

		btn1=(Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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
				switch (position) {
				case 0:
					if(sp.getBoolean("islogin", false)){
						startActivity(new Intent(Aboutthumbactivity.this,Nitiwogai.class));

					}else{
						startActivity(new Intent(Aboutthumbactivity.this,Loginactivity.class));
					}


					break;
				case 1:
					startActivity(new Intent(Aboutthumbactivity.this,Thumbintroduce.class));
					break;
				case 2:
					ClipData textCd = ClipData.newPlainText("kkk", "天天赚理财");
					clipboard.setPrimaryClip(textCd);
					Toast.makeText(getApplicationContext(), "微博号已复制,微博搜索马上关注", 1000).show();
					break;
				case 3:
					ClipData textCd1 = ClipData.newPlainText("kkk", "长汇天天赚");
					clipboard.setPrimaryClip(textCd1);
					Toast.makeText(getApplicationContext(), "公众号已复制,微信搜索马上关注", 1000).show();
					break;
				case 4:
					startActivity(new Intent(Aboutthumbactivity.this,Teachactivity.class));

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
			return 5;
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

			if(position==2||position==3){
				TextView tv2=(TextView) v.findViewById(R.id.textView2);
				tv2.setText(arrayname[position]);
			}else{}
			return v;
		}

	}
}
