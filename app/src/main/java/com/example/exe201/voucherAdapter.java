package com.example.exe201;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class voucherAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Voucher> voucherList;

    public voucherAdapter(Context context, int layout, List<Voucher> voucherList){
        this.context= context;
        this.layout= layout;
        this.voucherList=voucherList;
    }
    @Override
    public int getCount() {
        return voucherList.size();
    }

    @Override
    public Object getItem(int i) {
        return voucherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(layout,null);

        TextView txtTen= (TextView) view.findViewById(R.id.tvNameVoucher);
        TextView txtExpiredDate= (TextView) view.findViewById(R.id.tvExprirdDate);
        TextView txtMota= (TextView) view.findViewById(R.id.tvDescription);
        TextView txtPrice= (TextView) view.findViewById(R.id.tvPrice);

        Voucher voucher= voucherList.get(i);
        txtTen.setText(voucher.getVoucherName());
        txtExpiredDate.setText(voucher.getExpiredDate());
        txtMota.setText(voucher.getDescription());
        txtPrice.setText(String.valueOf(voucher.getVoucherPrice()));
        return view;
    }
}
