package com.example.calculator;

/**
 * <p>La classe DataEntry è la struttura dati che contien appunto i dati
 * provenienti dal sistema Android e/o inseriti dal utente
 * 
 * [0]Latitude	Viene recuperato dal sistema di geolocalizzaizone  
 * [1]Longitude	Viene recuperato dal sistema di geolocalizzaizone
 * [2]Altitude	Siccome il verificarsi de eclisse è poco infuenzato da questo valore, si è pensati di settarlo ad un valora costante
 * [3]Time Zone	 
 * [4]index, start from 0
 * [5]Choose eclipse types for search[all, partial & total, total]: il tipo di eclisse usato per questa applicazione è quello tortale 
 * 
 * @author SAJMIR
 * 
 */
public class DataEntry {
	private String CityName = "";
	private double Latitude = 0;
	private double Longitude = 0;
	private int Altitude = 0;
	private int TimeZone = 0;
	private int Index = 0;
	private enum EclipseType  {ALL, PARTIAL, TOTAL};
	private EclipseType EclipseT ;

	public int getIndex() {
		return Index;
	}
	public void setIndex(int index) {
		Index = index;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude * Math.PI/180.0;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude * -Math.PI/180.0;
	}
	public int getAltitude() {
		return Altitude;
	}
	public void setAltitude(int altitude) {
		Altitude = altitude;
	}
	public int getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(int timeZone) {
		TimeZone = timeZone;
	}
	public EclipseType getEclipseT(){
		return EclipseT;
	}
	public void setEclipseT(String eclipset){
		//Di nostro interesse è solo il TOTAL 
		if(eclipset.equalsIgnoreCase( "all")){
			EclipseT = EclipseType.ALL;
		}else if(eclipset.equalsIgnoreCase("partial")){
			EclipseT = EclipseType.PARTIAL;
		}else if(eclipset.equalsIgnoreCase("total")){
			EclipseT = EclipseType.TOTAL;
		}else{
			EclipseT = EclipseType.ALL;
		}

	}
}
