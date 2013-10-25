package com.nicfix.stopspot.domain;


public class GeoPos {
	private double latitude;
	private double longitude;
	private String addressName;
	/**
	 * / wrong side
	 */
	private Bus position;
	
	
	public GeoPos(){}
	
	
	public GeoPos(double latitude, double longitude){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressName() {
		return this.addressName;
	}

	public void setPosition(Bus position) {
		this.position = position;
	}

	public Bus getPosition() {
		return this.position;
	}
}