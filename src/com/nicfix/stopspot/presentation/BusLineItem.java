package com.nicfix.stopspot.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicfix.stopspot.LoginActivity;
import com.nicfix.stopspot.R;
import com.nicfix.stopspot.SelectBusLine;
import com.nicfix.stopspot.SpotActivity;
import com.nicfix.stopspot.StopSpotApp;
import com.nicfix.stopspot.domain.Line;

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
		if (l.getPath().size() > 0)
			this.lineDirection.setText(l.getPath().get(l.getPath().size() - 1)
					.getBusStop().getCode());

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), SpotActivity.class);
				getContext().startActivity(i);
			}
		});
	}

}
