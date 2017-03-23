package com.lynxspa.androidadvanced201617.mapDir;

public class Mappa {

    private String name;
    private String lon;
    private String lat;
    private int profileId;

    public Mappa(String name, String lon, String lat, int profileId) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.profileId=profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
