package com.example.thumb;


import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import android.app.Activity;
import android.os.Message;
import android.widget.TextView;


public class JARActivity extends BaseActivity {

	
    @Override
    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
    	
    	
      UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null, null,
    		  tn, mode);
    }

    @Override
    public void updateTextView(TextView tv) {
        String txt = "����ָ�ϣ�\n1:����sdkĿ¼�µ�UPPayAssistEx.jar��libsĿ¼��\n"
                + "2:������Ҫ����sdk/jar/data.bin����sdkPro/jar/data.bin�������̵�res/drawableĿ¼��\n"
                + "3:������Ҫ����sdk/jar/XXX/XXX.so����sdkPro/jar/XXX/XXX.so��libsĿ¼��\n"
                + "4:������Ҫ����sdk/jar/UPPayPluginEx.jar����sdkPro/jar/UPPayPluginExPro.jar�������̵�libsĿ¼��\n"
                + "5:��ȡtn��ͨ��UPPayAssistEx.startPayByJar(...)�������ÿؼ�";
        tv.setText(txt);
    }

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

	
}