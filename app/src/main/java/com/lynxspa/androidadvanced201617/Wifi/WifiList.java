package com.lynxspa.androidadvanced201617.wifi;

public class WifiList{

    private String ssid;
    private String bssid;
    private String signal;
    private int profileId;

    public WifiList(String ssid, String bssid, String signal, int profileId) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.signal = signal;
        this.profileId=profileId;
    }


    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
