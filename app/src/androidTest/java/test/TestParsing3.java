package test;

import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
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
public class TestParsing3 {
    @org.junit.Test
    public void test__01_CountHafasContentTables() {
        System.out.println("test example3");

        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        assertEquals(parser.countHafasContentTables(), 1);
        System.out.println("test_01: exactly one hafasContentTable exists");
    }


    @org.junit.Test
    public void test__11_CheckPredictTime_Row0() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);

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
    public void test__05_CheckCountRowsTimeTable() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_05: count rows in timeTable: " + parser.countRowsTimeTable());
        assertTrue(parser.countRowsTimeTable() <= 15);
    }



    @org.junit.Test
    public void test__21_CheckDestination_Row0() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);

        for(int row=0; row<5; row++) {
            System.out.println("test_21: destination: row: " + row + "[" + parser.getDestinationRow(row) + "]");
            assertTrue(parser.getDestinationRow(row).length() > 0);
        }
    }


    @org.junit.Test
    public void test_01_CountHafasContentTables() {
        System.out.println("test example1");

        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        assertEquals(parser.countHafasContentTables(), 1);
        System.out.println("test_01: exactly one hafasContentTable exists");
    }


    @org.junit.Test
    public void test_02_CheckHafasContentTable() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        TagNode hafasContentTable = parser.getHafasContentTable();
        assertTrue(hafasContentTable != null);
        System.out.println("test_02: access to hafasContentTable is possible");
    }


    @org.junit.Test
    public void test_03_CheckAbfragezeiten() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        String hafasContentTable = parser.getAbfrageZeiten();
        assertTrue(hafasContentTable.startsWith("Abfahrt"));
        System.out.println("test_03: " + hafasContentTable);
    }


    @org.junit.Test
    public void test_04_CheckTimeTable() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        TagNode timeTable = parser.getTimeTable();
        assertTrue(timeTable != null);
        System.out.println("test_04: timeTable found");
    }


    @org.junit.Test
    public void test_05_CheckCountRowsTimeTable() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_05: count rows in timeTable: " + parser.countRowsTimeTable());
        assertTrue(parser.countRowsTimeTable() == 15);
    }


    @org.junit.Test
    public void test_06_CheckDepartureTime_Row1() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_06: departure: " + parser.getDepartureRow(0));
        assertTrue("04:28".contentEquals(parser.getDepartureRow(0)));
    }



    @org.junit.Test
    public void test_07_CheckDepartureTime_Row2() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_07: departure: " + parser.getDepartureRow(1));
        assertTrue("04:58".contentEquals(parser.getDepartureRow(1)));
    }


    @org.junit.Test
    public void test_08_CheckDepartureTime_Row3() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_08: departure: " + parser.getDepartureRow(2));
        assertTrue("05:28".contentEquals(parser.getDepartureRow(2)));
    }


    @org.junit.Test
    public void test_09_CheckDepartureTime_Row4() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_09: departure: " + parser.getDepartureRow(3));
        assertTrue("05:43".contentEquals(parser.getDepartureRow(3)));
    }


    @org.junit.Test
    public void test_10_CheckDepartureTime_Row5() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_10: departure: " + parser.getDepartureRow(4));
        assertTrue("05:57".contentEquals(parser.getDepartureRow(4)));
    }


    @org.junit.Test
    public void test_11_CheckPredictTime_Row1() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_11: prediction: [" + parser.getPredictionRow(0) + "]");
        assertTrue("".contentEquals(parser.getPredictionRow(0)));
    }


    @org.junit.Test
    public void test_12_CheckPredictTime_Row2() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_12: prediction: [" + parser.getPredictionRow(1) + "]");
        assertTrue("".contentEquals(parser.getPredictionRow(1)));
    }


    @org.junit.Test
    public void test_13_CheckPredictTime_Row3() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_13: prediction: [" + parser.getPredictionRow(2) + "]");
        assertTrue("".contentEquals(parser.getPredictionRow(2)));
    }


    @org.junit.Test
    public void test_14_CheckPredictTime_Row4() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_14: prediction: [" + parser.getPredictionRow(3) + "]");
        assertTrue("".contentEquals(parser.getPredictionRow(3)));
    }


    @org.junit.Test
    public void test_15_CheckPredictTime_Row5() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_15: prediction: [" + parser.getPredictionRow(4) + "]");
        assertTrue("".contentEquals(parser.getPredictionRow(4)));
    }


    @org.junit.Test
    public void test_16_CheckGetLine_Row0() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_16: line: [" + parser.getLineRow(0) + "]");
        assertTrue("S1".contentEquals(parser.getLineRow(0)));
    }


    @org.junit.Test
    public void test_17_CheckGetLine_Row1() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_17: line: [" + parser.getLineRow(1) + "]");
        assertTrue("S1".contentEquals(parser.getLineRow(1)));
    }


    @org.junit.Test
    public void test_18_CheckGetLine_Row2() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_18: line: [" + parser.getLineRow(2) + "]");
        assertTrue("S1".contentEquals(parser.getLineRow(2)));
    }


    @org.junit.Test
    public void test_19_CheckGetLine_Row3() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_19: line: [" + parser.getLineRow(3) + "]");
        assertTrue("S1".contentEquals(parser.getLineRow(3)));
    }


    @org.junit.Test
    public void test_20_CheckGetLine_Row4() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_20: line: [" + parser.getLineRow(4) + "]");
        assertTrue("S1".contentEquals(parser.getLineRow(4)));
    }


    @org.junit.Test
    public void test_21_CheckDestination_Row0() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_21: destination: [" + parser.getDestinationRow(0) + "]");
        assertTrue("Brebach".contentEquals(parser.getDestinationRow(0)));
    }


    @org.junit.Test
    public void test_22_CheckDestination_Row1() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_22: destination: [" + parser.getDestinationRow(1) + "]");
        assertTrue("Brebach".contentEquals(parser.getDestinationRow(1)));
    }


    @org.junit.Test
    public void test_23_CheckDestination_Row2() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_23: destination: [" + parser.getDestinationRow(2) + "]");
        assertTrue("Brebach".contentEquals(parser.getDestinationRow(2)));
    }


    @org.junit.Test
    public void test_24_CheckDestination_Row3() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_24: destination: [" + parser.getDestinationRow(3) + "]");
        assertTrue("Brebach".contentEquals(parser.getDestinationRow(3)));
    }


    @org.junit.Test
    public void test_25_CheckDestination_Row4() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_25: destination: [" + parser.getDestinationRow(4) + "]");
        assertTrue("Lebach".contentEquals(parser.getDestinationRow(4)));
    }

    @org.junit.Test
    public void test_30_CheckHeaderRow() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_30: headerRow");
        assertTrue(parser.getHeaderRowTimeTable() != null);
    }

    @org.junit.Test
    public void test_31_CheckGetHeaderColumn() {
        String fileContent = FILE_CONTNENT;
        ParseHTML parser = new ParseHTML(fileContent);
        System.out.println("test_30: headerRow");
        assertTrue(parser.getColumnNumber(ParseHTML.COLUMN_NAME_FAHRT) == 1);
        assertTrue(parser.getColumnNumber(ParseHTML.COLUMN_NAME_InRichtung) == 2);
        assertTrue(parser.getColumnNumber(ParseHTML.COLUMN_NAME_PROGNOSE) == -1);
        assertTrue(parser.getColumnNumber(ParseHTML.COLUMN_NAME_ZEIT) == 0);
    }

    static final String FILE_CONTNENT = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "<html lang=\"de\">\n" +
            "<head>\n" +
            "<title>Saarfahrplan Ankunft&nbsp;/&nbsp;Abfahrt</title>\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10\" />\n" +
            "<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\">\n" +
            "<link rel=\"shortcut icon\" href=\"/hafas-res/img/favicon.ico\" type=\"image/ico\">\n" +
            "<link rel=\"apple-touch-icon-precomposed\" href=\"/hafas-res/img/apple-touch-icon-precomposed.png\">\n" +
            "<script type=\"text/javascript\" src=\"/hafas-res/js/hafas_standard.js?guiV=47\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/hafas-res/js/FSuggest_v1.2.js?guiV=47\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "/* <![CDATA[ */\n" +
            "var t_suggestionsoff = \"Vorschläge aus\";\n" +
            "var t_suggestionson = \"Vorschläge ein\";\n" +
            "var t_minimapoff = \"Mini-Karte aus\";\n" +
            "var t_suggestions = \"Vorschl&#228;ge\";\n" +
            "var t_suggestionscookie = \"Favoriten\";\n" +
            "var t_topMatches = \"Toptreffer\";\n" +
            "var t_furtherMatches = \"Nach weiteren Treffern suchen...\";\n" +
            "var t_meansOfTransport = \"Verkehrsmittel an\";\n" +
            "var t_connectionsPerYear = \"Fahrten im Jahr\";\n" +
            "var t_location = \"Ort\";\n" +
            "var t_limitSearchMap = \"Suche nur auf Kartenausschnitt\";\n" +
            "var t_minimapon = \"Mini-Karte an\";\n" +
            "var t_suggestHint1 = \"Keine Toptreffer gefunden. Benutzen Sie die <br/><b>Suggest-Funktion</b>, in dem Sie mindestens <br/><b>\";\n" +
            "var t_suggestHint2 = \"Buchstaben eingeben</b>!\";\n" +
            "var t_filterStations = \"Hst.\";\n" +
            "var t_filterAddresses = \"Adressen\";\n" +
            "var t_filterPOI = \"POI\";\n" +
            "function fixedLoc(par){\n" +
            "//document.getElementById('inputEvaId').value = par.evaId;\n" +
            "}\n" +
            "function getElementsByClass(searchClass, domNode, tagName) {\n" +
            "if (domNode == null) {\n" +
            "domNode = document.body;\n" +
            "}\n" +
            "if (tagName == null) {\n" +
            "tagName = \"*\";\n" +
            "}\n" +
            "var el = new Array;\n" +
            "var tags = domNode.getElementsByTagName(tagName);\n" +
            "var tcl = \"\" + searchClass + \"\";\n" +
            "for (i = 0, j = 0; i < tags.length; i++) {\n" +
            "var test = \" \" + tags[i].className + \" \";\n" +
            "if (tags[i].className.indexOf(searchClass) != -1) {\n" +
            "el[j++] = tags[i];\n" +
            "}\n" +
            "}\n" +
            "return el;\n" +
            "}\n" +
            "function initHafasSuggest(){\n" +
            "var elements = getElementsByClass(\"HafasSuggest\",null,\"input\");\n" +
            "for (var i=0;i < elements.length; i++) {\n" +
            "if(elements[i].id != 'undefined') {\n" +
            "var suggid = elements[i].id;\n" +
            "}else{\n" +
            "var suggid = 'hfs_suggest_input_' + i;\n" +
            "}\n" +
            "var classes = elements[i].className.split(\" \");\n" +
            "var suggestType = 0;\n" +
            "var stationBoardSuggest = false;\n" +
            "var isVia = false;\n" +
            "var callbackArr = new Array();\n" +
            "for(var j=0;j < classes.length; j++){\n" +
            "if(classes[j] == \"st\"){\n" +
            "suggestType += 1;\n" +
            "}else if(classes[j] == \"adr\"){\n" +
            "suggestType += 2;\n" +
            "}else if(classes[j] == \"poi\"){\n" +
            "suggestType += 4;\n" +
            "}else if(classes[j] == \"stb\"){\n" +
            "stationBoardSuggest = true;\n" +
            "}else if(classes[j] == \"via\"){\n" +
            "isVia = true;\n" +
            "}else if(classes[j].indexOf('sc_') >= 0){\n" +
            "var selCallback = eval(classes[j].replace('sc_',''));\n" +
            "callbackArr.push(selCallback);\n" +
            "}\n" +
            "}\n" +
            "if(suggestType == 0){\n" +
            "suggestType = 255;\n" +
            "}\n" +
            "var attr = (elements[i].getAttribute('name') != null)?elements[i].getAttribute('name'):'S';\n" +
            "var currParam = {loc:suggid,\n" +
            "type:attr,\n" +
            "minChar:4,\n" +
            "cookiename:'std30-history',\n" +
            "stopDelay:400,\n" +
            "requestType:'js',\n" +
            "useFurtherMatches:true ,\n" +
            "useProducts:true,\n" +
            "useWeight:true,\n" +
            "useWrap:false,\n" +
            "useHouseNumber:false,\n" +
            "useHighlighting:true,\n" +
            "useCategory:false,\n" +
            "detachable:true,\n" +
            "useTypeFilter:false,\n" +
            "useMaps:true,\n" +
            "useTopFavorites:true,\n" +
            "useAutoFillIn:true\n" +
            "};\n" +
            "if(stationBoardSuggest){\n" +
            "currParam.type = \"stb\";\n" +
            "}\n" +
            "if(isVia){\n" +
            "suggid.match(/(\\d)/);\n" +
            "var viaNo = RegExp.$1;\n" +
            "currParam.requestURL = 'http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStops'+viaNo+'.0A='+suggestType+'&getstop=1&noSession=yes&REQ0JourneyStopsB=12&REQ0JourneyStops'+viaNo+'.0G=';\n" +
            "}else{\n" +
            "currParam.requestURL = 'http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStopsS0A='+suggestType+'&getstop=1&noSession=yes&REQ0JourneyStopsB=12&REQ0JourneyStopsS0G=';\n" +
            "}\n" +
            "currParam.selectCallback = function(par){\n" +
            "for(var i=0; i < callbackArr.length;i++){\n" +
            "callbackArr[i](par);\n" +
            "}\n" +
            "};\n" +
            "new FSuggest(currParam);\n" +
            "}\n" +
            "}\n" +
            "/* ]]> */\n" +
            "</script>\n" +
            "<script type=\"text/javascript\">\n" +
            "var gImagePath = '/hafas-res/img/';\n" +
            "var fsugg_map = '<img src=\"/hafas-res/img/icon_google_earth.gif\">';\n" +
            "</script>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/hafas-res/css/vgs.css?guiV=47\">\n" +
            "<style type=\"text/css\">\n" +
            "ul.jmap_slider      { margin:0px;list-style-type:none; padding:0px;  }\n" +
            "ul.jmap_slider a    { color: black;}\n" +
            ".additionMapMenu    { background-color: white;\n" +
            "border: 2px solid #dfdfdf;\n" +
            "font-size: 11px;\n" +
            "padding: 0 0 0 4px;\n" +
            "position: absolute;\n" +
            "right: 116px;\n" +
            "top: 4px;\n" +
            "width: 85px;\n" +
            "line-height:20px;\n" +
            "z-index: 200000;    }\n" +
            ".additionMapMenu a  { color:black;font-weight:bold;}\n" +
            ".suggestButton      { background-position:2px 3px;background-repeat:no-repeat;background-image:url(/hafas-res/img/icon_calexport.gif);}\n" +
            "#suggestion div     { background-image:url(/hafas-res/img/suggest_stop.gif);background-repeat:no-repeat;background-position:2px 2px; }\n" +
            "#suggestion div.train,\n" +
            "#suggestion div.trainselected   { background-image:url(/hafas-res/img/look/prod_ic.gif); background-repeat:no-repeat; background-position: 3px 1px;}\n" +
            "#suggestion div.poi,\n" +
            "#suggestion div.poiselected     { background-image:url(/hafas-res/img/suggest_poi.gif);background-repeat:no-repeat;background-position:2px 2px; }\n" +
            "#suggestion div.adr,\n" +
            "#suggestion div.adrselected     { background-image:url(/hafas-res/img/suggest_adr.gif);background-repeat:no-repeat;background-position:2px 2px; }\n" +
            "#suggestion div.delfi,\n" +
            "#suggestion div.delfiselected     { background-image:url(/hafas-res/img/suggest_delfi.gif);background-repeat:no-repeat;background-position:5px 3px; }\n" +
            "#suggestion div.furtherMatches,\n" +
            "#suggestion div.furtherMatchesselected  { background-image:none;}\n" +
            "</style>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/hafas-res/css/vgs_suggest.css?guiV=47\">\n" +
            "</head>\n" +
            "<body >\n" +
            "<div id=\"hafas\"><a name=\"top\"></a>\n" +
            "<table class=\"hafasHeader\" cellspacing=\"0\" summary=\"Kopfbereich\">\n" +
            "<tr>\n" +
            "<td class=\"logo bottom nopadding\" style=\"width:300px;\"><a href=\"http://www.saarfahrplan.de\"><img src=\"/hafas-res/img/logo_saarfahrplan.jpg\" alt=\"Saarfahrplan\"></a></td>\n" +
            "<td class=\"logo bottom nopadding\" style=\"width:400px;\"><a href=\"http://www.vgs-online.de/?p=997\"><img src=\"/hafas-res/img/logo_saarfahrplan.gif\" alt=\"Saarfahrplan\"></a></td>\n" +
            "<td class=\"logo bottom nopadding\" style=\"width:auto;\"><a href=\"http://www.vgs-online.de\"><img src=\"/hafas-res/img/logo_vgs.jpg\" alt=\"VGS\"></a></td>\n" +
            "<td class=\"logo bottom nopadding\" style=\"width:171px;\"><a href=\"http://www.saarvv.de\"><img src=\"/hafas-res/img/logo_saarvv.jpg\" alt=\"SaarVV\"></a></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td colspan=\"3\" style=\"background:url(/hafas-res/img/header_line.gif) repeat-x;height:41px;\">\n" +
            "Der Saarfahrplan optimiert f&#252;r Handy/Smartphone: <a class=\"headerLink\" href=\"http://mobil.saarfahrplan.de\">mobil.saarfahrplan.de</a>\n" +
            "</td>\n" +
            "<td colspan=\"1\" style=\"background:url(/hafas-res/img/header_line.gif) repeat-x;height:41px;\" align=\"right\">\n" +
            "<b>Hotline: 06898 / 500 4000</b>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"hafasSlider\"  cellspacing=\"0\" summary=\"HAFAS-Navigation\">\n" +
            "<tr>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/query.exe/dn?L=public&\">Verbindung</a></td>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/query.exe/dn?L=public&view=map&SetGlobalOptionGO_mapSearch=ACTIVE&REQMapCenterX=9741016&REQMapCenterY=52376763&REQMapScaling=500000\">Karte</a></td>\n" +
            "<td class=\"activeslider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&\">Ankunft&nbsp;/&nbsp;Abfahrt</a></td>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/tb/query-p2w.exe/dn?L=public&whichTask=pf&\">Pendlerfahrplan</a></td>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/tb/query-p2w.exe/dn?L=public&whichTask=hst&\">Haltestellenfahrplan</a></td>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/query.exe/dn?L=public&wai=yes&\">Textversion</a></td>\n" +
            "<td class=\"slider\"><a href=\"http://www.saarfahrplan.de/cgi-bin/eu/query.exe/dn?\">International <span class=\"red\">(beta)</span></a></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"hafasHeader\" cellspacing=\"0\" summary=\"Sprachwahl\">\n" +
            "<tr>\n" +
            "<td class=\"changeLanguage borderbottom\" style=\"padding-left:10px;\"><a class=\"activelanguage\" href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&newrequest=yes&\"><img class=\"language\" src=\"/hafas-res/img/flag_germany.gif\" border=\"0\" width=\"16\" height=\"10\" alt=\"Deutsch\" title=\"Deutsch\" /></a>\n" +
            "<a class=\"language\" href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/en?L=public&newrequest=yes&\"><img class=\"language\" src=\"/hafas-res/img/flag_uk.gif\" border=\"0\" width=\"16\" height=\"10\" alt=\"English\" title=\"English\" /></a>\n" +
            "<a class=\"language\" href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/fn?L=public&newrequest=yes&\"><img class=\"language\" src=\"/hafas-res/img/flag_france.gif\" border=\"0\" width=\"16\" height=\"10\" alt=\"Fran&#231;ais\" title=\"Fran&#231;ais\" /></a>\n" +
            "</td></tr>\n" +
            "</table>\n" +
            "<table class=\"hafasHeader\" cellspacing=\"0\" summary=\"Seitentitel/Haupt&#252;berschrift\">\n" +
            "<tr>\n" +
            "<td class=\"pagetitle\">Markt, Heusweiler</td>\n" +
            "<td class=\"pagetitle right\">Aktueller Abfahrtsplan g&#252;ltig am 30.10.17 (01:24 - 07:28 Uhr)</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"hafasContentTable\" cellspacing=\"0\">\n" +
            "<tr>\n" +
            "<td class=\"hafasContentTable\">\n" +
            "<div class=\"separator\" style=\"margin-bottom:1px;\"><img src=\"/hafas-res/img/stress_sep.gif\" width=\"11\" height=\"11\" alt=\"\" />Ihre Anfrage</div>\n" +
            "<table class=\"hafasResult\" cellspacing=\"0\">\n" +
            "<tr>\n" +
            "<th class=\"querysummary\">Bhf./Haltest.:</th>\n" +
            "<td class=\"querysummary screennowrap\">Markt, Heusweiler\n" +
            "<a href=\"#end\">(Allgemeine Informationen)</a>\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/query.exe/dn?L=public&view=map&REQMapRoute0.DisplayMode=LOCATION&MapLocation.Scaling=2000&MapLocation.X=6930036&MapLocation.Y=49336765&MapLocation.Name=Markt, Heusweiler\"><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> Karte</a>\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<th class=\"querysummary\">Fahrplan:</th>\n" +
            "<td class=\"querysummary\">\n" +
            "Mo, 30.10.17,\n" +
            "Abfahrt 01:24\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=now&productsFilter=7:1111101&selectDate=today&maxJourneys=15&start=yes\"><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> aktuell</a>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<div class=\"separatingLine\" style=\"margin-top:5px;\"><hr style=\"display:none;\" /></div>\n" +
            "<table class=\"hafasButtons\" cellspacing=\"0\">\n" +
            "<tr>\n" +
            "<td class=\"nowrap links\"><a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=01:24&maxJourneys=15&dateBegin=&dateEnd=&selectDate=today&productsFilter=7:1111101&editStation=yes&dirInput=&\"><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> Anfrage &#228;ndern</a><a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&\"><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> Neue Anfrage</a><a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=arr&time=01:24&maxJourneys=15&dateBegin=&dateEnd=&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\"><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> Ankunftsplan</a><a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=01:24&maxJourneys=15&dateBegin=&dateEnd=&selectDate=today&productsFilter=7:1111101&start=yes&pageViewMode=PRINT&dirInput=&\" target=\"Printview\" onclick=\"openPopup('about:blank','Printview',20,200,700,600,'001000');\" accesskey=\"p\"><img class=\"icon\" src=\"/hafas-res/img/icon_print.gif\" width=\"14\" height=\"14\" alt=\"\" /> Druckansicht</a></td></tr>\n" +
            "</table>\n" +
            "<div class=\"separator\" style=\"margin-bottom:1px;\"><img src=\"/hafas-res/img/stress_sep.gif\" width=\"11\" height=\"11\" alt=\"\" />Abfahrt (01:24 - 07:28)</div>\n" +
            "<table cellspacing=\"0\" class=\"hafasResult\" style=\"width: 100%;\">\n" +
            "<tr>\n" +
            "<th class=\"borderright\">Zeit</th>\n" +
            "<th class=\"borderright\">Fahrt</th>\n" +
            "<th class=\"borderright\">In Richtung</th>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">04:28</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/967275/347938/773296/64223/80?L=public&backLink=sq&date=30.10.17&time=04:28&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=05:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=04:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "04:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=04:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "04:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=04:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "04:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=04:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "04:33\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=04:54&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "04:54\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=05:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=05:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:03\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=05:06&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:06\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=05:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=05:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:17\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">04:58</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/987591/354710/698210/19909/80?L=public&backLink=sq&date=30.10.17&time=04:58&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=05:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=04:58&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "04:58\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=04:59&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "04:59\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=05:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "05:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=05:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "05:03\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=05:24&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "05:24\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=05:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=05:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:33\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=05:36&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:36\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=05:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=05:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "05:47\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">05:28</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/603480/226673/851874/224779/80?L=public&backLink=sq&date=30.10.17&time=05:28&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=05:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "05:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=05:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "05:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=05:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "05:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=05:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "05:33\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=05:54&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "05:54\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=06:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=06:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:03\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=06:06&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:06\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=06:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:17\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">05:43</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/756615/277764/768524/132057/80?L=public&backLink=sq&date=30.10.17&time=05:43&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=05:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "05:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=05:44&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "05:44\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=05:46&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "05:46\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=05:48&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "05:48\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=06:09&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "06:09\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=06:16&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:16\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=06:18&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:18\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=06:21&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:21\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=06:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:32\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">05:57</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/301446/125965/97802/51581/80?L=public&backLink=sq&date=30.10.17&time=05:57&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=06:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=05:57&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "05:57\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15536&boardType=dep&time=05:59&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "In der Hommersbach, Heusweiler\n" +
            "</a>\n" +
            "05:59\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15541&boardType=dep&time=06:00&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Kirschhof, Heusweiler\n" +
            "</a>\n" +
            "06:00\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15420&boardType=dep&time=06:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler, Heusweiler\n" +
            "</a>\n" +
            "06:02\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15421&boardType=dep&time=06:04&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler Nord, Heusweiler\n" +
            "</a>\n" +
            "06:04\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42208&boardType=dep&time=06:07&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler S&#252;d, Lebach\n" +
            "</a>\n" +
            "06:07\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42207&boardType=dep&time=06:08&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler Nord, Lebach\n" +
            "</a>\n" +
            "06:08\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=41529&boardType=dep&time=06:10&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach S&#252;d\n" +
            "</a>\n" +
            "06:10\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=06:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "06:13\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">05:58</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/302211/126287/746886/272706/80?L=public&backLink=sq&date=30.10.17&time=05:58&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=05:58&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "05:58\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=05:59&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "05:59\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=06:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "06:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=06:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "06:03\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=06:24&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "06:24\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=06:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=06:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:33\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=06:36&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:36\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=06:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=06:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:47\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">06:13</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/344778/140485/464484/117317/80?L=public&backLink=sq&date=30.10.17&time=06:13&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=06:14&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "06:14\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=06:16&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "06:16\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=06:18&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "06:18\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=06:39&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "06:39\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=06:46&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:46\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=06:48&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:48\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=06:51&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:51\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=06:58&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "06:58\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:02\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">06:27</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/142194/72881/181750/43478/80?L=public&backLink=sq&date=30.10.17&time=06:27&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=06:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:27&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:27\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15536&boardType=dep&time=06:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "In der Hommersbach, Heusweiler\n" +
            "</a>\n" +
            "06:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15541&boardType=dep&time=06:30&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Kirschhof, Heusweiler\n" +
            "</a>\n" +
            "06:30\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15420&boardType=dep&time=06:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler, Heusweiler\n" +
            "</a>\n" +
            "06:32\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15421&boardType=dep&time=06:34&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler Nord, Heusweiler\n" +
            "</a>\n" +
            "06:34\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42208&boardType=dep&time=06:37&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler S&#252;d, Lebach\n" +
            "</a>\n" +
            "06:37\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42207&boardType=dep&time=06:38&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler Nord, Lebach\n" +
            "</a>\n" +
            "06:38\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=41529&boardType=dep&time=06:40&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach S&#252;d\n" +
            "</a>\n" +
            "06:40\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=06:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "06:43\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">06:28</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/540540/205730/52064/154149/80?L=public&backLink=sq&date=30.10.17&time=06:28&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=06:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "06:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=06:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "06:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=06:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "06:33\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=06:54&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "06:54\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=07:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=07:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:03\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=07:06&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:06\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=07:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:17\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">06:43</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/567708/214795/705964/163748/80?L=public&backLink=sq&date=30.10.17&time=06:43&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=06:44&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "06:44\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=06:46&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "06:46\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=06:48&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "06:48\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=07:09&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "07:09\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=07:16&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:16\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=07:18&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:18\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=07:21&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:21\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=07:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:32\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">06:57</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/891279/322573/687900/46857/80?L=public&backLink=sq&date=30.10.17&time=06:57&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8007868&boardType=dep&time=07:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach-Jabach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:57&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:57\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15536&boardType=dep&time=06:59&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "In der Hommersbach, Heusweiler\n" +
            "</a>\n" +
            "06:59\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15541&boardType=dep&time=07:00&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Kirschhof, Heusweiler\n" +
            "</a>\n" +
            "07:00\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15420&boardType=dep&time=07:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler, Heusweiler\n" +
            "</a>\n" +
            "07:02\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15421&boardType=dep&time=07:04&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler Nord, Heusweiler\n" +
            "</a>\n" +
            "07:04\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42208&boardType=dep&time=07:07&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler S&#252;d, Lebach\n" +
            "</a>\n" +
            "07:07\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42207&boardType=dep&time=07:08&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler Nord, Lebach\n" +
            "</a>\n" +
            "07:08\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=41529&boardType=dep&time=07:10&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach S&#252;d\n" +
            "</a>\n" +
            "07:10\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=07:15&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "07:15\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8007868&boardType=dep&time=07:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach-Jabach\n" +
            "</a>\n" +
            "07:17\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">06:58</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/969726/348792/388410/129039/80?L=public&backLink=sq&date=30.10.17&time=06:58&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:58&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "06:58\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=06:59&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "06:59\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=07:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "07:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=07:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "07:03\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=07:24&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "07:24\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=07:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=07:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:33\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=07:36&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:36\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=07:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=07:47&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:47\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">07:13</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/134148/70275/822300/366437/80?L=public&backLink=sq&date=30.10.17&time=07:13&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=08:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=07:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "07:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=07:14&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "07:14\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=07:16&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "07:16\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=07:18&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "07:18\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=07:39&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "07:39\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=07:46&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:46\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=07:48&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:48\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=07:51&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:51\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=07:58&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "07:58\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=08:02&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:02\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-light\">\n" +
            "<td class=\"bold center sepline top\">07:27</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/446961/174446/228564/34705/80?L=public&backLink=sq&date=30.10.17&time=07:27&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8007868&boardType=dep&time=07:45&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach-Jabach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=07:27&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "07:27\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15536&boardType=dep&time=07:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "In der Hommersbach, Heusweiler\n" +
            "</a>\n" +
            "07:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15541&boardType=dep&time=07:30&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Kirschhof, Heusweiler\n" +
            "</a>\n" +
            "07:30\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15420&boardType=dep&time=07:32&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler, Heusweiler\n" +
            "</a>\n" +
            "07:32\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15421&boardType=dep&time=07:34&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Eiweiler Nord, Heusweiler\n" +
            "</a>\n" +
            "07:34\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42208&boardType=dep&time=07:37&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler S&#252;d, Lebach\n" +
            "</a>\n" +
            "07:37\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=42207&boardType=dep&time=07:38&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Landsweiler Nord, Lebach\n" +
            "</a>\n" +
            "07:38\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=41529&boardType=dep&time=07:40&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach S&#252;d\n" +
            "</a>\n" +
            "07:40\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8000563&boardType=dep&time=07:43&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach\n" +
            "</a>\n" +
            "07:43\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8007868&boardType=dep&time=07:45&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Lebach-Jabach\n" +
            "</a>\n" +
            "07:45\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr class=\"depboard-dark\">\n" +
            "<td class=\"bold center sepline top\">07:28</td>\n" +
            "<td class=\"bold top nowrap sepline\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/traininfo.exe/dn/375282/150644/310176/29997/80?L=public&backLink=sq&date=30.10.17&time=07:28&sqPf=7:1111101&sqMj=15&sqDa=30.10.17&sqTi=01:24&station_evaId=15537&sqType=dep&station_type=dep&sqEvaId=15537&\"><img src=\"/hafas-res/img/sbs_pic.gif\" width=\"24\" height=\"24\" alt=\"S      1\" style=\"vertical-align:top\"> S      1</a>\n" +
            "</td>\n" +
            "<td class=\"sepline top\">\n" +
            "<span class=\"bold\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=08:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach\n" +
            "</a>\n" +
            "</span>\n" +
            "<br />\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=07:28&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Markt, Heusweiler\n" +
            "</a>\n" +
            "07:28\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15529&boardType=dep&time=07:29&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Schulzentrum, Heusweiler\n" +
            "</a>\n" +
            "07:29\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17607&boardType=dep&time=07:31&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "M&#252;hlenstr., Walpershofen Riegelsberg\n" +
            "</a>\n" +
            "07:31\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=17600&boardType=dep&time=07:33&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Walpershofen Mitte, Riegelsberg\n" +
            "</a>\n" +
            "07:33\n" +
            "&#8226;\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10511&boardType=dep&time=07:54&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Rastpfuhl, Saarbr&#252;cken Malstatt\n" +
            "</a>\n" +
            "07:54\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10603&boardType=dep&time=08:01&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Trierer Str., Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:01\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10600&boardType=dep&time=08:03&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Hauptbahnhof, Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:03\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10619&boardType=dep&time=08:06&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Johanneskirche, Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:06\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=10602&boardType=dep&time=08:13&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "R&#246;merkastell, Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:13\n" +
            "-\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=8001135&boardType=dep&time=08:17&maxJourneys=15&productsFilter=7:1111101&\">\n" +
            "Brebach Bf, Saarbr&#252;cken\n" +
            "</a>\n" +
            "08:17\n" +
            "</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td class=\"small sepline\">&nbsp;</td>\n" +
            "<td class=\"small sepline\">&nbsp;</td>\n" +
            "<td class=\"small sepline\">\n" +
            "Anzeige aller Halte bis zu diesem Zeichen &#149;, dahinter Anzeige der wichtigsten Halte.\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"hafasButtons\" cellspacing=\"0\">\n" +
            "<tr>\n" +
            "<td class=\"links\">\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=07:28%2B1&productsFilter=7:1111101&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&maxJourneys=15&&start=yes&dirInput=&\"><b><img class=\"icon\" src=\"/hafas-res/img/icon_std.gif\" width=\"13\" height=\"13\" alt=\"\" /> Weitere Fahrten</b></a>\n" +
            "</td>\n" +
            "<td class=\"links\">\n" +
            "F&#252;r weitere Fahrplaninformationen w&#228;hlen Sie bitte Ihre gew&#252;nschte Uhrzeit:\n" +
            "<select name=\"selectTime\" onchange=\"window.location.replace(this.options[this.selectedIndex].value)\">\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=00:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">00:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=01:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">01:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=02:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">02:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=03:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">03:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=04:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">04:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=05:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">05:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=06:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">06:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=07:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">07:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=08:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">08:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=09:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">09:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=10:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">10:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=11:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">11:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=12:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">12:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=13:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">13:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=14:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">14:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=15:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">15:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=16:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">16:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=17:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">17:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=18:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">18:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=19:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">19:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=20:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">20:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=21:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">21:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=22:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">22:00</option>\n" +
            "<option value=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=23:00&maxJourneys=15&dateBegin=30.10.17&dateEnd=30.10.17&selectDate=today&productsFilter=7:1111101&start=yes&dirInput=&\">23:00</option>\n" +
            "</select>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<div class=\"separator\" style=\"margin-top:10px; margin-bottom:1px;\"><img src=\"/hafas-res/img/stress_sep.gif\" width=\"11\" height=\"11\" alt=\"\" />Markt, Heusweiler - Allgemeine Informationen</div>\n" +
            "<a name=\"end\"></a>\n" +
            "<table cellspacing=\"0\" class=\"hafasResult\">\n" +
            "<tr>\n" +
            "<td valign=\"top\">\n" +
            "<span class=\"bold\">Haltestellen in Fu&#223;wegn&#228;he:</span>\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=115500&boardType=dep&time=now&productsFilter=7:1111101&\">Rathaus, Heusweiler</a> (ca. 3 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=115502&boardType=dep&time=now&productsFilter=7:1111101&\">Kath. Kirche, Heusweiler</a> (ca. 13 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15510&boardType=dep&time=now&productsFilter=7:1111101&\">Dilsburg Br&#252;cke, Heusweiler</a> (ca. 11 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15538&boardType=dep&time=now&productsFilter=7:1111101&\">Dilsburg Ludwigstr., Heusweiler</a> (ca. 14 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15501&boardType=dep&time=now&productsFilter=7:1111101&\">Illinger Str., Heusweiler</a> (ca. 8 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15507&boardType=dep&time=now&productsFilter=7:1111101&\">Kirchstr., Heusweiler</a> (ca. 8 Minuten)\n" +
            "<br />\n" +
            "- <a href=\"http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15516&boardType=dep&time=now&productsFilter=7:1111101&\">Ev. Kirche, Heusweiler</a> (ca. 7 Minuten)\n" +
            "<br />\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"hafasFooter\" cellspacing=\"0\" summary=\"Fu&#223;bereich\">\n" +
            "<tr>\n" +
            "<td>\n" +
            "Reiseinformationen g&#252;ltig vom 11.12.16 bis 09.12.17.\n" +
            "<br />\n" +
            "Software/Daten: HAFAS 5.42.STANDARD.4.7/5.42.STANDARD.5.0.1 - 30.10.17\n" +
            "<br />\n" +
            "&#169; 2017\n" +
            "<a href=\"http://www.vgs-online.de/\">VGS mbH</a>,&nbsp;\n" +
            "<a href=\"http://www.hacon.de/hacon/index.shtml\">HaCon Ingenieurgesellschaft mbH</a>.\n" +
            "Alle Angaben ohne Gew&#228;hr.\n" +
            "<a href=\"http://www.saarfahrplan.de/cgi-bin/help.exe/dn?tpl=imprint&\" target=\"imprint\" onclick=\"openPopup('about:blank','imprint',200,200,325,275,'000000')\">Impressum</a>\n" +
            "<br />\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

}

