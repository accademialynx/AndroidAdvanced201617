package com.lynxspa.androidadvanced201617.mapDir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.Display;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.profileDir.Profilo;

import java.util.List;
import java.util.jar.Manifest;

public class MapsActivity extends FragmentActivity implements  OnMapReadyCallback {

    private DBHelper mydb;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Marker mMarker;
    private Circle circle;
    private LatLng myPosition;
    private SeekBar seekBar;
    private Mappa mappa;
    private int idprofilo=0;

    public int getZoomLevel(Circle circle){
        int zoomLevel = 11;
        if (circle!=null){
            double radius = circle.getRadius()+circle.getRadius()/2;
            double scale = radius/500;
            zoomLevel = (int)(16 - Math.log(scale)/Math.log(2));
        }
        return zoomLevel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mydb=DBHelper.getInstance(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        seekBar = (SeekBar) findViewById(R.id.circleZoom);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
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
            mappa = new Mappa(myPosition.toString(), String.valueOf(longitude),String.valueOf(latitude), idprofilo);
        }
        mydb.insertOrUpdateMap(mappa);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         return;
        }

        mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                latitude = latLng.latitude;
                longitude = latLng.longitude;

                myPosition = latLng;//new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, mMap.getMaxZoomLevel() - 5));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));

                if (mMarker != null) {
                    mMarker.remove();
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title("My position"));
                    if (circle != null)
                        circle.remove();
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                } else {
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title("My position"));
                    mMarker.setTag(myPosition.toString());
                }

                Toast.makeText(getApplicationContext(), "" + latitude + " " + longitude, Toast.LENGTH_LONG).show();
            }
    });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                latitude = latLng.latitude;
                longitude = latLng.longitude;

                myPosition = latLng;//new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, mMap.getMaxZoomLevel() - 5));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));

                if (mMarker != null) {
                    mMarker.remove();
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title("My position"));
                    if (circle != null)
                        circle.remove();
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                } else {
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title("My position"));
                    mMarker.setTag(myPosition.toString());
                }

                Toast.makeText(getApplicationContext(), "" + latitude + " " + longitude, Toast.LENGTH_LONG).show();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (circle != null)
                    circle.remove();
                if(mMarker!=null)
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                circle.setRadius(progress + 200);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                seekBar.setMax(1000);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
