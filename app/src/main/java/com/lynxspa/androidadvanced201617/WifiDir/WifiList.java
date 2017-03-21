package com.lynxspa.androidadvanced201617.WifiDir;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiList implements Parcelable {

    private String ssid;
    private String bssid;
    private String signal;

    public WifiList(String ssid, String bssid, String signal) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.signal = signal;
    }

    protected WifiList(Parcel in) {
        ssid = in.readString();
        bssid = in.readString();
        signal = in.readString();
    }

    public static final Creator<WifiList> CREATOR = new Creator<WifiList>() {
        @Override
        public WifiList createFromParcel(Parcel in) {
            return new WifiList(in);
        }

        @Override
        public WifiList[] newArray(int size) {
            return new WifiList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ssid);
        dest.writeString(bssid);
        dest.writeString(signal);
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
