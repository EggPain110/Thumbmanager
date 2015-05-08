package com.example.thumb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

public class FileRWbyYY {
	public static boolean write(String str,int x,Context context){					// 写文件
		String road;

		try {
			if(x==0){
				road=context.getFilesDir().getAbsolutePath()+"product"+".txt";
			}else{
				road=context.getFilesDir().getAbsolutePath()+"longproduct"+".txt";
			}

			File saveFile=new File(road);
			FileOutputStream outStream = new FileOutputStream(saveFile);
			outStream.write(str.getBytes());
			outStream.close();
			return true;
			//Log.i("tag",Environment.getExternalStorageDirectory()+"");//获取sd卡相对路径
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	private String read(Context context) {
		// TODO Auto-generated method stub
		String res1 = null;
		String road=context.getFilesDir().getAbsolutePath()+"product"+".txt";

		try {
			File file = new File(road);    
			FileInputStream fis = new FileInputStream(file);    
			int length = fis.available();   
			byte [] buffer = new byte[length];   
			fis.read(buffer);       
			res1 = EncodingUtils.getString(buffer, "UTF-8");   
			fis.close();       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res1.toString();  
	}
}
