package org.sge.stationdisplaysb;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;

import sge.bahn.ParseHTML;
import sge.bahn.UI_StationDisplay;
import sge.bahn.Util;

/**
 * Created by Admin on 03.11.2017.
 */

abstract public class DisplayActivity extends DisplayTimerActivity {
    //--- attributes ----------------------------
    protected final DisplayActivity displayActivity = this;
    private Button changeStationhButton = null;

    //--- class methods ---------------------------
    public DisplayActivity(int stationIdIntPara, String displayNumberPara, int activityNumber) {
        super(stationIdIntPara, displayNumberPara, activityNumber);
    }

    public boolean isPortraitOrientation() {
        return getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT;
    }

/*
    protected void removeRows(int viewId) {
        System.out.println("removeRows");
        //TableLayout tableLayout = (TableLayout)findViewById(Util.getResourceID("tableLayoutSDSB" + getDisplayNumber(), "id", getApplicationContext()));
        TableLayout tableLayout = (TableLayout)findViewById(viewId);

        TextView emptyRow1 = (TextView) findViewById(R.id.leerzeile1);
        TextView emptyRow2 = (TextView) findViewById(R.id.leerzeile2);
        tableLayout.removeView(emptyRow1);
        tableLayout.removeView(emptyRow2);

        for(int row=5; row<15; row ++) {
            TableRow tableRow = (TableRow)findViewById(Util.getResourceID("row" + (row+1) + getDisplayNumber(), "id", getApplicationContext()));
            System.out.println("remove id: " + "row" + (row+1) + getDisplayNumber());
            tableLayout.removeView(tableRow);
        }
    }
*/


    public void sendResponse(ParseHTML parser) {
        System.out.println("response ist angekommen");

        setLastRefresh();
        fillUI(parser);
        resetTimer();
    }


    public void fillUI(ParseHTML parser) {
        if (parser == null) {
            System.out.println("parserContent is empty");
            return;
        }

        new UI_StationDisplay().fillUI(parser, this, getDisplayNumber());
    }


    protected void initChangeStationButton() {
        changeStationhButton = (Button) findViewById(R.id.changeStationButton);
        changeStationhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("change station button pressed on a DisplyActivity");
                startActivity(new Intent(DisplayActivity.this, ChangeStationActivity.class));
            }
        });
    }


}
