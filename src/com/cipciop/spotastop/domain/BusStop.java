package com.cipciop.spotastop.domain;

import java.lang.reflect.Type;
import java.util.ArrayList;

import requests.Link;
import resources.Resource;
import rest.RestApi;
import android.annotation.SuppressLint;

import com.cipciop.spotastop.StopSpotApp;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class BusStop extends Resource implements Comparable<Object> {

	private String code;

	private Line line;

	private GeoPos position;

	public BusStop() {
		super("cipciop\\spotastop\\Stop");
	}

	public BusStop(String code) {
		this.setCode(code);
	}

	public BusStop(String code, double latitude, double longitude) {
		this.code = code;
		this.position = new GeoPos(latitude, longitude);

	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setPosition(GeoPos position) {
		this.position = position;
	}

	public GeoPos getPosition() {
		return this.position;
	}

	public void store() {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(this);
		ArrayList<Link> links=new ArrayList<Link>();
		links.add(new Link(this, this.line,"line"));
		links.add(new Link(this,StopSpotApp.getInstance().getLoggedUser(),"creator"));
		RestApi.editResources(resources,links);
	}

	@SuppressLint("DefaultLocale")
	@Override
	public int compareTo(Object another) {

		return this.code.toLowerCase().compareTo(
				((BusStop) another).code.toLowerCase());
	}

	@Override
	public JsonElement serialize(Resource arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.addProperty("name", this.code);
		obj.addProperty("latitudine", this.position.getLatitude());
		obj.addProperty("longitudine", this.position.getLongitude());
		obj.addProperty("resourceClassifier", this.resourceClassifier);
		return obj;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}