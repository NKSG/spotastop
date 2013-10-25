package com.nicfix.stopspot.domain;

public class Bus {
	private String code;
	private String type;
	private Node nextStop = new Node();
	private Line line;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setNextStop(Node nextStop) {
		this.nextStop = nextStop;
	}

	public Node getNextStop() {
		return this.nextStop;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Line getLine() {
		return this.line;
	}
}