package sge.bahn;

import android.text.Html;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;


public class ParseHTML {
	public final static String HAFASCONTENT_TABLE_TAG_NAME =  "hafasContentTable";

	public final static String COLUMN_NAME_ZEIT       =  "Zeit";
	public final static String COLUMN_NAME_PROGNOSE   =  "Prognose";
	public final static String COLUMN_NAME_FAHRT      =  "Fahrt";
	public final static String COLUMN_NAME_InRichtung =  "In Richtung";

	private TagNode rootTagNode = null;

	private String exchangeUmlaute(String input) {
		return input
				//.replaceAll("&#252;", "ü") // repaced by Html.fromHtml(returnString).toString() in the get-methods
				//.replaceAll("&#220;", "Ü")
				//.replaceAll("&#246;", "ö")
				//.replaceAll("&#214;", "Ö")
				//.replaceAll("&#228;", "ä")
				//.replaceAll("&#196;", "Ä")
				//.replaceAll("&#223;", "ß")
				//.replaceAll("&#225;", "á")
				.replaceAll("&nbsp;", " ")
				.replaceAll("&quot;", " ")
				.replaceAll("&gt;", " ")
				.replaceAll("&lt;", " ")
				.replaceAll("&amp;", " ")
				.replaceAll("&apos;", " ");
	}


	public ParseHTML(String bahnRequestResult) {
		//System.out.println("String bahnRequestResult: " + bahnRequestResult);
		//TagNode rootTagNode = new HtmlCleaner().clean(exchangeUmlaute(bahnRequestResult), "windows-1252"); charset=ISO-8859-1
		TagNode rootTagNode = new HtmlCleaner().clean(exchangeUmlaute(bahnRequestResult));
		this.rootTagNode = rootTagNode;
	}


	public int countHafasContentTables() {
		try {
			TagNode[] tagNodes = rootTagNode.getElementsByName("td", true);

			int count=0;
			for(int i=0; i<tagNodes.length; i++) {
				//System.out.println("i: " + i + "  Name: " + tagNodes[i].getName());
				String classAttribute = tagNodes[i].getAttributeByName("class");
				//System.out.println("  class attribute: " + classAttribute);
				if(classAttribute != null) {
					if(HAFASCONTENT_TABLE_TAG_NAME.contentEquals(classAttribute)) {
						count++;
					}
				}
				// System.out.println("i: " + i + "  Name: " + tagNodes[i].getName() + "  text: " + tagNodes[i].getText());
			}

			return count;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return 0;
		}
	}


	TagNode hafasContentTable_Cache = null;
	boolean hafasContentTable_Cached = false;

