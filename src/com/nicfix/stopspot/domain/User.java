package com.nicfix.stopspot.domain;

public class User {
	private String code;
	private String password;
	private String name;
	private String surname;
	private String bdate;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
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

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBdate() {
		return this.bdate;
	}
}