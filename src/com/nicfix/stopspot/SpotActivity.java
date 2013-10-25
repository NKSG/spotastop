package com.nicfix.stopspot;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpotActivity extends Activity {

	/**
	 * All activities that uses this class must register this receiver to the
	 * location service
	 */
	public LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			boolean found = false;
			if (!location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
				found = true;
			} else {
				if (location.getAccuracy() < 30) {
					found = true;
				}
			}
			if (found) {
				((TextView) SpotActivity.this
						.findViewById(R.id.actualPositionText)).setText(Html
						.fromHtml("Posizione rilevata :) <br> lat:"
								+ String.valueOf(location.getLatitude())
								+ "lng:"
								+ String.valueOf(location.getLongitude())));
				((ProgressBar) SpotActivity.this
						.findViewById(R.id.searchingPositionBar))
						.setIndeterminate(false);
			} else
				((TextView) SpotActivity.this
						.findViewById(R.id.actualPositionText))
						.setText("Precisione della posizione insufficiente");

		}

		@Override
		public void onProviderDisabled(String provider) {
			SpotActivity.this.findViewById(R.id.noLocationLayout)
					.setVisibility(View.VISIBLE);
		}

		@Override
		public void onProviderEnabled(String provider) {
			SpotActivity.this.findViewById(R.id.noLocationLayout)
					.setVisibility(View.GONE);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			((ProgressBar) SpotActivity.this
					.findViewById(R.id.searchingPositionBar))
					.setIndeterminate(true);

		};
	};
	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spot);
		SpotActivity.this.findViewById(R.id.noLocationLayout).setVisibility(
				View.GONE);

	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1,
				this.locationListener);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this.locationListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spot, menu);
		return true;
	}

}
