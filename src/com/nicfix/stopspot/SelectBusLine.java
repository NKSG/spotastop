package com.nicfix.stopspot;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nicfix.stopspot.domain.Line;
import com.nicfix.stopspot.presentation.BusLineItem;

public class SelectBusLine extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_bus_line);
		Line l=new Line();
		l.setName("11");
		BusLineItem lineItem = new BusLineItem(SelectBusLine.this);
		lineItem.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		((ViewGroup) findViewById(R.id.busList)).addView(lineItem);
		lineItem.setLine(l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_bus_line, menu);
		return true;
	}

	private BroadcastReceiver linesListReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			for (Line l : StopSpotApp.getInstance().getLinesList()) {
				BusLineItem lineItem = new BusLineItem(SelectBusLine.this);
				lineItem.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));
				((ViewGroup) findViewById(R.id.busList)).addView(lineItem);
				lineItem.setLine(l);
			}
		}

	};
	private LayoutInflater inflater;

}
