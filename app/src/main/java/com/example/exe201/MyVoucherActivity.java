package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exe201.adapters.MyVoucherAdapter;
import com.example.exe201.models.MyVoucher;

import java.util.ArrayList;

public class MyVoucherActivity extends AppCompatActivity {
    ImageView btnBack;
    DBHelper DB;
    ListView lvMyVoucher;
    MyVoucherAdapter adapter;
    ArrayList<MyVoucher> arrayMyVoucher;
    int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        btnBack= findViewById(R.id.back5);
        lvMyVoucher= findViewById(R.id.lvMyVoucher);
        DB= new DBHelper(this);
        arrayMyVoucher= new ArrayList<>();
        adapter= new MyVoucherAdapter(this, R.layout.my_voucher_row, arrayMyVoucher);
        lvMyVoucher.setAdapter(adapter);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        getView();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lvMyVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri=i;
                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData= ClipData.newPlainText("Copy", arrayMyVoucher.get(i).getVoucherCode());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MyVoucherActivity.this, "Đã sao chép mã", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getView() {
        Intent intent= getIntent();
        String username= intent.getStringExtra("user");
        Cursor cursor= DB.getMyVoucher(username);
        arrayMyVoucher.clear();
        while (cursor.moveToNext()){
            String ten= cursor.getString(0);
            String code= cursor.getString(1);
            String mota= cursor.getString(2);
            arrayMyVoucher.add(new MyVoucher(ten, code, mota));
        }
        adapter.notifyDataSetChanged();
    }
}