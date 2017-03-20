package com.lynxspa.androidadvanced201617.BeaconDir;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.lynxspa.androidadvanced201617.R;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by kisho on 14/03/2017.
 */
public class BeaconActivity extends Activity {

   /* private BeaconManager beaconManager;
    private BluetoothLeScanner mbluetoothLeScanner;
    private ScanSettings mScanSettings;
    private ScanRecord mScanRecord;
    private Region region;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private ScanCallback mScanCallBack;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mbluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        mBluetoothLeAdvertiser = mBluetoothAdapter.getBluetoothLeAdvertiser();
    }

        private void setScanFilter(){
        ScanFilter.Builder mScanFilter = new ScanFilter.Builder();
            ByteBuffer mManufacturedData = ByteBuffer.allocate(23);
            ByteBuffer mManuacturedDataMask = ByteBuffer.allocate(24);
            byte [] uuid = getIdAsByte(UUID.fromString("0CF052C297CA407C84F8B62AAC4E9020"));
            mManufacturedData.put(0,(byte)0xBE);
            mManuacturedDataMask.put(1,(byte)0xAC);

            for (int i=2; i<=17; i++){
                mManufacturedData.put(i, uuid[i-2]);
            }
            for (int i=0; i<=17;i++){
                mManuacturedDataMask.put((byte)0x01);
            }
            mBuilder.setManufacuredData(224, mManufacturedData.array(), mManuacturedDataMask.array());
            mScanFilter = mBuilder.build();

            mbluetoothLeScanner.startScan(Arrays.asList(mScanFilter),mScanSettings, mScanCallBack);
    }*/





   /*     beaconManager = BeaconManager.getInstanceForApplication(this);

        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);


        bluetoothLeScanner.startScan(new ArrayList<ScanFilter>(), mScanSettings, new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                ScanRecord mScanRecord = result.getScanRecord();
                String deviceName = mScanRecord.getDeviceName();
                int mRssi = result.getRssi();
            }
        });


        iBeaconManager.setRangeNotifier(new RangeNotifier(){
        @Override
                public void onBeaconServiceCOnnect(){
            beaconManager.addRangeNotifier(new RangerNotifier(){
               @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region){
                   if (iBeacons.size() > 0) {
                       double distance = iBeacons.iterator().next().getAccuracy();
                       DecimalFormat decimalFormat = new DecimalFormat("##.##");
                       double distanceFormatted = Double.valueOf(decimalFormat.format(distance));
                       runOnUiThread(new Runnable() {
               }
            });
            try {
                beaconManager.startRingingBeaconsInRegion(new Region("myMonitoringUniqueId",null,null,null));
            }catch (RemoteException e){

            }
        });
    }

        /*@Override
        public void onBeaconServiceConnect() {
            Log.i(TAG,"1");
            beaconManager.setRangeNotifier(new RangeNotifier() {
                @Override
                public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                    Log.i(TAG, "2");
                    if (beacons.size() > 0) {
                        Log.i(TAG, "Im Interested in this Beacon: " + beacons.iterator().next().getId1());
                    }
                }
            });

            try {
                Log.i(TAG, "3");
                beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
            } catch (RemoteException e) {
                Log.i(TAG, "4");

            }}}

    private void setScanSettings (){
        ScanSettings.Builder mbuilder = new ScanSettings.Builder();
        mbuilder.setReportDelay(0);
        mbuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
        mScanSettings = mbuilder.build();
    }

    private  void setScanMode(){
        ScanSettings.Builder mBuilder = new ScanSettings.Builder();
        mBuilder.setReportDelay(0);
        mBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
        mScanSettings = mBuilder.build();
    }
        */

    }
