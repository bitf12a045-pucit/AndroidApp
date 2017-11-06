package com.image_app.main;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.image_app.models.Url;
import com.image_app.utility.Constants;
import com.image_app.utility.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Search {

	List<Url> imageUrlList = new ArrayList<Url>();

	public List doSearch(String keywords)throws Exception {
		final String url = Constants.URL;
		final String urlRequest = url + "?query=" + URLEncoder.encode(keywords);
		final String jsonObjet = HttpRequest.doRequest(urlRequest);

		JSONObject jsonObj =  new JSONObject(jsonObjet);

		JSONArray jSonObjectArray = jsonObj.getJSONArray("images");
//		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson= new Gson();
		for (int i=0;i<jSonObjectArray.length();i++) {

			String json =	jSonObjectArray.getString(i);
			Url imageurls =	gson.fromJson(json, Url.class);
			imageUrlList.add(imageurls);
		}

//		ObjectMapper objectMapper = new ObjectMapper();
//		Url imageurls =	objectMapper.readValue(jSonObjectArray, Url.class);
//		imageUrlList.add(imageurls);
		return imageUrlList;
	}
}

