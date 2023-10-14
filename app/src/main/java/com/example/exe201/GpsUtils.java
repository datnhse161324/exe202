package com.example.exe201;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class GpsUtils {
    AppCompatActivity activity;

    public GpsUtils(AppCompatActivity activity) {
        this.activity = activity;
    }
    public void statusCheck(){
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean isEnable = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!isEnable)
        {
            buildAlertMessageNoGps();
        }
    }
    public void buildAlertMessageNoGps(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Cho phép sử dụng GPS?")
                .setCancelable(false)
                .setPositiveButton("Có", (dialog, which) -> {
                    activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                })
                .setNegativeButton("Không",(dialog, which) ->{
                    dialog.cancel();
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
