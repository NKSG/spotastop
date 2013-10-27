package com.cipciop.spotastop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpotActivity extends Activity {

	/**
	 * All activities that uses this class must register this receiver to the
	 * location service
	 */
	public LocationListener locationListener = new LocationListener() {
		@SuppressLint("NewApi")
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
						.fromHtml("Linea "
								+ StopSpotApp.getInstance().getActualLine()
										.getName()
								+ " <br>Posizione rilevata :) <br> lat:"
								+ String.valueOf(location.getLatitude())
								+ "lng:"
								+ String.valueOf(location.getLongitude())));
				StopSpotApp.getInstance().setActualLocation(location);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
					final int animTime = getResources().getInteger(
							android.R.integer.config_longAnimTime);
					((ViewGroup) findViewById(R.id.interactive)).animate()
							.setDuration(animTime).alpha(1);
				} else
					((ViewGroup) findViewById(R.id.interactive)).setAlpha(1);
			} else {
				StopSpotApp.getInstance().setActualLocation(null);
				((TextView) SpotActivity.this
						.findViewById(R.id.actualPositionText))
						.setText("Precisione della posizione insufficiente");
			}

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

		};
	};

	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spot);

		SpotActivity.this.findViewById(R.id.noLocationLayout).setVisibility(
				View.GONE);
		SpotActivity.this.findViewById(R.id.push_stop).setOnClickListener(
				new OnClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onClick(View v) {

						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							final int animTime = getResources().getInteger(
									android.R.integer.config_longAnimTime);
							((ViewGroup) findViewById(R.id.interactive))
									.animate().setDuration(animTime).alpha(0);
						} else
							((ViewGroup) findViewById(R.id.interactive))
									.setAlpha(0);

						((TextView) SpotActivity.this
								.findViewById(R.id.actualPositionText))
								.setText(getResources().getString(
										R.string.pushingStop));

						StopSpotApp.getInstance().pushNewStop();

					}
				});
		SpotActivity.this.findViewById(R.id.line_change).setOnClickListener(
				new OnClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onClick(View v) {

						Intent i = new Intent(SpotActivity.this,
								SelectBusLine.class);
						startActivity(i);

					}
				});

	}

	/* Request updates at startup */
	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();

		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		locationManager.requestLocationUpdates(provider, 100, 1,
				this.locationListener);

		if (StopSpotApp.getInstance().getActualLocation() == null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				final int animTime = getResources().getInteger(
						android.R.integer.config_longAnimTime);
				((ViewGroup) findViewById(R.id.interactive)).animate()
						.setDuration(animTime).alpha(0);
			} else
				((ViewGroup) findViewById(R.id.interactive)).setAlpha(0);
		}

		IntentFilter focusChangedFilter = new IntentFilter();
		focusChangedFilter
				.addAction("com.cipciop.spotastop.stopsPushCompleted");
		this.registerReceiver(this.stopsPushCompletedReceiver,
				focusChangedFilter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this.locationListener);
		this.unregisterReceiver(stopsPushCompletedReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.spot, menu);
		return true;
	}

	private BroadcastReceiver stopsPushCompletedReceiver = new BroadcastReceiver() {
		@SuppressLint("NewApi")
		@Override
		public void onReceive(Context context, Intent intent) {
			((TextView) SpotActivity.this.findViewById(R.id.actualPositionText))
					.setText(getResources()
							.getString(R.string.new_stop_created));
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				final int animTime = getResources().getInteger(
						android.R.integer.config_longAnimTime);
				((ViewGroup) findViewById(R.id.interactive)).animate()
						.setDuration(animTime).alpha(1);
			} else
				((ViewGroup) findViewById(R.id.interactive)).setAlpha(1);

		}

	};
}
