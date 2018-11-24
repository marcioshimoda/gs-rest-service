package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class RestService {
	final static String WEBSERVICE_URL = "http://localhost:8080";
	
	public static String getQuery(String[] market, String[] stack) {
		String query = "";
		
		if (market != null) {
    		for (String mkt: market) {
    			if (query != "") {
    				query += "&market=" + mkt;
    			} else {
    				query = "market=" + mkt;
    			}
    		}
    	}
		
    	if (stack != null) {
    		for (String stk: stack) {
    			if (query != "") {
    				query += "&stack=" + stk;
    			} else {
    				query = "stack=" + stk;
    			}
    		}
    	}
		
		return query;
	}
	
	public static String call(String endpoint, String[] market, String[] stack) {
		String json = "";
		try {
			String query = getQuery(market, stack);
			if (query != "") {
				query = "?" + query; 
			}
			
			URL url = new URL(WEBSERVICE_URL + endpoint + query);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
		
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
		
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String buffer;
			while ((buffer = reader.readLine()) != null) {
				json += buffer;
			}
		
			conn.disconnect();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
