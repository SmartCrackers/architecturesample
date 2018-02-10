package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.ObjectUtils;

public class DataAccessObject {

	final static Logger LOGGER = Logger.getLogger(DataAccessObject.class);
	
	private URL obj = null;
	private HttpURLConnection con = null ;
	
	protected String sendPOST(String url, String data, Map<String, String> header){
		
		try{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			for (Map.Entry<String, String> entry : header.entrySet())
			{
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}

			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(data.getBytes());
			os.flush();
			os.close();

			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				LOGGER.info("DAO success for post request.");
				return response.toString();
			} else {
				LOGGER.error("App DAO Response = POST request not worked. cause :"+con.getResponseMessage());
			}
		}catch(Exception e){
			LOGGER.error("error while accessing api");
		}finally{
			if(!ObjectUtils.isEmpty(con))
				con.disconnect();
			LOGGER.error("cleanup");
		}
		
		return "500";
	}
	
	protected void sendPOST1(String url, String data, Map<String, String> header) throws IOException {
		try{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			for (Map.Entry<String, String> entry : header.entrySet())
			{
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
			
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(data.getBytes());
			os.flush();
			os.close();

			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

			} else {
				System.out.println("POST request not worked");
			}
		}catch(Exception e){
			LOGGER.error("error while accessing api");
		}finally{
			if(!ObjectUtils.isEmpty(con))
				con.disconnect();
			LOGGER.error("cleanup");
		}
	}
	
	protected String sendGET(String url,  Map<String, String> header){
		try{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Data", "dfg");
			
			for (Map.Entry<String, String> entry : header.entrySet())
			{
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
			
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				LOGGER.info("DAO success for get request.");
				return response.toString();
			} else {
				System.out.println("App DAO Response = GET request not worked");
				LOGGER.error("App DAO Response = GET request not worked");
			}
		}catch(Exception e){
			LOGGER.error("error while accessing api");
		}finally{
			if(!ObjectUtils.isEmpty(con))
				con.disconnect();
			LOGGER.error("cleanup");
		}
		return "500";
	}
}