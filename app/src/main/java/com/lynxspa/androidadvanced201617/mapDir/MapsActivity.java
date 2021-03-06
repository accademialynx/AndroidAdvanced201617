package com.lynxspa.androidadvanced201617.mapDir;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.Display;
import android.widget.SeekBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lynxspa.androidadvanced201617.R;

import java.util.jar.Manifest;

public class MapsActivity extends FragmentActivity implements  OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Marker mMarker;
    private Location mLastLocation;
    SeekBar circleZoom;
    private Circle circle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myPosition = new LatLng(0, 0);
        //LatLng myPosition = new LatLng(dLat,dLong);
        // Add a marker in Sydney, Australia, and move the camera.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
         return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager= (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (userLocation != null) {
            myPosition = new LatLng(userLocation.getLatitude(),
                    userLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
                    mMap.getMaxZoomLevel() - 5));

            //Circle circle = mMap.addCircle(new CircleOptions().center(new LatLng(dLat, dLong)).radius(1000));
            //Circle circle = mMap.addCircle(new CircleOptions().center(new LatLng(userLocation.getLatitude(),userLocation.getLongitude() )).radius(500).strokeColor(Color.RED));
            circle = mMap.addCircle((new CircleOptions().center(new LatLng(userLocation.getLatitude(), userLocation.getLongitude())).radius(200).strokeColor(Color.RED)));
            //circleZoom = (SeekBar)findViewById(R.id.circleZoom);
            final SeekBar progress = (SeekBar) findViewById(R.id.circleZoom);
            progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                    seekBar.setMax(1000);
                    circle.setRadius(200+progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    circle.setRadius(seekBar.getProgress());
                }
                @Override
            public void onStopTrackingTouch(final SeekBar seekBar){

                }
            });

            mMap.addMarker(new MarkerOptions().position(myPosition).title("My position"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
        }
    }
}
