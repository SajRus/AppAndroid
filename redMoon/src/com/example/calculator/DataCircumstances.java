package com.example.calculator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * <p>"Data Circumstances è la classe che elabora i dati provenienti dal JSON 
 * nel package files, questi dati vengono elaborati considerando le informazioni 
 * ottenute dal sitema Android e/o dai dati inseriti dal utente. </p>
 * <p>
 * Tipo di evento che si vuole osservare (nel nosto caso è statico mid) 
 * [0] Event type (p1=-3, u1=-2, u2=-1,mid=0, u3=1, u4=2, p4=3)
 * [1] t
 * [2] houre angle
 * altitudine dove ci si trova
 * [3] altitude
 * visibiltà del evento
 * [4] visibility (0 = above horizont, 1 = no event, 2 = below horizont)
 * </p>
 * 
 * @author SAJMIR
 * @serialData	data	tutti gli dati recuperati dal sistema Android e/o inseriti dal utente
 * @serialData	rec		un oggeto JSON creato dal file data.json
 * 
 */

public class DataCircumstances {

	JSONArray record;
	private double p1[] = null;	//Penumbral Eclipse begins
	private double u1[] = null;	//Partial Eclipse beginis
	private double u2[] = null;	//Total Eclipse begins
	private double mid[] = null;//Mid Eclipse time
	private double u3[] = null;	//Total Eclipse End
	private double u4[] = null;	//Partial Eclipse End
	private double p4[] = null;	//Penumbral Eclipse End
	DataEntry obsvconst = new DataEntry();

	JSONArray dataf = null;
	JSONArray str = null;
	JSONArray datas = null;
	JSONObject rec = null;
	public DataCircumstances(DataEntry data, JSONObject rec) {
		this.rec = rec;
		this.obsvconst = data;
	}
	/**
	 * Metodi ausiliari
	 * @return getters & setters
	 */
	public double[] getP1() {
		return p1;
	}
	public void setP1(double[] p1) {
		this.p1 = p1;
	}
	public double[] getU1() {
		return u1;
	}
	public void setU1(double[] u1) {
		this.u1 = u1;
	}
	public double[] getU2() {
		return u2;
	}
	public void setU2(double[] u2) {
		this.u2 = u2;
	}
	public double[] getMid() {
		return mid;
	}
	public void setMid(double[] mid) {
		this.mid = mid;
	}
	public double[] getU3() {
		return u3;
	}
	public void setU3(double[] u3) {
		this.u3 = u3;
	}
	public double[] getU4() {
		return u4;
	}
	public void setU4(double[] u4) {
		this.u4 = u4;
	}
	public double[] getP4() {
		return p4;
	}
	public void setP4(double[] p4) {
		this.p4 = p4;
	}

	/**
	 * <p>Metodo che recupera i dati dal ogetto JSON e calcola il valore del vettore Event type</p>
	 * {@code Populate the p1, u1, u2, mid, u3, u4 and p4 arrays} 
	 * 
	 */
	public void RetriveData(){
		p1 = new double[6];
		u1 = new double[6];
		u2 = new double[6];
		mid = new double[6];
		u3 = new double[6];
		u4 = new double[6];
		p4 = new double[6];

		dataf = (JSONArray) rec.get("data");
		str = (JSONArray) rec.get("str");
		datas = (JSONArray) rec.get("datas");

		p1[1] = (Double) datas.get(4);
		populatecircumstances(rec,p1);
		mid[1] = (Double) datas.get(7);
		populatecircumstances(rec,mid);
		p4[1] = (Double) datas.get(10);
		populatecircumstances(rec,p4);

		if((Long) datas.get(0) < 3){

			u1[1] = (Double) datas.get(5);
			populatecircumstances(rec,u1);
			u4[1] = (Double) datas.get(9);
			populatecircumstances(rec,u4);

			if((Long) datas.get(0) < 2){
				u2[1] = (Double) datas.get(6);
				u3[1] = (Double) datas.get(8);
				populatecircumstances(rec,u2);
				populatecircumstances(rec,u3);
			}else{
				u2[5] = 1;
				u3[5] = 1;
			}
		}else{
			u1[5] = 1;
			u2[5] = 1;
			u3[5] = 1;
			u4[5] = 1;
		}
		if((p1[5] != 0) && (u1[5] != 0) && (u2[5] != 0) && (mid[5] != 0) && (u3[5] != 0) && (u4[5] != 0) && (p4[5] != 0)){
			mid[5] = 1;
		}		
	}

	/**
	 * 
	 * @param rec sono i dati contenuti nel json
	 * @param circumstance sono le entry condition, circumstances[0] deve contenere il valore corretto
	 */
	private void populatecircumstances(JSONObject rec, double[] p12) {
		double t = p12[1];
		double ra = (Double) datas.get(13)*t + (Double) datas.get(12);
		ra = ra * t + (Double) datas.get(11);
		double dec = (Double) datas.get(16) * t + (Double) datas.get(15);
		dec = dec * t +  (Double) datas.get(14);
		dec = (dec * Math.PI / 180.00);

		p12[3] = dec;

		double h = 15.0 *(((Double) datas.get(1)) + (t - ((Double) dataf.get(2)/3600.0) )*1.00273791) - ra ;
		h = (h * Math.PI / 180.0 - obsvconst.getLongitude());
		p12[2] = h;

		p12[4] = Math.asin( Math.sin(obsvconst.getLatitude()) * Math.sin(dec) + 
				Math.cos(obsvconst.getLatitude()) * Math.cos(dec) * Math.cos(h) 
				);
		p12[4] -= Math.asin(Math.sin((Double)datas.get(2)*Math.PI/180.0) * Math.cos(p12[4]));
		if(p12[4] * 180.0/Math.PI < ((Double) datas.get(3) - 0.5667)){
			p12[5] = 2;
		}else if(p12[3] < 0.0 ){
			p12[4] = 0.0;
			p12[5] = 0;
		}else{
			p12[5] = 0;
		}
	}

}
