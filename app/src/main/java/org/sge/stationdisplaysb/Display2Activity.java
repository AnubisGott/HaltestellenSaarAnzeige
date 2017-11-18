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

public class Display2Activity extends DisplayActivity implements GestureDetector.OnGestureListener {
    public final static int INIT_STATION_ID = 10600;
    public final static String DISPLAY_NUMBER = "D2";
    private Button refreshButton;
    private Button vorwaertsButton;
    private Button rueckwaertsButton;
    private GestureDetectorCompat mDetector = null;


    public Display2Activity() {
        super(INIT_STATION_ID, DISPLAY_NUMBER, 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);

        //if(!isPortraitOrientation())removeRows(R.id.tableLayoutSDSBD2);
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


    public void onRadioButtonClickedD2(View view) {
        System.out.println("radio button nur Bahn oder alle clicked D2");

        resetTimer();
        Settings.getInstance().saveSettings(this, getActivityNumber(), isAlleChecked());
        BahnRequest.createBahnRequest(getApplicationContext(), this, isAlleChecked(), getStationIdInt());
    }


    public boolean isAlleChecked() {
        final RadioButton radioButtonAlle = (RadioButton) findViewById(R.id.radioButtonAlleD2);
        System.out.println("radioButtonAlleD2.isChecked(): " + radioButtonAlle.isChecked());
        return radioButtonAlle.isChecked();
    }


    private void initRefreshButton() {
        refreshButton = (Button) findViewById(R.id.refreshButtonD2);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("refresh button D2 pressed");
                BahnRequest.createBahnRequest(getApplicationContext(), displayActivity, isAlleChecked(), getStationIdInt());
            }
        });
   }


    private void initVorwaertsButton() {
        vorwaertsButton = (Button) findViewById(R.id.buttonNextD2);
        vorwaertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display2Activity.this, Display3Activity.class));
            }
        });
    }


    private void initRueckwaertsButton() {
        rueckwaertsButton = (Button) findViewById(R.id.buttonBackD2);
        rueckwaertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display2Activity.this, MainActivity.class));
                //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left); // forward
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
            startActivity(new Intent(Display2Activity.this, Display3Activity.class));
            return false; // Right to left
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            System.out.println("onFling left to right");
            startActivity(new Intent(Display2Activity.this, MainActivity.class));
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