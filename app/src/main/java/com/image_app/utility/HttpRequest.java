package com.image_app.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	
	public static synchronized String doRequest(String urlRequest) throws Exception
    {
		String jsonResponse = "";

		HttpURLConnection urlConnection= null;
		URL url = new URL(urlRequest);
		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod("GET");
		jsonResponse = convertStreamToString(urlConnection.getInputStream());

    	return jsonResponse;
    }
	
	private static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line + "\n");
	    }
	    is.close();
	    return sb.toString();
	  }
	
}	
	
