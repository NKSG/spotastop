package com.nicfix.stopspot.domain;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Date;


public class BusStop implements Comparable<Object> {
	
	private String code;
	private java.util.ArrayList<Node> nodes = new ArrayList<Node>();
	private GeoPos position;


	public BusStop(){}
	
	public BusStop(String code){
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

	public void setNodes(java.util.ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public java.util.ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public void setPosition(GeoPos position) {
		this.position = position;
	}

	public GeoPos getPosition() {
		return this.position;
	}

	public int getIncomingBusNumber()
	{
		int turnback=0;
		for(int i=0;i<this.nodes.size();i++)
		{
			Node node=nodes.get(i);
			Date now = new Date();
			long minutesDiff = ((node.getTime().getTime() / 60000) - (now.getTime() / 60000));
			if(minutesDiff>0)
			{
				turnback++;
			}
			
		}
		return turnback;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public int compareTo(Object another) {
		
		return this.code.toLowerCase().compareTo(((BusStop)another).code.toLowerCase());
	}
}