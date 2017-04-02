package com.oncontentstop.anothertestapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SizePicker extends AppCompatActivity {

	private TextView xText, yText;
	private Button incrementX, incrementY;
	private Button decrementX, decrementY;
	private RelativeLayout.LayoutParams xTextDetails, yTextDetails;
	private int xTextWidth, yTextWidth;
	private int buttonHeight, buttonWidth;
	private RelativeLayout innerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Background background = new Background();
		RelativeLayout sizePickerLayout = new RelativeLayout(this);
		CommonVars.getBackgroundBackgroundTimer().setLayout(sizePickerLayout);
		sizePickerLayout.setBackgroundColor(Color.WHITE);

		//positioning rules
		RelativeLayout.LayoutParams titleDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		titleDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		titleDetails.addRule(RelativeLayout.ALIGN_TOP);
		titleDetails.setMargins(0, toDp(20), 0, 0);

		RelativeLayout.LayoutParams xSizeDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		RelativeLayout.LayoutParams innerLayoutDetails = new RelativeLayout.LayoutParams(toDp(250), toDp(200));
		innerLayoutDetails.setMargins(0, toDp(12), 0, 0);

		RelativeLayout.LayoutParams startButtonDetails = new RelativeLayout.LayoutParams(toDp(120), toDp(60));
		startButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		startButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		startButtonDetails.setMargins(0, 0, 0, toDp(20));




		//display elements
		TextView title = new TextView(this);
		title.setText(R.string.sizePickerTitle);
		title.setTextSize(toDp(20));
		title.setId(1);
		title.setTextColor(Color.BLACK);
		innerLayoutDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		innerLayoutDetails.addRule(RelativeLayout.BELOW, title.getId());


		innerLayout = new RelativeLayout(this);
		innerLayout.setId(3);
		innerLayout.setBackgroundColor(Color.alpha(0));
		sizePickerLayout.addView(innerLayout, innerLayoutDetails);

		//region size picker elements
		{
			buttonWidth = toDp(80);
			buttonHeight = toDp(50);
			RelativeLayout.LayoutParams incXDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			incXDetails.addRule(RelativeLayout.ALIGN_TOP);
			incXDetails.addRule(RelativeLayout.ALIGN_LEFT);

			RelativeLayout.LayoutParams incYDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			incYDetails.addRule(RelativeLayout.ALIGN_TOP);
			incYDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			incYDetails.setMargins(toDp(250) - (2 * buttonWidth), 0, 0, 0);

			xTextDetails = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
			);
//			xTextDetails.addRule(RelativeLayout.ALIGN_LEFT);
			xTextDetails.addRule(RelativeLayout.CENTER_VERTICAL);

			yTextDetails = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
			);
			yTextDetails.addRule(RelativeLayout.CENTER_VERTICAL);
			yTextDetails.setMargins(toDp(250) - (2 * buttonWidth), 0, 0, 0);

			RelativeLayout.LayoutParams decXDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			decXDetails.addRule(RelativeLayout.ALIGN_LEFT);
			decXDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			decXDetails.setMargins(0, toDp(200) - buttonHeight * 2, 0, 0);

			RelativeLayout.LayoutParams decYDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			decYDetails.setMargins(toDp(250) - 2 * buttonWidth, toDp(200) - 2 * buttonHeight, 0, 0);

			incrementX = new Button(this);
			incrementX.setBackgroundColor(Color.WHITE);
			incrementX.setId(1);
			incrementX.setText("▲");
			incrementX.setTextSize(toDp(10));
			incrementX.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonVars.incrementSizeX();
					updateSize();
				}
			});
			innerLayout.addView(incrementX, incXDetails);
			incYDetails.addRule(RelativeLayout.RIGHT_OF, incrementX.getId());
			xTextDetails.addRule(RelativeLayout.CENTER_VERTICAL, incrementX.getId());
			decXDetails.addRule(RelativeLayout.BELOW, incrementX.getId());

			incrementY = new Button(this);
			incrementY.setBackgroundColor(Color.WHITE);
			incrementY.setId(2);
			incrementY.setText("▲");
			incrementY.setTextSize(toDp(10));
			incrementY.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonVars.incrementSizeY();
					updateSize();
				}
			});
			innerLayout.addView(incrementY, incYDetails);
			decYDetails.addRule(RelativeLayout.BELOW, incrementY.getId());

			xText = new TextView(this);
			xText.setId(3);
			xText.setText(Integer.toString(CommonVars.sizeX()));
			xText.setTextSize(toDp(20));
			xText.setTextColor(Color.BLACK);
			xText.measure(0, 0);
			xTextWidth = xText.getMeasuredWidth();
			Log.i("", Integer.toString(xTextWidth));
			xTextDetails.setMargins(((buttonWidth - xTextWidth) / 2), 0, 0, 0);
			yTextDetails.addRule(RelativeLayout.RIGHT_OF, xText.getId());
			innerLayout.addView(xText, xTextDetails);

			yText = new TextView(this);
			yText.setId(4);
			yText.setText(Integer.toString(CommonVars.sizeY()));
			yText.setTextSize(toDp(20));
			yText.setTextColor(Color.BLACK);
			yText.measure(0, 0);
			yTextWidth = yText.getMeasuredWidth();
			Log.i("", Integer.toString(yTextWidth));
			yTextDetails.setMargins(((buttonWidth - xTextWidth) / 2) + 500 - 2 * buttonWidth + ((buttonWidth - yTextWidth) / 2), 0, 0, 0);
			innerLayout.addView(yText, yTextDetails);

			decrementX = new Button(this);
			decrementX.setId(5);
			decrementX.setBackgroundColor(Color.WHITE);
			decrementX.setText("▼");
			decrementX.setTextSize(toDp(10));
			decrementX.setTextColor(Color.BLACK);
			decrementX.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonVars.decrementSizeX();
					updateSize();
				}
			});
			innerLayout.addView(decrementX, decXDetails);
			decYDetails.addRule(RelativeLayout.RIGHT_OF, decrementX.getId());

			decrementY = new Button(this);
			decrementY.setId(6);
			decrementY.setBackgroundColor(Color.WHITE);
			decrementY.setText("▼");
			decrementY.setTextSize(toDp(10));
			decrementY.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonVars.decrementSizeY();
					updateSize();
				}
			});
			innerLayout.addView(decrementY, decYDetails);

			updateSize();

		}
		//endregion

		Button startButton = new Button(this);
		startButton.setId(4);
		startButton.setBackgroundColor(Color.GREEN);
		startButton.setText(R.string.playButtonText);
		startButton.setTextSize(toDp(10));
		startButton.setTextColor(Color.BLACK);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CommonVars.setSizeX(Integer.parseInt((String) xText.getText()));
				CommonVars.setSizeY(Integer.parseInt((String) yText.getText()));
				CommonVars.resetGameTimer();
				CommonVars.setGameStatus(GameStatus.IN_PROGRESS);
				CommonVars.resetMistakes();
				if(CommonVars.getControlMode() == ControlMode.MARK) {
					CommonVars.toggleControlMode();
				}
				startActivity(new Intent(SizePicker.this, GameActivity.class));
			}
		});
		sizePickerLayout.addView(startButton, startButtonDetails);


		//add display elements
		sizePickerLayout.addView(title, titleDetails);

		//show final product
		setContentView(sizePickerLayout);

		//start changing background colors
		CommonVars.getBackgroundBackgroundTimer().start();


		/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
	}

	private void updateSize() {
		xText.setText(Integer.toString(CommonVars.sizeX()));
		yText.setText(Integer.toString(CommonVars.sizeY()));
		if(CommonVars.sizeX() == 25) {
			incrementX.setVisibility(View.INVISIBLE);
		} else {
			incrementX.setVisibility(View.VISIBLE);
		}
		if(CommonVars.sizeY() == 25) {
			incrementY.setVisibility(View.INVISIBLE);
		} else {
			incrementY.setVisibility(View.VISIBLE);
		}
		if(CommonVars.sizeX() == 1) {
			decrementX.setVisibility(View.INVISIBLE);
		} else {
			decrementX.setVisibility(View.VISIBLE);
		}
		if(CommonVars.sizeY() == 1) {
			decrementY.setVisibility(View.INVISIBLE);
		} else {
			decrementY.setVisibility(View.VISIBLE);
		}
		xText.measure(0, 0);
		xTextWidth = xText.getMeasuredWidth();
		yText.measure(0, 0);
		yTextWidth = yText.getMeasuredWidth();
		innerLayout.removeView(yText);
		innerLayout.removeView(xText);
		xTextDetails.setMargins(((buttonWidth - xTextWidth) / 2), 0, 0, 0);
		yTextDetails.setMargins(((buttonWidth - xTextWidth) / 2) + toDp(250) - 2 * buttonWidth + ((buttonWidth - yTextWidth) / 2), 0, 0, 0);
		innerLayout.addView(yText, yTextDetails);
		innerLayout.addView(xText, xTextDetails);
	}

	private int toDp(int px) {
		return (int) ((float)px * getResources().getDisplayMetrics().scaledDensity);
	}

}
