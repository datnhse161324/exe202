package com.example.exe201;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    Button btnChoose;
    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btnChoose= findViewById(R.id.selectLoca);
        Intent intent= getIntent();
        String username= intent.getStringExtra("user");
        location = findViewById(R.id.tvAddress);
        btnChoose.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
            intent1.putExtra("user",username);
            intent1.putExtra("address", location.getText());
            startActivity(intent1);
        });

        location.setText(getIntent().getStringExtra("location"));
    }
}