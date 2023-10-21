package com.example.exe201.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exe201.R;
import com.example.exe201.models.Order;
import com.example.exe201.models.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<OrderDetail> detailList;

    public OrderDetailAdapter(Context context, int layout, List<OrderDetail> detailList) {
        this.context = context;
        this.layout = layout;
        this.detailList = detailList;
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        TextView txtMatName = view.findViewById(R.id.tvMaterialName);
        TextView txtMatAmount = view.findViewById(R.id.tvMaterialAmount);

        OrderDetail detail = detailList.get(position);
        txtMatName.setText(detail.getMaterialName());
        txtMatAmount.setText(String.valueOf(detail.getMaterialAmount()));
        return view;
    }
}
