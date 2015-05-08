package com.example.thumb;



import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class FouthPage extends Fragment implements View.OnClickListener{
	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn9,btn10,btn11,btn12,btn13;
	ImageView imageview1,imageview2,imageview3,imageview4,imageview6,imageview7;
	ImageView img1,img2,img3,img4;
	SharedPreferences sp;

	public static int scrrenWidth  ;  // 屏幕宽度
	public static int scrrenHeight ;  //屏幕高度
	RelativeLayout rlayout;
	int isexit=0;
	
	View view;
	/**
	 * ViewPager
	 */
	//private ViewPager viewPager;

	/**
	 * 装点点的ImageView数组
	 */
	//private ImageView[] tips;

	/**
	 * 装ImageView数组
	 */
	//private ImageView[] mImageViews;

	/**
	 * 图片资源id
	 */


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.more, container, false);
		
		sp = getActivity().getSharedPreferences("SP",0x0000);
		scrrenWidth=sp.getInt("ScrrenWidth", 1080);
		scrrenHeight=sp.getInt("ScrrenHeight", 1920);
		

		btn1=(Button) view.findViewById(R.id.button1);
		btn2=(Button)  view.findViewById(R.id.button2);
		btn3=(Button)  view.findViewById(R.id.button3);
		btn4=(Button)  view.findViewById(R.id.button4);
		btn5=(Button)  view.findViewById(R.id.button5);
		btn6=(Button)  view.findViewById(R.id.button6);
		btn7=(Button)  view.findViewById(R.id.button7);
		//btn8=(Button)  view.findViewById(R.id.button8);
		
		
		
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		//btn8.setOnClickListener(this);
	
		
		
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {  
		case R.id.button1: 

			startActivity(new Intent(getActivity(),Productadvanceactivity.class));
			//Toast.makeText(getApplicationContext(), "111", 1000).show();
			break;  
		case R.id.button2:  
			//Toast.makeText(getApplicationContext(), "2", 1000).show();
			showShare();
			break;  
		case R.id.button3:  

			startActivity(new Intent(getActivity(),Aboutthumbactivity.class));
			break;  
		case R.id.button4:  
			//Toast.makeText(getApplicationContext(), "4", 1000).show();
			//startActivity(new Intent(Moreactivty.this,Setactivity.class));
			startActivity(new Intent(getActivity(),Personalactivity.class));
			break;  
		case R.id.button5:  
			//Toast.makeText(getApplicationContext(), "5", 1000).show();
			if(sp.getBoolean("islogin", false)){
				startActivity(new Intent(getActivity(),Systemmessageactivity.class));
			}else{
				startActivity(new Intent(getActivity(),Loginactivity.class));
			}


			break;  
		case R.id.button6:  
			startActivity(new Intent(getActivity(),Helpcenteractivity.class));

			break;  

		case R.id.button7:  
			//Toast.makeText(getApplicationContext(), "5", 1000).show();
			//startActivity(new Intent(Moreactivty.this,com.way.locus.SetPasswordActivity.class));
			//startActivity(new Intent(Moreactivty.this,Savecenteractivity.class));
			startActivity(new Intent(getActivity(),Huodongactivity.class));

			break;  
		/*case R.id.button8:  
			startActivity(new Intent(getActivity(),Luntanactivity.class));

			break;  */

			/*	case R.id.button9:  
			

			break;  */
		default:  
			break;  
		}  

	}
	
	private void showShare() {
		ShareSDK.initSDK(getActivity());
		OnekeyShare oks = new OnekeyShare();
		//关闭sso授权
		oks.disableSSOWhenAuthorize(); 
		
		String sharestring="点击立即下载";
		
		String sharestringqq="注册天天赚即送1888体验金，更有20%加息收益等你抽，再不理财，你的钱包真的就瘦啦！点击立即下载:http://api.ddearn.com/Page/Help/Share.html";


		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.ic_launcher, "长汇天天赚APP");
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(sharestring);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://api.ddearn.com/Page/Help/Share.html");
		// text是分享文本，所有平台都需要这个字段
		oks.setText(sharestringqq);
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://api.ddearn.com/Page/Help/Share.html");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment(sharestring);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("长汇天天赚APP");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(sharestring);
		oks.setImageUrl("http://www.ddearn.com/images/logo.png"); 
		// 启动分享GUI
		oks.show(getActivity());
	}



}
