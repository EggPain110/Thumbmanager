package com.example.thumb;

import com.igexin.sdk.PushConsts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;

public class PushDemoReceiver extends BroadcastReceiver{
	SharedPreferences sp;
	
    @Override
    public void onReceive(Context context, Intent intent) {
    	
        Bundle bundle = intent.getExtras();
       // Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));
        
        sp=context.getSharedPreferences("SP", 0);
        
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_MSG_DATA:
                // 获取透传（payload）数据
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null)
                {
                    String data = new String(payload);
                   // Log.d("GetuiSdkDemo", "Got Payload:" + data);
                    // TODO:接收处理透传（payload）数据
                    Uri uri = Uri.parse(data);  
                    Intent it = new Intent(Intent.ACTION_VIEW, uri); 
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                    
                }
                break;
                
            case PushConsts.GET_CLIENTID:
            	
                // 获取ClientID(CID)
                String cid = bundle.getString("clientid");
              //  Log.d("GetuiSdkDemo", "Got ClientID:" + cid);
                Editor editor=sp.edit();
                editor.putString("ClientID", cid);
                editor.commit();
                
            //添加其他case
            //......... 
            default:
                break;
        }
    }

}

