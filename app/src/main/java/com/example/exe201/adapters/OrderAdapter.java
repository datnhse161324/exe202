package com.example.exe201.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exe201.ConfirmOrderActivity;
import com.example.exe201.DBHelper;
import com.example.exe201.R;
import com.example.exe201.ViewOrderActivity;
import com.example.exe201.models.Order;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Order> orderList;
    DBHelper DB;
    public OrderAdapter(Context context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
        DB = new DBHelper(context);
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
//        TextView txtPoint = (TextView) view.findViewById(R.id.tvOrderPoint);

        Order order = orderList.get(position);
        txtCode.setText(order.getOrderCode());
        txtCreDate.setText(order.getCreateDate());
        txtOrderDate.setText(order.getGetDate());
        txtOrderTime.setText(order.getGetTime());
        txtAddress.setText(order.getGetAddress());
        txtStatus.setText(order.getStatus());
//        txtPoint.setText(String.valueOf(order.getOrderPoint()));
        if(context instanceof ViewOrderActivity){
            Button btnStatus = view.findViewById(R.id.btnStatus);
            Button btnGuide = view.findViewById((R.id.btnGuide));
//            btnStatus.setOnClickListener(v->{
//                Intent confirm = new Intent(context, ConfirmOrderActivity.class);
//                confirm.putExtra(ConfirmOrderActivity.Order,order);
//                context.startActivity(confirm);
//            });
            btnStatus.setOnClickListener(v->{
                String name= order.getUserName();
                if(order.getStatus().equals("waiting")){
                    dialogThem(name);
                    DB.updateOrderStatus(order.getOrderCode(),"finished");
                }else {
                    Toast.makeText(context, "Đơn đã hoàn tất", Toast.LENGTH_SHORT).show();
                }
            });
            btnGuide.setOnClickListener(v->{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
    private void dialogThem(String name){
        Dialog dialog= new Dialog(context);
        dialog.setContentView(R.layout.dialog_price);
        Cursor res= DB.getData(name);

        EditText editTen= (EditText) dialog.findViewById(R.id.editTextSua);
        Button btnThem= (Button) dialog.findViewById(R.id.buttonSua);
        Button btnTHuy= (Button) dialog.findViewById(R.id.buttonHuy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten =editTen.getText().toString();
                if(ten.equals("")){
                    Toast.makeText(context,"Vui lòng nhập số tiền !",Toast.LENGTH_SHORT).show();
                }else {
                    int temp=0;
                    temp= Integer.parseInt(ten);
                    if(res.getCount()== 0){
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int myPoint=0;
                    while (res.moveToNext()){
                        myPoint= res.getInt(9);
                    }
                    int a= myPoint+ (temp/10);
                    DB.updatePoint(name,a);
                    Toast.makeText(context,"Đã hoàn tất đơn",Toast.LENGTH_SHORT).show();
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
