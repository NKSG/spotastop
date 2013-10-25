package com.nicfix.stopspot.domain;

import java.util.Date;

public class Validation {
	private Date validationTime;
	private Ticket ticket;
	private User user;
	private Validator validator;

	public void setValidationTime(Date validationTime) {
		this.validationTime = validationTime;
	}

	public Date getValidationTime() {
		return this.validationTime;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public Validator getValidator() {
		return this.validator;
	}
}