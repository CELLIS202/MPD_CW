package org.me.gcu.ellis_cameron_s1823540_mpd_cw;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SitesXmlPullParser {

	static final String KEY_ITEM = "item";
	static final String KEY_TITLE = "title";
	static final String KEY_DESCRIPTION = "description";
	static final String KEY_LINK = "link";
	static final String KEY_GEORSS = "georss";
	static final String KEY_PUBDATE = "pubdate";

	public static List<StackItem> getStackItemsFromFile(Context ctx, String file) {

		List<StackItem> stackItems;
		stackItems = new ArrayList<StackItem>();

		StackItem curStackItem = new StackItem();
		String curText = "";

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			FileInputStream fis = ctx.openFileInput(file );
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			xpp.setInput(reader);

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = xpp.getName();

				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(KEY_ITEM)) {
						curStackItem = new StackItem();
					}
					break;

				case XmlPullParser.TEXT:
					curText = xpp.getText();
					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase(KEY_ITEM)) {
						stackItems.add(curStackItem);
					} else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
						curStackItem.setTitle(curText);
					} else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
						curStackItem.setDescription(curText);
					} else if (tagname.equalsIgnoreCase(KEY_LINK)) {
						curStackItem.setLink(curText);
					} else if (tagname.equalsIgnoreCase(KEY_GEORSS)) {
						curStackItem.setGeorss(curText);
					} else if (tagname.equalsIgnoreCase(KEY_PUBDATE)) {
						curStackItem.setPubDate(curText);
					}

					break;

				default:
					break;
				}
				eventType = xpp.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stackItems;
	}
}
//*<!-- Cameron Ellis S1823540 -->