package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ShipperMainActivity extends AppCompatActivity {
    LinearLayout imgOrderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_main);
        imgOrderList = findViewById(R.id.layoutOrderList);
        checkPermission();
        GpsUtils gpsUtils = new GpsUtils(ShipperMainActivity.this);
        gpsUtils.statusCheck();
        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        imgOrderList.setOnClickListener(v->{
            Intent viewOrderList= new Intent(getApplicationContext(), ViewOrderActivity.class);
            viewOrderList.putExtra("user",username);
            startActivity(viewOrderList);
        });
    }
    public void checkPermission(){
        if(ContextCompat.checkSelfPermission(ShipperMainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(ShipperMainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(ShipperMainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else{
                ActivityCompat.requestPermissions(ShipperMainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }
}