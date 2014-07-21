package com.example.redmoon;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.example.calculator.DataCircumstances;
import com.example.calculator.DataEntry;
import com.example.calculator.LocalEventData;

import com.example.redmoon.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * <p>Questa classe è responsabile della visualizzaziode dei risultati ottenuti dal oggetto
 * LocalEventData. La classe in questione gestisce le diverse componenti del Activity e 
 * ovviamente il suo ciclo di vita.</p>  
 * 
 * @author SajRus
 *
 * 
 */
public class Luna extends Activity{

	private TextView next_eventV;
	private TextView dateEclipseV;
	private TextView startTimeV;
	private TextView endTimeV;
	private TextView midTimeV;
	private TextView noEclipseV;
	private TextView nextEclTextV;

	static final int INFO_DIALOG_ID = 0, NEXT_EVENT = 1;
	public static final String SPREF_NAME = "redMoonFile";

	AlertDialog aboutUs, nextEvent;
	Button devInfo;

	/*Event Day*/
	private int day, month, year;

	/**
	 * @Eclipse Calculator
	 * 
	 * DataEntry:	Le informazioni ottenute dal sitema android e/o inserite dal utente
	 * DataCircumstances: 	  Elaborazione dati
	 * LocalEventData:	Calcola il giorno nel quale si verica un evento
	 *  
	 * */
	final DataEntry data = new DataEntry();
	JSONParser parser = new JSONParser();
	JSONObject rec = null;
	JSONArray dataf = null , str = null, datas = null;
	DataCircumstances dataCirc = null;
	LocalEventData startTime, midTime, endTime = null;

	int evDay = 0, evMonth = 0, evYear = 2015; //Evento

	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	String nextMoon = "";

	/*START OnCreate*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AlertDialog.Builder builder = new AlertDialog.Builder(this),
				builder_n = new AlertDialog.Builder(this);

		builder_n.setMessage("Non ci sono eventi nel anno corrente").setCancelable(true);
		builder.setMessage("RedMoon vi tiene in contatto con il cielo e le stelle...")
		.setCancelable(true)
		.setNeutralButton("Developer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.twitter.com/SajmirRusi")));
			}			
		});
		setContentView(R.layout.activity_luna);

		next_eventV = (TextView) findViewById(R.id.nextEclipse);
		dateEclipseV = (TextView) findViewById(R.id.dateEclipse);
		startTimeV = (TextView) findViewById(R.id.startEclTime);
		endTimeV = (TextView) findViewById(R.id.endEclTime);
		midTimeV = (TextView) findViewById(R.id.midEclTime);
		noEclipseV = (TextView) findViewById(R.id.noElipse);
		nextEclTextV = (TextView) findViewById(R.id.nextEclText);

		aboutUs = builder.create();
		nextEvent = builder_n.create();

	}
	/*END OnCreate*/

