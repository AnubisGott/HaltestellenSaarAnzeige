package org.sge.stationdisplaysb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import sge.bahn.BahnRequest;
import sge.bahn.Settings;


public class MainActivity extends DisplayActivity implements GestureDetector.OnGestureListener {
    public final static int INIT_STATION_ID = 15537;

    private Button vorwaertsButton;
    private Button refreshButton;
    private final MainActivity mainActiviy = this;
    private GestureDetectorCompat mDetector = null;

    public MainActivity() {
        super(INIT_STATION_ID,"", 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSettings();

        // System.out.println("createBahnRequest in onCreate");
        // already called in onResume BahnRequest.createBahnRequest(getApplicationContext(), this, isAlleChecked(), STATION_ID);

        initRefreshButton();
        initChangeStationButton();
        initVorwaertsButton();
        startTimer();
        //if(!isPortraitOrientation())removeRows(R.id.tableLayoutSDSB);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
    }


    @Override
    protected void onStop () {
        super.onStop();
        BahnRequest.cancelAll(this.getApplicationContext());
    }


    @Override
    public void onResume() {
        super.onResume();
        DisplayTimerActivity.setCurrentDisplayActivity(this);
        System.out.println("createBahnRequest in onResume");
        loadSettings();
        BahnRequest.createBahnRequest(getApplicationContext(), this, isAlleChecked(), getStationIdInt());
        startTimer();
    }


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        System.out.println("onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState (Bundle outState) {
        System.out.println("onRestoreInstanceState");
    }


    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }


    private void initRefreshButton() {
        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("refresh button pressed");
                System.out.println("createBahnRequest in clickedRefreshButton");
                BahnRequest.createBahnRequest(getApplicationContext(), mainActiviy, isAlleChecked(), getStationIdInt());
            }
        });
    }



/*
    private void initChangeStationButton() {
        refreshButton = (Button) findViewById(R.id.changeStationButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("change station button pressed");
                BahnRequest.createSuggestionRequest(getApplicationContext(), mainActiviy, isAlleChecked(), STATION_ID);
            }
        });
    }
*/


    private void initVorwaertsButton() {
        vorwaertsButton = (Button) findViewById(R.id.buttonNext);
        vorwaertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("vorwaerts button pressed");
                startActivity(new Intent(MainActivity.this, Display2Activity.class));
            }
        });
    }


    public boolean isAlleChecked() {
        final RadioButton radioButtonAlle = (RadioButton)findViewById(R.id.radioButtonAlle);
        System.out.println("radioButtonAlle.isChecked(): " + radioButtonAlle.isChecked());
        return radioButtonAlle.isChecked();
    }


    public void onRadioButtonClicked(View view) {
        System.out.println("radio button nur Bahn oder alle clicked");

        resetTimer();
        Settings.getInstance().saveSettings(this, getActivityNumber(), isAlleChecked());
        System.out.println("createBahnRequest in onRadioButtonClicked");
        BahnRequest.createBahnRequest(getApplicationContext(), mainActiviy, isAlleChecked(), getStationIdInt());
    }


    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                System.out.println("touch event sge");
                BahnRequest.createBahnRequest(getApplicationContext(), this);

                return true;
            case (MotionEvent.ACTION_MOVE):
                // Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                // Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                // Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                // Log.d(DEBUG_TAG,"Movement occurred outside bounds of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
    */

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // System.out.println("onFling: " + e1.toString() + e2.toString());

        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            System.out.println("onFling right to left");
            startActivity(new Intent(MainActivity.this, Display2Activity.class));
            return false; // Right to left
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            System.out.println("onFling left to right");
            return false; // Left to right
        }

        if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            return false; // Bottom to top
        }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            return false; // Top to bottom
        }
        return false;
    }

}

