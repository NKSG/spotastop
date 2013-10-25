package com.nicfix.stopspot.domain;

import java.util.ArrayList;

import android.util.Log;

public class Trip implements Comparable<Object> {

	private Node startNode, finishNode;

	private ArrayList<GeoPos> points;

	private int color = 0xff00ff00;

	private static int[] colors = new int[10];

	private static int numberOfTrips = 0;

	public Trip() {
		reGenerateColors();
		numberOfTrips = (numberOfTrips + 1) % 10;
		this.color = colors[numberOfTrips];
	}

	public Node getStartNode() {
		return startNode;
	}

	public static void reGenerateColors() {
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getFinishNode() {
		return finishNode;
	}

	public void setFinishNode(Node finishNode) {
		this.finishNode = finishNode;
	}

	public ArrayList<GeoPos> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GeoPos> points) {
		this.points = points;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public int compareTo(Object trip) {
		int callback = 0;
		try {
			callback = this.startNode.getTime().compareTo(
					((Trip) trip).startNode.getTime());
		} catch (NullPointerException e) {
			Log.d("NPE", "start: " + this.startNode.getBusStop().getCode()
					+ "->" + ((Trip) trip).startNode.getBusStop().getCode()
					+ "-LINE- " + this.startNode.getLine().getName());
		}
		return callback;

	}
}
