package com.nicfix.stopspot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import android.app.Application;

import com.nicfix.stopspot.domain.Line;

public class StopSpotApp extends Application {

	private static StopSpotApp instance;

	public static StopSpotApp getInstance() {
		if (instance == null) {
			instance = new StopSpotApp();
		}
		return instance;
	}

	private HashMap<String, Line> lines = new HashMap<String, Line>();

	public HashMap<String, Line> getLines() {
		return lines;
	}

	public void setLines(HashMap<String, Line> lines) {
		this.lines = lines;
	}

	public void putLine(Line l, boolean replace) {
		if (this.lines.containsKey(l.getName()) && replace) {
			this.lines.remove(l.getName());
		}
		this.lines.put(l.getName(), l);
	}
	
	public Line getLine(String lineName)
	{
		return this.lines.get(lineName);
	}
	
	public Collection<Line> getLinesList()
	{
		return this.lines.values();
	}

}
