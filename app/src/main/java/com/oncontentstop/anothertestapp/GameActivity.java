package com.oncontentstop.anothertestapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mario on 3/24/2017.
 */

public class GameActivity extends AppCompatActivity {
	private Handler mHandler;
	private RelativeLayout coverLayout;
	private TextView timerText;
	private RelativeLayout pauseLayout, failLayout, succeedLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout gameLayout = new RelativeLayout(this);
		CommonVars.getBackgroundBackgroundTimer().setLayout(gameLayout);

		int deviceWidth = getResources().getDisplayMetrics().widthPixels;
		int deviceHeight = getResources().getDisplayMetrics().heightPixels;
		int pauseButtonHeight = toDp(50);
		int bottomBarHeight = toDp(75);
		int testWindowMaxHeight = deviceHeight - pauseButtonHeight - bottomBarHeight;

		RelativeLayout.LayoutParams gridDetails = new RelativeLayout.LayoutParams(deviceWidth, testWindowMaxHeight);
		gridDetails.setMargins(0, pauseButtonHeight, 0, bottomBarHeight);
		gridDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		gridDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		RelativeLayout.LayoutParams pauseButtonDetails = new RelativeLayout.LayoutParams(toDp(65), toDp(65));
		pauseButtonDetails.setMargins(3, 3, 0, 0);
		pauseButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		pauseButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		RelativeLayout.LayoutParams mistakeCounterDetails = new RelativeLayout.LayoutParams(
				toDp(250),
				toDp(60)
		);
		mistakeCounterDetails.setMargins(toDp(5), 0, 0, toDp(5));
		mistakeCounterDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		RelativeLayout.LayoutParams coverLayoutDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT
		);

		RelativeLayout.LayoutParams timerDetails = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT
		);
		timerDetails.setMargins(0, 0, toDp(5), toDp(5));
		timerDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		timerDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		RelativeLayout.LayoutParams modeButtonDetails = new RelativeLayout.LayoutParams(
				toDp(75),
				toDp(60)
		);
		modeButtonDetails.setMargins(toDp(5), 0, 0, toDp(5));
		modeButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		modeButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);


		GameGrid grid = new GameGrid(this, CommonVars.sizeX(), CommonVars.sizeY());
		gameLayout.addView(grid, gridDetails);

		/*Button pauseButton = new Button(this);
		pauseButton.setTextSize(toDp(10));
		pauseButton.setText(R.string.pauseButtonText);
		pauseButton.setBackgroundColor(Color.YELLOW);
		pauseButton.setTextColor(Color.BLACK);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaused(true);
			}
		});*/

		MyButton pause = new MyButton(this);
		pause.setBackgroundColor(0xffffff00);
		pause.setText("||");
		pause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaused(true);
			}
		});

