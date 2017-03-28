package com.lynxspa.androidadvanced201617.Beacon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.profile.Profilo;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeaconActivity extends Activity implements BeaconConsumer{

    private DBHelper mydb;
    public static final String TAG = "BeaconsEverywhere";
    private BeaconManager beaconManager;
    private String STR_PARSER="m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
    private List<BeaconList> arrayListBeacons =null;
    private int idprofilo=0;
    private BeaconAdapter beaconAdapter;
    private ListView beaconListView;
    private Button confirm;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        mydb=DBHelper.getInstance(this);
        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout(STR_PARSER));
        context=this;

        beaconManager.bind(this);

        beaconListView=(ListView)findViewById(R.id.beaconList);
        confirm=(Button)findViewById(R.id.confirmButtonBeacon);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        final Bundle profilo = getIntent().getExtras();
        if (profilo != null) {
            Profilo currentProfile = mydb.getProfileById((Integer) profilo.get("Profilo"));
            idprofilo = currentProfile.getId();
        } else {
            List<Profilo> profili = mydb.getAllProfiles();
            if (!profili.isEmpty() || profili != null) {
                for (int i = 0; i < profili.size(); i++) {
                    idprofilo = profili.get(i).getId() + 1;
                }
            } else {
                idprofilo = 1;
            }
        }

        final Region region = new Region("myBeacons", null, null, null);

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                BeaconList myBeacon = null;
                arrayListBeacons = new ArrayList<BeaconList>();
                for (Beacon oneBeacon : beacons) {
                    String nameBeacon = oneBeacon.getBluetoothName();
                  //  String distanceBeacon = String.valueOf(oneBeacon.getDistance());
                    String addressBeacon = oneBeacon.getBluetoothAddress();
                    String distanceBeacon = String.valueOf(calculateDistance(oneBeacon.getTxPower(), oneBeacon.getRssi()));
                    myBeacon = new BeaconList(nameBeacon, addressBeacon, distanceBeacon, idprofilo);
                    arrayListBeacons.add(myBeacon);
                }

                if (arrayListBeacons.size() > 0) {
                    beaconAdapter = new BeaconAdapter(BeaconActivity.this, arrayListBeacons);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            beaconAdapter.notifyDataSetChanged();
                            //if() {
                            beaconListView.setAdapter(beaconAdapter);
                            //}
                        }
                    });
                }

            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                BeaconList beacon = null;
                for (int i = 0; i < arrayListBeacons.size(); i++) {
                    String nameBeacon = arrayListBeacons.get(i).getNameBeacon();
                    String addressBeacon = arrayListBeacons.get(i).getAddressBeacon();
                    String distanceBeacon = arrayListBeacons.get(i).getDistanceBeacon();
                    beacon= new BeaconList(nameBeacon, addressBeacon, distanceBeacon, idprofilo);
                    mydb.insertOrUpdateBeacons(beacon);
                }
                /* devo implementare la parcelable? */
                // intent.putParcelableArrayListExtra("wifi", (ArrayList<? extends Parcelable>) arraylistWifi);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    public double calculateDistance(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi * 1.0 / txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            return accuracy;
        }
    }
}
