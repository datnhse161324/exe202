package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exe201.adapters.OrderDetailAdapter;
import com.example.exe201.models.Material;
import com.example.exe201.models.Order;
import com.example.exe201.models.OrderDetail;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity {

    Button btnGuide, btnStatus, btnAddOrderDetail;
    ImageView btnBack;
    ListView lvOrderDetail;
    ArrayList<OrderDetail> arrayList;
    ArrayList<Material> arrayMaterial;
    OrderDetailAdapter adapter;
    DBHelper DB;
    public static final String Order = "order";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        btnStatus = findViewById(R.id.btnStatus);
        btnGuide = findViewById((R.id.btnGuide));
        btnAddOrderDetail = findViewById(R.id.btnAddOrderDetail);
        lvOrderDetail = findViewById(R.id.lvOrderDetailList);
        btnBack= findViewById(R.id.back4);
        DB= new DBHelper(this);
        arrayList = new ArrayList<>();
        adapter = new OrderDetailAdapter(this, R.layout.order_detail_row, arrayList);
        lvOrderDetail.setAdapter(adapter);

        TextView txtCode = findViewById(R.id.tvOrderCode);
        TextView txtCreDate = findViewById(R.id.tvCreateDate);
        TextView txtOrderDate = findViewById(R.id.tvOrderDate);
        TextView txtOrderTime = findViewById(R.id.tvOrderTime);
        TextView txtAddress = findViewById(R.id.tvAddress);
        TextView txtStatus = findViewById(R.id.tvStatus);
        TextView txtPoint = findViewById(R.id.tvOrderPoint);

        Order order = getIntent().getParcelableExtra(Order);
        txtCode.setText(order.getOrderCode());
        txtCreDate.setText(order.getCreateDate());
        txtOrderDate.setText(order.getGetDate());
        txtOrderTime.setText(order.getGetTime());
        txtAddress.setText(order.getGetAddress());
        txtStatus.setText(order.getStatus());
        btnStatus.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn thay đổi trạng thái của đơn này?")
                    .setCancelable(true)
                    .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBHelper DB = new DBHelper(ConfirmOrderActivity.this);
                            DB.updateOrderStatus(order.getOrderCode(),"finished");
                            dialogInterface.cancel();
                            Intent intent = getIntent();
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });
        btnGuide.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn đường đi tới địa chỉ này?")
                    .setCancelable(true)
                    .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String destination = order.getGetAddress();
                            getDirection(destination);
                            dialogInterface.cancel();
                        }
                    })
                    .setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddOrderDetail.setOnClickListener(v->{
            adapter.notifyDataSetChanged();
        });
    }

    private void getDirection(String to){
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+to);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    private void getMaterial(){
        DB.queryData("Create Table if not exists Material (materialID Integer Primary Key Autoincrement," +
                "materialName nvarchar(20), unitPrice Integer)");
        Cursor dataMaterial= DB.getVMaterial();
        if(dataMaterial.getCount()<=0){
            DB.queryDataMaterial();
            dataMaterial= DB.getVMaterial();
        }
        arrayMaterial.clear();
        while (dataMaterial.moveToNext()){
            String ten= dataMaterial.getString(1);
            int gia= dataMaterial.getInt(2);
            int id= dataMaterial.getInt(0);
            arrayMaterial.add(new Material(id, ten, gia));
        }
    }
}