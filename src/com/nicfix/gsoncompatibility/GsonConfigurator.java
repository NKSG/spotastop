package com.nicfix.gsoncompatibility;

import resources.Resource;
import responses.beContentResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfigurator {

	private GsonBuilder gsonBuilder;

	private static GsonConfigurator instance;

	public static GsonConfigurator getInstance() {
		if (instance == null) {
			instance = new GsonConfigurator();
		}
		return instance;
	}

	private GsonConfigurator() {
		gsonBuilder=new GsonBuilder();
		gsonBuilder.registerTypeAdapter(beContentResponse.class,
				new beContentResponse());
	}

	public Gson build() {
		return gsonBuilder.create();
	}

	public void registerDeserializer(Resource resource, Class T) {
		gsonBuilder.registerTypeAdapter(T, resource);
	}

}
