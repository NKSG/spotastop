package com.cipciop.spotastop.domain;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.cipciop.spotastop.StopSpotApp;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import resources.Resource;
import rest.RestApi;

public class Line extends Resource implements Comparable<Object> {

	private String name;

	private String direzione;

	public Line() {
		super("cipciop\\spotastop\\Line");
	}

	public Line(String name) {
		this.setName(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getDirezione() {
		return direzione;
	}

	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}

	@Override
	public int compareTo(Object another) {

		return this.name.compareTo(((Line) another).name);
	}

	public void store() {
		super.store();
		RestApi.linkResources(this, StopSpotApp.getInstance().getLoggedUser(),
				"creator");
	}

	@Override
	public Resource deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		Line turnback = new Line();
		turnback.resourceClassifier = arg0.getAsJsonObject()
				.get("resourceClassifier").getAsString();
		turnback.resourceIdentifier = arg0.getAsJsonObject().get("id")
				.getAsLong();
		if (arg0.getAsJsonObject().get("resourceIdentifier") != null)
			turnback.resourceClassifier = arg0.getAsJsonObject()
					.get("resourceIdentifier").getAsString();
		if (arg0.getAsJsonObject().get("nome") != null)
			turnback.name = arg0.getAsJsonObject().get("nome").getAsString();
		if (arg0.getAsJsonObject().get("direzione") != null)
			turnback.direzione = arg0.getAsJsonObject().get("direzione")
					.getAsString();

		return turnback;
	}


}