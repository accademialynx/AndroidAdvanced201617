package com.lynxspa.androidadvanced201617.BeaconDir;

public class BeaconList {

    private String nameBeacon;
    private String distanceBeacon;
    private String addressBeacon;

    public BeaconList(String nameBeacon, String distanceBeacon, String addressBeacon) {
        this.nameBeacon = nameBeacon;
        this.distanceBeacon = distanceBeacon;
        this.addressBeacon = addressBeacon;
    }

    public String getNameBeacon() {
        return nameBeacon;
    }

    public void setNameBeacon(String nameBeacon) {
        this.nameBeacon = nameBeacon;
    }

    public String getDistanceBeacon() {
        return distanceBeacon;
    }

    public void setDistanceBeacon(String distanceBeacon) {
        this.distanceBeacon = distanceBeacon;
    }

    public String getAddressBeacon() {
        return addressBeacon;
    }

    public void setAddressBeacon(String addressBeacon) {
        this.addressBeacon = addressBeacon;
    }
}
