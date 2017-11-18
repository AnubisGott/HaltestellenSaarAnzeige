package sge.bahn;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.sge.stationdisplaysb.ChangeStationActivity;
import org.sge.stationdisplaysb.DisplayTimerActivity;

public class BahnRequest {
	public final static String REQUEST_TAG   = "BahnRequestDBV1.0";
	public final static String auskunftUrl   = "http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn?L=public&input=15537&boardType=dep&time=now&productsFilter=7:1111101&selectDate=today&maxJourneys=15&start=yes";
	public final static String predictionUrl = "http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStopsS0A=255&getstop=1&noSession=yes&REQ0JourneyStopsB=12&REQ0JourneyStopsS0G=heus?&js=true&";


	private static String responseString = "";

	public static void createBahnRequest(Context context, final DisplayTimerActivity displayActivity, boolean alle, int stationId) {
		System.out.println("createBahnRequest: " + stationId);
		// Instantiate the RequestQueue.
		VolleyQueue.getInstance().getQueue(context).cancelAll(REQUEST_TAG);
		System.out.println("Queue" + VolleyQueue.getInstance().getQueue(context).getSequenceNumber());

		// Request a string response from the provided URL.
		String auskunftUrl = getUrlAuskunftUrl(alle, stationId);
		System.out.println("request url: " + auskunftUrl);

		StringRequest stringRequest = new StringRequest(Request.Method.GET, auskunftUrl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response string.
						// mTextView.setText("Response is: "+ response.substring(0,500));
						// return response;
						responseString = response;
						System.out.println("Request response arrived" /*+ response*/);
						ParseHTML p = new ParseHTML(response);
						String abfrageZeiten = p.getAbfrageZeiten();
						displayActivity.sendResponse(p);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// mTextView.setText("That didn't work!");
				responseString = "";
				System.out.println("Request failed");
			}
		});
		// Add the request to the RequestQueue.
		stringRequest.setTag(REQUEST_TAG);
		VolleyQueue.getInstance().getQueue(context).add(stringRequest);
		System.out.println("request added to queue");
	}


	public static String getBahnRequest(Context context, int stationId, boolean alle) {
		System.out.println("createBahnRequest: " + stationId);
		VolleyQueue.getInstance().getQueue(context).cancelAll(REQUEST_TAG);
		System.out.println("Queue" + VolleyQueue.getInstance().getQueue(context).getSequenceNumber());

		// Request a string response from the provided URL.
		String auskunftUrl = getUrlAuskunftUrl(alle, stationId);
		System.out.println("request url: " + auskunftUrl);


		int currentVolleyCounter = VolleyResponse.getInstance().getCounter();
		StringRequest stringRequest = new StringRequest(Request.Method.GET, auskunftUrl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						VolleyResponse.getInstance().receiveResponse(response);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println("Request failed");
			}
		});
		// Add the request to the RequestQueue.
		stringRequest.setTag(REQUEST_TAG);
		VolleyQueue.getInstance().getQueue(context).add(stringRequest);
		System.out.println("request added to queue. Counter: " + currentVolleyCounter);

		if(waitForResponse(currentVolleyCounter)) {
			System.out.println(VolleyResponse.getInstance().getResponse());
			return VolleyResponse.getInstance().getResponse();
		}

		return "";
	}


	static public void waitMillis(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	private static boolean responseReceived(int volleyCounterRequestTime) {
		System.out.println("wait for response getCounter from Volley: " + VolleyResponse.getInstance().getCounter() + "   volleyCounterRequestTime: " + volleyCounterRequestTime + "   responseReceived: " + (VolleyResponse.getInstance().getCounter() > volleyCounterRequestTime));
		return VolleyResponse.getInstance().getCounter() > volleyCounterRequestTime;
	}


	private static boolean timeoutHasReached(long startTime, long timeoutValue) {
		return System.currentTimeMillis() > startTime + timeoutValue;
	}


	private static boolean waitForResponse(int currentVolleyCounter) {
		long startTime = System.currentTimeMillis();
		long timeoutValue = 5000;

		while(!timeoutHasReached(startTime, timeoutValue) && !responseReceived(currentVolleyCounter)){
			System.out.println("waiting cycle: " + (System.currentTimeMillis() - startTime));
			System.out.println("timeout has reached: " + timeoutHasReached(startTime, timeoutValue));
			System.out.println("response received: " + responseReceived(currentVolleyCounter));
			waitMillis(200);
		}

		System.out.println("timeout has reached: " + timeoutHasReached(startTime, timeoutValue));
		System.out.println("response received: " + responseReceived(currentVolleyCounter));

		if(responseReceived(currentVolleyCounter)) return true;
		else return false;
	}


	public static void createSuggestionRequest(Context context, final ChangeStationActivity changeStationActivity, String searchString) {
		System.out.println("Queue" + VolleyQueue.getInstance().getQueue(context).getSequenceNumber());

		// Request a string response from the provided URL.
		// String auskunftUrl = predictionUrl;
		String auskunftUrl = getSuggestionUrl(searchString);
		System.out.println("request url: " + auskunftUrl);

		StringRequest stringRequest = new StringRequest(Request.Method.GET, auskunftUrl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						responseString = response;
						System.out.println("Request suggestion response arrived" /*+ response*/);
						changeStationActivity.sendSuggestionResponse(extractJsonString(responseString));
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// mTextView.setText("That didn't work!");
				responseString = "";
				System.out.println("Request failed");
			}
		});
		// Add the request to the RequestQueue.
		stringRequest.setTag(REQUEST_TAG);
		VolleyQueue.getInstance().getQueue(context).add(stringRequest);
		System.out.println("request added to queue");
	}


	public static String createSuggestionRequest(Context context, String searchString) {
		System.out.println("Queue" + VolleyQueue.getInstance().getQueue(context).getSequenceNumber());

		// Request a string response from the provided URL.
		// String auskunftUrl = predictionUrl;
		String auskunftUrl = getSuggestionUrl(searchString);
		System.out.println("request url: " + auskunftUrl);

		int currentVolleyCounter = VolleyResponse.getInstance().getCounter();
		StringRequest stringRequest = new StringRequest(Request.Method.GET, auskunftUrl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						VolleyResponse.getInstance().receiveResponse(response);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println("Request failed");
			}
		});
		// Add the request to the RequestQueue.
		stringRequest.setTag(REQUEST_TAG);
		VolleyQueue.getInstance().getQueue(context).add(stringRequest);
		System.out.println("request added to queue. Counter: " + currentVolleyCounter);

		if(waitForResponse(currentVolleyCounter)) {
			System.out.println(VolleyResponse.getInstance().getResponse());
			return VolleyResponse.getInstance().getResponse();
		}

		return "";
	}


	public static String extractJsonString(String s) {
		int start = s.indexOf('{');
		int end   = s.lastIndexOf('}') + 1;
		return s.substring(start,  end);
	}


	public static String getUrlAuskunftUrl(boolean alle, int stationId) {
		String baseUrl       = "http://www.saarfahrplan.de/cgi-bin/stboard.exe/dn";
		String paraL         = "L=public";
		String paraStation   = "input=" + stationId;
		String paraBoardType = "boardType=dep";
		String paraTime      = "time=now";

		String paraProductFilter   = "productsFilter=7:1111101";
		if(alle) paraProductFilter = "";

		String paraDate = "selectDate=today";
		String paraCountJourneys = "maxJourneys=15";
		String paraStart = "start=yes";

		return baseUrl + "?" + paraL + "&" + paraStation + "&" + paraBoardType + "&" + paraTime + "&" + paraProductFilter + "&" + paraDate + "&" + paraCountJourneys + "&" + paraStart;
	}


	public static String getSuggestionUrl(String search) {
		// final String predictionUrl_1 = "http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStopsS0A=255&getstop=1&noSession=yes&REQ0JourneyStopsB=12&REQ0JourneyStopsS0G=";
		// final String predictionUrl_1 = "http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStopsS0A=255&getstop=1&noSession=yes&REQ0JourneyStopsB=255&REQ0JourneyStopsS0G=";
		final String predictionUrl_1 = "http://www.saarfahrplan.de/cgi-bin/ajax-getstop.exe/dny?start=1&tpl=suggest2json&REQ0JourneyStopsS0A=255&getstop=1&noSession=yes&REQ0JourneyStopsB=255&REQ0JourneyStopsS0G=255";
		final String predictionUrl_2 = "?&js=true&";

		return predictionUrl_1 + search + predictionUrl_2;
	}


	public static void cancelAll(Context context) {
		VolleyQueue.getInstance().getQueue(context).cancelAll(REQUEST_TAG);
	}

}
