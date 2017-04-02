package com.oncontentstop.anothertestapp;

import android.graphics.Color;
import android.os.Handler;

import java.util.Locale;

/**
 * Created by mario on 4/2/2017.
 */

class Timer {
	Handler timerHandler = new Handler();
	Runnable timerRunnable;


	int MS;
	public Timer() {
		timerRunnable = new Runnable() {
			long startTime = System.currentTimeMillis();
			@Override
			public void run() {
				MS = (int) (System.currentTimeMillis() - startTime);
				timerHandler.postDelayed(this, 10);
			}
		};
	}
	public void start() {
		timerHandler.postDelayed(timerRunnable, 0);
	}
	public void resume() {
		timerHandler.postDelayed(timerRunnable, 0);
	}
	public void pause() {
		timerHandler.removeCallbacks(timerRunnable);
	}
	public int getMS() {
		return MS;
	}
	public void reInitTimer() {
		timerRunnable = new Runnable() {
			long startTime = System.currentTimeMillis();
			@Override
			public void run() {
				MS = (int) (System.currentTimeMillis() - startTime);
				timerHandler.postDelayed(this, 10);
			}
		};
	}

	/**
	 *
	 * @return H:MM:SS.mmm
	 */
	public String toString() {
		String out = "";
		int totalSeconds = MS / 1000;
		int totalMinutes = totalSeconds / 60;
		int totalHours = totalMinutes / 60;
		int millis = MS % 1000;
		int seconds = totalSeconds % 60;
		int minutes = totalMinutes % 60;
		if(totalHours > 0) {
			out += "" + totalHours + ":";
		}
		if(totalMinutes > 0) {
			if(totalHours == 0) {
				out += "" + minutes + ":";
			} else {
				out += String.format(Locale.ENGLISH, "%02d", minutes) + ":";
			}
		}
		if(totalMinutes == 0) {
			out += "" + seconds + ".";
		} else {
			out += "" + String.format(Locale.ENGLISH, "%02d", seconds) + ".";
		}
		out += "" + String.format(Locale.ENGLISH, "%03d", millis);
		return out;
	}
}
