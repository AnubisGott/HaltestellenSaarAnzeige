package test;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.provider.Browser;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.htmlcleaner.TagNode;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.sge.stationdisplaysb.ChangeStationActivity;
import org.sge.stationdisplaysb.model.StationIdName;

import java.util.ArrayList;

import sge.bahn.BahnRequest;
import sge.bahn.ParseHTML;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
import static sge.bahn.BahnRequest.createSuggestionRequest;
import static sge.bahn.BahnRequest.extractJsonString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class TestStationSearch {

    @org.junit.Test
    public void test_01_CheckStationSearch_Extract() {
        System.out.println("test_01: station search extract");

        String jsonFileContent = FILE_CONTNENT;

        String json = BahnRequest.extractJsonString(jsonFileContent);

        assertTrue(json.startsWith("{"));
        assertTrue(json.endsWith("}"));
    }


    @org.junit.Test
    public void test_02_CheckStationSearch_Parsing() {
        System.out.println("test_02: station search parsing");

        Instrumentation mInstrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = mInstrumentation.addMonitor(ChangeStationActivity.class.getName(), null, false);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(mInstrumentation.getTargetContext(), ChangeStationActivity.class.getName());
        mInstrumentation.startActivitySync(intent);

        Activity currentActivity = getInstrumentation().waitForMonitor(monitor);
        assertNotNull(currentActivity);

        String jsonFileContent = FILE_CONTNENT;
        String json = BahnRequest.extractJsonString(jsonFileContent);

        ChangeStationActivity changeStationActivity = (ChangeStationActivity)currentActivity;
        ArrayList<StationIdName> resultList = changeStationActivity.readJason(json);

        System.out.println(resultList.get(2).getId());
        System.out.println(resultList.get(2).getName());

        assertTrue("000015420".contentEquals(resultList.get(2).getId()));
        assertTrue("Eiweiler, Heusweiler".contentEquals(resultList.get(2).getName()));
    }

    @org.junit.Test
    public void test_03_CheckStationSearch_Parsing() {
        System.out.println("test_03: station search parsing with get request");

        Instrumentation mInstrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = mInstrumentation.addMonitor(ChangeStationActivity.class.getName(), null, false);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(mInstrumentation.getTargetContext(), ChangeStationActivity.class.getName());
        mInstrumentation.startActivitySync(intent);

        Activity currentActivity = getInstrumentation().waitForMonitor(monitor);
        assertNotNull(currentActivity);

        String jsonResponse = BahnRequest.createSuggestionRequest(currentActivity.getApplicationContext(), "Markt, Heusweiler");
        // System.out.println(jsonResponse);

        String json = BahnRequest.extractJsonString(jsonResponse);

        ChangeStationActivity changeStationActivity = (ChangeStationActivity)currentActivity;
        ArrayList<StationIdName> resultList = changeStationActivity.readJason(json);

        System.out.println(resultList.get(1).getId());
        System.out.println(resultList.get(1).getName());

        assertTrue("000015537".contentEquals(resultList.get(1).getId()));
        assertTrue("Markt, Heusweiler".contentEquals(resultList.get(1).getName()));
    }

    static final String FILE_CONTNENT = "SLs.sls={\"suggestions\":[{\"value\":\"Markt, Heusweiler\",\"id\":\"A=1@O=Markt, Heusweiler@X=6930036@Y=49336765@U=80@L=000015537@B=1@V=11.9,@p=0@\",\"extId\":\"000015537\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6930036\",\"ycoord\":\"49336765\",\"state\":\"id\",\"prodClass\":\"1728\",\"weight\":\"1125\"},{\"value\":\"Rathaus, Heusweiler\",\"id\":\"A=1@O=Rathaus, Heusweiler@X=6930665@Y=49335866@U=80@L=000115500@B=1@V=11.9,@p=0@\",\"extId\":\"000115500\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6930665\",\"ycoord\":\"49335866\",\"state\":\"id\",\"prodClass\":\"1664\",\"weight\":\"466\"},{\"value\":\"Eiweiler, Heusweiler\",\"id\":\"A=1@O=Eiweiler, Heusweiler@X=6934719@Y=49356442@U=80@L=000015420@B=1@V=11.9,@p=0@\",\"extId\":\"000015420\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6934719\",\"ycoord\":\"49356442\",\"state\":\"id\",\"prodClass\":\"64\",\"weight\":\"400\"},{\"value\":\"Holzer Platz, Holz Heusweiler\",\"id\":\"A=1@O=Holzer Platz, Holz Heusweiler@X=6986156@Y=49331614@U=80@L=000015603@B=1@V=11.9,@p=0@\",\"extId\":\"000015603\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6986156\",\"ycoord\":\"49331614\",\"state\":\"id\",\"prodClass\":\"1664\",\"weight\":\"456\"},{\"value\":\"Hartgiebel, Holz Heusweiler\",\"id\":\"A=1@O=Hartgiebel, Holz Heusweiler@X=6991306@Y=49324620@U=80@L=000015611@B=1@V=11.9,@p=0@\",\"extId\":\"000015611\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6991306\",\"ycoord\":\"49324620\",\"state\":\"id\",\"prodClass\":\"128\",\"weight\":\"118\"},{\"value\":\"Kirschhof, Heusweiler\",\"id\":\"A=1@O=Kirschhof, Heusweiler@X=6936742@Y=49349637@U=80@L=000015541@B=1@V=11.9,@p=0@\",\"extId\":\"000015541\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6936742\",\"ycoord\":\"49349637\",\"state\":\"id\",\"prodClass\":\"64\",\"weight\":\"400\"},{\"value\":\"Kirchstr., Heusweiler\",\"id\":\"A=1@O=Kirchstr., Heusweiler@X=6927932@Y=49338302@U=80@L=000015507@B=1@V=11.9,@p=0@\",\"extId\":\"000015507\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6927932\",\"ycoord\":\"49338302\",\"state\":\"id\",\"prodClass\":\"640\",\"weight\":\"126\"},{\"value\":\"Albertstr., Heusweiler\",\"id\":\"A=1@O=Albertstr., Heusweiler@X=6921775@Y=49339731@U=80@L=000015508@B=1@V=11.9,@p=0@\",\"extId\":\"000015508\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6921775\",\"ycoord\":\"49339731\",\"state\":\"id\",\"prodClass\":\"640\",\"weight\":\"126\"},{\"value\":\"Ev. Kirche, Heusweiler\",\"id\":\"A=1@O=Ev. Kirche, Heusweiler@X=6928876@Y=49337808@U=80@L=000015516@B=1@V=11.9,@p=0@\",\"extId\":\"000015516\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6928876\",\"ycoord\":\"49337808\",\"state\":\"id\",\"prodClass\":\"128\",\"weight\":\"118\"},{\"value\":\"Schule, Holz Heusweiler\",\"id\":\"A=1@O=Schule, Holz Heusweiler@X=6989634@Y=49331254@U=80@L=000015602@B=1@V=11.9,@p=0@\",\"extId\":\"000015602\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6989634\",\"ycoord\":\"49331254\",\"state\":\"id\",\"prodClass\":\"1664\",\"weight\":\"466\"},{\"value\":\"Schulzentrum, Heusweiler\",\"id\":\"A=1@O=Schulzentrum, Heusweiler@X=6924948@Y=49333628@U=80@L=000015529@B=1@V=11.9,@p=0@\",\"extId\":\"000015529\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6924948\",\"ycoord\":\"49333628\",\"state\":\"id\",\"prodClass\":\"192\",\"weight\":\"669\"},{\"value\":\"Dilsburg Brücke, Heusweiler\",\"id\":\"A=1@O=Dilsburg Brücke, Heusweiler@X=6931699@Y=49331875@U=80@L=000015510@B=1@V=11.9,@p=0@\",\"extId\":\"000015510\",\"type\":\"1\",\"typeStr\":\"[Bhf/Hst]\",\"xcoord\":\"6931699\",\"ycoord\":\"49331875\",\"state\":\"id\",\"prodClass\":\"1664\",\"weight\":\"466\"}]};SLs.showSuggestion();";

}
