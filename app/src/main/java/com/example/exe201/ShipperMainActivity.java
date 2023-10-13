package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ShipperMainActivity extends AppCompatActivity {
    ImageView imgOrderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_main);
        imgOrderList = findViewById(R.id.ivOrderList);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        imgOrderList.setOnClickListener(v->{
            Intent viewOrderList= new Intent(getApplicationContext(), ViewOrderActivity.class);
            viewOrderList.putExtra("user",username);
            startActivity(viewOrderList);
        });
    }
}