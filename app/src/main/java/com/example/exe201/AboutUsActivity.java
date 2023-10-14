package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutUsActivity extends AppCompatActivity {
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        btnback=findViewById(R.id.back8);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }
        });
    }
}