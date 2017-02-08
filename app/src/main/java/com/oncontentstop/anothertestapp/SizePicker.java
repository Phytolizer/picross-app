package com.oncontentstop.anothertestapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
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

		//display elements
		TextView title = new TextView(this);
		title.setText(R.string.sizePickerTitle);
		title.setTextSize(50);
		title.setId(1);
		title.setTextColor(Color.BLACK);

		//add display elements
		sizePickerLayout.addView(title, titleDetails);

		//show final product
		setContentView(sizePickerLayout);

		//start changing background colors
		CommonVars.getTimer().start();


		/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
	}

}
