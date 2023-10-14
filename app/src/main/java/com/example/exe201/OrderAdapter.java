package com.example.exe201;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Order> orderList;

    public OrderAdapter(Context context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtCode = (TextView) view.findViewById(R.id.tvOrderCode);
        TextView txtCreDate = (TextView) view.findViewById(R.id.tvCreateDate);
        TextView txtOrderDate = (TextView) view.findViewById(R.id.tvOrderDate);
        TextView txtOrderTime = (TextView) view.findViewById(R.id.tvOrderTime);
        TextView txtAddress = (TextView) view.findViewById(R.id.tvAddress);
        TextView txtStatus = (TextView) view.findViewById(R.id.tvStatus);

        Order order = orderList.get(position);
        txtCode.setText(order.getOrderCode());
        txtCreDate.setText(order.getCreateDate());
        txtOrderDate.setText(order.getOrderDate());
        txtOrderTime.setText(order.getOrderTime());
        txtAddress.setText(order.getAddress());
        txtStatus.setText(order.getStatus());
        if(context instanceof  ViewOrderActivity){
            Button btnStatus = view.findViewById(R.id.btnStatus);
            Button btnGuide = view.findViewById((R.id.btnGuide));
            btnStatus.setOnClickListener(v->{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn muốn thay đổi trạng thái của đơn này?")
                        .setCancelable(true)
                        .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper DB = new DBHelper(context);
                                DB.updateOrderStatus(order.getOrderCode(),"finished");
                                dialogInterface.cancel();
                                Intent intent = ((ViewOrderActivity) context).getIntent();
                                context.startActivity(intent);
                                ((ViewOrderActivity) context).finish();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn muốn đường đi tới địa chỉ này?")
                        .setCancelable(true)
                        .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String destination = order.getAddress();
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
            if(!txtStatus.getText().toString().equals("waiting")){
                btnStatus.setVisibility(View.GONE);
            }
        }
        return view;
    }
    private void getDirection(String to){
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+to);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
