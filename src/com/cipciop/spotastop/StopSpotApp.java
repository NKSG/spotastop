package com.cipciop.spotastop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import resources.Resource;
import rest.RestApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;

import com.cipciop.spotastop.domain.BusStop;
import com.cipciop.spotastop.domain.GeoPos;
import com.cipciop.spotastop.domain.Line;
import com.cipciop.spotastop.domain.User;
import com.cipciop.spotastop.services.RetrieveLinesListService;
import com.cipciop.spotastop.services.SpotBusStopService;

public class StopSpotApp extends Application {

	private static StopSpotApp instance;

	private HashMap<String, Line> lines = new HashMap<String, Line>();

	private ArrayList<Resource> pendingBusStops = new ArrayList<Resource>();

	private Line actualLine = null;

	private Location actualLocation = null;

	private String insertedPassword;

	private String insertedUsername;

	private User loggedUser = null;

	private SharedPreferences prefs;

	public static StopSpotApp getInstance() {
		return StopSpotApp.instance;
	}

	public StopSpotApp() {
		super();
		StopSpotApp.instance = this;
		RestApi.setServerUrl("http://79.8.248.12");
	}

	public SharedPreferences getPrefs() {
		return prefs;
	}

	public void setPrefs(SharedPreferences prefs) {
		this.prefs = prefs;
	}

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

	public Line getLine(String lineName) {
		return this.lines.get(lineName);
	}

	public Collection<Line> getLinesList() {
		return this.lines.values();
	}

	public void requestLinesListUpdate() {
		this.lines.clear();
		Line l = new Line();
		Intent i = new Intent(this.getApplicationContext(),
				RetrieveLinesListService.class);
		startService(i);
	}

	public void updateLinesList(ArrayList<Resource> lines) {
		for (int i = 0; i < lines.size(); i++) {
			StopSpotApp.this.lines.put(
					String.valueOf(lines.get(i).getResourceIdentifier()),
					((Line) lines.get(i)));
		}
		Intent intent = new Intent();
		intent.setAction("com.cipciop.spotastop.linesUpdateReceived");
		StopSpotApp.this.getApplicationContext().sendBroadcast(intent);
	}

	public Line getActualLine() {
		return actualLine;
	}

	public void setActualLine(Line actualLine) {
		this.actualLine = actualLine;
	}

	public Location getActualLocation() {
		return actualLocation;
	}

	public void setActualLocation(Location actualLocation) {
		this.actualLocation = actualLocation;
	}

	public void pushNewStop() {
		Location latest = this.actualLocation;
		if (latest != null) {
			BusStop newStop = new BusStop();
			newStop.setPosition(new GeoPos(latest.getLatitude(), latest
					.getLongitude()));
			newStop.setLine(actualLine);
			this.pendingBusStops.add(newStop);
			Intent i = new Intent(this.getApplicationContext(),
					SpotBusStopService.class);
			startService(i);
		}
	}

	public ArrayList<Resource> getPendingBusStops() {
		return pendingBusStops;
	}

	public void newStopsPushCompleted() {
		this.pendingBusStops = new ArrayList<Resource>();
		Intent intent = new Intent();
		intent.setAction("com.cipciop.spotastop.stopsPushCompleted");
		StopSpotApp.this.getApplicationContext().sendBroadcast(intent);
	}

	public String getInsertedPassword() {
		if (insertedPassword == null) {
			
			insertedUsername = prefs.getString("username", null);
			insertedPassword = prefs.getString("password", null);
		}
		return insertedPassword;
	}

	public void setInsertedPassword(String insertedPassword) {
		this.insertedPassword = insertedPassword;
	}

	public String getInsertedUsername() {
		if (insertedUsername == null) {
			
			insertedUsername = prefs.getString("username", null);
			insertedPassword = prefs.getString("password", null);
		}
		return insertedUsername;
	}

	public void setInsertedUsername(String insertedUsername) {
		this.insertedUsername = insertedUsername;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;

		prefs.edit().putString("username", insertedUsername);
		prefs.edit().putString("password", insertedPassword);
		prefs.edit().commit();

		Intent intent = new Intent();
		intent.setAction("com.cipciop.spotastop.loginDone");
		StopSpotApp.this.getApplicationContext().sendBroadcast(intent);
	}

}
