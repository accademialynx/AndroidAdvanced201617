package com.lynxspa.androidadvanced201617.profileDir;

public class ProfiloEncrypted {

    private int id;
    private String name;
    private String radioButton;
    private String brigthnesBar;
    private String brightnessCheckBox;
    private String volumeBarRing;
    private String volumeBarMusic;
    private String volumeBarNotification;
    private String bluetoothSwitch;
    private String wifiSwitch;
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

    public ProfiloEncrypted(int id, String name, String radioButton, String brigthnesBar, String brightnessCheckBox, String volumeBarRing, String volumeBarMusic, String volumeBarNotification, String bluetoothSwitch, String wifiSwitch
            ,String longitude,String latitude,String wifiSSID,String wifiBSSID,String wifiSignal,String nfcTagId,String beaconName,String beaconId,String beaconSignal,String appName) {
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

    public String getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(String radioButton) {
        this.radioButton = radioButton;
    }

    public String getBrigthnesBar() {
        return brigthnesBar;
    }

    public void setBrigthnesBar(String brigthnesBar) {
        this.brigthnesBar = brigthnesBar;
    }

    public String getBrightnessCheckBox() {
        return brightnessCheckBox;
    }

    public void setBrightnessCheckBox(String brightnessCheckBox) {
        this.brightnessCheckBox = brightnessCheckBox;
    }

    public String getVolumeBarRing() {
        return volumeBarRing;
    }

    public void setVolumeBarRing(String volumeBarRing) {
        this.volumeBarRing = volumeBarRing;
    }

    public String getVolumeBarMusic() {
        return volumeBarMusic;
    }

    public void setVolumeBarMusic(String volumeBarMusic) {
        this.volumeBarMusic = volumeBarMusic;
    }

    public String getVolumeBarNotification() {
        return volumeBarNotification;
    }

    public void setVolumeBarNotification(String volumeBarNotification) {
        this.volumeBarNotification = volumeBarNotification;
    }

    public String getBluetoothSwitch() {
        return bluetoothSwitch;
    }

    public void setBluetoothSwitch(String bluetoothSwitch) {
        this.bluetoothSwitch = bluetoothSwitch;
    }

    public String getWifiSwitch() {
        return wifiSwitch;
    }

    public void setWifiSwitch(String wifiSwitch) {
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
