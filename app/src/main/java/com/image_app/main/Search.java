package com.image_app.main;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.image_app.models.Url;
import com.image_app.utility.Constants;
import com.image_app.utility.HttpRequest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Search {

	List<Url> imageUrlList = new ArrayList<Url>();

	public List doSearch(String keywords)throws Exception {
		final String url = Constants.URL;
		final String urlRequest = url + "?querry=" + URLEncoder.encode(keywords);
		final String jsonObjet = HttpRequest.doRequest(urlRequest);

		ObjectMapper objectMapper = new ObjectMapper();
		Url imageurls =	objectMapper.readValue(jsonObjet, Url.class);
		imageUrlList.add(imageurls);
		return imageUrlList;
	}
}

