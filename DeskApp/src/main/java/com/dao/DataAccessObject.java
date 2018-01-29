package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

public class DataAccessObject {

	final static Logger logger = Logger.getLogger(DataAccessObject.class);
	
	private URL obj = null;
	private HttpURLConnection con = null ;
	
	protected String sendPOST(String url, String data, Map<String, String> header) throws IOException {
		obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		//System.out.println("API RESPONSE POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			//System.out.println("App DAO Response = "+response.toString());
			logger.info("DAO success for post request.");
			return response.toString();
		} else {
			System.out.println("App DAO Response = POST request not worked");
			logger.error("App DAO Response = POST request not worked");
		}
		return "500";
	}
	
	protected void sendPOST1(String url, String data, Map<String, String> header) throws IOException {
		obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		for (Map.Entry<String, String> entry : header.entrySet())
		{
			con.setRequestProperty(entry.getKey(), entry.getValue());
		}
		
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}
	
	protected String sendGET(String url,  Map<String, String> header) throws IOException {
		obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Data", "dfg");
		
		for (Map.Entry<String, String> entry : header.entrySet())
		{
			con.setRequestProperty(entry.getKey(), entry.getValue());
		}
		
		
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println("App DAO Response= "+response.toString());
			logger.info("DAO success for get request.");
			return response.toString();
		} else {
			System.out.println("App DAO Response = GET request not worked");
			logger.error("App DAO Response = GET request not worked");
		}
		return null;
	}
}