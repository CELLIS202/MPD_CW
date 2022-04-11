package org.me.gcu.ellis_cameron_s1823540_mpd_cw;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {

	private static String myTag = "StackItems";
	
	static final int POST_PROGRESS = 1;

	public static void DownloadFromUrl(String URL, FileOutputStream fos) {  //this is the downloader method
		try {

			URL url = new URL(URL); //URL of the file
			

			long startTime = System.currentTimeMillis();
			Log.d(myTag, "download begining");
			
			URLConnection ucon = url.openConnection();

			Log.i(myTag, "Opened Connection");

			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Log.i(myTag, "Got InputStream and BufferedInputStream");
			

			
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			Log.i(myTag, "Got FileOutputStream and BufferedOutputStream");

			byte data[] = new byte[1024];
            //long total = 0;
            int count;
            //loop and read the current chunk
            while ((count = bis.read(data)) != -1) {            	
            	//keep track of size for progress.
                //total += count;
            	
            	//write this chunk
                bos.write(data, 0, count);
            }
			bos.flush();
			bos.close();
			
			Log.d(myTag, "download ready in "
					+ ((System.currentTimeMillis() - startTime))
					+ " milisec");
		} catch (IOException e) {
			Log.d(myTag, "Error: " + e);
		}
	}
}
//*<!-- Cameron Ellis S1823540 -->