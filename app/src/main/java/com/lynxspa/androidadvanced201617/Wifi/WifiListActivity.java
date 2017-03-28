package com.lynxspa.androidadvanced201617.Wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.profile.Profilo;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends Activity {

    private DBHelper mydb;
    private WifiManager wifi;
    private ListView wifiListView;
    private int size = 0;
    private List<ScanResult> results;
    private Button confirm;
    private int idprofilo=0;
    private List<WifiList> arraylistWifi=new ArrayList<>();
    private WifiAdapter wifiAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mydb=DBHelper.getInstance(this);
        setContentView(R.layout.activity_wifi_list);

        wifiListView = (ListView)findViewById(R.id.listWifi);
        confirm=(Button)findViewById(R.id.confirmButtonWifi);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }


        final Bundle profilo = getIntent().getExtras();
        if(profilo!=null){
            Profilo currentProfile=mydb.getProfileById((Integer) profilo.get("Profilo"));
            idprofilo=currentProfile.getId();
        }else{
            List<Profilo> profili = mydb.getAllProfiles();
            if(!profili.isEmpty() || profili!=null) {
                for (int i = 0; i < profili.size(); i++) {
                    idprofilo = profili.get(i).getId() + 1;
                }
            }
            else{
                idprofilo=1;
            }
        }

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {

                arraylistWifi.clear();
                wifi.startScan();

                try {
                    size = size - 1;
                    while (size >= 0) {
                        int signal=wifi.calculateSignalLevel(results.get(size).level,100);
                        WifiList wifiList=new WifiList(results.get(size).SSID,results.get(size).BSSID,signal+"%",idprofilo);
                        arraylistWifi.add(wifiList);
                        size--;
                    }
                } catch (Exception e) {
                }

                wifiAdapter = new WifiAdapter(WifiListActivity.this, arraylistWifi);
                wifiListView.setAdapter(wifiAdapter);
                results = wifi.getScanResults();
                size = results.size();
                wifi.startScan();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                WifiList wifi = null;
                for (int i = 0; i < arraylistWifi.size(); i++) {
                    String ssid = arraylistWifi.get(i).getSsid();
                    String bssid = arraylistWifi.get(i).getBssid();
                    String signal = arraylistWifi.get(i).getSignal();
                    wifi = new WifiList(ssid, bssid, signal, idprofilo);
                    mydb.insertOrUpdateWifiList(wifi);
                }
                /* devo implementare la parcelable? */
                // intent.putParcelableArrayListExtra("wifi", (ArrayList<? extends Parcelable>) arraylistWifi);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
