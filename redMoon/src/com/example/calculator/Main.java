package com.example.calculator;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * Classe di enter point per il programma
 * 1-crea un oggetto data, acquisisce i dati e inizializza l'ogetto.
 * 2- 
 */
public class Main {
	public static void main(String[] args){ 

		/**
		 * Observer constants
		 * 
		 * [0]Latitude
		 * [1]Longitude
		 * [2]Altitude
		 * [3]Time Zone
		 * [4]index, start from 0
		 * [5]Choose eclipse types for search[all, partial & total, total]
		 * 
		 */
		final DataEntry data = new DataEntry();

		Scanner scanIn = new Scanner(System.in);
		JSONParser parser = new JSONParser();
		JSONObject rec = null;
		LocalEventData startTime, midTime, endTime = null;
		DataCircumstances dataCirc = null;
		JSONArray dataf = null , str = null, datas = null;
		Iterator<String> iterator = null;
		Iterator<Double> iteratorDouble = null, iteratorDouble1 = null;
		Calendar dates = Calendar.getInstance(), datem = Calendar.getInstance(),datef = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

		try{

			System.out.println("**************Moon-Eclipse-Calculator****************");
			System.out.println("*****Emter the follow data to calculate Eclipse******");
			System.out.println("*******************Your City Name********************");
			//City Name viene preso dal valore dela Text View
			System.out.print("City: ");data.setCityName(scanIn.next()); 
			System.out.println("*********************Latitude************************");
			System.out.println("*****************[-90.0°, 90.0°]*********************");
			System.out.print("Lat: ");data.setLatitude(Double.valueOf( scanIn.next()));
			System.out.println("********************Longitude************************");
			System.out.println("****************[-180.0°, 180.0°]********************");
			System.out.print("Long: ");data.setLongitude(Double.valueOf(scanIn.next()));
			System.out.println("*******************[Altitude]************************");
			//un valore predefinito 100m
			System.out.print("Altitute: ");data.setAltitude(Integer.valueOf(scanIn.next()));
			System.out.println("****************Timezone[-12,14]*********************");
			//public static synchronized TimeZone getDefault ()
			System.out.print("TimeZone: ");data.setTimeZone(Integer.valueOf(scanIn.next()));
			System.out.println("***Eclipse Type to show[ALL, PARTIAL, TOTAL]*********");
			//all
			System.out.print("Eclipse Type: ");data.setEclipseT(scanIn.next());
			FileReader in = new FileReader("C:\\Users\\SAJMIR\\Desktop\\eMoonData\\data.json");

			JSONObject obj = (JSONObject) parser.parse(in);
			JSONObject moonDatas = (JSONObject) obj.get("moonDatas");
			JSONArray record = (JSONArray) moonDatas.get("record");
			/* 
			 * Per avere solo risultati che riguardano la luna rossa, 
			 * bisonga scartare quelli record con datas[0] >= 1
			 */
			for(int i = 0; i < record.size();i++){
				rec = (JSONObject) record.get(i);

				dataf = (JSONArray) rec.get("data");
				str = (JSONArray) rec.get("str");
				datas = (JSONArray) rec.get("datas");

				iterator  = str.iterator();
				iteratorDouble = dataf.iterator();
				iteratorDouble1 = datas.iterator();
				
				/* Stampa di tutti i dati
				System.out.print("data:[");
				while(iteratorDouble.hasNext()){

					System.out.print(iteratorDouble.next());
				}
				System.out.print("],\n str:[");
				while(iterator.hasNext()){
					System.out.print(iterator.next());
				}
				System.out.print("], \n datas:[3]");
				while(iteratorDouble1.hasNext()){
					System.out.print(iteratorDouble1.next());
				}
				*/
				
				dataCirc = new DataCircumstances(data, rec);
				dataCirc.RetriveData();
				/*
				 * Stampo solo gli eventi di un certo tipo
				 */
				
					if((Long) datas.get(0) == 1){
						startTime = new LocalEventData(data, rec, dataCirc.getU2(), dates);
						midTime = new LocalEventData(data, rec, dataCirc.getMid(), datef);
						endTime = new LocalEventData(data, rec, dataCirc.getU3(), datem);;
						double mid[] = dataCirc.getMid();
						
						dates = startTime.getTimeDate();
						//Mi serve il 24 ore format
						//dateFormat.format(dates);
						datem = midTime.getTimeDate();
						datef = endTime.getTimeDate();
						
						

						if(mid[5] != 1.0){
						System.out.println("***********************************************\n" +
								"un altra luna rossa \n"+
								"Date: \t" + startTime.getDate() + "\n" +
								"Time: \t" + startTime.getTime() + "----" +  midTime.getTime() + "----" + endTime.getTime() + "\n" +
								"Ecl Type: \t" + datas.get(0));
						int ores = dates.get(Calendar.HOUR);
						int mins = dates.get(Calendar.MINUTE);
						
						if(dates.get(Calendar.AM_PM) == 0){
							System.out.println(ores+":"+mins + "PM");
						}else{
							System.out.println(ores+":"+mins + "AM");
						}
						
							}
						}

			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			scanIn.close();
		}
		System.out.println("\n\nCity: \t" + data.getCityName() + "\n" +
				"Lat: \t" + data.getLatitude() + "\n" +
				"Long: \t" + data.getLongitude() + "\n" +
				"Altitude: \t"+ data.getAltitude() + "\n" + 
				"Timezone: \t" + data.getTimeZone() + "\n" +
				"Eclipse Type: \t" + data.getEclipseT() + "\n");

		

	}	
}
