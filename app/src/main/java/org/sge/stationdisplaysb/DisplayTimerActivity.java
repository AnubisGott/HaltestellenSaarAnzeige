package org.sge.stationdisplaysb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sge.bahn.BahnRequest;
import sge.bahn.ParseHTML;
import sge.bahn.Settings;
import sge.bahn.UI_StationDisplay;
import sge.bahn.Util;

/**
 * Created by Admin on 03.11.2017.
 */

public abstract class DisplayTimerActivity extends AppCompatActivity {
    //--- attributes ----------------------
    protected final DisplayTimerActivity displayTimerActivity = this;
    public static final int REFRESH_INTERVAL = 30;
    private static String letzteAktualisierungGlobal = null;

    private int stationIdInt;
    private int activityNumber;
    private String displayNumber = "";
    static private DisplayTimerActivity currentActivity = null;
    public final static int countActivities = 4;
    static private List<DisplayTimerActivity> activityList = new ArrayList<DisplayTimerActivity>(countActivities);


    //--- abstract methods ----------------------
    abstract public void sendResponse(ParseHTML parser);
    abstract public boolean isAlleChecked();


    //--- class methods ---------------------------
    public DisplayTimerActivity(int stationIdIntPara, String displayNumberPara, int activityNumberPara) {
        super();

        stationIdInt = stationIdIntPara;
        displayNumber = displayNumberPara;
        activityNumber = activityNumberPara;
    }

    public int getStationIdInt() { return stationIdInt; }
    public void setStationIdInt(int id) { stationIdInt = id; }
    public String getDisplayNumber() { return displayNumber; }
    public int getActivityNumber() { return activityNumber; }

    public String getLastRefresh() {
        return letzteAktualisierungGlobal;
    }

    protected void setLastRefresh() {
        letzteAktualisierungGlobal = Util.getTimeStamp();
    }

    long startTime = 0;
    TextView timerTextView = null;

    public int getCountDown() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);

        int countDownTime = REFRESH_INTERVAL - seconds;
        if(countDownTime < 0) countDownTime = 0;

        return countDownTime;
    }


    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }


    public void resetTimer() {
        startTime = System.currentTimeMillis();
    }

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            // timerTextView.setText(String.format("%02d", seconds));
            new UI_StationDisplay().fillLetzteAktualisierung(displayTimerActivity, displayNumber);

            if(seconds > REFRESH_INTERVAL) {
                resetTimer();
                BahnRequest.createBahnRequest(getApplicationContext(), displayTimerActivity, isAlleChecked(), stationIdInt);
            }

            timerHandler.postDelayed(this, 500);
        }
    };


    static public void setCurrentDisplayActivity(DisplayTimerActivity activity) {
        System.out.println("set current display activity: " + activity);
        currentActivity = activity;
    }


    static public DisplayTimerActivity getCurrentDisplayActivity() {
        return currentActivity;
    }


    private void setRadioButtonVerkehrsart(boolean nurBahn) {
        RadioButton radioButtonNurBahn  = (RadioButton) findViewById(Util.getResourceID("radioButtonNurBahn" + displayNumber, "id", getApplicationContext()));
        RadioButton radioButtonAlle     = (RadioButton) findViewById(Util.getResourceID("radioButtonAlle"    + displayNumber, "id", getApplicationContext()));


        if(nurBahn) {
            radioButtonNurBahn.setChecked(true);
            radioButtonAlle.setChecked(false);
        }
        else {
            radioButtonNurBahn.setChecked(false);
            radioButtonAlle.setChecked(true);
        }
    }


    protected void setStationNameOnUI() {
        TextView name  = (TextView) findViewById(Util.getResourceID("textView1" + displayNumber, "id", getApplicationContext()));
        name.setText(Settings.getInstance().getStationName(this.getActivityNumber()));
    }

    protected void loadSettings() {
        Settings.getInstance().loadSettings(this);

        setStationIdInt(Settings.getInstance().getStationId(this.getActivityNumber()));
        setRadioButtonVerkehrsart(Settings.getInstance().getNurBahn(this.getActivityNumber()));
        setStationNameOnUI();
    }}
