package com.example.thumb;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.thumb.R.drawable;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Page2 extends Fragment{
	//private Button btn1,btn2;
	View view;
	ListView listView;
	Typeface face ;
	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	SharedPreferences sp;
	String res1;
	JSONArray myresult;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.page1, container, false);
		listView=(ListView) view.findViewById(R.id.listView1);

		sp = getActivity().getSharedPreferences("SP",0);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);
		read();
		/*scrrenWidth=1082;
		scrrenHeight=1920;*/
		/*btn1=(Button) view.findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "123213", 1000).show();
			}
		});*/
		listView.setDivider(null);//无分割线
		face = Typeface.createFromAsset (getActivity().getAssets() , "fonts/fangz.ttf" );
		listView.setAdapter(new myadpter());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				MyAsyncTask myAsyncTask=new MyAsyncTask();	
				if(sp.getBoolean("islogin", false)){
					if(myAsyncTask.isNetworkConnected(getActivity().getApplicationContext())){
						try {
							JSONObject jobject2=new JSONObject(myresult.getJSONObject(position).toString());
							Intent intent=new Intent(getActivity(),Productdetailactivity.class);
							intent.putExtra("productid", jobject2.getString("Ipro_id"));
							intent.putExtra("productname", jobject2.getString("Ipro_name"));
							intent.putExtra("buynum","30天购买人数    "+jobject2.getString("purchaseNum"));
							intent.putExtra("day", "期限（天）"+jobject2.getString("dayDiff"));
							intent.putExtra("shouyi", String.format("%.2f", jobject2.getDouble("pctInterest"))+"%");
							
							startActivity(intent);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}else{
						Toast.makeText(getActivity().getApplicationContext(), "请连接网络", 1000).show();
					}
				}else{
					startActivity(new Intent(getActivity(),Loginactivity.class));
				}
			

			}
		});

		return view;

	}


	class myadpter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myresult.length();    //list.lengh
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

			View v=getLayoutInflater(null).inflate(R.layout.item, null);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int) (scrrenWidth*0.9583), (int) (scrrenWidth*0.319));
			v.setLayoutParams(lp);						//设置item尺寸

			//解析json

			if(position==0){
				v.setBackgroundResource(R.drawable.itemfirst);
			}else{}
			if(listView.getCount()-1==position){
				v.setBackgroundResource(R.drawable.itemthird);
			}else{}

			json(v,position);	

			return v;
		}

	}

	private void read() {
		// TODO Auto-generated method stub

		String road=getActivity().getFilesDir().getAbsolutePath()+"longproduct"+".txt";

		try {
			File file = new File(road);    

			FileInputStream fis = new FileInputStream(file);    

			int length = fis.available();   

			byte [] buffer = new byte[length];   
			fis.read(buffer);       

			res1 = EncodingUtils.getString(buffer, "UTF-8");   

			fis.close();  

			try {
				JSONObject jobject=new JSONObject(res1);				//json拆包；
				myresult=jobject.getJSONArray("DataObj");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}

	public void json(View v,int position){

		try {

			final JSONObject jobject1=new JSONObject(myresult.getJSONObject(position).toString());

			TextView tv1=(TextView) v.findViewById(R.id.textView1);				//预期年化收益
			TextView tv2=(TextView) v.findViewById(R.id.textView2);				//产品名称
			TextView tv6=(TextView) v.findViewById(R.id.textView6);				//理财期限
			TextView tv7=(TextView) v.findViewById(R.id.textView7);				//起投金额
			TextView tv8=(TextView) v.findViewById(R.id.textView8);				//融资金额
			TextView tv9=(TextView) v.findViewById(R.id.textView9);				//融资进度
			TextView tv10=(TextView) v.findViewById(R.id.textView10);			//购买人数
			ImageView img1=(ImageView) v.findViewById(R.id.imageView1);
			ProgressBar pro=(ProgressBar) v.findViewById(R.id.progressBar1);

			String str4=String.format("%.2f", jobject1.getDouble("pctInterest"))+"%";
			SpannableStringBuilder style3=new SpannableStringBuilder(str4); 
			style3.setSpan(new RelativeSizeSpan((float) 0.5),str4.length()-1, str4.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

			tv1.setText(style3);												// 预期年化收益
			tv1.setTypeface(face);



			if(jobject1.getString("Ipro_name").length()>8){

				tv2.setText(jobject1.getString("Ipro_name").substring(0, 8));													// 产品名称
			}else{
				tv2.setText(jobject1.getString("Ipro_name"));					// 产品名称
			}

			tv6.setText(jobject1.getString("dayDiff")+"天");						// 期限
			tv7.setText(jobject1.getString("Ipro_amount"));						// 起购金额
			tv8.setText(jobject1.getString("Ipro_Ashare"));						// 融资金额
			
			String str1=String.format("%.2f", jobject1.getDouble("pctPurchased")*100)+"%";
			tv9.setText("");			// 融资进度
			tv10.setText(jobject1.getString("purchaseNum")+"人");				// 购买人数
			/*LayoutParams linearParams2 =(LayoutParams) img2.getLayoutParams(); 
			linearParams2.height = (int) (scrrenHeight*0.002);					// 控件的高强制设成20  
			if(jobject1.getDouble("pctPurchased")>1){
				linearParams2.width = (int) (scrrenWidth*0.6);	
			}else{
				linearParams2.width = (int) (scrrenWidth*0.6*jobject1.getDouble("pctPurchased"));	
			}			
			img2.setLayoutParams(linearParams2);								//使设置好的布局参数应用到控件</pre>  
			//img2.setLayoutParams(new LayoutParams(10,10));
*/			
			
			pro.setMax(100);
			pro.setProgress((int) (jobject1.getDouble("pctPurchased")*100));
			if(jobject1.getString("Ipro_reco").equals("01")){
				img1.setVisibility(View.VISIBLE);
			}else{
				img1.setVisibility(View.INVISIBLE);
			}
			
			if(jobject1.getString("Ipro_state").equals("02")){
				img1.setVisibility(View.VISIBLE);
				img1.setBackgroundResource(drawable.shouqing);
			}
		

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
