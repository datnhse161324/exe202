package com.example.exe201;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exe201.adapters.OrderAdapter;
import com.example.exe201.models.Order;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {
    ImageView btnBack;
    DBHelper DB;
    ListView lvOrderList;
    OrderAdapter adapter;
    ArrayList<Order> arrayOrder;
    int vitri;
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

        lvOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri= i;
                Order order= arrayOrder.get(i);
                String name= order.getUserName();
                if(order.getStatus().equals("waiting")){
                    dialogThem(name);
                    DB.updateOrderStatus(order.getOrderCode(),"done");
                }else {
                    Toast.makeText(ViewOrderActivity.this, "Đơn đã hoàn tất", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getView(){
        DB.queryData("create table if not exists Orders (orderID Integer Primary Key Autoincrement,orderCode nvarchar(20)," +
                "userName nvarchar(20), materialAmount decimal, createDate nvarchar(20), status nvarchar(20), getAddress nvarchar(50)," +
                "getDate nvarchar(20), getTime nvarchar(20), orderPoint Integer,Constraint fk_UserOrder Foreign Key (userName) references User(userName))");
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
        }else{
            Toast.makeText(ViewOrderActivity.this, "Chưa có lịch hẹn", Toast.LENGTH_SHORT).show();
            finish();
        }
        adapter.notifyDataSetChanged();
    }

    private void dialogThem(String name){
        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_price);

        EditText editTen= (EditText) dialog.findViewById(R.id.editTextSua);
        Button btnThem= (Button) dialog.findViewById(R.id.buttonSua);
        Button btnTHuy= (Button) dialog.findViewById(R.id.buttonHuy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten =editTen.getText().toString();
                if(ten.equals("")){
                    Toast.makeText(ViewOrderActivity.this,"Vui lòng nhập số tiền !",Toast.LENGTH_SHORT).show();
                }else {
                    int temp=0;
                    temp= Integer.getInteger(String.valueOf(editTen.getText()));
                    Cursor res= DB.getData(name);
                    if(res.getCount()== 0){
                        Toast.makeText(ViewOrderActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int myPoint=0;
                    while (res.moveToNext()){
                        myPoint= res.getInt(9);
                    }
                    int a= myPoint+ (temp/10);
                    DB.updatePoint(name,a);
                    Toast.makeText(ViewOrderActivity.this,"Đã hoàn tất đơn",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btnTHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}