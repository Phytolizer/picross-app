package com.oncontentstop.anothertestapp;

import android.widget.RelativeLayout;

import static com.oncontentstop.anothertestapp.GameStatus.*;

/**
 * Created by mario on 2/7/2017.
 */

public class CommonVars {
	private static BackgroundTimer backgroundBackgroundTimer;
	private static Timer gameTimer;
	private static int sizeX = 10;
	private static int sizeY = 10;
	/**
	 * Approximate proportion of the generated grid that will be filled; must be a value between <code>0.0</code> and <code>1.0</code>.
	 */
	private static final double BOARD_DENSITY = 0.5;
	private static boolean paused = false;
	private static int numMistakes = 0;
	private static GameStatus status = NONE;
	private static int maxMistakes = 5;
	private static ControlMode controlMode = ControlMode.NORMAL;

	public static void makeBackgroundTimer(Background background, RelativeLayout layout) {
		backgroundBackgroundTimer = new BackgroundTimer(background, layout);
	}
	public static BackgroundTimer getBackgroundBackgroundTimer() {
		return backgroundBackgroundTimer;
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

	public static void setPaused(boolean paused) {
		CommonVars.paused = paused;
		if(paused)
			gameTimer.pause();
		else if(status == IN_PROGRESS)
			gameTimer.resume();
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void addMistake() {
		numMistakes++;
		if(numMistakes >= maxMistakes)
			setGameStatus(FAILED);
	}
	public static int getNumMistakes() {
		return numMistakes;
	}

	public static void setGameStatus(GameStatus newStatus) {
		status = newStatus;
		if(status == IN_PROGRESS) {
			gameTimer.resume();
		} else {
			gameTimer.pause();
		}
	}
	public static GameStatus getGameStatus() {
		return status;
	}

	public static int getMaxMistakes() {
		return maxMistakes;
	}

	public static void resetMistakes() {
		numMistakes = 0;
	}

	public static void resetGameTimer() {
		gameTimer = new Timer();
		gameTimer.start();
	}

	public static String getTime() {
		return gameTimer.toString();
	}

	public static void toggleControlMode() {
		if(controlMode == ControlMode.NORMAL) {
			controlMode = ControlMode.MARK;
		} else {
			controlMode = ControlMode.NORMAL;
		}
	}

	public static ControlMode getControlMode() {
		return controlMode;
	}

	public static void setupNewGame() {
		resetGameTimer();
		setGameStatus(GameStatus.IN_PROGRESS);
		resetMistakes();
		if(controlMode == ControlMode.MARK) {
			toggleControlMode();
		}
	}

}
