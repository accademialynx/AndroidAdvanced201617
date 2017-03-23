package com.lynxspa.androidadvanced201617.BeaconDir;

import android.app.Activity;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.profileDir.Profilo;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeaconActivity extends Activity implements BeaconConsumer{

    private DBHelper mydb;
    private ScanSettings mScanSettings;
    private BeaconManager beaconManager;
    private ListView beaconListView;
    private List<BeaconList> arrayListBeacons=new ArrayList<>();
    private Button confirm;
    private BeaconAdapter beaconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        mydb=DBHelper.getInstance(this);
        beaconListView=(ListView)findViewById(R.id.beaconList);
        confirm=(Button)findViewById(R.id.confirmButtonBeacon);
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
                arrayListBeacons.clear();
                final Bundle profilo = getIntent().getExtras();
                Profilo currentProfile = mydb.getProfileById((Integer) profilo.get("Profilo"));
                BeaconList beacon = null;
                for (Beacon b : collection) {
                    String address = b.getBluetoothAddress();
                    String name = b.getBluetoothName();
                    double distance = b.getDistance();
                    beacon = new BeaconList(name, address, String.valueOf(distance), currentProfile.getId());
                    arrayListBeacons.add(beacon);
                    Log.d("AAAAAAAAAAAAAAAAAAA", address + name + distance);
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(region);
        }catch (RemoteException e){}

        beaconAdapter= new BeaconAdapter(BeaconActivity.this, arrayListBeacons);
        beaconListView.setAdapter(beaconAdapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                final Bundle profilo = getIntent().getExtras();
                Profilo currentProfile = mydb.getProfileById((Integer) profilo.get("Profilo"));
                BeaconList beacon = null;
                for (int i = 0; i < arrayListBeacons.size(); i++) {
                    String name = arrayListBeacons.get(i).getNameBeacon();
                    String addressBeacon = arrayListBeacons.get(i).getAddressBeacon();
                    String distance = arrayListBeacons.get(i).getDistanceBeacon();
                    beacon = new BeaconList(name, addressBeacon, distance, currentProfile.getId());
                    mydb.insertOrUpdateBeacons(beacon);
                }

               // intent.putParcelableArrayListExtra("beacon", (ArrayList<? extends Parcelable>) arrayListBeacons);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

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
