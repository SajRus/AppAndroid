package com.example.redmoon;
import android.os.Bundle;
import android.widget.TabHost;
import android.app.TabActivity;
import android.content.Intent;
/**
 * @author SAJMIR 
 * @deprecated  la classe TabActivity è stata sconsigliata da api level 13. Utilizzata quì solo a scopo ilistrativo.
 * @TODO per la prossima release verra implementato la classe Fragments, 
 * per offrire supporto anche alle vecchie versioni si utilizzerà la v4support library  
 * 
 * @description La classe RedMoon estende TabActivicty e non imposta alcun layout, in quando ne eredita 
 * uno di default. Anche la gestione del setup viene ereditata.
 */

@Deprecated
public class RedMoon extends TabActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

				TabHost tabHost = getTabHost();

				tabHost.addTab(tabHost.newTabSpec("Terra").setContent(
						new Intent(this,Terra.class)).setIndicator(
						"Terra"));
				tabHost.addTab(tabHost.newTabSpec("Sole").setContent(
						new Intent(this, Sole.class)).setIndicator(
						"Sole"));	
				tabHost.addTab(tabHost.newTabSpec("Luna").setContent(
						new Intent(this, Luna.class)).setIndicator(
						"Luna"));	
	}
	
}