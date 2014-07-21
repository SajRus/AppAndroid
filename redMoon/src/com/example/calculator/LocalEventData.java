package com.example.calculator;

import java.util.Calendar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Questa classe è responsabile del calcolo di giorno, ora e altitudine del verificarsi di un evento di luna rossa.
 * 
 * @see getDate()
 * @see	getTime()
 * @see	getAlt()
 * 
 * @author SAJMIR
 *
 */

public class LocalEventData {
	
	DataEntry obsvconst = new DataEntry();
	double oneOver24 = 0.041666667;
	JSONObject rec ;
	final JSONArray dataf;	// = (JSONArray) rec.get("data");
	final JSONArray str;	// = (JSONArray) rec.get("str");
	final JSONArray datas;	// = (JSONArray) rec.get("datas");
	private double [] p;
	private String [] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul",
			"Aug","Sep","Oct","Nov","Dec"};
	private Calendar date = Calendar.getInstance();

	public LocalEventData(DataEntry data,  JSONObject rec, double [] p, Calendar date) {
		
		this.rec = rec;
		this.obsvconst = data;
		this.p = p;
		this.dataf = (JSONArray) rec.get("data");
		this.str = (JSONArray) rec.get("str");
		this.datas = (JSONArray) rec.get("datas");
		this.date = date;
	}
	
	public Calendar getTimeDate (){
		return date;
	}
	/**
	 * 
	 * @return String ma setta un oggetto Calendar.date
	 *   
	 */
	public String getDate(){

		String ans = "";
		
		double jd =  Math.floor((Double) dataf.get(0) - ((Long)dataf.get(1) * oneOver24));
		double t = p[1] + (Long) dataf.get(1) - obsvconst.getTimeZone() - ((Double)dataf.get(2) - 30.0)/3600.0;
		if(t < 0.0 ) 
			jd--;
		if(t >= 24.00) 
			jd++;

		double a;
		if(jd >= 2299160.0){
			a = Math.floor((jd - 1867216.25) / 36524.25);
			a = jd + 1 + a - Math.floor(a/4);
		}else{
			a = jd;
		}
		double b = a + 1525.0;
		int c = (int) Math.floor((b-122.1)/365.25);
		int d = (int) Math.floor(365.25*c);
		double e = Math.floor((b - d) / 30.6001);
		d = (int) (b - d - Math.floor(30.6001*e));
		if (e < 13.5) {
			e = e - 1;
		} else {
			e = e - 13;
		}
		if (e > 2.5) {
			ans = c - 4716 + "-";
			date.set(date.YEAR, c-4716);
		} else {
			ans = c - 4715 + "-";
			date.set(date.YEAR, c-4715);
		}
		ans += month[(int) (e-1)] + "-";
		date.set(date.MONTH, (int) e-1);
		if (d < 10) {
			ans = ans + "0";
		}
		date.set(date.DATE, d);
		ans = ans + d;

		return ans;
	}

	/**
	 * 
	 * @return String ma setta un oggetto Calendar.HOUR e Calendar.MINUTE
	 * 
	 */
	public String getTime(){
		String ans = "";

		double t = (p[1] + (Long) dataf.get(1) + obsvconst.getTimeZone() - ((Double) dataf.get(2) -30.0)/3600.0);
		if (t < 0.0) {
			t = t + 24.0;
		}
		if (t >= 24.0) {
			t = t - 24.0;
		}
		if (t < 10.0) {
			ans = ans + "0";
		}
		ans = ans + Integer.valueOf((int) Math.floor(t)) + ":";
		date.set(date.HOUR,  (int) Math.floor(t));
		t = (t * 60.0) - 60.0 * Math.floor(t);
		if (t < 10.0) {
			ans = ans + "0";
		}

		ans = ans + Integer.valueOf((int) Math.floor(t));
		date.set(date.MINUTE, (int) Math.floor(t));

		if (p[5] == 2) {
			return ans;
		} else {
			return ans;
		}
	}

	String getAlt(){
		String ans = "";

		double  t = p[4] * 180.0 / Math.PI;
		t = Math.floor(t+0.5);

		if (t < 0.0) {
			ans = "-";
			t = -t;
		} else {
			ans = "+";
		}
		if (t < 10.0) {
			ans = ans + "0";
		}
		ans = ans + t;
		if(p[5] == 2){
			return ans;
		}else{
			return ans;	
		}
	}
}
