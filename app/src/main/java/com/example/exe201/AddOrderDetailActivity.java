package com.example.exe201;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.exe201.adapters.MaterialAdapter;
import com.example.exe201.models.Material;
import com.example.exe201.models.OrderDetail;

import java.util.ArrayList;

public class AddOrderDetailActivity extends AppCompatActivity {
    ImageView btnBack;
    ListView lvMaterial;
    DBHelper DB;
    ArrayList<Material> arrayMaterial;
    MaterialAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_detail);

        btnBack= findViewById(R.id.back4);
        lvMaterial= findViewById(R.id.lvMaterial);
        DB= new DBHelper(this);
        arrayMaterial= new ArrayList<>();
        adapter=new MaterialAdapter(this, R.layout.material_row,arrayMaterial);
        lvMaterial.setAdapter(adapter);
        getView();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lvMaterial.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn muốn chọn vật liệu này?")
                    .setCancelable(true)
                    .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent= new Intent(AddOrderDetailActivity.this, ConfirmOrderActivity.class);
                            Material material = arrayMaterial.get(position);
                            intent.putExtra(ConfirmOrderActivity.MATERIAL_NAME,material);
                            setResult(RESULT_OK, intent);
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
    }
    private void getView() {
        DB.queryData("Create Table if not exists Material (materialID Integer Primary Key Autoincrement," +
                "materialName nvarchar(20), unitPrice Integer)");
//        DB.queryDataMaterial();
        Cursor dataMaterial= DB.getVMaterial();
        if(dataMaterial.getCount()<=0){
            DB.queryDataMaterial();
            getView();
        }
        arrayMaterial.clear();
        while (dataMaterial.moveToNext()){
            String ten= dataMaterial.getString(1);
            int gia= dataMaterial.getInt(2);
            int id= dataMaterial.getInt(0);
            arrayMaterial.add(new Material(id, ten, gia));
        }
        adapter.notifyDataSetChanged();
    }
}