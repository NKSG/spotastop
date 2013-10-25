package com.nicfix.stopspot;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;

import com.nicfix.stopspot.domain.Line;
import com.nicfix.stopspot.presentation.BusLineItem;

public class SelectBusLine extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_bus_line);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Line l=new Line();
		l.setName("11Comma bis");
		
		BusLineItem lineItem= new BusLineItem(this);
		lineItem.setLine(l);
		((ViewGroup) findViewById(R.id.busList)).addView(lineItem);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_bus_line, menu);
		return true;
	}

	private BroadcastReceiver linesListReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			for (Line l : StopSpotApp.getInstance().getLinesList()) {
				BusLineItem lineItem = (BusLineItem) inflater.inflate(
						R.layout.bus_line_item,
						(ViewGroup) findViewById(R.id.busList), true);
				lineItem.setLine(l);
			}
		}

	};
	private LayoutInflater inflater;

}
