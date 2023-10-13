package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewMyOrderActivity extends AppCompatActivity {
    ImageView btnBack;
    DBHelper DB;
    ListView lvOrderList;
    OrderAdapter adapter;
    ArrayList<Order> arrayOrder;
    int location;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_order);

        btnBack= findViewById(R.id.back4);
        lvOrderList = findViewById(R.id.lvOrderList);
        DB= new DBHelper(this);
        arrayOrder = new ArrayList<>();
        adapter = new OrderAdapter(this, R.layout.order_row, arrayOrder);
        lvOrderList.setAdapter(adapter);

        Intent intent= getIntent();
        String username= getIntent().getStringExtra("user");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getView(username);
    }

    private void getView(String username){
        Cursor cursor= DB.getMyOrder(username);
        arrayOrder.clear();
        while (cursor.moveToNext()){
            int orderId = cursor.getInt(0);
            String orderCode = cursor.getString(1);
            String userName = cursor.getString(2);
            double materialAmount = cursor.getDouble(3);
            String createDate = cursor.getString(4);
            String status = cursor.getString(5);
            String address = cursor.getString(6);
            String orderDate = cursor.getString(7);
            String orderTime = cursor.getString(8);
            arrayOrder.add(new Order(orderId, orderCode, userName, materialAmount, createDate, status,address,orderDate, orderTime));
        }
        adapter.notifyDataSetChanged();
    }
}