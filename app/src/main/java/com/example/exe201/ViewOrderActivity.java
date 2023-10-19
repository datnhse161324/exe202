package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.exe201.adapters.OrderAdapter;
import com.example.exe201.models.Order;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {
    ImageView btnBack;
    DBHelper DB;
    ListView lvOrderList;
    OrderAdapter adapter;
    ArrayList<Order> arrayOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        btnBack= findViewById(R.id.back4);
        lvOrderList = findViewById(R.id.lvOrderList);
        DB= new DBHelper(this);
        arrayOrder = new ArrayList<>();
        adapter = new OrderAdapter(this, R.layout.order_row, arrayOrder);
        lvOrderList.setAdapter(adapter);
        getView();
        Intent intent= getIntent();
        String username= getIntent().getStringExtra("user");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getView(){
        Cursor cursor= DB.getOrder();
        arrayOrder.clear();
        if(cursor.getCount()>0) {
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
                int orderPoint = cursor.getInt(9);
                arrayOrder.add(new Order(orderId, orderCode, userName, materialAmount, createDate, status,address,orderDate, orderTime, orderPoint));
            }
        }
        adapter.notifyDataSetChanged();
    }

}