package org.me.gcu.ellis_cameron_s1823540_mpd_cw;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



import java.io.FileNotFoundException;

public class MainActivity extends Activity  {

	private SitesAdapter mAdapter;
	private ListView sitesList;
	private Button prwbutton;
	private Button rwbutton;
	private Button cibutton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Items", "OnCreate()");
		setContentView(R.layout.activity_main);

		prwbutton = (Button) findViewById(R.id.prwbutton);
		rwbutton = (Button) findViewById(R.id.prwbutton);
		cibutton = (Button) findViewById(R.id.prwbutton);


		sitesList = (ListView) findViewById(R.id.sitesList);

		sitesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				String url = mAdapter.getItem(pos).getLink();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}

		});
		prwbutton =(Button)findViewById(R.id.prwbutton);
		prwbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v){
				mAdapter = new SitesAdapter(MainActivity.this, -1, SitesXmlPullParser.getStackItemsFromFile(MainActivity.this, "plannedroadworks.xml"));
				sitesList.setAdapter(mAdapter);
				Log.i("Items", "adapter size = " + mAdapter.getCount());
			}
		});
		rwbutton =(Button)findViewById(R.id.rwbutton);
		rwbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v){
				mAdapter = new SitesAdapter(MainActivity.this, -1, SitesXmlPullParser.getStackItemsFromFile(MainActivity.this, "roadworks.xml"));
				sitesList.setAdapter(mAdapter);
				Log.i("Items", "adapter size = " + mAdapter.getCount());
			}
		});
		cibutton =(Button)findViewById(R.id.cibutton);
		cibutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v){
				mAdapter = new SitesAdapter(MainActivity.this, -1, SitesXmlPullParser.getStackItemsFromFile(MainActivity.this, "incidents.xml"));
				sitesList.setAdapter(mAdapter);
				Log.i("Items", "adapter size = " + mAdapter.getCount());
			}
		});

	}


	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			//Download the file
			try {
				Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/plannedroadworks.aspx", openFileOutput("plannedroadworks.xml", Context.MODE_PRIVATE));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.d("Downloads", "Download failed");
			}
			try {
				Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/roadworks.aspx", openFileOutput("roadworks.xml", Context.MODE_PRIVATE));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.d("Downloads", "Download failed");
			}
			try {
				Downloader.DownloadFromUrl("https://trafficscotland.org/rss/feeds/currentincidents.aspx", openFileOutput("incidents.xml", Context.MODE_PRIVATE));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.d("Downloads", "Download failed");
			}

			return null;
		}


	}
}
//*<!-- Cameron Ellis S1823540 -->
