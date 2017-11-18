package sge.bahn;

import android.content.Context;

import org.sge.stationdisplaysb.DisplayTimerActivity;

import java.util.*;


public class Settings {
    public static String defaultConfigurationString = "Markt, Heusweiler;;;;;15537;;;;;true;;;;;Hauptbahnhof, Saarbrücken;;;;;10600;;;;;true;;;;;Trierer Str., Saarbrücken;;;;;10603;;;;;true;;;;;Brückenstr., Saarbrücken Malstatt;;;;;10205;;;;;false;;;;;";
    public static String sep = ";;;;;";

    public final static String settingsFileName = "settings.txt";
    private static Settings settingsSingleton = null;
    public static final int START_LEVEL = 1;

    private List<String> stationNames   = new ArrayList<String>();
    private List<String> stationId      = new ArrayList<String>();
    private List<String> stationNurBahn = new ArrayList<String>();

    public static Settings getInstance() {
        if (settingsSingleton == null) settingsSingleton = new Settings();
        return settingsSingleton;
    }


    private Settings() {
        setStartSettings();
    }


    public void setStartSettings() {
    }


    public void saveSettings(Context context, int activityNumber, boolean alleChecked) {
        loadSettings(context);

        if(alleChecked) {
            stationNurBahn.set(activityNumber, "false");
        }
        else {
            stationNurBahn.set(activityNumber, "true");
        }

        String saveString = computeSaveString();
        System.out.println("save string: " + saveString);

        WriteAndReadFileUtil.writeStringToFile(settingsFileName, saveString, context);
    }


    public void saveSettings(Context context, int activityNumber, int stationIdInt, String stationName) {
        loadSettings(context);

        stationNames.set(activityNumber, stationName);
        stationId.set(activityNumber, "" + stationIdInt);

        String saveString = computeSaveString();
        System.out.println("save string: " + saveString);

        WriteAndReadFileUtil.writeStringToFile(settingsFileName, saveString, context);
    }


    private String computeSaveString() {
        String result = "";

        for(int a=0; a< DisplayTimerActivity.countActivities; a++)
        {
            result = result.concat(stationNames.get(a));
            result = result.concat(sep);

            result = result.concat(stationId.get(a));
            result = result.concat(sep);

            result = result.concat(stationNurBahn.get(a));
            result = result.concat(sep);
        }


        return result;
    }


    private void computeLoadString(String loadString) {
        String workString = loadString;

        stationNames.clear();
        stationId.clear();
        stationNurBahn.clear();

        for(int a=0; a< DisplayTimerActivity.countActivities; a++)
        {
            int endOfPayload = workString.indexOf(sep);
            String payLoad1 = workString.substring(0, endOfPayload);
            workString = workString.substring(endOfPayload + sep.length());
            //System.out.println("payload1: " + payLoad1);
            stationNames.add(payLoad1);

            endOfPayload = workString.indexOf(sep);
            String payLoad2 = workString.substring(0, endOfPayload);
            workString = workString.substring(endOfPayload + sep.length());
            //System.out.println("payload2: " + payLoad2);
            stationId.add(payLoad2);

            endOfPayload = workString.indexOf(sep);
            String payLoad3 = workString.substring(0, endOfPayload);
            workString = workString.substring(endOfPayload + sep.length());
            //System.out.println("payload3: " + payLoad3);
            stationNurBahn.add(payLoad3);
        }
    }


    public void loadSettings(Context context) {
        String readString = WriteAndReadFileUtil.readStringFromFile(settingsFileName, context);
        if (readString == null) readString = defaultConfigurationString;

        computeLoadString(readString);
    }


    public String getStationName(int i) {
        return this.stationNames.get(i);
    }


    public int getStationId(int i) {
        return Integer.parseInt(this.stationId.get(i));
    }


    public boolean getNurBahn(int i) {
        return "true".contentEquals(this.stationNurBahn.get(i));
    }
}