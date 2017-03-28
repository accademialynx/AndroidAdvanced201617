package com.lynxspa.androidadvanced201617.profile;

public class Profilo {

    private int id;
    private String name;
    private int radioButton;
    private int brigthnesBar;
    private int brightnessCheckBox;
    private int volumeBarRing;
    private int volumeBarMusic;
    private int volumeBarNotification;
    private int bluetoothSwitch;
    private int wifiSwitch;
    private String appName;

    public Profilo(int id, String name, int radioButton, int brigthnesBar, int brightnessCheckBox,
                   int volumeBarRing, int volumeBarMusic, int volumeBarNotification, int bluetoothSwitch, int wifiSwitch,String appName) {
        this.id = id;
        this.name = name;
        this.radioButton= radioButton;
        this.brigthnesBar = brigthnesBar;
        this.brightnessCheckBox = brightnessCheckBox;
        this.volumeBarRing = volumeBarRing;
        this.volumeBarMusic=volumeBarMusic;
        this.volumeBarNotification=volumeBarNotification;
        this.bluetoothSwitch = bluetoothSwitch;
        this.wifiSwitch = wifiSwitch;
        this.appName=appName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(int radioButton) {
        this.radioButton = radioButton;
    }

    public int getBrigthnesBar() {
        return brigthnesBar;
    }

    public void setBrigthnesBar(int brigthnesBar) {
        this.brigthnesBar = brigthnesBar;
    }

    public int getBrightnessCheckBox() {
        return brightnessCheckBox;
    }

    public void setBrightnessCheckBox(int brightnessCheckBox) {
        this.brightnessCheckBox = brightnessCheckBox;
    }

    public int getVolumeBarRing() {
        return volumeBarRing;
    }

    public void setVolumeBarRing(int volumeBarRing) {
        this.volumeBarRing = volumeBarRing;
    }

    public int getVolumeBarMusic() {
        return volumeBarMusic;
    }

    public void setVolumeBarMusic(int volumeBarMusic) {
        this.volumeBarMusic = volumeBarMusic;
    }

    public int getVolumeBarNotification() {
        return volumeBarNotification;
    }

    public void setVolumeBarNotification(int volumeBarNotification) {
        this.volumeBarNotification = volumeBarNotification;
    }

    public int getBluetoothSwitch() {
        return bluetoothSwitch;
    }

    public void setBluetoothSwitch(int bluetoothSwitch) {
        this.bluetoothSwitch = bluetoothSwitch;
    }

    public int getWifiSwitch() {
        return wifiSwitch;
    }

    public void setWifiSwitch(int wifiSwitch) {
        this.wifiSwitch = wifiSwitch;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
