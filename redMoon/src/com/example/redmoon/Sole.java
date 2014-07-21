package com.example.redmoon;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
/**
 * @author SajRus
 *
 * 
 */
public class Sole extends Activity{
	private TextView cityText;
	private TextView sunrise;
	private TextView sunset;
	static final int INFO_DIALOG_ID = 0;
	AlertDialog aboutUs;
	Button devInfo;
	public static final String SPREF_NAME = "redMoonFile";

	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("RedMoon vi tiene in contatto con il cielo e le stelle...")
		.setCancelable(true)
		.setNeutralButton("Developer", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(Intent.ACTION_VIEW)
				.setData(Uri.parse("https://www.twitter.com/SajmirRusi")));
			}			
		});				
		setContentView(R.layout.sole);
		cityText = (TextView) findViewById(R.id.cityText);
		sunrise = (TextView) findViewById(R.id.sunrise);
		sunset = (TextView) findViewById(R.id.sunset);
		aboutUs = builder.create();
	}
	/*END onCreate*/
	@Override
	protected void onStart(){
		super.onStart();
		//Restore preferences
		SharedPreferences settings = getSharedPreferences(SPREF_NAME, 0);
		String currentCity = settings.getString("lastCity", "città non presente");
		Long sunr = settings.getLong("lastsunrise", 00);
		Long suns = settings.getLong("lastsunset", 00);
		cityText.setText(currentCity);
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(sunr * 1000);
		dateFormat.setCalendar(cal);
		sunrise.setText("Sorge: " + dateFormat.format(cal.getTime()));
		

		cal.setTimeInMillis(suns * 1000);
		dateFormat.setCalendar(cal);
		sunset.setText("Tramonta: " + dateFormat.format(cal.getTime()));
		
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
		}
		return null;
	}
}
