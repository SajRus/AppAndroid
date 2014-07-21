package com.example.location;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.redmoon.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
/**
 * <p>Questa classe è l'activity che ha come obiettivo mostare un interfaccia tramite la quale è possibile
 *  utilizzare il servizio Place Autocomplete che è un servizio offetto dalle api di Google
 *  Inolte implementa l'interfaccia OnItemClickListener, la quale offre la definizione di 
 *  interfaccia per un metodo di callback che viene chiamato quando un item nella AdapterView viene cliccato.
 *   
 * @author SAJMIR
 * @param API_KEY = "AIzaSyCKkCQ4-KQMgb4Ric440S3qxoPUwEiH4zk";
 * @param PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
 * 
 */
public class PlacePicker extends Activity implements OnItemClickListener{
	private static final String LOG_TAG = "ExampleApp";

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
	private static final String OUT_JSON = "/json";

	private static final String API_KEY = "AIzaSyCKkCQ4-KQMgb4Ric440S3qxoPUwEiH4zk";
	/**
	 * <a>Una TextView modificabile che mostra il sugerimento del testo mentre l'utente digita.
	 * L'elenco dei suggerimento viene visualizzato in un menu a tendina da cui l'utente può scegliere un elemento.</a>
	 */
	private AutoCompleteTextView placePicker; 

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.placepicker);
		placePicker = (AutoCompleteTextView) findViewById(R.id.placepicker);
		placePicker.setAdapter(new PlacesAutoCompleteAdapter(this,R.layout.list_item));
		placePicker.setOnItemClickListener(this);
	}

	private ArrayList<String> autocomplete(String input) {
		ArrayList<String> resultList = null;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
			sb.append("?sensor=true&key=" + API_KEY);
			sb.append("&components=country:it");
			sb.append("&input=" + URLEncoder.encode(input, "utf8"));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Error processing Places API URL", e);
			return resultList;
		} catch (IOException e) {
			Log.e(LOG_TAG, "Error connecting to Places API", e);
			return resultList;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<String>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
			}
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Cannot process JSON results", e);
		}
		return resultList;
	}
	private class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
		private ArrayList<String> resultList;

		public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public int getCount() {
			return resultList.size();
		}

		@Override
		public String getItem(int index) {
			return resultList.get(index);
		}

		@Override
		public Filter getFilter() {
			Filter filter = new Filter() {
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults filterResults = new FilterResults();
					if (constraint != null) {
						resultList = autocomplete(constraint.toString());

						filterResults.values = resultList;
						filterResults.count = resultList.size();
					}
					return filterResults;
				}
				@Override
				protected void publishResults(CharSequence constraint, FilterResults results) {
					if (results != null && results.count > 0) {
						notifyDataSetChanged();
					}
					else {
						notifyDataSetInvalidated();
					}
				}};
				return filter;
		}
	}
	/**
	 * @param AdapterView
	 * <a>Metodo di callback che viene chiamato ogni volta che viene inserito un carattere nella AdapterView</a>
	 */
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int portion, long id) {
		String str = (String) adapterView.getItemAtPosition(portion);
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		Intent data = new Intent();
		data.putExtra("result", str);
		setResult(RESULT_OK, data);
		finish();
	}
}
