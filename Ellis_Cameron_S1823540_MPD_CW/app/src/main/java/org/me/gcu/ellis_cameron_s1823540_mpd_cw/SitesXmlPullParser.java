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

		// List of StackItems that we will return
		List<StackItem> stackItems;
		stackItems = new ArrayList<StackItem>();

		// temp holder for current StackSite while parsing
		StackItem curStackItem = new StackItem();
		// temp holder for current text value while parsing
		String curText = "";

		try {
			// Get our factory and PullParser
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			// Open up InputStream and Reader of our file.
			FileInputStream fis = ctx.openFileInput(file );
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			// point the parser to our file.
			xpp.setInput(reader);

			// get initial eventType
			int eventType = xpp.getEventType();

			// Loop through pull events until we reach END_DOCUMENT
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Get the current tag
				String tagname = xpp.getName();

				// React to different event types appropriately
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(KEY_ITEM)) {
						// If we are starting a new <site> block we need
						//a new StackSite object to represent it
						curStackItem = new StackItem();
					}
					break;

				case XmlPullParser.TEXT:
					//grab the current text so we can use it in END_TAG event
					curText = xpp.getText();
					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase(KEY_ITEM)) {
						// if </site> then we are done with current Site
						// add it to the list.
						stackItems.add(curStackItem);
					} else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
						// if </name> use setName() on curSite
						curStackItem.setTitle(curText);
					} else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
						// if </link> use setLink() on curSite
						curStackItem.setDescription(curText);
					} else if (tagname.equalsIgnoreCase(KEY_LINK)) {
						// if </about> use setAbout() on curSite
						curStackItem.setLink(curText);
					} else if (tagname.equalsIgnoreCase(KEY_GEORSS)) {
						// if </link> use setLink() on curSite
						curStackItem.setGeorss(curText);
					} else if (tagname.equalsIgnoreCase(KEY_PUBDATE)) {
						// if </about> use setAbout() on curSite
						curStackItem.setPubDate(curText);
					}

					break;

				default:
					break;
				}
				//move on to next iteration
				eventType = xpp.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return the populated list.
		return stackItems;
	}
}
//*<!-- Cameron Ellis S1823540 -->