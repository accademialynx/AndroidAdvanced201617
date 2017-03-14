package com.lynxspa.androidadvanced201617.BeaconDir;

import android.app.Activity;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Bundle;

import com.lynxspa.androidadvanced201617.R;

import java.util.ArrayList;

/**
 * Created by kisho on 14/03/2017.
 */
public class BeaconActivity extends Activity{
/*
    private BeaconManager beaconManager;
    private BluetoothLeScanner bluetoothLeScanner;
    private ScanSettings mScanSettings;
    private ScanRecord mScanRecord;


    private void  setScanSettings(){
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setReportDelay(0);
        builder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        // beaconManager.getBeaconParsers().add(new BeaconParser().
        //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);

        bluetoothLeScanner.startScan(new ArrayList<ScanFilter>(), mScanSettings, new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                String deviceName = mScanRecord ;
            }
        });


    }*/

}
