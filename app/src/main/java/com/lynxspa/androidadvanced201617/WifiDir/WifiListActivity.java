package com.lynxspa.androidadvanced201617.WifiDir;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends Activity {

    private DBHelper mydb;
    private WifiManager wifi;
    private ListView wifiListView;
    private int size = 0;
    private List<ScanResult> results;
    private Button confirm;

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

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {

                arraylistWifi.clear();
                wifi.startScan();
                int idProfilo=mydb.getAllProfiles().iterator().next().getId();
                try {
                    size = size - 1;
                    while (size >= 0) {
                        int signal=wifi.calculateSignalLevel(results.get(size).level,100);
                        WifiList wifiList=new WifiList(results.get(size).SSID,results.get(size).BSSID,signal+"%",idProfilo);
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
                Intent intent= new Intent();
                intent.putParcelableArrayListExtra("wifi", (ArrayList<? extends Parcelable>) arraylistWifi);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
