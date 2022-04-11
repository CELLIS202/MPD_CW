package org.me.gcu.ellis_cameron_s1823540_mpd_cw;


/*
 * Data object that holds all of our information about a StackExchange Site.
 */
public class StackItem {

	private String title;
	private String description;
	private String link;
	private String georss;
	private String pubDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGeorss() {
		return georss;
	}

	public void setGeorss(String georss) {
		this.georss = georss;
	}

	public String getPubDate() { return pubDate; }

	public void setPubDate(String pubDate) { this.pubDate = pubDate; }

	@Override
	public String toString() {
		return "StackItem [title=" + title + ", description=" + description + ", link=" + link + ", georss=" + georss + ", pubDate=" + pubDate + "]";
	}
}

//*<!-- Cameron Ellis S1823540 -->