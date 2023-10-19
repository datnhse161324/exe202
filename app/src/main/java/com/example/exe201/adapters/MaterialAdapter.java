package com.example.exe201.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exe201.R;
import com.example.exe201.models.Material;

import java.util.List;

public class MaterialAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Material> materialList;

    public MaterialAdapter(Context context, int layout, List<Material> materialList){
        this.context= context;
        this.layout= layout;
        this.materialList=materialList;
    }
    @Override
    public int getCount() {
        return materialList.size();
    }

    @Override
    public Object getItem(int i) {
        return materialList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(layout,null);

        TextView txtTen= (TextView) view.findViewById(R.id.tvNameMaterial);
        TextView txtPrice= (TextView) view.findViewById(R.id.tvUnitPrice);

        Material material= materialList.get(i);
        txtTen.setText(material.getMaterialName());
        txtPrice.setText(String.valueOf(material.getUnitPrice()));
        return view;
    }
}
