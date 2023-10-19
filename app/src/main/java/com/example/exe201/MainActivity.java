package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout imgSet, imgVoucher, imgMaterial, imgMyVoucher, imgMyOrder, imgAboutUs;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgSet= findViewById(R.id.layoutSetCalen);
        imgVoucher= findViewById(R.id.layoutViewVoucher);
        imgProfile= findViewById(R.id.profile);
        imgMaterial= findViewById(R.id.layoutViewMaterial);
        imgMyVoucher= findViewById(R.id.layoutViewMyVoucher);
        imgMyOrder = findViewById(R.id.layoutViewMyOrder);
        imgAboutUs = findViewById(R.id.layoutAboutUs);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        imgSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ViewProfileActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ViewVoucherActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ViewMaterialActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), MyVoucherActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ViewMyOrderActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });

        imgAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), AboutUsActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
            }
        });
    }
}