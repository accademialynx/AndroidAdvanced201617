package com.lynxspa.androidadvanced201617.WifiDir;

/**
 * Created by Esami on 07/03/2017.
 */
public class WifiList {

    private String ssid;
    private String bssid;
    private String signal;

    public WifiList(String ssid, String bssid, String signal) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.signal = signal;
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
}
