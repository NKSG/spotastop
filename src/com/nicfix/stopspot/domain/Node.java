package com.nicfix.stopspot.domain;

import java.util.Date;


import android.util.Log;

public class Node {
	private Date time=new Date();
	private Node nextNode;
	private Node previousNode;
	private Line line;
	private BusStop busStop;
	private Node pathStartNode;
	
	
	public Node()
	{
		this.time.setTime((long)(Math.random()*12)*60*60*1000);
		this.nextNode=null;
		this.previousNode=null;
	}
	
	public Node(Line linea, BusStop busStop)
	{
		this.time.setTime((long)   ((Math.random()*12)*60*60*1000)+System.currentTimeMillis() );
		this.setLine(linea);
		this.setBusStop(busStop);
		Log.d("NPE", this.busStop.getCode()
				+ "-LINE- " + this.line.getName()
				+"-TIME- "+this.time.toString());
		this.nextNode = null;
		this.previousNode=null;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return this.time;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
		if(nextNode!=null)
			nextNode.setPreviousNode(this);
	}

	public Node getNextNode() {
		return this.nextNode;
	}
	
	public Node findFinishNode()
	{
		Node turnback=this;
		while(turnback.nextNode!=null)
		{
			turnback=turnback.nextNode;
		}
		return turnback;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Line getLine() {
		return this.line;
	}

	public void setBusStop(BusStop busStop) {
		this.busStop = busStop;
	}

	public BusStop getBusStop() {
		return this.busStop;
	}
	
	
	public boolean equals(Node node)
	{
		/**
		 * Same line, same stop and same time, so same Node!
		 */
		return (this.line.getName()==node.getLine().getName() 
				&&
				this.busStop.getCode()==node.getBusStop().getCode()
				&&
				this.time==node.getTime()
				);
	}

	public Node getPathStartNode() {
		return pathStartNode;
	}

	public void setPathStartNode(Node startNode) {
		this.pathStartNode = startNode;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}
	
	public boolean comesBefore(BusStop stop) {
		Node nextNode = this.getNextNode();
		
		while(nextNode != null) {
			if(nextNode.getBusStop().equals(stop))
				return true;
			else nextNode = nextNode.getNextNode();
		}
		
		return false;
	}
	
	
	public boolean comesAfter(BusStop stop) {
		Node previousNode = this.getPreviousNode();
		
		while(previousNode != null) {
			if(previousNode.getBusStop().getCode().equals(stop.getCode()))
				return true;
			else previousNode = previousNode.getPreviousNode();
		}
		
		return false;
	}
}