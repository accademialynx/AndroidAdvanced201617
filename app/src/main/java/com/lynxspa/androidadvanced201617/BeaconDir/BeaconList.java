package com.lynxspa.androidadvanced201617.BeaconDir;

public class BeaconList {

    private String nameBeacon;
    private String distanceBeacon;
    private String addressBeacon;
    private int profileId;

    public BeaconList(String nameBeacon, String addressBeacon, String distanceBeacon, int profileId) {
        this.nameBeacon = nameBeacon;
        this.addressBeacon = addressBeacon;
        this.distanceBeacon = distanceBeacon;
        this.profileId = profileId;
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