	public TagNode getHafasContentTable() {
		if(hafasContentTable_Cached) return hafasContentTable_Cache;

		try {
			TagNode[] tagNodes = rootTagNode.getElementsByName("td", true);

			for(int i=0; i<tagNodes.length; i++) {
				//System.out.println("i: " + i + "  Name: " + tagNodes[i].getName());
				String classAttribute = tagNodes[i].getAttributeByName("class");
				//System.out.println("  class attribute: " + classAttribute);
				if(classAttribute != null) {
					if(HAFASCONTENT_TABLE_TAG_NAME.contentEquals(classAttribute)) {
						hafasContentTable_Cache = tagNodes[i];
						hafasContentTable_Cached = true;
						return tagNodes[i];
					}
				}
			}

			return null;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}



	public String getAbfrageZeiten() {
		try {
			TagNode hafasContentTable = getHafasContentTable();

			TagNode[] divElements = hafasContentTable.getElementsByName("div", false);
			for(int i=0; i<divElements.length; i++) {
				String text = divElements[i].getText().toString();
				if(text != null) {
					// System.out.println("i: " + i + "  text: " + divElements[i].getText().toString());
					if(text.startsWith("Abfahrt")) {
						return text;
					}
				}
			}

			return "";
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return "";
		}
	}


	TagNode timeTable_cache = null;
	boolean timeTable_chached = false;
	public TagNode getTimeTable() {
		if(timeTable_chached) return timeTable_cache;

		try {
			TagNode hafas = getHafasContentTable();
			TagNode[] tableElements = hafas.getElementsByName("table", false);

			int indexTable = 0;
			for(int i=0; i<tableElements.length; i++) {
				String classNameAttribute = tableElements[i].getAttributeByName("class");
				if(classNameAttribute != null) {
					if(classNameAttribute.equals("hafasResult")) {
						indexTable++;
						if(indexTable == 2) {
							timeTable_cache = tableElements[i].getElementsByName("tbody", false)[0];
							timeTable_chached = true;

							return tableElements[i].getElementsByName("tbody", false)[0];
						}
					}
				}
			}


			return null;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return null;
		}

	}


	HashMap<Integer, TagNode> rowTimeTable_cache = new HashMap<Integer, TagNode>();
	public TagNode getRowTimeTable(int row) {
		if(rowTimeTable_cache.containsKey(row)) {
			return rowTimeTable_cache.get(row);
		}

		try {
			TagNode timeTable = getTimeTable();
			TagNode[] rowElements = timeTable.getElementsByName("tr", false);

			int indexRow = 0;
			for(int i=0; i<rowElements.length; i++) {
				String classNameAttribute = rowElements[i].getAttributeByName("class");
				if(classNameAttribute != null) {
					if(classNameAttribute.startsWith("depboard")) {
						if(indexRow == row) {
							rowTimeTable_cache.put(row, rowElements[i]);
							return rowElements[i];
						}
						indexRow++;
					}
				}
			}


			return null;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}


	TagNode headerRowTimeTable_cache = null;
	boolean headerRowTimeTable_cached = false;
	public TagNode getHeaderRowTimeTable() {
		if(headerRowTimeTable_cached) return headerRowTimeTable_cache;
		try {
			TagNode timeTable = getTimeTable();
			TagNode[] rowElements = timeTable.getElementsByName("tr", false);
			headerRowTimeTable_cache = rowElements[0];
			headerRowTimeTable_cached = true;
			return rowElements[0];
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}


	HashMap<String, Integer> columnNumber_cache = new HashMap<String, Integer>();
	public int getColumnNumber(String columnHeaderText) {
		if(columnHeaderText == null) return -1;

		if(columnNumber_cache.containsKey(columnHeaderText)) {
			// System.out.println("getColumnNumber cache was used: " + columnHeaderText + "  v: " + columnNumber_cache.get(columnHeaderText));
			return columnNumber_cache.get(columnHeaderText);
		}

		try {
			TagNode headerRow = getHeaderRowTimeTable();

			TagNode[] columHeaders = headerRow.getElementsByName("th", false);
			for(int col=0; col<columHeaders.length; col++) {
				// System.out.println("column Header " + col + "  : " + columHeaders[col].getText().toString());
				if(columnHeaderText.equals(columHeaders[col].getText().toString().trim())) {
					columnNumber_cache.put(columnHeaderText, col);
					return col;
				}
			}
			return -1;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return -1;
		}
	}


	public int countRowsTimeTable() {
		try {
			TagNode timeTable = getTimeTable();
			TagNode[] rowElements = timeTable.getElementsByName("tr", false);

			int countRows = 0;
			for(int i=0; i<rowElements.length; i++) {
				String classNameAttribute = rowElements[i].getAttributeByName("class");
				if(classNameAttribute != null) {
					if(classNameAttribute.startsWith("depboard")) {
						countRows++;
					}
				}
			}

			return countRows;
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return 0;
		}
	}


	public String getDepartureRow(int r) {
		try {
			TagNode rowElement = getRowTimeTable(r);

			TagNode[] depatureTimeNode = rowElement.getElementsByName("td", false);
			return depatureTimeNode[getColumnNumber(COLUMN_NAME_ZEIT)].getText().toString();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return "?";
		}
	}


	public String getPredictionRow(int r) {
		try {
			TagNode rowElement = getRowTimeTable(r);
			int columnNumber = getColumnNumber(COLUMN_NAME_PROGNOSE);
			if(columnNumber == -1) return "";

			TagNode[] depatureTimeNode = rowElement.getElementsByName("td", false);
			String returnString = depatureTimeNode[columnNumber].getText().toString().trim();
			return Html.fromHtml(returnString).toString();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return "?";
		}

	}


	public String getLineRow(int r) {
		try {
			TagNode rowElement = getRowTimeTable(r);

			TagNode[] lineNodes = rowElement.getElementsByName("td", false);
			TagNode aHRef = lineNodes[getColumnNumber(COLUMN_NAME_FAHRT)];
			String returnString =  aHRef.getElementsByName("a", false)[0].getText().toString().replaceAll(" ", "").trim();
			return Html.fromHtml(returnString).toString();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return "?";
		}
	}


	public String getDestinationRow(int r) {
		try {
			TagNode rowElement = getRowTimeTable(r);

			TagNode[] lineNodes = rowElement.getElementsByName("td", false);
			TagNode td = lineNodes[getColumnNumber(COLUMN_NAME_InRichtung)];
			TagNode[] spans = td.getElementsByName("span", false);
			TagNode span = spans[0];
			TagNode[] aHRefs = span.getElementsByName("a", false);
			TagNode aHRef = aHRefs[0];

			String returnString = aHRef.getText().toString().trim();
			return Html.fromHtml(returnString).toString();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			return "?";
		}
	}

}
