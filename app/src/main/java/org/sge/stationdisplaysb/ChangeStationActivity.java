package org.sge.stationdisplaysb;

import android.app.Instrumentation;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sge.stationdisplaysb.model.StationIdName;

import java.util.ArrayList;
import java.util.List;

import sge.bahn.BahnRequest;
import sge.bahn.Settings;


public class ChangeStationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button searchButton      = null;
    private Button searchResetButton = null;
    private Button takeOverButton    = null;

    private EditText editText = null;
    private List<TextView> tvList = new ArrayList<TextView>();
    private List<String> stationNameList = new ArrayList<String>();
    private List<String> stationIdList = new ArrayList<String>();
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_station);
        initSearchResetButton();
        initSearchStationButton();
        initTakeOverButton();
        initChangeTextListener();
    }


    private void initChangeTextListener() {
        editText = (EditText) findViewById(R.id.plain_text_input);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString();
                System.out.println("edit text has changed: " + text + " " + text.length());
                if(text.length() >= 4) enableSearchButton(true);
                else enableSearchButton(false);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }


    private String getEditFieldContent() {
        editText = (EditText) findViewById(R.id.plain_text_input);
        return  editText.getText().toString();
    }


    private void clearEditField() {
        editText = (EditText) findViewById(R.id.plain_text_input);
        editText.setText("");
    }


    private void initSearchStationButton() {
        final ChangeStationActivity changeStationActivity = this;
        searchButton = (Button) findViewById(R.id.searchStationButton);
        enableSearchButton(false);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("search station button pressed");
                clearLists();
                enableTakeOverButton(false);
                BahnRequest.createSuggestionRequest(getApplicationContext(), changeStationActivity, getEditFieldContent());
            }
        });
    }


    private void initSearchResetButton() {
        final ChangeStationActivity changeStationActivity = this;
        searchResetButton = (Button) findViewById(R.id.clearButton);
        searchResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("search reset button pressed");
                clearEditField();
                clearLists();
                enableTakeOverButton(false);
                enableSearchButton(false);
            }
        });
    }


    private void enableTakeOverButton(boolean enable) {
        Button takeOverButton = (Button) findViewById(R.id.selectStationButton);
        takeOverButton.setEnabled(enable);
    }


    private void enableSearchButton(boolean enable) {
        Button searchButton = (Button) findViewById(R.id.searchStationButton);
        searchButton.setEnabled(enable);
    }


    private void initTakeOverButton() {
        final ChangeStationActivity changeStationActivity = this;
        takeOverButton = (Button) findViewById(R.id.selectStationButton);
        enableTakeOverButton(false);

        takeOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("take over button pressed");
                DisplayTimerActivity a = DisplayTimerActivity.getCurrentDisplayActivity();
                a.setStationIdInt(Integer.parseInt(stationIdList.get(selectedIndex)));
                System.out.println("set station id to: " + Integer.parseInt(stationIdList.get(selectedIndex)));
                String stationName = stationNameList.get(selectedIndex);
                System.out.println("set station name to: " + stationName);
                Settings.getInstance().saveSettings(a.getApplicationContext(), a.getActivityNumber(), Integer.parseInt(stationIdList.get(selectedIndex)), stationName);
                startActivity(new Intent(ChangeStationActivity.this, a.getClass()));
            }
        });
    }


    private void clearLists() {
        System.out.println("clear lists");

        LinearLayout lo = (LinearLayout) findViewById(R.id.suggestionList);
        lo.removeAllViews();
        tvList.clear();
        stationNameList.clear();
        stationIdList.clear();
    }


    public ArrayList<StationIdName> readJason(String jsonString) {
        System.out.println("read json");
        System.out.println(jsonString);

        ArrayList<StationIdName> resultList = new ArrayList<StationIdName>();

        JSONObject reader = null;
        try {
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray stationArray = null;
        try {
            stationArray = reader.getJSONArray("suggestions");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int s=0; s<stationArray.length(); s++) {
            JSONObject station = null;
            try {
                station = stationArray.getJSONObject(s);

                String stationName = station.getString("value");
                String stationId   = station.getString("extId");

                stationNameList.add(stationName);
                stationIdList.add(stationId);
                resultList.add(new StationIdName(stationId, stationName));

                System.out.println("i: " + s + " " + stationName + " " + stationId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }


    private void fillResultList() {
        LinearLayout lo = (LinearLayout) findViewById(R.id.suggestionList);

        for(int i=0; i<stationNameList.size(); i++) {
            TextView textView = new TextView(this);
            textView.setId(i);
            textView.setClickable(true);
            textView.setOnClickListener(this);
            tvList.add(textView);
            // System.out.println("add station name suggestion: " + i);

            textView.setText(stationNameList.get(i));
            lo.addView(textView);
        }

    }


    public void sendSuggestionResponse(String jsonString) {
        System.out.println("suggestion response ist angekommen");
        System.out.println(jsonString);

        clearLists();
        readJason(jsonString);

        fillResultList();
    }


    @Override
    public void onClick(View v) {
        System.out.println("on text view clicked " + v.getId() + ((TextView)v).getText().toString());
        EditText ed = (EditText)findViewById(R.id.plain_text_input);
        ed.setText(stationNameList.get(v.getId()));
        selectedIndex = v.getId();
        enableTakeOverButton(true);
    }
}

