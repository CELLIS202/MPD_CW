package org.me.gcu.ellis_cameron_s1823540_mpd_cw;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


/*
 * Custom Adapter class that is responsible for holding the list of sites after they
 * get parsed out of XML and building row views to display them on the screen.
 */
public class SitesAdapter extends ArrayAdapter<StackItem> {


	public SitesAdapter(Context ctx, int textViewResourceId, List<StackItem> items) {
		super(ctx, textViewResourceId, items);
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		RelativeLayout row = (RelativeLayout)convertView;
		Log.i("StackItems", "getView pos = " + pos);
		if(null == row){
			//No recycled View, we have to inflate one.
			LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = (RelativeLayout)inflater.inflate(R.layout.row_site, null);
		}
		
		//Get our View References
		TextView nameTxt = (TextView)row.findViewById(R.id.nameTxt);
		TextView aboutTxt = (TextView)row.findViewById(R.id.aboutTxt);
		TextView linkTxt = (TextView)row.findViewById(R.id.linkTxt);
		TextView georssTxt = (TextView)row.findViewById(R.id.georssTxt);
		TextView pubDateTxt = (TextView)row.findViewById(R.id.pubDateTxt);


		
		nameTxt.setText(getItem(pos).getTitle());
		aboutTxt.setText(getItem(pos).getDescription());
		linkTxt.setText(getItem(pos).getLink());
		georssTxt.setText(getItem(pos).getGeorss());
		pubDateTxt.setText(getItem(pos).getPubDate());
		
		
		return row;
				
				
	}

}
//*<!-- Cameron Ellis S1823540 -->
