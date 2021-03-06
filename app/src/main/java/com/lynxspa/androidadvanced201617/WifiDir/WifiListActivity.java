package com.lynxspa.androidadvanced201617.WifiDir;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esami on 07/03/2017.
 */
public class WifiListActivity extends Activity
{
    WifiManager wifi;
    ListView wifiListView;
    Button buttonSetWifi;
    int size = 0;
    List<ScanResult> results;

    List<WifiList> arraylistWifi=new ArrayList<>();
    WifiAdapter wifiAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);

        wifiListView = (ListView)findViewById(R.id.listWifi);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
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
                        WifiList wifiList=new WifiList(results.get(size).SSID,results.get(size).BSSID,signal+"%");
                        arraylistWifi.add(wifiList);
                        size--;

                    }
                } catch (Exception e) {
                }

                wifiAdapter = new WifiAdapter(WifiListActivity.this, arraylistWifi);
                wifiListView.setAdapter(wifiAdapter);
                results = wifi.getScanResults();
                size = results.size();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));


    }
}
