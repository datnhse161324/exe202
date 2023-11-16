package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout imgSet, imgVoucher, imgMaterial, imgMyVoucher, imgMyOrder, imgAboutUs;
    ImageView imgProfile, imgPre;

    DBHelper DB;

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
        imgPre = findViewById(R.id.vip);
        DB= new DBHelper(this);

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

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogVip(username);
            }
        });
    }

    private void dialogVip(String username){
        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_vip);

        Button btnHuy= dialog.findViewById(R.id.buttonHuy2);
        ImageView imgMua= dialog.findViewById(R.id.imMomo);

        imgMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check= DB.updateVip(username);
                if(check== true){
                    Toast.makeText(MainActivity.this, "Đã đăng ký gói Premium thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}