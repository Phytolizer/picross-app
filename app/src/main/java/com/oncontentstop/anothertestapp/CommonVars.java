package com.oncontentstop.anothertestapp;

import android.widget.RelativeLayout;

/**
 * Created by mario on 2/7/2017.
 */

public class CommonVars {
	public static Timer timer;
	public static void makeTimer(Background background, RelativeLayout layout) {
		timer = new Timer(background, layout);
	}
	public static Timer getTimer() {
		return timer;
	}
}
