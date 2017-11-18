package test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import sge.bahn.Util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import sge.bahn.BahnRequest;
import sge.bahn.ParseHTML;
import sge.bahn.Util;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class TestIntegration {

    @Test
    public void test_01_IntegrationTest() {
        System.out.println("test integration");
        Context appContext = InstrumentationRegistry.getTargetContext();
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(appContext, 15537, false));
        assertEquals(parser.countHafasContentTables(), 1);
        System.out.println("test_01: exactly one hafasContentTable exists");
    }


    @org.junit.Test
    public void test_02_CheckHafasContentTable() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));
        TagNode hafasContentTable = parser.getHafasContentTable();
        assertTrue(hafasContentTable != null);
        System.out.println("test_02: access to hafasContentTable is possible");
    }


    @org.junit.Test
    public void test_03_CheckAbfragezeiten() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));
        String hafasContentTable = parser.getAbfrageZeiten();
        assertTrue(hafasContentTable.startsWith("Abfahrt"));
        System.out.println("test_03: " + hafasContentTable);
    }


    @org.junit.Test
    public void test_04_CheckTimeTable() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));
        TagNode timeTable = parser.getTimeTable();
        assertTrue(timeTable != null);
        System.out.println("test_04: timeTable found");
    }


    @org.junit.Test
    public void test_05_CheckCountRowsTimeTable() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));
        System.out.println("test_05: count rows in timeTable: " + parser.countRowsTimeTable());
        assertTrue(parser.countRowsTimeTable() == 15);
    }



    @org.junit.Test
    public void test_10_CheckDepartureTime_Row4() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));

        for(int row=0; row<5; row++) {
            String dep = parser.getDepartureRow(row);
            System.out.println("test_10: departure: row: " + row + " " + dep);
            assertTrue(dep.length() == 5);
            assertTrue(dep.charAt(2) == ':');
            assertTrue(Util.isNumeric(dep.substring(0, 1)));
            assertTrue(Util.isNumeric(dep.substring(3, 4)));
        }
    }


    @org.junit.Test
    public void test_11_CheckPredictTime_Row0() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));

        for(int row=0; row<5; row++) {
            String prediction = parser.getPredictionRow(row);
            System.out.println("test_11: prediction: row: " + row + "  [" + prediction + "]");

            boolean onTime                = "pünktl.".contentEquals(prediction);
            boolean delayInMin            = prediction.contains("min");
            boolean emptyNonBrakingSpace  = prediction.contains("&nbsp;");
            boolean emptyField            = prediction.contains("");
            boolean delayTime             = prediction.startsWith("ca.");


            assertTrue(onTime || delayInMin || delayTime || emptyField || emptyNonBrakingSpace);
        }
    }

    @org.junit.Test
    public void test_16_CheckGetLine_Row0() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));

        for(int row=0; row<5; row++) {
            String line = parser.getLineRow(row);
            System.out.println("test_16: line: [" + line + "]");

            assertTrue(line.length() > 0);
        }
    }


    @org.junit.Test
    public void test_21_CheckDestination_Row0() {
        ParseHTML parser = new ParseHTML(BahnRequest.getBahnRequest(InstrumentationRegistry.getTargetContext(), 15537, false));

        for(int row=0; row<5; row++) {
            System.out.println("test_21: destination: row: " + row + "[" + parser.getDestinationRow(row) + "]");
            assertTrue(parser.getDestinationRow(row).length() > 0);
        }
    }
}
