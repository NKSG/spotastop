package com.cipciop.spotastop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cipciop.spotastop.domain.Line;
import com.cipciop.spotastop.presentation.BusLineItem;

public class SelectBusLine extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_bus_line);
	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter focusChangedFilter = new IntentFilter();
		focusChangedFilter
				.addAction("com.cipciop.spotastop.linesUpdateReceived");
		this.registerReceiver(this.linesListReceiver, focusChangedFilter);
		StopSpotApp.getInstance().requestLinesListUpdate();
	}

	/* Remove the locationlistener updates when Activity is paused */
	@SuppressLint("NewApi")
	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(this.linesListReceiver);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			final int animTime = getResources().getInteger(
					android.R.integer.config_longAnimTime);
			((ViewGroup) findViewById(R.id.interactive)).animate()
					.setDuration(animTime).alpha(0);
		} else
			((ViewGroup) findViewById(R.id.interactive)).setAlpha(0);
		
		((TextView) SelectBusLine.this
				.findViewById((R.id.linesLoadedText)))
				.setText(getResources().getString(R.string.requestingLines));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_bus_line, menu);
		return true;
	}

	private BroadcastReceiver linesListReceiver = new BroadcastReceiver() {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
		@Override
		public void onReceive(Context context, Intent intent) {
			((ViewGroup) findViewById(R.id.interactive)).setAlpha(0);
			((ViewGroup) findViewById(R.id.busList)).removeAllViews();
			
			
			
			((TextView) SelectBusLine.this
					.findViewById((R.id.linesLoadedText)))
					.setText("Linee ricevute correttamente");

			for (Line l : StopSpotApp.getInstance().getLinesList()) {
				final BusLineItem lineItem = new BusLineItem(SelectBusLine.this);
				lineItem.setLine(l);
				lineItem.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));
				((ViewGroup) findViewById(R.id.busList)).addView(lineItem);
			}
			
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