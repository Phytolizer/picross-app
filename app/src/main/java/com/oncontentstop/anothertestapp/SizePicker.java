package com.oncontentstop.anothertestapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SizePicker extends AppCompatActivity {

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


		//display elements
		TextView title = new TextView(this);
		title.setText(R.string.sizePickerTitle);
		title.setTextSize(50);
		title.setId(1);
		title.setTextColor(Color.BLACK);

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

}
