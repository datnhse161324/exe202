package com.example.exe201;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exe201.adapters.OrderDetailAdapter;
import com.example.exe201.models.Material;
import com.example.exe201.models.Order;
import com.example.exe201.models.OrderDetail;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    public static final String MATERIAL_NAME="materialName";
    Button btnGuide, btnStatus, btnAddOrderDetail;
    ImageView btnBack;
    RecyclerView lvOrderDetail;
    ArrayList<OrderDetail> arrayList;
    OrderDetailAdapter adapter;
    DBHelper DB;
    ConstraintLayout constraintLayout;
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
        lvOrderDetail.setHasFixedSize(true);
        lvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        constraintLayout = findViewById(R.id.ctConfirm);

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

        launcher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Material material = data.getParcelableExtra(MATERIAL_NAME);
                        arrayList.add(new OrderDetail(order.getOrderCode(), material.getMaterialName(), 0, material));
                        adapter.notifyDataSetChanged();
                    }
                });

        btnStatus.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn thay đổi trạng thái của đơn này?")
                    .setCancelable(true)
                    .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(!arrayList.isEmpty()){
                                DBHelper DB = new DBHelper(ConfirmOrderActivity.this);
                                DB.updateOrderStatus(order.getOrderCode(),"finished");
                                dialogInterface.cancel();
                                finish();
                            }else{
                                Toast.makeText(ConfirmOrderActivity.this, "Bạn chưa thêm material", Toast.LENGTH_SHORT).show();
                            }
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
            Intent intent = new Intent(ConfirmOrderActivity.this, AddOrderDetailActivity.class);
            launcher.launch(intent);
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Snackbar snackbar = Snackbar.make(constraintLayout, "đã xóa thành công khỏi danh sách",Snackbar.LENGTH_LONG);
                snackbar.show();
                int index = viewHolder.getAdapterPosition();
                double value = arrayList.get(index).getMaterial().getUnitPrice()*arrayList.get(index).getMaterialAmount();
                double point = Double.parseDouble(String.valueOf(txtPoint.getText()))- value;
                arrayList.remove(index);
                txtPoint.setText(String.valueOf(point));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(lvOrderDetail);

        Button btnPoint = findViewById(R.id.btnAddPoint);
        btnPoint.setOnClickListener(v->{
            double point = 0;
            for (OrderDetail orderDetail:arrayList) {
                point += orderDetail.getMaterial().getUnitPrice() * orderDetail.getMaterialAmount();
            }
            txtPoint.setText(String.valueOf(point));
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

}