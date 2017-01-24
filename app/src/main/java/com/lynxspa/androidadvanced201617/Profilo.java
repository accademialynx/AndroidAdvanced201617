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
    private RadioGroup radioGroup;
    private SeekBar brigthnesBar;
    private CheckBox brightnessCheckBox;
    private SeekBar volumeBar;
    private Switch bluetoothSwitch;
    private Switch wifiSwitch;

    public Profilo(int id, String name, RadioGroup radioGroup, SeekBar brigthnesBar, CheckBox brightnessCheckBox, SeekBar volumeBar, Switch bluetoothSwitch, Switch wifiSwitch) {
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

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public SeekBar getBrigthnesBar() {
        return brigthnesBar;
    }

    public void setBrigthnesBar(SeekBar brigthnesBar) {
        this.brigthnesBar = brigthnesBar;
    }

    public CheckBox getBrightnessCheckBox() {
        return brightnessCheckBox;
    }

    public void setBrightnessCheckBox(CheckBox brightnessCheckBox) {
        this.brightnessCheckBox = brightnessCheckBox;
    }

    public SeekBar getVolumeBar() {
        return volumeBar;
    }

    public void setVolumeBar(SeekBar volumeBar) {
        this.volumeBar = volumeBar;
    }

    public Switch getBluetoothSwitch() {
        return bluetoothSwitch;
    }

    public void setBluetoothSwitch(Switch bluetoothSwitch) {
        this.bluetoothSwitch = bluetoothSwitch;
    }

    public Switch getWifiSwitch() {
        return wifiSwitch;
    }

    public void setWifiSwitch(Switch wifiSwitch) {
        this.wifiSwitch = wifiSwitch;
    }

}
