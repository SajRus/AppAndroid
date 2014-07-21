package com.example.redmoon;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherHttpClient {

	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
	private static String IMG_URL = "http://www.openweathermap.org/img/w/";
	
	public String getWeatherData(String url_extension) {
		HttpURLConnection con = null ;
		InputStream is = null;
		
		try {
			con = (HttpURLConnection) ( new URL(BASE_URL + url_extension)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			int conCode = con.getResponseCode();
			// Let's read the response
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (  (line = br.readLine()) != null )
				buffer.append(line + "\r\n");
			
			is.close();
			con.disconnect();
			return buffer.toString();
	    }
		catch(Throwable  t) {
			t.printStackTrace();
		}
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}

		return null;
				
	}
	
	public byte[] getImage(String code) {
		HttpURLConnection com = null ;
		InputStream is = null;
		
		try {
			com = (HttpURLConnection) ( new URL(IMG_URL + code )).openConnection();
			com.setRequestMethod("GET");
			com.setDoInput(true);
			//com.setDoOutput(true);
			com.connect();
			
			// Let's read the response
			String method = com.getRequestMethod();
			int conCode = com.getResponseCode();

			
			is = com.getInputStream();
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while ( is.read(buffer) != -1)
				baos.write(buffer);
			
			return baos.toByteArray();
	    }
		catch(ProtocolException ex){
			ex.printStackTrace();
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { com.disconnect(); } catch(Throwable t) {}
		}
		
		return null;
		
	}

}
