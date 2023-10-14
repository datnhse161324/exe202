package com.example.exe201;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(layout,null);

        TextView txtCode = (TextView) view.findViewById(R.id.tvOrderCode);
        TextView txtCreDate = (TextView) view.findViewById(R.id.tvCreateDate);
        TextView txtOrderDate = (TextView) view.findViewById(R.id.tvOrderDate);
        TextView txtOrderTime = (TextView) view.findViewById(R.id.tvOrderTime);
        TextView txtAddress = (TextView) view.findViewById(R.id.tvAddress);
        TextView txtStatus = (TextView) view.findViewById(R.id.tvStatus);

        Order order= orderList.get(position);
        txtCode.setText(order.getOrderCode());
        txtCreDate.setText(order.getCreateDate());
        txtOrderDate.setText(order.getOrderDate());
        txtOrderTime.setText(order.getOrderTime());
        txtAddress.setText(order.getAddress());
        txtStatus.setText(order.getStatus());
        return view;
    }
}
