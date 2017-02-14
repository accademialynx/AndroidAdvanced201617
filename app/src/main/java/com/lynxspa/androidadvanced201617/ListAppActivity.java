package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_app);

        ListView listView=(ListView)findViewById(R.id.listAppView);

        List<AppList> installedApps = getInstalledApps();
        AppAdapter installedAppAdapter = new AppAdapter(ListAppActivity.this, installedApps);
        listView.setAdapter(installedAppAdapter);
    }


    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                res.add(new AppList(appName));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
