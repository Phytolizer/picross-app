package com.oncontentstop.anothertestapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "picrossApp";
	private RelativeLayout mainActivityLayout;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        Background background = new Background();
        mainActivityLayout = new RelativeLayout(this);

	    CommonVars.makeTimer(background, mainActivityLayout);

        mainActivityLayout.setBackgroundColor(Color.WHITE);

	    RelativeLayout.LayoutParams titleDetails = new RelativeLayout.LayoutParams(
			    RelativeLayout.LayoutParams.WRAP_CONTENT,
			    RelativeLayout.LayoutParams.WRAP_CONTENT
	    );
	    titleDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    titleDetails.setMargins(0, 0, 0, 30);

        RelativeLayout.LayoutParams startGameDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        startGameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    startGameDetails.setMargins(0, 0, 0, 25);

	    RelativeLayout.LayoutParams dummy1Details = new RelativeLayout.LayoutParams(
			    RelativeLayout.LayoutParams.WRAP_CONTENT,
			    RelativeLayout.LayoutParams.WRAP_CONTENT
	    );
	    dummy1Details.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    dummy1Details.addRule(RelativeLayout.CENTER_VERTICAL);
	    dummy1Details.setMargins(0, 0, 0, 25);

	    RelativeLayout.LayoutParams dummy2Details = new RelativeLayout.LayoutParams(
			    RelativeLayout.LayoutParams.WRAP_CONTENT,
			    RelativeLayout.LayoutParams.WRAP_CONTENT
	    );
	    dummy2Details.addRule(RelativeLayout.CENTER_HORIZONTAL);

	    TextView picrossTitle = new TextView(this);
	    picrossTitle.setText(R.string.title);
	    picrossTitle.setId(1);
	    picrossTitle.setTextSize(50);
	    picrossTitle.setTextColor(Color.BLACK);

        final Button bStartGame = new Button(this);
	    bStartGame.setId(2);
	    bStartGame.setBackgroundColor(Color.GREEN);
	    bStartGame.setText(R.string.startButtonText);
	    bStartGame.setTextSize(20);
	    bStartGame.setWidth(400);
	    bStartGame.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
//			    Log.i(TAG, "User clicked " + R.string.startButtonText);
			    //say 'you did it!'
			    startActivity(new Intent(MainActivity.this, SizePicker.class));

		    }
	    });
	    titleDetails.addRule(RelativeLayout.ABOVE, bStartGame.getId());

	    Button bDummy1 = new Button(this);
	    bDummy1.setId(3);
	    bDummy1.setBackgroundColor(Color.YELLOW);
	    bDummy1.setText(R.string.button2Text);
	    bDummy1.setTextSize(20);
	    bDummy1.setWidth(400);
	    bDummy1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
//			    Log.i(TAG, "User clicked " + R.string.button2Text);
			    //do something
		    }
	    });
	    startGameDetails.addRule(RelativeLayout.ABOVE, bDummy1.getId());
	    dummy2Details.addRule(RelativeLayout.BELOW, bDummy1.getId());

	    Button bDummy2 = new Button(this);
	    bDummy2.setId(4);
	    bDummy2.setBackgroundColor(Color.BLUE);
	    bDummy2.setText(R.string.button3Text);
	    bDummy2.setTextSize(20);
	    bDummy2.setWidth(400);
	    bDummy2.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    //do something else
		    }
	    });

	    mainActivityLayout.addView(picrossTitle, titleDetails);
	    mainActivityLayout.addView(bStartGame, startGameDetails);
	    mainActivityLayout.addView(bDummy1, dummy1Details);
	    mainActivityLayout.addView(bDummy2, dummy2Details);
	    setContentView(mainActivityLayout);


	    CommonVars.getTimer().start();
    }

	@Override
	protected void onPause() {
		super.onPause();
		CommonVars.getTimer().pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		CommonVars.getTimer().start();
		CommonVars.getTimer().setLayout(mainActivityLayout);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
