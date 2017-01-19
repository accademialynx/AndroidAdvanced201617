package com.lynxspa.androidadvanced201617.data;

import com.lynxspa.androidadvanced201617.Utils.SurveyType;

/**
 * Created by Samuele on 17/01/2017.
 */

public class Profile {



    private int id;
    private String profileName;
    private SurveyType surveyType;
    private int brightness;
    private boolean autoBrightness;
    private int volume;
    private boolean bluetooth;
    private boolean wifi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SurveyType getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(SurveyType surveyType) {
        this.surveyType = surveyType;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public boolean isAutoBrightness() {
        return autoBrightness;
    }

    public void setAutoBrightness(boolean autoBrightness) {
        this.autoBrightness = autoBrightness;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
}
