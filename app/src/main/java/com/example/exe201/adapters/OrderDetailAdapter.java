package com.example.exe201.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exe201.R;
import com.example.exe201.models.OrderDetail;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<OrderDetail> detailList;
    View view;
    public OrderDetailAdapter(Context context, int layout, List<OrderDetail> detailList) {
        this.context = context;
        this.layout = layout;
        this.detailList = detailList;
    }
//
//    @Override
//    public int getCount() {
//        return detailList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return detailList.get(position);
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail detail = detailList.get(position);
        holder.txtMatName.setText(detail.getMaterialName());
        holder.txtMatAmount.setTag(position);
//        holder.txtMatAmount.setText(String.valueOf(detail.getMaterialAmount()));
    }
    @Override
    public int getItemCount() {
        return detailList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMatName;
        EditText txtMatAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatName = itemView.findViewById(R.id.tvMaterialName);
            txtMatAmount = itemView.findViewById(R.id.tvMaterialAmount);
            txtMatAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(txtMatAmount.getTag()!=null){
                    OrderDetail orderDetail = detailList.get((int)txtMatAmount.getTag());
                    orderDetail.setMaterialAmount(Double.parseDouble(String.valueOf(txtMatAmount.getText())));
                    detailList.set((int)txtMatAmount.getTag(), orderDetail);
                    notifyItemChanged((int)txtMatAmount.getTag());
                    }
                    return false;
                }
            });
        }
    }
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(layout, null);
//        TextView txtMatName = view.findViewById(R.id.tvMaterialName);
//        TextView txtMatAmount = view.findViewById(R.id.tvMaterialAmount);
//
//        OrderDetail detail = detailList.get(position);
//        txtMatName.setText(detail.getMaterialName());
//        txtMatAmount.setText(String.valueOf(detail.getMaterialAmount()));
//        return view;
//    }
}
