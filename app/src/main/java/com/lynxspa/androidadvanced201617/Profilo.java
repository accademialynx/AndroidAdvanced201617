package com.lynxspa.androidadvanced201617;

import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by Esami on 24/01/2017.
 */
public class Profilo {

    private int id;
    private String name;
    private int radioGroup;
    private int brigthnesBar;
    private int brightnessCheckBox;
    private int volumeBar;
    private int bluetoothSwitch;
    private int wifiSwitch;

    public Profilo(int id, String name, int radioGroup, int brigthnesBar, int brightnessCheckBox, int volumeBar, int bluetoothSwitch, int wifiSwitch) {
        this.id = id;
        this.name = name;
        this.radioGroup = radioGroup;
        this.brigthnesBar = brigthnesBar;
        this.brightnessCheckBox = brightnessCheckBox;
        this.volumeBar = volumeBar;
        this.bluetoothSwitch = bluetoothSwitch;
        this.wifiSwitch = wifiSwitch;
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

    public int getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(int radioGroup) {
        this.radioGroup = radioGroup;
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

    public int getVolumeBar() {
        return volumeBar;
    }

    public void setVolumeBar(int volumeBar) {
        this.volumeBar = volumeBar;
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

}
