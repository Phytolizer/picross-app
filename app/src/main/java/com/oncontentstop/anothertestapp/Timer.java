package com.oncontentstop.anothertestapp;

import android.graphics.Color;
import android.os.Handler;
import android.widget.RelativeLayout;

/**
 * @author onContentStop
 */

public class Timer {
	Handler timerHandler = new Handler();
	Runnable timerRunnable;
	RelativeLayout layout;
	Background background;
	public Timer(final Background bg, final RelativeLayout relativeLayout) {
		layout = relativeLayout;
		background = bg;
		timerRunnable = new Runnable() {
			long startTime = System.currentTimeMillis();
			@Override
			public void run() {
				long MS = System.currentTimeMillis() - startTime;
				background.updateColor((int) MS);
				if(MS > 10000) {
					startTime = System.currentTimeMillis();
					background.resetColor();
				}
				int[] currColor = background.getCurrColor();
				layout.setBackgroundColor(Color.rgb(currColor[0], currColor[1], currColor[2]));

				timerHandler.postDelayed(this, 10);
			}
		};
	}
	public void start() {
		timerHandler.postDelayed(timerRunnable, 0);
	}
	public void pause() {
		timerHandler.removeCallbacks(timerRunnable);
	}
	public void setLayout(RelativeLayout relativeLayout) {
		layout = relativeLayout;
	}
}
