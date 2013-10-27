package com.cipciop.spotastop.domain;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import resources.Resource;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

public class User extends Resource {
	private String password;
	private String name;
	private String surname;
	private String username;

	public User() {
		super("cipciop\\spotastop\\User");
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean login(String username, String password) {
		String passwordHash = "";
		if (password != null) {

			MessageDigest md;
			try {
				byte[] bytesOfMessage = password.getBytes("UTF-8");
				md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				passwordHash = new String(thedigest, "UTF-8");
			} catch (NoSuchAlgorithmException e) {
			} catch (UnsupportedEncodingException e) {
			}
		}
		return this.username.equals(username)
				&& (this.password.equals(password) || passwordHash
						.equals(passwordHash));
	}

	@Override
	public Resource deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {

		User turnback = new User();
		turnback.resourceClassifier = arg0.getAsJsonObject()
				.get("resourceClassifier").getAsString();
		turnback.resourceIdentifier = arg0.getAsJsonObject().get("id")
				.getAsLong();
		if (arg0.getAsJsonObject().get("resourceIdentifier") != null)
			turnback.resourceClassifier = arg0.getAsJsonObject()
					.get("resourceIdentifier").getAsString();

		if (arg0.getAsJsonObject().get("username") != null)
			turnback.username = arg0.getAsJsonObject().get("username")
					.getAsString();

		if (arg0.getAsJsonObject().get("password") != null)
			turnback.password = arg0.getAsJsonObject().get("password")
					.getAsString();

		return turnback;
	}

	@Override
	public JsonElement serialize(Resource arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.addProperty("username", this.username);
		obj.addProperty("password", this.password);
		obj.addProperty("resourceClassifier", this.resourceClassifier);
		return obj;
	}
}