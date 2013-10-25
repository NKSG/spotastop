package com.nicfix.stopspot.domain;

import java.util.ArrayList;

public class Validator {
	private String code;
	private java.util.ArrayList<Validation> validations = new ArrayList<Validation>();
	private Bus bus;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setValidations(java.util.ArrayList<Validation> validations) {
		this.validations = validations;
	}

	public java.util.ArrayList<Validation> getValidations() {
		return this.validations;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Bus getBus() {
		return this.bus;
	}
}