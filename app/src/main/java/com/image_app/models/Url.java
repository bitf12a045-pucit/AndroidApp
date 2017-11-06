package com.image_app.models;

public class Url {

	String id;
	String url;

	String large_url;

	String copyright;

	String site;

	String source_id;



	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSource_id() {
		return source_id;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String get_largeUrl() {
		return large_url;
	}

	public void set_largeUrl(String largeUrl) {
		this.large_url = largeUrl;
	}
}
