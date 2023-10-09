package com.example.exe201;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyVoucherAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MyVoucher> myVoucherList;

    public MyVoucherAdapter(Context context, int layout, List<MyVoucher> myVoucherList) {
        this.context = context;
        this.layout = layout;
        this.myVoucherList = myVoucherList;
    }

    @Override
    public int getCount() {
        return myVoucherList.size();
    }

    @Override
    public Object getItem(int i) {
        return myVoucherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(layout,null);

        TextView txtTen=  (TextView) view.findViewById(R.id.tvMyNameVoucher);
        TextView txtCode=  (TextView) view.findViewById(R.id.tvVoucherCode);
        TextView txtMota= (TextView) view.findViewById(R.id.tvMyDescription);

        MyVoucher myVoucher= myVoucherList.get(i);
        txtTen.setText(myVoucher.getMyVoucherName());
        txtCode.setText(myVoucher.getVoucherCode());
        txtMota.setText(myVoucher.getMyVoucherDescription());
        return view;
    }
}
