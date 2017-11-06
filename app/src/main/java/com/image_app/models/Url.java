package com.image_app.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"url",
		"largeUrl"
})
public class Url {

	@JsonProperty("id")
	String id;

	@JsonProperty("url")
	String url;

	@JsonProperty("largeUrl")
	String largeUrl;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("largeUrl")
	public String getLargeUrl() {
		return largeUrl;
	}

	@JsonProperty("largeUrl")
	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}
}
