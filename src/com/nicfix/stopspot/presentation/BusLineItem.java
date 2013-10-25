package com.nicfix.stopspot.presentation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.nicfix.stopspot.R;
import com.nicfix.stopspot.domain.Line;

public class BusLineItem extends View {

	private TextView lineName;

	private TextView lineDirection;

	public BusLineItem(Context context) {
		super(context);
		
	}

	public void setLine(Line l) {
		/*
		this.lineName = (TextView) findViewById(R.id.lineName);
		this.lineDirection = (TextView) findViewById(R.id.lineDirection);
		this.lineName.setText(l.getName());
		if (l.getPath().size() > 0)
			this.lineDirection.setText(l.getPath().get(l.getPath().size() - 1)
					.getBusStop().getCode());
				*/
	}

}