//		gameLayout.addView(pauseButton, pauseButtonDetails);
		gameLayout.addView(pause, pauseButtonDetails);

		MistakeCounter mistakeCounter = new MistakeCounter(this);
		grid.setTiedCounter(mistakeCounter);


		timerText = new TextView(this);
		timerText.setTextSize(35);
		timerText.setTextColor(0xff000000);
		timerText.setText("");

		gameLayout.addView(timerText, timerDetails);

		final MyButton modeButton = new MyButton(this);
		modeButton.setText("Pencil");
		modeButton.setId(1);
		modeButton.setBackgroundColor(Color.WHITE);
		modeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CommonVars.toggleControlMode();
				if(CommonVars.getControlMode() == ControlMode.NORMAL) {
					modeButton.setText("Pencil");
					modeButton.invalidate();
				} else {
					modeButton.setText("Pen");
					modeButton.invalidate();
				}
			}
		});

		mistakeCounterDetails.addRule(RelativeLayout.RIGHT_OF, modeButton.getId());
		gameLayout.addView(mistakeCounter, mistakeCounterDetails);

		gameLayout.addView(modeButton, modeButtonDetails);

		coverLayout = new RelativeLayout(this);
		coverLayout.setBackgroundColor(0x66000000);
		coverLayout.setVisibility(View.INVISIBLE);
		{
			RelativeLayout.LayoutParams pauseLayoutDetails = new RelativeLayout.LayoutParams(deviceWidth / 5, deviceHeight / 5);
			pauseLayoutDetails.addRule(RelativeLayout.CENTER_VERTICAL);
			pauseLayoutDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

			pauseLayout = new RelativeLayout(this);
			pauseLayout.setBackgroundColor(Color.WHITE);
			{
				RelativeLayout.LayoutParams pausedTextDetails = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT
				);
				pausedTextDetails.setMargins(0, toDp(15), 0, 0);
				pausedTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				pausedTextDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);

				TextView pausedText = new TextView(this);
				pausedText.setTextColor(Color.BLACK);
				pausedText.setTextSize(toDp(20));
				pausedText.setText(R.string.pausedText);

				pauseLayout.addView(pausedText, pausedTextDetails);

				RelativeLayout.LayoutParams resumeButtonDetails = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						deviceHeight / 5 - toDp(10) - deviceHeight / 10
				);
				resumeButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				resumeButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

				MyButton resumeButton = new MyButton(this);
				resumeButton.setBackgroundColor(Color.GREEN);
				resumeButton.setText("Resume");
				resumeButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						setPaused(false);
					}
				});

				pauseLayout.addView(resumeButton, resumeButtonDetails);
			}//pause layout

			coverLayout.addView(pauseLayout, pauseLayoutDetails);

			RelativeLayout.LayoutParams failLayoutDetails = new RelativeLayout.LayoutParams(
					deviceWidth / 4,
					deviceHeight / 4
			);
			failLayoutDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
			failLayoutDetails.addRule(RelativeLayout.CENTER_VERTICAL);

			failLayout = new RelativeLayout(this);
			failLayout.setBackgroundColor(Color.WHITE);
			{
				RelativeLayout.LayoutParams loseTextDetails = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT
				);
				loseTextDetails.setMargins(0, toDp(15), 0, 0);
				loseTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				loseTextDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);

				TextView loseText = new TextView(this);
				loseText.setTextColor(0xffaa0000);//red
				loseText.setTextSize(toDp(30));
				loseText.setText(R.string.failedText);

				failLayout.addView(loseText, loseTextDetails);

				RelativeLayout.LayoutParams retryButtonDetails = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						deviceHeight / 12
				);
				retryButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				retryButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

				MyButton retryButton = new MyButton(this);
				retryButton.setBackgroundColor(Color.GREEN);
				retryButton.setText("Try Again");
				retryButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onBackPressed();
					}
				});
				failLayout.addView(retryButton, retryButtonDetails);
			}//fail layout
			coverLayout.addView(failLayout, failLayoutDetails);

			RelativeLayout.LayoutParams succeedLayoutDetails = new RelativeLayout.LayoutParams(
					deviceWidth / 4,
					deviceHeight / 4
			);
			succeedLayoutDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
			succeedLayoutDetails.addRule(RelativeLayout.CENTER_VERTICAL);

			succeedLayout = new RelativeLayout(this);
			succeedLayout.setBackgroundColor(Color.WHITE);
			{
				RelativeLayout.LayoutParams winTextDetails = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT
				);
				winTextDetails.setMargins(0, toDp(15), 0, 0);
				winTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				winTextDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);

				TextView winText = new TextView(this);
				winText.setTextColor(0xff00aa00);//green
				winText.setTextSize(toDp(30));
				winText.setText(R.string.succeededText);

				succeedLayout.addView(winText, winTextDetails);

				RelativeLayout.LayoutParams retryButtonDetails = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						deviceHeight / 12
				);
				retryButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
				retryButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

				MyButton retryButton = new MyButton(this);
				retryButton.setBackgroundColor(Color.GREEN);
				retryButton.setText("Another!");
				retryButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onBackPressed();
					}
				});
				succeedLayout.addView(retryButton, retryButtonDetails);
			}//succeed layout
			coverLayout.addView(succeedLayout, succeedLayoutDetails);
		}//all layouts that cover the game screen

		gameLayout.addView(coverLayout, coverLayoutDetails);


		setContentView(gameLayout);

		mHandler = new Handler();
		mHandler.postDelayed(m_Runnable, 10);
		CommonVars.getBackgroundBackgroundTimer().start();
	}

	private final Runnable m_Runnable = new Runnable() {
		public void run() {
			mHandler.postDelayed(m_Runnable, 10);
			timerText.setText(CommonVars.getTime());
			switch (CommonVars.getGameStatus()) {
				case FAILED:
					succeedLayout.setVisibility(View.INVISIBLE);
					failLayout.setVisibility(View.VISIBLE);
					pauseLayout.setVisibility(View.INVISIBLE);
					coverLayout.setVisibility(View.VISIBLE);
					break;
				case SUCCEEDED:
					succeedLayout.setVisibility(View.VISIBLE);
					failLayout.setVisibility(View.INVISIBLE);
					pauseLayout.setVisibility(View.INVISIBLE);
					coverLayout.setVisibility(View.VISIBLE);
					break;
				case IN_PROGRESS:
					if(!CommonVars.isPaused())
						coverLayout.setVisibility(View.INVISIBLE);
					break;
			}
		}
	};

	public void setPaused(boolean pause) {
		CommonVars.setPaused(pause);
		if(pause) {
			pauseLayout.setVisibility(View.VISIBLE);
			failLayout.setVisibility(View.INVISIBLE);
			succeedLayout.setVisibility(View.INVISIBLE);
			coverLayout.setVisibility(View.VISIBLE);
		} else {
			coverLayout.setVisibility(View.INVISIBLE);
		}
	}
	private int toDp(int px) {
		return (int) ((float)px/* * getResources().getDisplayMetrics().scaledDensity*/);
	}

	@Override
	public void onBackPressed() {
		if(CommonVars.isPaused()) {
			setPaused(false);
		} else {
			super.onBackPressed();
		}
	}
}
