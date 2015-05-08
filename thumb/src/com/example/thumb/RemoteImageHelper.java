package com.example.thumb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class RemoteImageHelper {

	Handler handler;
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	final String TAG = "RemoteImageHelper";

	private static final Map<String, Drawable> cache = new HashMap<String, Drawable>();

	public void addDrawableIntoCache(final String url) {

		new Thread(){
			public void run() {

				Drawable drawable = null;
				InputStream is = null;

				if(cache.containsKey(url)){
					return;
				}

				try {
					while (drawable == null) {
						is = download(url);
						drawable = Drawable.createFromStream(is, "src");
					}
					if (drawable != null) {
						synchronized (cache) {
							cache.put(url, drawable);
						}
					}
				} catch (Exception e) {
					//Log.e(this.getClass().getSimpleName(), "Image download failed", e);
					// Show "download fail" image
					// drawable = imageView.getResources().getDrawable(R.drawable.icon);
				}

				try {
					if(is != null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void loadImage(final ImageView imageView, final String urlString) {
		loadImage(imageView, urlString, true);
	}

	public void loadImage(final ImageView imageView, final String urlString, boolean useCache) {

		synchronized(cache){
			if (useCache && cache.containsKey(urlString)) {
				imageView.setImageDrawable(cache.get(urlString));
				return;
			}			
		}

		// You may want to show a "Loading" image here
		//imageView.setImageResource(R.drawable.icon);

		//Log.d(this.getClass().getSimpleName(), "Image url:" + urlString);

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				//				imageView.setImageDrawable((Drawable) message.obj); 	// way 1	error

				//				if(mAdapter != null)									// way 2	too slow
				//					mAdapter.notifyDataSetChanged();

				synchronized(cache){									// way 3 	no error and fast
					if (cache.containsKey(imageView.getTag().toString())) {
						imageView.setImageDrawable(cache.get(imageView.getTag().toString()));
						return;
					}			
				}


			}
		};


		Runnable runnable = new Runnable() {
			public void run() {
				Drawable drawable = null;
				InputStream is = null;
				try {
					while(drawable == null){
						is = download(urlString);
						drawable = Drawable.createFromStream(is, "src");
					}

					if (drawable != null) {
						synchronized(cache){
							cache.put(urlString, drawable);
						}
					}
				} catch (Exception e) {
					//Log.e(this.getClass().getSimpleName(),
						//	"Image download failed", e);
					// Show "download fail" image
					//drawable = imageView.getResources().getDrawable(R.drawable.icon);
				}


				// Notify UI thread to show this image using Handler
				if(drawable == null){
					return;
				}
				Message msg = handler.obtainMessage(1, drawable);
				handler.sendMessage(msg);


				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(runnable).start();


	}


	/**
	 * only use at update function
	 * @param pkgName
	 * @param url
	 * @param savePath
	 */





	public void downloadImage(final String url , final String savePath){


		if(cache.containsKey(url)){

			Drawable drawable = cache.get(url);
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			Bitmap bitmap = bitmapDrawable.getBitmap();

			try {
				OutputStream os1 = new FileOutputStream(savePath);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, os1);
				os1.close();
				return;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 

		}


		new Thread(){
			public void run() {
				try {
					URL urlImage = new URL(url);

					InputStream is = urlImage.openStream();

					new File(savePath).getParentFile().mkdirs();

					String tmpPath = savePath + ".tmp";
					OutputStream os = new FileOutputStream(tmpPath);

					byte[] buff = new byte[1024];
					int hasRead = 0;

					while((hasRead = is.read(buff)) > 0){
						os.write(buff, 0, hasRead);
					}

					new File(tmpPath).renameTo(new File(savePath));


					is.close();
					os.close();

					Message msg = handler.obtainMessage(0x01);  
					msg.obj = "true";  
					msg.sendToTarget();


				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}.start();

	}

	/**
	 * Download image from given url. Make sure you have
	 * "android.permission.INTERNET" permission set in AndroidManifest.xml.
	 * 
	 * @param urlString
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private InputStream download(String urlString)
			throws MalformedURLException, IOException {
		InputStream inputStream = (InputStream) new URL(urlString).getContent();
		return inputStream;
	}

}
