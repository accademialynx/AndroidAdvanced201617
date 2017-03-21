package com.lynxspa.androidadvanced201617.profileDir;

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
    private String longitude;
    private String latitude;
    private String wifiSSID;
    private String wifiBSSID;
    private String wifiSignal;
    private String nfcTagId;
    private String beaconId;
    private String beaconName;
    private String beaconSignal;
    private String appName;


    public Profilo(int id, String name, int radioButton, int brigthnesBar, int brightnessCheckBox, int volumeBarRing, int volumeBarMusic, int volumeBarNotification, int bluetoothSwitch, int wifiSwitch
    ,String longitude,String latitude,String wifiSSID,String wifiBSSID,String wifiSignal,String nfcTagId,String beaconNam,String beaconIde,String beaconSignal,String appName) {
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
        this.longitude=longitude;
        this.latitude=latitude;
        this.wifiSSID=wifiSSID;
        this.wifiBSSID=wifiBSSID;
        this.wifiSignal=wifiSignal;
        this.nfcTagId=nfcTagId;
        this.beaconId=beaconId;
        this.beaconName=beaconName;
        this.beaconSignal=beaconSignal;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiBSSID() {
        return wifiBSSID;
    }

    public void setWifiBSSID(String wifiBSSID) {
        this.wifiBSSID = wifiBSSID;
    }

    public String getWifiSignal() {
        return wifiSignal;
    }

    public void setWifiSignal(String wifiSignal) {
        this.wifiSignal = wifiSignal;
    }

    public String getNfcTagId() {
        return nfcTagId;
    }

    public void setNfcTagId(String nfcTagId) {
        this.nfcTagId = nfcTagId;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public String getBeaconSignal() {
        return beaconSignal;
    }

    public void setBeaconSignal(String beaconSignal) {
        this.beaconSignal = beaconSignal;
    }
}
