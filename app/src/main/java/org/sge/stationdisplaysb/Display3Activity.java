package org.sge.stationdisplaysb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import sge.bahn.BahnRequest;
import sge.bahn.Settings;

public class Display3Activity extends DisplayActivity implements GestureDetector.OnGestureListener {
    public final static int INIT_STATION_ID = 10603;
    public final static String DISPLAY_NUMBER = "D3";
    private Button refreshButton;
    private Button vorwaertsButton;
    private Button rueckwaertsButton;
    private GestureDetectorCompat mDetector = null;

    public Display3Activity() {
        super(INIT_STATION_ID, DISPLAY_NUMBER, 2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display3);

        //if(!isPortraitOrientation())removeRows(R.id.tableLayoutSDSBD3);
        loadSettings();
        // already done in onResume BahnRequest.createBahnRequest(getApplicationContext(), this, isAlleChecked(), STATION_ID);
        initRefreshButton();
        initChangeStationButton();
        initVorwaertsButton();
        initRueckwaertsButton();
        startTimer();
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


    public boolean isAlleChecked() {
        final RadioButton radioButtonAlle = (RadioButton) findViewById(R.id.radioButtonAlleD3);
        return radioButtonAlle.isChecked();
    }


    public void onRadioButtonClickedD3(View view) {
        resetTimer();
        Settings.getInstance().saveSettings(this, getActivityNumber(), isAlleChecked());
        BahnRequest.createBahnRequest(getApplicationContext(), this, isAlleChecked(), getStationIdInt());
    }


    private void initRefreshButton() {
        refreshButton = (Button) findViewById(R.id.refreshButtonD3);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BahnRequest.createBahnRequest(getApplicationContext(), displayActivity, isAlleChecked(), getStationIdInt());
            }
        });
    }


    private void initVorwaertsButton() {
        vorwaertsButton = (Button) findViewById(R.id.buttonNextD3);
        vorwaertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display3Activity.this, Display4Activity.class));
            }
        });
    }


    private void initRueckwaertsButton() {
        rueckwaertsButton = (Button) findViewById(R.id.buttonBackD3);
        rueckwaertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display3Activity.this, Display2Activity.class));
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right); // backward
            }
        });
    }
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
            startActivity(new Intent(Display3Activity.this, Display4Activity.class));
            return false; // Right to left
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            System.out.println("onFling left to right");
            startActivity(new Intent(Display3Activity.this, Display2Activity.class));
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right); // backward
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