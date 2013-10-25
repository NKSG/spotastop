package com.nicfix.stopspot.domain;

import java.util.ArrayList;

public class Line implements Comparable<Object> {
	private String name;
	private java.util.ArrayList<Node> path = new ArrayList<Node>();

	public Line(){}
	
	public Line(String name)
	{
		this.setName(name);		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPath(java.util.ArrayList<Node> path) {
		this.path = path;
	}

	public java.util.ArrayList<Node> getPath() {
		return this.path;
	}

	@Override
	public int compareTo(Object another) {
		
		return this.name.compareTo(((Line)another).name);
	}
}