package com.lynxspa.androidadvanced201617.BeaconDir;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by kisho on 14/03/2017.
 */
public class BeaconActivity extends Activity implements BeaconConsumer{

    private ScanSettings mScanSettings;
    private BeaconManager beaconManager;
    ListView beaconListView;
    List<BeaconList> arrayListBeacons=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        beaconListView=(ListView)findViewById(R.id.beaconList);
        beaconManager=BeaconManager.getInstanceForApplication(this);
        mScanSettings=setScanSettings();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);
    }

    private ScanSettings setScanSettings(){
        ScanSettings.Builder mBuilder=new ScanSettings.Builder();
        mBuilder.setReportDelay(0);
        mBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
        mScanSettings = mBuilder.build();
        return mScanSettings;
    }

    @Override
    public void onBeaconServiceConnect() {
        final org.altbeacon.beacon.Region region=new org.altbeacon.beacon.Region("myMonitoringINiqueID",null,null,null);
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, org.altbeacon.beacon.Region region) {
                for(Beacon b:collection){
                    String address=b.getBluetoothAddress();
                    String name=b.getBluetoothName();
                    double distance=b.getDistance();
                    Log.d("AAAAAAAAAA",address+name+distance);
                }
                /*per lòa prssoma volta fare l'adapter */
            }
        });
        try {
            beaconManager.startRangingBeaconsInRegion(region);
        }catch (RemoteException e){}

    }

    public double calculateDistance(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }
        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,10);
        }
        else {
            double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;
            return accuracy;
        }
    }
}
