package com.example.redmoon;

import java.util.List;

import org.json.JSONException;

import com.example.location.PlacePicker;
import com.example.parsing.JSONWeatherParser;
import com.example.parsing.Weather;
import com.example.redmoon.R;
import com.example.parsing.LocationRetrive;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author SajRus
 *
 */
public class Terra extends Activity{
	private TextView cityText, condDescr, temp, minTemp, maxTemp, press, windSpeed, windDeg, hum;
	private ImageView imgView;
	
	static final int INFO_DIALOG_ID = 0;
	public static final String SPREF_NAME = "redMoonFile";
	static final int PLACE_PICKER_REQ = 1;
	AlertDialog aboutUs;
	Button devInfo;
	
	LocationRetrive locret = new LocationRetrive();
			
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this),
							builder_n = new AlertDialog.Builder(this);
		builder.setMessage("RedMoon vi tiene in contatto con il cielo e le stelle...")
		.setCancelable(true)
		.setNeutralButton("Developer", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.twitter.com/SajmirRusi")));
			}			
		});
		String conServ = Context.CONNECTIVITY_SERVICE;
		ConnectivityManager  conState =  (ConnectivityManager) getSystemService(conServ);
		NetworkInfo[] conmanag = conState.getAllNetworkInfo();
		setContentView(R.layout.main);
		boolean flag = false;
		for(int i=0; i< conmanag.length - 1; i++){
			if(conmanag[i].isConnected()){
				flag = false;
				final JSONWeatherTask task = new JSONWeatherTask();
				/**
				 * Get the last known location
				 * */
				LocationManager locmanag = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				List<String> providers = locmanag.getProviders(true);
				Location loc = null;
				for(int j= providers.size()-1; j>=0;j--){
					loc = locmanag.getLastKnownLocation(providers.get(j));
					if( loc != null) break;
				}
				double[] gps = new double[2];
				if(loc != null){
					gps[0] = loc.getLatitude();
					gps[1] = loc.getLongitude();
					locret.setLatitude((float) gps[0]);
					locret.setLongitude((float) gps[1]);
					String city = "";
					city = locret.getCity();
					task.execute(new String[]{"lat=" + String.valueOf(gps[0]), "lon=" + String.valueOf(gps[1]), city });
				}
				cityText = (TextView) findViewById(R.id.cityText);
				condDescr = (TextView) findViewById(R.id.condDescr);
				temp = (TextView) findViewById(R.id.temp);
				minTemp = (TextView) findViewById(R.id.minTemp);
				maxTemp = (TextView) findViewById(R.id.maxTemp);
				hum = (TextView) findViewById(R.id.hum);
				press = (TextView) findViewById(R.id.press);
				windSpeed = (TextView) findViewById(R.id.windSpeed);
				windDeg = (TextView) findViewById(R.id.windDeg);
				imgView = (ImageView) findViewById(R.id.condIcon);	
				break;
			} else {
				flag = true; //Data network non disponibile
			}
		}
		
		aboutUs = builder.create();
		
		if(flag){
			builder_n.setTitle(R.string.no_connection)
				.setMessage(R.string.active_connection)
				.setPositiveButton(R.string.connection_yes, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_LAUNCHER);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			});
			builder_n.setNegativeButton(R.string.connection_no, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			});
		builder_n.create();
		builder_n.show();
		}else{
			cityText.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					placePick(); 
					cityText = (TextView) findViewById(R.id.cityText);
				}
			});	
		}
	}			
/*END onCreate*/
	
	@Override
	protected void onStart(){
		super.onStart();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	
	@Override
	protected void onPause(){
	    super.onPause();
	   // We need an Editor object to make preference changes.
	   // All objects are from android.context.Context
	   SharedPreferences settings = getSharedPreferences(SPREF_NAME, 0);
	   SharedPreferences.Editor editor = settings.edit();
	   editor.putString("lastCity", locret.getCity() + ", " +locret.getCountry());
	   editor.putLong("lastsunrise", locret.getSunrise());
	   editor.putLong("lastsunset", locret.getSunset());
	   editor.putFloat("lat", locret.getLatitude());
	   editor.putFloat("lon", locret.getLongitude());
	   // Commit the edits!
	   editor.commit();
	}			
	
	/* PlacePicker Activity intent*/
	private void placePick(){
		Intent pick = new Intent(this, PlacePicker.class);
		startActivityForResult(pick, PLACE_PICKER_REQ);
	}
		
	@Override
	protected void onActivityResult(int reqCode, int resCode, Intent data){
		// Check which request we're responding to
		if (reqCode == PLACE_PICKER_REQ) {
		   // Make sure the request was successful
		   if (resCode == RESULT_OK) {	
			   String result = data.getStringExtra("result");
			   Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
			   String[] parts = result.split(",");
			   locret.setCity(parts[0]);
			   final JSONWeatherTask task = new JSONWeatherTask();	
			   task.execute(new String[]{null, null, locret.getCity()});
		   	}
	    }
	}
	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
		Weather weather = new Weather();
	
		@Override
		protected Weather doInBackground(String... params) {
			/*
			 * getWeatherData(String url_extension);
			 * 
			 * */
			String url_extension_city = "q=" + params[2] + "&lang=it";
			String url_extension = params[0] + "&" + params[1] + "&lang=it";
			String data = "";
			
			/*
			 * da inserire qui un progressDialog
			 */
			if(params[2] != null)
			{data = ( (new WeatherHttpClient()).getWeatherData(url_extension_city));}
			else{data = ( (new WeatherHttpClient()).getWeatherData(url_extension));}
					
			try {
				weather = JSONWeatherParser.getWeather(data);
				weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			return weather;
		}
		
		@Override
		protected void onPostExecute(Weather weather) {			
			super.onPostExecute(weather);
			if (weather.iconData != null && weather.iconData.length > 0) {
				Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
				if(img != null){
					Bitmap imgx = Bitmap.createScaledBitmap(img, (img.getWidth()*2), (img.getHeight()*2), true);
					imgView.setImageBitmap(imgx);

				}else{
					imgView.setImageBitmap(img);
				}
			}
			cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
			locret.setCity(weather.location.getCity());
			locret.setCountry(weather.location.getCountry());
			locret.setSunrise(weather.location.getSunrise());
			locret.setSunset(weather.location.getSunset());
			condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
			temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "°C");
			minTemp.setText("" + Math.round((weather.temperature.getMinTemp() - 273.15)) + "°C");
			maxTemp.setText("" + Math.round((weather.temperature.getMaxTemp() - 273.15)) + "°C");
			hum.setText("" + weather.currentCondition.getHumidity() + "%");
			press.setText("" + weather.currentCondition.getPressure() + " hPa");
			windSpeed.setText("" + weather.wind.getSpeed() + " mps");
			windDeg.setText("" + weather.wind.getDeg() + "°");	
		}	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		int order = Menu.FIRST, GROUPA = 0;
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
		}
		return null;
	}
}