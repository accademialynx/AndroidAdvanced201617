package com.lynxspa.androidadvanced201617.map;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.profile.Profilo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private DBHelper mydb;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Marker mMarker;
    private Circle circle;
    private LatLng myPosition;
    private SeekBar seekBar;
    private Mappa mappa;
    private int idprofilo = 0;
    private String cityName = "";
    private LocationManager mLocationManager;

    public int getZoomLevel(Circle circle) {
        int zoomLevel = 11;
        if (circle != null) {
            double radius = circle.getRadius() + circle.getRadius() / 2;
            double scale = radius / 500;
            zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mydb = DBHelper.getInstance(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        seekBar = (SeekBar) findViewById(R.id.circleZoom);
        mLocationManager=(LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if (myPosition != null) {
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
                mappa = new Mappa(cityName, String.valueOf(longitude), String.valueOf(latitude), idprofilo);
            }
            mydb.insertOrUpdateMap(mappa);
            setResult(Activity.RESULT_OK, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, null);
        }
        finish();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission
                        (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }



        Location userLocation=null;
        if(mLocationManager!=null){
            userLocation=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            myPosition = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, mMap.getMaxZoomLevel() - 5));
            mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            circle = mMap.addCircle(new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));
        }else{
            mLocationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    myPosition= new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, mMap.getMaxZoomLevel() - 5));
                    mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    circle = mMap.addCircle(new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            },Looper.myLooper());
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

                Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 1);
                    if (addresses.size() > 0) {
                        cityName = addresses.get(0).getLocality();
                    } else
                        return;
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (mMarker != null) {
                    mMarker.remove();
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    if (circle != null)
                        circle.remove();
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                } else {
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
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

                Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(myPosition.latitude, myPosition.longitude, 1);
                    if (addresses.size() > 0) {
                        cityName = addresses.get(0).getLocality();
                    } else
                        return;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (mMarker != null) {
                    mMarker.remove();
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    if (circle != null)
                        circle.remove();
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                } else {
                    mMarker = mMap.addMarker(new MarkerOptions().position(myPosition).title(cityName).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }

                Toast.makeText(getApplicationContext(), "" + latitude + " " + longitude, Toast.LENGTH_LONG).show();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (circle != null)
                    circle.remove();
                if (mMarker != null) {
                    circle = mMap.addCircle((new CircleOptions().center(myPosition).radius(200).strokeColor(Color.RED)));
                    circle.setRadius(progress + 200);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, getZoomLevel(circle)));
                }
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
