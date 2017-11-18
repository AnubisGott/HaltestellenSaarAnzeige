package sge.bahn;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import org.sge.stationdisplaysb.DisplayActivity;
import org.sge.stationdisplaysb.DisplayTimerActivity;
import org.sge.stationdisplaysb.R;

/**
 * Created by peter on 23.10.2017.
 */

public class UI_StationDisplay {
    public final static int NUMBER_ROWS_PORTRAIT = 15;
    public final static int NUMBER_ROWS_LANDSCAPE = 5;
    private int NUMBER_ROWS = 5;


    public void fillUI(ParseHTML parserContent, DisplayActivity displayActivity, String displayNumber) {
        System.out.println("*** fillUI ******************************************************************************************");
        if(displayActivity.isPortraitOrientation()) NUMBER_ROWS = NUMBER_ROWS_PORTRAIT;
        else NUMBER_ROWS = NUMBER_ROWS_LANDSCAPE;

        fillLetzteAktualisierung((DisplayTimerActivity) displayActivity, displayNumber);
        fillAbfahrt(parserContent, displayActivity, displayNumber);
        fillPrognose(parserContent, displayActivity, displayNumber);
        fillLinie(parserContent, displayActivity, displayNumber);
        fillRichtung(parserContent, displayActivity, displayNumber);
    }


    public void fillAbfahrt(ParseHTML parser, Activity activity, String displayNumber) {
        for(int row=0; row < NUMBER_ROWS; row++) {
            int id = R.id.textViewAbfahrt1 + row;

            TextView abfahrt1  = (TextView) activity.findViewById(Util.getResourceID("textViewAbfahrt" + (row+1) + displayNumber, "id", activity.getApplicationContext()));
            abfahrt1.setText(parser.getDepartureRow(row));
        }
    }


    public void fillLetzteAktualisierung(DisplayTimerActivity displayTimerActivity, String displayNumber) {
        //System.out.println("fillLetzteAktualisierung: " + "letzte Aktualisierung: " +  displayTimerActivity.getLastRefresh() + "   (" + displayTimerActivity.getCountDown() + ")");
        TextView letzteAktualisierung  = (TextView) displayTimerActivity.findViewById(Util.getResourceID("textViewLetzteAktualisierung" + displayNumber, "id", displayTimerActivity.getApplicationContext()));
        letzteAktualisierung.setText("letzte Aktualisierung: " +  displayTimerActivity.getLastRefresh() + "   (" + displayTimerActivity.getCountDown() + ")");
    }


    public void fillRichtung(ParseHTML parser, Activity activity, String displayNumber) {
        for(int row=0; row < NUMBER_ROWS; row++) {
            TextView richtung1 = (TextView) activity.findViewById(Util.getResourceID("textViewRichtung" + (row+1) + displayNumber, "id", activity.getApplicationContext()));
            richtung1.setText(parser.getDestinationRow(row));
        }
    }


    public void fillLinie(ParseHTML parser, Activity activity, String displayNumber) {
        for(int row=0; row < NUMBER_ROWS; row++) {
            TextView line1 = (TextView) activity.findViewById(Util.getResourceID("textViewLinie" + (row+1) + displayNumber, "id", activity.getApplicationContext()));
            line1.setText(parser.getLineRow(row));
        }
    }


    private void setTextColorGreenOrRed(TextView textView, String prognoseText) {
        if(prognoseText == null) return;

        if("pünktl.".contentEquals(prognoseText)) {
            textView.setTextColor(Color.GREEN);
        }
        else {
            if(prognoseText.contains("min")) {
                textView.setTextColor(Color.RED);
            }
            else{
                if(prognoseText.contains("ca.")) {
                    textView.setTextColor(Color.RED);
                }
                else {
                    textView.setTextColor(Color.WHITE);
                }
            }
        }
    }


    private String correctedDelay(String delay) {
        if(delay == null) return delay;
        if(delay.startsWith("Neue Fahrt")) return "Neue Fahrt";

        return delay;
    }


    public void fillPrognose(ParseHTML parser, Activity activity, String displayNumber) {
        for(int row=0; row < NUMBER_ROWS; row++) {
            TextView prognose1 = (TextView) activity.findViewById(Util.getResourceID("textViewDelay" + (row+1) + displayNumber, "id", activity.getApplicationContext()));
            setTextColorGreenOrRed(prognose1, parser.getPredictionRow(row));
            String delay = parser.getPredictionRow(row);
            String correctedDelay = correctedDelay(delay);
            prognose1.setText(correctedDelay);
        }

    }
}