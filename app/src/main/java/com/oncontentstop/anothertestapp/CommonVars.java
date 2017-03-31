package com.oncontentstop.anothertestapp;

import android.widget.RelativeLayout;

/**
 * Created by mario on 2/7/2017.
 */

public class CommonVars {
	private static Timer timer;
	private static int sizeX = 10;
	private static int sizeY = 10;
	private static final double BOARD_DENSITY = 0.5;

	public static void makeTimer(Background background, RelativeLayout layout) {
		timer = new Timer(background, layout);
	}
	public static Timer getTimer() {
		return timer;
	}

	public static int sizeX() {
		return sizeX;
	}

	public static void setSizeX(int sizeX) {
		CommonVars.sizeX = sizeX;
	}

	public static int sizeY() {
		return sizeY;
	}

	public static void setSizeY(int sizeY) {
		CommonVars.sizeY = sizeY;
	}

	public static void incrementSizeX() {
		if(sizeX < 25)
			sizeX++;
	}

	public static void incrementSizeY() {
		if(sizeY < 25)
			sizeY++;
	}

	public static void decrementSizeX() {
		if(sizeX() > 1)
			sizeX--;
	}

	public static void decrementSizeY() {
		if(sizeY > 1)
			sizeY--;
	}

	public static double getBoardDensity() {
		return BOARD_DENSITY;
	}
}
