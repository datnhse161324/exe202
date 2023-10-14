package com.example.exe201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class ViewLocation extends AppCompatActivity implements OnMapReadyCallback{
    final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 500;
    private static final int DEFAULT_CAMERA_ZOOM = 11;
    private static final int DEFAULT_ANIMATION_DURATION = 1000;
    private GoogleMap googleMap;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        checkPermission();
        googleMap.setMyLocationEnabled(true);
        LatLng startLocation = new LatLng(10.762622, 106.660172);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(startLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 10));
    }

    public void checkPermission(){
        if(ContextCompat.checkSelfPermission(ViewLocation.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(ViewLocation.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(ViewLocation.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else{
                ActivityCompat.requestPermissions(ViewLocation.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }
}