package sge.bahn;

/**
 * Created by Admin on 17.11.2017.
 */

public class VolleyResponse {
    private static VolleyResponse instance = null;
    private static int requestCounter = 0;
    private static String response = "";

    private VolleyResponse() {}

    public static VolleyResponse getInstance() {
        if(instance == null) return new VolleyResponse();
        else return instance;
    }


    public void receiveResponse(String data) {
        System.out.println("==========================================");
        System.out.println("VolleyResponse::receiveResponse()");
        // System.out.println(data);
        response = data;
        requestCounter++;
        System.out.println("requestCounter: " + requestCounter);
    }


    public int getCounter() {
        return requestCounter;
    }

    public String getResponse() {
        return response;
    }

}
