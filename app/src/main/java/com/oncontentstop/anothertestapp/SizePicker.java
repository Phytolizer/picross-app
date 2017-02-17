package com.oncontentstop.anothertestapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
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
		CommonVars.getTimer().setLayout(sizePickerLayout);
		sizePickerLayout.setBackgroundColor(Color.WHITE);

		//positioning rules
		RelativeLayout.LayoutParams titleDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		titleDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		titleDetails.addRule(RelativeLayout.ALIGN_TOP);
		titleDetails.setMargins(0, 50, 0, 0);

		RelativeLayout.LayoutParams backButtonDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		backButtonDetails.addRule(RelativeLayout.ALIGN_TOP);
		backButtonDetails.addRule(RelativeLayout.ALIGN_LEFT);
		backButtonDetails.setMargins(40, 40, 0, 0);

		RelativeLayout.LayoutParams xSizeDetails = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		RelativeLayout.LayoutParams innerLayoutDetails = new RelativeLayout.LayoutParams(500, 300);
		innerLayoutDetails.setMargins(0, 30, 0, 0);

		RelativeLayout.LayoutParams startButtonDetails = new RelativeLayout.LayoutParams(150, 80);
		startButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		startButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		startButtonDetails.setMargins(0, 0, 0, 50);




		//display elements
		TextView title = new TextView(this);
		title.setText(R.string.sizePickerTitle);
		title.setTextSize(toDp(50));
		title.setId(1);
		title.setTextColor(Color.BLACK);
		innerLayoutDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		innerLayoutDetails.addRule(RelativeLayout.BELOW, title.getId());

		Button backButton = new Button(this);
		backButton.setText(R.string.backButtonText);
		backButton.setTextSize(20);
		backButton.setBackgroundColor(Color.RED);
		backButton.setTextColor(Color.BLACK);
		backButton.setId(2);
		backButton.setWidth(100);
		backButton.setHeight(100);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SizePicker.this, MainActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i);
			}
		});

		innerLayout = new RelativeLayout(this);
		innerLayout.setId(3);
		innerLayout.setBackgroundColor(Color.alpha(0));
		sizePickerLayout.addView(innerLayout, innerLayoutDetails);

		//region size picker elements
		{
			buttonWidth = 150;
			buttonHeight = 80;
			RelativeLayout.LayoutParams incXDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			incXDetails.addRule(RelativeLayout.ALIGN_TOP);
			incXDetails.addRule(RelativeLayout.ALIGN_LEFT);

			RelativeLayout.LayoutParams incYDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			incYDetails.addRule(RelativeLayout.ALIGN_TOP);
			incYDetails.setMargins(500 - (2 * buttonWidth), 0, 0, 0);

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
			yTextDetails.setMargins(500 - (2 * buttonWidth), 0, 0, 0);

			RelativeLayout.LayoutParams decXDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			decXDetails.addRule(RelativeLayout.ALIGN_LEFT);
			decXDetails.setMargins(0, 300 - 2 * buttonHeight, 0, 0);

			RelativeLayout.LayoutParams decYDetails = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
			decYDetails.setMargins(500 - 2 * buttonWidth, 300 - 2 * buttonHeight, 0, 0);

			incrementX = new Button(this);
			incrementX.setBackgroundColor(Color.WHITE);
			incrementX.setId(1);
			incrementX.setText("▲");
			incrementX.setTextSize(toDp(50));
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
			incrementY.setTextSize(toDp(50));
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
			xText.setTextSize(toDp(buttonHeight));
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
			yText.setTextSize(toDp(buttonHeight));
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
			decrementX.setTextSize(toDp(50));
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
			decrementY.setTextSize(toDp(50));
			decrementY.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonVars.decrementSizeY();
					updateSize();
				}
			});
			innerLayout.addView(decrementY, decYDetails);

		}
		//endregion

		Button startButton = new Button(this);
		startButton.setId(4);
		startButton.setBackgroundColor(Color.GREEN);
		startButton.setText(R.string.playButtonText);
		startButton.setTextSize(toDp(50));
		startButton.setTextColor(Color.BLACK);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		sizePickerLayout.addView(startButton, startButtonDetails);


		//add display elements
		sizePickerLayout.addView(title, titleDetails);
		sizePickerLayout.addView(backButton, backButtonDetails);

		//show final product
		setContentView(sizePickerLayout);

		//start changing background colors
		CommonVars.getTimer().start();


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
		Log.i("Pillsbury", String.valueOf(xTextWidth));
		yText.measure(0, 0);
		yTextWidth = yText.getMeasuredWidth();
		Log.i("Pillsbury", String.valueOf(yTextWidth));
		innerLayout.removeView(yText);
		innerLayout.removeView(xText);
		xTextDetails.setMargins(((buttonWidth - xTextWidth) / 2), 0, 0, 0);
		yTextDetails.setMargins(((buttonWidth - xTextWidth) / 2) + 500 - 2 * buttonWidth + ((buttonWidth - yTextWidth) / 2), 0, 0, 0);
		innerLayout.addView(yText, yTextDetails);
		innerLayout.addView(xText, xTextDetails);
	}

	private int toDp(int px) {
		return (int) ((float)px / getResources().getDisplayMetrics().density);
	}

}