	/*START OnStart :)*/
	@Override
	protected void onStart(){
		super.onStart();

		//Restore Shared preferences
		SharedPreferences settings = getSharedPreferences(SPREF_NAME, 0);
		String currentCity = settings.getString("lastCity", "città non presente");
		Float latitude = settings.getFloat("lat", 00);
		Float longitude = settings.getFloat("lon", 00);

		//Set Input data for Elcipse Calculator
		data.setCityName(currentCity); 	// Set Current city 
		data.setAltitude(100);			//Set altitudine di un valore statico
		data.setLatitude(latitude);		//Set Latitudine
		data.setLongitude(longitude);	//Set Longitudine
		data.setEclipseT("ALL");		//Set type of Event

		//Today
		Calendar cal = Calendar.getInstance();		//Solo una istanza per volta di Calendar può essere utilizzata
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//System.out.println(dateFormat.format(cal.getTime()));
		dateEclipseV.setText(data.getCityName() +"  "+ dateFormat.format(cal.getTime()));//Visualizzo la città e la data


		try {

			InputStream inStream = getResources().openRawResource(R.raw.data);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			StringBuilder out = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null){
				out.append(line);
			}

			//Parsing
			JSONObject obj = (JSONObject) parser.parse(out.toString());
			//Get Object
			JSONObject moonDatas = (JSONObject) obj.get("moonDatas");
			//Create Array
			JSONArray record = (JSONArray) moonDatas.get("record");


			for(int i = 0; i < record.size();i++){

				rec = (JSONObject) record.get(i);

				//Creo gli JSONarray
				dataf = (JSONArray) rec.get("data");
				str = (JSONArray) rec.get("str");
				datas = (JSONArray) rec.get("datas");

				dataCirc = new DataCircumstances(data, rec);
				dataCirc.RetriveData();

				/*
				 * Stampo solo gli eventi di un certo tipo, 
				 * cioè quelli di eclisso totale visibile come luna rossa
				 * con datas[0] >= 1
				 * 
				 */
				if((Long) datas.get(0) == 1){

					Calendar dates = Calendar.getInstance();
					startTime = new LocalEventData(data, rec, dataCirc.getU2(), dates);
					dates = startTime.getTimeDate(); //Ora di Inizio


					Calendar datemid = Calendar.getInstance();
					midTime = new LocalEventData(data, rec, dataCirc.getMid(), datemid);
					datemid = midTime.getTimeDate();	//Ora di fine


					Calendar datefin = Calendar.getInstance();
					endTime = new LocalEventData(data, rec, dataCirc.getU3(), datefin);
					datefin = endTime.getTimeDate();	//Ora di pieno eclisse


					double mid[] = dataCirc.getMid();

					String dateEvent = startTime.getDate(); // Data Evento
					String startH = startTime.getTime();
					String midH	= midTime.getTime();
					String endH	= endTime.getTime();


					String [] dt = dateEvent.split("\\-");

					//Data evento
					//10-01-2001
					//16-05-2003
					//09-12-2003

					evDay = dates.get(dates.DAY_OF_MONTH);
					evMonth = dates.get(dates.MONTH);
					evYear = dates.get(dates.YEAR);

					//Oggi
					Calendar present = Calendar.getInstance();
					month = present.get(Calendar.MONTH);	//Test 0, 4, 11
					year = present.get(Calendar.YEAR);		//2001, 2003, 2003
					day = present.get(Calendar.DAY_OF_MONTH);//10, 16, 09

					//Tutti gli eventi visibili dalla location
					if(mid[5] != 1.0){

						if(day == evDay && month == evMonth && year == evYear){
							//Se la condizione è vera, abbiamo un evento 
							startTimeV.setText(startH);
							midTimeV.setText(midH);
							endTimeV.setText(endH);

							i =+ record.size();
						}else{
							noEclipseV.setText(R.string.noEclipse);

						}
						if(year == evYear){
							nextMoon = "Prossimo evento di luna rossa: " +  startTime.getDate();
						}else{
							nextMoon = "Non ci sono eventi nel anno corrente";
						}
					}

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 


		//TODO scrivere un "curiosita" random
		nextEclTextV.setText(R.string.nextEclText); 
	}
	/*END OnStart :)*/


	@SuppressWarnings("deprecation")
	public void openNext(View v){
		showDialog(NEXT_EVENT);
	}

	/*START Options Menu*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		int order = Menu.FIRST;
		int GROUPA = 0;

		menu.add(GROUPA, 0, order++, R.string.aboutus_string);
		menu.add(GROUPA, 1, order++, R.string.exit_menu_string);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case 0:
			showDialog(INFO_DIALOG_ID);
			return true;
		case 1:
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}		
	}
	@Override
	protected Dialog onCreateDialog(int id){
		switch(id){
		case INFO_DIALOG_ID:
			return aboutUs;
		case NEXT_EVENT:
			return nextEvent;
		}
		return null;
	}
	/*END Options Menu*/
}
