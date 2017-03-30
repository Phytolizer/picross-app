package com.oncontentstop.anothertestapp;

/**
 * Created by mario on 3/28/2017.
 */

public class Coordinate {
	public int x, y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Coordinate(Coordinate copyFrom) {
		x = copyFrom.x;
		y = copyFrom.y;
	}
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
