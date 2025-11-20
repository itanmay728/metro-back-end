package com.metro.metrobackend.models;

public class MetroStation {

    private String id;
    private String name;
    private double lat;
    private double lon;

    // NEW FIELDS
    private String lineName;
    private String color;

    public MetroStation(String id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
       	this.lon = lon;
    }

    // =====================
    // GETTERS
    // =====================

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getLineName() {
        return lineName;
    }

    public String getColor() {
        return color;
    }

    // =====================
    // SETTERS
    // =====================

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
