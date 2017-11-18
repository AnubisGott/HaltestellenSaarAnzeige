package org.sge.stationdisplaysb.model;

/**
 * Created by Admin on 18.11.2017.
 */

public class StationIdName {
    private String id;
    private String name;

    public StationIdName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}
