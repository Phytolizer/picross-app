package com.oncontentstop.anothertestapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

	    CommonVars.makeBackgroundTimer(background, mainActivityLayout);

        mainActivityLayout.setBackgroundColor(Color.WHITE);

	    RelativeLayout.LayoutParams titleDetails = new RelativeLayout.LayoutParams(
			    RelativeLayout.LayoutParams.WRAP_CONTENT,
			    RelativeLayout.LayoutParams.WRAP_CONTENT
	    );
	    titleDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    titleDetails.setMargins(0, 0, 0, 30);

        RelativeLayout.LayoutParams startGameDetails = new RelativeLayout.LayoutParams(
                600,
                200
        );
        startGameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    startGameDetails.setMargins(0, 0, 0, 25);

	    RelativeLayout.LayoutParams leaderboardDetails = new RelativeLayout.LayoutParams(
			    600,
			    200
	    );
	    leaderboardDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    leaderboardDetails.addRule(RelativeLayout.CENTER_VERTICAL);
	    leaderboardDetails.setMargins(0, 0, 0, 25);

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

        final MyButton bStartGame = new MyButton(this);
	    bStartGame.setId(2);
	    bStartGame.setBackgroundColor(Color.GREEN);
	    bStartGame.setText("Start Game");
	    bStartGame.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
//			    Log.i(TAG, "User clicked " + R.string.startButtonText);
			    //say 'you did it!'
			    startActivity(new Intent(MainActivity.this, SizePicker.class));
		    }
	    });
	    titleDetails.addRule(RelativeLayout.ABOVE, bStartGame.getId());

	    MyButton bLeaderboard = new MyButton(this);
	    bLeaderboard.setId(3);
	    bLeaderboard.setBackgroundColor(Color.YELLOW);
	    bLeaderboard.setText("Leaderboard");
	    bLeaderboard.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
//			    Log.i(TAG, "User clicked " + R.string.button2Text);
			    //do something
			    String url = "https://www.westonreed.com/picross";
			    Intent i = new Intent(Intent.ACTION_VIEW);
			    i.setData(Uri.parse(url));
			    startActivity(i);
		    }
	    });
	    startGameDetails.addRule(RelativeLayout.ABOVE, bLeaderboard.getId());
	    dummy2Details.addRule(RelativeLayout.BELOW, bLeaderboard.getId());

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
		bDummy2.setVisibility(View.INVISIBLE);

	    mainActivityLayout.addView(picrossTitle, titleDetails);
	    mainActivityLayout.addView(bStartGame, startGameDetails);
	    mainActivityLayout.addView(bLeaderboard, leaderboardDetails);
	    mainActivityLayout.addView(bDummy2, dummy2Details);
	    setContentView(mainActivityLayout);


	    CommonVars.getBackgroundBackgroundTimer().start();
    }

	@Override
	protected void onPause() {
		super.onPause();
		CommonVars.getBackgroundBackgroundTimer().pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		CommonVars.getBackgroundBackgroundTimer().start();
		CommonVars.getBackgroundBackgroundTimer().setLayout(mainActivityLayout);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
