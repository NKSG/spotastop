package com.cipciop.spotastop.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cipciop.spotastop.LoginActivity;
import com.cipciop.spotastop.SelectBusLine;
import com.cipciop.spotastop.SpotActivity;
import com.cipciop.spotastop.StopSpotApp;
import com.cipciop.spotastop.domain.Line;
import com.cipciop.spotastop.R;

public class BusLineItem extends LinearLayout {

	private TextView lineName;

	private TextView lineDirection;

	private LayoutInflater inflater;

	private Line line;

	public BusLineItem(Context context) {
		super(context);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.bus_line_item, this, true);
	}

	public void setLine(Line l) {

		this.line = l;
		this.lineName = (TextView) findViewById(R.id.lineName);
		this.lineDirection = (TextView) findViewById(R.id.lineDirection);
		this.lineName.setText(l.getName());
		this.lineDirection.setText(l.getDirezione());

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StopSpotApp.getInstance().setActualLine(line);
				Intent i = new Intent(getContext(), SpotActivity.class);
				getContext().startActivity(i);
			}
		});
	}

}
