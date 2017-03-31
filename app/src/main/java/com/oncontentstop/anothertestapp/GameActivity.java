package com.oncontentstop.anothertestapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

/**
 * Created by mario on 3/24/2017.
 */

public class GameActivity extends AppCompatActivity {
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout gameLayout = new RelativeLayout(this);
		CommonVars.getTimer().setLayout(gameLayout);

		int deviceWidth = getResources().getDisplayMetrics().widthPixels;
		int deviceHeight = getResources().getDisplayMetrics().heightPixels;
		int pauseButtonHeight = 50;
		int bottomBarHeight = 50;
		int testWindowMaxHeight = deviceHeight - pauseButtonHeight - bottomBarHeight;
		RelativeLayout.LayoutParams testWindowDetails = new RelativeLayout.LayoutParams(deviceWidth, testWindowMaxHeight);
		testWindowDetails.setMargins(0, pauseButtonHeight, 0, bottomBarHeight);
		testWindowDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		testWindowDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		GameGrid testWindow = new GameGrid(this, CommonVars.sizeX(), CommonVars.sizeY());
		gameLayout.addView(testWindow, testWindowDetails);

		setContentView(gameLayout);

		mHandler = new Handler();
		mHandler.postDelayed(m_Runnable, 10);
		CommonVars.getTimer().start();
	}

	private final Runnable m_Runnable = new Runnable() {
		public void run() {
			mHandler.postDelayed(m_Runnable, 10);
		}
	};
}
